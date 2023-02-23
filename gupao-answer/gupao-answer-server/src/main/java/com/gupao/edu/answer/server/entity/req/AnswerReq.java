package com.gupao.edu.answer.server.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-19 21:36
 **/
@Data
@ApiModel(value = "回答")
public class AnswerReq {
    @ApiModelProperty(value = "问题id")
    private Integer questionId;
    @ApiModelProperty(value = "回答内容")
    private String content;
    @ApiModelProperty(value = "附件地址")
    private List<String> urls;
}
