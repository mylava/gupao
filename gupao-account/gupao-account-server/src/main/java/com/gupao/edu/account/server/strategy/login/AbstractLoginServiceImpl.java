package com.gupao.edu.account.server.strategy.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gupao.edu.account.client.entity.User;
import com.gupao.edu.account.client.entity.UserLoginHistory;
import com.gupao.edu.account.client.req.LoginReq;
import com.gupao.edu.account.client.resp.LoginResp;
import com.gupao.edu.account.server.service.CommonService;
import com.gupao.edu.account.server.service.UserLoginHistoryService;
import com.gupao.edu.account.server.service.UserService;
import com.gupao.edu.account.server.strategy.login.LoginService;
import com.gupao.edu.common.cache.CacheUtil;
import com.gupao.edu.common.cache.key.UserPrefix;
import com.gupao.edu.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/27 15:28
 */
@Service
public abstract class AbstractLoginServiceImpl{
    @Autowired
    private CommonService commonService;
    @Autowired
    private UserService userService;
    @Value("${user.password.salt:123456}")
    private String passwordSalt;

    @Value("${user.jwt.secret:abcd}")
    private String secret;
    @Autowired
    private UserLoginHistoryService userLoginHistoryService;
    /**
     * 验证登录通过后的操作
     * @param loginReq
     * @return
     */
    public Result<LoginResp> newlogin(LoginReq loginReq,User user) {

       //清除其他设备token，让其他设备下线，从登录历史表里面判断token不为null的记录;
        UserLoginHistory userLoginHistory = userLoginHistoryService.getOne(new QueryWrapper<UserLoginHistory>()
                    .eq("user_unique_code", user.getUserUniqueCode()).isNotNull("access_token"));
            //说明该用户在其他入口上有在线，让其下线
            if (null != userLoginHistory) {
                userLoginHistory.setAccessToken("");
                //库中原始登录token清除
                userLoginHistoryService.update(userLoginHistory,new UpdateWrapper<UserLoginHistory>().setEntity(userLoginHistory));
                //redis中原始token清除，需要注意每次这个key必须不一样
//			redisService.remove(userLoginHistory.getAccessToken());
            }
            //生成此次的token 返回给前端
            String accessToken = LoginService.getAccessToken(user.getUserUniqueCode(), secret);
            //把用户信息存redis
             //把token和对应用户信息储存到redis----token和uniqueode
             CacheUtil.set(UserPrefix.USER_TOKEN,user.getUserUniqueCode(),accessToken);//redisService.set(accessToken, user, 1L, TimeUnit.HOURS);
             CacheUtil.set(UserPrefix.USER_UNIQUECODE,accessToken,user.getUserUniqueCode());
            //redisService.set(accessToken, user, 1L, TimeUnit.HOURS);
            LoginResp loginResp = new LoginResp();
            loginResp.setHasBindMobile(true);
            loginResp.setAccessToken(accessToken);
            //记录登录轨迹到登录历史表信息中,其他设备信息需要前端传给我
            /*userLoginHistory = new UserLoginHistory();
            userLoginHistory.setUserUniqueCode(user.getUserUniqueCode());
            userLoginHistory.setAccessToken(accessToken);
            userLoginHistoryService.save(userLoginHistory);*/

            return Result.success(loginResp);
        }
}
