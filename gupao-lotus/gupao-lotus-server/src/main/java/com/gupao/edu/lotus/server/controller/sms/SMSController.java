package com.gupao.edu.lotus.server.controller.sms;

import com.alibaba.fastjson.JSON;
import com.gupao.edu.common.constant.SmsChannel;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.lotus.client.message.SmsMessage;
import com.gupao.edu.lotus.client.message.VerifyCodeMessage;
import com.gupao.edu.lotus.client.message.YunpianSmsTemplate;
import com.gupao.edu.lotus.server.service.sms.SmsService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 短信服务controller
 * @Author： wz
 * @Date: 2019-04-01 11:09
 **/
@RestController
@RequestMapping("/lotus/sms")
public class SMSController {
	private Logger logger = LoggerFactory.getLogger(SMSController.class);
	@Autowired
	private SmsService smsService;

	/**
	 * 默认通道为云片
	 */
	private Enum defaultChannel = SmsChannel.SMS_YUNPIAN;
	/**
	 * 发送短信失败次数（最多允许出现5次失败，不然换通道）
	 */
	private AtomicInteger defaultFailNum = new AtomicInteger(0);

	/**
	 * 公共发送短信接口
	 *
	 * @param smsMessage
	 * @return
	 */
	@ApiOperation(value = "根据模板配置参数发送短信")
	@PostMapping(value = "/sendMessage")
	public Result commonSendMessage(@RequestBody  SmsMessage smsMessage) {
		logger.info("请求参数为,{}", JSON.toJSONString(smsMessage));

		//根据失败次数判断是否切换通道
		if (defaultFailNum.get() >= 5) {
			defaultChannel = Objects.equals(defaultChannel, SmsChannel.SMS_ALI) ? SmsChannel.SMS_YUNPIAN : SmsChannel.SMS_ALI;
			defaultFailNum.set(0);
		}

		if (Objects.equals(defaultChannel, SmsChannel.SMS_YUNPIAN)) {
			logger.info("目前通道为,{}", SmsChannel.SMS_YUNPIAN.getMessage());
			smsMessage.setSmsChannelCode(SmsChannel.SMS_YUNPIAN.getCode());
			Result res = smsService.sendSmsMsg(smsMessage);
			if (res.getStatus() != 0) {
				defaultFailNum.incrementAndGet();
				logger.info("云片发送短信失败第{}次", defaultFailNum.get());
				//换通道
				smsMessage.setSmsChannelCode(SmsChannel.SMS_ALI.getMessage());
				return smsService.sendSmsMsg(smsMessage);
			}
			defaultFailNum.set(0);
			return res;
		} else {
			logger.info("目前通道为,{}", SmsChannel.SMS_ALI.getMessage());
			smsMessage.setSmsChannelCode(SmsChannel.SMS_ALI.getCode());
			Result res = smsService.sendSmsMsg(smsMessage);
			if (res.getStatus() != 1) {
				defaultFailNum.incrementAndGet();
				logger.info("阿里发送短信失败第{}次", defaultFailNum.get());
				smsMessage.setSmsChannelCode(SmsChannel.SMS_YUNPIAN.getCode());
				Result result = smsService.sendSmsMsg(smsMessage);
				//如果云片通道好了,主动切换到云片通道
				if (result.getStatus() == 1) {
					defaultChannel = SmsChannel.SMS_YUNPIAN;
					defaultFailNum.set(0);
				}
				return result;
			}
			defaultFailNum.set(0);
			return res;
		}
	}


	@ApiOperation(value = "获得短信模块")
	@PostMapping(value = "/findTemple")
	public Result commonSendMessage() {
		List<YunpianSmsTemplate> list= smsService.findYunpianTemple();
		return null;

	}
	@ApiOperation(value = "验证短信")
	@PostMapping(value = "/verifyCode")
	public Result verifyCode(@RequestBody VerifyCodeMessage verifyCodeMessage) throws Exception {
		return smsService.verifyCode(verifyCodeMessage);
	}
}