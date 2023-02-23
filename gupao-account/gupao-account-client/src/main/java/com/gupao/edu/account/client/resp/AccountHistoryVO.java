package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 2020/4/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学币明细返回参数")
public class AccountHistoryVO {

    @ApiModelProperty(value = "金额", position = 1)
    private Integer balance;

    @ApiModelProperty(value = "描述", position = 2)
    private String describes;

    @ApiModelProperty(value = "交易时间", position = 3)
    private LocalDateTime dealTime;
}
