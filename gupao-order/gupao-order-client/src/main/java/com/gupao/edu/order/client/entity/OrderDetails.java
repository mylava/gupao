package com.gupao.edu.order.client.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 商品id
     */
    private Integer itemId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品图片（缩略图）
     */
    private String itemImg;

    /**
     * 原始价格，单位:分
     */
    private Integer price;

    /**
     * 成交价格，单位:分
     */
    private Integer cost;

    /**
     * 购买数量
     */
    private Integer buyCounts;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
