package com.gupao.edu.course.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程大纲表（关联视频资源）
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseOutline implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 大纲ID，主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级目录ID
     */
    private Integer parentId;

    /**
     * 所属课程ID
     */
    private Integer courseId;

    /**
     * 标题
     */
    private String title;

    /**
     * 课程大纲排序
     */
    private Integer sort;

    /**
     * 录播还是直播  0录播 1直播
     */
    private Boolean videoOrLive;

    /**
     * 关联视频ID（如果是直播，需创建直播表）
     */
    private Integer videoOrStreamId;

    /**
     * 1 大纲（章-一级目录）2 是视频（节-二级目录）方便查询使用
     */
    private Integer outlineLevel;

    /**
     * 是否是视频  0否 1是 方便查询使用
     */
    private Boolean isVideo;

    /**
     * 创建者的userId
     */
    private Integer createUserId;

    /**
     * 修改者的userId
     */
    private Integer updateUserId;

    /**
     * 是否可以试听 0-否 1-是
     */
    private Boolean onTry;

    /**
     * 试听活动开始时间
     */
    private LocalDateTime tryTimeStart;

    /**
     * 试听活动结束时间
     */
    private LocalDateTime tryTimeEnd;

    /**
     * 试听时长，单位秒 -1表示永久
     */
    private Integer limitTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
