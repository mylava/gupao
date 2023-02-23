package com.gupao.edu.account.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 互动消息表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInteraction implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer parentId;

    /**
     * 消息发送者
     */
    private String fromUniqueCode;

    /**
     * 消息接收者
     */
    private String targetUniqueCode;

    /**
     * 对应问答、文章、答疑、作业、课程互动的ID
     */
    private Integer resourceId;

    /**
     * 问答、文章、答疑、作业 对应字典表
     */
    private Integer sourceTypeId;

    /**
     * 通知类型:对应字典表
     */
    private Integer attentionTypeId;

    /**
     * 主动互动方头像
     */
    private String avatar;

    /**
     * 主动互动方昵称
     */
    private String nickName;

    /**
     * 标题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否已读 0-未读 1-已读
     */
    private Integer isRead;

    /**
     * 0-未删除 1-删除
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
