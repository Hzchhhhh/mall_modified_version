package com.example.mall_modified_version.exception;

/**
 * 非法操作异常
 * 会返回HTTP 403
 */
public class IllegalOperationException extends RuntimeException {

    public IllegalOperationException(String message) {
        super(message);
    }
}