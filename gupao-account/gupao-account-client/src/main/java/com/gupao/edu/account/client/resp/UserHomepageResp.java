package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 14/04/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("我的主页返回值")
public class UserHomepageResp {

    @ApiModelProperty(value = "头像", name = "avatar",  position = 1)
    private String avatar;

    @ApiModelProperty(value = "昵称", name = "nickName",  position = 2)
    private String nickName;

    @ApiModelProperty(value = "称谓：如Java学员", name = "title",  position = 3)
    private String title;

    @ApiModelProperty(value = "今日学习时长，单位分钟", name = "todayStudy",  position = 4)
    private Integer todayStudy;

    @ApiModelProperty(value = "总学习时长，单位分钟", name = "totalStudy",  position = 5)
    private Integer totalStudy;

    @ApiModelProperty(value = "工作城市，如：湖南-长沙", name = "workCity",  position = 6)
    private String workCity;

    @ApiModelProperty(value = "职位名称", name = "jobTitle",  position = 7)
    private String jobTitle;

    @ApiModelProperty(value = "自我介绍", name = "introduction",  position = 8)
    private String introduction;

    @ApiModelProperty(value = "课程动态数量", name = "courseCount",  position = 9)
    private Integer courseCount;

    @ApiModelProperty(value = "回答数量", name = "answerCount",  position = 10)
    private Integer answerCount;

    @ApiModelProperty(value = "提问数量", name = "questionCount",  position = 5)
    private Integer questionCount;

}

