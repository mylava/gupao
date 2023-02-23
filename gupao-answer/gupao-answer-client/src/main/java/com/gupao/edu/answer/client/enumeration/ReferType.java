package com.gupao.edu.answer.client.enumeration;

public enum ReferType {

    QUESTION("question", "问答"),
    ANSWER("answer", "回答"),
    ARTICLE("article", "文章"),
    WORK("work", "作业"),
    SUBJECT("subject", "题目"),
    BUBBLE("bubble","冒泡");

    ReferType(String code, String label) {
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
