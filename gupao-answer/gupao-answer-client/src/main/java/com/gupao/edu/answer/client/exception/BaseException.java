package com.gupao.edu.answer.client.exception;

public class BaseException extends Exception{
    private String errCode;
    private String errMsg;

    public BaseException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BaseException(ErrorCode errorCodeObj) {
        this.errCode = errorCodeObj.getCode();
        this.errMsg = errorCodeObj.getMessage();
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
