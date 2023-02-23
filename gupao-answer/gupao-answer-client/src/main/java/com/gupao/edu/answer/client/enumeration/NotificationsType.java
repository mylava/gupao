package com.gupao.edu.answer.client.enumeration;

public enum NotificationsType {

    ADOPT_ANSWER("adopt_answer", "采纳回答"),
    ANSWER("answer", "回答"),
    COMMENT_ANSWER("comment_answer", "评论回答"),
    COMMENT_ARTICLE("comment_article", "评论文章"),
    COMMENT_QUESTION("comment_question", "评论问答"),
    COMMENT_BUBBLE("comment_bubble", "评论冒泡"),
    COMMENT_WORK("comment_work", "评论作业"),
    COMMENT_SUBJECT("comment_subject", "评论题目"),
    FOLLOW_QUESTION("follow_question", "关注问答"),
    FOLLOW_USER("follow_user", "关注用户"),
    INVITE_ANSWER("invite_answer", "邀请回答"),
    SYSTEM_NOTIFICATION("system_notification", "系统通知"),
    NOTICE_REMINDER("notice_reminder", "提醒通知采纳"),
    REPLY_COMMENT("reply_comment", "回复"),

    COLLECT_ARTICLE("collect_article", "收藏文章"),
    COLLECT_QUESTION("collect_question", "收藏问题"),
    COLLECT_SUBJECT("collect_subject", "收藏题目"),
    SHARE_ARTICLE("share_article", "分享文章"),
    SHARE_QUESTION("share_question", "分享问题"),
    SUPPORT_ARTICLE("support_article", "点赞文章"),
    SUPPORT_ANSWER("support_answer", "点赞回答"),
    SUPPORT_BUBBLE("support_bubble", "点赞冒泡"),
    SUPPORT_COMMENT("support_comment", "点赞评论"),

    REWARD_ARTICLE("reward_article", "打赏文章"),
    SCORE_WORK("score_work", "评分作业"),
    SUBMIT_WORK("submit_work", "提交作业"),
    PERSONAL_AUTHENTICATION("personal_authentication", "个人认证"),
    PERSONAL_CLASSIFICATION("personal_classification", "个人分级"),
    ;

    NotificationsType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    private String code;
    private String label;

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
