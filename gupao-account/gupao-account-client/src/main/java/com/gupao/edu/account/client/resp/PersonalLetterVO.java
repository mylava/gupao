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
@ApiModel("私信列表项")
public class PersonalLetterVO {

    @ApiModelProperty(value = "对方唯一编码", required = true, position = 1)
    private String oppositeUniqueCode;

    @ApiModelProperty(value = "对方昵称", name = "oppositeName",  position = 2)
    private String oppositeName;

    @ApiModelProperty(value = "对方头像", name = "oppositeAvatar",  position = 3)
    private String oppositeAvatar;

    @ApiModelProperty(value = "最后信息", name = "lastSent",  position = 4)
    private String lastSent;

    @ApiModelProperty(value = "最后发送时间", name = "lastSentTime",  position = 5)
    private LocalDate lastSentTime;
}
