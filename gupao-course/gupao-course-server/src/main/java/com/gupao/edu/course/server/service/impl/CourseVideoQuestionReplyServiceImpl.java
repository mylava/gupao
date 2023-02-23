package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.facade.member.UserAuthFacade;
import com.gupao.edu.account.client.resp.AccountHistoryVO;
import com.gupao.edu.account.client.resp.UserSimpleInfoDTO;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.course.client.entity.CourseVideoQuestion;
import com.gupao.edu.course.client.entity.CourseVideoQuestionReply;
import com.gupao.edu.course.client.entity.CourseVideoQuestionReplyComment;
import com.gupao.edu.course.client.req.center.CourseVedioReplyReq;
import com.gupao.edu.course.client.req.center.QuestionReplyQueryReq;
import com.gupao.edu.course.client.resp.center.CourseVedioReplyComment;
import com.gupao.edu.course.client.resp.center.CourseVedioReplyResp;
import com.gupao.edu.course.server.exception.CourseException;
import com.gupao.edu.course.server.mapper.CourseVideoQuestionReplyMapper;
import com.gupao.edu.course.server.service.CourseAskQuestionService;
import com.gupao.edu.course.server.service.CourseVideoQuestionReplyCommentService;
import com.gupao.edu.course.server.service.CourseVideoQuestionReplyService;
import com.gupao.edu.course.server.service.CourseVideoQuestionService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 视频答疑回复表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class CourseVideoQuestionReplyServiceImpl extends ServiceImpl<CourseVideoQuestionReplyMapper, CourseVideoQuestionReply> implements CourseVideoQuestionReplyService {

    @Autowired
    private CourseVideoQuestionService courseVideoQuestionService;
    @Autowired
    private UserAuthFacade userAuthFacade;
    @Autowired
    private CourseVideoQuestionReplyCommentService courseVideoQuestionReplyCommentService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addReply(CourseVedioReplyReq req) {
        //获取问题
        CourseVideoQuestion question = courseVideoQuestionService.getById(req.getCourseVideoQuestionId());
        if(question == null){
            throw new CourseException(CodeMessage.CORS_VIDEO_ASK_NOT_FOUND);
        }
        //根据用户编码获取用户是否存在
        UserSimpleInfoDTO user = userAuthFacade.getUserSimpleInfo(req.getUserUniqueCode());
        if(user == null || user.getUserUniqueCode() == null){
            throw new CourseException(CodeMessage.USER_NOT_FOUND);
        }
        //更新问题表,回答数加1
        question.setReplyCount(question.getReplyCount()+1);
        courseVideoQuestionService.updateById(question);
        //新增回答
        CourseVideoQuestionReply reply = new CourseVideoQuestionReply();
        BeanUtils.copyProperties(req,reply);
        reply.setQuestionTitle(question.getTitle());
        reply.setCreateTime(LocalDateTime.now());
        this.baseMapper.insert(reply);
    }

    @Override
    public List<CourseVideoQuestionReply> findReplyListByQuestionId(Integer questionId) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("course_video_question_id",questionId);
        return this.baseMapper.selectList(qw);
    }

    @Override
    public IPage<CourseVedioReplyResp> findReplyRespListByQuestionId(Integer questionId,
        QuestionReplyQueryReq req) {
        IPage<CourseVedioReplyResp> page = new Page(req.getPageNum(),req.getPageSize());
        //查询回复信息
        List<CourseVedioReplyResp> replyList = baseMapper.findReplyRespListByQuestionId(page,questionId);
        replyList.forEach(reply->{
            //根据用户编码获取用户信息
            UserSimpleInfoDTO user = userAuthFacade.getUserSimpleInfo(reply.getUserUniqueCode());
            if(user != null){
                reply.setUserName(user.getNickName());
            }
            //查询评论
            List<CourseVedioReplyComment> comments = courseVideoQuestionReplyCommentService.findCommentRespByReplyId(reply.getId());
            reply.setCourseVedioReplyCommentList(comments);
        });
        page.setRecords(replyList);
        return page;
    }
}
