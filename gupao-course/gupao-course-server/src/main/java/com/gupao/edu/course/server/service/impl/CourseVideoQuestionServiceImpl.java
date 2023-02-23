package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.facade.member.UserAuthFacade;
import com.gupao.edu.account.client.resp.UserSimpleInfoDTO;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.course.client.entity.CourseVideo;
import com.gupao.edu.course.client.entity.CourseVideoQuestion;
import com.gupao.edu.course.client.entity.CourseVideoQuestionReply;
import com.gupao.edu.course.client.req.center.CourseVedioQuestionReq;
import com.gupao.edu.course.client.req.center.QuestionReplyQueryReq;
import com.gupao.edu.course.client.req.center.UserAskReq;
import com.gupao.edu.course.client.resp.center.AskAnsweResp;
import com.gupao.edu.course.client.resp.center.CourseQuestionResp;
import com.gupao.edu.course.client.resp.center.CourseVedioQuestionResp;
import com.gupao.edu.course.client.resp.center.CourseVedioReplyResp;
import com.gupao.edu.course.server.exception.CourseException;
import com.gupao.edu.course.server.mapper.CourseVideoQuestionMapper;
import com.gupao.edu.course.server.service.CourseVideoQuestionReplyService;
import com.gupao.edu.course.server.service.CourseVideoQuestionService;
import com.gupao.edu.course.server.service.CourseVideoService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 视频答疑问题表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class CourseVideoQuestionServiceImpl extends ServiceImpl<CourseVideoQuestionMapper, CourseVideoQuestion> implements CourseVideoQuestionService {

    @Autowired
    private CourseVideoService courseVideoService;
    @Autowired
    private UserAuthFacade userAuthFacade;
    @Autowired
    private CourseVideoQuestionReplyService courseVideoQuestionReplyService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCourseAsk(CourseVedioQuestionReq req) {
        //根据视频id获取视频是否存在
        CourseVideo video = courseVideoService.getById(req.getVideoId());
        if(video == null){
            throw new CourseException(CodeMessage.CORS_VIDEO_NOT_FOUND);
        }
        //根据用户编码获取用户是否存在
        UserSimpleInfoDTO user = userAuthFacade.getUserSimpleInfo(req.getUserUniqueCode());
        if(user == null || user.getUserUniqueCode() == null){
            throw new CourseException(CodeMessage.USER_NOT_FOUND);
        }
        CourseVideoQuestion ask = new CourseVideoQuestion();
        BeanUtils.copyProperties(req,ask);
        ask.setCreateTime(LocalDateTime.now());
        this.baseMapper.insert(ask);
    }

    @Override
    public AskAnsweResp findQuestionInfoById(QuestionReplyQueryReq req) {
        AskAnsweResp resp = new AskAnsweResp();
        //查询提问信息
        CourseVideoQuestion question = this.baseMapper.selectById(req.getQuestionId());
        if(question == null){
            return null;
        }
        BeanUtils.copyProperties(question,resp);
        //根据用户编码获取用户信息
        UserSimpleInfoDTO user = userAuthFacade.getUserSimpleInfo(question.getUserUniqueCode());
        if(user != null){
            resp.setUserName(user.getNickName());
        }
        //查询回答
        IPage<CourseVedioReplyResp> replyResps = courseVideoQuestionReplyService.findReplyRespListByQuestionId(question.getId(),req);
        resp.setReplyPage(replyResps);
        return resp;
    }

    @Override
    public IPage<CourseVedioQuestionResp> findQuestionPage(UserAskReq userAskReq) {
        IPage<CourseQuestionResp> questionRespPage = new Page(userAskReq.getPageNum(),userAskReq.getPageSize());
        List<CourseQuestionResp> questionRespList = null;
        if(userAskReq.getType() == 0){//全部
            questionRespList = this.baseMapper.findQuestionPage(questionRespPage,userAskReq.getVideoId(),null);
        }else if(userAskReq.getType() == 1){//我的
            questionRespList = this.baseMapper.findQuestionPage(questionRespPage,userAskReq.getVideoId(),userAskReq.getUserUniqueCode());
        }else{
            return null;
        }
        if(questionRespList == null || questionRespList.size() == 0){
            return null;
        }
        //获取视频播放时间并去重
        Set<Integer> points = new LinkedHashSet<>();
        questionRespList.forEach(question->points.add(question.getPoint()));
        //根据播放时间分组
        Map<Integer, List<CourseQuestionResp>> questionGroupMap = questionRespList.stream()
            .collect(Collectors.groupingBy(CourseQuestionResp::getPoint));
        List<CourseVedioQuestionResp> resps = new ArrayList<>();
        points.forEach(point->{
            CourseVedioQuestionResp resp = new CourseVedioQuestionResp();
            resp.setPoint(point);
            resp.setRespList(questionGroupMap.get(point));
            resps.add(resp);
        });
        IPage<CourseVedioQuestionResp> respPage = new Page(userAskReq.getPageNum(),userAskReq.getPageSize());
        BeanUtils.copyProperties(questionRespPage,respPage);
        respPage.setRecords(resps);
        return respPage;
    }
}
