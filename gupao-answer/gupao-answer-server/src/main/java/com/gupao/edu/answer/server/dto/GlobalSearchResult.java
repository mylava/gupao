package com.gupao.edu.answer.server.dto;

import com.gupao.edu.account.client.resp.UserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("全局搜索返回数据")
public class GlobalSearchResult implements Serializable {

    @ApiModelProperty("内容ID")
    private Integer contentId;
    @ApiModelProperty("内容类型 1：课程 2：问答 3：文章 4：用户")
    private Integer contentType;
    @ApiModelProperty("课程")
    private CourseDTO course;
    @ApiModelProperty("问答")
    private QuestionDTO question;
    @ApiModelProperty("文章")
    private ArticlesDTO article;
    @ApiModelProperty("用户")
    private UserVO user;


}
