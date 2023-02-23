package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * comment: 办理入学参数
 *
 * @author: lipengfei
 * @date: 17/04/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntranceReq {

    /**
     * 用户唯一码
     */
    private String userUniqueCode;

    @ApiModelProperty(value = "课程ID", required = true, position = 1)
    private Integer courseId;

    @ApiModelProperty(value = "推荐老师ID", required = true, position = 2)
    private Integer salesmanId;

    @ApiModelProperty(value = "姓名", required = true, position = 3)
    private String name;

    @ApiModelProperty(value = "生日", required = true, position = 4)
    private LocalDate birthday;

    @ApiModelProperty(value = "性别：1-男 2-女", required = true, position = 5)
    private Integer gender;

    @ApiModelProperty(value = "省ID", required = true, position = 6)
    private Integer provinceId;

    @ApiModelProperty(value = "市ID", required = true, position = 7)
    private Integer cityId;

    @ApiModelProperty(value = "学历ID", required = true, position = 8)
    private Integer education;

    @ApiModelProperty(value = "QQ号", required = true, position = 9)
    private String qq;

    @ApiModelProperty(value = "所在公司", required = true, position = 10)
    private String employer;

    @ApiModelProperty(value = "工作年限", required = true, position = 11)
    private Integer seniority;

    @ApiModelProperty(value = "职位名称", required = true, position = 12)
    private Integer jobTitle;

    @ApiModelProperty(value = "目前年薪:单位万", required = true, position = 13)
    private Integer currentSalary;

    @ApiModelProperty(value = "期望年薪:单位万", required = true, position = 14)
    private Integer expectSalary;

    @ApiModelProperty(value = "试听讲师ID", required = true, position = 15)
    private Integer lectureId;

    @ApiModelProperty(value = "推荐学员id", required = true, position = 16)
    private Integer recommendUserId;

    @ApiModelProperty(value = "了解渠道", required = true, position = 17)
    private Integer realizeChannel;

    @ApiModelProperty(value = "试听次数", required = true, position = 18)
    private Integer auditionCounts;

    @ApiModelProperty(value = "需解决的问题", required = false, position = 19)
    private String solveProblem;

}
