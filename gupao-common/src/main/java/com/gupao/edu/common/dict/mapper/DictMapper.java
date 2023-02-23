package com.gupao.edu.common.dict.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.common.dict.entity.Dict;
import com.gupao.edu.course.client.req.good.DictListReq;
import com.gupao.edu.course.client.resp.good.DictValueResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 数据字典表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Mapper
@Repository
public interface DictMapper extends BaseMapper<Dict> {

	List<DictValueResp> getDictValues(DictListReq gradeListReq);

	String getValueByName(String agreementUrl);
}
