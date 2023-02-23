package com.gupao.edu.answer.client.enumeration;

public enum UserDataStaticalType {
    QUESTIONS("questions", "提问数"),
    ARTICLES("article", "文章数"),
    ANSWERS("answer", "回答数"),
    ADOPTIONS("adoption", "采纳数"),
    SUPPORTS("supports", "推荐/点赞"),
    FOLLOWERS("followers", "关注数"),
    VIEWS("views", "访问次数"),
    ;

    private String code;
    private String label;

    UserDataStaticalType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
