package com.zq.spring.zuul.service;

import com.zq.spring.zuul.model.ResponseVo;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: zq
 * @date: 2021.08.20
 */
public interface HelloWordService {

     boolean addStr(String val);

     ResponseVo<Object> getStr(String key);


}
