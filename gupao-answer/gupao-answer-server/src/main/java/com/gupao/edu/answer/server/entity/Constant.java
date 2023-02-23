package com.gupao.edu.answer.server.entity;

public class Constant {

	public static final byte GP_STATUS_VALID = 1;

	public static final byte GP_STATUS_INVALID = 0;

	public static final byte GP_STATUS_REFUSE = -1; //黑名单/审核拒绝

	public static final byte GP_STATUS_STOP = -2; //已被停用

	/**
	 * TOKEN 备份有效时间 7 * 24h，缓存有效时间为24h，微服务开启后实际有效时间以缓存为准
	 */
	public static final int TOKEN_DEFAULT_EXPIRE = 7 * 24 * 60 * 60;



}
