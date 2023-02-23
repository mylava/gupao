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
 * 私信表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PersonalLetter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 消息父ID
     */
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
     * 消息内容
     */
    private String message;

    /**
     * 是否已读 0-未读 1-已读
     */
    private Integer isRead;

    /**
     * 0-未删除 1-删除
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
