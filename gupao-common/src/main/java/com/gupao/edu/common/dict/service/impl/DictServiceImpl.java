package com.gupao.edu.common.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.common.dict.entity.Dict;
import com.gupao.edu.common.dict.mapper.DictMapper;
import com.gupao.edu.common.dict.service.DictService;
import com.gupao.edu.course.client.req.good.DictListReq;
import com.gupao.edu.course.client.resp.good.DictValueResp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据字典表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {


    @Override
    public List<DictValueResp> getDictValues(DictListReq gradeListReq) {
        return this.baseMapper.getDictValues(gradeListReq);
    }
}
