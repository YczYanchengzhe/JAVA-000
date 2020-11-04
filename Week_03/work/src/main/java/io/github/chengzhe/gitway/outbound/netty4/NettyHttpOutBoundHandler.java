package io.github.chengzhe.gitway.outbound.netty4;

import io.github.chengzhe.gitway.inbound.HttpInboundInitializer;
import io.github.chengzhe.gitway.outbound.NameThreadFactory;
import io.github.chengzhe.gitway.outbound.OutBoundHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/4 23:05
 * @Description:
 */
public class NettyHttpOutBoundHandler implements OutBoundHandler {


    private static Logger logger = LoggerFactory.getLogger(NettyHttpOutBoundHandler.class);

    private Bootstrap client;
    private Channel channel;
    /**
     * 后端真实url
     */
    private String backendUrl;

    public NettyHttpOutBoundHandler(String backendUrl) {
        this.backendUrl = backendUrl.endsWith("/") ? backendUrl.substring(1, backendUrl.length() - 1) : backendUrl;

        //定义线程组 , 处理读写和链接事件
        EventLoopGroup workGroup = new NioEventLoopGroup(4);
        //启动客户端
        client = new Bootstrap();
        //绑定客户端通道
        client.group(workGroup).channel(NioSocketChannel.class);
        //初始化handler处理读写事件
        client.handler(new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
//                channel.pipeline().addLast(new HttpRequestEncoder());
                channel.pipeline().addLast(new HttpResponseDecoder());
//                channel.pipeline().addLast(new HttpClientCodec());
//                channel.pipeline().addLast(new HttpObjectAggregator(65536));
//                channel.pipeline().addLast(new HttpContentDecompressor());
                channel.pipeline().addLast(new HttpClientHandler());

            }
        });


    }

    @Override
    public void handler(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        //执行的处理
        try {
            //连接服务器
//            String host = backendUrl.split("//")[1].split(":")[0];
//            String port = backendUrl.split("//")[1].split(":")[1];
//            channel = client.connect(host, Integer.parseInt(port)).sync().channel();
        } catch (Exception e) {
            logger.error("NettyHttpOutBoundHandler deal error", e);
        }
    }

    private void fetchGet(final FullHttpRequest inBound, final ChannelHandlerContext channelHandlerContext) {
        //发送
        channel.flush();
        //接收
        AttributeKey attributeKey = AttributeKey.valueOf("ServiceData");
        Object o = channel.attr(attributeKey).get();
        if (inBound != null) {
            if (HttpUtil.isKeepAlive(inBound)) {
                channelHandlerContext.write(o).addListener(ChannelFutureListener.CLOSE);
            }
        }
        channelHandlerContext.flush();

    }
}
