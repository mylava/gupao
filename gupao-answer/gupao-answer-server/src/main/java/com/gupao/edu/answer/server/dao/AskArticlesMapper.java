package com.gupao.edu.answer.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gupao.edu.answer.server.entity.AskArticles;
import com.gupao.edu.answer.server.entity.req.HomePageofArticleReq;
import com.gupao.edu.answer.server.entity.res.HomePageofArticleRes;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxp
 * @since 2020-03-22
 */
public interface AskArticlesMapper extends BaseMapper<AskArticles> {

    /**
     * 查询未置顶文章列表
     * @param tagId
     * @return
     */
    IPage<HomePageofArticleRes> queryHomePageUnTop(IPage<HomePageofArticleRes> iPage ,HomePageofArticleReq tagId);
    /**
     * 查询顶文章（所有）
     * @param homePageofArticleReq
     * @return
     */
    IPage<AskArticles> queryHomePageTop(IPage<AskArticles> iPage ,HomePageofArticleReq homePageofArticleReq);


    void updateArticlesInfo(Map<String, Object> articlesMap);

    List<String> tagList (Integer articleId);
}
