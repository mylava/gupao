package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.facade.member.UserAuthFacade;
import com.gupao.edu.account.client.resp.UserSimpleInfoDTO;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.course.client.entity.CourseVideoQuestionReply;
import com.gupao.edu.course.client.entity.CourseVideoQuestionReplyComment;
import com.gupao.edu.course.client.req.center.CourseVedioReplyCommentReq;
import com.gupao.edu.course.client.resp.center.CourseVedioReplyComment;
import com.gupao.edu.course.server.exception.CourseException;
import com.gupao.edu.course.server.mapper.CourseVideoQuestionReplyCommentMapper;
import com.gupao.edu.course.server.service.CourseVideoQuestionReplyCommentService;
import com.gupao.edu.course.server.service.CourseVideoQuestionReplyService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 视频答疑回复评论表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class CourseVideoQuestionReplyCommentServiceImpl extends ServiceImpl<CourseVideoQuestionReplyCommentMapper, CourseVideoQuestionReplyComment> implements CourseVideoQuestionReplyCommentService {

    @Autowired
    private CourseVideoQuestionReplyService courseVideoQuestionReplyService;
    @Autowired
    private UserAuthFacade userAuthFacade;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addComment(CourseVedioReplyCommentReq req) {
        //查询回复信息
        CourseVideoQuestionReply reply = courseVideoQuestionReplyService.getById(req.getCourseVideoQuestionReplyId());
        if(reply == null){
            throw new CourseException(CodeMessage.CORS_VIDEO_ASK_REPLY_NOT_FOUND);
        }
        //根据父id查询回复
        if(req.getParentId() != null && req.getParentId() != 0){
            CourseVideoQuestionReplyComment c = this.baseMapper.selectById(req.getParentId());
            if(c == null ){
                throw new CourseException(CodeMessage.CORS_VIDEO_ASK_REPLY_COMMENT_NOT_FOUND);
            }
        }
        //根据用户编码获取用户是否存在
        UserSimpleInfoDTO user = userAuthFacade.getUserSimpleInfo(req.getUserUniqueCode());
        if(user == null || user.getUserUniqueCode() == null){
            throw new CourseException(CodeMessage.USER_NOT_FOUND);
        }
        CourseVideoQuestionReplyComment comment = new CourseVideoQuestionReplyComment();
        BeanUtils.copyProperties(req,comment);
        comment.setCreateTime(LocalDateTime.now());
        this.baseMapper.insert(comment);
    }

    @Override
    public List<CourseVedioReplyComment> findCommentRespByReplyId(Integer replyId) {
        List<CourseVedioReplyComment> list = new ArrayList<>();
        List<CourseVideoQuestionReplyComment> comments = findCommentsByReplyId(replyId);
        comments.forEach(comment->{
            CourseVedioReplyComment CommentResp = new CourseVedioReplyComment();
            BeanUtils.copyProperties(comment,CommentResp);
            //根据用户编码获取用户信息
            UserSimpleInfoDTO user = userAuthFacade.getUserSimpleInfo(comment.getUserUniqueCode());
            if(user != null){
                CommentResp.setUserName(user.getNickName());
            }
            List<CourseVedioReplyComment> child = findCommentRespByParentId(comment.getId());
            CommentResp.setCommentChildList(child);
            list.add(CommentResp);
        });
        return list;
    }

    public List<CourseVideoQuestionReplyComment> findCommentsByReplyId(Integer replyId) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("course_video_question_reply_id",replyId);
        qw.eq("parent_id",0);
        return this.baseMapper.selectList(qw);
    }

    @Override
    public List<CourseVedioReplyComment> findCommentRespByParentId(Integer parentId) {
        List<CourseVedioReplyComment> list = new ArrayList<>();
        List<CourseVideoQuestionReplyComment> comments = findCommentsByParentId(parentId);
        comments.forEach(comment->{
            CourseVedioReplyComment CommentResp = new CourseVedioReplyComment();
            BeanUtils.copyProperties(comment,CommentResp);
            //根据用户编码获取用户信息
            UserSimpleInfoDTO user = userAuthFacade.getUserSimpleInfo(comment.getUserUniqueCode());
            if(user != null){
                CommentResp.setUserName(user.getNickName());
            }
            CommentResp.setCommentChildList(findCommentRespByParentId(comment.getId()));
            list.add(CommentResp);
        });
        return list;
    }

    @Override
    public List<CourseVideoQuestionReplyComment> findCommentsByParentId(Integer parentId) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("parent_id",parentId);
        return this.baseMapper.selectList(qw);
    }
}
