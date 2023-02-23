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
 * 登录历史记录表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserLoginHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 用户全局唯一编码,所有关联用户的标使用这个字段作为外键
	 */
	private String userUniqueCode;

	/**
	 * app平台:1-ios 2-android)
	 */
	private Integer osType;

	/**
	 * 设备类型(机型)
	 */
	private String deviceType;

	/**
	 * 地理位置
	 */
	private String geographicalLocation;

	/**
	 * 机器设备号
	 */
	private String deviceNo;

	/**
	 * 登录ip
	 */
	private String ipAddress;

	/**
	 * 登录入口 1-app 2-社区 3-小程序
	 */
	private Integer serviceType;
	/**
	 * 登录token
	 */
	private String accessToken;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;


}
