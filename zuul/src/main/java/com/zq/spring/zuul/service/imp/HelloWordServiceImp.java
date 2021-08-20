package com.zq.spring.zuul.service.imp;

import com.zq.spring.zuul.model.ResponseVo;
import com.zq.spring.zuul.service.HelloWordService;
import com.zq.spring.zuul.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Description:
 * @author: zq
 * @date: 2021.08.21
 */
@Service
public class HelloWordServiceImp implements HelloWordService {
    @Autowired
    RedisUtil redisUtil;
    @Override
    public boolean addStr(String val) {
        long num = redisUtil.sSet("test",val);
        return num == 0;
    }

    @Override
    public ResponseVo<Object> getStr(String key) {
        Set<Object> tempSet = redisUtil.sGet("test");
        ResponseVo<Object> a = new ResponseVo<>();
        a.setContent(tempSet);
        a.setMessage(key);
        return a;
    }
}
