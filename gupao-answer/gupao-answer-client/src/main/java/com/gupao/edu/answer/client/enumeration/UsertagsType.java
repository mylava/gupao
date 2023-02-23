package com.gupao.edu.answer.client.enumeration;

public enum UsertagsType {

    QUESTIONS("QUESTIONS", "问答"),
    ARTICLES("ARTICLES", "文章"),
    ANSWERS("ANSWERS", "回答"),
    SUPPORTS("SUPPORTS", "支持"),
    ADOPTIONS("ADOPTIONS", "采纳");

    UsertagsType(String code, String label) {
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
