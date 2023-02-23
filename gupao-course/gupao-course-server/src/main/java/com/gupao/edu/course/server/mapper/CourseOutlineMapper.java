package com.gupao.edu.course.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.course.client.entity.CourseOutline;
import com.gupao.edu.course.client.resp.CourseOutlineTreeResp;
import com.gupao.edu.course.client.resp.OutLineResp;
import com.gupao.edu.course.client.resp.OutlineVedioVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程大纲表（关联视频资源） Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseOutlineMapper extends BaseMapper<CourseOutline> {

    /**
     * 获取 大纲
     *  节的基本信息
     * @return
     */
    OutlineVedioVO getOutlineSimpleInfo(@Param("outlineId") Integer outlineId);

    /**
     * 获取 顶级 大纲
     * @param courseId
     * @return
     */
    List<CourseOutlineTreeResp> listCourseOutlineTopLevel(@Param("courseId") Integer courseId);

    /**
     * 查询 大纲 的 节视频播放进度信息
     * @param parentOutlineId 父 大纲id
     * @return
     */
    List<CourseOutlineTreeResp> listCourseOutlineAndVedioInfo(@Param("userUniqueCode") String userUniqueCode,
            @Param("parentOutlineId") Integer parentOutlineId);
    /**
     * 查询 大纲 的所有目录
     * @param courseId
     * @return
     */
    List<OutLineResp> listCourseOutLineLevel(Integer courseId);
}
