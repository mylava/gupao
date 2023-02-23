package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.facade.member.UserAuthFacade;
import com.gupao.edu.account.client.resp.UserSimpleInfoDTO;
import com.gupao.edu.course.client.dto.MyHomeworkDTO;
import com.gupao.edu.course.client.entity.CourseHomeworkReply;
import com.gupao.edu.course.client.req.center.HomeworkDetailQueryReq;
import com.gupao.edu.course.client.resp.center.HomeworkReplyResp;
import com.gupao.edu.course.server.mapper.CourseHomeworkReplyMapper;
import com.gupao.edu.course.server.service.CourseHomeworkReplyCommentService;
import com.gupao.edu.course.server.service.CourseHomeworkReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 学员答作业表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class CourseHomeworkReplyServiceImpl extends ServiceImpl<CourseHomeworkReplyMapper, CourseHomeworkReply> implements CourseHomeworkReplyService {

    @Autowired
    private CourseHomeworkReplyCommentService courseHomeworkReplyCommentService;
    @Autowired
    private UserAuthFacade userAuthFacade;

    /**
     * 根据课程作业ID获取提交作业数
     * @param courseHomeworkId 课程作业ID
     * @return 优秀作业数
     */
    @Override
    public Integer countCommitNum(Integer courseHomeworkId) {
        return baseMapper.countCommitNum(courseHomeworkId);
    }

    /**
     * 根据课程作业ID获取优秀作业数
     * @param courseHomeworkId 课程作业ID
     * @return 优秀作业数
     */
    @Override
    public Integer countGoodNum(Integer courseHomeworkId) {
        return baseMapper.countGoodNum(courseHomeworkId);
    }

    /**
     * 根据用户唯一编码获取我的作业列表
     * @param userUniqueCode 用户唯一编码
     * @return 我的作业列表
     */
    @Override
    public List<MyHomeworkDTO> selectMyHomeworkByUserUniqueCode(Page<MyHomeworkDTO> page, String userUniqueCode) {
        return baseMapper.selectMyHomeworkByUserUniqueCode(page,userUniqueCode);
    }

    /**
     * 判断某用户是否提交了某作业
     * @param homeworkId 作业ID
     * @param userUniqueCode 用户唯一编码
     * @return true or false
     */
    @Override
    public boolean whetherReplyHomework(Integer homeworkId,String userUniqueCode){
        List<CourseHomeworkReply> list = baseMapper.selectList(new QueryWrapper<CourseHomeworkReply>()
                .eq("course_homework_id" , homeworkId)
                .eq("user_unique_code" , userUniqueCode));
        return null != list && list.size() > 0;
    }

    /**
     * 根据作业ID获取该作业学员答作业列表 - 按时间排列
     * @param page 分页信息
     * @param homeworkId 作业ID
     * @return 学员答作业列表
     */
    @Override
    public List<CourseHomeworkReply> selectReplyOrderNewestByHomeworkId(IPage<CourseHomeworkReply> page, Integer homeworkId){
        return baseMapper.selectReplyOrderNewestByHomeworkId(page,homeworkId);
    }

    /**
     * 根据作业ID获取该作业学员答作业列表 - 按分数排列
     * @param page 分页信息
     * @param homeworkId 作业ID
     * @return 学员答作业列表
     */
    @Override
    public List<CourseHomeworkReply> selectReplyOrderScoreByHomeworkId(IPage<CourseHomeworkReply> page,Integer homeworkId){
        return baseMapper.selectReplyOrderScoreByHomeworkId(page,homeworkId);
    }

    /**
     * 根据作业ID和用户唯一编码获取用户作业回复信息
     * @param homeworkId 作业ID
     * @param userUniqueCode 用户唯一编码
     * @return 用户作业回复信息
     */
    @Override
    public CourseHomeworkReply selectReplyByUserAndHomework(Integer homeworkId,String userUniqueCode){
        List<CourseHomeworkReply> list = baseMapper.selectList(new QueryWrapper<CourseHomeworkReply>()
                .eq("course_homework_id" , homeworkId)
                .eq("user_unique_code" , userUniqueCode));
        if (null == list || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据作业回复分页参数获取其分页列表信息
     * @param req 作业回复分页参数
     * @return 作业回复分页列表信息
     * @throws Exception 抛出异常
     */
    @Override
    public IPage<HomeworkReplyResp> findHomeworkReplyRespIPage(HomeworkDetailQueryReq req) throws Exception{

        // 需获取作业回复分页列表
        Page<HomeworkReplyResp> homeworkReplyRespPage = new Page<>(req.getPageNum(),req.getPageSize());
        List<HomeworkReplyResp> homeworkReplyRespList = new ArrayList<>();
        // 获取作业回复分页列表
        Page<CourseHomeworkReply> courseHomeworkReplyPage = new Page<>(req.getPageNum(),req.getPageSize());
        // 获取作业回复列表
        List<CourseHomeworkReply> courseHomeworkReplyList = this.findCourseHomeworkReplyList(courseHomeworkReplyPage,req.getHomeworkId(),req.getDesc());

        // 遍历评论回复
        for (CourseHomeworkReply reply : courseHomeworkReplyList) {
            // 获取各学员答作业信息
            HomeworkReplyResp replyResp = new HomeworkReplyResp();
            BeanUtils.copyProperties(reply,replyResp);

            //调用远程接口根据用户唯一编码获取用信息
            UserSimpleInfoDTO userSimpleInfoDTO = userAuthFacade.getUserSimpleInfo(reply.getUserUniqueCode());
            if (null == userSimpleInfoDTO) {
                throw new Exception("获取不到用户信息！");
            }
            BeanUtils.copyProperties(userSimpleInfoDTO,replyResp);

            // 学员评论作业信息
            replyResp.setHomeworkCommentRespList(courseHomeworkReplyCommentService.fingHomeworkCommentRespList(reply.getId()));

            // 插入学员作业回复信息
            homeworkReplyRespList.add(replyResp);
        }
        courseHomeworkReplyPage.setRecords(courseHomeworkReplyList);
        BeanUtils.copyProperties(courseHomeworkReplyPage,homeworkReplyRespPage);

        return homeworkReplyRespPage.setRecords(homeworkReplyRespList);
    }

    /**
     * 根据条件判断按最新排序还是按分数排序获取作业回复列表
     * @param desc 1：按最新排序，default：按分数排序
     * @return 作业回复列表
     */
    @Override
    public List<CourseHomeworkReply> findCourseHomeworkReplyList(Page<CourseHomeworkReply> courseHomeworkReplyPage,Integer homeworkId,Integer desc){
        switch (desc){
            case 1:
                // 按最新排序
                return this.selectReplyOrderNewestByHomeworkId(courseHomeworkReplyPage,homeworkId);
            default:
                // 按默认排序（按分数排序）
                return this.selectReplyOrderScoreByHomeworkId(courseHomeworkReplyPage,homeworkId);

        }
    }
}
