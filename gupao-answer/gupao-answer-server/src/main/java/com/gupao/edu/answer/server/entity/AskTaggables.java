package com.gupao.edu.answer.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 提问文章标签关联表
 * </p>
 *
 * @author lipengfei
 * @since 2020-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AskTaggables implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签ID
     */
    @TableId
    private Integer tagId;

    /**
     * 文章或者问答ID
     */
    private Integer taggableId;

    /**
     * 关联类型
     * App\\Models\\Article 文章
     * App\\Models\\Question 问答
     */
    private String taggableType;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 操作类型
     */
    private Integer optType;


}
