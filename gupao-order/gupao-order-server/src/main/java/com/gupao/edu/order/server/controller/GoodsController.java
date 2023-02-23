package com.gupao.edu.order.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.order.client.entity.Goods;
import com.gupao.edu.order.client.entity.Orders;
import com.gupao.edu.order.server.enums.ItemEnums;
import com.gupao.edu.order.server.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-06 21:19
 */
@Api(value = "商品相关", tags = {"商品相关的api接口"})
@RestController
@RequestMapping("goods")
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "1.查询会员商品信息", notes = "查询会员商品信息", httpMethod = "GET")
    @ApiOperationSupport(order = 1)
    @GetMapping("/queryMemberByItemInfo")
    public Result< Goods > queryMemberByItemInfo(@ApiParam(name = "resourceId", value = "关联资源ID", required = true) Integer resourceId,
                                                 @ApiParam(name = "itemType", value = "商品类型", required = true) Integer itemType) {
        if (!ItemEnums.itemType.MEMBER_GOODS.getValue().equals(itemType)){
            return  Result.fail(null);
        }

        Goods goods = goodsService.queryMemberByItemInfo(resourceId, itemType);
        if (goods!=null)
            return Result.success(goods);
        return Result.fail(CodeMessage.ORDER_NOT_GOODS.fillArgs(Objects.requireNonNull(ItemEnums.itemType.valueOf1(itemType)).getName()));
    }

    @ApiOperation(value = "2.查询学币商品信息", notes = "查询学币商品信息", httpMethod = "GET")
    @ApiOperationSupport(order = 2)
    @GetMapping("/queryCreditsByItemsInfo")
    public Result< List< Goods > > queryCreditsByItemsInfo(@ApiParam(name = "itemType", value = "商品类型", required = true) Integer itemType,
                                                           @ApiParam(name = "isAll", value = "是否全部 1:否 2：是", required = true) Integer isAll) {

        if (!ItemEnums.itemType.CREDITS_GOODS.getValue().equals(itemType)){
            return  Result.fail(null);
        }
        List< Goods > goods = goodsService.queryCreditsByItemsInfo(itemType, isAll);
        if (!goods.isEmpty())
            return Result.success(goods);
        return Result.fail(CodeMessage.ORDER_NOT_GOODS.fillArgs(Objects.requireNonNull(ItemEnums.itemType.valueOf1(itemType)).getName()));

    }

    @ApiOperation(value = "3.查询课程商品信息", notes = "查询课程商品信息", httpMethod = "GET")
    @ApiOperationSupport(order = 3)
    @GetMapping("/queryCourseByItemInfo")
    public Result< Goods > queryCourseByItemInfo(@ApiParam(name = "resourceId", value = "关联资源ID", required = true) Integer resourceId,
                                                 @ApiParam(name = "itemType", value = "商品类型", required = true) Integer itemType) {

        Goods goods = goodsService.queryCourseByItemInfo(resourceId, itemType);
        if (goods!=null)
            return Result.success(goods);
        return Result.fail(CodeMessage.ORDER_NOT_GOODS.fillArgs(Objects.requireNonNull(ItemEnums.itemType.valueOf1(itemType)).getName()));
    }


}
