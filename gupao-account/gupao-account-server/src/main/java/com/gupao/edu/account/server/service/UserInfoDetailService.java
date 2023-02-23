package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.UserInfoDetail;
import com.gupao.edu.account.client.req.UserInfoReq;
import com.gupao.edu.account.client.resp.UserInfoResp;

/**
 * <p>
 * 用户详细信息表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserInfoDetailService extends IService<UserInfoDetail> {

    /**
     * 根据用户 编码 获取 用户详情
     * @param targetUniqueCode
     * @return
     */
    UserInfoResp getUserInfo(String targetUniqueCode);

    /**
     *  更新用户
     * @param req
     * @return
     */
    Boolean update(UserInfoReq req);

}
