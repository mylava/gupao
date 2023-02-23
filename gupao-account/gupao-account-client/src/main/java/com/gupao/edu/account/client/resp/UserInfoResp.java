package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * <p>
 * 用户个人信息
 * </p>
 *
 * @author wuzhenping
 * @since 2020-03-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户个人资料")
public class UserInfoResp {

	@ApiModelProperty(value = "头像", name = "avatar", position = 1)
	private String avatar;

	@ApiModelProperty(value = "昵称", name = "nickName", position = 2)
	private String nickName;

	@ApiModelProperty(value = "真实名字", name = "realName", position = 3)
	private String realName;

	@ApiModelProperty(value = "年龄", name = "age", position = 4)
	private Integer age;

	/**
	 * 出生年月日 计算年龄
	 */
	private LocalDate birthday;

	@ApiModelProperty(value = "性别 1-男 2-女", name = "gender", position = 5)
	private Integer gender;

	@ApiModelProperty(value = "所在省份", name = "province", position = 6)
	private Integer province;

	@ApiModelProperty(value = "所在城市", name = "city", position = 7)
	private Integer city;

	@ApiModelProperty(value = "学历", name = "certificate", position = 8)
	private Integer certificate;

	@ApiModelProperty(value = "自我介绍", name = "description", position = 9)
	private String description;

	@ApiModelProperty(value = "所在公司", name = "company", position = 10)
	private String company;

	@ApiModelProperty(value = "工作年限", name = "workAge", position = 11)
	private Integer workAge;

	@ApiModelProperty(value = "职位名称", name = "position", position = 12)
	private String position;

	@ApiModelProperty(value = "目前年薪", name = "currentSalary", position = 13)
	private Integer currentSalary;

	@ApiModelProperty(value = "期望薪资", name = "expectSalary", position = 14)
	private Integer expectSalary;

//	@ApiModelProperty(value = "用户编码", name = "userUniqueCode", position = 15)
//	private String userUniqueCode;

}
