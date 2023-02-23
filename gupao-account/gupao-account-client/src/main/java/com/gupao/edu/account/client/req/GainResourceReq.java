package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GainResourceReq {

    @ApiModelProperty(value = "用户全局唯一编码", name = "userUniqueCode", required = true)
    private String userUniqueCode;

    @ApiModelProperty(value = "会员权益资源ID", name = "memberRightResourceId", required = true)
    private Integer memberRightResourceId;

    @ApiModelProperty(value = "关联[会员权益字典]的权益ID", name = "memberRightId", required = true)
    private Integer memberRightId;

    @ApiModelProperty(value = "用户地址ID", name = "userAddressId", required = true)
    private Integer userAddressId;
}
