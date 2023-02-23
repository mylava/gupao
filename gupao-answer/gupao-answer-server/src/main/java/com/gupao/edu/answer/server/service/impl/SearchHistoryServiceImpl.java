package com.gupao.edu.answer.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.answer.server.dao.AskSearchHistoryMapper;
import com.gupao.edu.answer.server.dto.SearchHistoryReq;
import com.gupao.edu.answer.server.entity.SearchHistory;
import com.gupao.edu.answer.server.service.SearchHistoryService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 搜索历史
 */
@Service
public class SearchHistoryServiceImpl extends ServiceImpl<AskSearchHistoryMapper, SearchHistory> implements SearchHistoryService {


    @Override
    public Set<String> searchHistory(SearchHistoryReq req){
        Page<SearchHistory> pageParam = new Page<>(req.getPageNum(),req.getPageSize());
        IPage<SearchHistory> page = this.page(pageParam, Wrappers.<SearchHistory>lambdaQuery()
                .eq(SearchHistory::getUserId, req.getUserId())
                .orderByDesc(SearchHistory::getSearchTime));
        return page.getRecords().stream().map(SearchHistory::getKeyword).collect(Collectors.toSet());
    }

    @Override
    public boolean saveSearchHistory(Integer userId,String keyword){
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setUserId(userId);
        searchHistory.setKeyword(keyword);
        searchHistory.setSearchTime(new Timestamp(System.currentTimeMillis()));
        return this.save(searchHistory);
    }

}
