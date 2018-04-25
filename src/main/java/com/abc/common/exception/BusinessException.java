package com.abc.common.exception;

/**
 * 业务验证异常类
 * Created by ChenShi on 2017/7/25 0025.
 */
public class BusinessException extends Exception{

    private static final long serialVersionUID = 1L;

    private String code;

    public BusinessException() {
        super();
    }
    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
