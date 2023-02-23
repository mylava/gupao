package com.gupao.edu.lotus.client.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 支付表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
public class OrderPayRes implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 商户订单号
     */
    private String merchantOrderId;

    /**
     * 用户唯一编码
     */
    private String userUniqueCode;

    /**
     * 支付方式 1:微信 2:支付宝 3苹果支付
     */
    private Integer payMethod;

    /**
     * 支付状态 1未支付 2支付中 3已支付 4支付失败 5已退款
     */
    private Integer paymentState;

    /**
     * 实际支付金额 实际支付的金额，单位:分
     */
    private Integer cost;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 是否已删除 0否 1是
     */
    private Boolean isDeleted;


}
