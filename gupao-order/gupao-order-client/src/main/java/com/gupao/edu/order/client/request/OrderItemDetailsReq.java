package com.gupao.edu.order.client.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author wzq.Jolin
 * @company none
 * @create 2020-03-17 18:23
 */
@ApiModel("订单详情集合")
@Data
public class OrderItemDetailsReq  implements Serializable {
    @ApiModelProperty(value = "商品唯一标识", name = "itemId",  required = true)
    private String itemId;
    @ApiModelProperty(value = "商品图片链接", name = "itemImg",  required = false)
    private String itemImg;
    @ApiModelProperty(value = "商品名称", name = "itemName",  required = true)
    private String itemName;
    @ApiModelProperty(value = "商品类型", name = "itemType", required = true)
    private String itemType;
    @ApiModelProperty(value = "商品规格唯一标识", name = "specId",  required = false)
    private String specId;
    @ApiModelProperty(value = "商品规格名称", name = "specName", required = false)
    private String specName;
    @ApiModelProperty(value = "购买数量，目前只传1", name = "buyCounts",  required = true)
    private Integer buyCounts;
    @ApiModelProperty(value = "商品价格,以分为单位", name = "priceNormal", required = true)
    private Integer priceNormal;
    @ApiModelProperty(value = "报名学科，1-java构架 2-人工智能 3-大数据 4-软件测试..", name = "vipCourseType", required = false)
    private Integer vipCourseType;




}
