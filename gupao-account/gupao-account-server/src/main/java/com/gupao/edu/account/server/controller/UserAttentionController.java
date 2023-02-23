package com.gupao.edu.account.server.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.account.client.entity.UserAttention;
import com.gupao.edu.account.client.req.AttentionPageReq;
import com.gupao.edu.account.client.resp.UserAttentionVO;
import com.gupao.edu.account.server.service.UserAttentionService;
import com.gupao.edu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户关注表
 * </p>
 *
 * @author wuzhenping
 * @since 2020-03-18
 */
@Api(tags ={"关注"})
@RestController
@RequestMapping("/account/attention")
public class UserAttentionController {

    @Autowired
    private UserAttentionService userAttentionService;

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "关注个人", notes = "查看个人资料接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userUniqueCode", value = "关注人唯一编码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "attentionUserCode", value = "被关注人唯一编码", required = true, dataType = "String")
    })
    @PostMapping("/attention")
    public Result<Boolean> attention(@RequestParam String userUniqueCode,@RequestParam String attentionUserCode) {
        UserAttention userAttention = new UserAttention();
        userAttention.setUserUniqueCode(userUniqueCode);
        userAttention.setAttentionUserCode(attentionUserCode);
        return Result.success(userAttentionService.save(userAttention));
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "取消关注", notes = "取消关注接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "attentionUserCode", value = "被关注人唯一编码", required = true, dataType = "String")
    })
    @DeleteMapping("/cancel")
    public Result<Integer> cancel(@RequestParam String userUniqueCode,@RequestParam String attentionUserCode)  {
        return Result.success(userAttentionService.deleteAttention(userUniqueCode,attentionUserCode));
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "关注列表", notes = "关注列表接口")
    @GetMapping("/attentionList")
    public Result<IPage<UserAttentionVO>> attentionList(@RequestBody AttentionPageReq req) {
        return Result.success(userAttentionService.attentionList(req));
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "粉丝列表", notes = "粉丝列表接口")
    @GetMapping("/fanList")
    public Result<IPage<UserAttentionVO>> fanList(@RequestBody AttentionPageReq req) {
        return Result.success(userAttentionService.fanList(req));
    }
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "关注数", notes = "关注列表接口")
    @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String")
    @GetMapping("/attentionCount")
    public Result<Integer> attentionCount(@RequestParam String userUniqueCode) {
        return Result.success(userAttentionService.attentionCount(userUniqueCode));
    }
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "粉丝数", notes = "关注列表接口")
    @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String")
    @GetMapping("/fanCount")
    public Result<Integer> fanCount(@RequestParam String userUniqueCode) {
        return Result.success(userAttentionService.fanCount(userUniqueCode));
    }

}
