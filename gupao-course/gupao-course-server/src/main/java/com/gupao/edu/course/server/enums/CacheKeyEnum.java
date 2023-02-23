package com.gupao.edu.course.server.enums;

/**
 * <h3>app-backend</h3>
 * <p>缓存key定义</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-03 23:26
 **/
public enum  CacheKeyEnum {
    STUDY_TOTAL_TIME("STUDY:TOTAL:TIME:","今日学习总时长")
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
