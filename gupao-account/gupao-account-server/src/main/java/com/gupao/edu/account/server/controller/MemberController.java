package com.gupao.edu.account.server.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.account.client.req.BasePageReq;
import com.gupao.edu.account.client.req.GainResourceReq;
import com.gupao.edu.account.client.resp.GainHistoryResp;
import com.gupao.edu.account.client.resp.GainResourceResp;
import com.gupao.edu.account.client.resp.MemberRightResourceVO;
import com.gupao.edu.account.client.resp.MemberRightsResp;
import com.gupao.edu.account.server.service.MemberRightGainHistoryService;
import com.gupao.edu.account.server.service.MemberService;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author lipengfei
 * @since 2020-03-20
 */
@Slf4j
@Api(tags ={"会员管理"}, description = "会员、权益管理")
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRightGainHistoryService memberRightGainHistoryService;

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "1.领取会员权益", notes = "领取会员权益")
    @ApiImplicitParam(name = "gainResourceReq", value = "领取会员权益请求参数", required = true, dataType = "GainResourceReq")
    @PostMapping("/gainResource")
    public Result<GainResourceResp> gainResource(@RequestBody GainResourceReq gainResourceReq) {
        log.info("领取会员权益，请求参数: {}",gainResourceReq);
        GainResourceResp gainResourceResp = new GainResourceResp();
        try {
            Boolean result = memberService.gainResource(gainResourceReq);
            gainResourceResp.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            gainResourceResp.setResult(false);
            log.info("领取会员权益，抛出异常:{}",e.getMessage());
            return Result.fail(new CodeMessage(CodeMessage.MEMBER_MANAGEMENT_EXCEPTION.getCode(),e.getMessage()));
        }
        log.info("领取会员权益，响应结果: {}",gainResourceResp);
        return Result.success(gainResourceResp);
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "2.展示会员权益", notes = "展示会员权益")
    @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编号", required = true, dataType = "String")
    @PostMapping("/memberRights")
    public Result<MemberRightsResp> memberRights(@RequestParam(value = "userUniqueCode") String userUniqueCode) {
        log.info("展示会员权益，请求参数: {}",userUniqueCode);
        MemberRightsResp memberRightsResp = memberService.memberRights(userUniqueCode);
        log.info("展示会员权益，响应结果: {}",memberRightsResp);
        return Result.success(memberRightsResp);
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "3.查看书籍详情", notes = "书籍详情接口")
    @ApiImplicitParam(name = "resourceId", value = "资源ID", required = true, dataType = "Integer")
    @PostMapping("/memberRightResourceDetail")
    public Result<MemberRightResourceVO>  memberRightResourceDetail(@RequestParam(value = "resourceId") Integer resourceId) {
        log.info("查看书籍详情，请求参数: {}",resourceId);
        MemberRightResourceVO memberRightResourceVO = memberService.memberRightResourceDetail(resourceId);
        log.info("查看书籍详情，响应结果: {}",memberRightResourceVO);
        return Result.success(memberRightResourceVO);
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "4.查看会员权益领取记录", notes = "查看会员权益领取记录")
    @ApiImplicitParam(name = "basePageReq", value = "查看权益领取记录请求参数", required = true, dataType = "BasePageReq")
    @PostMapping("/gainHistory")
    public Result<IPage<GainHistoryResp>> gainHistory(@RequestBody BasePageReq basePageReq) {
        log.info("查看会员权益领取记录，请求参数: {}",basePageReq);
        IPage<GainHistoryResp> gainHistoryRespIPage = memberRightGainHistoryService.selectGainHistoryPage(basePageReq);
        log.info("查看会员权益领取记录，响应结果: {}",gainHistoryRespIPage);
        return Result.success(gainHistoryRespIPage);
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "5.开通/续费会员", notes = "开通/续费会员")
    @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编号", required = true, dataType = "String")
    @PostMapping("/openMember")
    public Result<Boolean> openMember(@RequestParam String userUniqueCode) {
        log.info("开通/续费会员，请求参数: {}",userUniqueCode);
        Boolean result = memberService.openMember(userUniqueCode);
        log.info("开通/续费会员，响应结果: {}",result);
        return Result.success(result);
    }

}
