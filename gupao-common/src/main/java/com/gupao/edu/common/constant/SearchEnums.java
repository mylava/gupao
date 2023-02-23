package com.gupao.edu.common.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import com.gupao.edu.common.converter.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class SearchEnums {

    public static final String COURSE = "gp_course";
    public static final String QUESTION = "ask_question";
    public static final String ARTICLE = "ask_article";
    public static final String USER = "user";

    /**
     * 搜索类型
     */
    @AllArgsConstructor
    public enum SearchType implements IEnum {

        ALL(0,"全部"),
        COURSE(1,"课程"),
        QUESTION(2,"问答"),
        ARTICLE(3,"文章"),
        USER(4,"用户"),
        ;

        int code;
        String name;

        public static IndexEnum valueOf(int code){
            for (IndexEnum value : IndexEnum.values()) {
                if (value.getCode()==code){
                    return value;
                }
            }
            throw new IllegalArgumentException();
        }

        @JsonValue
        @Override
        public int getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 索引枚举
     */
    @AllArgsConstructor
    @Getter
    public enum IndexEnum {

        COURSE(SearchEnums.COURSE,1),
        QUESTION(SearchEnums.QUESTION,2),
        ARTICLE(SearchEnums.ARTICLE,3),
        USER(SearchEnums.USER,4),
        ;

        String index;
        int code;

        public static IndexEnum valueOf(int code){
            for (IndexEnum value : IndexEnum.values()) {
                if (value.getCode()==code){
                    return value;
                }
            }
            throw new IllegalArgumentException();
        }

        @Override
        public String toString() {
            return this.index;
        }

        public static IndexEnum getEnum(String index) {
            for(IndexEnum enums : values())
                if(enums.getIndex().equalsIgnoreCase(index)) return enums;
            throw new IllegalArgumentException();
        }

    }
}
