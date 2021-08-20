package com.zq.spring.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.zq.spring.zuul.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @Description: filter
 * @author: zq
 * @date: 2021.08.20
 */
@Component
public class PostFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Autowired
    private RedisUtil redisUtil;
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
        return FilterConstants.POST_TYPE;
    }

    @Override
    public Object run() {
        logger.info("POST_TYPE run");
        System.out.println("POST_TYPE run");
        return null;
    }
}
