package com.gupao.edu.answer.server.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-19 21:39
 **/
@Data
@ApiModel(value = "回答查询条件")
public class AnswerConditionReq {

    @ApiModelProperty(value = "问题id")
    private Integer questionId;
    @ApiModelProperty(value = "排序规则 1默认 2最新")
    private Integer orderBy;
    @ApiModelProperty(value = "页数")
    private Integer pageNum;
    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;
}
