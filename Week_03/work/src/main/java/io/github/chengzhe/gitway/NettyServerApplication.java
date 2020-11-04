package io.github.chengzhe.gitway;

import io.github.chengzhe.gitway.inbound.HttpInboundServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yanchengzhe
 * @Auther: yanchengzhe
 * @Date: 2020/11/2 23:35
 * @Description: 网关入口
 */
public class NettyServerApplication {

    private static Logger logger = null;
    public final static String GATWAY_NAME = "NIOGetWay";
    public final static String GATWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
        logger = LoggerFactory.getLogger(NettyServerApplication.class);
        //真实后端服务端口
        String proxyServer = System.getProperty("proxyServer", "http://localhost:8801");
        //网关代理层端口
        String proxyPort = System.getProperty("proxyPort", "8888");

        int port = Integer.parseInt(proxyPort);
        System.out.println(GATWAY_NAME + " " + GATWAY_VERSION + "starting...");
        //网关的前置拦截 :
        HttpInboundServer server = new HttpInboundServer(port, proxyServer);
        System.out.println(GATWAY_NAME + " " + GATWAY_VERSION + " start at http://localhost:" + port + " for server : " + proxyServer);
        try {
            server.run();
        } catch (Exception e) {
            logger.error("netty get way run error : ", e);
        }

    }
}
