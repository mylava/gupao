package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.UserAttention;
import com.gupao.edu.account.client.req.AttentionPageReq;
import com.gupao.edu.account.client.resp.UserAttentionVO;

/**
 * <p>
 * 用户关注表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserAttentionService extends IService<UserAttention> {

    Integer deleteAttention(String userUniqueCode,String attentionUserCode);

    IPage<UserAttentionVO> attentionList(AttentionPageReq req);

    IPage<UserAttentionVO> fanList(AttentionPageReq req);

    Integer attentionCount(String userUniqueCode);

    Integer fanCount(String userUniqueCode);

}
