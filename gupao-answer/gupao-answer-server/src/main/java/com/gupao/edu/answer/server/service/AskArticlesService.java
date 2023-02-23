package com.gupao.edu.answer.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.answer.server.entity.AskArticles;
import com.gupao.edu.answer.server.entity.req.HomePageofArticleReq;
import com.gupao.edu.answer.server.entity.res.HomePageofArticleRes;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxp
 * @since 2020-03-22
 */
public interface AskArticlesService extends IService<AskArticles> {
    /**
     * 更新文章的动态（点赞查看等数量）
     *
     * @param
     * @return 是否成功
     */
    void updateArticlesInfo(Integer sourceId, String userId, String categoryId, String type);



    AskArticles queryById(Integer id);

    /**
     * 通过tagID查询数据列表
     *
     * @param homePageofArticleReq
     * @return 实例对象
     */
    IPage<HomePageofArticleRes> queryByTagId(HomePageofArticleReq homePageofArticleReq);
    /**
     * 查询置顶文章列表
     *
     * @param homePageofArticleReq
     * @return 实例对象
     */
    IPage<AskArticles> queryTop(HomePageofArticleReq homePageofArticleReq);
}
