package com.gupao.edu.order.server.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-03-22 22:24
 */
public class OrdersEnums {

    /**
     * 订单状态
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum OrderStatus {

        WAIT_PAY(1, "待付款"),
        WAIT_DELIVER(2, "已付款"),
        WAIT_RECEIVE(3, "已发货，待收货"),
        SUCCESS(4, "交易成功"),
        CLOSE(5, "交易关闭");


        private final Integer value;
        private final String name;

        OrderStatus(Integer value, String name) {
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
        public static OrdersEnums.OrderStatus valueOf(Integer value) {
            for ( OrdersEnums.OrderStatus  orderStatus :  OrdersEnums.OrderStatus .values()) {
                if (orderStatus.getValue()==(value)) {
                    return orderStatus;
                }
            }
            return null;
        }


    }







    /**
     * 订单类型
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum OrderType {

        VIRTUAL_PRODUCT(10001, "虚拟商品订单"),
        BOOK(10002, "书籍订单");



        private final Integer value;
        private final String name;

        OrderType(Integer value, String name) {
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
        public static OrdersEnums.OrderType valueOf(Integer value) {
            for ( OrdersEnums.OrderType  orderType :  OrdersEnums.OrderType.values()) {
                if (Objects.equals(orderType.getValue(), value)) {
                    return orderType;
                }
            }
            return null;
        }


    }

    /**
     * 订单渠道
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum OrderClientChannel {

        PC("1", "PC"),
        APP("2", "APP"),
        APPLETS("3", "小程序"),
        TENCENT_CLASSROOM("4", "腾讯课堂"),
        NETEASE_CLOUD_CLASSROOM("5", "网易云课堂");


        private final String value;
        private final String name;

        OrderClientChannel(String value, String name) {
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
        public static OrdersEnums.OrderClientChannel valueOf1(String value) {
            for ( OrdersEnums.OrderClientChannel  orderClientChannel :  OrdersEnums.OrderClientChannel.values()) {
                if (Objects.equals(orderClientChannel.getValue(), value)) {
                    return orderClientChannel;
                }
            }
            return null;
        }


    }
}
