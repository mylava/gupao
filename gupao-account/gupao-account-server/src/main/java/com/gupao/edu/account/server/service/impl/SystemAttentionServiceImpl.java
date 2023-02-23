package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.SystemAttention;
import com.gupao.edu.account.client.entity.UserSystemAttention;
import com.gupao.edu.account.client.req.MessageListReq;
import com.gupao.edu.account.client.resp.SystemAttentionVO;
import com.gupao.edu.account.server.enums.CacheKeyEnum;
import com.gupao.edu.account.server.enums.ReadEnum;
import com.gupao.edu.account.server.mapper.SystemAttentionMapper;
import com.gupao.edu.account.server.mapper.UserSystemAttentionMapper;
import com.gupao.edu.account.server.service.SystemAttentionService;
import com.gupao.edu.account.server.service.converter.SystemAttentionToVOConverter;
import com.gupao.edu.common.cache.CacheUtil;
import com.gupao.edu.common.cache.key.UserPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 平台通知表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-14
 */
@Service
public class SystemAttentionServiceImpl extends ServiceImpl<SystemAttentionMapper, SystemAttention> implements SystemAttentionService {

    /**
     * 平台 通知 mapper
     */
    @Autowired
    private SystemAttentionMapper systemAttentionMapper;

    @Autowired
    private UserSystemAttentionMapper userSystemAttentionMapper;

    /**
     * 分 页 查询  平台 通知
     * 查询 登录用户的 所有平台通知
     * @param req
     * @return
     */
    @Override
    public List<SystemAttentionVO> listAttentionPage(MessageListReq req) {
        if(req.getPage() < 1) {
            req.setPage(1);
        }
        if(req.getPageNum() < 1) {
            req.setPage(10);
        }
        req.setPage((req.getPage() - 1) * req.getPageNum());
        req.setPageNum(req.getPage() * req.getPageNum());
        Wrapper<UserSystemAttention> systemAttentionWrapper = new QueryWrapper<>(new UserSystemAttention());
        systemAttentionWrapper.getEntity().setUserUniqueCode(req.getUserUniqueCode());
        List<UserSystemAttention> systemAttentions = userSystemAttentionMapper.selectList(systemAttentionWrapper);
        List<Integer> attentionIds = new ArrayList<>();
        systemAttentions.stream().forEach(u -> attentionIds.add(u.getSystemAttentionId()));
        if (attentionIds.size() > 0) {
            return systemAttentionMapper.listAttentionPage(attentionIds);
        } else {
            return new ArrayList<>(0);
        }
    }

    /**
     * 计算 用户 的 平台 通知 未读 数量
     * @param messageListReq
     * @return
     */
    @Override
    public int count(MessageListReq messageListReq) {
        //设置 通知到 用户
        //设置 未读:0
        //平台 通知未读数量
        String count = CacheUtil.get(UserPrefix.USER_MESSAGER,CacheKeyEnum.PLATFORM_MSG_NOTIFICATIONS_CACHE_KEY.getKey() + ":" + messageListReq.getUserUniqueCode());
        if(count == null) {
            int value = systemAttentionMapper.count(messageListReq.getUserUniqueCode(),
                    ReadEnum.UN_READ.getReadStatus());
            CacheUtil.set(UserPrefix.USER_MESSAGER, CacheKeyEnum.PLATFORM_MSG_NOTIFICATIONS_CACHE_KEY.getKey() + ":" +messageListReq.getUserUniqueCode(), String.valueOf(value) );
            return value;
        } else {
            return Integer.parseInt(count);
        }
    }

    /**
     * 查询 平台 通知详情
     * @param id
     * @return
     */
    @Override
    public SystemAttentionVO getSystemAttentionDetail(Integer id) {
        SystemAttention systemAttention = systemAttentionMapper.selectById(id);
        return SystemAttentionToVOConverter.toVO(systemAttention);
    }
}
