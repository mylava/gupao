package com.gupao.edu.answer.client.enumeration;

public enum OpenAreaType {
    ALL("all", "对所有开放"),
    VIP("vip", "对vip开放")
    ;

    private String code;
    private String label;

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    OpenAreaType(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
