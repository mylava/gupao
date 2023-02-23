package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.MemberRightResource;
import com.gupao.edu.account.server.mapper.MemberRightResourceMapper;
import com.gupao.edu.account.server.service.MemberRightResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 会员权益资源表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
@Service
public class MemberRightResourceServiceImpl extends ServiceImpl<MemberRightResourceMapper, MemberRightResource> implements MemberRightResourceService {

    @Override
    @Transactional
    public Boolean updateForReceiveRight(Integer memberRightResourceId) throws Exception{
        MemberRightResource memberRightResource = this.getById(memberRightResourceId);
        if (null == memberRightResource){
            throw new Exception("没有找到该会员权益资源信息");
        }
        //会员权益资源信领取人数+1
        memberRightResource.setReceiveNum(memberRightResource.getReceiveNum() + 1);
        return this.updateById(memberRightResource);
    }

    /**
     * 根据权益ID获取权益资源信息集
     * @param rightId 权益ID（字典ID）
     * @return List<MemberRightResource>
     */
    @Override
    public List<MemberRightResource> selectMemberRightResourceByRightId(Integer rightId) {
        return baseMapper.selectMemberRightResourceByRightId(rightId);
    }

}
