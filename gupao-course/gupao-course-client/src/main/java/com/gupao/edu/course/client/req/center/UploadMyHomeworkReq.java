package com.gupao.edu.course.client.req.center;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 〈上传我的作业参数〉
 *
 * @author yangshibo
 * @create 2020/3/26
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UploadMyHomeworkReq {

    @ApiModelProperty(name = "homeworkId", value = "作业id")
    private Integer homeworkId;

    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;

    @ApiModelProperty(name = "userName", value = "用户姓名")
    private String userName;

    @ApiModelProperty(name = "comment", value = "作业内容")
    private String comment;

    @ApiModelProperty(name = "gradeId", value = "学科ID")
    private Integer gradeId;









}
