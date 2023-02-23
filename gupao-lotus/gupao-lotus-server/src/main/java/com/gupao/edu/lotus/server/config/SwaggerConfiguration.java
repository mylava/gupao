package com.gupao.edu.lotus.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 13/03/2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gupao.edu.lotus.server.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //文档的标题
                .title("swagger-bootstrap-ui RESTful APIs")
                //描述
                .description("swagger-bootstrap-ui")
                //文档的License信息
                .termsOfServiceUrl("http://localhost:8999/")
                .contact(new Contact("wei zhiqiang", "http://www.abc.com", ""))
                .version("1.0")
                .build();
    }
}
