package com.chen.nots_web.exception;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-09-16 11-06
 */
@Data
public class BusinessException extends RuntimeException {

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    public BusinessException (int code, String message){
        this.code = code;
        this.message = message;
    }

    public BusinessException (String message){
        this.code = ErrorCode.SYSTEM_ERROR.getCode();
        this.message = message;
    }

    public BusinessException (ErrorCode errorCode){
        this(errorCode.getCode(), errorCode.getMessage());
    }

}
