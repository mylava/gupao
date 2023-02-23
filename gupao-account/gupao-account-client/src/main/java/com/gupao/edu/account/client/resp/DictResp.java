package com.gupao.edu.account.client.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字典响应
 *
 * @author leon
 * @date 2020-04-30 17:28:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictResp {
	private String name;
	private String value;
}
