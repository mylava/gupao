package com.gupao.edu.answer.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gupao.edu.answer.server.entity.req.AnswerConditionReq;
import com.gupao.edu.answer.server.entity.req.AnswerReq;
import com.gupao.edu.answer.server.entity.req.OppositionReq;
import com.gupao.edu.answer.server.entity.req.SupportReq;
import com.gupao.edu.answer.server.entity.res.AnswerRes;
import com.gupao.edu.answer.server.service.AskAnswerService;
import com.gupao.edu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-22 21:11
 **/
@Api(value = "回答接口",tags = {"回答相关"})
@RestController
@RequestMapping("/answer")
public class AskAnswerController {

    @Autowired
    private AskAnswerService answerService;

    @ApiOperation(value = "回答")
    @PostMapping("answer")
    public Result answer(@RequestBody AnswerReq answerRes){
        answerService.answer(answerRes);
        return Result.success("回答问题成功");
    }

    @ApiOperation(value = "查询回答")
    @PostMapping("selectAnswer")
    public Result<IPage<AnswerRes>> selectAnswer(@RequestBody AnswerConditionReq answerConditionRes){
        return Result.success(answerService.selectAnswer(answerConditionRes));
    }


    @ApiOperation(value = "点赞")
    @PostMapping("support")
    public Result support(@RequestBody SupportReq supportReq){
        answerService.support(supportReq);
        return Result.success("操作成功");
    }

    @ApiOperation(value = "踩")
    @PostMapping("opposition")
    public Result opposition(@RequestBody OppositionReq oppositionReq){
        answerService.opposition(oppositionReq);
        return Result.success("操作成功");
    }
}
