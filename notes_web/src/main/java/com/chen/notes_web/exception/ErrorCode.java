package com.chen.notes_web.exception;

import lombok.Getter;

/**
 * <p>
 *  错误码枚举
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-09-16 11-08
 */
@Getter
public enum ErrorCode {

    REQUEST_PARAMS_ERROR(40000, "请求参数错误"),

    SYSTEM_ERROR(50000, "系统错误"),

    RESPONSE_PACK_ERROR(1003, "response返回包装失败"),

    // 资源冲突，或者资源被锁
    CONFLICT(40009, "资源冲突"),

    NOT_FOUND(40004, "未找到资源");

    private final int code;

    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
