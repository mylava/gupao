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
 * 课程评论表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 被评标的所属的课程ID
     */
    private Integer courseId;

    /**
     * 发表评论的用户编码
     */
    private String userUniqueCode;

    /**
     * 发表评论的用户的呢称
     */
    private String nickName;

    /**
     * 星级，一般最高为五星好评
     */
    private Integer star;

    /**
     * 评论的内容
     */
    private String content;

    /**
     * 官方是否回复
     */
    private Boolean officeReplied;

    /**
     * 官方回复内容
     */
    private String officeRepliedContent;

    /**
     * 已上课时间（秒）
     */
    private Integer courseTime;

    /**
     * 是否好评 0 未评价 1好评 2差评
     */
    private Integer praiseOrNegative;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 0未删除，1已删除
     */
    private Boolean isDeleted;


}
