package com.zq.spring.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @Description: filter
 * @author: zq
 * @date: 2021.08.20
 */
@Component
public class RouteFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(RouteFilter.class);

    @Override
    public boolean shouldFilter() {
        // 使能过滤
        return true;
    }

    @Override
    public int filterOrder() {
        // 设置顺序
        return 1;
    }

    @Override
    public String filterType() {
        // 前置过滤
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public Object run() {
        logger.info("ROUTE_TYPE run");
        System.out.println("ROUTE_TYPE run");
        return null;
    }
}
