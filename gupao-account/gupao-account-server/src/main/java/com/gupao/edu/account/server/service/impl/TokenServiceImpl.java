package com.gupao.edu.account.server.service.impl;

import com.gupao.edu.account.client.entity.Token;
import com.gupao.edu.account.server.constants.ConstantCom;
import com.gupao.edu.account.server.service.TokenService;
import com.gupao.edu.common.cache.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/27 14:04
 */
@Component
@Transactional
public class TokenServiceImpl implements TokenService {
    @Autowired
    RedisService redisService;

    private static long TOKEN_TS = 7 * 24 * 60 * 60*1000;            //token有效期 毫秒数
    private static long RT_TOKEN_TS = 11 * 24 * 60 * 60*1000;       //REFRESH_TOEKN有效期 毫秒数

    /**
     * 生成token
     * @param uid 用户ID
     * @return
     */
    public Token getNewToken(Integer uid){
        String tk = UUID.randomUUID().toString().replaceAll("-","");
        String rftk = UUID.randomUUID().toString().replaceAll("-","");
        String key = ConstantCom.ACCESS_TOEKN_PREFIX.concat(tk);
        String key2 = ConstantCom.REFRESH_TOEKN__PREFIX.concat(rftk);
        String key3 = ConstantCom.REFRESH_TOEKN__UID_PREFIX.concat(rftk);
        String key4 = ConstantCom.ACCESS_TOEKN_RF_PREFIX.concat(tk);

        Token token = new Token();
        long ts = System.currentTimeMillis();
        long tkts = ts+TOKEN_TS;
        long rftkts = ts+RT_TOKEN_TS;
        token.setAccessToken(tk);
        token.setRefreshToken(rftk);
        token.setExpiseIn(tkts);
        token.setRefreshExpiseIn(rftkts);
        token.setUserId(uid);

        redisService.set(key4,rftk,TOKEN_TS, TimeUnit.MILLISECONDS);
        redisService.set(key,uid,TOKEN_TS, TimeUnit.MILLISECONDS);
        redisService.set(key2,tk,RT_TOKEN_TS, TimeUnit.MILLISECONDS);
        redisService.set(key3,uid,RT_TOKEN_TS, TimeUnit.MILLISECONDS);

        return token;
    }

    public boolean validateToken(String accessToken, HttpServletRequest request) throws Exception {
        String key = ConstantCom.ACCESS_TOEKN_PREFIX.concat(accessToken);
        Integer uid = (Integer)redisService.get(key);
        if(uid == null){
            throw new Exception( "accessToken无效");
        }else{
            request.setAttribute("userId",uid);
        }
        return true;
    }

    /**
     * 刷新token
     * @param reftoken
     * @return
     */
    public Token refreshNewToken(String reftoken) throws Exception {
        String key2 = ConstantCom.REFRESH_TOEKN__PREFIX.concat(reftoken);
        String actToken = (String)redisService.get(key2);
        if(actToken == null){
            throw new Exception("refreshToken无效");
        }
        String key3 = ConstantCom.REFRESH_TOEKN__UID_PREFIX.concat(reftoken);
        Integer uid = (Integer)redisService.get(key3);
        String key = ConstantCom.ACCESS_TOEKN_PREFIX.concat(actToken);
        String key4 = ConstantCom.ACCESS_TOEKN_RF_PREFIX.concat(actToken);
        redisService.remove(key,key2,key3,key4);
        return getNewToken(uid);
    }

    /**
     * 删除token
     * @param
     * @return
     */
    public void removeToken(String token){

        String key = ConstantCom.ACCESS_TOEKN_PREFIX.concat(token);
        String key4 = ConstantCom.ACCESS_TOEKN_RF_PREFIX.concat(token);
        String rftk = (String)redisService.get(key4);
        String key2 = "",key3="";
        if(StringUtils.isNotBlank(rftk)){
            key2 = ConstantCom.REFRESH_TOEKN__PREFIX.concat(rftk);
            key3 = ConstantCom.REFRESH_TOEKN__UID_PREFIX.concat(rftk);
        }
        redisService.remove(key,key2,key3,key4);
    }

}
