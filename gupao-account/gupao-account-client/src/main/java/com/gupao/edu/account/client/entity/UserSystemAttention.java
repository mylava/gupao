package com.gupao.edu.account.client.entity;/**
 * Created by dudu on 2020/4/28.
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <h3>gupao-parent</h3>
 * <p>平台 用户 通知白哦</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-28 23:21
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserSystemAttention implements Serializable {

    private static final long serialVersionUID = -579645694702228439L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer systemAttentionId;

    /**
     * 对应 userUniqueCode : 是对应的字段名
     */
    private String userUniqueCode;

    private Integer isSend;

    private Integer isRead;

    private Integer isDeleted;

    private LocalDateTime createTime;
    /**
     * 严格区分 大小写 ： updateTIme --> update_t_ime
     */
    private LocalDateTime updateTime;



}
