package com.gupao.edu.order.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.resp.AddressVO;

import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.common.utils.IdLeaf.IdLeafService;
import com.gupao.edu.common.utils.IdLeaf.impl.SnowflakeIdLeafServiceImpl;
import com.gupao.edu.common.utils.JsonUtils;
import com.gupao.edu.marketing.client.dto.CouponDetailDto;
import com.gupao.edu.marketing.client.dto.CouponDto;
import com.gupao.edu.marketing.client.entity.FlashPromotion;
import com.gupao.edu.marketing.client.feign.CouponFeign;
import com.gupao.edu.marketing.client.feign.FlashPromotionFeign;
import com.gupao.edu.marketing.client.req.GetCanUseCouponListReq;
import com.gupao.edu.marketing.client.req.GetCouponDetailReq;
import com.gupao.edu.order.client.entity.*;
import com.gupao.edu.order.client.request.OrderSupplementReq;
import com.gupao.edu.order.client.request.SubmitOrdersReq;
import com.gupao.edu.order.client.response.Orderresponse;
import com.gupao.edu.order.client.vo.OrderDetailsVO;
import com.gupao.edu.order.client.vo.OrderListVO;
import com.gupao.edu.order.client.vo.TransportInformationVO;
import com.gupao.edu.order.server.enums.ItemEnums;
import com.gupao.edu.order.server.enums.OrdersEnums;
import com.gupao.edu.order.server.enums.PaymentEnums;
import com.gupao.edu.order.server.enums.YesOrNo;
import com.gupao.edu.order.server.mapper.OrderDetailsMapper;
import com.gupao.edu.order.server.mapper.OrdersMapper;
import com.gupao.edu.order.server.service.GoodsService;
import com.gupao.edu.order.server.service.OrderLogisticsService;
import com.gupao.edu.order.server.service.OrderSupplementService;
import com.gupao.edu.order.server.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Slf4j
@Service
public class OrdersServiceImpl extends ServiceImpl< OrdersMapper, Orders > implements OrdersService {


    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;


//    @Qualifier("snowflakeIdLeafServiceImpl")
//    @Resource
//    private IdLeafService idLeafService;

    @Autowired
    private SnowflakeIdLeafServiceImpl snowflakeIdLeafService;


    @Autowired
    private GoodsService productsService;
    @Autowired
    private OrderSupplementService ordersSupplementService;

    @Autowired
    private OrderLogisticsService orderLogisticsService;


