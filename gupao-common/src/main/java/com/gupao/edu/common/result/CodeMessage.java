package com.gupao.edu.common.result;

import lombok.Getter;
import lombok.ToString;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 11/05/2019
 */
@Getter
@ToString
public class CodeMessage {

    public static CodeMessage SUCCESS = new CodeMessage(0, "sucess");

    //通用响应码 500100开始
    public static CodeMessage SERVER_ERROR = new CodeMessage(500100, "服务端异常");
    public static CodeMessage PARAMS_EMPTY_ERROR = new CodeMessage(500101, "参数为空异常：%s");
    public static CodeMessage FEIGN_INVOKE_ERROR = new CodeMessage(500102,"远程调用失败：%s");
    public static CodeMessage VALIDATE_ERROR = new CodeMessage(500103,"参数错误：%s");
    public static CodeMessage REPEAT_REQUEST= new CodeMessage(500104,"重复请求:%s");
    public static CodeMessage RUNTIME_ERROR = new CodeMessage(500106, "请求异常:%s");

    //用户模块
    public static CodeMessage ILLEGAL_TOKEN_ERROR = new CodeMessage(500201, "TOKEN状态异常：[%s]");
    public static CodeMessage HAS_NO_TOKEN_ERROR = new CodeMessage(500202, "用户尚未登录：%s");
    public static CodeMessage LOGIN_TOKEN_EXPIRED = new CodeMessage(500203,"Token已过期");
    public static CodeMessage LOGIN_TOKEN_INCORRECT = new CodeMessage(500204,"无效Token");
    public static CodeMessage LOGIN_USER_NOT_EXIST = new CodeMessage(500205,"用户不存在");
    public static CodeMessage LOGIN_PASSWORD_INCORRECT = new CodeMessage(500206,"用户名或密码错误");
    public static CodeMessage LOGIN_STATE_INCORRECT = new CodeMessage(500207,"用户被冻结");
    public static CodeMessage ILLEGAL_STATUS_ERROR = new CodeMessage(500208,"用户状态异常：%s");
    public static CodeMessage THIRD_PARTY_AUTH_ERROR= new CodeMessage(500210, "第三方授权失败:%s");
    public static CodeMessage THIRD_PARTY_AUTH_NULL= new CodeMessage(500211, "授权账号为空:%s");
    public static CodeMessage LOGIN_OUT = new CodeMessage(500205,"退出失败");
    public static CodeMessage UPDATE_USER_FAIL = new CodeMessage(500209,"更新用户失败");



    public static CodeMessage REGISTER_USER_HAS_EXIST = new CodeMessage(500212, "用户已注册,请勿重复注册!");
    public static CodeMessage REGISTER_FAIL = new CodeMessage(500213, "注册失败!");
    public static CodeMessage BINDMOBILE_FAIL = new CodeMessage(500214, "换绑手机号失败!");
    public static CodeMessage LOGIN_MOBILE_NULL = new CodeMessage(500215,"手机号不能为空!");
    public static CodeMessage MOBILE_INCORRECT = new CodeMessage(500234,"手机格式不正确!");
    public static CodeMessage MOBILE_SAME = new CodeMessage(500235,"手机号不能相同!");
    public static CodeMessage UPDATE_FAIL = new CodeMessage(500216,"修改密码失败!");
    public static CodeMessage UPDATE_UNBIND = new CodeMessage(500217,"解除绑定失败!");
    public static CodeMessage UPDATE_BIND = new CodeMessage(500218,"绑定失败!");
    public static CodeMessage QQ_UPDATE_ALREADY = new CodeMessage(500219,"该qq已经绑定!");
    public static CodeMessage WECHAT_UPDATE_ALREADY = new CodeMessage(500220,"该微信已经绑定!");
    public static CodeMessage UPDATE_FAIL_PASSWORD = new CodeMessage(500221,"原始密码不正确，无法修改!");
    public static CodeMessage USER_NOT_FOUND = new CodeMessage(500222,"根据用户编码查询不出用户信息!");
    //短信
    public static CodeMessage SMS_SEND_FAIL = new CodeMessage(500230,"短信发送失败:%s");
    //短信验证码已过期
    public static CodeMessage TIMEOUT_VERIFY_CODE_CHECK = new CodeMessage(500231,"短信验证码已过期");
    public static CodeMessage VERIFY_CODE_FAIL_CHECK = new CodeMessage(500232,"短信验证码验证失败");

    //发送私信 用户校验
    public static CodeMessage SENDER_LETTER_USER_NOT_EXIST = new CodeMessage(500233,"发送方用户不存在");
    public static CodeMessage RECEIVEDER_LETTER_USER_NOT_EXIST = new CodeMessage(500233,"发送方用户不存在");

    //会员管理
    public static CodeMessage MEMBER_MANAGEMENT_EXCEPTION = new CodeMessage(500400, "会员管理异常");
    public static CodeMessage GAIN_RESOURCE_FAIL = new CodeMessage(500401, "领取会员权益失败");
    public static CodeMessage MEMBER_RIGHT_RESOURCE_DETAIL_FAIL = new CodeMessage(500402, "书籍不存在");

