package com.gupao.edu.answer.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("类别")
public class CategoriesRes {
    @ApiModelProperty("类别ID")
    private Integer id;
    @ApiModelProperty("类别名称")
    private String name;

    private String icon;
    private String slug;
    private Byte status;

}
