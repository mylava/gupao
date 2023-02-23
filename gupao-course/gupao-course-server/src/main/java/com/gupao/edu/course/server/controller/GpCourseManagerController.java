package com.gupao.edu.course.server.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.common.page.PageBean;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.course.client.entity.Course;


import com.gupao.edu.course.client.entity.CourseHomework;
import com.gupao.edu.course.client.entity.CourseOutline;
import com.gupao.edu.course.client.entity.CourseVideo;
import com.gupao.edu.course.client.req.good.CourseQuestionReq;
import com.gupao.edu.course.client.req.manager.*;
import com.gupao.edu.course.client.resp.CourseDetailResp;
import com.gupao.edu.course.client.resp.OutLineResp;
import com.gupao.edu.course.client.resp.center.AskAnsweResp;
import com.gupao.edu.course.client.resp.managecourse.ManageCourseResp;
import com.gupao.edu.course.server.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 咕泡课程管理模块
 *
 * @author zhangpan
 * @since 2020-03-18
 */
@Api(tags = "课程管理模块")
@RestController
@RequestMapping("/coursemanager")
public class GpCourseManagerController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseOutlineService courseOutlineService;

    /**
     * 首页管理服务
     */
    @Autowired
    private HomePageManageService homePageManageService;

    /**
     * 课程 疑问 记录服务
     */
    @Autowired
    private CourseAskQuestionService courseAskQuestionService;

    /**
     * 后台查询课程详情
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "后台查询课程详情", notes = "后台查询课程详情")
    @ApiImplicitParam(name = "courseId", value = "课程ID", required = true, dataType = "Integer")
    @GetMapping(value = "/getCourseById")
    public Result<ManageCourseResp> getCourseById(@RequestParam("courseId") Integer courseId) {
        ManageCourseResp resp = new ManageCourseResp();
        List<OutLineResp> outlines = courseOutlineService.findOutLineByCourseId(courseId);
        resp.setOutlines(outlines);
        return Result.success(resp);
    }


    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "创建课程", notes = "创建课程")
    @ApiImplicitParam(name = "gpCourse", value = "课程的实体类", required = true, dataType = "GpCourse")
    @GetMapping(value = "/createCourse")
    public Result<Boolean> createCourse(@RequestBody GpCourseReq gpCourseReq) {
        List<CourseOutline> outlines = gpCourseReq.getCourseOutlines();
        Course gpCourse = new Course();
        boolean outlineFlag = courseOutlineService.saveOrUpdateBatch(outlines);
        boolean flag = courseService.save(gpCourse);
        if (flag && outlineFlag) {
            return Result.success();
        }
        return Result.fail(CodeMessage.CORS_SAVE_FAIL);
    }



    /**
     * 创建课程大纲
     *
     * @GetMapping(value = "/createCourseOutLine")
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "创建课程大纲", notes = "创建课程大纲")
    @ApiImplicitParam(name = "courseOutline", value = "课程大纲", required = true, dataType = "CourseOutline")
    @GetMapping(value = "/createCourseOutline")
    public Result<Boolean> createCourseOutline(@RequestBody List<CourseOutline> courseOutlines) {
        for (CourseOutline courseOutline : courseOutlines) {
            boolean flag = courseOutlineService.save(courseOutline);
        }

        /*if(!flag) {
            return Result.fail(CodeMessage.CORS_SAVE_FAIL);
        }*/
        return Result.success();
    }

    /**
     * 视频管理模块
     *
     * @GetMapping(value = "/upLoadCourse")
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "视频管理模块", notes = "视频管理模块")
    @ApiImplicitParam(name = "courseVideo", value = "视频实体类", required = true, dataType = "CourseVideo")
    @GetMapping(value = "/upLoadCourse")
    public Object upLoadCourse(@RequestBody CourseVideo courseVideo) {
        return Result.success();
    }

    /**
     * 课程编辑
     *
     * @return
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "课程编辑", notes = "课程编辑")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseOutline", value = "课程大纲", required = true, dataType = "CourseOutline"),
            @ApiImplicitParam(name = "courseImage", value = "图文详情", required = true, dataType = "CourseImage")
    })
    @GetMapping(value = "/updateCourse")
    public Object updateCourse(@RequestParam CourseOutline courseOutline) {
        return Result.success();
    }

    /**
     * 首页管理
     *
     * @return
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "首页管理", notes = "首页管理")
    @ApiImplicitParam(name = "homePageManage", value = "首页配置", required = true, dataType = "HomePageManage")
    @GetMapping(value = "/courseIndexManager")
    public Result courseCatalogue(@RequestBody HomeworkReq homePageManage) {
        return Result.success();
    }

    /**
     * 新增 或者 修改首页配置
     *
     * @param homePageManage
     * @return
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "新增首页配置", notes = "新增首页管理")
    @ApiImplicitParam(name = "saveCourseCatalogue", value = "新增首页配置", required = true, dataType = "HomePageManage")
    @GetMapping(value = "/saveCourseCatalogue")
    public Result saveCourseCatalogue(@RequestBody HomeworkReq homePageManage) {
       // boolean flag = homePageManageService.save(homePageManage);

        return Result.success();
    }

    /**
     * 上下 移动 首页配置
     *
     * @param homePageManage
     * @return
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "上下移动配置", notes = "上下移动配置")
    @ApiImplicitParam(name = "upAndDownCourseCatalogue", value = "上下移动配置", required = true, dataType = "HomePageManageReq")
    @GetMapping(value = "/upAndDownCourseCatalogue")
    public Result saveCourseCatalogue(@RequestBody HomePageManageReq homePageManage) {
        //批量更新 序号
       /* boolean flag = homePageManageService.updateBatchById(homePageManage.getHomePageManages());
        if (!flag) {
            return Result.fail(CodeMessage.CORS_SAVE_FAIL);
        }*/
        return Result.success();
    }

    /**
     * 删除 配置 后台接口
     *
     * @param homePageManage
     * @return
     */
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "删除配置", notes = "删除配置")
    @ApiImplicitParam(name = "deleteCourseCatalogue", value = "上下移动配置", required = true, dataType = "HomePageManage")
    @GetMapping(value = "/deleteCourseCatalogue")
    public Result deleteCourseCatalogue(@RequestBody HomeworkReq homePageManage) {
        /*boolean flag = homePageManageService.removeById(homePageManage.getRelatedId());
        if (!flag) {
            return Result.fail(CodeMessage.CORS_SAVE_FAIL);
        }*/
        return Result.success();
    }


    /**
     * 评价管理
     *
     * @return
     */
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "评价管理", notes = "评价管理")
    @ApiImplicitParam(name = "courseId", value = "需要查看的课程", required = true, dataType = "Integer")
    @GetMapping(value = "/courseComment")
    public Object courseComment(@RequestParam Integer courseId) {
        return Result.success();
    }

    /**
     * 答疑管理 : 新增答疑 ： 回答别人的疑问 问题
     *            修改 ： 考虑为编辑
     *            删除 ： 直接删除（如果下面有回复呢？）
     *            查询 ： 查询所有的答疑 : 查询答疑列表 (默认情况是怎么查询)
     *              答疑默认是在 视频下面的， 关联视频查询答疑： 或者传入课程Id
     *              那么就是根据课程Id查询疑问， 如果存在视频Id 直接查询视频的疑问
     * @return
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "课程疑问列表", notes = "课程疑问列表")
    @ApiImplicitParam(name = "CourseQuestionReq", value = "课程疑问请求实体", required = true, dataType = "CourseQuestionReq")
    @GetMapping(value = "/listCourseQuestion")
    public Result<PageBean<AskAnsweResp>> courseAnswer(@RequestBody CourseQuestionReq courseQuestionReq) {
        PageBean<AskAnsweResp> pageBean = courseAskQuestionService.listPageCourseQuestionByCourseId(courseQuestionReq);
        return Result.success(pageBean);
    }

    /**
     * 作业管理--老师题目管理
     *
     * @return
     */
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "老师题目管理", notes = "老师题目管理")
    @ApiImplicitParam(name = "HomeworkReq", value = "搜索条件", required = true, dataType = "HomeworkReq")
    @GetMapping(value = "/homeworkTeacherManager")
    public Result<Boolean> homeworkTeacherManager(@RequestBody HomeworkReq homeworkReq) {
        //List<CourseTeacherManagerDTO> list = new ArrayList<>();
        return Result.success();

    }

    /**
     * 作业管理---布置作业
     *
     * @return
     */
    @ApiOperationSupport(order = 12)
    @ApiOperation(value = "布置作业", notes = "布置作业")
    @ApiImplicitParam(name = "teacherManagerDTO", value = "传入布置的作业", required = true, dataType = "CourseTeacherManagerDTO")
    @GetMapping(value = "/assignHomework")
    public Result<Boolean> assignHomework(@RequestBody HomeworkReq teacherManagerDTO) {
        return Result.success();
    }

    /**
     * 作业管理--学员作业管理
     *
     * @return
     */
    @ApiOperationSupport(order = 13)
    @ApiOperation(value = "学员作业管理", notes = "学员作业管理")
    @ApiImplicitParam(name = "courseManagerReq", value = "传入查询条件", required = true, dataType = "CourseManagerReq")
    @GetMapping(value = "/homeworkManager")
    public Result<List<CourseHomework>> homeworkManager(@RequestBody CourseManagerReq courseManagerReq) {
        List<CourseHomework> list = new ArrayList<>();
        return Result.success(list);
    }

    /**
     * 批量回复作业---回复作业
     *
     * @return
     */
    @ApiOperationSupport(order = 14)
    @ApiOperation(value = "批量回复作业与批改作业", notes = "批量回复作业与批改作业")
    @ApiImplicitParam(name = "batchReplyReq", value = "批量回复作业", required = true, dataType = "BatchReplyReq")
    @GetMapping(value = "/batchReply")
    public Result<Boolean> batchReply(@RequestBody BatchReplyReq batchReplyReq) {
        return Result.success();
    }

    /**
     * 删除作业
     *
     * @return
     */
    @ApiOperationSupport(order = 15)
    @ApiOperation(value = "删除作业", notes = "删除作业")
    @ApiImplicitParam(name = "homeworkId", value = "删除作业", required = true, dataType = "Integer")
    @GetMapping(value = "/deleteHomework")
    public Result<Boolean> deleteHomework(@RequestParam Integer homeworkId) {
        return Result.success();
    }
}
