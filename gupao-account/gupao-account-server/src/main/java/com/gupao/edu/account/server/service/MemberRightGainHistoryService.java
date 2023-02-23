package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.MemberRightGainHistory;
import com.gupao.edu.account.client.req.BasePageReq;
import com.gupao.edu.account.client.resp.GainHistoryResp;

/**
 * <p>
 * 权益领取记录表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
public interface MemberRightGainHistoryService extends IService<MemberRightGainHistory> {
    /**
     * 获取最后一条会员权益领取信息
     * @param userUniqueCode 用户唯一编码
     * @param memberRightId 关联[会员权益字典]的权益ID
     * @return MemberRightGainHistory
     */
    MemberRightGainHistory getLastMemberRightGainHistory(String userUniqueCode,Integer memberRightId);

    /**
     *  判断用户是否可以领取会员权益
     * @param userUniqueCode  用户唯一编码
     * @param memberRightId 关联[会员权益字典]的权益ID
     * @return Boolean
     * @throws Exception 抛出异常
     */
    Boolean whetherReceive(String userUniqueCode, Integer memberRightId) throws Exception;

    /**
     *  领取会员权益时新增一条权益领取记录
     * @param userUniqueCode  用户唯一编码
     * @param memberRightId 关联[会员权益字典]的权益ID
     * @param memberId 会员ID
     * @param userAddressId 用户地址ID
     * @return Boolean
     */
    Boolean updateForReceiveRight(String userUniqueCode,Integer memberRightId,Integer memberId,Integer userAddressId);

    /**
     * 查看会员权益领取记录
     * @param basePageReq 分页实体
     * @return IPage<GainHistoryResp>
     */
    IPage<GainHistoryResp> selectGainHistoryPage(BasePageReq basePageReq);

}
