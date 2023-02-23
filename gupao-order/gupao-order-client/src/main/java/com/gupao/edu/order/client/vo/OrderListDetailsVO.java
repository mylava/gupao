package com.gupao.edu.order.client.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-03-24 01:29
 */
@Data
public class OrderListDetailsVO implements Serializable {

    @ApiModelProperty(value = "商品唯一标识", name = "itemId",  required = true)
    private String itemId;
    @ApiModelProperty(value = "商品图片链接", name = "itemImg",  required = false)
    private String itemImg;
    @ApiModelProperty(value = "商品名称", name = "itemName",  required = true)
    private String itemName;
    @ApiModelProperty(value = "购买数量，目前只传1", name = "buyCounts",  required = true)
    private Integer buyCounts;
    @ApiModelProperty(value = "商品原始价格,以分为单位", name = "priceNormal", required = true)
    private Integer priceNormal;
    @ApiModelProperty(value = "商品成交价格,以分为单位", name = "priceDiscount", required = true)
    private Integer priceDiscount;
}
