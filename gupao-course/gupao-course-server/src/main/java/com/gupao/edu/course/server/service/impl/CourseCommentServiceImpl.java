package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.common.page.PageBean;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.common.utils.AutoMapper;
import com.gupao.edu.course.client.entity.Course;
import com.gupao.edu.course.client.entity.CourseComment;
import com.gupao.edu.course.client.entity.CourseOutline;
import com.gupao.edu.course.client.req.center.CourseCommentResp;
import com.gupao.edu.course.client.req.good.CourseCommonParamReq;
import com.gupao.edu.course.client.resp.OutLineResp;
import com.gupao.edu.course.client.resp.good.CourseCommentCensusResp;
import com.gupao.edu.course.server.mapper.CourseCommentMapper;
import com.gupao.edu.course.server.service.CourseCommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程评论表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class CourseCommentServiceImpl extends ServiceImpl<CourseCommentMapper, CourseComment> implements CourseCommentService {

    @Override
    public PageBean<CourseCommentResp> findPageCourseCommentByCourseId(Integer courseId) {
        return null;
    }

    /**
     * 根据课程id查询课程 评价
     * @param courseId
     * @return
     */
    @Override
    public CourseCommentCensusResp findCommentCensusByCourseId(Integer courseId) {
        //1.根据courseId，查询课程所有的评价  2.课程的评价只能官方进行回复，不支持学员相互评价
        Map<String,Object> map = new HashMap<>();
        map.put("course_id",courseId);
        List<CourseComment> courseComments = baseMapper.selectByMap(map);
        CourseCommentCensusResp courseCommentCensusResp = new CourseCommentCensusResp();
        if (courseComments != null && courseComments.size() > 0) {
            List<CourseCommentResp> courseCommentResps = new ArrayList<>();
            try {
                courseCommentResps = AutoMapper.batchTransform(CourseCommentResp.class,courseComments);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 总分
            int total = 0;
            int star1 = 0;
            int star2 = 0;
            int star3 = 0;
            int star4 = 0;
            int star5 = 0;
            for (CourseComment courseComment : courseComments) {
                // 查询均分
                total = total + courseComment.getStar();
                if (courseComment.getStar() == 1 ) {
                    star1++;
                }
                if (courseComment.getStar() == 2 ) {
                    star2++;
                }
                if (courseComment.getStar() == 3 ) {
                    star3++;
                }
                if (courseComment.getStar() == 4 ) {
                    star4++;
                }
                if (courseComment.getStar() == 5 ) {
                    star5++;
                }
            }
            courseCommentCensusResp.setAverage(new BigDecimal(total/courseComments.size()));
            courseCommentCensusResp.setStartLevel1(star1);
            courseCommentCensusResp.setStartLevel2(star2);
            courseCommentCensusResp.setStartLevel3(star3);
            courseCommentCensusResp.setStartLevel4(star4);
            courseCommentCensusResp.setStartLevel5(star5);
            courseCommentCensusResp.setCommentSize(courseComments.size());
            courseCommentCensusResp.setCourseCommentResps(courseCommentResps);
        }

        // 统计评价数量
        return courseCommentCensusResp;
    }

    @Override
    public Result courseComment(CourseCommonParamReq courseCommonQueryReq) {
        if (courseCommonQueryReq == null) {
            return Result.fail(CodeMessage.COMMENT_FAIL);
        }
        CourseComment courseComment = new CourseComment();
        courseComment.setCourseId(courseCommonQueryReq.getCourseId());
        courseComment.setUserUniqueCode(courseCommonQueryReq.getUserUniqueCode());
        courseComment.setNickName(courseCommonQueryReq.getNickName());
        courseComment.setStar(courseCommonQueryReq.getStar());
        // 已上课时间
        courseComment.setCreateTime(LocalDateTime.now());
        courseComment.setContent(courseCommonQueryReq.getContent());
        try {
            this.baseMapper.insert(courseComment);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.fail(CodeMessage.COMMENT_FAIL);
        }
        return Result.success();
    }
}
