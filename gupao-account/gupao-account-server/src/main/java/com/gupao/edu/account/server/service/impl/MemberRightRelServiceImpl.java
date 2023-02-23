package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.MemberRightRel;
import com.gupao.edu.account.server.mapper.MemberRightRelMapper;
import com.gupao.edu.account.server.service.MemberRightRelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员与权益关联表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
@Service
public class MemberRightRelServiceImpl extends ServiceImpl<MemberRightRelMapper, MemberRightRel> implements MemberRightRelService {

    @Override
    public MemberRightRel selectByUserUniqueMemberRightId(String userUniqueCode,Integer memberRightId) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("user_unique_code",userUniqueCode);
        map.put("member_right_id",memberRightId);
        return baseMapper.selectOne(new QueryWrapper<MemberRightRel>().allEq(map));
    }

    @Override
    @Transactional
    public Boolean updateForReceiveRight(String userUniqueCode, Integer memberRightId) throws Exception{
        MemberRightRel memberRightRel = this.selectByUserUniqueMemberRightId(userUniqueCode,memberRightId);
        if (null == memberRightRel){
            throw new Exception("没有该项权益");
        }
        LocalDate invalidTime = memberRightRel.getInvalidTime();
        //判断权益过期时间
        if (null != invalidTime){
            if(invalidTime.isBefore(LocalDate.now())){
                throw new Exception("已失效");
            }
        }
        //判断是否已领取完
        if (memberRightRel.getGainLimit() <= memberRightRel.getGainTimes()){
            throw new Exception("已领完");
        }
        memberRightRel.setGainTimes(memberRightRel.getGainTimes()+1);
        return this.saveOrUpdate(memberRightRel);
    }
}
