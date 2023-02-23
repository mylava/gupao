package com.gupao.edu.course.client.resp;/**
 * Created by dudu on 2020/3/22.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <h3>gupao-parent</h3>
 * <p>课程订单 dto</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-03-22 22:19
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("确认订单需要的数据")
public class CourseOrderResp {
    /**
     * 课程名字
     */
    @ApiModelProperty(value = "课程名字", name = "courseName")
    private String courseName;
    /**
     * 课程价格
     */
    @ApiModelProperty(value = "课程价格", name = "nowPrice")
    private Double nowPrice;
}
