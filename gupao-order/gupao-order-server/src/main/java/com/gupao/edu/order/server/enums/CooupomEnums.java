package com.gupao.edu.order.server.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-05-04 16:28
 */
public class CooupomEnums {

    /**
     * 优惠券范围
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum couponRange {

        COURSE_GOODS(1, "全场"),
        CREDITS_GOODS(2, "指定商品"),
        MEMBER_GOODS(3, "指定科目");



        private final Integer value;
        private final String name;

        couponRange(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        /**
         * 根据值获取枚举
         *
         * @param value 值
         * @return 枚举
         */
        public static CooupomEnums.couponRange valueOf1(Integer value) {
            for ( CooupomEnums.couponRange   itemType :  CooupomEnums.couponRange .values()) {
                if (Objects.equals(itemType.getValue(), value)) {
                    return itemType;
                }
            }
            return null;
        }


    }
}
