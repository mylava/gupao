package com.gupao.edu.course.server.polyv.enumeration;

/**
 * 错误代码及默认值
 *
 * @author zhangpan@centaline.com
 * createTime 2020/5/6
 */
public enum ErrorCodeEnum {
    DEFAULT(-99999, "int类型默认值"),
    ;

    /**
     * <pre>
     * 字段名：错误代码/默认值
     * 变量名：code
     * 是否必填：是
     * </pre>
     */
    private int code;

    /**
     * <pre>
     * 字段名：提示信息
     * 变量名：msg
     * 是否必填：是
     * </pre>
     */
    private String msg;

    ErrorCodeEnum(int code, String msg) {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ErrorCodeEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
