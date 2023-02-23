package com.gupao.edu.common.constant;

/**
 * 分类枚举
 *
 * @author leon
 * @date 2020-04-27 14:40:40
 */
public enum CategoryEnum {

	// 1课程类型（训练营、小课） 2班级类型（Java、大数据） 3学历 4职位 5互动通知类型 6订单渠道 7获悉渠道 8权益
	COURSE(1, "课程类型"),
	CLASS(2, "班级类型"),
	EDUCATIONAL_BACKGROUND(3, "学历"),
	POSITION(4, "职位"),
	NOTIFY(5, "通知类型"),
	ORDER(6, "订单渠道"),
	LEARN_CHANNEL(7, "获悉渠道");

	private Integer id;
	private String code;

	CategoryEnum(Integer id, String code) {
		this.id = id;
		this.code = code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeById(Integer id) {
		if (id == null) {
			return null;
		}
		CategoryEnum[] values = CategoryEnum.values();
		for (CategoryEnum categoryEnum : values) {
			if (id.equals(categoryEnum.id)) {
				return categoryEnum.code;
			}
		}
		return null;
	}
}



