package com.gupao.edu.course.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.course.client.dto.MyHomeworkDTO;
import com.gupao.edu.course.client.entity.CourseHomeworkReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 学员答作业表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseHomeworkReplyMapper extends BaseMapper<CourseHomeworkReply> {

    /**
     * 根据课程作业ID获取提交作业数
     * @param courseHomeworkId 课程作业ID
     * @return 优秀作业数
     */
    int countCommitNum(@Param("courseHomeworkId") Integer courseHomeworkId);

    /**
     * 根据课程作业ID获取优秀作业数
     * @param courseHomeworkId 课程作业ID
     * @return 优秀作业数
     */
    int countGoodNum(@Param("courseHomeworkId") Integer courseHomeworkId);

    /**
     * 获取我的作业列表
     * @param page 翻页对象
     * @param userUniqueCode 用户唯一编号
     * @return 获取我的作业列表
     */
    List<MyHomeworkDTO> selectMyHomeworkByUserUniqueCode(Page<MyHomeworkDTO> page, @Param("userUniqueCode") String userUniqueCode);

    /**
     * 根据作业ID获取该作业学员答作业分页列表 - 按时间倒序排列
     * @param page 分页信息
     * @param courseHomeworkId 作业ID
     * @return 学员答作业列表
     */
    List<CourseHomeworkReply> selectReplyOrderNewestByHomeworkId(IPage<CourseHomeworkReply> page, @Param("courseHomeworkId") Integer courseHomeworkId);

    /**
     * 根据作业ID获取该作业学员答作业分页列表 - 按分数倒序排列
     * @param page 分页信息
     * @param courseHomeworkId 作业ID
     * @return 学员答作业列表
     */
    List<CourseHomeworkReply> selectReplyOrderScoreByHomeworkId(IPage<CourseHomeworkReply> page, @Param("courseHomeworkId")Integer courseHomeworkId);
}
