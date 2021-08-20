package com.zq.spring.zuul.controller;

import com.zq.spring.zuul.model.ResponseVo;
import com.zq.spring.zuul.service.HelloWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: test
 * @author: zq
 * @date: 2021.08.20
 */
@Slf4j
@RestController
public class HelloWordController {


    @Autowired
    HelloWordService helloWordService;

    @GetMapping
    public String helloworld(){
        log.info("helloworld");
        return "helloword1";
    }

    @GetMapping("/test")
    public String test(){
        log.info("test");
        return "test";
    }

    @GetMapping("/adda/{val}")
    public boolean adda(@PathVariable("val")String a){
        log.info("test");
        return helloWordService.addStr(a);
    }
    @GetMapping("/key/{key}")
    public ResponseVo<Object> getVal(@PathVariable("key") String a){
        log.info("test");
        return helloWordService.getStr(a);
    }
}
