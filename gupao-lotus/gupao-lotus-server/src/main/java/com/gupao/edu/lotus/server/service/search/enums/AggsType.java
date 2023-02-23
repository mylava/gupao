package com.gupao.edu.lotus.server.service.search.enums;

import lombok.AllArgsConstructor;

/**
 * 聚合metric类型枚举
 */
@AllArgsConstructor
public enum AggsType {
    SUM("sum"),
    MIN("min"),
    MAX("max"),
    COUNT("count"),
    AVG("avg")
    ;

    String name;

    @Override
    public String toString() {
        return this.name;
    }
}
