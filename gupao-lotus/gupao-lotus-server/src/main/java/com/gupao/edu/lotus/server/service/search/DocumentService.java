package com.gupao.edu.lotus.server.service.search;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gupao.edu.common.utils.JsonUtils;
import com.gupao.edu.lotus.server.service.search.utils.IndexUtils;
import com.gupao.edu.lotus.server.service.search.utils.SettingData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class DocumentService {
    private static final int DEFAULT_PAGE_SIZE = 500;

    private RestHighLevelClient client;

    public SearchResponse search(SearchRequest searchRequest) throws IOException {
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }

    public <T> List<T> search(QueryBuilder queryBuilder, Class<T> indexClass, String... indexNames) throws IOException{
        List<T> list = new ArrayList<>();
        if (indexNames.length==0){
            SettingData settings = IndexUtils.getSettings(indexClass);
            indexNames = new String[]{settings.getIndexName()};
        }
        SearchRequest searchRequest = new SearchRequest(indexNames);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(DEFAULT_PAGE_SIZE);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            T t = JsonUtils.string2Obj(hit.getSourceAsString(), indexClass);
            IndexUtils.setDocId(indexClass, t, hit.getId());
            list.add(t);
        }
        return list;
    }

    public <T> IPage<T> searchPage(IPage<T> page, QueryBuilder queryBuilder, Class<T> indexClass, String... indexNames) throws IOException{
        List<T> list = new ArrayList<>();
        page.setRecords(list);
        if (indexNames.length==0){
            SettingData settings = IndexUtils.getSettings(indexClass);
            indexNames = new String[]{settings.getIndexName()};
        }
        SearchRequest searchRequest = new SearchRequest(indexNames);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.from((int)page.offset());
        searchSourceBuilder.size((int)page.getSize());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        page.setTotal(hits.getTotalHits().value);
        for (SearchHit hit : searchHits) {
            T t = JsonUtils.string2Obj(hit.getSourceAsString(), indexClass);
            IndexUtils.setDocId(indexClass, t, hit.getId());
            list.add(t);
        }
        return page;
    }

    public <T> boolean save(T t) throws IOException {
        SettingData settings = IndexUtils.getSettings(t.getClass());
        String docId = IndexUtils.getDocId(t);
        IndexRequest request = new IndexRequest(settings.getIndexName());
        if (StringUtils.hasText(docId)){
            request.id(docId);
        }
        String source = JsonUtils.obj2String(t);
        request.source(source, XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        return DocWriteResponse.Result.CREATED == response.getResult();
    }

    public <T> boolean update(T t) throws IOException {
        SettingData settings = IndexUtils.getSettings(t.getClass());
        String docId = IndexUtils.getDocId(t);
        UpdateRequest request = new UpdateRequest();
        request.index(settings.getIndexName());
        if (StringUtils.hasText(docId)){
            request.id(docId);
        }
        String source = JsonUtils.obj2String(t);
        request.doc(source, XContentType.JSON);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        return DocWriteResponse.Result.CREATED == response.getResult()
                ||DocWriteResponse.Result.UPDATED == response.getResult();
    }

    public <T> boolean delete(T t) throws IOException {
        SettingData settings = IndexUtils.getSettings(t.getClass());
        String docId = IndexUtils.getDocId(t);
        DeleteRequest request = new DeleteRequest();
        request.index(settings.getIndexName());
        if (!StringUtils.hasText(docId)){
            throw new RuntimeException("ID cannot be empty");
        }
        request.id(docId);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        return DocWriteResponse.Result.DELETED == response.getResult();
    }

    public <T> T getById(String id,Class<T> indexClass) throws IOException {
        SettingData settings = IndexUtils.getSettings(indexClass);
        GetRequest request = new GetRequest(settings.getIndexName(),id);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        if (response.isExists()){
            T t = JsonUtils.string2Obj(response.getSourceAsString(), indexClass);
            IndexUtils.setDocId(indexClass, t, response.getId());
            return t;
        }
        return null;
    }



}
