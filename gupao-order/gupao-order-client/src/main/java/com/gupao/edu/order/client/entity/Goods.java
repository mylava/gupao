package com.gupao.edu.order.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id 唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联资源ID,如果是课程,就存课程ID
     */
    @ApiModelProperty(value="关联资源ID,如果是课程,就存课程ID", name="resourceId",example = "1", required = true)
    private Integer resourceId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value="商品名称", name="itemName",example = "java课程", required = true)
    private String itemName;

    /**
     * 商品图片（缩略图）
     */
    @ApiModelProperty(value="商品图片（缩略图）", name="itemImg",example = "", required = true)
    private String itemImg;

    /**
     * 商品类型:1课程 2学币 3会员
     */
    @ApiModelProperty(value="商品类型:1课程 2学币 3会员", name="itemType",example = "1", required = true)
    private Integer itemType;

    /**
     * 简介
     */
    @ApiModelProperty(value="简介", name="summary",example = "", required = true)
    private String summary;

    /**
     * 图文详情
     */
    @ApiModelProperty(value="图文详情", name="description",example = "", required = true)
    private String description;

    /**
     * 原始价格 单位:分
     */
    @ApiModelProperty(value="原始价格 单位:分", name="priceNormal",example = "100", required = true)
    private Integer priceNormal;

    /**
     * 会员价 单位分
     */
    @ApiModelProperty(value="会员价 单位分", name="priceVip",example = "100", required = true)
    private Integer priceVip;

    /**
     * 库存数量： -1表示无限
     */
    @ApiModelProperty(value="库存数量： -1表示无限", name="buyCounts",example = "-1", required = true)
    private Integer buyCounts;

    @ApiModelProperty(value="创建时间", name="createTime",example = "", required = true)
    private LocalDateTime createTime;

    @ApiModelProperty(value="更新时间", name="updateTime",example = "", required = true)
    private LocalDateTime updateTime;


}
