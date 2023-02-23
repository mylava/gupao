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
@ApiModel("学币商品返回值封装")
public class CoinVO {

    @ApiModelProperty(value = "商品ID", position = 1)
    private Integer goodsId;

    @ApiModelProperty(value = "面额，单位分", position = 2)
    private Integer amount;

    @ApiModelProperty(value = "售价，单位分", position = 3)
    private Integer cost;

    @ApiModelProperty(value = "排序", position = 4)
    private Integer sort;

}
