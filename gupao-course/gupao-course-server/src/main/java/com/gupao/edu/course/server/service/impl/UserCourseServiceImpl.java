package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.course.client.entity.UserCourse;
import com.gupao.edu.course.client.entity.UserCourseProgress;
import com.gupao.edu.course.client.req.center.StudyCenterReq;
import com.gupao.edu.course.client.resp.UserCourseProgressVO;
import com.gupao.edu.course.client.resp.center.MyCourseResp;
import com.gupao.edu.course.server.mapper.UserCourseMapper;
import com.gupao.edu.course.server.service.UserCourseProgressService;
import com.gupao.edu.course.server.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 我购买的课程表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class UserCourseServiceImpl extends ServiceImpl<UserCourseMapper, UserCourse> implements UserCourseService {

    @Autowired
    private UserCourseMapper userCourseMapper;

    /**
     * 查询 最近上课
     */
    @Autowired
    private UserCourseProgressService userCourseProgressService;

    @Override
    public List<MyCourseResp> listMyCourse(StudyCenterReq studyCenterReq) {
        //重新计算 limit 后面的值 : 开始 记录数 + 多少条
        int size = studyCenterReq.getPageSize();
        if(size <= 0) {
            studyCenterReq.setPageSize(10);
        } else {
            studyCenterReq.setPageSize(studyCenterReq.getPageNum() * size);
        }
        //设置 开始记录数
        if(studyCenterReq.getPageNum() <= 0) {
            studyCenterReq.setPageNum(1);
        } else {
            studyCenterReq.setPageNum((studyCenterReq.getPageNum() - 1) * studyCenterReq.getPageSize());
        }
        List<MyCourseResp> respList = userCourseMapper.listMyCourse(studyCenterReq);
        respList.forEach(u -> {
            if(u.getVideoSeconds() != null && u.getPlaySeconds() != null && u.getVideoSeconds() > 0) {
                Long be = u.getPlaySeconds();
                Long af = u.getVideoSeconds();
                u.setRateOfLearning(String.valueOf((float)be / af * 100).substring(0, 2) + "%");
            }
            //todo  查完用户购买的课程列表后 然后进行列表遍历 : 查询最近上的一个视频及进度
            UserCourseProgressVO userCourseProgress = userCourseProgressService.getLastStudyVedioVO(studyCenterReq.getUserUniqueCode(),
                    u.getCourseId());
            if(userCourseProgress != null) {
                u.setCourseOutLineName(userCourseProgress.getCourseOutLineName());
                u.setCourseOutLineId(userCourseProgress.getCourseOutlineId());
                u.setPlaySeconds(userCourseProgress.getPlaySeconds());
                u.setVideoSeconds(userCourseProgress.getVideoSeconds());
            }
        });
        return respList;
    }
// 计算百分比 暂时这样
//    public static void main(String[] args) {
//        long current = 100695896L;
//        long total = 817214494L;
//        System.out.println(String.valueOf(((float)100695896/817214494 * 100)).substring(0, 2) + "%");
//    }
}
