package com.gupao.edu.answer.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.answer.server.entity.AskTag;
import com.gupao.edu.answer.server.entity.res.AskTagRes;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description
 * @author: chenlong
 * @data: 2020-04-11 20:39
 **/
@Mapper
public interface AskTagMapper extends BaseMapper<AskTag> {

    List<AskTagRes> findTagList(@Param("taggableId") Integer taggableId,@Param("taggableType") String taggableType);
}
