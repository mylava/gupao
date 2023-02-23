package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.Member;
import com.gupao.edu.account.client.req.GainResourceReq;
import com.gupao.edu.account.client.resp.MemberRightResourceVO;
import com.gupao.edu.account.client.resp.MemberRightsResp;
import com.gupao.edu.account.client.resp.UserHomeResp;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
public interface MemberService extends IService<Member> {

    /**
     * 获取会员信息
     * @param userUniqueCode 用户唯一编码
     * @return Member
     */
    Member selectOneMember(String userUniqueCode);

    /**
     * 判断会员是否有效
     * @param userUniqueCode 用户唯一编码
     * @return Member
     * @throws Exception 抛出异常
     */
    Member whetherInvalid(String userUniqueCode) throws Exception;

    /**
     * 领取会员权益
     * @param gainResouceReq 领取会员权益请求参数
     * @return Boolean
     * @throws Exception 抛出异常
     */
    Boolean gainResource(GainResourceReq gainResouceReq) throws Exception;
    /**
     * 展示会员权益
     * @param userUniqueCode 用户唯一编码
     * @return MemberRightsResp
     */
    MemberRightsResp memberRights(String userUniqueCode);

    /**
     * 查看书籍详情
     * @param resourceId 资源ID
     * @return MemberRightResourceVO
     */
    MemberRightResourceVO memberRightResourceDetail(Integer resourceId);

    /**
     * 根据用户编码查询会员信息
     * @param resp
     * @param userUniqueCode
     * @return UserHomeResp
     */
    UserHomeResp setMemberInfo(UserHomeResp resp, String userUniqueCode);

    /**
     * 开通/续费会员
     * @param userUniqueCode 用户唯一编码
     * @return Boolean
     */
    Boolean openMember(String userUniqueCode);
}
