package com.gupao.edu.account.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.account.client.entity.UserInfoDetail;
import com.gupao.edu.account.client.resp.UserInfoResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户详细信息表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Mapper
@Repository
public interface UserInfoDetailMapper extends BaseMapper<UserInfoDetail> {

    /**
     * 获取 用户 详情
     * @param targetUniqueCode
     * @return
     */
    UserInfoResp getUserInfo(@Param("userUniqueCode") String targetUniqueCode);
}
