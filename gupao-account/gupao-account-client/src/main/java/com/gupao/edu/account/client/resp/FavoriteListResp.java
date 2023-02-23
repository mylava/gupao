package com.gupao.edu.account.client.resp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 2020/4/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("我的收藏列表返回值")
public class FavoriteListResp {
    @ApiModelProperty(value = "收藏的课程数", position = 1)
    private Integer courseNum;

    @ApiModelProperty(value = "收藏的问答数", position = 2)
    private Integer askNum;

    @ApiModelProperty(value = "收藏的文章数", position = 3)
    private Integer articleNum;

    @ApiModelProperty(value = "收藏的课程列表", position = 4)
    private IPage<CourseFavoriteVO> courses;

    @ApiModelProperty(value = "收藏的问答列表", position = 5)
    private IPage<CourseFavoriteVO> asks;

    @ApiModelProperty(value = "收藏的文章列表", position = 6)
    private IPage<CourseFavoriteVO> articles;
}
