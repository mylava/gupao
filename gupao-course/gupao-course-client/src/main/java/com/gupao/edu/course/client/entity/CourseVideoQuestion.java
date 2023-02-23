package com.gupao.edu.course.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

/**
 * <p>
 * 视频答疑问题表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseVideoQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 问题ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户唯一编码
     */
    private String userUniqueCode;

    /**
     * 视频id
     */
    private Integer videoId;

    /**
     * 视频播放时间点(秒)
     */
    private Integer point;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 问题内容
     */
    private String content;

    /**
     * 回答数
     */
    private Integer replyCount;

    /**
     * 浏览人数
     */
    private Integer browseCount;

    /**
     * 状态(-1:审核不通过，0:审核中，1:审核通过)
     */
    private Integer auditState;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
