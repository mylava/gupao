package com.gupao.edu.course.server.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.common.dict.service.DictService;
import com.gupao.edu.common.exception.GuPaoBaseException;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.course.client.dto.CourseDTO;
import com.gupao.edu.course.client.dto.LecturerDTO;
import com.gupao.edu.course.client.entity.Course;
import com.gupao.edu.course.client.entity.Lecturer;
import com.gupao.edu.course.client.entity.UserCourseFavorite;
import com.gupao.edu.course.client.req.CourseFavoriteReq;
import com.gupao.edu.course.client.req.good.*;
import com.gupao.edu.course.client.resp.CourseDetailResp;
import com.gupao.edu.course.client.resp.OutLineResp;
import com.gupao.edu.course.client.resp.good.CourseCommentCensusResp;
import com.gupao.edu.course.client.resp.good.CourseListResp;
import com.gupao.edu.course.client.resp.good.DictValueResp;
import com.gupao.edu.course.client.resp.good.ShareCourseResp;
import com.gupao.edu.course.server.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


/**
 * 首页，好课模块
 *
 * @author zhangpan
 * @since 2020-03-18
 */
@Slf4j
@Api(tags = "好课模块")
@RestController
@RequestMapping("/coursecenter")
@Validated
public class GpGoodCourseController {

    @Autowired
    private CourseOutlineService courseOutlineService;

    @Resource
    private CourseCommentService courseCommentService;
    @Autowired
    private DictService dictService;
    /**
     * 课程主表服务
     */
    @Autowired
    private CourseService courseService;
    /**
     * 评论服务
     */
    @Autowired
    private CourseCommentService commentService;

    /**
     * 讲师 服务
     */
    @Autowired
    private LecturerService lecturerService;

    /**
     * 用户收藏课程服务
     */
    @Autowired
    private UserCourseFavoriteService userCourseFavoriteService;

    /**
     * 课程分享 服务
     */
    @Autowired
    private CourseShareService courseShareService;


