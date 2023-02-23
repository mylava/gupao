package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * comment: 会员权益返回值
 *
 * @author: lipengfei
 * @date: 17/04/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("会员权益返回值")
public class MemberRightsResp {

    @ApiModelProperty(value = "头像", name = "avatar",  position = 1)
    private String avatar;

    @ApiModelProperty(value = "昵称", name = "nickName",  position = 2)
    private String nickName;

    @ApiModelProperty(value = "是否为会员", name = "isMember",  position = 3)
    private Boolean isMember;

    @ApiModelProperty(value = "会员到期时间", name = "invalidTime",  position = 4)
    private LocalDate invalidTime;

    @ApiModelProperty(value = "会员权益列表", name = "invalidTime",  position = 4)
    private List<MemberRightVO> memberRightList;

}
