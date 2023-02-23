package com.gupao.edu.lotus.client.facade.lotus;

import com.gupao.edu.common.result.Result;
import com.gupao.edu.lotus.client.message.SmsMessage;
import com.gupao.edu.lotus.client.message.VerifyCodeMessage;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Class:
 *
 * @Author: wangzhong
 * @Date: 2020-03-31 15:38
 */
@FeignClient(value = "gupao-lotus")
public interface SMSFegin {
	@RequestMapping(method = RequestMethod.POST ,value = "/lotus/sms/sendMessage",consumes = "application/json")
	Result sendMessage(SmsMessage smsMessage);
	@RequestMapping(method = RequestMethod.POST ,value = "/lotus/sms/verifyCode",consumes = "application/json")
	Result verifyCode(VerifyCodeMessage verifyCodeMessage);

}