package com.gupao.edu.answer.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gupao.edu.answer.server.dto.GlobalSearchReq;
import com.gupao.edu.answer.server.dto.GlobalSearchResult;
import com.gupao.edu.answer.server.dto.SearchHistoryReq;
import com.gupao.edu.answer.server.service.SearchHistoryService;
import com.gupao.edu.answer.server.service.SearchHotWordService;
import com.gupao.edu.answer.server.service.SearchService;
import com.gupao.edu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Api(value = "搜索服务",tags = {"全局搜索"})
@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private SearchService searchService;
    private SearchHotWordService searchHotWordService;
    private SearchHistoryService searchHistoryService;
    /**
     * 全局搜索
     * @param request
     * @return
     */
    @ApiOperation("全局搜索")
    @GetMapping
    public Result<IPage<GlobalSearchResult>> search(@Valid GlobalSearchReq request){
        return Result.success(searchService.globalSearch(request));
    }

    /**
     * 热词搜索
     * @return
     */
    @ApiOperation("查询热词搜索")
    @GetMapping("/hotWords")
    public Result<List<String>> searchHotWords(){
        return Result.success(searchHotWordService.searchHotWords());
    }

    /**
     * 搜索历史
     * @return
     */
    @ApiOperation("查询搜索历史")
    @GetMapping("/history")
    public Result<Set<String>> searchHistory(SearchHistoryReq req){
        return Result.success(searchHistoryService.searchHistory(req));
    }
}
