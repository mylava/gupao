package com.gupao.edu.order.client.vo;

import lombok.Data;

import java.util.List;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-03 20:03
 */
@Data
public class OrderDetailsVO {

    private List<OrderListVO>  orderInfoVOList;

    private   TransportInformationVO  transportInformationVO;
}
