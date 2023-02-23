package com.gupao.edu.answer.server.entity.req;

import com.gupao.edu.answer.server.enums.SourceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-19 21:39
 **/
@Data
@ApiModel(value = "点赞参数")
public class SupportReq {

    @ApiModelProperty(value = "来源id")
    private Integer sourceId;
    @ApiModelProperty(value = "来源类型")
    private String sourceType;
    @ApiModelProperty(value = "点赞类型,0:不点赞,1:点赞")
    private int type;
}
