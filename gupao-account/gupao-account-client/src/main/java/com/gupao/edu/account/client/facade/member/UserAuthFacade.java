package com.gupao.edu.account.client.facade.member;

import com.gupao.edu.account.client.resp.UserSimpleInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 30/03/2020
 */
@FeignClient(name = "gupao-account",url = "localhost:18011")
public interface UserAuthFacade {

    /**
     * 通过token获取用户ID，token无效或过期时，返回空
     * @param token 登录令牌
     * @return 用户ID
     */
    @GetMapping("/validateToken")
    Long validateToken(String token);

    /**
     * 获取用户 的 简单信息 提供给外部调用
     *  @RequestParam需要加上 保持和 那个 Controller层一致
     * @param userUniqueCode
     * @return
     */
    @GetMapping("/home/getUserSimpleInfo")
    UserSimpleInfoDTO getUserSimpleInfo(@RequestParam("userUniqueCode") String userUniqueCode);
}
