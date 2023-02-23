package com.gupao.edu.order.client.request;




import com.gupao.edu.order.client.entity.OrderSupplement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author lipengfei
 * @since 2020-03-17
 */
@ApiModel(value = "创建订单集合")
@Data
public class SubmitOrdersReq   implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 用户id
     */
    @ApiModelProperty(value="用户全局唯一标识", name="userUniqueCode",  required = true)
    private String userUniqueCode;
    /**
     * 支付方式 1:微信 2:支付宝
     */
    @ApiModelProperty(value="支付方式 1:微信 2:支付宝 3:苹果支付,4学币支付", name="payMethod",  required = true)
    private Integer payMethod;
    /**
     * 买家留言
     */
    @ApiModelProperty(value="买家留言", name="leftMsg",  required = false)
    private String leftMsg;
    /**
     * 优惠券id
     */
    @ApiModelProperty(value="优惠券id", name="couponId", example = "1",required = false)
    private Integer couponId;
    /**
     * 渠道 区分三方渠道过来的订单
     */
    @ApiModelProperty(value="渠道  1:PC，2:APP，3：小程序 4腾讯课堂 5网易课堂", name="clientSource",  required = true)
    private String clientSource;
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品唯一标识，多个商品用 ','隔开 ", name = "itemId",  required = true)
    private String itemId;



    @ApiModelProperty(value = "订单补充消息，三方渠道必传", name = "ordersSupplement",  required = false)
    private OrderSupplementReq orderSupplementReq;
    /**
     * 商品详情
     */
//    @ApiModelProperty(value="商品详情", name="detailsList")
//    List< OrderItemDetailsReq >   detailsList;





}
