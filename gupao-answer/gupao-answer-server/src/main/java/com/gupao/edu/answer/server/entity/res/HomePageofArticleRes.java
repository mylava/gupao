package com.gupao.edu.answer.server.entity.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class HomePageofArticleRes {

    private Integer id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "摘要")
    private String summary;

    @ApiModelProperty(value = "评论数")
    private Integer comments;

    @ApiModelProperty(value = "观看数")
    private Integer views;



}
