package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.UserLoginHistory;
import com.gupao.edu.account.server.mapper.UserLoginHistoryMapper;
import com.gupao.edu.account.server.service.UserLoginHistoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录历史记录表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class UserLoginHistoryServiceImpl extends ServiceImpl<UserLoginHistoryMapper, UserLoginHistory> implements UserLoginHistoryService {

}
