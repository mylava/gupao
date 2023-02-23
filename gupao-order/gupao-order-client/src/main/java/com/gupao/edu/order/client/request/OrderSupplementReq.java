package com.gupao.edu.order.client.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单补充表 用于存储第三方渠道的课程生成的订单信息
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderSupplementReq implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 课程分类id（训练营、精品小课、vip课，对应字典表）
     */
    @ApiModelProperty(value = "课程分类id", name = "categoryId",  required = true)
    private Integer categoryId;

    /**
     * 推荐码
     */
    @ApiModelProperty(value = "推荐码", name = "recommendCode",  required = true)
    private String recommendCode;

    /**
     * 推荐老师id
     */
    @ApiModelProperty(value = "推荐老师id", name = "recommendTeacherId",  required = true)
    private Integer recommendTeacherId;

    /**
     * 推荐学员手机号
     */
    @ApiModelProperty(value = "推荐学员手机号", name = "recommendMobile",  required = true)
    private String recommendMobile;

    /**
     * 推荐学员id
     */
    @ApiModelProperty(value = "推荐学员id", name = "recommendUserId",  required = true)
    private Integer recommendUserId;

    /**
     * 听哪位老师课
     */
    @ApiModelProperty(value = "听哪位老师课", name = "lectureId",  required = true)
    private Integer lectureId;

    /**
     * 了解渠道，关联字典表
     */
    @ApiModelProperty(value = "了解渠道，关联字典表", name = "realizeChannel",  required = true)
    private Integer realizeChannel;

    /**
     * 试听次数,关联字典表
     */
    @ApiModelProperty(value = "试听次数,关联字典表", name = "auditionCounts",  required = true)
    private Integer auditionCounts;

    /**
     * 需解决的问题
     */
    @ApiModelProperty(value = "需解决的问题", name = "solveProblem",  required = true)
    private String solveProblem;

    /**
     * 第三方订单审核状态(-1:审核不通过，0:待审核，1:审核通过)
     */
    @ApiModelProperty(value = "第三方订单审核状态(-1:审核不通过，0:待审核，1:审核通过)", name = "auditState",  required = false)
    private Integer auditState;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
