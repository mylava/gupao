package com.gupao.edu.course.server.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.common.utils.CalculateProportionUtil;
import com.gupao.edu.course.client.entity.Course;
import com.gupao.edu.course.client.entity.UserCourseProgress;
import com.gupao.edu.course.client.req.center.*;
import com.gupao.edu.course.client.resp.CourseOutlineTreeResp;
import com.gupao.edu.course.client.resp.StudyCenterCourseDetailResp;
import com.gupao.edu.course.client.resp.center.*;
import com.gupao.edu.course.server.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * app学习模块
 *
 * @author zhangpan
 * @since 2020-03-18
 */
@Slf4j
@Api(tags = "学习模块")
@RestController
@RequestMapping("/coursestudy")
@Validated
public class GpStudyCourseController {

    /**
     * 用户 购买的课程服务
     */
    @Autowired
    private UserCourseService userCourseService;

    /**
     * 用户 课程 播放进度 表
     */
    @Autowired
    private UserCourseProgressService userCourseProgressService;


    /**
     * 课程大纲服务
     */
    @Autowired
    private CourseOutlineService courseOutlineService;
    /**
     * 问答服务
     */
    @Autowired
    private CourseVideoQuestionService courseVideoQuestionService;
    /**
     * 问题回答服务
     */
    @Autowired
    private CourseVideoQuestionReplyService courseVideoQuestionReplyService;
    /**
     * 点赞服务
     */
    @Autowired
    private CourseVideoQuestionReplyPraiseService courseVideoQuestionReplyPraiseService;
    /**
     * 评论回复
     */
    @Autowired
    private CourseVideoQuestionReplyCommentService courseVideoQuestionReplyCommentService;

    /**
     * 作业
     */
    @Autowired
    private CourseHomeworkService courseHomeworkService;

    /**
     * 课程 服务
     */
    @Autowired
    private CourseService courseService;

