package com.gupao.edu.answer.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("类别参数")
public class CategoriesReq {
    @ApiModelProperty("类别名称")
    private String type;

}
