package com.gupao.edu.account.server.enums;

/**
 * Created by dudu on 2020/4/25.
 */
public enum ReadEnum {
    UN_READ(0, "未读"),
    MARK_READ(1, "已读")
    ;

    private Integer readStatus;
    private String readDesc;

    ReadEnum(int readStatus, String readDesc) {
        this.readStatus = readStatus;
        this.readDesc = readDesc;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public String getReadDesc() {
        return readDesc;
    }

    public void setReadDesc(String readDesc) {
        this.readDesc = readDesc;
    }
}
