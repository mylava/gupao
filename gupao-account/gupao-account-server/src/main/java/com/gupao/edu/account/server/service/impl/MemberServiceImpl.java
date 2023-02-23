package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.Member;
import com.gupao.edu.account.client.entity.MemberRightResource;
import com.gupao.edu.account.client.entity.User;
import com.gupao.edu.account.client.req.GainResourceReq;
import com.gupao.edu.account.client.resp.MemberRightResourceVO;
import com.gupao.edu.account.client.resp.MemberRightVO;
import com.gupao.edu.account.client.resp.MemberRightsResp;
import com.gupao.edu.account.client.resp.UserHomeResp;
import com.gupao.edu.account.server.mapper.MemberMapper;
import com.gupao.edu.account.server.service.*;
import com.gupao.edu.common.dict.entity.Dict;
import com.gupao.edu.common.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {


    @Autowired
    private UserService userService;

    @Autowired
    private MemberRightResourceService memberRightResourceService;

    @Autowired
    private MemberRightRelService memberRightRelService;

    @Autowired
    private MemberRightGainHistoryService memberRightGainHistoryService;

    @Autowired
    private MemberRightResourceRelService memberRightResourceRelService;

    /**
     * 获取会员信息
     * @param userUniqueCode 用户唯一编码
     * @return Member
     */
    @Override
    public Member selectOneMember(String userUniqueCode){
        Map<String,Object> map = new HashMap<>();
        map.put("user_unique_code",userUniqueCode);
        return this.baseMapper.selectOne(new QueryWrapper<Member>().allEq(map));
    }

    /**
     * 判断会员是否有效
     * @param userUniqueCode 用户唯一编码
     * @return Member
     * @throws Exception 抛出异常
     */
    @Override
    public Member whetherInvalid(String userUniqueCode) throws Exception{
        //获取会员信息
        Member member = this.selectOneMember(userUniqueCode);
        if (null == member){
            throw new Exception("您还不是会员，请开通会员");
        }
        LocalDate memberInvalidTime = member.getInvalidTime();
        if (null != memberInvalidTime){
            if (memberInvalidTime.isBefore(LocalDate.now())){
                throw new Exception("会员已过期，请先续费会员");
            }
        }
        return member;
    }

    /**
     * 领取会员权益
     * @param gainResourceReq 领取会员权益请求参数
     * @return GainResourceResp
     * @throws Exception 抛出异常
     */
    @Override
    @Transactional
    public Boolean gainResource(GainResourceReq gainResourceReq) throws Exception {

        if (null == gainResourceReq){
            throw new Exception("领取会员权益请求实体为空");
        }
        String userUniqueCode = gainResourceReq.getUserUniqueCode();
        Integer memberRightId = gainResourceReq.getMemberRightId();
        Integer memberRightResourceId = gainResourceReq.getMemberRightResourceId();
        Integer userAddressId = gainResourceReq.getUserAddressId();

        //判断会员是否有效，获取有效的会员信息
        Member member = this.whetherInvalid(userUniqueCode);

        //每季度只能领一次（每三个月只能领取一次）
        memberRightGainHistoryService.whetherReceive(userUniqueCode,memberRightId);

        //更新会员权益资源信息
        memberRightResourceService.updateForReceiveRight(memberRightResourceId);

        // 更新会员权益关联信息
        memberRightRelService.updateForReceiveRight(userUniqueCode,memberRightId);

        //增加一条领取历史记录信息
        memberRightGainHistoryService.updateForReceiveRight( userUniqueCode, memberRightId, member.getId(), userAddressId);

        return true;
    }

    /**
     * 展示会员权益
     * @param userUniqueCode 用户唯一编码
     * @return MemberRightsResp
     */
    @Override
    public MemberRightsResp memberRights(String userUniqueCode) {
        MemberRightsResp memberRightsResp = new MemberRightsResp();
        //获取用户信息
        User user = userService.getByUserUniqueCode(userUniqueCode);
        if (null != user) BeanUtils.copyProperties(user,memberRightsResp);

        //获取会员信息
        Member member = this.selectOneMember(userUniqueCode);
        if (null != member) {
            BeanUtils.copyProperties(member,memberRightsResp);
            memberRightsResp.setIsMember(true);
        }else {
            memberRightsResp.setIsMember(false);
        }

        //获取会员权益列表
        List<MemberRightVO> memberRightList = new ArrayList<>();
        List<Dict>  dictList = memberRightResourceRelService.selectDictForMemberRight();
        for (Dict dict : dictList ) {
            MemberRightVO memberRightVO = new MemberRightVO();
            BeanUtils.copyProperties(dict,memberRightVO);
            memberRightVO.setMemberRightId(dict.getId());

            //获取权益项包含资源列表
            List<MemberRightResourceVO> memberRightResourceVOList = new ArrayList<>();
            List<MemberRightResource> memberRightResourceList = memberRightResourceService.selectMemberRightResourceByRightId(dict.getId());
            for (MemberRightResource memberRightResource : memberRightResourceList ) {
                MemberRightResourceVO memberRightResourceVO = new MemberRightResourceVO();
                BeanUtils.copyProperties(memberRightResource,memberRightResourceVO);
                memberRightResourceVO.setMemberRightResourceId(memberRightResource.getId());
                memberRightResourceVOList.add(memberRightResourceVO);
            }
            memberRightVO.setMemberRightResourceVOList(memberRightResourceVOList);
            memberRightList.add(memberRightVO);
        }
        memberRightsResp.setMemberRightList(memberRightList);
        return memberRightsResp;
    }

    /**
     * 查看书籍详情
     * @param resourceId 资源ID
     * @return MemberRightResourceVO
     */
    @Override
    public MemberRightResourceVO  memberRightResourceDetail(Integer resourceId) {
        MemberRightResourceVO memberRightResourceVO = new MemberRightResourceVO();
        MemberRightResource memberRightResource = memberRightResourceService.getById(resourceId);
        if (null != memberRightResource ) {
            BeanUtils.copyProperties(memberRightResource, memberRightResourceVO);
            memberRightResourceVO.setMemberRightResourceId(memberRightResource.getId());
        }
        return memberRightResourceVO;
    }

    @Override
    public UserHomeResp setMemberInfo(UserHomeResp resp, String userUniqueCode) {
        Member member = this.selectOneMember(userUniqueCode);
        //是会员
        if(member != null){
            //会员并且没有过期
            long days = DateUtils.countDaysByNowAndParam(member.getInvalidTime());
            if( days >= 0){
                resp.setIsMember(true);
                resp.setMemberSurplus((int) days);

                resp.setBookWait(false);
            }else {//会员过期
                resp.setIsMember(true);
                resp.setMemberSurplus(-1);
                resp.setBookWait(false);
            }
        }else{
            //不是会员
            resp.setIsMember(false);
            resp.setMemberSurplus(0);
            resp.setBookWait(false);
        }
        return resp;
    }

    /**
     * 开通/续费会员
     * @param userUniqueCode 用户唯一编码
     * @return Boolean
     */
    @Override
    @Transactional
    public Boolean openMember(String userUniqueCode){
        Member member = this.selectOneMember(userUniqueCode);
        LocalDate today = LocalDate.now();
        if (null == member){ //开通会员
            member = new Member();
            member.setUserUniqueCode(userUniqueCode);
            member.setEffectiveTime(today);
            member.setInvalidTime(today.plusYears(1)); //会员期默认一年
        }else { //续费会员
            LocalDate invalidTime = member.getInvalidTime();
            member.setInvalidTime(invalidTime.plusYears(1)); //会员期默认一年
        }
        member.setDuration(12);//购买时长默认十二个月
        return this.saveOrUpdate(member);
    }
}
