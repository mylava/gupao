package com.gupao.edu.lotus.server.service.search.index;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gupao.edu.common.constant.SearchEnums;
import com.gupao.edu.lotus.server.service.search.annotation.DocId;
import com.gupao.edu.lotus.server.service.search.annotation.IndexSettings;
import com.gupao.edu.lotus.server.service.search.annotation.Mapping;
import com.gupao.edu.lotus.server.service.search.enums.Analyzer;
import com.gupao.edu.lotus.server.service.search.enums.DataType;
import lombok.Data;

import java.util.Date;

/**
 * 文章索引
 */
@Data
@IndexSettings(name = SearchEnums.ARTICLE,number_of_shards = 1,number_of_replicas = 0)
public class ArticleIndex {

    @DocId
    @JsonIgnore
    private Integer id;
    @Mapping(type = DataType.INTEGER)
    private Integer categoryId;
    @Mapping(type = DataType.KEYWORD)
    private String categoryName;
    @Mapping(type = DataType.TEXT,analyzer = Analyzer.IK_MAX_WORD,search_analyzer = Analyzer.IK_SMART)
    private String title;
    @Mapping(type = DataType.TEXT,analyzer = Analyzer.IK_MAX_WORD,search_analyzer = Analyzer.IK_SMART)
    private String content;
    @Mapping(type = DataType.INTEGER)
    private Integer views;
    @Mapping(type = DataType.INTEGER)
    private Integer collections;
    @Mapping(type = DataType.INTEGER)
    private Integer comments;
    @Mapping(type = DataType.INTEGER)
    private Integer supports;
    @Mapping(type = DataType.INTEGER)
    private Integer status;
    @Mapping(type = DataType.DATE)
    private Date createdAt;
    @Mapping(type = DataType.DATE)
    private Date updatedAt;
    @Mapping(type = DataType.INTEGER)
    private Integer istop;
    @Mapping(type = DataType.KEYWORD)
    private Integer openArea;
    @Mapping(type = DataType.INTEGER)
    private Integer draft;



}
