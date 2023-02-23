package com.gupao.edu.answer.server.entity;

import java.util.Date;
import lombok.Data;

/**
 * @description
 * @author: chenlong
 * @data: 2020-04-11 20:35
 **/
@Data
public class AskTag {

    private Integer id;
    //标签名称
    private String name;
    //分类id
    private Integer categoryId;

    private String logo;

    private String summary;

    private String description;

    private Integer parentId;

    private Integer followers;

    private Date createdAt;

    private Date updatedAt;
}
