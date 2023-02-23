package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.CourseHomework;
import com.gupao.edu.course.client.req.center.*;
import com.gupao.edu.course.client.resp.center.CourseHomeworkResp;
import com.gupao.edu.course.client.resp.center.HomeworkDetailResp;
import com.gupao.edu.course.client.resp.center.MyHomeworkResp;

import java.util.List;

/**
 * <p>
 * 作业问题表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseHomeworkService extends IService<CourseHomework> {

    /**
     * 根据大纲ID获取大纲作业题目列表
     * @param outlineId 大纲ID
     * @return 大纲作业题目列表
     */
     List<CourseHomeworkResp> outLineHomeworkList(Integer outlineId);

    /**
     * 查询大纲作业题目列表
     * @param req 查询大纲作业题目列表分页参数实体
     * @return 大纲作业题目列表
     */
    IPage<MyHomeworkResp> myHomework(MyHomeworkPageReq req);

    /**
     * 获取作业详情及其作业回答列表
     * @param req 作业详情及其作业回答列表请求实体
     * @return 作业详情及其作业回答列表
     * @throws Exception 抛出异常
     */
    HomeworkDetailResp homeworkDetails(HomeworkDetailQueryReq req) throws Exception;

    /**
     * 提交/修改我的作业
     * @param req 提交/修改我的作业请求参数实体
     * @return true or false
     * @throws Exception 抛出异常
     */
    Boolean updateMyHomework(UpdateMyHomeworkReq req) throws Exception;

    /**
     * 评论作业
     * @param req 评论作业实体
     * @return true or false
     * @throws Exception 抛出异常
     */
    Boolean homeworkComment(HomeworkCommentReq req) throws Exception;

    /**
     * 作业评论的点赞与踩
     * @param req 作业评论的点赞与踩实体
     * @return true or false
     * @throws Exception 抛出异常
     */
    Boolean homeworkReplyPraise(HomeworkReplyPraiseReq req) throws Exception;

    /**
     * 作业评分
     * @param req 作业评分请求参数
     * @return true or false
     * @throws Exception 抛出异常
     */
    Boolean scoreHomework(ScoreHomeworkReq req) throws Exception;

}
