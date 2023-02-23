package com.gupao.edu.course.client.resp.good;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/17 14:43
 * 课程收藏返回实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("课程收藏返回实体类")
public class UserCourseFavoriteResp {


    /**
     * 用户唯一编码
     */
    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode",  position = 2)
    private String userUniqueCode;

    /**
     * 课程id
     */
    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode",  position = 2)
    private Integer courseId;
    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode",  position = 2)
    private LocalDateTime createTime;

}
