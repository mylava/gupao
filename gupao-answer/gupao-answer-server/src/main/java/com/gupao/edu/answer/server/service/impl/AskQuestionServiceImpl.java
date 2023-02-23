package com.gupao.edu.answer.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.answer.server.dao.AskQuestionMapper;
import com.gupao.edu.answer.server.entity.AskAnswer;
import com.gupao.edu.answer.server.entity.AskQuestion;
import com.gupao.edu.answer.server.entity.AskTaggables;
import com.gupao.edu.answer.server.entity.req.AcceptQuestionReq;
import com.gupao.edu.answer.server.entity.req.QuestionConditionReq;
import com.gupao.edu.answer.server.entity.req.QuestionReq;
import com.gupao.edu.answer.server.entity.res.AskTagRes;
import com.gupao.edu.answer.server.entity.res.QuestionDetailRes;
import com.gupao.edu.answer.server.entity.res.QuestionRes;
import com.gupao.edu.answer.server.enums.SourceType;
import com.gupao.edu.answer.server.service.AskAnswerService;
import com.gupao.edu.answer.server.service.AskQuestionService;
import com.gupao.edu.answer.server.service.AskTagService;
import com.gupao.edu.answer.server.service.AskTaggablesService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-21 19:48
 **/
@Service
public class AskQuestionServiceImpl extends ServiceImpl<AskQuestionMapper, AskQuestion> implements AskQuestionService{


    @Autowired
    private AskQuestionMapper askQuestionDao;
    @Autowired
    private AskAnswerService answerService;
    @Autowired
    private AskTaggablesService taggablesService;
    @Autowired
    private AskTagService tagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addQuestion(QuestionReq questionRes) {
        AskQuestion question = new AskQuestion();
        checkQuestion(questionRes);
        BeanUtils.copyProperties(questionRes,question);
        askQuestionDao.insert(question);
        //扣减咕泡币 todo
        //添加标签
        List<AskTaggables> taggablesList = new ArrayList<>();
        AskTaggables taggables;
        for (Integer tagId : questionRes.getTagIds()) {
            taggables = new AskTaggables();
            taggables.setTagId(tagId);
            taggables.setCreatedAt(LocalDateTime.now());
            taggables.setTaggableId(question.getId());
            taggables.setTaggableType(SourceType.SOURCETYPE_QUESTION.getCode());
            taggablesList.add(taggables);
        }
        taggablesService.saveBatch(taggablesList);
    }

    @Override
    public QuestionDetailRes selectQuestionsById(Integer id) {
        QuestionDetailRes detailRes = new QuestionDetailRes();
        AskQuestion askQuestion = askQuestionDao.selectById(id);
        BeanUtils.copyProperties(askQuestion,detailRes);
        //查询标签列表
        List<AskTagRes> tags = tagService.findTagList(askQuestion.getId(),SourceType.SOURCETYPE_QUESTION.getCode());
        detailRes.setTags(tags);
        return detailRes;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void acceptQuestion(AcceptQuestionReq acceptQuestionReq) {
        //判断登录人是否是提问的人 todo

        //修改问题的状态为已采纳
        AskQuestion question = askQuestionDao.selectById(acceptQuestionReq.getQuestionId());
        question.setStatus(2);
        //修改回答的状态为已采纳
        AskAnswer askAnswer = answerService.getById(acceptQuestionReq.getAnswerId());
        question.setAnswers(askAnswer.getId());
        askQuestionDao.updateById(question);

        askAnswer.setStatus(1);
        answerService.updateById(askAnswer);
    }

    @Override
    public IPage<QuestionRes> selectQuestionsByPage(QuestionConditionReq questionConditionRes) {
        IPage<AskQuestion> page = new Page<>(questionConditionRes.getPageNum(), questionConditionRes.getPageSize());
        QueryWrapper<AskQuestion> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("created_at");
        IPage<AskQuestion> askQuestionPage = baseMapper.selectPage(page, wrapper);
        return null;
    }

    private void checkQuestion(QuestionReq questionRes){
        //CodeMessage里面加 500701开始
        if(StringUtils.isEmpty(questionRes.getTitle())){
            throw new RuntimeException("问答标题不能为空");
        }
        if(StringUtils.isEmpty(questionRes.getDescription())){
            throw new RuntimeException("问答内容不能为空");
        }
        if(questionRes.getPrice() == null || questionRes.getPrice() == 0){
            throw new RuntimeException("支付费用必须大于0");
        }
    }
}
