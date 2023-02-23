package com.gupao.edu.answer.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gupao.edu.answer.server.entity.req.*;
import com.gupao.edu.answer.server.entity.res.QuestionRes;
import com.gupao.edu.answer.server.entity.res.QuestionDetailRes;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.answer.server.service.AskQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-17 21:22
 **/
@RestController
@RequestMapping("/question")
@Api(value = "问答接口",tags = {"问题相关"})
public class AskQuestionController {

    @Autowired
    private AskQuestionService questionService;

    @ApiOperation(value = "新增问题")
    @PostMapping("/addQuestion")
    public Result addQuestion(@RequestBody QuestionReq questionRes){
        questionService.addQuestion(questionRes);
        return Result.success("新增问题成功");
    }

    @ApiOperation(value = "问题分页查询")
    @PostMapping("selectQuestionsByPage")
    public Result<IPage<QuestionRes>> selectQuestionsByPage(@RequestBody QuestionConditionReq questionConditionRes){
        return Result.success(questionService.selectQuestionsByPage(questionConditionRes));
    }

    @ApiOperation(value = "问题查询详情")
    @GetMapping("selectQuestionsById")
    public Result<QuestionDetailRes> selectQuestionsById(@RequestParam(value = "id") Integer id){
        return Result.success(questionService.selectQuestionsById(id));
    }

    @ApiOperation(value = "采纳回答")
    @PostMapping("acceptQuestion")
    public Result acceptQuestion(@RequestBody AcceptQuestionReq acceptQuestionReq){
        questionService.acceptQuestion(acceptQuestionReq);
        return Result.success("采纳回答成功");
    }
}
