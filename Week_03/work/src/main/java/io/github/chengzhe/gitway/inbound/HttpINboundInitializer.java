package io.github.chengzhe.gitway.inbound;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/3 08:23
 * @Description: 前置拦截器的初始化操作
 */
public class HttpINboundInitializer extends ChannelInitializer<SocketChannel> {

    private String proxyServer;

    public HttpINboundInitializer(String proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //可以添加证书验证 TODO ??
//        if (sslCtx != null) {
//            pipeline.addLast(sslCtx.newHandler(socketChannel.alloc()));
//        }

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpServerExpectContinueHandler());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        pipeline.addLast(new HttpInBoundHandler(this.proxyServer));
    }
}
