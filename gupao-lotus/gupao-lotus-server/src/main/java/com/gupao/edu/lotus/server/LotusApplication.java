package com.gupao.edu.lotus.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 14/03/2020
 */
@ComponentScan(basePackages = {"com.gupao.edu.lotus", "com.gupao.edu.common.global"})
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.gupao.edu.lotus.server.mapper")
public class LotusApplication {
    public static void main(String[] args) {
        SpringApplication.run(LotusApplication.class, args);
    }
}
