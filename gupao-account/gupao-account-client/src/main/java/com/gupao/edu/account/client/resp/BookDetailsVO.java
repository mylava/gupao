package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 书籍详情VO
 * </p>
 *
 * @author wuzhenping
 * @since 2020-03-30
 */
@Data
public class BookDetailsVO {

	@ApiModelProperty(value = "主键ID", name = "id" ,required = true)
	private Integer id;

	@ApiModelProperty(value = "权益ID", name = "memberRightId" ,required = true)
	private Integer memberRightId;

	@ApiModelProperty(value = "资源分类：1书籍 2优惠券 3文化衫", name = "resourceType" ,required = true)
	private Integer resourceType;

	@ApiModelProperty(value = "资源名称", name = "name" ,required = true)
	private String name;

	@ApiModelProperty(value = "资源简介", name = "summary" ,required = true)
	private String summary;

	@ApiModelProperty(value = "资源图文描述，富文本应编码存储", name = "description" ,required = true)
	private String description;

}
