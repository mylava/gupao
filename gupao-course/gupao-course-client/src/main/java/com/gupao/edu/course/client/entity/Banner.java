package com.gupao.edu.course.client.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
    * 首页banner表
    * </p>
*
* @author hduong
* @since 2020-03-22
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * banner排序
            */
    private Integer sort;

            /**
            * banner图片
            */
    private String bannerImage;

            /**
            * banner跳转对象地址
            */
    private String bannerUrl;

            /**
            * 是否删除 0未删除 1已删除
            */
    private Boolean isdelete;

            /**
            * 创建时间
            */
    private LocalDateTime ctime;

            /**
            * 修改时间
            */
    private LocalDateTime utime;


}
