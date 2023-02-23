package com.gupao.edu.account.client.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * <h3>app-backend</h3>
 * <p>用户 feign 接口传输对象</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-03 15:03
 **/
@Data
public class UserSimpleInfoDTO implements Serializable {

    private static final long serialVersionUID = -3057587146660536118L;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 昵称
     */
    private String nickName;

    private String userUniqueCode;
}
