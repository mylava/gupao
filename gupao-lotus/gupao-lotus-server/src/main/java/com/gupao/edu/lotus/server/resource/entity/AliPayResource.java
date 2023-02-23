package com.gupao.edu.lotus.server.resource.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-05-02 11:35
 */
@Component
@ConfigurationProperties(prefix = "alipay")
@PropertySource("classpath:bootstrap.yml")
@Data
public class AliPayResource {

    /**
     * 应用id
     */
    private String appId;


    /**
     * 应用私钥
     */
    private String privateKey;
    /**
     *
     */
    private String publicKey;


    /**
     *
     */
    private String serviceUrl;


    /**
     *
     */
    private String signType;

    /**
     *
     */
    private String charset;

    /**
     *
     */
    private String notifyUrl;
}
