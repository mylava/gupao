package com.gupao.edu.account.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.account.client.req.BasePageReq;
import com.gupao.edu.account.client.resp.AccountBalanceResp;
import com.gupao.edu.account.client.resp.AccountHistoryVO;
import com.gupao.edu.account.server.service.UserAccountHistoryService;
import com.gupao.edu.account.server.service.UserAccountService;
import com.gupao.edu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 2020/4/24
 */
@Api(tags = {"学币相关接口"})
@RestController
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserAccountHistoryService userAccountHistoryService;

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "1.当前可用学币", notes = "当前可用学币")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String")
    })
    @GetMapping(value = "/accountBalance")
    public Result<AccountBalanceResp> accountBalance(@RequestParam(value = "userUniqueCode") String userUniqueCode) {
        if(StringUtils.isEmpty(userUniqueCode)){
            return Result.success();
        }
        return Result.success(userAccountService.findAccountBalance(userUniqueCode));
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "2.查询学币明细", notes = "查询学币明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "basePageReq", value = "分页参数", required = true, dataType = "BasePageReq")
    })
    @PostMapping(value = "/accountHistory")
    public Result<IPage<AccountHistoryVO>> accountHistory(@RequestBody BasePageReq basePageReq) {
        return Result.success(userAccountHistoryService.findAccountHistory(basePageReq));
    }

}
