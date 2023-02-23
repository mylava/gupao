package com.gupao.edu.account.server;

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
@MapperScan({"com.gupao.edu.account.server.mapper", "com.gupao.edu.common.dict.mapper","com.gupao.edu.common.area.mapper"})
@ComponentScan(basePackages = {"com.gupao.edu.account", "com.gupao.edu.common"})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.gupao.edu.lotus.client.facade.lotus")
//@EnableFeignClients
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}
}
