package com.gupao.edu.order.client.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-03-23 12:10
 */
@Data
public class OrderListVO implements Serializable {

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号", name = "orderNo",  required = true)
    private String orderNo;
    /**
     * 实际支付总价格 实际支付总价格，单位:分
     */
    @ApiModelProperty(value = "实际支付总价格，单位:分", name = "realPayAmount",  required = true)
    private Integer realPayAmount;



    /**
     * 订单状态 订单交易的状态
     */
    @ApiModelProperty(value = "订单交易的状态", name = "orderStatus",  required = true)
    private String orderStatus;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createdTime",  required = true)
    private Date createdTime;


    @ApiModelProperty(value = "商品名称", name = "itemName",  required = true)
    private String itemName;




    private List< OrderListDetailsVO >   detailsVOList;




}
