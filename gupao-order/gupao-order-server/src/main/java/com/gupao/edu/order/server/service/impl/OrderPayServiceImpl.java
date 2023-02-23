package com.gupao.edu.order.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.order.client.entity.OrderPay;
import com.gupao.edu.order.server.mapper.OrderPayMapper;
import com.gupao.edu.order.server.service.OrderPayService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class OrderPayServiceImpl extends ServiceImpl<OrderPayMapper, OrderPay> implements OrderPayService {

}
