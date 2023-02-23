package com.gupao.edu.answer.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.answer.server.entity.AskQuestion;
import com.gupao.edu.answer.server.entity.req.AcceptQuestionReq;
import com.gupao.edu.answer.server.entity.req.QuestionConditionReq;
import com.gupao.edu.answer.server.entity.req.QuestionReq;
import com.gupao.edu.answer.server.entity.res.QuestionDetailRes;
import com.gupao.edu.answer.server.entity.res.QuestionRes;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-21 19:48
 **/
public interface AskQuestionService extends IService<AskQuestion> {

    void addQuestion(QuestionReq questionRes);

    QuestionDetailRes selectQuestionsById(Integer id);

    void acceptQuestion(AcceptQuestionReq acceptQuestionReq);

    IPage<QuestionRes> selectQuestionsByPage(QuestionConditionReq questionConditionRes);
}
