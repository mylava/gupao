package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.UserInteraction;
import com.gupao.edu.account.client.req.MessageListReq;
import com.gupao.edu.account.client.resp.UserInteractionVO;
import com.gupao.edu.account.server.enums.ReadEnum;
import com.gupao.edu.account.server.mapper.UserInteractionMapper;
import com.gupao.edu.account.server.service.UserInteractionService;
import com.gupao.edu.account.server.service.converter.UserInterActionToVOConverter;
import com.gupao.edu.common.page.PageBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 互动消息表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class UserInteractionServiceImpl extends ServiceImpl<UserInteractionMapper, UserInteraction> implements UserInteractionService {

    @Autowired
    private UserInteractionMapper userInteractionMapper;

    /**
     * 分页 查询 用户平台 通知
     * @param messageListReq
     * @return
     */
    @Override
    public Page<UserInteractionVO> listPage(MessageListReq messageListReq) {
        IPage<UserInteraction> page = new Page<>();
        //设置 第几页
        page.setCurrent(messageListReq.getPage());
        //设置 每页数目
        page.setSize(messageListReq.getPage());
        Wrapper<UserInteraction> queryParams = new QueryWrapper<>();
        queryParams.getEntity().setAttentionTypeId(messageListReq.getMessageType());
        //设置 通知到 用户
        queryParams.getEntity().setTargetUniqueCode(messageListReq.getUserUniqueCode());
        Page<UserInteraction> resultUserInteractionPage = (Page<UserInteraction>)  userInteractionMapper.selectPage(page, queryParams);
        Page<UserInteractionVO> voPage = PageBeanUtils.transPlusPageToAnotherPage(resultUserInteractionPage,
                UserInterActionToVOConverter::toVO);
        return voPage;
    }

    /**
     *  平台 通知角标 （针对用户userUniqueCode）
     *  计算 用户 消息 角标 ： 统计总数
     * @param messageListReq
     * @return
     */
    @Override
    public int count(MessageListReq messageListReq) {
        Wrapper<UserInteraction> queryParams = new QueryWrapper<>();
        queryParams.getEntity().setAttentionTypeId(messageListReq.getMessageType());
        //设置 通知到 用户
        queryParams.getEntity().setTargetUniqueCode(messageListReq.getUserUniqueCode());
        //设置 未读:0
        queryParams.getEntity().setIsRead(ReadEnum.UN_READ.getReadStatus());
        return  userInteractionMapper.selectCount(queryParams);
    }

}
