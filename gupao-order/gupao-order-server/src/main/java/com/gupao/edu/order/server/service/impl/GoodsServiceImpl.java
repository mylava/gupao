package com.gupao.edu.order.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.order.client.entity.Goods;
import com.gupao.edu.order.server.mapper.GoodsMapper;
import com.gupao.edu.order.server.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class GoodsServiceImpl extends ServiceImpl< GoodsMapper, Goods > implements GoodsService {

    @Override
    public Goods queryMemberByItemInfo(Integer resourceId, Integer itemType) {
        QueryWrapper< Goods > wrapper = new QueryWrapper<>();
        Goods goods = new Goods();
        goods.setResourceId(resourceId);
        goods.setItemType(itemType);
        wrapper.setEntity(goods);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List< Goods > queryCreditsByItemsInfo(Integer itemType, Integer isAll) {
        QueryWrapper< Goods > wrapper = new QueryWrapper<>();
        wrapper.eq("item_type", itemType);
        wrapper.orderByAsc("price_normal");
        List< Goods > goodsList = getBaseMapper().selectList(wrapper);
        if (isAll==1) {
            goodsList = goodsList.stream().filter(e -> 6==e.getPriceNormal() || 30==(e.getPriceNormal())
                    || 50==e.getPriceNormal() || 108==e.getPriceNormal()
                    || 208==e.getPriceNormal()).collect(Collectors.toList());
        }
        return goodsList;
    }

    @Override
    public Goods queryCourseByItemInfo(Integer resourceId, Integer itemType) {
        QueryWrapper< Goods > wrapper = new QueryWrapper<>();
        wrapper.eq("item_type", itemType);
        wrapper.eq("resource_id", resourceId);
        return getBaseMapper().selectOne(wrapper);
    }
}
