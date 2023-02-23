package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 15/04/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("平台通知模型")
public class SystemAttentionVO {

    @ApiModelProperty(value = "通知类型 1-后台录入通知 2-优惠卷过期 3-收到优惠卷 4-关注课程上架 5-权益到期 6-会员新季度书籍上架", position = 1)
    private Integer type;


    @ApiModelProperty(value = "发送内容id", position = 2)
    private Integer sourceId;

    @ApiModelProperty(value = "标题", position = 3)
    private String subject;


    @ApiModelProperty(value = "内容", position = 4)
    private String content;

    @ApiModelProperty(value = "通知时间", position = 4)
    private LocalDateTime noticeTime;

    @ApiModelProperty(value = "通知类型 1-后台录入通知 2-优惠卷过期 3-收到优惠卷 4-关注课程上架 5-权益到期 6-会员新季度书籍上架", position = 4)
    private String noticeType;

}
