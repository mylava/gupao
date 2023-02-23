package com.gupao.edu.account.server.facade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 14/03/2020
 */

//@FeignClient(name = "gupao-lotus", fallbackFactory = ConsumerFacadeFallbackFactory.class)
//使用通用的fallback处理，如果需要自己的实现，可以添加fallback配置
@FeignClient(name = "gupao-lotus")
public interface ConsumerFacade {

    @GetMapping("/get")
    String getRemote();

    @GetMapping("/fallbackDemo")
    String fallbackDemo();
}
