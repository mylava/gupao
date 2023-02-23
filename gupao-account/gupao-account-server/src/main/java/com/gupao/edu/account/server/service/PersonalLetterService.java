package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.PersonalLetter;
import com.gupao.edu.account.client.req.MessageListReq;
import com.gupao.edu.account.client.req.SendLetterReq;
import com.gupao.edu.account.client.resp.PersonalLetterDetailVO;
import com.gupao.edu.account.client.resp.PersonalLetterVO;

import java.util.List;

/**
 * <p>
 * 私信表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface PersonalLetterService extends IService<PersonalLetter> {
    /**
     * 分页 查询 互动 消息
     * @param messageListReq
     * @return
     */
    List<PersonalLetterVO> listPage(MessageListReq messageListReq);

    /**
     * 计算 当前 用户 平台 消息的角标
     * @param messageListReq
     * @return
     */
    int count(MessageListReq messageListReq);

    /**
     * 查询 2 个人的所有私信列表
     * @param userUniqueCode
     * @param oppositeUniqueCode
     * @return
     */
    List<PersonalLetterDetailVO> listPersonalLetterDetail(String userUniqueCode,
                                                          String oppositeUniqueCode);


    /**
     * 更新 阅读状态
     * @param req
     * @return
     */
    int update(SendLetterReq req);

    int batchUpdate(SendLetterReq sendLetterReq);
}
