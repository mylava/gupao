package com.gupao.edu.answer.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.answer.server.dto.SearchHistoryReq;
import com.gupao.edu.answer.server.entity.SearchHistory;

import java.util.Set;

/**
 * 搜索历史
 */
public interface SearchHistoryService extends IService<SearchHistory> {

    /**
     * 查询搜索历史
     * @param req
     * @return
     */
    Set<String> searchHistory(SearchHistoryReq req);

    /**
     * 保存搜索历史
     * @param userId
     * @param keyword
     * @return
     */
    boolean saveSearchHistory(Integer userId,String keyword);

}
