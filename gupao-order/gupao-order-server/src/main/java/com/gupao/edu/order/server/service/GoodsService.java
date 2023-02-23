package com.gupao.edu.order.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.order.client.entity.Goods;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 查询会员商品信息
     * @param resourceId
     * @param itemType
     * @return
     */
     Goods  queryMemberByItemInfo( Integer resourceId, Integer itemType);

    /**
     * 查询学币商品信息
     * @param itemType
     * @param isAll
     * @return
     */
     List< Goods >  queryCreditsByItemsInfo( Integer itemType, Integer isAll);

    /**
     *
     * @param resourceId
     * @param itemType
     * @return
     */
    Goods  queryCourseByItemInfo( Integer resourceId, Integer itemType);



}
