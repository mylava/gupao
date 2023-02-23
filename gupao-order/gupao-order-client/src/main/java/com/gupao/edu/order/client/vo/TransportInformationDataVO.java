package com.gupao.edu.order.client.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-03-24 23:40
 */
@Data
public class TransportInformationDataVO {

    @ApiModelProperty(value = "物流信息结点", name = "context",  required = true)
    private  String  context;
    @ApiModelProperty(value = "起始时间", name = "time",  required = true)
    private  String  time;
    @ApiModelProperty(value = "截止时间", name = "ftime",  required = true)
    private  String  ftime;
}
