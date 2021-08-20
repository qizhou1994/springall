package com.zq.spring.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.zq.spring.zuul.util.IpUtil;
import io.micrometer.core.instrument.util.StringUtils;
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
public class PreFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(PreFilter.class);

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
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public Object run() {
        logger.info("PRE_TYPE run");
        System.out.println("PRE_TYPE run");
        RequestContext ctx = RequestContext.getCurrentContext();
        String ip = IpUtil.getIPAddress(ctx.getRequest());
        // 在黑名单中禁用
        if (StringUtils.isNotBlank(ip)  ) {
            ctx.setSendZuulResponse(false);
//            ResponseData data = ResponseData.fail("非法请求 ", ResponseCode.NO_AUTH_CODE.getCode());
//            ctx.setResponseBody(JsonUtils.toJson(data));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        return null;
    }
}
