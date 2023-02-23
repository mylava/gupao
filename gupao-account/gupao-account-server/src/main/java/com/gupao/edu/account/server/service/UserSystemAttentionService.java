package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.UserSystemAttention;

/**
 * <h3>gupao-parent</h3>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-28 23:27
 */
public interface UserSystemAttentionService extends IService<UserSystemAttention> {


    /**
     * 阅读 平台通知
     * @param userUniqueCode
     * @param systemAttentionId
     * @param readStatus
     * @return
     */
    int readSystemAttention(String userUniqueCode, Integer systemAttentionId, int readStatus);
}
