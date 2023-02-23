package com.gupao.edu.common.cache.key;

/**
 * 搜索
 */
public class SearchPrefix extends BasePrefix {

	public static final String HOT_WORD = "hotWord";

	private SearchPrefix(int expireSeconds, String prefix) {
		super(prefix);
	}

	public static SearchPrefix search = new SearchPrefix(300*1000,"search:");

}