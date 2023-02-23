package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.facade.member.UserAuthFacade;
import com.gupao.edu.account.client.resp.UserAttentionVO;
import com.gupao.edu.account.client.resp.UserSimpleInfoDTO;
import com.gupao.edu.course.client.dto.CourseHomeworkDTO;
import com.gupao.edu.course.client.dto.HomeworkDetailDTO;
import com.gupao.edu.course.client.dto.MyHomeworkDTO;
import com.gupao.edu.course.client.entity.CourseHomework;
import com.gupao.edu.course.client.entity.CourseHomeworkReply;
import com.gupao.edu.course.client.entity.CourseHomeworkReplyComment;
import com.gupao.edu.course.client.entity.CourseHomeworkReplyPraise;
import com.gupao.edu.course.client.req.center.*;
import com.gupao.edu.course.client.resp.center.*;
import com.gupao.edu.course.server.mapper.CourseHomeworkMapper;
import com.gupao.edu.course.server.service.CourseHomeworkReplyCommentService;
import com.gupao.edu.course.server.service.CourseHomeworkReplyPraiseService;
import com.gupao.edu.course.server.service.CourseHomeworkReplyService;
import com.gupao.edu.course.server.service.CourseHomeworkService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 作业问题表 服务实现类
 * </p>
 *
 * @author wuzhenping
 * @since 2020-04-12
 */
@Service
public class CourseHomeworkServiceImpl extends ServiceImpl<CourseHomeworkMapper, CourseHomework> implements CourseHomeworkService {

    @Autowired
    private CourseHomeworkReplyService courseHomeworkReplyService;
    @Autowired
    private CourseHomeworkReplyCommentService courseHomeworkReplyCommentService;
    @Autowired
    private CourseHomeworkReplyPraiseService courseHomeworkReplyPraiseService;
    @Autowired
    private UserAuthFacade userAuthFacade;

    /**
     * 根据大纲ID获取大纲作业题目列表
     * @param outlineId 大纲ID
     * @return 大纲作业题目列表
     */
    @Override
    public List<CourseHomeworkResp> outLineHomeworkList(Integer outlineId){
        List<CourseHomeworkResp> list = new ArrayList<>();
        //1.获取当前章节下的作业列表
        List<CourseHomeworkDTO> courseHomeworkList = baseMapper.selectCourseHomeworkListByOutlineId(outlineId);
        for (CourseHomeworkDTO dto : courseHomeworkList) {
            CourseHomeworkResp resp = new CourseHomeworkResp();
            BeanUtils.copyProperties(dto,resp);
            //2.显示提交人数与优秀作业数
            Integer homeworkId = dto.getHomeworkId();
            resp.setCommitNum(courseHomeworkReplyService.countCommitNum(homeworkId));
            resp.setGoodNum(courseHomeworkReplyService.countGoodNum(homeworkId));
            list.add(resp);
        }
        return list;
    }

    /**
     * 查询大纲作业题目列表
     * @param req 查询大纲作业题目列表分页参数实体
     * @return 大纲作业题目列表
     */
    @Override
    public IPage<MyHomeworkResp> myHomework(MyHomeworkPageReq req){
        Page<MyHomeworkResp> page = new Page<>();
        if (null == req) {
            return page;
        }
        page.setSize(req.getPageSize());
        page.setCurrent(req.getPageNum());

        String userUniqueCode = req.getUserUniqueCode();
        if (null == userUniqueCode){
            return page;
        }

        Page<MyHomeworkDTO> dtoPage = new Page<>(req.getPageNum(),req.getPageSize());

        List<MyHomeworkResp> list = new ArrayList<>();
        //1.获取我的的作业列表
        List<MyHomeworkDTO> courseHomeworkList = courseHomeworkReplyService.selectMyHomeworkByUserUniqueCode(dtoPage,userUniqueCode);
        for (MyHomeworkDTO dto : courseHomeworkList) {
            MyHomeworkResp resp = new MyHomeworkResp();
            BeanUtils.copyProperties(dto,resp);
            //2.显示评论数
            resp.setCommentNum(courseHomeworkReplyCommentService.countCommentNumByReplyId(dto.getId()));
            list.add(resp);
        }
        dtoPage.setRecords(courseHomeworkList);
        BeanUtils.copyProperties(dtoPage,page);
        return page.setRecords(list);
    }

    /**
     * 获取作业详情及其作业回答列表
     * @param req 作业详情及其作业回答列表请求实体
     * @return 作业详情及其作业回答列表
     * @throws Exception 抛出异常
     */
    @Override
    public HomeworkDetailResp homeworkDetails(HomeworkDetailQueryReq req) throws Exception{
        if (null == req) {
            throw new Exception("查看作业详情时，请求参数不能为空");
        }
        Integer homeworkId = req.getHomeworkId();
        if (null == homeworkId) {
            throw new Exception("查看作业详情时，作业ID不能为空");
        }
        String userUniqueCode = req.getUserUniqueCode();
        if (StringUtils.isEmpty(userUniqueCode)) {
            throw new Exception("用户唯一编码不能为空");
        }

        HomeworkDetailResp resp = new HomeworkDetailResp();

        // 获取作业详情信息
        HomeworkDetailDTO dto =  baseMapper.selectHomeworkDetailById(homeworkId);
        if (null == dto) {
            throw new Exception("没有该作业信息！");
        }
        BeanUtils.copyProperties(dto,resp);

        // 判断当前用户是否提交该作业
        resp.setCommit(courseHomeworkReplyService.whetherReplyHomework(homeworkId,userUniqueCode));

        // 作业回复列表
        resp.setHomeworkReplyRespIPage(courseHomeworkReplyService.findHomeworkReplyRespIPage(req));
        return resp;
    }

