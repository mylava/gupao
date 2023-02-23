package com.gupao.edu.answer.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.answer.server.dao.AskCategoriesMapper;
import com.gupao.edu.answer.server.dto.CategoriesRes;
import com.gupao.edu.answer.server.dto.TagsRes;
import com.gupao.edu.answer.server.entity.AskCategories;
import com.gupao.edu.answer.server.service.AskCategoriesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * (AskCategories)表服务实现类
 *
 * @author makejava
 * @since 2020-03-20 22:16:19
 */
@Service("askCategoriesService")
public class AskCategoriesServiceImpl extends ServiceImpl<AskCategoriesMapper,AskCategories> implements AskCategoriesService {


    @Override
    public Page<CategoriesRes> pageQueryByCategories(Page<CategoriesRes> page,String types,Integer status) {
        String[] split = null;
        if(StringUtils.isNotBlank(types)){
            split = StringUtils.split(types, ",");
        }
        return this.baseMapper.selectCategories(page,split, status);
    }

    @Override
    public Page<TagsRes> selectHotTags(Page<TagsRes> page) {
        return this.baseMapper.selectHotTags(page);
    }

    @Override
    public Page<TagsRes> selectByCategoryId(Page<TagsRes> page, Integer categoryId) {
        return this.baseMapper.selectByCategoryId(page,categoryId);
    }
}