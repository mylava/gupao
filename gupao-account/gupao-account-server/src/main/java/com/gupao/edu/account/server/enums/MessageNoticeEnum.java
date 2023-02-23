package com.gupao.edu.account.server.enums;

/**
 *  互动 消息 枚举
 * @author hduong
 * @company none
 * @create
 */
public enum MessageNoticeEnum {

    PLATFORM_MSG_NOTIFICATIONS(1, "平台消息通知"),
    INTERACTIVE_MSG_REMINDER(2, "互动消息提醒"),
    PERSONAL_LETTER(3, "私信")
    ;

    private int messageType;

    private String messageDesc;

    MessageNoticeEnum(int messageType, String messageDesc) {
        this.messageType = messageType;
        this.messageDesc = messageDesc;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessageDesc() {
        return messageDesc;
    }

    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }
}
