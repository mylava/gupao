package com.gupao.edu.common.cache.key;

/**
 * 区域
 *
 * @author leon
 * @date 2020-04-30 16:35:50
 */
public class DictPrefix extends BasePrefix {

	private static final int DEFAULT_EXPIRE = 3600 * 24 * 7;
	public static DictPrefix dictPrefix = new DictPrefix("dictList:");

	public DictPrefix(String prefix) {
		super(DEFAULT_EXPIRE, prefix);
	}
}
