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
 * 物流信息表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderLogistics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单主键 同时也是订单编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户唯一编码
     */
    private String userUniqueCode;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 物流单号
     */
    private String logisticsNo;

    /**
     * 物流渠道：比如顺丰、申通
     */
    private String logisticsChannel;

    /**
     * 收货地址ID
     */
    private Integer userAddressId;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人手机号
     */
    private String receiverMobile;

    /**
     * 邮费 默认可以为零，代表包邮
     */
    private Integer logisticsFees;

    /**
     * 发件人ID,关联字典表（json）
     */
    private Integer senderId;

    /**
     * 寄件人姓名
     */
    private String senderName;

    /**
     * 寄件人地址
     */
    private String senderAddress;

    /**
     * 寄件人手机号
     */
    private String senderMobile;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 快递公司编码
     */
    private String trackCompanyCode;


}
