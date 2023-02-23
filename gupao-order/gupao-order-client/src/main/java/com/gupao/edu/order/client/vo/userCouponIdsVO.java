package com.gupao.edu.order.client.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-05-12 13:56
 */
public class userCouponIdsVO {

    /**
     * 用户id
     */
    @ApiModelProperty(value="用户全局唯一标识", name="userUniqueCode",  required = true)
    private String userUniqueCode;
}
