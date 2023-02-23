package com.gupao.edu.lotus.server.service.search;

import com.gupao.edu.lotus.server.service.search.enums.DataType;
import com.gupao.edu.lotus.server.service.search.utils.IndexUtils;
import com.gupao.edu.lotus.server.service.search.utils.MappingData;
import com.gupao.edu.lotus.server.service.search.utils.SettingData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class IndexService {

    private RestHighLevelClient client;

    /**
     * 创建索引
     * @param indexClass
     * @param <T>
     * @throws IOException
     */
    public <T> CreateIndexResponse createIndex(Class<T> indexClass) throws IOException {
        SettingData settings = IndexUtils.getSettings(indexClass);
        String indexName = settings.getIndexName();
        int shards = settings.getNumberOfShards();
        int replicas = settings.getNumberOfReplicas();
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder()
            .put("index.number_of_shards", shards)
            .put("index.number_of_replicas", replicas)
        );
        List<MappingData> mappingList = IndexUtils.getMappingData(indexClass);
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                for (MappingData mapping : mappingList) {
                    builder.startObject(mapping.getFieldName());
                    {
                        DataType type = mapping.getType();
                        builder.field("type", type);
                        switch (type){
                            case TEXT:
                                builder.field("analyzer", mapping.getAnalyzer().toString());
                                builder.field("search_analyzer", mapping.getAnalyzer().toString());
                                break;
                            case DATE:
                                builder.field("format", mapping.getFormat());
                                break;
                        }

                    }
                    builder.endObject();
                }

            }
            builder.endObject();
        }
        builder.endObject();
        request.mapping(builder);
//        别名
//        request.alias(new Alias("twitter_alias").filter(QueryBuilders.termQuery("user", "kimchy")));
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        String index = response.index();
        log.info("创建索引:{}成功！",index);
        return response;
    }

    /**
     * 是否存在索引
     * @param indexClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> boolean exists(Class<T> indexClass) throws IOException {
        SettingData settings = IndexUtils.getSettings(indexClass);
        String indexName = settings.getIndexName();
        GetIndexRequest request = new GetIndexRequest(indexName);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

    /**
     * 删除索引
     * @param indexClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> AcknowledgedResponse deleteIndex(Class<T> indexClass) throws IOException {
        SettingData settings = IndexUtils.getSettings(indexClass);
        String indexName = settings.getIndexName();
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        return client.indices().delete(request, RequestOptions.DEFAULT);
    }
}
