package com.gupao.edu.answer.server.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 热词
 */
@Data
public class SearchHotWord implements Serializable {
    /**
     * 热词ID
     */
    @TableId
    private Integer id;
    /**
     * 热词
     */
    private String hotWord;
    /**
     * 排序
     */
    private Integer sortNum;
    /**
     * 状态
     */
    private Boolean status;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
}
