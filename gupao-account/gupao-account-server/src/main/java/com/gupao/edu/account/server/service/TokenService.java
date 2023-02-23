package com.gupao.edu.account.server.service;

import com.gupao.edu.account.client.entity.Token;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/27 13:54
 */
public interface TokenService {
    /**
     * 生成token
     * @param uid 用户ID
     * @return
     */
    Token getNewToken(Integer uid);

    /**
     * 刷新token
     * @param token
     * @return
     */
    Token refreshNewToken(String token) throws Exception;

    /**
     * 验证token
     * @param accessToken
     * @param request
     * @return
     */
    boolean validateToken(String accessToken, HttpServletRequest request) throws Exception;

    /**
     * 删除token
     * @param token
     */
    void removeToken(String token);
}
