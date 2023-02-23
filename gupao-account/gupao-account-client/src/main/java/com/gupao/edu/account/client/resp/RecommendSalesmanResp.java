package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * comment:
 *
 * @author: lipengfei
 * @date : 16/04/2020
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("推荐码获取推荐老师返回值")
public class RecommendSalesmanResp {

    @ApiModelProperty(value = "推荐老师昵称", position = 1)
    private String salesman;

    @ApiModelProperty(value = "推荐老师ID", position = 2)
    private Integer salesmanId;
}
