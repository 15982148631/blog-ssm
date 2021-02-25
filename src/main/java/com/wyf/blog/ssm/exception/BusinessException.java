package com.wyf.blog.ssm.exception;

/**
 * @Author wyf
 * @ClassName BusinessException
 * @Description 自定义异常类封装
 * @Date 2020/10/28 14:07
 * @Version 1.0.0
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    /**
     * 错误状态码
     */
    protected Integer errorCode;
    /**
     * 错误提示
     */
    protected String errorMsg;

    public BusinessException(){

    }

    public BusinessException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}