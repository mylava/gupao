package com.gupao.edu.course.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/17 15:03
 * 我的课程返回实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("我的课程返回实体类")
public class UserCourseResp {
    // 对应小节id
    @ApiModelProperty(value = "对应小节id", name = "outlineId",  position = 1)
    private Integer outlineId;
    // 课程数量
    @ApiModelProperty(value = "课程数量", name = "count",  position = 2)
    private Integer count;
    // 提醒上课时间
    @ApiModelProperty(value = "提醒上课时间", name = "notifyTime",  position = 3)
    private Date notifyTime;
    // 一周的第几天
    @ApiModelProperty(value = "一周的第几天", name = "weekDay",  position = 4)
    private String weekDay;
    // 学习内容--要提醒的学习数量
    @ApiModelProperty(value = "学习内容", name = "outLineName",  position = 5)
    private String outLineName;
}
