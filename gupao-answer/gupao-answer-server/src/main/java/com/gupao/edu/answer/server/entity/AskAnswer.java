package com.gupao.edu.answer.server.entity;

import lombok.Data;
import java.util.Date;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-22 21:01
 **/
@Data
public class AskAnswer {
    /**
     * 回答id
     */
    private Integer id;
    /**
     * 问题id
     */
    private Integer questionId;
    /**
     * 回答用户id
     */
    private Integer userId;
    /**
     * 回答内容
     */
    private String content;
    /**
     * 点赞数
     */
    private Integer supports;
    /**
     * 反对数
     */
    private Integer oppositions;
    /**
     * 回复数
     */
    private Integer comments;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 状态 是否采纳 0:默认状态,没有采纳 1:采纳
     */
    private Integer status;
}
