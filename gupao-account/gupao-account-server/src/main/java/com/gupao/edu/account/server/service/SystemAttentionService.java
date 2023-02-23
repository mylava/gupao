package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.SystemAttention;
import com.gupao.edu.account.client.req.MessageListReq;
import com.gupao.edu.account.client.resp.SystemAttentionVO;

import java.util.List;

/**
 * <p>
 * 平台通知表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-14
 */
public interface SystemAttentionService extends IService<SystemAttention> {

    /**
     * 分页 列表查询
     * @param messageListReq
     * @return
     */
    List<SystemAttentionVO> listAttentionPage(MessageListReq messageListReq);

    /**
     * 计算 当前 用户 平台 消息的角标
     * @param messageListReq
     * @return
     */
    int count(MessageListReq messageListReq);

    /**
     * 查询平台通知详情
     * @param id
     * @return
     */
    SystemAttentionVO getSystemAttentionDetail(Integer id);
}
