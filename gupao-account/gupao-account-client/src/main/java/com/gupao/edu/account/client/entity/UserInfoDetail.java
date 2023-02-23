package com.gupao.edu.account.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户详细信息表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfoDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户全局唯一编码,所有关联用户的标使用这个字段作为外键
     */
    private String userUniqueCode;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 学员称谓
     */
    private String title;

    /**
     * 学科ID，多个id使用json:{"1":"ID"}
     */
    private String gradeIds;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 1-男 2-女
     */
    private Integer gender;

    /**
     * 所在城市
     */
    private String workCity;

    /**
     * 所在省ID:对应字典表
     */
    private Integer provinceId;

    /**
     * 所在市ID:对应字典表
     */
    private Integer cityId;

    /**
     * 学历:对应字典表
     */
    private Integer education;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 所在公司
     */
    private String employer;

    /**
     * 工作年限
     */
    private Integer seniority;

    /**
     * 职位名称ID:对应字典表
     */
    private Integer jobTitle;

    /**
     * 目前年薪:单位万
     */
    private Integer currentSalary;

    /**
     * 期望年薪:单位万
     */
    private Integer expectSalary;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 新增自我介绍
     */
    private String introduction;



}
