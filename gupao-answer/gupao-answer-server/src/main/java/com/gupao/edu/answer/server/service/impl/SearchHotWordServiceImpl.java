package com.gupao.edu.answer.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.answer.server.dao.SearchHotWordMapper;
import com.gupao.edu.answer.server.entity.SearchHotWord;
import com.gupao.edu.answer.server.service.SearchHotWordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchHotWordServiceImpl extends ServiceImpl<SearchHotWordMapper, SearchHotWord> implements SearchHotWordService {


    @Override
    public List<String> searchHotWords(){
        return this.list(Wrappers.<SearchHotWord>lambdaQuery()
                .eq(SearchHotWord::getStatus,true)
                .orderByDesc(SearchHotWord::getSortNum))
                .stream()
                .map(SearchHotWord::getHotWord)
                .collect(Collectors.toList());
    }


}
