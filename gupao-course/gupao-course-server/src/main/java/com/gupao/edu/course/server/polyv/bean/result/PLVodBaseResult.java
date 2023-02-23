package com.gupao.edu.course.server.polyv.bean.result;

/**
 * 响应结果基础类
 *
 * @author zhangpan@centaline.com
 * createTime 2020/5/6
 */
public abstract class PLVodBaseResult {

    /**
     * <pre>
     * 字段名：响应状态码
     * 变量名：code
     * 是否必填：是
     * </pre>
     */
    protected int code;

    /**
     * <pre>
     * 字段名：响应状态
     * 变量名：status
     * 是否必填：是
     * </pre>
     */
    protected String status;

    /**
     * <pre>
     * 字段名：响应信息
     * 变量名：message
     * 是否必填：是
     * </pre>
     */
    protected String message;

    /**
     * 判断请求是否成功
     *
     * @return true/false
     */
    public boolean isRequestOk() {
        return 200 == this.code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = "".equals(message) ? "null" : message;
    }

    @Override
    public String toString() {
        return "PLVodBaseResult{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
