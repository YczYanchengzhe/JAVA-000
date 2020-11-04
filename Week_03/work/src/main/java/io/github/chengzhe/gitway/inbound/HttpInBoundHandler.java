package io.github.chengzhe.gitway.inbound;

import io.github.chengzhe.gitway.filter.Filter;
import io.github.chengzhe.gitway.outbound.OutBoundHandler;
import io.github.chengzhe.gitway.outbound.httpclient4.HttpOutBoundHandler;
import io.github.chengzhe.gitway.outbound.netty4.NettyHttpOutBoundHandler;
import io.github.chengzhe.gitway.outbound.okhttp.OkHttpOutBoundHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/3 08:23
 * @Description:
 */
public class HttpInBoundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInBoundHandler.class);

    private final String proxyService;

    private OutBoundHandler outBoundHandler;

    private Filter filter = new HttpInBoundRequestFilter();

    public HttpInBoundHandler(String proxyService) {
        this.proxyService = proxyService;
//        this.outBoundHandler = new HttpOutBoundHandler(proxyService);
//        this.outBoundHandler = new OkHttpOutBoundHandler(proxyService);
        this.outBoundHandler = new NettyHttpOutBoundHandler(proxyService);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //收到请求
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        logger.info("channelRead 接收请求 , 时间为  {} {} ", localDate, localTime);

        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        String uri = fullHttpRequest.uri();
        logger.info("收到的请求是 : " + uri);
        if (uri.contains("/test")) {
            handlerTest(fullHttpRequest, ctx);
        }

        //此时在 inbound 处理之后 outbound 处理之前 , 进行 inbound filter处理
        filter.filter(fullHttpRequest,ctx);

        outBoundHandler.handler(fullHttpRequest, ctx);
    }

    private void handlerTest(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        String value = "hello world";
        HttpVersion version = HttpVersion.HTTP_1_1;
        HttpResponseStatus status = HttpResponseStatus.OK;
        try {
            response = new DefaultFullHttpResponse(version, status, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
        } catch (Exception e) {
            logger.error("处理器错误", e);
            status = HttpResponseStatus.NO_CONTENT;
            response = new DefaultFullHttpResponse(version, status);
        } finally {
            if (fullHttpRequest != null && response != null) {
                if (HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }

            }
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("调用出现异常 : ", cause);
        ctx.close();
    }

}
