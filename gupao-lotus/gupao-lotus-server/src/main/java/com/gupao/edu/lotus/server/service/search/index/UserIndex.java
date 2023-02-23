package com.gupao.edu.lotus.server.service.search.index;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gupao.edu.common.constant.SearchEnums;
import com.gupao.edu.lotus.server.service.search.annotation.DocId;
import com.gupao.edu.lotus.server.service.search.annotation.IndexSettings;
import com.gupao.edu.lotus.server.service.search.annotation.Mapping;
import com.gupao.edu.lotus.server.service.search.enums.DataType;
import lombok.Data;

import java.util.Date;

/**
 * 用户索引
 */
@Data
@IndexSettings(name = SearchEnums.USER,number_of_shards = 1,number_of_replicas = 0)
public class UserIndex {

    @DocId
    @JsonIgnore
    private Integer id;
    @Mapping(type = DataType.KEYWORD)
    private String username;
    @Mapping(type = DataType.KEYWORD)
    private String nikename;
    @Mapping(type = DataType.INTEGER)
    private Integer age;
    @Mapping(type = DataType.INTEGER)
    private Integer gender;
    @Mapping(type = DataType.INTEGER)
    private Integer vipNum;
    @Mapping(type = DataType.INTEGER)
    private Integer userStatus;
    @Mapping(type = DataType.INTEGER)
    private Integer goodNum;
    @Mapping(type = DataType.INTEGER)
    private Integer isdelete;
    @Mapping(type = DataType.DATE)
    private Date createdTime;
    @Mapping(type = DataType.DATE)
    private Date updatedTime;

}
