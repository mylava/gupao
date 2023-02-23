package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.UserAccountHistory;
import com.gupao.edu.account.client.req.BasePageReq;
import com.gupao.edu.account.client.resp.AccountHistoryVO;
import java.util.List;

/**
 * <p>
 * 学币流水记录表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserAccountHistoryService extends IService<UserAccountHistory> {

    Page<AccountHistoryVO> findAccountHistory(BasePageReq basePageReq);
}
