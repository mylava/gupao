package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.UserAccount;
import com.gupao.edu.account.client.resp.AccountBalanceResp;
import com.gupao.edu.account.client.resp.CoinVO;
import com.gupao.edu.account.server.mapper.UserAccountMapper;
import com.gupao.edu.account.server.service.UserAccountService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学币账户表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public AccountBalanceResp findAccountBalance(String userUniqueCode) {
        AccountBalanceResp resp = new AccountBalanceResp();
        QueryWrapper qw = new QueryWrapper();
        qw.eq("user_unique_code",userUniqueCode);
        UserAccount userAccount = userAccountMapper.selectOne(qw);
        if(userAccount != null){
            resp.setBalance(userAccount.getAvailableAmount());
        }
        //todo 查询商品信息
        List<CoinVO> coinVOS = new ArrayList<>();
        resp.setCoinVOList(coinVOS);
        return resp;
    }

    @Override
    public UserAccount finByUserUniqueCode(String userUniqueCode) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("user_unique_code",userUniqueCode);
        return userAccountMapper.selectOne(qw);
    }
}
