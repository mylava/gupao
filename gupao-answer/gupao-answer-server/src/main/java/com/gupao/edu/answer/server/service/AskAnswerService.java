package com.gupao.edu.answer.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.answer.server.entity.AskAnswer;
import com.gupao.edu.answer.server.entity.req.AnswerConditionReq;
import com.gupao.edu.answer.server.entity.req.AnswerReq;
import com.gupao.edu.answer.server.entity.req.OppositionReq;
import com.gupao.edu.answer.server.entity.req.SupportReq;
import com.gupao.edu.answer.server.entity.res.AnswerRes;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-22 21:13
 **/
public interface AskAnswerService extends IService<AskAnswer> {

    void answer(AnswerReq answerRes);

    IPage<AnswerRes> selectAnswer(AnswerConditionReq answerConditionRes);

    void support(SupportReq supportReq);

    void opposition(OppositionReq oppositionReq);
}
