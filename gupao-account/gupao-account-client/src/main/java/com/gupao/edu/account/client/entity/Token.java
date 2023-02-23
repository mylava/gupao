package com.gupao.edu.account.client.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/27 13:57
 */
@Data
public class Token implements Serializable {
    private Integer id;

    private Integer userId;

    private String accessToken;

    private String restType;

    private String preToken;

    private Long expiseIn;

    private Date createTime;

    private Date updateTime;

    private String refreshToken;

    private Long refreshExpiseIn;
}
