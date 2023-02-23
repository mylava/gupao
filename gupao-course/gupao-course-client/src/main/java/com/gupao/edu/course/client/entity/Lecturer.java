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
 * 讲师详情表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Lecturer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 教师名字
     */
    private String name;

    /**
     * 教师简介
     */
    private String summary;

    /**
     * 教师图文详情
     */
    private String description;

    /**
     * 教师职位:对应字典表
     */
    private Integer jobTitle;

    /**
     * 教师标签
     */
    private String tag;

    /**
     * 手机
     */
    private String mobile;

    /**
     * qq
     */
    private String qq;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否明星讲师 1是0否
     */
    private Boolean star;

    /**
     * 教师排序
     */
    private Integer sort;

    /**
     * 讲师状态
     */
    private Integer state;

    /**
     * 是否删除 默认0否 1是
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

    /**
     * 创建人ID
     */
    private Integer creatorId;


}
