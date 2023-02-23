package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.resp.UserSimpleInfoDTO;
import com.gupao.edu.common.page.PageBean;
import com.gupao.edu.common.page.PageBeanUtils;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.course.client.entity.CourseVideo;
import com.gupao.edu.course.client.entity.CourseVideoQuestion;
import com.gupao.edu.course.client.req.center.CourseVedioQuestionReq;
import com.gupao.edu.course.client.req.good.CourseQuestionReq;
import com.gupao.edu.course.client.resp.center.AskAnsweResp;
import com.gupao.edu.course.server.exception.CourseException;
import com.gupao.edu.course.server.mapper.CourseAskQuestionMapper;
import com.gupao.edu.course.server.service.CourseAskQuestionService;
import com.gupao.edu.course.server.service.CourseVideoService;
import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 问题记录表 服务实现类
 * </p>
 *
 * @author hduong
 * @since 2020-03-22
 */
@Service
public class CourseAskQuestionServiceImpl extends ServiceImpl<CourseAskQuestionMapper, CourseVideoQuestion> implements CourseAskQuestionService {

    @Autowired
    private CourseAskQuestionMapper courseAskQuestionMapper;

    /**
     * TODO 待修改
     * 分页 单表查询 课程 疑问对象
     * @param courseQuestionReq
     * @return
     */
    @Override
    public PageBean<AskAnsweResp> listPageCourseQuestionByCourseId(CourseQuestionReq courseQuestionReq) {
        //Page 实现 IPage 查询返回 单表实体对象
        Page<AskAnsweResp> pageParam = new Page<>(courseQuestionReq.getPageNum(), courseQuestionReq.getPageSize());
        QueryWrapper<AskAnsweResp> courseAskQuestionQueryWrapper = new QueryWrapper<>();
        //如果传入了视频Id ： 那么只根据视频Id查询
        if(courseQuestionReq.getVedioId() != null) {
            courseAskQuestionQueryWrapper.eq("vedio_Id", courseQuestionReq.getVedioId());
        } //否则根据 课程Id作为参数条件查询
        else if(courseQuestionReq.getCourseId() != null) {
            courseAskQuestionQueryWrapper.eq("course_Id", courseQuestionReq.getCourseId());
        }
        //用户作为分离的对象条件 : 查询我的答疑
        if(courseQuestionReq.getUserId() != null) {
            courseAskQuestionQueryWrapper.eq("user_Id", courseQuestionReq.getVedioId());
        }
        //如果是问题Id ： 还需要查询问题的回复 TODO
        Page<AskAnsweResp> retPageBean = null;//(Page<AskAnsweResp>) courseAskQuestionMapper.selectPage(pageParam, courseAskQuestionQueryWrapper);
        PageBean<AskAnsweResp> retCoursePageBean = PageBeanUtils.PageToPageBean(retPageBean);
        return retCoursePageBean;
    }

}
