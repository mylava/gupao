package com.gupao.edu.course.client.resp.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/20 17:47
 * 学习中心返回实体类
 */
@Data
@ApiModel("学习中心返回实体类")
public class CenterListResp {
    /**
     * 今日学习时间
     */
    @ApiModelProperty(value = "今日学习时间", name = "studyTime",  position = 1)
    private Long studyTime;
    /**
     * 总共学习时间
     */
    @ApiModelProperty(value = "总共学习时间", name = "totalStudyTime",  position = 2)
    private Long totalStudyTime;
    /**
     * 我购买的课程数量
     */
    @ApiModelProperty(value = "我购买的课程数量", name = "size",  position = 3)
    private Integer size;
    /**
     * 我的课程
     */
    @ApiModelProperty(value = "我的课程", name = "myCourse",  position = 4)
    private List<MyCourseResp> myCourse;

}
