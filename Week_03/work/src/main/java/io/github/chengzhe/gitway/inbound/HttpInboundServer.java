package io.github.chengzhe.gitway.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/3 08:24
 * @Description:
 */
public class HttpInboundServer {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundServer.class);

    private int port;

    private String proxyServer;

    private static final int WORK_THREAD_COUNT = 16;
    private static final int BOSS_THREAD_COUNT = 1;

    public HttpInboundServer(int port, String proxyServer) {
        this.port = port;
        this.proxyServer = proxyServer;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(BOSS_THREAD_COUNT);
        EventLoopGroup workGroup = new NioEventLoopGroup(WORK_THREAD_COUNT);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            //当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
            bootstrap.option(ChannelOption.SO_BACKLOG, 128)
                    //禁用了Nagle算法 , 去掉演延时发包 ,
                    .option(ChannelOption.TCP_NODELAY, true)
                    //启动心跳 : 当设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文。
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    //允许重复使用本地地址和端口
                    .option(ChannelOption.SO_REUSEADDR,true)
                    //接收缓冲区
                    .option(ChannelOption.SO_RCVBUF,32 *1024)
                    //发送缓冲区
                    .option(ChannelOption.SO_SNDBUF,12 * 1024)
                    //支持多个进程或者线程绑定到同一端口
                    .option(EpollChannelOption.SO_REUSEPORT,true)
                    //对于boos创建出来的workEventGroup也保持心跳
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    //使用PooledByteBufAllocator来分配内存 : 共享内存
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpInboundInitializer(this.proxyServer));
            Channel channel = bootstrap.bind(port).sync().channel();
            logger.info("开启netty http 服务 , 监听地址和端口为 http://127.0.0.1 : " + port  + "/");
            channel.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
