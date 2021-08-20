package com.zq.spring.zuul.exceptionhandle;

import com.zq.spring.zuul.model.ResponseVo;
import com.zq.spring.zuul.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: Exception切片处理
 * @author: zq
 * @date: 2021.08.20
 */
public class ExceptionAdvice {
    private final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseVo<String> exceptionHandler(HttpServletRequest req, Exception e) {
        try {
            e.printStackTrace();
            logger.info("token:{}", req.getHeader("token"));
            logger.info("ip:{}", IpUtil.getIPAddress(req).trim());
            logger.info("uri:{}", req.getRequestURI());
            logger.info("Exception:{}", e.getMessage());
        } catch (Exception e1) {
            // TODO Auto-generated catch blockz
            e1.printStackTrace();
        }
        return new ResponseVo<String>().error("未知异常");
    }
}
