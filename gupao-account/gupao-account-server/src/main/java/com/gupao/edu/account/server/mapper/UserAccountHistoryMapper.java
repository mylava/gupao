package com.gupao.edu.account.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.account.client.entity.UserAccountHistory;
import com.gupao.edu.account.client.resp.AccountHistoryVO;
import java.util.List;

/**
 * <p>
 * 学币流水记录表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserAccountHistoryMapper extends BaseMapper<UserAccountHistory> {

    List<AccountHistoryVO> findAccountHistoryByUser(String userUniqueCode, Page page);
}
