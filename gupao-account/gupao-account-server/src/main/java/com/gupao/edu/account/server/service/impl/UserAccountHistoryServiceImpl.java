package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.UserAccountHistory;
import com.gupao.edu.account.client.req.BasePageReq;
import com.gupao.edu.account.client.resp.AccountHistoryVO;
import com.gupao.edu.account.server.mapper.UserAccountHistoryMapper;
import com.gupao.edu.account.server.service.UserAccountHistoryService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学币流水记录表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class UserAccountHistoryServiceImpl extends ServiceImpl<UserAccountHistoryMapper, UserAccountHistory> implements UserAccountHistoryService {

    @Autowired
    private UserAccountHistoryMapper userAccountHistoryMapper;

    @Override
    public Page<AccountHistoryVO> findAccountHistory(BasePageReq basePageReq) {
        Page<AccountHistoryVO> page = new Page(basePageReq.getPage(),basePageReq.getPageNum());
        List<AccountHistoryVO> list = userAccountHistoryMapper
            .findAccountHistoryByUser(basePageReq.getUserUniqueCode(), page);
        page.setRecords(list);
        return page;
    }
}
