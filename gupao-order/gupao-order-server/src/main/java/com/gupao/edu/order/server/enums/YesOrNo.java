package com.gupao.edu.order.server.enums;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-03-18 15:08
 */
public enum YesOrNo {
    NO(0, "否"),
    YES(1, "是");

    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
