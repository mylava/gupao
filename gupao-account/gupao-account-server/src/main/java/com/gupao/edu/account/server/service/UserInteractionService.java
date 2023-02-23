package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.UserInteraction;
import com.gupao.edu.account.client.req.MessageListReq;
import com.gupao.edu.account.client.resp.UserInteractionVO;

/**
 * <p>
 * 互动消息表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserInteractionService extends IService<UserInteraction> {

    /**
     * 分页 查询 互动 消息
     * @param messageListReq
     * @return
     */
    Page<UserInteractionVO> listPage(MessageListReq messageListReq);

    /**
     * 计算 当前 用户 平台 消息的角标
     * @param messageListReq
     * @return
     */
    int count(MessageListReq messageListReq);

}
