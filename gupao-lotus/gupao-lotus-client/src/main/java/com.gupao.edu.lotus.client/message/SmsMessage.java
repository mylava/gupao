package com.gupao.edu.lotus.client.message;


import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description: 短信参数DTO
 * @Author： wz
 * @Date: 2019-04-01 11:09
 **/
@Data
public class SmsMessage implements Serializable {
	/**
	 * 短信通道code
	 */
	private String smsChannelCode;
	/**
	 * 模版id
	 */
	private String templateId;
	/**
	 * 手机号集合
	 */
	private List<String> mobiles;
	/**
	 * 业务编码
	 */
	private Integer bizCode;


}

