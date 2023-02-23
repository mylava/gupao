package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.facade.member.UserAuthFacade;
import com.gupao.edu.account.client.resp.UserSimpleInfoDTO;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.course.client.entity.CourseVideoQuestionReply;
import com.gupao.edu.course.client.entity.CourseVideoQuestionReplyPraise;
import com.gupao.edu.course.client.req.center.QuestionReplyPraiseReq;
import com.gupao.edu.course.server.exception.CourseException;
import com.gupao.edu.course.server.mapper.CourseVideoQuestionReplyPraiseMapper;
import com.gupao.edu.course.server.service.CourseVideoQuestionReplyPraiseService;
import com.gupao.edu.course.server.service.CourseVideoQuestionReplyService;
import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 答疑点赞表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class CourseVideoQuestionReplyPraiseServiceImpl extends ServiceImpl<CourseVideoQuestionReplyPraiseMapper, CourseVideoQuestionReplyPraise> implements CourseVideoQuestionReplyPraiseService {

    @Autowired
    private UserAuthFacade userAuthFacade;
    @Autowired
    private CourseVideoQuestionReplyService courseVideoQuestionReplyService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void questionReplyPraise(QuestionReplyPraiseReq req) {
        //根据用户编码获取用户是否存在
        UserSimpleInfoDTO user = userAuthFacade.getUserSimpleInfo(req.getUserUniqueCode());
        if(user == null || user.getUserUniqueCode() == null){
            throw new CourseException(CodeMessage.USER_NOT_FOUND);
        }
        //查询回复
        CourseVideoQuestionReply reply = courseVideoQuestionReplyService.getById(req.getCourseVideoQuestionReplyId());
        if(reply == null){
            throw new CourseException(CodeMessage.CORS_VIDEO_ASK_REPLY_NOT_FOUND);
        }
        //查询当前用户的操作记录
        CourseVideoQuestionReplyPraise praise = getPraiseInfoByReplyId(req.getCourseVideoQuestionReplyId(),req.getUserUniqueCode());
        //点赞或踩
        if(req.getIsCancel() == 0){
            //如果已经点赞或踩,则不能操作
            if(praise != null){
                throw new CourseException(CodeMessage.CORS_VIDEO_ASK_REPLY_OPERATED);
            }
            //否则新增点赞或踩记录
            praise = new CourseVideoQuestionReplyPraise();
            BeanUtils.copyProperties(req,praise);
            praise.setCreateTime(LocalDateTime.now());
            this.baseMapper.insert(praise);
            //点赞需要将回复的点赞数加1,踩需要将反对数量加1
            if(req.getType() == 0){
                reply.setPraiseCount(reply.getPraiseCount()+1);
            }else{
                reply.setNegativeCount(reply.getNegativeCount()+1);
            }
            courseVideoQuestionReplyService.updateById(reply);
        }else if(req.getIsCancel() == 1){//取消点赞或踩
            //没有点赞或踩记录,不能取消操作
            if(praise == null){
                throw new CourseException(CodeMessage.CORS_VIDEO_ASK_REPLY_PRAISE_NOT_FOUND);
            }
            //删除点赞或踩记录
            this.baseMapper.deleteById(praise);
            //取消点赞需要将回复的点赞数减1,取消踩需要将反对数量减1
            if(req.getType() == 0){
                reply.setPraiseCount(reply.getPraiseCount()-1);
            }else{
                reply.setNegativeCount(reply.getNegativeCount()-1);
            }
            courseVideoQuestionReplyService.updateById(reply);
        }else{
            throw new CourseException(CodeMessage.CORS_VIDEO_ASK_REPLY_CANCEL_TYPE_FAIL);
        }
    }

    public CourseVideoQuestionReplyPraise getPraiseInfoByReplyId(Integer replyId,String userUniqueCode){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_video_question_reply_id",replyId);
        queryWrapper.eq("user_unique_code",userUniqueCode);
        return this.baseMapper.selectOne(queryWrapper);
    }
}
