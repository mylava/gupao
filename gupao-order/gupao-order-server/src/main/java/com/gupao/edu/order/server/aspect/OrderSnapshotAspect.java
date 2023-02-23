package com.gupao.edu.order.server.aspect;

import com.gupao.edu.common.result.Result;

import com.gupao.edu.order.client.entity.Orders;

import com.gupao.edu.order.server.service.OrderLogisticsService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @program: gupao-parent
 * @description: 订单快照切面类
 * @Author: <a href="568227120@qq.com">heliang.wang</a>
 * @Date: 2020/3/22 4:52 下午
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
public class OrderSnapshotAspect {



    @Resource
    OrderLogisticsService ordersLogsService;

    @Pointcut("@annotation(com.gupao.edu.order.server.annotation.OrderSnapshot)")
    public void updateOrderAspect() {
    }

    /***
     *  @Decsription: 切面，后置返回处理
     *  @Param joinPoint
     *  @Author: <a href="568227120@qq.com">heliang.wang</a>
     *  @Date: 2020/3/22 4:59 下午
     *  @return: java.lang.Object
     */
    @AfterReturning(value = "updateOrderAspect()", returning = "result")
    @Transactional
    public void afterReturning(Object result) {
        log.debug("第一个后置返回通知的返回值：" + result);
        if (result instanceof Result) {
            try {
                Result resultData = (Result) result;
                Object data = resultData.getData();
                if (data instanceof Orders) {
//                    Orders order = (Orders) data;
//                    order ordersLog = new OrdersLogs();
//                    BeanUtils.copyProperties(order, ordersLog);
//                    ordersLogsService.save(ordersLog);
                }
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
    }
}
