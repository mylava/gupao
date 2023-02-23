package com.gupao.edu.lotus.server.service.search.utils;

import com.gupao.edu.lotus.server.service.search.enums.Analyzer;
import com.gupao.edu.lotus.server.service.search.enums.DataType;
import lombok.Data;

/**
 * mapping注解对应的数据
 **/
@Data
public class MappingData {

    /*
     * 字段名
     */
    String fieldName;
    /**
     * 日期类型的格式
     */
    String format;
    /*
     * 数据类型（包含 关键字类型）
     */
    DataType type = DataType.TEXT;
    /*
     * 索引分词器设置
     */
    Analyzer analyzer = Analyzer.STANDARD;
    /**
     * 搜索内容分词器设置
     */
    Analyzer searchAnalyzer = Analyzer.STANDARD;




}
