package com.gupao.edu.order.client.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-03-23 11:06
 */
@ApiModel("订单列表请求集合")
@Data
public class QueryOrdersListReq   extends OrderBaseReq {


    /**
     * 交易成功时间 交易成功时间
     */
    @ApiModelProperty(value="交易成功时间-开始时间 ", name="successTimeBegin",example = "yyyy-MM-dd HH:mm:ss", required = false)
    private String successTimeBegin;
    @ApiModelProperty(value="交易成功时间-截止时间 ", name="successTimeBegin",example = "yyyy-MM-dd HH:mm:ss", required = false)
    private String successTimeEnd;
    /**
     * 订单号
     */
    @ApiModelProperty(value="订单id", name="successTimeBegin",example = "12321456", required = false)
    private String orderId;


    /**
     * 手机号
     */
    @ApiModelProperty(value="订单id", name="successTimeBegin",example = "12321456", required = false)
    private String mobile;
    /**
     * qq
     */
    @ApiModelProperty(value="订单id", name="successTimeBegin",example = "12321456", required = false)
    private String qq;

    /**
     * 支付方式 1:微信 2:支付宝 3：苹果支付
     */
    @ApiModelProperty(value="支付方式 1:微信 2:支付宝 3：苹果支付 ", name="payMethod",example = "1", required = true)
    private Integer payMethod;

    /**
     * 订单状态
     */
    @ApiModelProperty(value="订单id", name="successTimeBegin",example = "12321456", required = false)
    private String orderStatus;
    /**
     * 姓名
     */
    @ApiModelProperty(value="订单id", name="successTimeBegin",example = "12321456", required = false)
    private Integer nickName;

    @ApiModelProperty(value="收货人姓名", name="receiverName",  required = false)
    private String receiverName;
    /**
     * 课程id
     */
    @ApiModelProperty(value="订单id", name="itemId",example = "12321456", required = false)
    private String itemId;


    /**
     * 商品类型
     */
    @ApiModelProperty(value = "商品类型", name = "itemType",example = "1", required = true)
    private String itemType;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", name = "itemName",  example = "商品名称",required = false)
    private String itemName;
    /**
     * 学科类型
     */
    @ApiModelProperty(value="学科类型", name="vipCourseType",example = "2", required = false)
    private Integer vipCourseType;







}
