package com.gupao.edu.course.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.course.client.entity.Course;
import com.gupao.edu.course.client.req.good.CourseListReq;
import com.gupao.edu.course.client.resp.CourseDetailResp;
import com.gupao.edu.course.client.resp.good.CourseListResp;
import com.gupao.edu.course.client.resp.good.TeacherResp;
import com.gupao.edu.course.server.mapper.CourseMapper;
import com.gupao.edu.course.server.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/17 10:14
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {


    @Override
    public int countPage(CourseListReq courseSubjectReq) {
        return 0;
    }

    @Override
    public IPage<CourseListResp> listCoursePage(CourseListReq courseSubjectReq) {
        Page<CourseListResp> page = new Page<>(courseSubjectReq.getPageNum(),courseSubjectReq.getPageSize());
        return page.setRecords(baseMapper.listCoursePage(page, courseSubjectReq.getCategory_id(),courseSubjectReq.getGrade_id()));
    }

    /**
     * 根据课程id和 类型更新评论和查询人数
     * @param courseId
     * @param type
     * @return
     */
    public boolean saveOrUpdate(Integer courseId,Integer type) {
        // 更新类型： 0评论 1学习
        Course course = this.baseMapper.selectOne(new QueryWrapper<Course>().eq("id", courseId));
        if (course != null) {
            if (0 == type) {
                course.setCommentNum(course.getCommentNum() + 1);
                baseMapper.update(course,new QueryWrapper<Course>().eq("id",courseId));
                return true;
            }
            if (1 == type) {
                course.setStudyNum(course.getStudyNum() + 1);
                baseMapper.update(course,new QueryWrapper<Course>().eq("id",courseId));
                return true;
            }
        }
        return false;
    }

    /**
     * 根据课程id获取课程详情页
     * @param courseId
     * @param userUniqueCod
     * @return
     */
    @Override
    public CourseDetailResp getCourseDetail(Integer courseId, String userUniqueCod) {
        // 先获取课程实体
        Course course = this.baseMapper.selectOne(new QueryWrapper<Course>().eq("id", courseId));
        // 距离抢购结束时间和剩余名额数
        List<TeacherResp> teachers = JSON.parseObject(course.getLecturerIds(),new TypeReference<ArrayList<TeacherResp>>(){});
        //TODO  调用营销服务接口，查询抢购时间 与 剩余名额数
       // List<TeacherResp> teacherTeams = this.baseMapper.selectTeachersByIds(List<Integer> ids);
        // 优惠券问题 明早找鹏飞确认一下
        CourseDetailResp courseDetailResp = new CourseDetailResp();
        courseDetailResp.setId(courseId);
        courseDetailResp.setCourseName(course.getCourseName());
        // courseDetailResp.setBuyEndDateTime();\
        //courseDetailResp.setRemainingQuota();
         courseDetailResp.setCourseTitle(course.getCourseTitle());
         courseDetailResp.setImageUrl(course.getImageUrl());
         courseDetailResp.setStudyNum(course.getStudyNum());
         courseDetailResp.setCommentNum(course.getCommentNum());
        courseDetailResp.setTotalMinutes(course.getTotalMinutes());
        courseDetailResp.setPraiseScore(course.getPraiseScore());
        courseDetailResp.setCost(course.getCost());
        courseDetailResp.setPrice(course.getPrice());
        courseDetailResp.setDescription(course.getDescription());
        courseDetailResp.setTeacherTeams(teachers);
        return courseDetailResp;
    }



    @Override
    public boolean save(Course course) {
        return false;
    }
}
