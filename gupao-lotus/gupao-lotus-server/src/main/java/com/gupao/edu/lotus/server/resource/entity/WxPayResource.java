package com.gupao.edu.lotus.server.resource.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-05-02 12:55
 */
@Component
@ConfigurationProperties(prefix = "wxpay")
@PropertySource("classpath:bootstrap.yml")
@Data
public class WxPayResource {

    private String appId;
    private String merchantId;
    private String secrectKey;
    private String spbillCreateIp;
    private String serviceUrl;
    private String tradeType;
    private String notifyUrl;

}
