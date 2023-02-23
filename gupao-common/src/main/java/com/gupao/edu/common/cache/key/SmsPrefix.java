package com.gupao.edu.common.cache.key;

/**
 * Class:
 *
 * @Author: wangzhong
 * @Date: 2020-03-31 19:31
 */
public class SmsPrefix extends BasePrefix {

	private SmsPrefix(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

	public static SmsPrefix smsVerifyCodePrefix=new SmsPrefix(300*1000,"sms:verifyCode:mobile");
}