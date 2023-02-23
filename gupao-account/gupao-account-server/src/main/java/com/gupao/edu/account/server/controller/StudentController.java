package com.gupao.edu.account.server.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.account.client.req.EntranceReq;
import com.gupao.edu.account.client.resp.AgreementResp;
import com.gupao.edu.account.client.resp.CardResp;
import com.gupao.edu.account.client.resp.RecommendSalesmanResp;
import com.gupao.edu.account.client.resp.SchoolRollResp;
import com.gupao.edu.account.server.service.StudentService;
import com.gupao.edu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * comment:
 *
 * @author lipengfei
 * @date 15/04/2020
 */
@Api(tags = {"学籍相关功能"})
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "1.我的学籍", notes = "我的学籍")
    @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String")
    @PostMapping(value = "/schoolRoll")
    public Result<SchoolRollResp> schoolRoll(@RequestParam(value = "userUniqueCode") String userUniqueCode) {
        return Result.success(studentService.schoolRoll(userUniqueCode));
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "2.我的黑卡", notes = "我的黑卡")
    @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String")
    @PostMapping(value = "/card")
    public Result<CardResp> card(@RequestParam(value = "userUniqueCode") String userUniqueCode) {
        return Result.success(studentService.card(userUniqueCode));
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "3.推荐码获取推荐老师", notes = "推荐码获取推荐老师")
    @ApiImplicitParam(name = "recommendCode", value = "老师推荐码", required = true, dataType = "String")
    @PostMapping(value = "/recommendSalesman")
    public Result<RecommendSalesmanResp> recommendSalesman(@RequestParam(value = "recommendCode") String recommendCode) {
        return Result.success(studentService.recommendSalesman(recommendCode));
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "4.办理入学", notes = "办理入学")
    @ApiImplicitParam(name = "entranceReq", value = "办理入学参数", required = true, dataType = "EntranceReq")
    @PostMapping(value = "/entrance")
    public Result<String> entrance(@RequestBody EntranceReq entranceReq) {
        return studentService.entrance(entranceReq);
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "5.获取服务协议", notes = "获取服务协议")
    @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String")
    @PostMapping(value = "/agreement")
    public Result<AgreementResp> agreement(@RequestParam(value = "userUniqueCode") String userUniqueCode) {
        return Result.success(studentService.getAgreement(userUniqueCode));
    }


    @GetMapping("/test")
    public Result<String> test(){
        return Result.success(studentService.test());
    }
}
