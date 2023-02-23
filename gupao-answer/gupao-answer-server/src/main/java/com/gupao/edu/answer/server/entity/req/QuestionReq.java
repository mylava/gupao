package com.gupao.edu.answer.server.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-17 21:31
 **/
@Data
@ApiModel(value = "问答")
public class QuestionReq {
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "详情")
    private String description;
    @ApiModelProperty(value = "标签id")
    private List<Integer> tagIds;
    @ApiModelProperty(value = "gp币")
    private Integer price;
}
