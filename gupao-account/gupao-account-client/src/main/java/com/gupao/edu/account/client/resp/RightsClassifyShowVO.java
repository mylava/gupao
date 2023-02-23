package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 会员权益详情展示VO
 * </p>
 *
 * @author wuzhenping
 * @since 2020-03-30
 */
@Data
public class RightsClassifyShowVO {

	@ApiModelProperty(value = "会员资源id", name = "memberRightResourceId" ,required = true)
	private Integer memberRightResourceId;

	@ApiModelProperty(value = "权益ID", name = "memberRightId" ,required = true)
	private Integer memberRightId;

	@ApiModelProperty(value = "资源分类：1书籍 2优惠券 3文化衫", name = "resourceType" ,required = true)
	private Integer resourceType;

	@ApiModelProperty(value = "关联其他资源ID,比如优惠券模板ID", name = "resourceId" ,required = true)
	private Integer resourceId;

	@ApiModelProperty(value = "资源名称", name = "name" ,required = true)
	private String name;

	@ApiModelProperty(value = "资源图文描述", name = "description" ,required = true)
	private String description;

}
