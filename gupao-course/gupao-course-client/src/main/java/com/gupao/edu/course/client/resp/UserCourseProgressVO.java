package com.gupao.edu.course.client.resp;/**
 * Created by dudu on 2020/5/7.
 */

import lombok.Data;

/**
 * <h3>gupao-parent</h3>
 * <p>UserCourseProgressVO </p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-07 00:45
 **/
@Data
public class UserCourseProgressVO {
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
     * 大纲名字
     */
    private String courseOutLineName;

}
