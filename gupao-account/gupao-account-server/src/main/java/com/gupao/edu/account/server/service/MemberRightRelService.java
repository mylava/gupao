package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.MemberRightRel;

/**
 * <p>
 * 会员与权益关联表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
public interface MemberRightRelService extends IService<MemberRightRel> {

    /**
     * 获取会员与权益关联信息
     * @param userUniqueCode 用户唯一编码
     * @param memberRightId 字典id
     * @return MemberRightRel
     * @throws Exception 抛出异常
     */
    MemberRightRel selectByUserUniqueMemberRightId(String userUniqueCode,Integer memberRightId) throws Exception;

    /**
     * 领取会员权益时更新会员权益关联信息
     * @param userUniqueCode 用户唯一编码
     * @param memberRightId 字典id
     * @return Boolean
     * @throws Exception 抛出异常
     */
    Boolean updateForReceiveRight(String userUniqueCode, Integer memberRightId) throws Exception;
}
