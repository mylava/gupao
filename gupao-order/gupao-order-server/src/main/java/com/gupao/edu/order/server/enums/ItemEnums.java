package com.gupao.edu.order.server.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-02 11:12
 */
public class ItemEnums {

    /**
     * 订单类型
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum itemType {

        COURSE_GOODS(1, "课程商品"),
        CREDITS_GOODS(2, "学币商品"),
        MEMBER_GOODS(3, "会员商品");



        private final Integer value;
        private final String name;

        itemType(Integer value, String name) {
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
        public static ItemEnums.itemType valueOf1(Integer value) {
            for ( ItemEnums.itemType  itemType :  ItemEnums.itemType.values()) {
                if (Objects.equals(itemType.getValue(), value)) {
                    return itemType;
                }
            }
            return null;
        }


    }
}
