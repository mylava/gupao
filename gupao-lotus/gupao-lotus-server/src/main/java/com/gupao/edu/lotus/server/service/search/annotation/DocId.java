package com.gupao.edu.lotus.server.service.search.annotation;

import java.lang.annotation.*;

/**
 * 索引主键
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface DocId {

}
