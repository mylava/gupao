package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 15/04/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("我的学籍返回值")
public class SchoolRollResp {

    @ApiModelProperty(value = "学号", position = 1)
    private String studentNo;

    @ApiModelProperty(value = "头像", position = 2)
    private String avatar;

    @ApiModelProperty(value = "学籍列表", position = 3)
    private List<SchoolRollVO> schoolRollVOList;
}
