package com.gupao.edu.answer.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.answer.server.dto.CategoriesRes;
import com.gupao.edu.answer.server.dto.TagsRes;
import com.gupao.edu.answer.server.entity.AskCategories;
import org.apache.ibatis.annotations.Param;

/**
 * (AskCategories)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-20 22:16:19
 */
public interface AskCategoriesMapper extends BaseMapper<AskCategories> {


    /**
     * 查询热门标签
     * @param page
     * @return
     */
    Page<TagsRes> selectHotTags(Page<TagsRes> page);

    /**
     * 查询类别
     * @param page
     * @param types
     * @param status
     * @return
     */
    Page<CategoriesRes> selectCategories(Page<CategoriesRes> page,@Param("types")String[] types, @Param("status")Integer status);

    /**
     * 根据类别查询标签
     * @param page
     * @param categoryId
     * @return
     */
    Page<TagsRes> selectByCategoryId(Page<TagsRes> page,@Param("categoryId") Integer categoryId);
}