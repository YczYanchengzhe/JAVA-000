package io.github.chengzhe.gitway.router;

import java.util.List;

/**
 * @Auther: yanchengzhe
 * @Date: 2020/11/2 23:38
 * @Description:
 */
public interface Router {

    /**
     * 自定义路由策略
     * @param endPoints ; 所有的后端服务ip
     * @return : 路由ip
     */
    String route(List<String> endPoints);
}
