package com.gupao.edu.order.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.order.client.entity.OrderSupplement;
import com.gupao.edu.order.server.mapper.OrderSupplementMapper;
import com.gupao.edu.order.server.service.OrderSupplementService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单补充表 用于存储第三方渠道的课程生成的订单信息 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class OrderSupplementServiceImpl extends ServiceImpl<OrderSupplementMapper, OrderSupplement> implements OrderSupplementService {

}
