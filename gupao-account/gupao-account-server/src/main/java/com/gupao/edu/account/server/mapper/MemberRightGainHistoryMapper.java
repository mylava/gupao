package com.gupao.edu.account.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.account.client.entity.MemberRightGainHistory;
import com.gupao.edu.account.client.resp.GainHistoryResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权益领取记录表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-22
 */
public interface MemberRightGainHistoryMapper extends BaseMapper<MemberRightGainHistory> {

    /**
     * 查询 : 根据userUniqueCode用户唯一编码查询用户领取权益历史列表，分页显示
     * @param page 翻页对象
     * @param userUniqueCode 用户唯一编码
     * @return List<GainHistoryResp>
     */
    List<GainHistoryResp> selectGainHistoryList(Page<GainHistoryResp> page, @Param("userUniqueCode") String userUniqueCode);

}
