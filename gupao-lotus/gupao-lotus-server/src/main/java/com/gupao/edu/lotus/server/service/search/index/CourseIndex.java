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
 * 课程索引
 */
@Data
@IndexSettings(name = SearchEnums.COURSE,number_of_shards = 1,number_of_replicas = 0)
public class CourseIndex {

    @DocId
    @JsonIgnore
    private Integer id;
    @Mapping(type = DataType.INTEGER)
    private Integer typeId;
    @Mapping(type = DataType.KEYWORD)
    private String typeName;
    @Mapping(type = DataType.INTEGER)
    private Integer subjectId;
    @Mapping(type = DataType.KEYWORD)
    private String subjectName;
    @Mapping(type = DataType.TEXT,analyzer = Analyzer.IK_MAX_WORD,search_analyzer = Analyzer.IK_SMART)
    private String courseName;
    @Mapping(type = DataType.TEXT,analyzer = Analyzer.IK_MAX_WORD,search_analyzer = Analyzer.IK_SMART)
    private String courseTitle;
    @Mapping(type = DataType.INTEGER)
    private Integer viewNum;
    @Mapping(type = DataType.INTEGER)
    private Integer studyNum;
    @Mapping(type = DataType.INTEGER)
    private Integer commentNum;
    @Mapping(type = DataType.INTEGER)
    private Integer attentionNum;
    @Mapping(type = DataType.INTEGER)
    private Integer goodNum;
    @Mapping(type = DataType.INTEGER)
    private Integer isdelete;
    @Mapping(type = DataType.DATE)
    private Date ctime;
    @Mapping(type = DataType.DATE)
    private Date utime;

}
