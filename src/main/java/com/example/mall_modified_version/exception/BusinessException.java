package com.example.mall_modified_version.exception;

import com.example.mall_modified_version.enums.ResponseEnum;

/**
 * 业务异常
 * 构造函数参数是ResultCodeEnum，说明业务异常有多种类型
 * 会返回HTTP 200,但是会在response的body部分标注请求失败及失败原因
 */
public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(ResponseEnum responseEnum) {
        super(responseEnum.getDesc());
        this.code = responseEnum.getCode();
    }


    public BusinessException(ResponseEnum responseEnum, String message) {
        super(message);
        this.code = responseEnum.getCode();
    }

    public int getCode() {
        return code;
    }


}
