package com.gupao.edu.course.client.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 我的课程播放进度表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserCourseProgress implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 视频ID
     */
    private Integer videoId;

    /**
     * 大纲ID(对应大纲小节)
     */
    private Integer courseOutlineId;

    /**
     * 播放时间，单位秒
     */
    private Long playSeconds;

    /**
     * 视频总时长，单位秒
     */
    private Long videoSeconds;


    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
