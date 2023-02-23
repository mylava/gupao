package com.gupao.edu.answer.client.exception;

public enum ErrorCode {
    //公用ERRCODE
	SYSTEM_ERROR("500","系统繁忙，请稍后再试"),

//	SYSTEM_ENCODER_PASSWORD_ERROR("501","密码加密错误..."),

	SYSTEM_GPUSER_EMPTY("502","用户未登录"),

	SYSTEM_PERMISSION_DENIED("503","权限不足"),

    SYSTEM_USER_BLACKLIST("504", "黑名单"),

    SYSTEM_TOKEN_ERROR("600", "TOKEN无效，请重新登录"),
    SYSTEM_ACCESS_TOKEN_INVALID("610", "ACCESS_TOKEN无效"),
    SYSTEM_ACCESS_TOKEN_DUE("611", "ACCESS_TOKEN过期, 请使用REFRESH_TOKEN刷新"),
    SYSTEM_ACCESS_SIGNATURE_INVALID("612", "该用户没有按照正确的签名规则访问"),
    SYSTEM_ACCESS_SIGNATURE_EXPIRE("613", "该用户的时间戳过期"),
    SYSTEM_REFRESH_TOKEN_INVALID("620", "REFRESH_TOKEN无效"),
    SYSTEM_REPETITION_INVALID("622", "重放攻击已被拦截"),

    SYSTEM_CAPTCHA_ERROR("700", "验证码错误"),

    SYSTEM_DOMAIN_ERROR("800", "授权域名无效，请联系管理员"),

    THIRD_PARTY_AUTH_ERROR("900", "第三方授权失败"),

    OPERATE_SUCC("100001", "操作成功"),
	OPERATE_FAIL("100002", "操作失败"),

    PARAMETER_EMPTY("200001", "参数为空"),
    PARAMETER_ERROR("200002", "参数错误"),
    PARAMETER_INCOMPLETE("200003", "参数不全"),
    PARAMETER_USER_EMPTY("200004", "用户名或密码为空"),
    PARAMETER_USER_ERROR("200005", "用户名或密码错误"),
    PARAMETER_USER_EMAIL_ERROR("200006", "邮箱错误"),

    IP_EVERY_MINUTE_VISIT_COUNT("701", "该IP地址访问过于频繁, 请稍后再试。"),
    IP_EVERY_DAY_VISIT_COUNT("702", "该IP地址因访问过于频繁已被限制访问, 如需帮助, 请联系客服。"),

    USER_QUESTION_LIMIT_NUM("710", "用户发表问题过于频繁, 请稍后再试。"),
    USER_ANSWER_LIMIT_NUM("711", "用户发表回答过于频繁, 请稍后再试。"),
    USER_ARTICLE_LIMIT_NUM("712", "用户发表文章过于频繁, 请稍后再试。"),
    ;

    private String code;
    private String message;
    private ErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
