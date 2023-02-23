package com.gupao.edu.answer.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.answer.server.entity.SearchHotWord;

import java.util.List;

/**
 * 搜索热词
 */
public interface SearchHotWordService extends IService<SearchHotWord> {

    /**
     * 搜索热词
     * @return
     */
    List<String> searchHotWords();

}
