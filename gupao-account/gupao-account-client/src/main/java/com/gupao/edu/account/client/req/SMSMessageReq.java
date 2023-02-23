package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @Description: SMS短信
 * @Author： wz
 * @Date: 2019-04-12 10:22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMSMessageReq {
	/**
	 * 消息类型
	 */
	@ApiModelProperty(value = "发送短信类型:1-用户注册 2手机号更绑 3-更换密码 4-其他", required = true)
	private Integer type;

	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号", required = true)
	private List<String> mobiles;



}