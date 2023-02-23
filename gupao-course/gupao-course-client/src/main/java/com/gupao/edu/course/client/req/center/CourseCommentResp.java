package com.gupao.edu.course.client.req.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 〈学员评价dto〉
 *
 * @author yangshibo
 * @create 2020/3/23
 * @since 1.0.0
 */
@Data
@ApiModel("学生评价返回实体")
@AllArgsConstructor
@NoArgsConstructor
public class CourseCommentResp {

    /**
     * 发表评论的用户的呢称
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;

    /**
     * 发表用户的类型
     */
    @ApiModelProperty(name = "userType", value = "用户类型")
    private String userType;

    /**
     * 发表评论的用户的呢称
     */
    @ApiModelProperty(value = "用户名字", name = "userName")
    private String userName;

    /**
     * 用户照片
     */
    @ApiModelProperty(value = "用户照片", name = "userPhone")
    private String userPhoto;

    /**
     * 星级，一般最高为五星好评
     */
    @ApiModelProperty(value = "星级", name = "star")
    private Integer star;

    /**
     * 评论的内容
     */
    @ApiModelProperty(value = "评论的内容", name = "content")
    private String content;

    /**
     * 已上课时间（分）
     */
    @ApiModelProperty(value = "评论的内容", name = "content")
    private Integer courseTime;

    /**
     * 创建时间，即评论的时间
     */
    @ApiModelProperty(value = "评论的时间", name = "ctime")
    private Date ctime;

    @ApiModelProperty(name = "是否是官方", value = "isOfficeAnswer")
    private Boolean isOfficeAnswer;

    @ApiModelProperty(name = "官方回复内容", value = "officeAnswer")
    private String officeAnswer;
}
