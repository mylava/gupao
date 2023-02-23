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
 * 作业问题表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseHomework implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 大纲ID（大纲章ID，不是节ID）
     */
    private Integer courseOutlineId;

    /**
     * 学科ID
     */
    private Integer gradeId;

    /**
     * 作业标题
     */
    private String title;

    /**
     * 出题人,关联讲师ID
     */
    private Integer lecturerId;

    /**
     * 作业内容
     */
    private String content;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 是否已删除 0否 1是
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
