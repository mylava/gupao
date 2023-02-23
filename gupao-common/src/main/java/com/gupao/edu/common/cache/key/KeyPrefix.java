package com.gupao.edu.common.cache.key;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 22/08/2018
 * @since: JDK 1.8
 * @description:
 */
public interface KeyPrefix {
    int getExpire();

    String getPrefix();
}
