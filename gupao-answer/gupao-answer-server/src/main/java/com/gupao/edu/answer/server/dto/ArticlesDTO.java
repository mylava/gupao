package com.gupao.edu.answer.server.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 文章
 */
@Data
@ApiModel("全局搜索返回文章数据")
public class ArticlesDTO {

    @ApiModelProperty(value = " 文章Id")
    @TableId
    private Integer id;
    @ApiModelProperty(value = " 用户id")
    private Integer userId;
    @ApiModelProperty(value = " logo链接")
    private String logo;
    @ApiModelProperty(value = " 分类 ")
    private Integer categoryId;
    @ApiModelProperty(value = " 标题 ")
    private String title;
    @ApiModelProperty(value = " 摘要 ")
    private String summary;
    @ApiModelProperty(value = " 阅读数 ")
    private Integer views;
    @ApiModelProperty(value = " 收藏数 ")
    private Integer collections;
    @ApiModelProperty(value = " 评论数 ")
    private Integer comments;
    @ApiModelProperty(value = " 点赞数 ")
    private Integer supports;
    @ApiModelProperty(value = " 创建时间 ")
    private Date createdAt;
    @ApiModelProperty(value = " 更新时间 ")
    private Date updatedAt;
    /**
     * 操作类型
     */
    @ApiModelProperty(value = " 操作类型 ")
    private Byte optType;
    /**
     * 是否置顶(1:置顶，0:否)
     */
    @ApiModelProperty(value = " 是否置顶(1:置顶，0:否) ")
    private Byte istop;
    /**
     * 置顶时间
     */
    @ApiModelProperty(value = " 置顶时间 ")
    private Date topAt;

    /**
     * 公开区域(all：所有，vip：对vip)
     */
    @ApiModelProperty(value = " 公开区域(all：所有，vip：对vip) ")
    private String openArea;
    /**
     * 不推荐数量
     */
    @ApiModelProperty(value = " 不推荐数量 ")
    private Integer oppositions;
    /**
     * 文章类型（ origina：原创，repost：转载，translated：翻译）
     */
    @ApiModelProperty(value = " 文章类型（ origina：原创，repost：转载，translated：翻译） ")
    private String articleType;


}
