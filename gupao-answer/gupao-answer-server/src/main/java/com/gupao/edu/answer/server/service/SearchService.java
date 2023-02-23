package com.gupao.edu.answer.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gupao.edu.answer.server.dto.GlobalSearchReq;
import com.gupao.edu.answer.server.dto.GlobalSearchResult;

import java.util.List;

public interface SearchService {

    /**
     * 全局搜索
     * @param request
     * @return
     */
    IPage<GlobalSearchResult> globalSearch(GlobalSearchReq request);

}
