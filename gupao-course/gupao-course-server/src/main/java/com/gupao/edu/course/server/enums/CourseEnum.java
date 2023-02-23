package com.gupao.edu.course.server.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Objects;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/5/4 17:27
 * 课程分类
 */
public class CourseEnum {
    /**
     * 账号来源
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum courseType {

        PUBLIC_CCOURSE(1, "公开课"),
        TRAIN_COURSE(2, "训练营");



        private final Integer value;
        private final String name;

        courseType(Integer value, String name) {
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
        public static CourseEnum.courseType valueOf(Integer value) {
            for ( CourseEnum.courseType  bindChannel :  CourseEnum.courseType.values()) {
                if (Objects.equals(bindChannel.getValue(), value)) {
                    return bindChannel;
                }
            }
            return null;
        }


    }
}
