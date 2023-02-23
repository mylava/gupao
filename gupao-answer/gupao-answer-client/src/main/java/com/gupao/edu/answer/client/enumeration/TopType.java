package com.gupao.edu.answer.client.enumeration;

public enum  TopType {
    ANSWERS("answer", "解答榜"),
    REWARD("reward", "悬赏榜"),
    ARTICLES("article", "作家榜"),
    QUESTIONS("questions", "问答榜"),

    //时间维度
    MONTH("month","本月"),
    TOTAL("total","全部");

    TopType(String code, String label) {
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

    public static TopType getTopType(String code){
        for (TopType topType: TopType.values()) {
            if(topType.getCode().equals(code)){
                return topType;
            }
        }
        return null;
    }
}
