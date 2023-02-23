package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.MemberRightResource;

import java.util.List;

/**
 * <p>
 * 会员权益资源表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
public interface MemberRightResourceService extends IService<MemberRightResource> {

    /**
     * 领取会员权益时更新会员权益资源信息
     * @param memberRightResourceId 会员权益资源id
     * @return Boolean
     * @throws Exception 抛出异常
     */
    Boolean updateForReceiveRight(Integer memberRightResourceId) throws Exception;

    /**
     * 根据权益ID获取权益资源信息集
     * @param rightId 权益ID（字典ID）
     * @return List<MemberRightResource>
     */
    List<MemberRightResource> selectMemberRightResourceByRightId(Integer rightId);
}
