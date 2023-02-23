package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 17/04/2020
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("省市区域返回值")
public class AreaResp {

    @ApiModelProperty(value = "区域ID", position = 1)
    private Integer id;

    @ApiModelProperty(value = "区域名称", position = 2)
    private String name;

    private List<AreaResp>cityList;
}
