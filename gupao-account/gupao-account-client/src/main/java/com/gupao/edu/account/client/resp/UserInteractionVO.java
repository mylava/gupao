package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 14/04/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("互动消息列表项")
public class UserInteractionVO {

    @ApiModelProperty(value = "对方唯一编码", required = true, position = 1)
    private String oppositeUniqueCode;

    @ApiModelProperty(value = "对方昵称", name = "oppositeName",  position = 2)
    private String oppositeName;

    @ApiModelProperty(value = "对方头像", name = "oppositeAvatar",  position = 3)
    private String oppositeAvatar;

    @ApiModelProperty(value = "提醒时间", name = "noticeTime",  position = 4)
    private LocalDate noticeTime;

    @ApiModelProperty(value = "提醒事件类型", name = "sourceType",  position = 5)
    private String sourceType;

    @ApiModelProperty(value = "提醒动作类型", name = "sourceActionType",  position = 6)
    private String sourceActionType;

    @ApiModelProperty(value = "提醒源ID", name = "resourceId",  position = 7)
    private Integer resourceId;

    @ApiModelProperty(value = "提醒标题", name = "subject",  position = 8)
    private String subject;

    @ApiModelProperty(value = "提醒内容", name = "content",  position = 9)
    private String content;
}
