package com.gupao.edu.marketing.client.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 限时优惠活动表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FlashPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 限时优惠活动ID
     */
    private Integer id;

    /**
     * 商品 ID
     */
    private Integer goodsId;

    /**
     * 课程价格
     */
    private BigDecimal coursePrice;

    /**
     * 优惠价格
     */
    private BigDecimal preferentialPrice;

    /**
     * 限时优惠开始使用日期
     */
    private LocalDateTime startTime;

    /**
     * 限时优惠截止使用日期
     */
    private LocalDateTime endTime;

    /**
     * 是否限制 0 不限制 1 限制
     */
    private Integer isLimit;

    /**
     * 可用数量
     */
    private Integer availableQuantity;

    /**
     * 已用数量
     */
    private Integer usedQuantity;

    /**
     * 活动状态 0 未开始 1 进行中 2 已失效
     */
    private Integer status;

    /**
     * 创建者
     */
    private Integer creatorId;

    /**
     * 更新着
     */
    private Integer updaterId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
