package com.gupao.edu.answer.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.account.client.resp.UserVO;
import com.gupao.edu.answer.server.dto.*;
import com.gupao.edu.answer.server.service.SearchService;
import com.gupao.edu.common.constant.SearchEnums;
import com.gupao.edu.common.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private RestHighLevelClient restHighLevelClient;

    @Override
    public IPage<GlobalSearchResult> globalSearch(GlobalSearchReq globalSearchReq) {
        IPage<GlobalSearchResult> page = new Page<>(globalSearchReq.getCurrent(),globalSearchReq.getSize());
        List<GlobalSearchResult> list = new ArrayList<>();
        page.setRecords(list);
        SearchRequest request = new SearchRequest(getIndexNames(globalSearchReq.getType()));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(getQueryBuilder(globalSearchReq.getType(), globalSearchReq.getKeywords()));
        sourceBuilder.sort(ScoreSortBuilder.NAME, SortOrder.DESC);
        sourceBuilder.from((int)page.offset());
        sourceBuilder.size((int)page.getSize());
        request.source(sourceBuilder);
        try {
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            SearchHit[] searchHits = hits.getHits();
            page.setTotal(searchHits.length);
            for (SearchHit searchHit : searchHits) {
                String index = searchHit.getIndex();
                SearchEnums.IndexEnum indexEnums = SearchEnums.IndexEnum.getEnum(index);
                String sourceAsString = searchHit.getSourceAsString();
                GlobalSearchResult result = convertResult(indexEnums, sourceAsString,searchHit.getId());
                list.add(result);
            }
        } catch (IOException e) {
            log.error("查询失败",e);
        }
        return page;
    }

    private String[] getIndexNames(SearchEnums.SearchType type){
        if (type==SearchEnums.SearchType.ALL){
            return Arrays.stream(SearchEnums.IndexEnum.values()).map(SearchEnums.IndexEnum::getIndex).toArray(String[]::new);
        } else {
            return new String[]{SearchEnums.IndexEnum.valueOf(type.getCode()).getIndex()};
        }
    }

    private QueryBuilder getQueryBuilder(SearchEnums.SearchType type,String keywords){
        QueryBuilder queryBuilder = null;
        switch (type){
            case ALL:
                queryBuilder = QueryBuilders.boolQuery()
                        .must(QueryBuilders
                                        .multiMatchQuery(keywords,"category_name","title","content"
                                                ,"type_name","subject_name","course_name","course_title","username","nickname"
                                        )
                        )
                        .mustNot(QueryBuilders.matchQuery("status",0))
                        .mustNot(QueryBuilders.matchQuery("hide",0))
                        .mustNot(QueryBuilders.matchQuery("istop",1))
                        .mustNot(QueryBuilders.matchQuery("draft",0))
                        .mustNot(QueryBuilders.matchQuery("user_status",0))
                        .mustNot(QueryBuilders.matchQuery("isdelete",0))
                ;
                break;
            case COURSE:
                queryBuilder = QueryBuilders.boolQuery()
                        .must(QueryBuilders
                                        .multiMatchQuery(keywords,"type_name","subject_name","course_name","course_title")
                        )
                        .mustNot(QueryBuilders.matchQuery("isdelete",0));
                break;
            case QUESTION:
                queryBuilder = QueryBuilders.boolQuery()
                        .must(QueryBuilders
                                .multiMatchQuery(keywords,"category_name","title","content")
                        )
                        .mustNot(QueryBuilders.matchQuery("status",0))
                        .mustNot(QueryBuilders.matchQuery("hide",0))
                        .mustNot(QueryBuilders.matchQuery("istop",1))
                        .mustNot(QueryBuilders.matchQuery("draft",0));
                break;
            case ARTICLE:
                queryBuilder = QueryBuilders.boolQuery()
                        .must(QueryBuilders
                                .multiMatchQuery(keywords,"category_name","title","content")
                        )
                        .mustNot(QueryBuilders.matchQuery("status",0))
                        .mustNot(QueryBuilders.matchQuery("hide",0))
                        .mustNot(QueryBuilders.matchQuery("istop",1))
                        .mustNot(QueryBuilders.matchQuery("draft",0));
                break;
            case USER:
                queryBuilder = QueryBuilders.boolQuery()
                        .must(QueryBuilders
                                .multiMatchQuery(keywords,"username","nickname")
                        )
                        .mustNot(QueryBuilders.matchQuery("user_status",0))
                        .mustNot(QueryBuilders.matchQuery("isdelete",0));
                break;

        }
        return queryBuilder;
    }

    private GlobalSearchResult convertResult(SearchEnums.IndexEnum indexEnums, String jsonString, String id){
        GlobalSearchResult result = new GlobalSearchResult();
        result.setContentType(indexEnums.getCode());
        Integer contentId = Integer.valueOf(id);
        result.setContentId(contentId);
        switch (indexEnums) {
            case COURSE:
                CourseDTO dto = JsonUtils.string2Obj(jsonString, CourseDTO.class);
                dto.setCourseId(contentId);
                result.setCourse(dto);
                break;
            case USER:
                UserVO user = JsonUtils.string2Obj(jsonString, UserVO.class);
                user.setUserId(contentId);
                result.setUser(user);
                break;
            case ARTICLE:
                ArticlesDTO articles = JsonUtils.string2Obj(jsonString, ArticlesDTO.class);
                articles.setId(contentId);
                result.setArticle(articles);
                break;
            case QUESTION:
                QuestionDTO question = JsonUtils.string2Obj(jsonString, QuestionDTO.class);
                question.setId(contentId);
                result.setQuestion(question);
                break;
        }
        return result;
    }

}
