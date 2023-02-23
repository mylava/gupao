package com.gupao.edu.course.client.resp.managecourse;

import com.gupao.edu.course.client.resp.CourseDetailResp;
import com.gupao.edu.course.client.resp.OutLineResp;
import com.gupao.edu.course.client.resp.good.CourseResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <h3>gupao-parent</h3>
 * <p>ManageCourseResp 管理后台的 课程编辑查询返回对象</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-03-24 13:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("后台课程详情")
public class ManageCourseResp {
    /**
     * 课程 详情
     */
    @ApiModelProperty(value = "课程对象", name = "gpCourse", position = 1)
    private CourseDetailResp courseResp;

    /**
     * 课程 大纲集合
     */
    @ApiModelProperty(value = "课程大纲集合", name = "outlines", position = 2)
    private List<OutLineResp> outlines;
}
