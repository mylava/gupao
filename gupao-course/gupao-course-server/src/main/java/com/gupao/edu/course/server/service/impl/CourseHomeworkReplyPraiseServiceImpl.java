package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.course.client.entity.CourseHomeworkReplyPraise;
import com.gupao.edu.course.server.mapper.CourseHomeworkReplyPraiseMapper;
import com.gupao.edu.course.server.service.CourseHomeworkReplyPraiseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学员作业评论点赞表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class CourseHomeworkReplyPraiseServiceImpl extends ServiceImpl<CourseHomeworkReplyPraiseMapper, CourseHomeworkReplyPraise> implements CourseHomeworkReplyPraiseService {

    /**
     * 获取用户作业回复点赞信息
     * @param userUniqueCode 用户用户唯一编码
     * @param replyId 作业回复ID
     * @return 作业回复点赞信息
     */
    @Override
    public CourseHomeworkReplyPraise selectPraiseByUserAndReply(String userUniqueCode,Integer replyId){
        List<CourseHomeworkReplyPraise> praiseList = baseMapper.selectList(new QueryWrapper<CourseHomeworkReplyPraise>()
            .eq("course_homework_reply_id",replyId)
            .eq("user_unique_code",userUniqueCode));
        if (null == praiseList || praiseList.size() == 0) {
            return null;
        }
        return praiseList.get(0);
    }
}
