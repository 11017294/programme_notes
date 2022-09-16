package com.chen.nots_web.vo;

import com.chen.nots_web.exception.ErrorCode;

/**
 * <p>
 *  返回类 工具类
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-09-16 11-19
 */
public class ResultUtils {

    /**
     * 返回成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, data, "ok");
    }

    /**
     * 错误
     * @param code
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> error(int code, String errorMessage) {
        return new BaseResponse<>(code, null, errorMessage);
    }

    /**
     * 错误
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> error(String errorMessage) {
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR.getCode() ,errorMessage);
    }
}
