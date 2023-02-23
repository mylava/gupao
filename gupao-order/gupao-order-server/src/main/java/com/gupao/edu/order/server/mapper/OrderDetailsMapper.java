package com.gupao.edu.order.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.order.client.entity.OrderDetails;
import com.gupao.edu.order.client.vo.OrderListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单详情表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface OrderDetailsMapper extends BaseMapper<OrderDetails> {


    public List< OrderListVO > queryOrderDetails(@Param("paramsMap") Map<String, Object> map);
}
