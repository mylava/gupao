package com.gupao.edu.course.server;

import org.mybatis.spring.annotation.MapperScan;
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
@ComponentScan(basePackages = {"com.gupao.edu.course.server","com.gupao.edu.common.global","com.gupao.edu.common.dict"})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.gupao.edu.account.client.facade.member"})
@MapperScan({"com.gupao.edu.course.server.mapper", "com.gupao.edu.common.dict.mapper"})
public class CourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }
}
