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
 * 视频答疑回复表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseVideoQuestionReply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 问题标题
     */
    private String questionTitle;

    /**
     * 问题id
     */
    private Integer courseVideoQuestionId;

    /**
     * 用户唯一编码
     */
    private String userUniqueCode;

    /**
     * 点赞数量
     */
    private Integer praiseCount;

    /**
     * 反对数量
     */
    private Integer negativeCount;

    /**
     * 是否官方回复
     */
    private Boolean officeReply;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 是否被采纳
     */
    private Boolean isAdopted;

    /**
     * 采纳时间
     */
    private LocalDateTime adoptedTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
