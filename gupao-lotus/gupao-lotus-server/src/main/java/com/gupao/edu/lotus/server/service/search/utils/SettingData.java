package com.gupao.edu.lotus.server.service.search.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 索引设置
 */
@Data
@NoArgsConstructor
public class SettingData {

    String indexName = "";
    int numberOfShards;
    int numberOfReplicas;


    public SettingData(String indexName) {
        this.indexName = indexName;
    }

    public SettingData(String indexName, int numberOfShards, int numberOfReplicas) {
        this.indexName = indexName;
        this.numberOfShards = numberOfShards;
        this.numberOfReplicas = numberOfReplicas;
    }


}
