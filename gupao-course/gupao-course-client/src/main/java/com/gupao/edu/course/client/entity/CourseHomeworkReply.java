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
 * 学员答作业表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseHomeworkReply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作业回复主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 提交作业ID
     */
    private Integer courseHomeworkId;

    /**
     * 用户唯一编码
     */
    private String userUniqueCode;

    /**
     * 出题人,关联讲师ID
     */
    private Integer lecturerId;

    /**
     * 作业内容
     */
    private String homeworkReplyContent;

    /**
     * 作业得分
     */
    private Integer score;

    /**
     * 是否优秀作业
     */
    private Boolean isGood;

    /**
     * 作业状态。0：未批阅，1：已批阅
     */
    private Integer correctStatus;

    /**
     * 批阅内容
     */
    private String correct;

    /**
     * 批阅时间
     */
    private LocalDateTime correctTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
