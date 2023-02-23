package com.gupao.edu.course.client.req.good;/**
 * Created by dudu on 2020/3/20.
 */

import com.gupao.edu.course.client.req.BasePageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>gupao-parent</h3>
 * <p>学科 分页查询</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-03-20 12:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseListReq extends BasePageParams {
    /**
     * 根据 暂时用不到
     */
    @ApiModelProperty(value = "学科id(1.代表JAVA,2.代表大数据)", name = "grade_id")
    private Integer grade_id;

    /**
     * 分类ID 1课程类型（训练营、小课）
     * 2班级类型（Java、大数据）
     * 3学历 4职位 5互动通知类型 6订单渠道 7获悉渠道 8权益
     * dicValue
     */
    @ApiModelProperty(value = "1课程类型（1.小课,2.公开课）", name = "category_id")
    private Integer category_id;
}
