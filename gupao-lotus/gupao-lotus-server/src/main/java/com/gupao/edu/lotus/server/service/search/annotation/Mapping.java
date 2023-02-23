package com.gupao.edu.lotus.server.service.search.annotation;

import com.gupao.edu.lotus.server.service.search.enums.Analyzer;
import com.gupao.edu.lotus.server.service.search.enums.DataType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Mapping {
    /**
     * 字段名称
     */
    String name() default "";
    /**
     * 字段类型
     */
    DataType type() default DataType.TEXT;
    /**
     * 日期转换表达式，多个用 ||
     */
    String format() default "strict_date_optional_time||epoch_millis||yyyy-MM-dd HH:mm:ss";
    /**
     * 索引分词器
     */
    Analyzer analyzer() default Analyzer.STANDARD;
    /**
     * 搜索分词器设置
     */
    Analyzer search_analyzer() default Analyzer.STANDARD;
}
