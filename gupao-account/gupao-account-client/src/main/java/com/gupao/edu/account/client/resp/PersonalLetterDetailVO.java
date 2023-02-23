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
 * @date: 14/04/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("私信详情")
public class PersonalLetterDetailVO {

    @ApiModelProperty(value = "我的昵称", name = "nickName", position = 1)
    private String nickName;

    @ApiModelProperty(value = "我的头像", name = "avatar", position = 2)
    private String avatar;

    @ApiModelProperty(value = "我的唯一编码", required = true, position = 3)
    private String userUniqueCode;

    @ApiModelProperty(value = "对方昵称", name = "oppositeName", position = 4)
    private String oppositeName;

    @ApiModelProperty(value = "对方头像", name = "oppositeAvatar",  position = 5)
    private String oppositeAvatar;

    @ApiModelProperty(value = "消息内容", name = "message",  position = 6)
    private String message;

    @ApiModelProperty(value = "是否为图片", name = "isImage",  position = 7)
    private Boolean isImage;

    @ApiModelProperty(value = "发送时间", name = "sentTime",  position = 8)
    private LocalDateTime sentTime;

    @ApiModelProperty(value = "消息发送者唯一编码", required = true, position = 9)
    private String fromUniqueCode;
}
