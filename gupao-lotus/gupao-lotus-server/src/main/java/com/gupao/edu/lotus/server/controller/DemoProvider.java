package com.gupao.edu.lotus.server.controller;

import com.gupao.edu.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 14/03/2020
 */
@RestController
public class DemoProvider {

    @GetMapping("/get")
    public String helloNacos(){
        return "provider response!";
    }

    @GetMapping("/fallbackDemo")
    public Result<String> fallbackDemo() {
        throw new RuntimeException("provider 异常 fallback！");
    }
}
