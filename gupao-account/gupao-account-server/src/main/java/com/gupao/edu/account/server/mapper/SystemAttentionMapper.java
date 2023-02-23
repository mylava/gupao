package com.gupao.edu.account.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.account.client.entity.SystemAttention;
import com.gupao.edu.account.client.req.MessageListReq;
import com.gupao.edu.account.client.resp.SystemAttentionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 平台通知表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-14
 */
public interface SystemAttentionMapper extends BaseMapper<SystemAttention> {

    /**
     * 查询 用户 通知 未读数量
     * @param readStatus 阅读状态
     * @param userUniqueCode  用户编码
     * @return
     */
    int count(@Param("userUniqueCode") String userUniqueCode,
              @Param("readStatus")Integer readStatus);

    /**
     * 分页 查询
     * @param list
     * @return
     */
//    List<SystemAttentionVO> listAttentionPage(@Param("condition") MessageListReq req);
    List<SystemAttentionVO> listAttentionPage(List<Integer> list);
}
