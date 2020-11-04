package io.github.chengzhe.gitway.outbound.okhttp;

import io.github.chengzhe.gitway.outbound.NameThreadFactory;
import io.github.chengzhe.gitway.outbound.httpclient4.HttpOutBoundHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/4 08:32
 * @Description:
 */
public class OkHttpOutBoundHandler {

    private static Logger logger = LoggerFactory.getLogger(OkHttpOutBoundHandler.class);

    private OkHttpClient okHttpClient = null;

    /**
     * 代理服务
     */
    private ExecutorService proxyService;
    /**
     * 后端真实url
     */
    private String backendUrl;

    public OkHttpOutBoundHandler(String backendUrl) {
        this.backendUrl = backendUrl.endsWith("/") ? backendUrl.substring(1, backendUrl.length() - 1) : backendUrl;
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;

        //线程池策略 : 运行 / 丢弃
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize),
                new NameThreadFactory("proxyService"), handler);

        okHttpClient = new OkHttpClient();
    }

    /**
     * 对外暴露的方法 :
     *
     * @param fullHttpRequest
     * @param ctx
     */
    public void handler(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        final String url = this.backendUrl + fullHttpRequest.uri();
        //执行的处理
        proxyService.submit(() -> fetchGet(fullHttpRequest, ctx, url));
    }

    private void fetchGet(final FullHttpRequest inBound, final ChannelHandlerContext channelHandlerContext, final String uri) {
        Request request = new Request.Builder().url(uri).build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                // ... handle failed request
                handlerResponse(inBound, channelHandlerContext, response);
            }
        } catch (Exception e) {
            // ... handle  exception
            exceptionCaught(channelHandlerContext, e);
        }

    }

    /**
     * 把真实服务返回的数据 , 写回给用户
     * @param inBound
     * @param channelHandlerContext
     * @param okHttpResponse
     */
    private void handlerResponse(final FullHttpRequest inBound, final ChannelHandlerContext channelHandlerContext, final Response okHttpResponse) {
        FullHttpResponse response = null;
        HttpVersion version = HttpVersion.HTTP_1_1;
        HttpResponseStatus status = HttpResponseStatus.OK;
        try {
            //直接返回 value
            String myselfResult = "-->okHttpGetWay-->";
            String responseBody = Objects.requireNonNull(okHttpResponse.body()).string();
            // ... do something with response
            logger.info("okHttp response is {}", responseBody);
            myselfResult += responseBody;
            response = new DefaultFullHttpResponse(version, status, Unpooled.wrappedBuffer(myselfResult.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application.json");
            response.headers().setInt("Content-length", response.content().readableBytes());
        } catch (Exception e) {
            logger.error("处理器错误", e);
            status = HttpResponseStatus.NO_CONTENT;
            response = new DefaultFullHttpResponse(version, status);
            exceptionCaught(channelHandlerContext, e);
        } finally {
            if (inBound != null && response != null) {
                if (HttpUtil.isKeepAlive(inBound)) {
                    channelHandlerContext.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    channelHandlerContext.write(response);
                }
            }
            channelHandlerContext.flush();
        }
    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable e) {
        logger.error("cause error : ", e);
        channelHandlerContext.close();
    }

}
