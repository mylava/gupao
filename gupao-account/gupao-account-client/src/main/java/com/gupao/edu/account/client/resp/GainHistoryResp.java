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
 * @date: 2020/4/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("权益领取记录信息")
public class GainHistoryResp {

    // 数据字典表 dict
    @ApiModelProperty(value = "权益名称", name = "rightName")
    private String rightName;

    //会员权益资源表 member_right_resource
    @ApiModelProperty(value = "领取资源id", name = "resourceId")
    private Integer resourceId;

    @ApiModelProperty(value = "资源名称", name = "name")
    private String name;

    @ApiModelProperty(value = "资源简介", name = "summary")
    private String summary;

    @ApiModelProperty(value = "资源图片", name = "summary")
    private String picture;

    @ApiModelProperty(value = "物流状态：1待发货 2已发货 3已收货 4其他", name = "deliveryState")
    private Integer deliveryState;

    @ApiModelProperty(value = "物流单号", name = "logisticsNo")
    private String logisticsNo;

    @ApiModelProperty(value = "领取时间（创建时间）", name = "receiveTime")
    private LocalDateTime receiveTime;

    //物流信息 UserAddress
    @ApiModelProperty(value = "收件人姓名", name = "receiver")
    private String receiver;

    @ApiModelProperty(value = "联系电话", name = "mobile")
    private String mobile;

    @ApiModelProperty(value = "省份", name = "province")
    private String province;

    @ApiModelProperty(value = "市", name = "city")
    private String city;

    @ApiModelProperty(value = "区", name = "county")
    private String county;

    @ApiModelProperty(value = "详细地址", name = "address")
    private String address;

}
