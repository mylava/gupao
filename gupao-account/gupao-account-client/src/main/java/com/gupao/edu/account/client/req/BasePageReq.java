package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Class: 请求分页参数
 *
 * @Author: wangzhong
 * @Date: 2020-03-27 22:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePageReq implements Serializable {

	@ApiModelProperty(value = "第几页", required = true)
	private Integer page = 1;

	@ApiModelProperty(value = "每页数量", required = true)
	private Integer pageNum = 10;

	@ApiModelProperty(value = "唯一编码", name = "userUniqueCode",required = true)
	@NotBlank(message = "用户唯一编码不能为空")
	private String userUniqueCode;
}