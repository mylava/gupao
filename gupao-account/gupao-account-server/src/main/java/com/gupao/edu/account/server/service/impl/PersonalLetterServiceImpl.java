package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.PersonalLetter;
import com.gupao.edu.account.client.entity.User;
import com.gupao.edu.account.client.req.MessageListReq;
import com.gupao.edu.account.client.req.SendLetterReq;
import com.gupao.edu.account.client.resp.PersonalLetterDetailVO;
import com.gupao.edu.account.client.resp.PersonalLetterVO;
import com.gupao.edu.account.server.enums.CacheKeyEnum;
import com.gupao.edu.account.server.enums.ReadEnum;
import com.gupao.edu.account.server.mapper.PersonalLetterMapper;
import com.gupao.edu.account.server.service.PersonalLetterService;
import com.gupao.edu.common.cache.CacheUtil;
import com.gupao.edu.common.cache.key.UserPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 私信表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class PersonalLetterServiceImpl extends ServiceImpl<PersonalLetterMapper, PersonalLetter> implements PersonalLetterService {

    @Autowired
    private PersonalLetterMapper personalLetterMapper;

    /**
     * 分页 查询 私信列表集合
     * @param req
     * @return
     */
    @Override
    public List<PersonalLetterVO> listPage(MessageListReq req) {
        //重新计算那个查询参数
        req.setPage((req.getPage() - 1) * req.getPageNum());
        req.setPageNum(req.getPage() * req.getPageNum());
        return personalLetterMapper.listPersonalLetters(req);
    }

    /**
     * 查询 所有 的 私信 列表
     * @param userUniqueCode
     * @param oppositeUniqueCode
     * @return
     */
    @Override
    public List<PersonalLetterDetailVO> listPersonalLetterDetail(String userUniqueCode, String oppositeUniqueCode) {
//        req.setPage((req.getPage() - 1) * req.getPageNum());
//        req.setPageNum(req.getPage() * req.getPageNum());
        return personalLetterMapper.listPersonalLetterDetail(userUniqueCode, oppositeUniqueCode);
    }

    /**
     * 批量更新 阅读状态
     * @param sendLetterReq
     * @return
     */
    @Override
    public int batchUpdate(SendLetterReq sendLetterReq) {
        return personalLetterMapper.batchUpdate(sendLetterReq.getUserUniqueCode(), sendLetterReq.getOppositeUniqueCode(),
                    ReadEnum.MARK_READ.getReadStatus());
    }

    /**
     * 更新阅读状态 暂时留着 todo
     * @param req
     * @return
     */
    @Override
    public int update(SendLetterReq req) {
        LambdaUpdateWrapper<PersonalLetter> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        //设置接收者
        lambdaUpdateWrapper.eq(PersonalLetter::getTargetUniqueCode, req.getUserUniqueCode());
        //设置发送者
        lambdaUpdateWrapper.eq(PersonalLetter::getFromUniqueCode, req.getOppositeUniqueCode());
//        if(req.getIsRead() != 1) {
//            throw new IllegalArgumentException("是否已读状态参数不对!");
//        }
//        lambdaUpdateWrapper.set(PersonalLetter::getIsRead, req.getIsRead());
        int result = personalLetterMapper.update(null, lambdaUpdateWrapper);
        if(result > 0) {
            CacheUtil.incr(UserPrefix.USER_MESSAGER, CacheKeyEnum.PLATFORM_MSG_NOTIFICATIONS_CACHE_KEY.getKey(), -1);
        }
        return result;
    }

    /**
     * 查询 私信 列表 未读角标
     * @param messageListReq
     * @return
     */
    @Override
    public int count(MessageListReq messageListReq) {
        Wrapper<PersonalLetter> queryParams = new QueryWrapper<>(new PersonalLetter());
        //设置 通知到 用户
        queryParams.getEntity().setTargetUniqueCode(messageListReq.getUserUniqueCode());
        //设置 未读:0
        queryParams.getEntity().setIsRead(ReadEnum.UN_READ.getReadStatus());
        //私信未读 数量
        String count = CacheUtil.get(UserPrefix.USER_MESSAGER,CacheKeyEnum.PERSONAL_LETTER_CACHE_KEY.getKey() + ":" +  messageListReq.getUserUniqueCode());
        if(count == null) {
            int value = personalLetterMapper.selectCount(queryParams);
            CacheUtil.set(UserPrefix.USER_MESSAGER, CacheKeyEnum.PERSONAL_LETTER_CACHE_KEY.getKey() + ":" + messageListReq.getUserUniqueCode(), String.valueOf(value) );
            return value;
        } else {
            return Integer.parseInt(count);
        }
    }
}
