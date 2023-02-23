package com.gupao.edu.order.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.order.client.entity.OrderSupplement;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单补充表 用于存储第三方渠道的课程生成的订单信息 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface OrderSupplementMapper extends BaseMapper<OrderSupplement> {

}
