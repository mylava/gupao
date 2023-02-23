package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 收藏课程VO
 * </p>
 *
 * @author wuzhenping
 * @since 2020-03-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("收藏课程信息")
public class CourseFavoriteVO {

	@ApiModelProperty(value = "课程ID", name = "courseId" ,required = true)
	private Integer courseId;

	@ApiModelProperty(value = "课程名", name = "courseName" ,required = true)
	private String courseName;

	@ApiModelProperty(value = "课程封面图片", name = "image")
	private String image;

//	@ApiModelProperty(value = "观看人数", name = "viewNum")
//	private Integer viewNum;
//
//	@ApiModelProperty(value = "已学人数", name = "studyNum")
//	private Integer studyNum;

	@ApiModelProperty(value = "评论人数", name = "commentNum")
	private Integer commentNum;

	@ApiModelProperty(value = "关注人数（收藏人数）", name = "attentionNum")
	private Integer attentionNum;

	@ApiModelProperty(value = "现价，有效价格", name = "nowPrice")
	private Double nowPrice;

}
