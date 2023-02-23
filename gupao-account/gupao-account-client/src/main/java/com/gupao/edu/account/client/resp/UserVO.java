package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Class: 用户信息返回
 *
 * @Author: wangzhong
 * @Date: 2020-03-29 21:45
 */
@Data
@ApiModel(value = "用户信息")
public class UserVO {


	/**
	 * 用户id
	 */
	@ApiModelProperty("用户id")
	private Integer userId;

	/**
	 * 邮箱
	 */
	@ApiModelProperty("邮箱")
	private String email;


	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	private String mobile;

	/**
	 * qq号
	 */
	@ApiModelProperty("qq号")
	private String qq;


	/**
	 * 用户名
	 */
	@ApiModelProperty("用户姓名")
	private String userName;


	/**
	 * 头像
	 */
	@ApiModelProperty("用户头像")
	private String avatar;


	/**
	 * 昵称
	 */
	@ApiModelProperty("用户昵称")
	private String nickName;


	/**
	 * 年龄
	 */
	@ApiModelProperty("年龄")
	private Integer age;


	/**
	 * 1-男 2-女
	 */
	@ApiModelProperty("性别")
	private Integer gender;

	/**
	 * 所在城市
	 */
	@ApiModelProperty("城市")
	private String city;

	/**
	 * 学历
	 */
	@ApiModelProperty("学历")
	private String certificate;


	/**
	 * 自我介绍
	 */
	@ApiModelProperty("自我介绍")
	private String description;


	/**
	 * 所在公司
	 */
	@ApiModelProperty("所在公司")
	private String company;


	/**
	 * 工作年限
	 */
	@ApiModelProperty("工作年限")
	private String workAge;


	/**
	 * 岗位
	 */
	@ApiModelProperty("工作职位")
	private String position;

	/**
	 * 目前年薪
	 */
	@ApiModelProperty("目前年薪")
	private String annualSalary;


	/**
	 * 期望薪资
	 */
	@ApiModelProperty("期望年薪")
	private String expectSalary;


}