package com.gupao.edu.answer.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("标签")
public class TagsRes {
    @ApiModelProperty("标签ID")
    private Integer id;
    @ApiModelProperty("标签名称")
    private String name;
}
