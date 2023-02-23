package com.gupao.edu.course.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.course.client.dto.CourseHomeworkDTO;
import com.gupao.edu.course.client.dto.HomeworkDetailDTO;
import com.gupao.edu.course.client.entity.CourseHomework;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 作业问题表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseHomeworkMapper extends BaseMapper<CourseHomework> {

    /**
     * 根据大纲ID获取大纲作业题目列表
     * @param outlineId 大纲ID
     * @return 大纲作业题目列表
     */
     List<CourseHomeworkDTO> selectCourseHomeworkListByOutlineId(@Param("outlineId") Integer outlineId);

    /**
     * 根据作业ID获取作业详情
     * @param id 作业ID
     * @return 作业详情
     */
    HomeworkDetailDTO selectHomeworkDetailById(@Param("id") Integer id);

}