    /**
     * 学习中心
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "1.学习中心", notes = "学习中心")
    @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", dataType = "Integer")
    @PostMapping(value = "/centerList")
    public Result<CenterListResp> centerList(@Valid @RequestBody StudyCenterReq studyCenterReq) {
        //TODO userid 不需要，可以通过token获取 todo 后期修改为不用传入 用户唯一编码查询的吧
        // 通过用户id获取学习内容与课程列表
        // 查询用户购买的课程 及 课程信息 及
        // 课程数目 如果购买了很多 还是要分页
        log.info("学习中心页面: 接收参数: {}", studyCenterReq);
        String userUniqueCode = studyCenterReq.getUserUniqueCode();
        List<MyCourseResp> respList = userCourseService.listMyCourse(studyCenterReq);
        CenterListResp centerListResp = new CenterListResp();
        centerListResp.setMyCourse(respList);
        //设置角标
        centerListResp.setSize(respList.size());
        //总时长
        centerListResp.setTotalStudyTime(userCourseProgressService.computerTotalStudyTime(userUniqueCode, false));
        //今日学习
        centerListResp.setStudyTime(userCourseProgressService.computerTotalStudyTime(userUniqueCode, true));
        log.info("学习中心页面: 响应结果: {}", centerListResp);
        return Result.success(centerListResp);
    }
    /**
     * 根据CourseId获取录播课程大纲
     * ---带学习进度的（区别好课）
     **/
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "2.根据课程的ID获取课程大纲", notes = "根据课程的ID获取课程大纲")
    @PostMapping(value = "/orderOutlineList")
    public Result<StudyCenterCourseDetailResp> orderOutlineList(@Valid @RequestBody StudyCenterCourseDetailReq studyCenterCourseDetailReq) {
        // 1.根据courseId，查询课程大纲
        // 2.根据大纲parentId进行排序
        // 3.计算学习进度
        log.info("课程详情页面信息: 接收参数: {}", studyCenterCourseDetailReq);
        String userUniqueCode = studyCenterCourseDetailReq.getUserUniqueCode();
        Integer courseId = studyCenterCourseDetailReq.getCourseId();
        StudyCenterCourseDetailResp studyCenterCourseDetailResp = new StudyCenterCourseDetailResp();
        //1: 计算 课程学习总进度
        Long studyTime = userCourseProgressService.computerTotalStudyTime(userUniqueCode,false);
        log.info("课程详情页面信息: 计算学习时长: {} 秒", studyTime);
        Long totalCourseTime = courseOutlineService.computeCourseVedioTime(studyCenterCourseDetailReq.getCourseId());
        log.info("课程详情页面信息: 计算课程总时长: {} 秒", totalCourseTime);
        if(studyTime != null && totalCourseTime != null && totalCourseTime > 0) {
            studyCenterCourseDetailResp.setProgress(CalculateProportionUtil.proportionLong(studyTime, totalCourseTime));
//            studyCenterCourseDetailResp.setProgress(String.valueOf(((float) studyTime / totalCourseTime) * 100).toString().substring(0,2) + "%");
        }
        log.info("课程详情页面信息: 计算学习进度: {} ", studyCenterCourseDetailResp.getProgress());
        //2: 根据课程id 得到: 视频id  +  大纲id  + 视频id  + 课程名字 --> 从播放进度表中 获取
        //设置上次播放的大纲id  视频id : 根据课程id 查询 播放进度表 : 取第一条数据
        UserCourseProgress userCourseProgress = userCourseProgressService.getLastStudyVedioAndOutline(userUniqueCode,
                courseId);
        if(userCourseProgress != null) {
            studyCenterCourseDetailResp.setVideoId(userCourseProgress.getVideoId());
            studyCenterCourseDetailResp.setCourseOutlineId(userCourseProgress.getCourseOutlineId());
        }
        log.info("课程详情页面信息: 获取上次学到大纲视频: {} ", studyCenterCourseDetailResp.getVideoId());
        //3: 获取大纲列表 --> 从大纲表中获取  遍历大纲列表 : 获取节视频
        List<CourseOutlineTreeResp>  courseOutlineTreeResps = courseOutlineService.findOutLineCourseByCourseId(userUniqueCode, courseId);
        log.info("课程详情页面信息: 获取到大纲列表: {} ", courseOutlineTreeResps);
        studyCenterCourseDetailResp.setOutLineResps(courseOutlineTreeResps);
        //4: 设置课程名字
        Course course = courseService.getById(courseId);
        if(course != null) {
            studyCenterCourseDetailResp.setCourseName(course.getCourseName());
        }
        //异常在全局捕获了 所以在外面做处理看看 todo 异常后面再看
        return Result.success(studyCenterCourseDetailResp);
    }
    /**
     *
     * 获取视频大纲下
     * 大纲作业题目列表
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "3.大纲作业题目列表", notes = "大纲作业题目列表")
    @ApiImplicitParam(name = "outlineId", value = "大纲id", required = true, dataType = "Integer")
    @GetMapping(value = "/outLineHomeworkList")
    public Result<List<CourseHomeworkResp>> outLineHomeworkList(@RequestParam("outlineId")  Integer outlineId) {
        log.info("大纲作业题目列表,请求参数：{}",outlineId);
        List<CourseHomeworkResp> courseHomeworkRespList = courseHomeworkService.outLineHomeworkList(outlineId);
        log.info("大纲作业题目列表,响应参数：{}",courseHomeworkRespList);
        return Result.success(courseHomeworkRespList);
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "4.我的作业列表", notes = "我的作业列表")
    @PostMapping(value = "/myHomework")
    public Result<IPage<MyHomeworkResp>> myHomework(@RequestBody MyHomeworkPageReq myHomeworkPageReq) {
        log.info("大纲作业题目列表,请求参数：{}",myHomeworkPageReq);
        IPage<MyHomeworkResp> myHomeworkRespIPage = courseHomeworkService.myHomework(myHomeworkPageReq);
        log.info("大纲作业题目列表,响应参数：{}",myHomeworkRespIPage);
        return Result.success(myHomeworkRespIPage);
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "5.获取作业详情及其作业回答列表", notes = "获取作业详情及其作业回答列表")
    @PostMapping(value = "/homeworkDetails")
    public Result<HomeworkDetailResp> homeworkDetails(@RequestBody HomeworkDetailQueryReq homeworkDetailQueryReq) {
        log.info("获取作业详情及其作业回答列表,请求参数：{}",homeworkDetailQueryReq);
        try {
            HomeworkDetailResp homeworkDetailResp = courseHomeworkService.homeworkDetails(homeworkDetailQueryReq);
            log.info("获取作业详情及其作业回答列表,响应参数：{}",homeworkDetailResp);
            return Result.success(homeworkDetailResp);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取作业详情及其作业回答列表，抛出异常:{}",e.getMessage());
            return Result.fail(new CodeMessage(CodeMessage.COURSE_HOMEWORK_EXCEPTION.getCode(),e.getMessage()));
        }
    }

//    /**
//     * 上传作业
//     */
//    @ApiOperationSupport(order = 6)
//    @ApiOperation(value = "6.上传我的作业", notes = "上传我的作业")
//    @PostMapping(value = "/uploadMyHomework")
//    public Result<Boolean> uploadMyHomework(@RequestBody UploadMyHomeworkReq uploadMyHomeworkReq){
//        // CourseHomeworkReply
//        //TODO 1.提交作业  2.需要管理作业回复表，还要课程id，和作业id
//        // TODO 我的作业需要先关联作业的表--作业的内容可以带过来，存到作业回复表里面
//        return Result.success();
//    }

    /**
     * 作业评分
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "6.作业评分", notes = "作业评分")
    @PostMapping(value = "/scoreHomework")
    public Result<Boolean> scoreHomework(@RequestBody ScoreHomeworkReq scoreHomeworkReq){
        log.info("作业评分,请求参数：{}",scoreHomeworkReq);
        try {
            Boolean r  = courseHomeworkService.scoreHomework(scoreHomeworkReq);
            log.info("作业评分,响应参数：{}",r);
            return Result.success(r);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("作业评分，抛出异常:{}",e.getMessage());
            return Result.fail(new CodeMessage(CodeMessage.COURSE_SCORE_HOMEWORK_REPLY_EXCEPTION.getCode(),e.getMessage()));
        }
    }

    /**
     * 提交/修改我的作业
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "7.提交/修改我的作业", notes = "提交/修改我的作业")
    @PostMapping(value = "/updateMyHomework")
    public Result<Boolean> updateMyHomework(@RequestBody UpdateMyHomeworkReq updateMyHomeworkReq){
        log.info("提交/修改我的作业,请求参数：{}",updateMyHomeworkReq);
        try {
            Boolean r  = courseHomeworkService.updateMyHomework(updateMyHomeworkReq);
            log.info("提交/修改我的作业,响应参数：{}",r);
            return Result.success(r);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("提交/修改我的作业，抛出异常:{}",e.getMessage());
            return Result.fail(new CodeMessage(CodeMessage.COURSE_UPDATE_HOMEWORK_REPLY_EXCEPTION.getCode(),e.getMessage()));
        }
    }
    /**
     * 评论作业
     */
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "8.评论作业", notes = "评论作业")
    @PostMapping(value = "/homeworkComment")
    public Result<Boolean> homeworkComment(@RequestBody HomeworkCommentReq homeworkCommentReq){
        log.info("评论作业,请求参数：{}",homeworkCommentReq);
        try {
            Boolean r  = courseHomeworkService.homeworkComment(homeworkCommentReq);
            log.info("评论作业,响应参数：{}",r);
            return Result.success(r);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("评论作业，抛出异常:{}",e.getMessage());
            return Result.fail(new CodeMessage(CodeMessage.COURSE_COMMENT_HOMEWORK_REPLY_EXCEPTION.getCode(),e.getMessage()));
        }
    }
    /**
     * 作业评论的点赞与踩
     * @return boolean
     */
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "9.作业评论的点赞与踩", notes = "作业评论的点赞与踩")
    @PostMapping(value = "/homeworkReplyPraise")
    public Result<Boolean> homeworkReplyPraise(@RequestBody HomeworkReplyPraiseReq homeworkReplyPraiseReq) {
        log.info("作业评论的点赞与踩,请求参数：{}",homeworkReplyPraiseReq);
        try {
            Boolean r  = courseHomeworkService.homeworkReplyPraise(homeworkReplyPraiseReq);
            log.info("作业评论的点赞与踩,响应参数：{}",r);
            return Result.success(r);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("作业评论的点赞与踩，抛出异常:{}",e.getMessage());
            return Result.fail(new CodeMessage(CodeMessage.COURSE_PRAISE_HOMEWORK_REPLY_EXCEPTION.getCode(),e.getMessage()));
        }
    }
    /**
     * 获取我的答疑列表--获取视频问答列表（2合1）
     * @GetMapping(value = "/getUserAskList")
     * 问答入口 在哪一块
     * 我的答疑 + 答疑列表 + 答疑详情 完成 20200406
     * 新增 答疑的评论及回复 单表新增操作 完成 20200406
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "10.获取全部答疑以及获取我的答疑", notes = "获取全部答疑,获取我的答疑")
    @ApiImplicitParam(name = "userAskReqe", value = "获取答疑列表", required = true, dataType = "UserAskReq")
    @PostMapping(value = "/userAskList")
    public Result<IPage<CourseVedioQuestionResp>> userAskList(@RequestBody UserAskReq userAskReqe) {
        if(userAskReqe.getVideoId() == null  || userAskReqe.getVideoId() < 1){
            return Result.success();
        }
        IPage<CourseVedioQuestionResp> page = courseVideoQuestionService.findQuestionPage(userAskReqe);
        return Result.success(page);
    }
    /**
     * 获取问答详情
     * @GetMapping(value = "/getUserAskList")
     * 获取该问题下所有的详情
     */
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "11.获取问答详情", notes = "获取问答详情")
    @PostMapping(value = "/userAskDetails")
    public Result<AskAnsweResp> userAskDetails(@RequestBody QuestionReplyQueryReq req) {
        if(req.getQuestionId() == null || req.getQuestionId() < 1){
            return Result.success();
        }
        return Result.success(courseVideoQuestionService.findQuestionInfoById(req));
    }

    /**
     * 提问
     */
    @ApiOperationSupport(order = 12)
    @ApiOperation(value = "12.提问", notes = "提问")
    @PostMapping(value = "/publishMyQuestion")
    public Result<Boolean> publishMyQuestion(@Valid @RequestBody CourseVedioQuestionReq req){
        courseVideoQuestionService.addCourseAsk(req);
        return Result.success();
    }

    /**
     * 修改提问
     */
    @ApiOperationSupport(order = 13)
    @ApiOperation(value = "13.修改提问", notes = "修改提问")
    @ApiImplicitParam(name = "courseVedioQuestionReq", value = "发布问题实体", required = true, dataType = "CourseVedioQuestionReq")
    @PostMapping(value = "/updateMyQuestion")
    public Result<Boolean> updateMyQuestion(@RequestBody CourseVedioQuestionReq courseVedioQuestionReq){
        //TODO 修改提问
        return Result.success();
    }

    /**
     * 针对提问进行回复
     */
    @ApiOperationSupport(order = 14)
    @ApiOperation(value = "14.回复问答", notes = "回复问答")
    @ApiImplicitParam(name = "courseVedioReplyReq", value = "发布回复实体", required = true, dataType = "CourseVedioReplyReq")
    @PostMapping(value = "/publishMyReply")
    public Result<Boolean> publishMyReply(@Valid @RequestBody CourseVedioReplyReq courseVedioReplyReq){
        courseVideoQuestionReplyService.addReply(courseVedioReplyReq);
        return Result.success();
    }
    /**
     * 修改回复
     */
    @ApiOperationSupport(order = 15)
    @ApiOperation(value = "15.修改回复", notes = "修改回复")
    @ApiImplicitParam(name = "courseVedioReplyReq", value = "发布回复实体", required = true, dataType = "CourseVedioReplyReq")
    @PostMapping(value = "/updateMyReply")
    public Result<Boolean> updateMyReply(@RequestBody CourseVedioReplyReq courseVedioReplyReq){
        //TODO 修改回复  洪都自己确认一下
        return Result.success();
    }
    /**
     * 发布一个评论 ：针对评论进行回复，允许互评
     */
    @ApiOperationSupport(order = 16)
    @ApiOperation(value = "16.评论回答", notes = "评论回答")
    @ApiImplicitParam(name = "courseVedioReplyCommentReq", value = "发布评论实体", required = true, dataType = "CourseVedioReplyCommentReq")
    @PostMapping(value = "/publishMyComment")
    public Result<Boolean> publishMyComment(@Valid @RequestBody CourseVedioReplyCommentReq courseVedioReplyCommentReq){
        courseVideoQuestionReplyCommentService.addComment(courseVedioReplyCommentReq);
        return Result.success();
    }
    /**
     * 发布一个评论 ：
     * 修改评论
     */
    @ApiOperationSupport(order = 17)
    @ApiOperation(value = "17.修改评论", notes = "修改评论")
    @ApiImplicitParam(name = "courseVedioReplyCommentReq", value = "发布评论实体", required = true, dataType = "CourseVedioReplyCommentReq")
    @PostMapping(value = "/updateMyComment")
    public Result<Boolean> updateMyComment(@RequestBody CourseVedioReplyCommentReq courseVedioReplyCommentReq){
        return Result.success();
    }


    /**
     * 问答的点赞与踩
     * @return boolean
     */
    @ApiOperationSupport(order = 18)
    @ApiOperation(value = "18.问答的点赞与踩", notes = "问答的点赞与踩")
    @ApiImplicitParam(name = "questionReplyPraiseReq", value = "问答的点赞与踩实体", required = true, dataType = "QuestionReplyPraiseReq")
    @PostMapping(value = "/questionReplyPraise")
    public Result<Boolean> questionReplyPraise(@Valid @RequestBody QuestionReplyPraiseReq req) {
        courseVideoQuestionReplyPraiseService.questionReplyPraise(req);
        return Result.success();
    }


    /**
     * 播放进度管理
     * @param courseOutLineId
     * @return
     */
    @ApiOperationSupport(order = 19)
    @ApiOperation(value = "19.播放进度管理", notes = "播放进度管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseOutLineId", value = "课程大纲id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "playTime", value = "播放视频多长时间", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/playProgress")
    public Result<UserCourseProgress> playProgress(@RequestParam Integer courseOutLineId,Integer playTime) {
        //TODO 1.通过视频id 和当前用户 获取当前用户的播放进度，如果没有播放，则新建一条播放进度为0的数据
        return Result.success(new UserCourseProgress());
    }
    /**
     * 我购买的课程---课程中心使用
     * 通过fegion调用
     * @return
     */
    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "20.我购买的课程列表", notes = "我购买的课程列表")
    @ApiImplicitParam(name = "userId", value = "当前用户id", required = true, dataType = "Integer")
    @GetMapping(value = "/userBoughtcourses")
    public Result<MyCourseResp> userBoughtcourses(@RequestParam("userId") Integer userId) {
        //TODO 获取我的课表列表，根据我购买的订单 生产userCourse表
        // 返回值定义
        return Result.success(new MyCourseResp());

    }

















}
