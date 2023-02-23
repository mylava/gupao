package com.gupao.edu.account.server.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.account.client.req.FavoriteListReq;
import com.gupao.edu.account.client.resp.FavoriteListResp;
import com.gupao.edu.account.server.service.CommonService;
import com.gupao.edu.common.cache.CacheUtil;
import com.gupao.edu.common.cache.key.UserPrefix;
import com.gupao.edu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 2020/4/24
 */
@Api(tags = {"我的收藏列表接口"})
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private CommonService commonService;

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "1.我的收藏列表", notes = "我的收藏列表")
    @ApiImplicitParam(name = "favoriteListReq", value = "用户唯一编码", required = true, dataType = "FavoriteListReq")
    @PostMapping(value = "/list")
    public Result<FavoriteListResp> list(@RequestBody FavoriteListReq favoriteListReq) {
        if (StringUtils.isEmpty(favoriteListReq.getUserUniqueCode())) {
            return Result.success();
        }
        FavoriteListResp resp = new FavoriteListResp();
        //查询收藏文章数量
        resp.setArticleNum(commonService.getArticleNum(favoriteListReq.getUserUniqueCode()));
        //查询问答数量
        resp.setAskNum(commonService.getAskNum(favoriteListReq.getUserUniqueCode()));
        //查询课程数量
        resp.setCourseNum(commonService.getCourseNum(favoriteListReq.getUserUniqueCode()));
        if (favoriteListReq.getListType() == 1) {
            //todo 查询课程
        } else if (favoriteListReq.getListType() == 2) {
            //todo 查询课程
        } else if (favoriteListReq.getListType() == 3) {
            //todo 查询课程
        } else {
            return Result.success();
        }
        return Result.success(resp);
    }
}