    /**
     * app 接口 首页 ： 新增观看 人数 评论人数
     * 注意 ： mybatis plus 对 课程的人数更新和评论人数更新 以及 浏
     * 览人数更新 以及关注人数更新都是单表更新操作
     * 对于这几个服务的入参： 都是 课程 实体 对象 ： GpCourse
     * 服务自提供
     * ----------------
     * 传入参数 ： 不同的类型 ： 课程预告类型 训练营类型 小课类型
     * 最近课程 和 历史课程 呢 ?
     * 最近课程和往期课程怎么查?
     * 用户正在上的课程 怎么知道
     *
     * @param courseListReq
     * @return
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "1.课程列表", notes = "课程列表")
    @ApiImplicitParam(name = "courseListReq", value = "查询课程列表参数", required = false, dataType = "CourseListReq")
    @PostMapping(value = "/courseList")
    public Result<IPage<CourseListResp>> courseList(@RequestBody CourseListReq courseListReq) {
        //TODO 1.查看公开课列表 2.训练营最近课程列表 3.训练营历史课程列表
        //TODO 根据课程类型查询 课程列表 java或者 大数据
        log.info("我的地址列表,请求参数: {}", courseListReq);
        IPage<CourseListResp> courseListRespIPage = courseService.listCoursePage(courseListReq);
        log.info("我的地址列表,响应参数: {}", courseListRespIPage);
        return Result.success(courseListRespIPage);
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "2.课程分类字典查询", notes = "课程分类字典查询")
    @ApiImplicitParam(name = "dictListReq", value = "查询课程分类参数", required = true, dataType = "DictListReq")
    @PostMapping(value = "/gradeCourseList")
    public Result<List<DictValueResp>> gradeCourseList(@RequestBody DictListReq dictListReq) {
        //TODO 根据分类编码，和分类ID获取  dict_value
//        CourseListResp resp = new CourseListResp();
        List<DictValueResp> list = dictService.getDictValues(dictListReq);
        return Result.success(list);
    }

    /**
     * 更新 评论人数 更新 学习人数
     *
     * @param courseId
     * @return boolean
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "3.更新学习人数和评论人数", notes = "更新学习人数和评论人数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "type", value = "更新类型： 0评论 1学习", required = true, dataType = "Integer"),
    })
    @GetMapping(value = "/updateCourseCommentStudyNum")
    public Result updateCourseCommentStudyNum(@RequestParam Integer courseId, @RequestParam Integer type) {
        //TODO 1.根据courseId，查询课程 2.根据查到的更新，更新学习人数，评论人数
        boolean flag = courseService.saveOrUpdate(courseId, type);
        if (flag) {
            return Result.success();
        } else {
            return Result.fail(CodeMessage.CORS_SAVE_FAIL);
        }
    }

    /**
     * 根据课程视频的ID获取本课的课程详情
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "4.根据课程的ID获取本课的课程详情", notes = "根据课程的ID获取本课的课程详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "courseId", value = "课程id", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/getCourseDetailById")
    public Result<CourseDetailResp> getCourseDetailById(@RequestParam Integer courseId, @RequestParam(value = "userUniqueCode") String userUniqueCod) {
        //TODO 1.根据courseId，查询课程详情信息
        CourseDetailResp courseDetail = courseService.getCourseDetail(courseId, userUniqueCod);
        //TODO  json 解析
//        String teachers = courseResp.getLecturerIds();
//        if(!StringUtils.isEmpty(teachers)) {
//            //todo 查询教师集合
//            String[] tesIds = teachers.split(",");
//            Collection<TeacherTeam> teacherTeams = teacherTeamService.listByIds(Arrays.asList(teachers));
//           // courseResp.setTeacherTeams((List)teacherTeams);
//        }
        return Result.success(courseDetail);
    }

    /**
     * 根据CourseId获取课程大纲
     **/
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "5.根据课程的ID获取课程大纲", notes = "根据课程的ID获取课程大纲")
    @ApiImplicitParam(name = "courseId", value = "课程id", required = true, dataType = "Integer")
    @GetMapping(value = "/getOrderOutlineList")
    public Result<List<OutLineResp>> getOrderOutlineList(@RequestParam("courseId") Integer courseId) {
        //TODO 1.根据courseId，查询课程大纲  2.根据大纲parentId进行排序 3.计算学习总进度
        List<OutLineResp> outLineResps = courseOutlineService.findOutLineByCourseId(courseId);
        return Result.success(outLineResps);
    }

    /**
     * 根据课程ID--查询所有的评价
     *
     * @param courseId 课程ID
     * @return
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "6.根据课程ID--查询当前课程所有的评价", notes = "根据课程ID--查询当前课程所有的评价")
    @ApiImplicitParam(name = "courseId", value = "学员评价查询", required = true, dataType = "Integer")
    @PostMapping(value = "/findAllCourseCommentByCourseId")
    public Result<CourseCommentCensusResp> findAllCourseCommentByCourseId(@RequestParam("courseId") Integer courseId) {
        //TODO 1.根据courseId，查询课程所有的评价  2.课程的评价只能官方进行回复，不支持学员相互评价
        CourseCommentCensusResp resp = commentService.findCommentCensusByCourseId(courseId);
        return Result.success(resp);
    }

    /**
     * 学员评价
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "7.学员评价", notes = "学员评价")
    @ApiImplicitParam(name = "courseCommonParamReq", value = "学员评价插入", required = true)
    @PostMapping(value = "/courseComment")
    public Result courseComment(@RequestBody CourseCommonParamReq courseCommonQueryReq) {
        //TODO 学员点击评价进行评价，提交之后，插入评价到评论表中
        // 课程评价审核之后 通过

        return commentService.courseComment(courseCommonQueryReq);
    }

    /**
     * 根据课程id分享课程
     *
     * @param shareCourseQueryReq
     * @return
     */
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "8.保存用户分享的课程", notes = "保存用户分享的课程")
    @ApiImplicitParam(name = "shareCourseQueryReq", value = "保存用户分享的课程", required = true, dataType = "ShareCourseQueryReq")
    @PostMapping(value = "/saveUserShareCourse")
    public Result saveUserShareCourse(@Valid @RequestBody ShareCourseQueryReq shareCourseQueryReq) {
        // 学员点击分享  获取要分享的课程，进行分享
        int result = courseShareService.saveShare(shareCourseQueryReq);
        if (result > 0) {
            return Result.success();
        }
        return Result.fail(CodeMessage.CORS_SHARE_FAIL);
    }

    // todo  和前端联调时可以确定
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "9.根据课程id查询要分享的课程", notes = "根据课程id查询要分享的课程")
    @ApiImplicitParam(name = "courseId", value = "查询用户将要分享的课程", required = true, dataType = "Integer")
    @PostMapping(value = "/findShareCourseById")
    public Result<ShareCourseResp> getShareCourseById(@RequestParam Integer courseId) {
        // 学员点击分享  获取要分享的课程，进行分享
        //这个为主:
        //1: 调用用户接口 : feign接口查询 用户昵称 头像 及当前课程名字 + 课程图文详情
        //2: 小程序码 跳转相关课程 连接 : 里面携带课程url链接
        //3: 获取商品id : 入参: 资源类型 + 课程id --> 通过feign接口去调用
        //todo 调用用户 feign接口 查询用户昵称和头像 --> 这个部分是不是在sessoion中呢?
        //todo  获取课程详情 将名字 图文设置到分享对象中 或者app端直接将前面的信息带入图片中
        return Result.success(new ShareCourseResp());
    }

    /**
     * 收藏课程
     *
     * @return 点击收藏按钮
     * UserCourseFavorite
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "10.收藏课程", notes = "收藏课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id", dataType = "Integer"),
            @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", dataType = "String")
    })
    @PostMapping(value = "/courseFavorite")
    public Result courseFavorite(@NotNull(message = "课程id不能为空") @RequestParam("courseId") Integer courseId,
                                 @NotBlank(message = "用户唯一编码不能为空") @RequestParam("userUniqueCode") String userUniqueCode) {
        // 1.通过当前用户id 课程id将课程存到收藏表中
        // 新增收藏 ： 是否新增一个收藏资源类型 ： 可以收藏 问答 文章 todo
        //先查询课程是否收藏: todo 是不是校验 用户是否合法 课程是否合法
        log.info("收藏课程: 接收参数: 课程Id:{}; 用户唯一编码: {}", courseId, userUniqueCode);
        UserCourseFavorite userCourseFavorite = userCourseFavoriteService.selectByCourseIdAndUserUniqueCode(courseId, userUniqueCode);
        if (userCourseFavorite != null) {
            return Result.fail(CodeMessage.CORS_FAVORITE_REPEAT_FAIL);
        }
        if (userCourseFavoriteService.saveCourseFavorite(courseId, userUniqueCode) > 0) {
            log.info("收藏课程: 返回结果成功! 课程Id:{}; 用户唯一编码: {}", courseId, userUniqueCode);
            return Result.success();
        }
        return Result.fail(CodeMessage.CORS_FAVORITE_FAIL);
    }

    /**
     * 客服咨询
     *
     * @param courseFeedback
     * @return Upcoming Classes
     */
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "11.客服咨询", notes = "客服咨询")
    @GetMapping(value = "/saveFeedback")
    public Result getReturnBack(@RequestBody CourseFeedbackReq courseFeedback) {
        //TODO 关闭咨询窗户之后，通过fegion调用用户服务，获取聊天记录 插入咨询表中---
        // 是否返回一个老师的id
        return Result.success();
    }

    /**
     * 获取用户的课程收藏列表
     *
     * @return UserCourseFavorite
     */
    @ApiOperationSupport(order = 12)
    @ApiOperation(value = "12.课程收藏列表", notes = "课程收藏列表")
    @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", dataType = "Integer")
    @PostMapping(value = "/listCourseFavorite")
    public Result<List<CourseListResp>> listCourseFavorite(@Valid @RequestBody CourseFavoriteReq courseFavoriteReq) {
        // 1.通过当前用户id 获取用户的课程收藏列表  2.通过返回的课程id，将课程信息返回
        log.info("获取用户收藏课程列表: 接收参数: {}", courseFavoriteReq);
        List<CourseListResp> resps = userCourseFavoriteService.listCourseFavorite(courseFavoriteReq);
        log.info("获取用户收藏课程列表: 返回结果: {}", resps);
        return Result.success(resps);
    }

    /**
     * 视频播放  试听播放
     *
     * @return UserCourseFavorite
     */
    @ApiOperationSupport(order = 13)
    @ApiOperation(value = "13.视频播放与试听播放", notes = "视频播放  试听播放")
    @ApiImplicitParam(name = "videoId", value = "视频资源id", required = true, dataType = "Integer")
    @GetMapping(value = "/palyvideo")
    public Result<Boolean> palyVideo(@RequestParam("userId") Integer videoId) {
        //TODO 1.通过当前用户id 获取用户的课程收藏列表  2.通过返回的课程id，将课程信息返回
        return Result.success();
    }


    /**
     * 对外 提供feign 接口 获取讲师信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getLecturerDTO")
    public LecturerDTO getLecturerDTO(@RequestParam("id") Integer id) {
        LecturerDTO lecturerDTO = new LecturerDTO();
        Optional<Lecturer> lecturerOptional = Optional.of(lecturerService.getById(id));
        if (lecturerOptional.isPresent()) {
            Lecturer lecturer = lecturerOptional.get();
            lecturerDTO.setId(id);
            lecturerDTO.setName(lecturer.getName());
            lecturerDTO.setSummary(lecturer.getSummary());
            lecturerDTO.setDescription(lecturer.getDescription());
        } else {
            throw new GuPaoBaseException(CodeMessage.CORS_LECTURER_NOT_EXISTS);
        }
        return lecturerDTO;
    }

    /**
     * 对外 提供feign 接口 获取课程名字等信息
     *
     * @param courseId
     * @return
     */
    @GetMapping("/getCourseDTO")
    public CourseDTO getCourseDTO(@RequestParam("courseId") Integer courseId) {
        CourseDTO courseDTO = new CourseDTO();
        Optional<Course> courseOptional = Optional.of(courseService.getById(courseId));
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            courseDTO.setId(courseId);
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setCourseTitle(course.getCourseTitle());
            courseDTO.setImageUrl(course.getImageUrl());
        } else {
            throw new GuPaoBaseException(CodeMessage.CORS_COURSE_NOT_EXISTS);
        }
        return courseDTO;
    }


}
