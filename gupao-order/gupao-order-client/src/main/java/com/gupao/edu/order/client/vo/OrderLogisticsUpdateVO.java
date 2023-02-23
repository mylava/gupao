package com.gupao.edu.order.client.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 物流信息表
 * </p>
 *
 * @author lipengfei
 * @since 2020-03-24
 */
@Data
public class OrderLogisticsUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 快递单号 发货后的快递单号
     */
    @ApiModelProperty(value = "发货后的快递单号", name = "trackingNumber",  required = true)
    private String trackingNumber;
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id", name = "orderId",  required = true)
    private String orderId;

    /**
     * 快递公司编号 快递公司编号
     */
    @ApiModelProperty(value = "快递公司编号", name = "trackCompanyCode",  required = true)
    private String trackCompanyCode;




}
