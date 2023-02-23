package com.gupao.edu.answer.server.enums;

import org.apache.commons.lang3.StringUtils;

public enum SourceType {
    SOURCETYPE_ARTICLE("App\\Models\\Article","文章"),
    SOURCETYPE_QUESTION("App\\Models\\Question","问答"),
    SOURCETYPE_ANSWER("App\\Models\\Answer","回答"),
    SOURCETYPE_TAG("App\\Models\\Tag","标签"),
    SOURCETYPE_USER("App\\Models\\User","用户"),
    SOURCETYPE_COMMENT("App\\Models\\Comment","评论"),
    SOURCETYPE_BUBBLE("App\\Models\\Bubble","冒泡"),
    SOURCETYPE_WORKS("App\\Models\\Works", "作业"),
    SOURCETYPE_SUBJECT("App\\Models\\Subject", "题目"),
    SOURCETYPE_GOODS("App\\Models\\GOODS","商品"),
    SOURCETYPE_COURSE("App\\Models\\Course","录播课程")
    ;

    private String code;
    private String label;

    public String getCode() {
        return code;
    }

    SourceType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "SourceType{" +
                "label='" + label + '\'' +
                '}';
    }

    public static SourceType getSourceTypeByCode(String code){
        for (SourceType sourceType : SourceType.values()){
            if(StringUtils.equals(code,sourceType.getCode())){
                return sourceType;
            }
        }
        return null;
    }
}
