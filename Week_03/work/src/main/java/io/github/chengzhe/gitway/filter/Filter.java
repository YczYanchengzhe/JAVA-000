package io.github.chengzhe.gitway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author yanchengzhe
 * @Date: 2020/11/2 23:39
 * @Description:
 */
public interface Filter {

    /**
     * 过滤器
     * @param fullHttpRequest
     * @param ctx
     */
    void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx);
}
