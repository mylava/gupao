package com.gupao.edu.order.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gupao.edu.order.client.entity.Orders;
import com.gupao.edu.order.client.vo.OrderListVO;
import feign.Param;

import java.util.Map;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface OrdersMapper extends BaseMapper<Orders> {


    /**
     * 订单列表查询
     * @param orderListVOPage
     * @param map
     * @return
     */
    public IPage< OrderListVO >  queryOrderList(IPage< OrderListVO > orderListVOPage , @Param("paramMap") Map<String, Object> map);

    /**
     * 删除订单
     * @param map
     * @return
     */
    Integer updateOrderInfo(@Param("paramMap") Map<String, Object> map );
}
