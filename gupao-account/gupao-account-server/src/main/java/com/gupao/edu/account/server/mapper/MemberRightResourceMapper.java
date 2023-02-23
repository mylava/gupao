package com.gupao.edu.account.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.account.client.entity.MemberRightResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 会员权益资源表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
public interface MemberRightResourceMapper extends BaseMapper<MemberRightResource> {

    /**
     * 根据权益ID获取权益资源信息集
     * @param rightId 权益ID（字典ID）
     * @return List<MemberRightResource>
     */
    List<MemberRightResource> selectMemberRightResourceByRightId(@Param("rightId")Integer rightId);
}
