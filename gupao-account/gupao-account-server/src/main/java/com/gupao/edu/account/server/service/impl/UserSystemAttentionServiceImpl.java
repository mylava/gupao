package com.gupao.edu.account.server.service.impl;/**
 * Created by dudu on 2020/4/28.
 */

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.PersonalLetter;
import com.gupao.edu.account.client.entity.UserSystemAttention;
import com.gupao.edu.account.server.enums.CacheKeyEnum;
import com.gupao.edu.account.server.mapper.UserSystemAttentionMapper;
import com.gupao.edu.account.server.service.UserSystemAttentionService;
import com.gupao.edu.common.cache.CacheUtil;
import com.gupao.edu.common.cache.key.UserPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h3>gupao-parent</h3>
 * <p>UserSystemAttentionServiceImpl</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-28 23:27
 **/
@Service
public class UserSystemAttentionServiceImpl extends ServiceImpl<UserSystemAttentionMapper, UserSystemAttention> implements UserSystemAttentionService {

    /**
     * 用户 平台 通知 mapper
     */
    @Autowired
    private UserSystemAttentionMapper userSystemAttentionMapper;

    /**
     * 读取一个平台通知后
     * 缓存数量就需要减1
     * @param userUniqueCode
     * @param systemAttentionId
     * @param readStatus
     * @return
     */
    @Override
    public int readSystemAttention(String userUniqueCode, Integer systemAttentionId, int readStatus) {
        LambdaUpdateWrapper<UserSystemAttention> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(UserSystemAttention::getUserUniqueCode, userUniqueCode);
        lambdaUpdateWrapper.eq(UserSystemAttention::getSystemAttentionId, systemAttentionId);
        lambdaUpdateWrapper.set(UserSystemAttention::getIsRead, readStatus);
        int result = userSystemAttentionMapper.update(null, lambdaUpdateWrapper);
        if(result > 0) {
            CacheUtil.incr(UserPrefix.USER_MESSAGER, CacheKeyEnum.PLATFORM_MSG_NOTIFICATIONS_CACHE_KEY.getKey(), -1);
        }
        return result;
    }

}
