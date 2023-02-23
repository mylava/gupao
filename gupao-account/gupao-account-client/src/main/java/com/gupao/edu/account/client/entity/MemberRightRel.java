package com.gupao.edu.account.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 会员与权益关联表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MemberRightRel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会员ID
     */
    private Integer memberId;

    /**
     * 用户全局唯一编码
     */
    private String userUniqueCode;

    /**
     * 关联[会员权益字典]的权益ID
     */
    private Integer memberRightId;

    /**
     * 领取次数限制
     */
    private Integer gainLimit;

    /**
     * 已领取次数
     */
    private Integer gainTimes;

    /**
     * 权益过期时间
     */
    private LocalDate invalidTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
