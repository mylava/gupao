package com.gupao.edu.order.server.enums;

import java.util.Objects;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-03-22 22:38
 */
public class PaymentEnums {

    /**
     * 支付状态
     */
    public enum PayStatus {

        WAIT_PAY(1,"未支付"),
        PAYING(2,"支付中"),
        PAYED(3,"已支付"),
        PAY_FAILED(4,"支付失败"),
        SUCCESS(5,"已退款"),
        ;
        public  final Integer  value;
        public  final  String name;

        PayStatus(Integer value, String name) {
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
        public static PaymentEnums.PayStatus valueOf(Integer value ) {
            for ( PaymentEnums.PayStatus  payStatus :  PaymentEnums.PayStatus.values()) {
                if (payStatus.getValue()==(value)) {
                    return payStatus;
                }
            }
            return null;
        }


    }


    /**
     * 支付方式
     */
    public enum  PayMethod {

        WEIXIN(1, "微信"),
        ALIPAY(2, "支付宝"),
        APPLEPAY(3, "苹果支付"),
        LEARN_COINS(4, "学币"),
        THREE_CHANNEL(5, "三方渠道");

        public final Integer value;
        public final String name;

        PayMethod(Integer value, String name) {
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
        public static PaymentEnums.PayMethod valueOf(Integer value ) {
            for ( PaymentEnums.PayMethod  payMethod :  PaymentEnums.PayMethod.values()) {
                if (Objects.equals(payMethod.getValue(), value)) {
                    return payMethod;
                }
            }
            return null;
        }
    }
}
