package com.gupao.edu.order.server.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.order.client.vo.TransportInformationVO;
import com.gupao.edu.order.server.service.OrderLogisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 物流信息表  前端控制器
 * </p>
 *
 * @author lipengfei
 * @since 2020-03-24
 */
@Api(value = "物流接口", tags = {"物流相关的api接口"})
@RestController
@RequestMapping("/order/logistics")
public class OrderLogisticsController {

	@Resource
	OrderLogisticsService orderLogisticsService;


	/**
	 * 实时查询物流信息
	 * @param orderNo
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "1.查询物流信息", notes = "查询物流信息", httpMethod = "POST")
	@ApiOperationSupport(order = 1)
	@PostMapping("/queryLogistics")
	public Result<TransportInformationVO> queryLogistics(
			@ApiParam(name = "orderNo", value = "订单号", required = true) String orderNo,
			@ApiParam(name = "userId", value = "用户id", required = true) String userId) {
		TransportInformationVO transportInformationVO = orderLogisticsService.queryLogistics(orderNo, userId);
		if (transportInformationVO==null)
			return Result.fail(CodeMessage.LOGISTICS_HTTP_ERROR.fillArgs(orderNo));
		return   Result.success(transportInformationVO);
	}
}
