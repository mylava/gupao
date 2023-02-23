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
 * 优惠券表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 优惠券描述
     */
    private String description;

    /**
     * 所属学科id:(JAVA、大数据,对应字典表)
     */
    private Integer gradeId;

    /**
     * 优惠方式 1.打折 2. 满减3:立减
     */
    private Integer preferentialWay;

    /**
     * 优惠范围 1：全场 2：指定商品  3：指定科目
     */
    private Integer range;

    /**
     * 优惠券数量
     */
    private Integer amount;

    /**
     * 优惠券已发放数量
     */
    private Integer sendAmount;

    /**
     * 有效时间
     */
    private LocalDateTime validDate;

    /**
     * 优惠券开始使用日期
     */
    private LocalDateTime startTime;

    /**
     * 优惠券截止使用日期
     */
    private LocalDateTime endTime;

    /**
     * 优惠券状态1.启用 2.停用 3.失效
     */
    private Integer status;

    /**
     * 发行方式  1:私密发行 2：批量发行 3: 公开发行
     */
    private Integer issuedWay;

    /**
     * 创建者
     */
    private Integer creatorId;

    /**
     * 更新者
     */
    private Integer updaterId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
