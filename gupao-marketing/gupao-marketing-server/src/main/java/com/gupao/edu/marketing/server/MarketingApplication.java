package com.gupao.edu.marketing.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 14/03/2020
 */
@ComponentScan(basePackages = {"com.gupao.edu.marketing","com.gupao.edu.common.global"})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MarketingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketingApplication.class, args);
    }
}
