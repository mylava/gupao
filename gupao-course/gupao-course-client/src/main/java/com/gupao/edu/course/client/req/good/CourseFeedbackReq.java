package com.gupao.edu.course.client.req.good;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 客服咨询表
 * </p>
 *
 * @author hduong
 * @since 2020-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class CourseFeedbackReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * vipId
     */
    @ApiModelProperty(value = "vipId", name = "用户id")
    private Integer vipId;

    /**
     * QQ
     */
    @ApiModelProperty(value = "vipQq", name = "用户qq")
    private String vipQq;

    /**
     * 聊天记录
     */
    @ApiModelProperty(value = "remark", name = "聊天记录")
    private String remark;

    /**
     * 图片
     */
    @ApiModelProperty(value = "image", name = "图片")
    private String image;

    /**
     * 班主任ID
     */
    @ApiModelProperty(value = "creatorId", name = "班主任ID")
    private Integer creatorId;

    /**
     * 班主任名称
     */
    @ApiModelProperty(value = "creator", name = "班主任名称")
    private String creator;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "ctime", name = "创建时间")
    private LocalDateTime ctime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "utime", name = "更新时间")
    private LocalDateTime utime;

    /**
     * 是否已删除
     */
    @ApiModelProperty(value = "isDelete", name = "是否已删除")
    private Boolean isDelete;


}
