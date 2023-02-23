package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.facade.member.UserAuthFacade;
import com.gupao.edu.account.client.resp.UserSimpleInfoDTO;
import com.gupao.edu.course.client.entity.CourseHomeworkReplyComment;
import com.gupao.edu.course.client.resp.center.HomeworkCommentResp;
import com.gupao.edu.course.server.mapper.CourseHomeworkReplyCommentMapper;
import com.gupao.edu.course.server.service.CourseHomeworkReplyCommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 学员作业评论表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class CourseHomeworkReplyCommentServiceImpl extends ServiceImpl<CourseHomeworkReplyCommentMapper, CourseHomeworkReplyComment> implements CourseHomeworkReplyCommentService {

    @Autowired
    private UserAuthFacade userAuthFacade;

    /**
     * 根据学员答作业表ID统计其评论数
     * @param replyId 学员答作业表ID
     * @return 评论数
     */
    @Override
    public Integer countCommentNumByReplyId(Integer replyId){
        return baseMapper.selectCount(new QueryWrapper<CourseHomeworkReplyComment>().eq("course_homework_reply_id",replyId));
    }

    /**
     * 根据学员答作业ID获取其答作业评论列表
     * @param replyId 学员答作业ID
     * @return 答作业评论列表
     */
    @Override
    public List<CourseHomeworkReplyComment> selectCommentByReplyId(Integer replyId){
        return baseMapper.selectList(new QueryWrapper<CourseHomeworkReplyComment>()
            .eq("course_homework_reply_id",replyId));
    }

    /**
     * 根据学员答作业获取其答作业评论的评论列表
     * @param parentId 学员答作业下评论评论的ID
     * @return 答作业评论的评论列表
     */
    @Override
    public List<CourseHomeworkReplyComment> selectCommentByParentId(Integer parentId){
        return baseMapper.selectList(new QueryWrapper<CourseHomeworkReplyComment>()
            .eq("parent_id",parentId));
    }

    /**
     * 学员评论作业信息列表
     * @param replyId 答作业ID
     * @return 学员评论作业信息列表
     */
    @Override
    public List<HomeworkCommentResp> fingHomeworkCommentRespList(Integer replyId){
        List<HomeworkCommentResp> homeworkCommentRespList = new ArrayList<>();
        List<CourseHomeworkReplyComment> commentList = this.selectCommentByReplyId(replyId);

        // 遍历作业回复评论
        for (CourseHomeworkReplyComment comment : commentList) {
            HomeworkCommentResp commentResp = new HomeworkCommentResp();
            BeanUtils.copyProperties(comment,commentResp);
            commentResp.setCommentContent(comment.getContent());

            // 调用远程接口根据用户唯一编码获取用信息
            UserSimpleInfoDTO replyUserSimpleInfoDTO = userAuthFacade.getUserSimpleInfo(comment.getUserUniqueCode());
            if (null == replyUserSimpleInfoDTO) replyUserSimpleInfoDTO = new UserSimpleInfoDTO();
            BeanUtils.copyProperties(replyUserSimpleInfoDTO,commentResp);

            // 获取评论评论的信息
            commentResp.setCommentChildList(this.findCommentChildList(comment.getParentId()));
            // 插入学员评论作业信息
            homeworkCommentRespList.add(commentResp);
        }
        return homeworkCommentRespList;
    }

    /**
     * 获取评论评论的信息列表
     * @param parentId 评论评论的ID
     * @return 评论评论的信息列表
     */
    @Override
    public List<HomeworkCommentResp> findCommentChildList(Integer parentId){
        List<HomeworkCommentResp> commentChildList = new ArrayList<>();
        List<CourseHomeworkReplyComment> courseHomeworkReplyCommentList =  this.selectCommentByParentId(parentId);
        courseHomeworkReplyCommentList.forEach(courseHomeworkReplyComment ->{
            HomeworkCommentResp resp = new HomeworkCommentResp();
            BeanUtils.copyProperties(courseHomeworkReplyComment,resp);
            //根据用户编码获取用户信息
            UserSimpleInfoDTO user = userAuthFacade.getUserSimpleInfo(courseHomeworkReplyComment.getUserUniqueCode());
            if(user != null){
                BeanUtils.copyProperties(user,resp);
            }
            commentChildList.add(resp);
        });
        return commentChildList;
    }

}
