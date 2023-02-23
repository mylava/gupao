package com.gupao.edu.answer.server.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-19 21:49
 **/
@Data
@ApiModel(value = "问题查询条件")
public class QuestionConditionReq {
    @ApiModelProperty("标签类型id。- 分割")
    private  String tagIds;
    @ApiModelProperty(value = "排序规则",allowableValues = "0 综合排序 ；1 最新优先； 2 最热优先")
    private String orderBy;
    //@ApiModelProperty(value = "排序规则",allowableValues = "0 综合排序 ；1 高悬赏； 2 待解决；3 已解决； 4 未回答")
    //private Integer questionId;
    @ApiModelProperty(value = "页数")
    private Integer pageNum;
    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;
}
