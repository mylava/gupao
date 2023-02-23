package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.dto.MyHomeworkDTO;
import com.gupao.edu.course.client.entity.CourseHomeworkReply;
import com.gupao.edu.course.client.req.center.HomeworkDetailQueryReq;
import com.gupao.edu.course.client.resp.center.HomeworkReplyResp;

import java.util.List;

/**
 * <p>
 * 学员答作业表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseHomeworkReplyService extends IService<CourseHomeworkReply> {

    /**
     * 根据课程作业ID获取提交作业数
     * @param courseHomeworkId 课程作业ID
     * @return 优秀作业数
     */
    Integer countCommitNum(Integer courseHomeworkId);

    /**
     * 根据课程作业ID获取优秀作业数
     * @param courseHomeworkId 课程作业ID
     * @return 优秀作业数
     */
    Integer countGoodNum(Integer courseHomeworkId);

    /**
     * 根据用户唯一编码获取我的作业列表
     * @param userUniqueCode 用户唯一编码
     * @return 我的作业列表
     */
    List<MyHomeworkDTO> selectMyHomeworkByUserUniqueCode(Page<MyHomeworkDTO> page, String userUniqueCode);

    /**
     * 判断某用户是否提交了某作业
     * @param homeworkId 作业ID
     * @param userUniqueCode 用户唯一编码
     * @return true or false
     */
    boolean whetherReplyHomework(Integer homeworkId,String userUniqueCode);

    /**
     * 根据作业ID获取该作业学员答作业分页列表 - 按时间倒序排列
     * @param page 分页信息
     * @param homeworkId 作业ID
     * @return 学员答作业列表
     */
    List<CourseHomeworkReply> selectReplyOrderNewestByHomeworkId(IPage<CourseHomeworkReply> page, Integer homeworkId);

    /**
     * 根据作业ID获取该作业学员答作业分页列表 - 按分数倒序排列
     * @param page 分页信息
     * @param homeworkId 作业ID
     * @return 学员答作业列表
     */
    List<CourseHomeworkReply> selectReplyOrderScoreByHomeworkId(IPage<CourseHomeworkReply> page,Integer homeworkId);

    /**
     * 根据作业ID和用户唯一编码获取用户作业回复信息
     * @param homeworkId 作业ID
     * @param userUniqueCode 用户唯一编码
     * @return 用户作业回复信息
     */
    CourseHomeworkReply selectReplyByUserAndHomework(Integer homeworkId,String userUniqueCode);

    /**
     * 根据作业回复分页参数获取其分页列表信息
     * @param req 作业回复分页参数
     * @return 作业回复分页列表信息
     * @throws Exception 抛出异常
     */
    IPage<HomeworkReplyResp> findHomeworkReplyRespIPage(HomeworkDetailQueryReq req) throws Exception;

    /**
     * 根据条件判断按最新排序还是按分数排序获取作业回复列表
     * @param desc 1：按最新排序，default：按分数排序
     * @return 作业回复列表
     */
    List<CourseHomeworkReply> findCourseHomeworkReplyList(Page<CourseHomeworkReply> courseHomeworkReplyPage,Integer homeworkId,Integer desc);

}
