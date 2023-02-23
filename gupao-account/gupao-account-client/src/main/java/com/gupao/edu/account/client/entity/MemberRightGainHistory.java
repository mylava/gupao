package com.gupao.edu.account.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权益领取记录表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MemberRightGainHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户全局唯一编码
     */
    private String userUniqueCode;

    /**
     * 关联[会员权益与资源关联表]的权益ID
     */
    private Integer memberRightId;

    /**
     * 对应会员权益资源表ID
     */
    private Integer resourceId;

    /**
     * 资源类型ID
     */
    private Integer categoryId;

    /**
     * 资源类型编码
     */
    private String categoryCode;

    /**
     * 会员ID（领取人）
     */
    private Integer memberId;

    /**
     * 收货地址
     */
    private Integer userAddressId;

    /**
     * 物流状态：1待发货 2已发货 3已收货 4其他
     */
    private Integer deliveryState;

    /**
     * 物流单号
     */
    private String logisticsNo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
