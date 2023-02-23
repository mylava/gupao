package com.gupao.edu.account.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.account.client.entity.MemberRightResourceRel;
import com.gupao.edu.common.dict.entity.Dict;

import java.util.List;

/**
 * <p>
 * 会员权益资源关联表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
public interface MemberRightResourceRelMapper extends BaseMapper<MemberRightResourceRel> {

    /**
     * 获取会员权益的字典信息
     * @return
     */
    List<Dict> selectDictForMemberRight();
}
