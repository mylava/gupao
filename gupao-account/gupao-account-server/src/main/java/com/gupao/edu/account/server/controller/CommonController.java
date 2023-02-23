package com.gupao.edu.account.server.controller;

import com.alibaba.fastjson.JSON;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.account.client.resp.AreaResp;
import com.gupao.edu.account.client.resp.DictResp;
import com.gupao.edu.account.server.service.CommonService;
import com.gupao.edu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 14/04/2020
 */
@Api(tags = {"通用接口"})
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private CommonService commonService;
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "1.发送短信", notes = "发送短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "bizCode", value = "业务编码:1-用户注册 2手机号更绑 3-更换密码 4-其他",
                    required = true, dataType = "Integer")
    })
    @PostMapping(value = "/sendMessage")
    public Result sendMessage(@RequestParam(value = "mobile") String mobile,
                                       @RequestParam(value = "bizCode") Integer bizCode) {
        //TODO 1.添加[业务类型枚举]类 2.验证手机号码是否有效 3.为了防止跳过验证，短信在缓存中保存5分钟，执行发送短信逻辑
        return commonService.sendMessage(mobile,bizCode);
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "2.验证短信", notes = "验证短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "bizCode", value = "业务编码:1-用户注册 2手机号更绑 3-更换密码 4-其他",
                    required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sms", value = "验证码", required = true, dataType = "String")
    })
    @PostMapping(value = "/verifySMS")
    public Result verifySMS(@RequestParam(value = "mobile") String mobile,
                                     @RequestParam(value = "bizCode", required = false) Integer bizCode,
                                     @RequestParam(value = "sms") String sms) throws Exception {
        //TODO 1.验证短信是否正确、超时，返回验证结果
        log.info("请求参数为,{}", JSON.toJSONString(mobile+bizCode+sms));
        return commonService.verifyCode(mobile,bizCode,sms);

    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "3.获取地区列表", notes = "获取地区列表")
    @ApiImplicitParam(name = "id", value = "父区域ID", required = true, dataType = "Integer")
    @PostMapping(value = "/areaList")
    public Result<List<AreaResp>> areaList(@RequestParam(value = "id") Integer id) {
        //TODO 1.从缓存中读取区域信息 2.如果缓存中不存在，从db中读取area表数据
        return Result.success(commonService.getAreaList(id));
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "4.获取常量列表", notes = "获取常量列表")
    @ApiImplicitParam(name = "categoryCode", value = "分类编码", required = true, dataType = "String")
    @PostMapping(value = "/dictList")
    public Result<List<DictResp>> dictList(@RequestParam(value = "categoryCode") String categoryCode) {
        //TODO 1.声明常量类型列表常量 2.从缓存中读取区域信息 3.如果缓存中不存在，从db中读取dict表数据
        return Result.success(commonService.getDictList(categoryCode));
    }

}
