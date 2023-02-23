package com.gupao.edu.answer.server.service.impl;

import static com.gupao.edu.answer.server.enums.SourceType.SOURCETYPE_ANSWER;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.answer.client.exception.BaseRuntimeException;
import com.gupao.edu.answer.server.dao.AskAnswerMapper;
import com.gupao.edu.answer.server.entity.AskAnswer;
import com.gupao.edu.answer.server.entity.req.AnswerConditionReq;
import com.gupao.edu.answer.server.entity.req.AnswerReq;
import com.gupao.edu.answer.server.entity.req.OppositionReq;
import com.gupao.edu.answer.server.entity.req.SupportReq;
import com.gupao.edu.answer.server.entity.res.AnswerRes;
import com.gupao.edu.answer.server.service.AskAnswerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-22 21:13
 **/
@Service
public class AskAnswerServiceImpl extends ServiceImpl<AskAnswerMapper, AskAnswer> implements AskAnswerService {

    @Autowired
    private AskAnswerMapper answerDao;

    @Override
    public void answer(AnswerReq answerRes) {
        if(StringUtils.isEmpty(answerRes.getContent())){
            throw new BaseRuntimeException("","");
        }
        AskAnswer answer = new AskAnswer();
        BeanUtils.copyProperties(answerRes,answer);
        //获取用户信息 todo
        answerDao.insert(answer);
    }

    @Override
    public IPage<AnswerRes> selectAnswer(AnswerConditionReq answerConditionRes) {
        IPage<AskAnswer> page = new Page<>(answerConditionRes.getPageNum(), answerConditionRes.getPageSize());
        QueryWrapper<AskAnswer> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("created_at");
        IPage<AskAnswer> askQuestionPage = baseMapper.selectPage(page, wrapper);
        return null;
    }

    @Override
    public void support(SupportReq supportReq) {
        //回答点赞
        if(SOURCETYPE_ANSWER.getCode().equals(supportReq.getSourceType())){
            AskAnswer askAnswer = baseMapper.selectById(supportReq.getSourceId());
            //有张表 ask_supports
        }
    }

    @Override
    public void opposition(OppositionReq oppositionReq) {
        //回答踩
        if(SOURCETYPE_ANSWER.getCode().equals(oppositionReq.getSourceType())){
            //新建表
        }
    }

}
