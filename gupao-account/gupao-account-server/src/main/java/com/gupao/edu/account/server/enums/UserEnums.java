package com.gupao.edu.account.server.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-25 18:30
 */
public class UserEnums {


    /**
     * 账号来源
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum bindChannel {

        QQ_CHANNEL(1, "QQ"),
        WECHAT_CHANNEL(2, "微信");



        private final Integer value;
        private final String name;

        bindChannel(Integer value, String name) {
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
        public static UserEnums.bindChannel valueOf(Integer value) {
            for ( UserEnums.bindChannel  bindChannel :  UserEnums.bindChannel.values()) {
                if (Objects.equals(bindChannel.getValue(), value)) {
                    return bindChannel;
                }
            }
            return null;
        }


    }

}
