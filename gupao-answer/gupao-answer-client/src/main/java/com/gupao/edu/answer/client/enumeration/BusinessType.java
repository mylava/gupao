package com.gupao.edu.answer.client.enumeration;

public enum BusinessType {

    QUESTIONS("QUESTIONS", "问答"),
    ARTICLES("ARTICLES", "文章"),
    ANSWERS("ANSWERS", "回答"),
    ATTENTION("ATTENTION", "关注"),
    ADOPTIONS("ADOPTIONS", "采纳");

    BusinessType(String code, String label) {
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
