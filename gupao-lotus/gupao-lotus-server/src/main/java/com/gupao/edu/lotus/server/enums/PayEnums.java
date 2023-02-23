package com.gupao.edu.lotus.server.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;

/**支付枚举
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-28 09:29
 */
public class PayEnums {

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum PayStrategy {

        WX_PAY("1", "WX_PAY"),
        ALI_PAY("2", "ALI_PAY"),
        APPLE_PAY("3", "APPLE_PAY");



        private final String value;
        private final String name;

        PayStrategy(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public String getValue() {
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
        public static PayEnums.PayStrategy valueOf1(String value) {
            for ( PayEnums.PayStrategy  itemType :  PayEnums.PayStrategy.values()) {
                if (Objects.equals(itemType.getValue(), value)) {
                    return itemType;
                }
            }
            return null;
        }


    }
}
