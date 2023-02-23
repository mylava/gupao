package com.gupao.edu.account.server.enums;

/**
 *  缓存 枚举
 * @author hduong
 * @company none
 * @create
 */
public enum CacheKeyEnum {
    PLATFORM_MSG_NOTIFICATIONS_CACHE_KEY("PMNCK", "平台通知消息缓存总数统计"),
    PERSONAL_LETTER_CACHE_KEY("PLCK", "私信消息缓存总数统计"),
    ;

    private String key;
    private String desc;

    CacheKeyEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
