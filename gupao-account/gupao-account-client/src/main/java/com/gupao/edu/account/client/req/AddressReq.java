package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressReq {

    @ApiModelProperty(value = "地址ID,修改地址时为必填项", name = "addressId", required = false)
    private Integer addressId;

    @ApiModelProperty(value = "用户全局唯一编码", name = "userUniqueCode", required = true)
    private String userUniqueCode;

    @ApiModelProperty(value = "收件人姓名", name = "receiver", required = true)
    private String receiver;

    @ApiModelProperty(value = "联系电话", name = "mobile", required = true)
    private String mobile;

    @ApiModelProperty(value = "省份", name = "province", required = false)
    private String province;

    @ApiModelProperty(value = "所在省ID:对应字典表", name = "provinceId", required = true)
    private Integer provinceId;

    @ApiModelProperty(value = "市", name = "city", required = false)
    private String city;

    @ApiModelProperty(value = "所在市ID:对应字典表", name = "cityId", required = true)
    private Integer cityId;

    @ApiModelProperty(value = "区", name = "county", required = false)
    private String county;

    @ApiModelProperty(value = "所在区ID:对应字典表", name = "countyId", required = true)
    private Integer countyId;

    @ApiModelProperty(value = "详细地址", name = "address", required = true)
    private String address;

    @ApiModelProperty(value = "地址默认 1-是 0-否", name = "isDefault", required = true)
    private Boolean isDefault;
}
