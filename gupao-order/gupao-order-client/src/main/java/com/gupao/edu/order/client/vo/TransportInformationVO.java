package com.gupao.edu.order.client.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 物流信息 vo
 * @author wzq.Jolin
 * @company none
 * @create 2020-03-24 23:34
 */
@Data
public class TransportInformationVO {

    /**
     * 快递单当前状态，包括0在途，1揽收，2疑难，3签收，4退签，5派件，6退回，7转投 等8个状态
     */
    @ApiModelProperty(value = "快递单当前状态，包括0在途，1揽收，2疑难，3签收，4退签，5派件，6退回，7转投 等8个状态", name = "state",  required = true)
    private   Integer state;

    /**
     * 快递单明细状态标记，暂未实现，请忽略
     */
    @ApiModelProperty(value = "快递单明细状态标记，暂未实现，请忽略", name = "condition",  required = true)
    private   String condition;
    /**
     * 是否标记签收
     */
    @ApiModelProperty(value = "是否标记签收", name = "ischeck",  required = true)
    private   Integer ischeck;
    /**
     * 快递公司编码,一律用小写字母
     */
    @ApiModelProperty(value = "快递公司编码，一律用小写字母", name = "com",  required = true)
    private   String com;

	@ApiModelProperty(value = "快递公司名称", name = "companyName",  required = true)
    private   String companyName;
    /**
     * 快递单号
     */
    @ApiModelProperty(value = "快递单号", name = "nu",  required = true)
    private   String nu;

    private List< TransportInformationDataVO >  dataVOList;
}
