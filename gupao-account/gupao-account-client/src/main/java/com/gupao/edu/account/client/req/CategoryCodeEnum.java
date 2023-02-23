package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分类编码枚举
 *
 * @author leon
 * @date 2020-05-01 21:50:39
 */
public enum CategoryCodeEnum {

	/**
	 * 教育
	 */
	@ApiModelProperty(name = "学历", value = "education")
	EDUCATION("学历", "education"),
	/**
	 * 职位
	 */
	@ApiModelProperty(name = "职位", value = "jobTitle")
	JOB_TITLE("职位", "jobTitle"),
	/**
	 * 工作年限
	 */
	@ApiModelProperty(name = "工作年限", value = "seniority")
	SENIORITY("工作年限", "seniority"),
	/**
	 * 薪水
	 */
	@ApiModelProperty(name = "薪水", value = "salary")
	SALARY("薪水", "salary"),
	/**
	 * 试听次数
	 */
	@ApiModelProperty(name = "试听次数", value = "salary")
	AUDITION_COUNTS("试听次数", "auditionCounts");


	private String name;
	private String code;
	CategoryCodeEnum(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
