package com.gupao.edu.order.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单主键 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户唯一编码
     */
    private String userUniqueCode;

    /**
     * 订单总价格 订单总价格，单位:分
     */
    private Integer totalAmount;

    /**
     * 实际支付总价格 实际支付总价格，单位:分
     */
    private Integer realPayAmount;

    /**
     * 买家留言
     */
    private String leftMsg;

    /**
     * 订单状态 1待付款 2已付款，待发货 3已发货，待收货 4交易成功 5交易关闭
     */
    private Integer orderState;

    /**
     * 支付成功时间
     */
    private LocalDateTime payTime;

    /**
     * 交易关闭时间
     */
    private LocalDateTime closeTime;

    /**
     * 交易成功时间
     */
    private LocalDateTime successTime;

    /**
     * 渠道 区分三方渠道过来的订单 1:PC，2:APP，3：小程序 4腾讯课堂 5网易课堂
     */
    private Integer channelSource;

    /**
     * 第三方订单号
     */
    private String thirdOrderNo;

    /**
     * 用户优惠券ID,使用json格式保存，兼容叠加使用
     */
    private String userCouponIds;

    /**
     * 逻辑删除状态 1: 删除 0:未删除
     */
    private Integer isDeleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
