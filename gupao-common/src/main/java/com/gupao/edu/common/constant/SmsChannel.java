package com.gupao.edu.common.constant;

/**
 * Class: 发送短信通道
 *
 * @Author: wangzhong
 * @Date: 2020-03-16 12:46
 */
public enum SmsChannel {
	SMS_YUNPIAN("yunpian", "云片短信通道"),
	SMS_ALI("ali_dayu", "阿里短信通道");

	private String code;
	private String message;

	private SmsChannel(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
