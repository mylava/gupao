package com.gupao.edu.answer.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-22 20:52
 **/
@Data
public class AskQuestion {
    /**
     * 问题id
     */
    private Integer id ;
    /**
     * 用户id
     */
    private Integer userId ;
    /**
     * 标题
     */
    private String title ;
    /**
     * 内容
     */
    private String description ;
    /**
     * 价格
     */
    private Integer price ;
    /**
     * 采纳回答ID
     */
    private Integer answers ;
    /**
     * 查看数
     */
    private Integer views ;
    /**
     * 收藏次数
     */
    private Integer collections ;
    /**
     * 评论次数
     */
    private Integer comments ;
    /**
     * 状态
     */
    private Integer status ;
    /**
     * 创建时间
     */
    private Date createdAt ;
    /**
     * 更新时间
     */
    private Date updatedAt ;
    /**
     * 是否置顶
     */
    private Byte istop ;
    /**
     * 置顶时间
     */
    private Date topAt ;
    /**
     * 内容类型（ueditor  markdown）
     */
    private Integer editorType ;
    /**
     * markdown内容
     */
    private Integer mddescription ;
    /**
     * 公开区域(all：所有，vip：对vip)
     */
    private Integer openArea ;
    /**
     * 采纳用户ID
     */
    private Integer adoptUserId ;
}
