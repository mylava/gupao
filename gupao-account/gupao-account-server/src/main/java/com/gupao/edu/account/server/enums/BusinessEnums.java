package com.gupao.edu.account.server.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/26 16:21
 * 业务编码:1-用户注册 2手机号更绑 3-更换密码 4-其他
 */
public class BusinessEnums {
    /**
     * 账号来源
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum busssionType {

        USER_REGISTRATION(1, "用户注册"),
        PHONE_BINDING(2, "手机号更绑"),
        CHANGE_PASSWORD(3, "更换密码"),
        OTHER(4, "其他");



        private final Integer value;
        private final String name;

        busssionType(Integer value, String name) {
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
        public static BusinessEnums.busssionType valueOf(Integer value) {
            for ( BusinessEnums.busssionType  bindChannel :  BusinessEnums.busssionType.values()) {
                if (Objects.equals(bindChannel.getValue(), value)) {
                    return bindChannel;
                }
            }
            return null;
        }


    }
}
