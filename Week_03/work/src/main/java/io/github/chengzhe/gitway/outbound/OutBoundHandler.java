package io.github.chengzhe.gitway.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/5 00:03
 * @Description:
 */
public interface OutBoundHandler {

    public void handler(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx);
}
