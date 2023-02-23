package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.MemberRightGainHistory;
import com.gupao.edu.account.client.req.BasePageReq;
import com.gupao.edu.account.client.resp.GainHistoryResp;
import com.gupao.edu.account.server.mapper.MemberRightGainHistoryMapper;
import com.gupao.edu.account.server.service.MemberRightGainHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权益领取记录表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
@Service
public class MemberRightGainHistoryServiceImpl extends ServiceImpl<MemberRightGainHistoryMapper, MemberRightGainHistory> implements MemberRightGainHistoryService {

    /**
     * 获取最后一条会员权益领取信息
     * @param userUniqueCode 用户唯一编码
     * @param memberRightId 关联[会员权益字典]的权益ID
     * @return MemberRightGainHistory
     */
    @Override
    public MemberRightGainHistory getLastMemberRightGainHistory(String userUniqueCode,Integer memberRightId){
        Map<String,Object> map = new HashMap<>();
        map.put("user_unique_code",userUniqueCode);
        map.put("member_right_id",memberRightId);;
        List<MemberRightGainHistory> memberRightGainHistoryList = baseMapper.selectList(new QueryWrapper<MemberRightGainHistory>().allEq(map).orderByDesc("create_time"));
        if (null != memberRightGainHistoryList && memberRightGainHistoryList.size()> 0){
            return memberRightGainHistoryList.get(0);
        }
        return null;
    }

    /**
     *  判断用户是否可以领取会员权益
     * @param userUniqueCode  用户唯一编码
     * @param memberRightId 关联[会员权益字典]的权益ID
     * @return Boolean
     * @throws Exception 抛出异常
     */
    @Override
    public Boolean whetherReceive(String userUniqueCode, Integer memberRightId) throws Exception{
        MemberRightGainHistory lastHistory = this.getLastMemberRightGainHistory(userUniqueCode,memberRightId);
        if (null != lastHistory){
            LocalDateTime lastTime = lastHistory.getCreateTime();
            if(lastTime.isAfter(LocalDateTime.now().minusMonths(3))) {
                throw new Exception("每季度只能领一次");
            }
        }
        return true;
    }

    /**
     *  领取会员权益时新增一条权益领取记录
     * @param userUniqueCode  用户唯一编码
     * @param memberRightId 关联[会员权益字典]的权益ID
     * @param memberId 会员ID
     * @param userAddressId 用户地址ID
     * @return Boolean
     */
    @Override
    public Boolean updateForReceiveRight(
            String userUniqueCode,Integer memberRightId,Integer memberId,Integer userAddressId){
        LocalDateTime now = LocalDateTime.now();
        MemberRightGainHistory memberRightGainHistory = new MemberRightGainHistory();
        memberRightGainHistory.setUserUniqueCode(userUniqueCode);
        memberRightGainHistory.setMemberRightId(memberRightId);
        memberRightGainHistory.setResourceId(0);//20200426 现阶段空着就行
        memberRightGainHistory.setCategoryId(0);
        memberRightGainHistory.setCategoryCode("0000");
        memberRightGainHistory.setMemberId(memberId);
        memberRightGainHistory.setUserAddressId(userAddressId);
        memberRightGainHistory.setDeliveryState(1);
        return this.save(memberRightGainHistory);
    }

    /**
     *  查看会员权益领取记录
     * @param basePageReq 分页实体
     * @return IPage<GainHistoryResp>
     */
    @Override
    public IPage<GainHistoryResp> selectGainHistoryPage(BasePageReq basePageReq){
        Page<GainHistoryResp> page = new Page<>(basePageReq.getPage(),basePageReq.getPageNum());
        return page.setRecords(baseMapper.selectGainHistoryList(page, basePageReq.getUserUniqueCode()));
    }

}
