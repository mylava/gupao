package com.gupao.edu.course.client.resp.good;

import com.gupao.edu.course.client.req.center.CourseCommentResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * 〈课程评价统计dto〉
 *
 * @author yangshibo
 * @create 2020/3/23
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("课程评价查询和统计")
public class CourseCommentCensusResp {

    @ApiModelProperty(value = "评价均分", name = "Average")
    private BigDecimal average;
    @ApiModelProperty(value = "1星数量", name = "startLevel1")
    private Integer startLevel1;
    @ApiModelProperty(value = "2星数量", name = "startLevel2")
    private Integer startLevel2;
    @ApiModelProperty(value = "3星数量", name = "startLevel3")
    private Integer startLevel3;
    @ApiModelProperty(value = "4星数量", name = "startLevel4")
    private Integer startLevel4;
    @ApiModelProperty(value = "5星数量", name = "startLevel5")
    private Integer startLevel5;
    @ApiModelProperty(value = "评论总数", name = "commentSize")
    private Integer commentSize;
    @ApiModelProperty(value = "评价数据", name = "courseCommentRespIPage")
    private List<CourseCommentResp> courseCommentResps;
//    @ApiModelProperty(value = "分页评价数据", name = "courseCommentDTOPageBean")
//    private PageBean<CourseCommentResp> courseCommentDTOPageBean;
}
