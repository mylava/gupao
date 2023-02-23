package com.gupao.edu.common.cache.key;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 01/08/2019
 */
public class LockPrefix extends BasePrefix {

    private LockPrefix(String prefix) {
        super(ONE_DAY, prefix);
    }

    /**
     * 用户创建房间的锁
     */
    public static final LockPrefix LOCK_TEST = new LockPrefix("lock:test");
    public static final LockPrefix LOCK_TEST1 = new LockPrefix("lock:test1");
    public static final LockPrefix LOCK_ORDER = new LockPrefix("lock:createOrder");
    public static final LockPrefix LOCK_SMS = new LockPrefix("lock:sms");
}
