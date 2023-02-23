package com.gupao.edu.order.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**处理订单重复提交
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-03 11:32
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {
    /**
     * 设置请求锁定时间
     *
     * @return
     */
    int lockTime() default 5;

}
