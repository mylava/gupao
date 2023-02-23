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
 * @date: 2020/4/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户地址信息封装")
public class AddressVO {
    @ApiModelProperty(value = "地址ID", name = "addressId")
    private Integer addressId;

    @ApiModelProperty(value = "收件人姓名", name = "receiver")
    private String receiver;

    @ApiModelProperty(value = "联系电话", name = "mobile")
    private String mobile;

    @ApiModelProperty(value = "省份", name = "province")
    private String province;

    @ApiModelProperty(value = "所在省ID:对应字典表", name = "provinceId")
    private Integer provinceId;

    @ApiModelProperty(value = "市", name = "city")
    private String city;

    @ApiModelProperty(value = "所在市ID:对应字典表", name = "cityId")
    private Integer cityId;

    @ApiModelProperty(value = "区", name = "county")
    private String county;

    @ApiModelProperty(value = "所在区ID:对应字典表", name = "countyId")
    private Integer countyId;

    @ApiModelProperty(value = "详细地址", name = "address")
    private String address;
}
