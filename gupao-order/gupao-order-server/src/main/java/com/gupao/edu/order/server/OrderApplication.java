package com.gupao.edu.order.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 14/03/2020
 */
@ComponentScan(basePackages = {"com.gupao.edu.order","com.gupao.edu.common.global","com.gupao.edu.common.utils.IdLeaf"})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.gupao.edu.lotus.client.facade.lotus")
@MapperScan(basePackages = "com.gupao.edu.order.server.mapper")
@EnableScheduling       // 开启定时任务
@EnableTransactionManagement
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
