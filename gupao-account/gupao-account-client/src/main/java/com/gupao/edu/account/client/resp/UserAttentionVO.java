package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserAttentionVO {

    @ApiModelProperty(value = "关注者唯一编码", name = "userUniqueCode" ,required = true)
    private String userUniqueCode;

    @ApiModelProperty(value = "被关注者唯一编码", name = "attentionUserCode" ,required = true)
    private String attentionUserCode;

    @ApiModelProperty(value = "被关注人头像/粉丝头像", name = "avatar")
    private String avatar;
}
