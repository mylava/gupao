package com.gupao.edu.answer.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论表(AskComments)实体类
 *
 * @author makejava
 * @since 2020-03-19 18:18:07
 */
@ApiModel("用户评论")
@Data
public class AskComments implements Serializable {
    private static final long serialVersionUID = 489502205476556981L;
     @ApiModelProperty(value = "评论id")
     @TableId
    private Integer id;
     @ApiModelProperty(value = "用户id")
    private Integer userId;
     @ApiModelProperty(value = "内容")
    private String content;
     @ApiModelProperty(value = "评论上级编号")
    private Integer sourceId;
     @ApiModelProperty(value = "评论上级类型")
    private String sourceType;
     @ApiModelProperty(value = "评论对象")
    private Integer toUserId;
     @ApiModelProperty(value = "状态")
    private Byte status;
     @ApiModelProperty(value = "点赞数")
    private Integer supports;
     @ApiModelProperty(value = "是否登录？？")
    private Byte device;
     @ApiModelProperty(value = "创建时间")
    private Date createdAt;
     @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

     private String orderType;
    @ApiModelProperty("页码")
    private int pageNum;
    @ApiModelProperty("页面展示长度")
    private int pageSize;
    public void formatePage() {
        pageNum = pageNum>0?pageNum:1;
        pageSize = pageSize>0?pageSize:10;
    }
    /**
    * 操作类型
    */ @ApiModelProperty(value = " 操作类型 ")
    private Byte optType;
    @ApiModelProperty("子评论")
    private List<AskComments> askComments;
    @ApiModelProperty("子评论数")
    private Integer total;



}