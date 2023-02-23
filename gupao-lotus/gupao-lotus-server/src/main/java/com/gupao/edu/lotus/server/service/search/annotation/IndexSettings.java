package com.gupao.edu.lotus.server.service.search.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Component
public @interface IndexSettings {

    /**
     * 索引名称
     */
    String name();
    /**
     * 主分片数量
     * @return
     */
    int number_of_shards() default 1;
    /**
     * 备份分片数量
     * @return
     */
    int number_of_replicas() default 1;

}
