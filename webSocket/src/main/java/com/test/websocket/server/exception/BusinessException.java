package com.test.websocket.server.exception;

/**
 * Create by lxp
 * Date: 2023/2/15 14:21
 * ClassName:
 * Description:
 * 业务异常
 *
 * @author lxp
 */
public class BusinessException extends Exception {
    public BusinessException() {
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

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
