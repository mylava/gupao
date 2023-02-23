package com.gupao.edu.answer.client.enumeration;

public enum NewbieTaskType {

    ASK_QUESTIONS("ask_questions", "发布问答"),
    ANSWERS_QUESTIONS("answers_questions", "回答问题"),
    ADOPT_QUESTIONS("adopt_questions","采纳问题"),
    READNOTES("readnote", "阅读须知"),
    INFORMATION("information", "完善资料"),
    DAILY_ATTENDANCE("daily_attendance","日常签到"),
    WRITING_ARTICLES("writing_articles","撰写文章"),
    SHARES_ARTICLES("shares_articles","分享文章"),
    COMMENTS_ARTICLES("comments_articles","评论文章"),
    //第二期
    HELP_ADOPT("help_adopt","帮助采纳问题"),
    COLLECT_ARTICLES("collect_articles","收藏文章"),
    SUPPORT_ARTICLES("support_articles","点赞文章"),
    ATTENTION_AUTHOR("attention_author","关注作者"),
    ATTENTION_QUESTIONS("attention_questions","关注问题"),
    SHARES_QUESTIONS("shares_questions","分享问题"),
    COMMENTS_QUESTIONS("comments_questions","评论问题"),
    COMMENTS_ANSWERS("comments_answers","评论答案"),
    SUPPORT_ANSWERS("support_answers","点赞回答"),
    SCORE_WORKS("score_works","作业评分"),
    COMMENTS_WORKS("comments_works","作业评论")
    ;

    NewbieTaskType(String code, String label) {
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
