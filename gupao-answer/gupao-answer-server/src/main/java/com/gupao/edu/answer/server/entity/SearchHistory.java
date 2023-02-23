package com.gupao.edu.answer.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class SearchHistory implements Serializable {

    /**
     * ID
     */
    @TableId
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 关键词
     */
    private String keyword;
    /**
     * 搜索时间
     */
    private Timestamp searchTime;

}
