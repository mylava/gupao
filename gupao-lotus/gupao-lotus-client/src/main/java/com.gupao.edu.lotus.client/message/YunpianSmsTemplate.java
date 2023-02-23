package com.gupao.edu.lotus.client.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 云片短息模板
 *
 * @author
 */
@Data
public class YunpianSmsTemplate implements Serializable {

	private static final long serialVersionUID = 1529394466335L;

	private String reason;

	@JsonProperty("country_code")
	private String countryCode;

	@JsonProperty("tpl_content")
	private String tplContent;

	@JsonProperty("tpl_id")
	private String tplId;

	private String lang;

	@JsonProperty("check_status")
	private String checkStatus;


	@Override
	public String toString() {
		return "YunpianSmsTemplate{" +
				"reason='" + reason + '\'' +
				", countryCode='" + countryCode + '\'' +
				", tplContent='" + tplContent + '\'' +
				", tplId='" + tplId + '\'' +
				", lang='" + lang + '\'' +
				", checkStatus='" + checkStatus + '\'' +
				'}';
	}
}
