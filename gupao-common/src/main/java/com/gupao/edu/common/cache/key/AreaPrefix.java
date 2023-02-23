package com.gupao.edu.common.cache.key;

/**
 * 区域
 *
 * @author leon
 * @date 2020-04-30 16:35:50
 */
public class AreaPrefix extends BasePrefix {

	private static final int DEFAULT_EXPIRE = 3600 * 24 * 30;
	public static AreaPrefix areaPrefix = new AreaPrefix("areaList:");

	public AreaPrefix(String prefix) {
		super(DEFAULT_EXPIRE, prefix);
	}
}
