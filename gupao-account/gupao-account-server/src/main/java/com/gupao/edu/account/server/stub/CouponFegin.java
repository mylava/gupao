
package com.gupao.edu.account.server.stub;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Class:
 *
 * @Author: wangzhong
 * @Date: 2020-03-28 11:25
 */
@FeignClient(name = "marketing-service",url = "localhost:8084")
public interface CouponFegin {
	//@RequestMapping(method = RequestMethod.POST, value = "/findCouponListByIds",consumes = "application/json")
	 //List<AccountCouponVO> findCouponListsByIds(List<Integer> couponIds);
}

