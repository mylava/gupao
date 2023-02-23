package com.gupao.edu.common.dict.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 数据字典表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类编码
     */
    private String categoryCode;

    /**
     * 分类ID 1课程类型（训练营、小课） 2班级类型（Java、大数据） 3学历 4职位 5互动通知类型 6订单渠道 7获悉渠道 8权益
     */
    private Integer categoryId;

    /**
     * 字典数据名称
     */
    private String dictName;

    /**
     * 字典数据的值
     */
    private String dictValue;

    /**
     * 描述
     */
    private String description;


}
