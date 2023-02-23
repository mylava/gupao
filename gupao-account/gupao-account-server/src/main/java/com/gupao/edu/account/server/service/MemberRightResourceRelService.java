package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.MemberRightResourceRel;
import com.gupao.edu.common.dict.entity.Dict;

import java.util.List;

/**
 * <p>
 * 会员权益资源关联表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
public interface MemberRightResourceRelService extends IService<MemberRightResourceRel> {

    /**
     * 获取会员权益字典信息列表
     * @return List<Dict>
     */
    List<Dict> selectDictForMemberRight();
}
