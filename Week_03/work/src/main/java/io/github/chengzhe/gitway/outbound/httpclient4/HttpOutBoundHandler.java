package io.github.chengzhe.gitway.outbound.httpclient4;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.conn.NHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/3 08:45
 * @Description:
 */
public class HttpOutBoundHandler {

    private static Logger logger = LoggerFactory.getLogger(HttpOutBoundHandler.class);

    private CloseableHttpAsyncClient httpAsyncClient;
    /**
     * 代理服务
     */
    private ExecutorService proxyService;
    /**
     * 后端真实url
     */
    private String backendUrl;

    public HttpOutBoundHandler(String backendUrl) {
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

        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();

        httpAsyncClient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioReactorConfig)
                .setKeepAliveStrategy(((httpResponse, httpContext) -> 6000))
                .build();
        httpAsyncClient.start();
    }

    public void handler(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        final String url = this.backendUrl + fullHttpRequest.uri();
        //执行的处理
        proxyService.submit(()->fetchGet(fullHttpRequest,ctx,url));
    }

    private void fetchGet(final FullHttpRequest inBound, final ChannelHandlerContext channelHandlerContext, final String uri) {
        final HttpGet httpGet = new HttpGet(uri);
        httpAsyncClient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                handlerResponse(inBound, channelHandlerContext, httpResponse);
            }

            @Override
            public void failed(Exception e) {
                httpGet.abort();
                logger.error("http get failed : ", e);
            }

            @Override
            public void cancelled() {
                httpGet.abort();
                logger.error("http get cancelled ");
            }
        });

    }

    private void handlerResponse(final FullHttpRequest inBound, final ChannelHandlerContext channelHandlerContext, final HttpResponse httpResponse) {
        FullHttpResponse response = null;

        HttpVersion version = HttpVersion.HTTP_1_1;
        HttpResponseStatus status = HttpResponseStatus.OK;
        try {
            //直接返回 value
            String value = "hello world";
            response = new DefaultFullHttpResponse(version, status, Unpooled.wrappedBuffer(value.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application.json");
            response.headers().setInt("Content-length", response.content().readableBytes());

            //透传 body
            byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
            logger.info("get response from outBound  : {} , length is {}", new String(body), body.length);
            response = new DefaultFullHttpResponse(version, status, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application.json");
            response.headers().setInt("Content-length", Integer.parseInt(httpResponse.getFirstHeader("Content-length").getValue()));
            ;

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
