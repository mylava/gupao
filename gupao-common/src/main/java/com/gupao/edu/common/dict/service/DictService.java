package com.gupao.edu.common.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.common.dict.entity.Dict;
import com.gupao.edu.course.client.req.good.DictListReq;
import com.gupao.edu.course.client.resp.good.DictValueResp;

import java.util.List;

/**
 * <p>
 * 数据字典表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface DictService extends IService<Dict> {


    /**
     * 根据code和id获取 字典类型
     * @return
     * @param gradeListReq
     */
    List<DictValueResp> getDictValues(DictListReq gradeListReq);
}
