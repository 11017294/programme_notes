package com.chen.nots_web.vo;

import lombok.Data;

/**
 * <p>
 *  通用返回类
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-09-16 11-16
 */
@Data
public class BaseResponse<T> {

    private int code;
    private T data;
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

}
