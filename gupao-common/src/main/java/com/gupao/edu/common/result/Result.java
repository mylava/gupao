package com.gupao.edu.common.result;


import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * comment: http统一返回结果
 *
 * @author: lipengfei
 * @date: 11/05/2019
 */
@Data
public class Result<T> implements Serializable {
	private static final long serialVersionUID = -5736951573549764512L;
	private static final Map EMPTY_DATA = new HashMap();
	/**
	 * 状态
	 */
	private int status;
	/**
	 * msg
	 */
	private String message;
	/**
	 * 返回数据
	 */
	private T data;

	private Result(){}

	private Result(T data) {
		this.status = CodeMessage.SUCCESS.getCode();
		this.message = CodeMessage.SUCCESS.getMessage();
		this.data = data;
	}

	public Result(int status, String message) {
		this.status = status;
		this.message = message;
	}

	private Result(CodeMessage codeMessage) {
		if (null == codeMessage) {
			return;
		}
		this.status = codeMessage.getCode();
		this.message = codeMessage.getMessage();
	}

	/**
	 * 成功
	 *
	 * @return
	 */
	public static Result success() {
		return new Result(EMPTY_DATA);
	}

	/**
	 * 成功
	 *
	 * @param <T>
	 * @return
	 */
	public static <T> Result<T> success(T data) {
		return new Result<T>(data);
	}

	/**
	 * 失败
	 *
	 * @param <T>
	 * @return
	 */
	public static <T> Result<T> fail(CodeMessage codeMessage) {
		return new Result<T>(codeMessage);
	}

	public static <T> Result<T> fail(int code, String message) {
		return new Result<T>(code,message);
	}

	
}
