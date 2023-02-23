package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.UserAttention;
import com.gupao.edu.account.client.req.AttentionPageReq;
import com.gupao.edu.account.client.resp.UserAttentionVO;
import com.gupao.edu.account.server.mapper.UserAttentionMapper;
import com.gupao.edu.account.server.service.UserAttentionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户关注表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class UserAttentionServiceImpl extends ServiceImpl<UserAttentionMapper, UserAttention> implements UserAttentionService {

    @Override
    public Integer deleteAttention(String userUniqueCode, String attentionUserCode) {
        Map<String,Object> map = new HashMap<>();
        map.put("userUniqueCode",userUniqueCode);
        map.put("attentionUserCode",attentionUserCode);
        return baseMapper.deleteByMap(map);
    }

    @Override
    public IPage<UserAttentionVO> attentionList(AttentionPageReq req) {
        Page<UserAttentionVO> page = new Page<>();
        page.setSize(req.getPageSize());
        page.setCurrent(req.getPageNum());
        return page.setRecords(baseMapper.selectPageAttentionVO(page, req.getUserUniqueCode()));

    }

    @Override
    public IPage<UserAttentionVO> fanList(AttentionPageReq req) {
        Page<UserAttentionVO> page = new Page<>();
        page.setSize(req.getPageSize());
        page.setCurrent(req.getPageNum());
        return page.setRecords(baseMapper.selectPageFanVO(page, req.getUserUniqueCode()));

    }

    @Override
    public Integer attentionCount(String userUniqueCode) {
        Map<String,Object> map = new HashMap<>();
        map.put("userUniqueCode",userUniqueCode);
        return baseMapper.selectByMap(map).size();
    }

    @Override
    public Integer fanCount(String userUniqueCode) {
        Map<String,Object> map = new HashMap<>();
        map.put("attentionUserCode",userUniqueCode);
        return baseMapper.selectByMap(map).size();
    }


}
