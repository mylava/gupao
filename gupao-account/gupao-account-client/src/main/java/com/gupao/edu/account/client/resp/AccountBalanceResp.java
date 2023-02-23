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
 * @date: 2020/4/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("我的学币页面返回参数")
public class AccountBalanceResp {

    @ApiModelProperty(value = "学币余额，单位分", position = 1)
    private Integer balance;

    @ApiModelProperty(value = "学币列表", position = 2)
    private List<CoinVO> coinVOList;
}