    //课程
    public static CodeMessage CORS_SAVE_FAIL = new CodeMessage(500500, "新增学科失败");
    public static CodeMessage CORS_FAVORITE_FAIL = new CodeMessage(500501, "收藏课程失败");
    public static CodeMessage CORS_SHARE_FAIL = new CodeMessage(500502, "分享课程失败");
    public static CodeMessage CORS_FAVORITE_REPEAT_FAIL = new CodeMessage(500502, "重复收藏课程");
    public static CodeMessage COMMENT_FAIL = new CodeMessage(500505, "评论失败");
    public static CodeMessage CORS_LECTURER_NOT_EXISTS = new CodeMessage(500506, "讲师信息不存在");
    public static CodeMessage CORS_COURSE_NOT_EXISTS = new CodeMessage(500507, "课程信息不存在");
    //课程问答
    public static CodeMessage CORS_VIDEO_ASK_NOT_FOUND = new CodeMessage(500900, "根据问答id获取不到问答信息!");
    public static CodeMessage CORS_VIDEO_ASK_REPLY_TYPE_FAIL = new CodeMessage(500901, "回答的赞或踩的状态不正确!");
    public static CodeMessage CORS_VIDEO_ASK_REPLY_CANCEL_TYPE_FAIL = new CodeMessage(500902, "回答的赞或踩的是否取消状态不正确!");
    public static CodeMessage CORS_VIDEO_ASK_REPLY_OPERATED = new CodeMessage(500903, "你已经点赞或踩,不能继续操作!");
    public static CodeMessage CORS_VIDEO_ASK_REPLY_PRAISE_NOT_FOUND = new CodeMessage(500903, "你没有点赞或踩,不能取消!");
    public static CodeMessage CORS_VIDEO_ASK_REPLY_NOT_FOUND = new CodeMessage(500904, "根据回复id查询不出回复信息!");
    public static CodeMessage CORS_VIDEO_ASK_REPLY_COMMENT_NOT_FOUND = new CodeMessage(500904, "根据评论id查询不出评论信息!");
    //课程视频
    public static CodeMessage CORS_VIDEO_NOT_FOUND = new CodeMessage(5001000, "根据视频id获取不到视频信息!");

    /**
    *  订单模块
    */
	public static CodeMessage ORDER_NOT_EXIST = new CodeMessage(500301,"查询订单不存在:%s");
	public static CodeMessage LOGISTICS_HTTP_ERROR = new CodeMessage(500302,"查询物流信息通信异常:%s");
    public static CodeMessage ORDER_NOT_ERROR = new CodeMessage(500303, "订单参数错误%s");
    public static CodeMessage ORDER_NOT_REPEAT = new CodeMessage(500304, "请不要重复提交订单，%s");
    public static CodeMessage ORDER_NOT_GOODS = new CodeMessage(500305, "未查询到商品信息，%s");
    public static CodeMessage ORDER_NOT_USED_COUPON = new CodeMessage(500306, "该优惠券不可用，%s");

	//文章模块
	public static CodeMessage PARAMETER_EMPTY = new CodeMessage(500601, "参数为空%s");
	public static CodeMessage PARAMETER_ERROR = new CodeMessage(500602, "参数错误%s");
	public static CodeMessage ARTICLE_ERROR = new CodeMessage(500603, "文章参数异常%s");
	public static CodeMessage VALIDITY_FAILED = new CodeMessage(500604, "参数校验失败%s");

    //问答模块
    public static CodeMessage QUESTION_TITLE_NOT_NULL = new CodeMessage(500701, "问答标题不能为空");
    public static CodeMessage QUESTION_DESCRIPTION_NOT_NULL = new CodeMessage(500702, "问答内容不能为空");

    //用户地址管理
    public static CodeMessage USER_ADDRESS_DETAIL_EXCEPTION = new CodeMessage(500800, "用户地址管理异常");

    //作业模块
    public static CodeMessage COURSE_HOMEWORK_EXCEPTION = new CodeMessage(500900, "获取作业详情及其作业回答列表异常");
    public static CodeMessage COURSE_UPDATE_HOMEWORK_REPLY_EXCEPTION = new CodeMessage(500901, "提交/修改我的作业异常");
    public static CodeMessage COURSE_COMMENT_HOMEWORK_REPLY_EXCEPTION = new CodeMessage(500902, "评论作业异常");
    public static CodeMessage COURSE_PRAISE_HOMEWORK_REPLY_EXCEPTION = new CodeMessage(500903, "作业评论的点赞与踩异常");
    public static CodeMessage COURSE_SCORE_HOMEWORK_REPLY_EXCEPTION = new CodeMessage(500904, "作业评论的点赞与踩异常");

    //优惠券模块
    public static CodeMessage RECEIVE_EXCEPTION = new CodeMessage(600100, "领取优惠券异常");


    //不能为空校验
    public static CodeMessage PARAM_NAME_NOT_NULL = new CodeMessage(999999, "%s不能为空");
    public static CodeMessage VALID_PARAM_NAME_NOT_NULL = new CodeMessage(999998, "%s");



    private int code;
    private String message;

    public CodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeMessage fillArgs(Object... args) {
        this.message = String.format(this.message, args);
        return this;
    }

    /**
     * 动态替换
     * @param param
     * @param flag 是否拼接是否为空
     * @return
     */
    public CodeMessage fillParam(String param, boolean flag) {
        this.message = "";
        if(flag) {
            this.message = param + "不能为空";
        } else {
            this.message = param;
        }
        return this;
    }

//    public static void main(String[] args) {
//        Result ss = Result.fail(CodeMessage.PARAM_NAME_NOT_NULL.fillArgs("courseId"));
//        Result ss2 = Result.fail(CodeMessage.PARAM_NAME_NOT_NULL.fillArgs("userUniqueCode"));
//        System.out.println(ss);
//        System.out.println(ss2);
//    }
}
