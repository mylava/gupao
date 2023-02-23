package com.gupao.edu.course.client.resp.good;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/17 14:21
 * 我的播放进度返回实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("课程大纲返回实体对象")
public class UserCourseProgressResp {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "播放进度主键ID", name = "id",  position = 1)
    private Integer id;

    /**
     * 用户唯一编码
     */
    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode",  position = 2)
    private String userUniqueCode;

    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id", name = "courseId",  position = 3)
    private Integer courseId;

    /**
     * 视频ID
     */
    @ApiModelProperty(value = "视频ID", name = "videoId",  position = 4)
    private Integer videoId;

    /**
     * 大纲ID(对应大纲小节)
     */
    @ApiModelProperty(value = "大纲ID(对应大纲小节)", name = "courseOutlineId",  position = 5)
    private Integer courseOutlineId;

    /**
     * 播放时间，单位秒
     */
    @ApiModelProperty(value = "播放时间", name = "playSeconds",  position = 6)
    private Integer playSeconds;

    /**
     * 视频总时长，单位秒
     */
    @ApiModelProperty(value = "播放时间", name = "videoSeconds",  position = 7)
    private Integer videoSeconds;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
