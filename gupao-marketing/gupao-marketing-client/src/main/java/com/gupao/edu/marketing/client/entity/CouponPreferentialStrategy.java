package com.gupao.edu.marketing.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 优惠策略表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CouponPreferentialStrategy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 优惠券ID
     */
    private Integer couponId;

    /**
     * 满金额,单位为分
     */
    private Integer fullPrice;

    /**
     * 减金额，单位为分
     */
    private Integer reducePrice;

    /**
     * 打折
     */
    private BigDecimal discount;

    /**
     * 状态 1生效  0失效
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
