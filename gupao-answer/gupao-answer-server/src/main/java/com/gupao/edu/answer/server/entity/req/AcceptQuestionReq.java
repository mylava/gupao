package com.gupao.edu.answer.server.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-22 21:07
 **/
@Data
@ApiModel(value = "采纳回答请求参数")
public class AcceptQuestionReq {
    @ApiModelProperty(value = "问题id")
    private Integer questionId;
    @ApiModelProperty(value = "回答id")
    private Integer answerId;
}
