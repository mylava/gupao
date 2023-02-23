package com.gupao.edu.answer.client.api;

import com.gupao.edu.answer.client.dto.ArticlesDTO;
import com.gupao.edu.answer.client.exception.BaseException;
import com.gupao.edu.answer.client.form.ArticlesForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
@FeignClient(name = "gupao-answer",url = "http://localhost:18084")
public interface ArticleApiService {

    /**
     * 前端查询所有文章数据
     * @param articlesForm
     * @return 文章数据列表
     */
//    @ResponseBody
//    @RequestMapping("/select")
//    PageInfo<ArticlesDTO> select(@RequestBody ArticlesForm articlesForm, Integer userId) ;

    /**
     * 用户分页查询所有文章数据
     * @param articlesForm
     * @return 文章数据列表
     */
//    PageInfo<ArticlesDTO> pageQuery(ArticlesForm articlesForm) ;

    /**
     * 查询起始结束日期用户发表文章排行榜
     * @param pageNum
     * @param pageSize
     * @param startTime
     * @param endTime
     * @return
     */
//    PageInfo<ArticlesDTO> pageQueryOrderByArticle(Integer pageNum, Integer pageSize, String startTime, String endTime);

    /**
     * 根据条件查找文章总数
     * @param articlesForm
     * @return
     */
    int getCount(ArticlesForm articlesForm);

    /**
     * 根据id查看文章详情
     * @param id
     * @return Articles
     */
    ArticlesDTO selectArticlesById(Integer id) ;

    /**
     * 根据id查看文章详情
     * @param id
     * @param userId
     * @return ArticlesDTO
     * @
     */
    ArticlesDTO getArticlesById(Integer id, Integer userId) ;

    /**
     * 修改文章
     * @param articlesForm
     */
    Integer updateArticles(ArticlesForm articlesForm) ;

    /**
     * 新增文章
     */
    Integer addArticles(ArticlesForm articlesForm) ;

    /**
     * 将文章草稿发表为正式文章
     * @param articlesForm
     * @return
     * @
     */
    Integer addDraftArticles(ArticlesForm articlesForm) ;

    /**
     * 定时任务发送文章
     * @param articlesForm
     * @return
     * @
     */
    Integer addTaskArticles(ArticlesForm articlesForm) ;

    /**
     * 文章新增修改草稿
     * @param articlesForm
     */
    Integer draft(ArticlesForm articlesForm) ;

    /**
     * 删除草稿
     * @param id
     * @param userId
     * @return
     */
    boolean deleteDraft(Integer id, Integer userId)  throws BaseException;

    /**
     * 通过审核移动分类删除
     * @param id  文章id
     * @param categoryId  分类id
     * @param userId  用户id
     * @param type  //1 新增 2修改 3 删除 4 移动分类  5 审核通过
     */
    boolean updateArticlesInfo(Integer id, Integer userId, Integer categoryId, String type) throws BaseException;

    /**
     * 相似文章
     * @param articlesForm
     * @return
     * @
     */
//    PageInfo<ArticlesDTO> similarArticle(ArticlesForm articlesForm) ;

    /**
     * 统计当前时间往前一周每天的文章数
     * @param dateTime
     * @return
     */
    List<Map<String,Object>> getArticlesWeekDayCountsList(String dateTime);

    /**
     * 待审核文章列表
     * @param userId
     * @return
     */
//    PageInfo<ArticlesDTO> auditArticleList(Integer userId, int page, int pageSize) ;

    /**
     * 打赏文章接口
     * @param form
     * @return
     */
    boolean reward(ArticlesForm form) ;
}
