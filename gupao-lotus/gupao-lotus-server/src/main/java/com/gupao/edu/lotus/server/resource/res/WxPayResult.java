package com.gupao.edu.lotus.server.resource.res;

import lombok.Data;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-05-02 15:45
 */
@Data
public class WxPayResult {

    private String return_code;				// 返回状态码
    private String appid;					// 公众账号ID
    private String mch_id;					// 商户号
    private String nonce_str;				// 随机字符串
    private String sign;					// 签名
    private String result_code;				// 业务结果
    private String openid;					// 用户标识
    private String trade_type;				// 交易类型
    private String bank_type;				// 付款银行
    private int total_fee;					// 总金额
    private int cash_fee;					// 现金支付金额
    private String transaction_id;			// 微信支付订单号
    private String out_trade_no;			// 商户订单号
    private String time_end;				// 支付完成时间
    private String return_msg;				// 返回信息
    private String device_info;				// 设备号
    private String err_code;				// 错误代码
    private String err_code_des;			// 错误代码描述
    private String is_subscribe;			// 是否关注公众账号
    private String fee_type;				// 货币种类
    private String cash_fee_type;			// 现金支付货币类型
    private String coupon_fee;				// 代金券或立减优惠金额
    private String coupon_count;			// 代金券或立减优惠使用数量
    private String coupon_id_$n;			// 代金券或立减优惠ID
    private String coupon_fee_$n;			// 单个代金券或立减优惠支付金额
    private String attach;					// 商家数据包
}
