package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 16/04/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("我的黑卡返回值")
public class CardResp {
    @ApiModelProperty(value = "头像", position = 1)
    private String avatar;

    @ApiModelProperty(value = "昵称", position = 2)
    private String nickName;

    @ApiModelProperty(value = "卡号", position = 3)
    private String cardNo;

    @ApiModelProperty(value = "黑卡背景图url", position = 4)
    private String cardImgUrl;

    @ApiModelProperty(value = "黑卡富文本url", position = 5)
    private String cardTextUrl;
}
