package com.gupao.edu.course.client.req.good;

import com.gupao.edu.course.client.req.BasePageParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 这个对象是专门用来 分页查询 课程 的条件
 * @author hongdu
 * @since 2020-03-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseTypeQueryReq extends BasePageParams {
    /**
     * 学科名字  : 模糊查询 还是 类型查询
     * 根据学科名字 --> 学科Id
     */
    private Integer subjectType;

    /**
     * 根据 课程名称 查询
     */
    private String courseType;

}
