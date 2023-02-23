package com.gupao.edu.answer.server.entity.res;

import com.gupao.edu.account.client.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

import java.util.Date;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-19 21:49
 **/
@Data
@ApiModel(value = "问题返回实体")
public class QuestionRes {
    @ApiModelProperty("问题id")
    private Integer id ;
    @ApiModelProperty("用户")
    private User user ;
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
    @ApiModelProperty("收藏次数")
    private Integer collections ;
    @ApiModelProperty("评论次数")
    private Integer comments ;
    @ApiModelProperty("状态")
    private Integer status ;
    @ApiModelProperty("创建时间")
    private Date createdAt ;
    @ApiModelProperty("更新时间")
    private Date updatedAt ;
    @ApiModelProperty("是否置顶")
    private Integer isTop ;
    @ApiModelProperty("置顶时间")
    private Date topAt ;
    @ApiModelProperty("内容类型（ueditor  markdown）")
    private Integer editorType ;
    @ApiModelProperty("markdown内容")
    private String mddescription ;
    @ApiModelProperty("公开区域(all：所有，vip：对vip)")
    private Integer openArea ;
    @ApiModelProperty("采纳用户ID")
    private User adoptUser ;
    @ApiModelProperty("标签")
    private List<Integer> tags;
}