    /**
     * 提交/修改我的作业
     * @param req 提交/修改我的作业请求参数实体
     * @return true or false
     * @throws Exception 抛出异常
     */
    @Override
    @Transactional
    public Boolean updateMyHomework(UpdateMyHomeworkReq req) throws Exception{
        if (null == req) {
            throw new Exception("提交作业信息实体不能为空");
        }
        Integer homeworkId = req.getHomeworkId();
        if (null == homeworkId) {
            throw new Exception("作业ID不能为空");
        }
        String userUniqueCode = req.getUserUniqueCode();
        if (StringUtils.isEmpty(userUniqueCode)) {
            throw new Exception("用户唯一编码不能为空");
        }
        String content = req.getHomeworkContent();
        if (StringUtils.isEmpty(content)) {
            throw new Exception("答作业内容不能为空");
        }

        CourseHomeworkReply reply = courseHomeworkReplyService.selectReplyByUserAndHomework(homeworkId,userUniqueCode);
        if (null == reply) {
            reply = new CourseHomeworkReply();
        }

        reply.setCourseHomeworkId(homeworkId);
        reply.setUserUniqueCode(userUniqueCode);
        reply.setHomeworkReplyContent(content);
        return courseHomeworkReplyService.saveOrUpdate(reply);
    }

    /**
     * 评论作业
     * @param req 评论作业实体
     * @return true or false
     * @throws Exception 抛出异常
     */
    @Override
    @Transactional
    public Boolean homeworkComment(HomeworkCommentReq req) throws Exception{
        if (null == req) {
            throw new Exception("评论作业实体不能为空");
        }
        Integer replyId = req.getCourseHomeworkReplyId();
        if (null == replyId) {
            throw new Exception("作业回复ID不能为空");
        }
        String userUniqueCode = req.getUserUniqueCode();
        if (StringUtils.isEmpty(userUniqueCode)) {
            throw new Exception("用户唯一编码不能为空");
        }
        String content = req.getContent();
        if (StringUtils.isEmpty(content)) {
            throw new Exception("答作业内容不能为空");
        }

        CourseHomeworkReplyComment comment = new CourseHomeworkReplyComment();
        BeanUtils.copyProperties(req,comment);
        return courseHomeworkReplyCommentService.save(comment);
    }

    /**
     * 作业评论的点赞与踩
     * @param req 作业评论的点赞与踩实体
     * @return true or false
     * @throws Exception 抛出异常
     */
    @Override
    @Transactional
    public Boolean homeworkReplyPraise(HomeworkReplyPraiseReq req) throws Exception{
        if (null == req) {
            throw new Exception("评论点赞实体不能为空");
        }
        Integer replyId = req.getCourseHomeworkReplyId();
        if (null == replyId) {
            throw new Exception("作业回复ID不能为空");
        }
        String userUniqueCode = req.getUserUniqueCode();
        if (StringUtils.isEmpty(userUniqueCode)) {
            throw new Exception("用户唯一编码不能为空");
        }
        Integer type = req.getType();
        if (StringUtils.isEmpty(type)) {
            throw new Exception("赞或踩不能为空");
        }

        CourseHomeworkReplyPraise praise = courseHomeworkReplyPraiseService.selectPraiseByUserAndReply(userUniqueCode,replyId);
        if (null == praise) {
            praise = new CourseHomeworkReplyPraise();
        }else {
            if (type.equals(praise.getType())) {
                if (0 == type) {
                    throw new Exception("不能重复点赞");
                }
                if (1 == type) {
                    throw new Exception("不能重复点踩");
                }
            }
        }
        BeanUtils.copyProperties(req,praise);
        return courseHomeworkReplyPraiseService.saveOrUpdate(praise);
    }

    /**
     * 作业评分
     * @param req 作业评分请求参数
     * @return true or false
     * @throws Exception 抛出异常
     */
    @Override
    @Transactional
    public Boolean scoreHomework(ScoreHomeworkReq req) throws Exception{
        if (null == req) {
            throw new Exception("评论点赞实体不能为空");
        }
        Integer replyId = req.getCourseHomeworkReplyId();
        if (null == replyId) {
            throw new Exception("作业回复ID不能为空");
        }
        Integer lecturerId = req.getLecturerId();
        if (null == lecturerId){
            throw new Exception("讲师ID不能为空");
        }
        CourseHomeworkReply reply = courseHomeworkReplyService.getById(replyId);
        if (null == reply){
            throw new Exception("没找到该作业信息");
        }
        BeanUtils.copyProperties(req,reply);
        return courseHomeworkReplyService.saveOrUpdate(reply);
    }
}
