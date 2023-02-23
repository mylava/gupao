package com.gupao.edu.course.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <h3>app-backend</h3>
 * <p>学习 中心 查看课程 详情</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-04 00:42
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("学习中心课程详情实体")
public class StudyCenterCourseDetailResp {

    //上次学到 章 + 小节 获取到 上次学到的 大纲id(可以在退出一个视频后,就进行一次维护播放进度表) --> 可以查到 播放时间点 + 以及相关提问答疑
    //课程学习总进度
    /**
     * 课程学习总进度
     */
    @ApiModelProperty(value = "课程学习总进度", name = "progress",  position = 1)
    private String progress;

    /**
     *
     * 上次播放的视频id
     */
    @ApiModelProperty(value = "上次播放视频id", name = "videoId",  position = 2)
    private Integer videoId;

    /**
     * 课程大纲
     */
    @ApiModelProperty(value = "课程大纲", name = "outLineResps",  position = 3)
    private List<CourseOutlineTreeResp> outLineResps;

    /**
     *
     * 上次播放的大纲id
     */
    @ApiModelProperty(value = "上次播放的大纲id", name = "courseOutlineId",  position = 4)
    private Integer courseOutlineId;

    /**
     * 课程名字
     */
    @ApiModelProperty(value = "课程名字", name = "courseName",  position = 5)
    private String courseName;
}
