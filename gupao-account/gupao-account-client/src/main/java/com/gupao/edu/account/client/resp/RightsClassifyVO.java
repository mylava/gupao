package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 会员权益分类VO
 * </p>
 *
 * @author wuzhenping
 * @since 2020-03-30
 */
@Data
public class RightsClassifyVO {

	@ApiModelProperty(value = "会员权益ID", name = "memberRightId" ,required = true)
	private Integer memberRightId;

	@ApiModelProperty(value = "权益名字", name = "rightsName" ,required = true)
	private String rightsName;

	@ApiModelProperty(value = "权益类型", name = "rightsType" ,required = true)
	private String rightsType;

	@ApiModelProperty(value = "权益详情展示列表", name = "rightsClassifyShowVOS" ,required = true)
	List<RightsClassifyShowVO> rightsClassifyShowVOS;

}
