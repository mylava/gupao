package com.gupao.edu.account.server.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.account.client.req.AddressReq;
import com.gupao.edu.account.client.req.BasePageReq;
import com.gupao.edu.account.client.resp.AddressResp;
import com.gupao.edu.account.client.resp.AddressVO;
import com.gupao.edu.account.server.service.UserAddressService;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户地址表 前端控制器
 * </p>
 *
 * @author wangzhong
 * @since 2020-03-23
 */
@Slf4j
@Api(tags = {"用户地址管理"})
@RestController
@RequestMapping("/address")
public class UserAddressController{
	@Autowired
	private UserAddressService userAddressService;

	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "1.我的地址列表", notes = "我的地址列表")
	@ApiImplicitParam(name = "basePageReq", value = "地址列表请求参数", required = true, dataType = "BasePageReq")
	@PostMapping("list")
	public Result<IPage<AddressResp>> list(@RequestBody BasePageReq basePageReq) {
		log.info("我的地址列表,请求参数: {}",basePageReq);
		IPage<AddressResp> addressRespIPage = userAddressService.selectUserAddressPage(basePageReq);
		log.info("我的地址列表,响应参数: {}",addressRespIPage);
		return Result.success(addressRespIPage);
	}


	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "2.我的地址详情", notes = "我的地址详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String"),
			@ApiImplicitParam(name = "addressId", value = "地址ID", required = true, dataType = "Integer")
	})
	@PostMapping("detail")
	public Result<AddressVO> detail(@RequestParam(value = "userUniqueCode") String userUniqueCode,@RequestParam(value = "addressId") Integer addressId){
		log.info("我的地址详情,请求参数: {},{}",userUniqueCode,addressId);
		try {
			AddressVO addressVO = userAddressService.detail(userUniqueCode,addressId);
			log.info("我的地址详情,响应参数: {}",addressVO);
			return Result.success(addressVO);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("我的地址详情,抛出异常: {}",e.getMessage());
			return Result.fail(new CodeMessage(CodeMessage.USER_ADDRESS_DETAIL_EXCEPTION.getCode(),e.getMessage()));
		}
	}

	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "3.新增地址", notes = "新增地址")
	@PostMapping("/add")
	public Result<Boolean> add(@RequestBody AddressReq addressReq) {
		log.info("新增地址,请求参数: {}",addressReq);
		Boolean result = userAddressService.add(addressReq);
		log.info("新增地址,响应参数: {}",result);
		return Result.success(result);
	}

	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "4.修改物流地址", notes = "修改物流地址")
	@PostMapping("/modify")
	public Result<Boolean> modify(@RequestBody AddressReq addressReq) {
		log.info("修改物流地址,请求参数: {}",addressReq);
		Boolean result = userAddressService.modify(addressReq);
		log.info("修改物流地址,响应参数: {}",result);
		return Result.success(result);
	}

}
