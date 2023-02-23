package com.gupao.edu.answer.server.entity.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-19 21:49
 **/
@Data
@ApiModel(value = "问题详情返回")
public class QuestionDetailRes {
    @ApiModelProperty("问题id")
    private Integer id ;
    @ApiModelProperty("用户id")
    private Integer userId ;
    @ApiModelProperty("标题")
    private String title ;
    @ApiModelProperty("内容")
    private String description ;
    @ApiModelProperty("价格")
    private Integer price ;
    @ApiModelProperty("采纳回答ID")
    private Integer answers ;
    @ApiModelProperty("查看数")
    private Integer views ;
    @ApiModelProperty("问题id")
    private Integer followers ;
    @ApiModelProperty("收藏次数")
    private Integer collections ;
    @ApiModelProperty("评论次数")
    private Integer comments ;
    @ApiModelProperty("状态")
    private Integer status ;
    @ApiModelProperty("创建时间")
    private Date created_at ;
    @ApiModelProperty("更新时间")
    private Date updated_at ;
    @ApiModelProperty("是否置顶")
    private Integer istop ;
    @ApiModelProperty("置顶时间")
    private Date top_at ;
    @ApiModelProperty("内容类型（ueditor  markdown）")
    private Integer editorType ;
    @ApiModelProperty("markdown内容")
    private Integer mddescription ;
    @ApiModelProperty("公开区域(all：所有，vip：对vip)")
    private Integer open_area ;
    @ApiModelProperty("邀请用户ID")
    private Integer to_user_id ;
    @ApiModelProperty("采纳用户ID")
    private Integer adopt_user_id ;
    @ApiModelProperty("标签")
    private List<AskTagRes> tags;
}
