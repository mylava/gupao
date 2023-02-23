package com.gupao.edu.answer.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gupao.edu.answer.server.entity.AskArticles;
import com.gupao.edu.answer.server.entity.req.HomePageofArticleReq;
import com.gupao.edu.answer.server.entity.res.HomePageofArticleRes;
import com.gupao.edu.answer.server.service.AskArticlesService;
import com.gupao.edu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * (AskArticles)表控制层
 *
 * @author makejava
 * @since 2020-03-18 22:09:49
 */
@Api(value = "文章接口",tags = {"文章相关"})
@RestController
@RequestMapping("/askArticles")
public class AskArticlesController {
    /**
     * 服务对象
     */
    @Autowired
    private AskArticlesService askArticlesService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    @ApiOperation(value = "用户文章详情查询")
    public Result<AskArticles> selectOne(@Validated @RequestParam("id") Integer id) {
        return Result.success(this.askArticlesService.queryById(id));
    }

    @GetMapping("/selectList")
    @ApiOperation(value = "文章列表查询（未置顶）")
    public Result<IPage<HomePageofArticleRes>> selectList(HomePageofArticleReq homePageofArticleReq) {
        return  Result.success(this.askArticlesService.queryByTagId(homePageofArticleReq));
    }

    @GetMapping("/selectTopList")
    @ApiOperation(value = "查询置顶文章")
    public Result<IPage<AskArticles>> selectTopList(HomePageofArticleReq homePageofArticleReq) {
        IPage<AskArticles> askArticles = askArticlesService.queryTop(homePageofArticleReq);


        return Result.success(askArticles);
    }

}