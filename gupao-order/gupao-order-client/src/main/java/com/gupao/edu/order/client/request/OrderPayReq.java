package com.gupao.edu.order.client.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "订单支付请求参数")
@Data
public class OrderPayReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id 订单号
     */
    @ApiModelProperty(value="订单号 ", name="orderNo",example = "144456ABC", required = true)
    private String orderNo;

    @ApiModelProperty(value="用户全局唯一标识", name="userUniqueCode", example = "144456ABC",  required = true)
    private String userUniqueCode;
    /**
     * 支付方式 WX：微信，ALI：支付宝，GPB：咕泡币
     */
    @ApiModelProperty(value="支付方式 1：微信，2：支付宝，3：苹果支付 ", name="payMethod",example = "1", required = true)
    private String payMethod;

    /**
     * 来源渠道 APP:应用APP MINIP: 小程序
     */
    @ApiModelProperty(value="来源渠道 APP:应用APP MINIP: 小程序 ", name="payChannel",example = "APP", required = true)
    private String payChannel;

    /**
     * 实际支付金额 实际支付的金额
     */
    @ApiModelProperty(value="支付金额 单位分 ", name="amount",example = "20", required = true)
    private Integer amount;

}