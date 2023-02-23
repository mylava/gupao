package com.gupao.edu.lotus.server.service.search.enums;

import lombok.AllArgsConstructor;

/**
 *  es字段类型
 */
@AllArgsConstructor
public enum DataType {
    KEYWORD("keyword"),
    TEXT("text"),
    BYTE("byte"),
    SHORT("short"),
    INTEGER("integer"),
    LONG("long"),
    FLOAT("long"),
    DOUBLE("double"),
    BOOLEAN("boolean"),
    DATE("date"),
    NESTED("nested");

    String name;

    @Override
    public String toString() {
        return this.name;
    }
}
