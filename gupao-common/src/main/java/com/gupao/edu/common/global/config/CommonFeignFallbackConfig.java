package com.gupao.edu.common.global.config;

import com.alibaba.csp.sentinel.SphU;
import feign.Feign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.alibaba.sentinel.feign.CommonSentinelFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * comment: 通用的feign fallback配置
 * 配置feign调用时使用的builder，调用自定义的Feign.Builder时，
 * 通过对自定义builder添加代理实现通用的fallback
 *
 * @author: lipengfei
 * @date: 15/03/2020
 */

@Configuration
public class CommonFeignFallbackConfig {
    @Bean
    @Scope("prototype")
    @ConditionalOnClass({SphU.class, Feign.class})
    @ConditionalOnProperty(name = "feign.sentinel.enabled")
    @Primary
    public Feign.Builder feignSentinelBuilder() {
        return CommonSentinelFeign.builder();
    }
}
