package com.gupao.edu.answer.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 问答
 */
@Data
@ApiModel("全局搜索返回问答数据")
public class QuestionDTO {

    @ApiModelProperty(value = "问题Id", name = "id", position = 1)
    private Integer id ;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户Id", name = "userId", position = 2)
    private Integer userId ;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", name = "title", position = 3)
    private String title ;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容", name = "description", position = 4)
    private String description ;
    /**
     * 价格
     */
    @ApiModelProperty(value = "价格", name = "price", position = 5)
    private Integer price ;
    /**
     * 查看数
     */
    @ApiModelProperty(value = "查看数", name = "views", position = 6)
    private Integer views ;
    /**
     * 收藏次数
     */
    @ApiModelProperty(value = "收藏次数", name = "collections", position = 7)
    private Integer collections ;
    /**
     * 评论次数
     */
    @ApiModelProperty(value = "评论次数", name = "comments", position = 8)
    private Integer comments ;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createdAt", position = 9)
    private Date createdAt ;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateAt", position = 10)
    private Date updatedAt ;
}
