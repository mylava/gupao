package com.gupao.edu.answer.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties("elasticsearch")
public class ElasticsearchProperties {
    private String host = "127.0.0.1:9200";
    private String username;
    private String password;


}
