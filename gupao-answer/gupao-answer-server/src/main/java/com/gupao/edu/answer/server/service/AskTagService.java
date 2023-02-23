package com.gupao.edu.answer.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.answer.server.entity.AskTag;
import com.gupao.edu.answer.server.entity.res.AskTagRes;
import java.util.List;

/**
 * @description
 * @author: chenlong
 * @data: 2020-04-11 20:37
 **/
public interface AskTagService extends IService<AskTag> {

    List<AskTagRes> findTagList(Integer taggableId, String taggableType);
}
