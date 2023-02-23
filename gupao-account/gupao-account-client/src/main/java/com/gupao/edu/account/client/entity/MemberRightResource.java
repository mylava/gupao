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
 * 会员权益资源表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MemberRightResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资源分类：1书籍 2优惠券 3文化衫
     */
    private Integer resourceType;

    /**
     * 关联其他资源ID,比如优惠券模板ID
     */
    private Integer resourceId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源简介
     */
    private String summary;

    /**
     * 资源图片
     */
    private String picture;

    /**
     * 现价，有效价格
     */
    private Integer cost;

    /**
     * 原价，无效价格
     */
    private Integer price;

    /**
     * 资源图文描述
     */
    private String description;

    /**
     * 领取人数
     */
    private Integer receiveNum;

    /**
     * 过期状态：0未过期 1已过期
     */
    private Boolean invalidState;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
