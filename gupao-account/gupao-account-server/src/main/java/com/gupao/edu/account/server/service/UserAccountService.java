package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.UserAccount;
import com.gupao.edu.account.client.resp.AccountBalanceResp;

/**
 * <p>
 * 学币账户表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserAccountService extends IService<UserAccount> {

    /**
     * 根据用户编码查询学币信息
     * @param userUniqueCode
     * @return
     */
    AccountBalanceResp findAccountBalance(String userUniqueCode);

    /**
     * 根据用户编码查询账户信息
     * @param userUniqueCode
     * @return
     */
    UserAccount finByUserUniqueCode(String userUniqueCode);

}
