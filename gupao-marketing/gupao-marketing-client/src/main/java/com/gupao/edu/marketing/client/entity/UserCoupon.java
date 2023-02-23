package com.gupao.edu.marketing.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户优惠券表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户唯一编码
     */
    private String userUniqueCode;

    /**
     * 优惠券ID
     */
    private Integer couponId;

    /**
     * 获取日期
     */
    private LocalDateTime gainDate;

    /**
     * 有效起始日期
     */
    private LocalDateTime startTime;

    /**
     * 有效截止日期
     */
    private LocalDateTime endTime;

    /**
     * 使用时间
     */
    private LocalDateTime usedTime;

    /**
     * 可用状态 0-已使用 1-未使用 3-已过期
     */
    private Integer availableState;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
