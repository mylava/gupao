package com.gupao.edu.order.server.annotation;

import java.lang.annotation.*;

/**
 * @program: gupao-parent
 * @description: 自定义 订单快照 注解
 * @Author: <a href="568227120@qq.com">heliang.wang</a>
 * @Date: 2020/3/22 4:50 下午
 * @Version: 1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderSnapshot {
}
