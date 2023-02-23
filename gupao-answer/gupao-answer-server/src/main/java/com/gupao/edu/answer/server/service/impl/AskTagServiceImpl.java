package com.gupao.edu.answer.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.answer.server.dao.AskTagMapper;
import com.gupao.edu.answer.server.entity.AskTag;
import com.gupao.edu.answer.server.entity.res.AskTagRes;
import com.gupao.edu.answer.server.service.AskTagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author: chenlong
 * @data: 2020-04-11 20:38
 **/
@Service
public class AskTagServiceImpl extends ServiceImpl<AskTagMapper, AskTag> implements AskTagService {

    @Autowired
    private AskTagMapper tagMapper;

    @Override
    public List<AskTagRes> findTagList(Integer taggableId, String taggableType) {
        return tagMapper.findTagList(taggableId,taggableType);
    }
}
