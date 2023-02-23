package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.common.utils.CalculateProportionUtil;
import com.gupao.edu.course.client.entity.CourseOutline;
import com.gupao.edu.course.client.entity.CourseVideo;
import com.gupao.edu.course.client.resp.CourseOutlineTreeResp;
import com.gupao.edu.course.client.resp.LittleVideo;
import com.gupao.edu.course.client.resp.OutLineResp;
import com.gupao.edu.course.client.resp.OutlineVedioVO;
import com.gupao.edu.course.server.mapper.CourseOutlineMapper;
import com.gupao.edu.course.server.service.CourseOutlineService;
import com.gupao.edu.course.server.service.CourseVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程大纲表（关联视频资源） 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class CourseOutlineServiceImpl extends ServiceImpl<CourseOutlineMapper, CourseOutline> implements CourseOutlineService {

    @Autowired
    private CourseOutlineMapper courseOutlineMapper;
    @Autowired
    private CourseVideoService courseVideoService;
    @Override
    public List<OutLineResp> findOutLineByCourseId(Integer courseId) {
        // 还是先按照方式一
        //先查询出课程中所有的大纲
        Map<String,Object> map = new HashMap<>();
        map.put("course_id",courseId);
        List<OutLineResp> respList = new ArrayList<>();
        List<CourseOutline> courseOutlines = baseMapper.selectByMap(map);

        List<CourseOutline> courseOutlines1 = new ArrayList<>();
        // 先获取第一级节点
        if (courseOutlines != null && courseOutlines.size() > 0) {
            for (CourseOutline courseOutline : courseOutlines) {
                if (0 == courseOutline.getParentId()) {
                    courseOutlines1.add(courseOutline);
                }
            }
            // 获取父节点得子节点，组装到resp中
            for (CourseOutline parent : courseOutlines1) {
                OutLineResp resp = new OutLineResp();
                resp = addLittleVedio(parent,courseOutlines,resp);
                respList.add(resp);
            }
            // 按照序号排序
            List<OutLineResp> result = respList.stream().sorted(Comparator.comparing(OutLineResp::getSort))
                    .collect(Collectors.toList());
            return result;
        }


        return respList;
    }

    private OutLineResp addLittleVedio(CourseOutline parent, List<CourseOutline> courseOutlines,OutLineResp resp) {
        resp.setChapterId(parent.getId());
        resp.setChapterName(parent.getTitle());
        resp.setSort(parent.getSort());
        // 创建章节目录相关 与视频相关
        List<LittleVideo> littleVideos = new ArrayList<>();
        for (CourseOutline course : courseOutlines) {
            if (Objects.equals(parent.getId(),course.getParentId())) {
                LittleVideo littleVideo = new LittleVideo();
                littleVideo.setLittleId(course.getId());
                littleVideo.setLittleName(course.getTitle());
                littleVideo.setSort(course.getSort());
                // 查出视频的时长--通过视频ID
                CourseVideo courseVideo = courseVideoService.getBaseMapper().selectById(course.getVideoOrStreamId());
                if (courseVideo != null) {
                    littleVideo.setVideoId(courseVideo.getPolyvVideoId());
                    littleVideo.setVideoSize(courseVideo.getFileSize());
                }
                littleVideos.add(littleVideo);
            }
        }
        // 按照序号排序
        List<LittleVideo> list = littleVideos.stream().sorted(Comparator.comparing(LittleVideo::getSort))
                .collect(Collectors.toList());
        resp.setLittleVideos(list);
        return resp;
    }

    @Override
    public Long computeCourseVedioTime(Integer courseId) {
        Long total = 0L;
        //1: 先查所有的父级大纲
        //2: 根据大纲id查询所有的小节视频
        Map<String, Object> parMap = new HashMap<>(4);
        parMap.put("course_id", courseId);
        parMap.put("parent_id", 0);
        List<CourseOutline> courseOutlines = courseOutlineMapper.selectByMap(parMap);
        //从这里可以开始递归
        for (CourseOutline courseOutline : courseOutlines) {
            //开始递归 : 大纲或者 视频 需要在开始的时候进行维护 否则这块有问题
            if(courseOutline.getOutlineLevel() != 1) {
                //表示是视频 可以统计时间
                OutlineVedioVO ov = courseOutlineMapper.getOutlineSimpleInfo(courseOutline.getId());
                if(ov.getVideoSize() != null) {
                    total += ov.getVideoSize();
                }
            } else {
                parMap.clear();
                parMap.put("parent_id", courseOutline.getId());
                List<CourseOutline> listCourseOutlines = courseOutlineMapper.selectByMap(parMap);
                for (CourseOutline courseOutline1 : listCourseOutlines) {
                    //关联视频资源表查询 视频时长
                    OutlineVedioVO ov = courseOutlineMapper.getOutlineSimpleInfo(courseOutline1.getId());
                    if(ov != null && ov.getVideoSize() != null) {
                        total += ov.getVideoSize();
                    }
                }
            }
        }
        return total;
    }

    /**
     * 定义树结构对象
     * 查询数据库所有的课程id 下的父级 parent_id
     * ① 查询所有的大纲数据 --> 构造树结构对象
     * ② 递归查询数据库 --> 只要不是视频, 继续往下面查询
     * @param courseId
     * @return
     */
    @Override
    public List<CourseOutlineTreeResp> findOutLineCourseByCourseId(String userUniqueCode, Integer courseId) {
        // 还是先按照方式一
        //获取顶级大纲 列表
        List<CourseOutlineTreeResp> courseOutlineTreeResps = courseOutlineMapper.listCourseOutlineTopLevel(courseId);
        if (courseOutlineTreeResps != null && courseOutlineTreeResps.size() > 0) {
            for(CourseOutlineTreeResp parentOutline : courseOutlineTreeResps) {
                //大纲序号
                parentOutline.setSortStr("0" + parentOutline.getSort());
                //查询所有的 视频及播放进度
                List<CourseOutlineTreeResp>  childInfo = courseOutlineMapper.listCourseOutlineAndVedioInfo(userUniqueCode, parentOutline.getId());
                //计算视频 进度
                if(childInfo != null && childInfo.size() > 0) {
                    childInfo.stream().forEach(c -> {
                        if(c.getPlaySeconds() != null && c.getVideoSeconds() != null && c.getVideoSeconds() > 0) {
                            c.setRateOfLearning(CalculateProportionUtil.proportionLong(c.getPlaySeconds(),
                                    c.getVideoSeconds()));
                        }
                        c.setSortStr(parentOutline.getSort() + "-" + c.getSort());
                    });
                }
                parentOutline.setChildOutline(childInfo);
            }
        }
        return courseOutlineTreeResps;
    }

    /**
     * 待测试 这个方法 todo
     * @param courseOutlines
     * @param total
     * @return
     */
    private Long total(List<CourseOutline> courseOutlines, Long total) {
        for (CourseOutline courseOutline : courseOutlines) {
            if(courseOutline.getOutlineLevel() != 1) {
                //表示是视频 可以统计时间
                OutlineVedioVO ov = courseOutlineMapper.getOutlineSimpleInfo(courseOutline.getId());
                if(ov.getVideoSize() != null) {
                    total += ov.getVideoSize();
                }
            } else {
                Map<String, Object> parMap = new HashMap<>(4);
                parMap.put("parent_id", courseOutline.getId());
                List<CourseOutline> listCourseOutlines = courseOutlineMapper.selectByMap(parMap);
                total(listCourseOutlines, total);
            }
        }
        return total;
    }



}
