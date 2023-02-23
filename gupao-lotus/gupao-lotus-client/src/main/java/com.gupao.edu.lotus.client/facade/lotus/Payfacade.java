package com.gupao.edu.lotus.client.facade.lotus;

import com.gupao.edu.common.result.Result;
import com.gupao.edu.lotus.client.req.OrderPayReq;
import com.gupao.edu.lotus.client.response.OrderPayRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-05-04 19:30
 */
@FeignClient(name = "gupao",url = "localhost:18041")
public interface Payfacade {


    /**
     * 支付接口
     * @param payReq
     * @return
     */
    @PostMapping("/pay/do")
    public Result< OrderPayRes > pay( @RequestBody OrderPayReq payReq);
}
