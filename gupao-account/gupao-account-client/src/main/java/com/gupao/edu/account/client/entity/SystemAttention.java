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
 * 平台通知表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemAttention implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 通知类型 1-后台录入通知 2-优惠卷过期提醒 3-收到优惠卷提醒 4-关注课程上架提醒 5-权益到期提醒 6-会员新季度书籍上架提醒
     */
    private Integer type;

    /**
     * 发送内容id(供链接跳转)
     */
    private Integer sourceId;

    /**
     * 标题
     */
    private String subject;

    /**
     * 通知内容url
     */
    private String url;

    /**
     * 内容
     */
    private String content;

    /**
     * 1-送达 0-未送达
     */
    private Boolean isSend;

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
