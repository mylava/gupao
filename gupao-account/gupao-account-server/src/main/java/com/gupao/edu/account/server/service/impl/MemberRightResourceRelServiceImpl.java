package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.MemberRightResourceRel;
import com.gupao.edu.account.server.mapper.MemberRightResourceRelMapper;
import com.gupao.edu.account.server.service.MemberRightResourceRelService;
import com.gupao.edu.common.dict.entity.Dict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员权益资源关联表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
@Service
public class MemberRightResourceRelServiceImpl extends ServiceImpl<MemberRightResourceRelMapper, MemberRightResourceRel> implements MemberRightResourceRelService {

    @Override
    public List<Dict> selectDictForMemberRight(){
        return baseMapper.selectDictForMemberRight();
    }
}
