package com.gupao.edu.course.client.req.good;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 〈课程评价插入参数〉
 *
 * @author yangshibo
 * @create 2020/3/26
 * @since 1.0.0
 */
@ApiModel("课程评价参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseCommonParamReq {

    @ApiModelProperty(name = "courseId",value = "课程id")
    private Integer courseId;

    @ApiModelProperty(name = "title",value = "标题")
    private String title;
    @ApiModelProperty(name = "star",value = "star")
    private Integer star;
    @ApiModelProperty(name = "content",value = "评价内容")
    private String content;
    @ApiModelProperty(name = "userUniqueCode",value = "userUniqueCode")
    private String userUniqueCode;
    @ApiModelProperty(name = "nickName",value = "用户昵称")
    private String nickName;
}
