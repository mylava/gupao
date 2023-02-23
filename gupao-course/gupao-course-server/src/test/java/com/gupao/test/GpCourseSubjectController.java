package com.gupao.test;/**
 * Created by dudu on 2020/3/20.
 */

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.course.server.service.CourseSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h3>gupao-parent</h3>
 * <p>课程 学科查询</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-03-20 12:24
 **/
@Api(tags = "学科模块")
@RestController
@RequestMapping("/gpCourseSubject")
public class GpCourseSubjectController {

    @Autowired
    private CourseSubjectService service;

    /**
     * 查询所有学科 : 对外的url尽量简单
     * @param
     * @return 公共的结果封装对象
     */
//    @ApiOperationSupport(order = 1)
//    @ApiOperation(value = "学科查询", notes = "学科查询")
//    @GetMapping(value = "/list")
//    public Result listCourseSubject(CourseSubjectReq subjectReq) {
//        List<CourseSubject> subjectList = service.listCourseSubject(subjectReq);
//        PageInfo<GpCourseSubject> pageInfo = new PageInfo<>(subjectList);
//        return Result.pagination(pageInfo);
//    }


}
