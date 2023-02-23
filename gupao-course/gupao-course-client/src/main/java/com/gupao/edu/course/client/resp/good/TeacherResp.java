package com.gupao.edu.course.client.resp.good;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/5/4 20:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("teacher_team")
@ApiModel("讲师对象返回实体")
public class TeacherResp {

    /**
     * 讲师名字
     */
    @ApiModelProperty(value = "讲师名称", name = "teacherName")
    private String teacherName;

    /**
     * 讲师头像
     */
    @ApiModelProperty(value = "讲师头像", name = "avatar")
    private String avatar;

    /**
     * 讲师简介
     */
    @ApiModelProperty(value = "讲师简介", name = "teacherResume")
    private String teacherResume;

}
