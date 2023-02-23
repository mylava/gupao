package com.gupao.edu.answer.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.answer.server.dao.AskArticlesMapper;
import com.gupao.edu.answer.server.entity.AskArticles;
import com.gupao.edu.answer.server.entity.req.HomePageofArticleReq;
import com.gupao.edu.answer.server.entity.res.HomePageofArticleRes;
import com.gupao.edu.answer.server.service.AskArticlesService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxp
 * @since 2020-03-22
 */
@Service
public class AskArticlesServiceImpl extends ServiceImpl<AskArticlesMapper, AskArticles> implements AskArticlesService {
    @Override
    public void updateArticlesInfo(Integer sourceId, String userId, String categoryId, String type) {

        AskArticles articlesDTO = this.queryById(sourceId);
        if(null == articlesDTO) {
            throw new RuntimeException("操作失败：没有找到相关文章信息！");
        }
        Map<String,Object> articlesMap = new HashMap<>();
        articlesMap.put("id", sourceId);
        articlesMap.put("categoryId", categoryId);
        articlesMap.put("userId", userId);
        articlesMap.put("type", type);
        baseMapper.updateArticlesInfo(articlesMap);
    }

    @Override
    public AskArticles queryById(Integer id) {
        AskArticles askArticles = this.baseMapper.selectById(id);
        List<String> strings = baseMapper.tagList(id);
        if(!CollectionUtils.isEmpty(strings)){
            askArticles.setTagList(strings);
        }
        return askArticles;
    }

    @Override
    public IPage<HomePageofArticleRes> queryByTagId(HomePageofArticleReq tagId) {
        Page<HomePageofArticleRes> homePage = new Page<>(tagId.getPageNum(),tagId.getPageSize());
        splitTagid(tagId);
        IPage<HomePageofArticleRes> homePageofArticleResIPage = baseMapper.queryHomePageUnTop(homePage, tagId);

        return  homePageofArticleResIPage;
    }

    @Override
    public IPage<AskArticles> queryTop(HomePageofArticleReq homePageofArticleReq) {
        homePageofArticleReq.formatePage();
        IPage<AskArticles> homePage = new Page<>(homePageofArticleReq.getPageNum(),homePageofArticleReq.getPageSize());
        splitTagid(homePageofArticleReq);
        IPage<AskArticles> list = baseMapper.queryHomePageTop(homePage,homePageofArticleReq);

        return list;
    }

    private void splitTagid(HomePageofArticleReq  tagId){
        if(null != tagId.getTagIds()){
            List<Integer> tagsList = new ArrayList<>();
            String[] arrList = tagId.getTagIds().split("-");
            for(String str:arrList){
                tagsList.add(Integer.valueOf(str.trim()));
            }
            tagId.setTagIdList(tagsList);
        }
    }
}