    @Autowired
    private CouponFeign couponFeign;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Orderresponse createPayOrder(SubmitOrdersReq submitOrdersReq) {
        //创建全局唯一订单号
        String orderNo = String.valueOf( snowflakeIdLeafService.getOrderNo() );
        String[] itemIdArr = submitOrdersReq.getItemId().split( "," );
        //订单总金额
        Integer totalAmount = 0;
        //实际支付金额
        Integer realPayAmount = 0;
        Integer discountAmount = 0;

        CouponDetailDto couponDetail = null;
        if (submitOrdersReq.getCouponId() != null) {
            //获取优惠券信息
            GetCouponDetailReq getCouponDetailReq = new GetCouponDetailReq();
            getCouponDetailReq.setCouponId( submitOrdersReq.getCouponId() );
            couponDetail = couponFeign.getCouponDetail( getCouponDetailReq );

        }


        //1.订单详情快照
        for (String itemId : itemIdArr) {

            Goods product = productsService.getBaseMapper().selectOne( new QueryWrapper< Goods >()
                    .eq( "resource_id", itemId ) );
            //todo 需要判断用户是不是会员
            totalAmount=totalAmount+product.getPriceNormal();
            OrderDetails orderDetails = new OrderDetails();
            BeanUtils.copyProperties( product, orderDetails, "id" );
            orderDetails.setOrderNo( orderNo );
            orderDetails.setCreateTime( LocalDateTime.now() );
            orderDetails.setUpdateTime( LocalDateTime.now() );
            log.info( "订单快照开始添加 orderDetails：{}", JSON.toJSONString( orderDetails ) );
            orderDetailsMapper.insert( orderDetails );
        }
        realPayAmount=totalAmount-discountAmount;

        //2.创建订单
        Orders orders = new Orders();
        orders.setRealPayAmount(realPayAmount);
        orders.setTotalAmount(totalAmount);
        BeanUtils.copyProperties( submitOrdersReq, orders );
        orders.setOrderState( OrdersEnums.OrderStatus.WAIT_PAY.getValue() );
        orders.setOrderNo( orderNo );
        orders.setIsDeleted( YesOrNo.NO.type );
        orders.setCreateTime( LocalDateTime.now() );
        orders.setUpdateTime( LocalDateTime.now() );
        log.info( "准备创建支付订单 orders：{}", JSON.toJSONString( orders ) );
        int flag = ordersMapper.insert( orders );
        if (flag > 0) {
            Orderresponse orderresponse = new Orderresponse();
            orderresponse.setOrders( orders );
            return orderresponse;
        }

        return null;
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Orders createThirdOrder(SubmitOrdersReq submitOrdersReq) {
        //创建全局唯一订单号
        String orderNo = String.valueOf( snowflakeIdLeafService.getOrderNo() );


        //1..三方渠道订单
        OrderSupplement orderSupplement = new OrderSupplement();
        OrderSupplementReq orderSupplementReq = submitOrdersReq.getOrderSupplementReq();
        BeanUtils.copyProperties( orderSupplementReq, orderSupplement );
        orderSupplement.setOrderNo( orderNo );
        orderSupplement.setCreateTime( LocalDateTime.now() );
        boolean save = ordersSupplementService.save( orderSupplement );
        if (save) {
            //2.创建订单
            Orders orders = new Orders();
            BeanUtils.copyProperties( submitOrdersReq, orders );
            orders.setOrderState( OrdersEnums.OrderStatus.WAIT_PAY.getValue() );
            orders.setOrderNo( orderNo );
            orders.setIsDeleted( YesOrNo.NO.type );
            orders.setCreateTime( LocalDateTime.now() );
            orders.setUpdateTime( LocalDateTime.now() );
            log.info( "准备创建第三方订单 orders：{}", JSON.toJSONString( orders ) );
            int flag = ordersMapper.insert( orders );
            if (flag > 0)
                return orders;
        }
        return null;
    }

    /**
     * 计算实际支付价格
     *
     * @param product
     * @param couponDetailDto
     * @return
     */
    private Integer getouponStrategyForRealPrice(Goods product, CouponDetailDto couponDetailDto) {

        return null;
    }

    @Override
    public IPage< OrderListVO > queryOrderList(String orderNo, String userId, String orderStatus, Integer pageNum, Integer pageSize) {
        Page< OrderListVO > orderListVOPage = new Page<>( pageNum, pageSize );
        HashMap< String, Object > map = new HashMap<>();
        map.put( "orderNo", orderNo );
        map.put( "userId", userId );
        map.put( "orderStatus", orderStatus );
        return ordersMapper.queryOrderList( orderListVOPage, map );
    }

    @Override
    public Result< OrderDetailsVO > queryOrderDetails(String orderNo, String userId) {
        Map< String, Object > map = new HashMap<>();
        map.put( "userId", userId );
        map.put( "orderId", orderNo );

        List< OrderListVO > orderListVOS = orderDetailsMapper.queryOrderDetails( map );
        TransportInformationVO transportInformationVO = orderLogisticsService.queryLogistics( orderNo, userId );
        OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
        orderDetailsVO.setTransportInformationVO( transportInformationVO );
        orderDetailsVO.setOrderInfoVOList( orderListVOS );
        return Result.success( orderDetailsVO );
    }

    @Override
    public void deleteOrderInfo(String orderNo, String userUniqueCode) {

        HashMap< String, Object > map = new HashMap<>();
        map.put( "orderNo", orderNo );
        map.put( "userUniqueCode", userUniqueCode );
        map.put( "isDeleted", YesOrNo.YES.type );
        ordersMapper.updateOrderInfo( map );
    }


}
