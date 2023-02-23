package com.gupao.edu.account.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.account.client.entity.UserAttention;
import com.gupao.edu.account.client.resp.UserAttentionVO;

import java.util.List;

/**
 * <p>
 * 用户关注表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserAttentionMapper extends BaseMapper<UserAttention> {

    /**
     * 获取关注列表
     * @param page 翻页对象
     * @param userUniqueCode 用户唯一编号
     * @return
     */
    List<UserAttentionVO> selectPageAttentionVO(Page<UserAttentionVO> page, String userUniqueCode);

    /**
     * 获取粉丝列表
     * @param page 翻页对象
     * @param userUniqueCode 用户唯一编号
     * @return
     */
    List<UserAttentionVO> selectPageFanVO(Page<UserAttentionVO> page, String userUniqueCode);
}
