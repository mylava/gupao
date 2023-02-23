package com.gupao.edu.lotus.client.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/5/2 13:11
 */
@Data
@AllArgsConstructor
public class VerifyCodeMessage implements Serializable {
    private String mobile;
    private Integer bizCode;
    private String msg;
}
