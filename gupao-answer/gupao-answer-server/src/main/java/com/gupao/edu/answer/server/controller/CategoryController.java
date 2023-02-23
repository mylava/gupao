package com.gupao.edu.answer.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.answer.server.dto.CategoriesRes;
import com.gupao.edu.answer.server.dto.TagsRes;
import com.gupao.edu.answer.server.service.AskCategoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value = "类别",tags = {"文章和问答的标签"})
@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private AskCategoriesService askCategoriesService;

    /**
     * 热门标签
     * @return
     */
    @ApiOperation(value = "热门标签",notes = "问答的查询条件")
    @GetMapping("/hotTags")
    public Result<Page<TagsRes>> selectHotTags(Page<TagsRes> page){
        return Result.success(askCategoriesService.selectHotTags(page));
    }

    /**
     * 类别查询
     * 文章 articles
     * 问答 questions
     * @return
     */
    @ApiOperation(value = "类别查询",notes = "文章的查询条件，发文章或者提问时的选择标签")
    @GetMapping("/page")
    public Result<Page<CategoriesRes>> selectCategoryByPage(Page<CategoriesRes> page,
            @ApiParam(name = "types",value = "标签类型 articles  questions " ,required = true)@RequestParam(value = "types")String types){
        return Result.success(askCategoriesService.pageQueryByCategories(page,types,1));
    }

    /**
     * 根据类别ID查询标签
     * @param page
     * @param id
     * @return
     */
    @ApiOperation(value = "根据分类查询标签",notes = "提问时根据类别选择标签")
    @GetMapping("/{id}/tags")
    public Result<Page<TagsRes>> queryTags(Page<TagsRes> page,
            @ApiParam(name = "id",value = "类别ID" ,required = true) @PathVariable Integer id){
        return Result.success(askCategoriesService.selectByCategoryId(page, id));
    }

}
