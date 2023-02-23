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
 * 用户收货地址表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户全局唯一编码,所有关联用户的标使用这个字段作为外键
     */
    private String userUniqueCode;

    /**
     * 收件人姓名
     */
    private String receiver;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 省份
     */
    private String province;

    /**
     * 所在省ID:对应字典表
     */
    private Integer provinceId;

    /**
     * 市
     */
    private String city;

    /**
     * 所在市ID:对应字典表
     */
    private Integer cityId;

    /**
     * 区
     */
    private String county;

    /**
     * 所在区ID:对应字典表
     */
    private Integer countyId;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 地址默认 1-是 0-否
     */
    private Boolean isDefault;

    /**
     * 1-删除 0-未删除
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
