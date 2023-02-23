package com.gupao.edu.answer.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.answer.server.dto.CategoriesRes;
import com.gupao.edu.answer.server.dto.TagsRes;
import com.gupao.edu.answer.server.entity.AskCategories;

/**
 * (AskCategories)表服务接口
 *
 * @author makejava
 * @since 2020-03-20 22:16:19
 */
public interface AskCategoriesService extends IService<AskCategories> {

    /**
     * 分页查询分类（文章的查询条件）
     * @param type
     * @param status
     * @return
     */
    Page<CategoriesRes> pageQueryByCategories(Page<CategoriesRes> page,String type,Integer status);

    /**
     * 分页查询热门标签（问答的查询条件）
     * @return
     */
    Page<TagsRes> selectHotTags(Page<TagsRes> page);


    Page<TagsRes> selectByCategoryId(Page<TagsRes> page,Integer categoryId);
}