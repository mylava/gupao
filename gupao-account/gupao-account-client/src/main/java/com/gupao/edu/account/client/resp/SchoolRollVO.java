package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 15/04/2020
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学籍列表项实体")
public class SchoolRollVO {

    @ApiModelProperty(value = "课程名称", position = 1)
    private String courseName;

    @ApiModelProperty(value = "审核状态：-1:审核不通过，0:审核中，1:审核通过", position = 2)
    private Integer auditState;

    @ApiModelProperty(value = "入学时间", position = 3)
    private LocalDate joinTime;

    @ApiModelProperty(value = "学员协议", position = 4)
    private String protocol;

    @ApiModelProperty(value = "入学手册", position = 5)
    private String notebook;
}
