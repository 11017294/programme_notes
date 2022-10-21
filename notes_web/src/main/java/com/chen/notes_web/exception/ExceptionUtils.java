package com.chen.notes_web.exception;

import org.springframework.util.StringUtils;

/**
 * <p>
 *  异常工具类
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-10-21 09-42
 */
public class ExceptionUtils {

    private ExceptionUtils() {
    }

    /**
     * isTrue
     * @param value
     * @param message
     * @throws BusinessException
     */
    public static void isTrue(boolean value, String message) {
        if(value){
            throw new BusinessException(message);
        }
    }

    public static void isTrue(boolean value,int code, String message) {
        if(value){
            throw new BusinessException(code, message);
        }
    }

    public static void isTrue(boolean value, ErrorCode errorCode) {
        if(value){
            throw new BusinessException(errorCode);
        }
    }

    /**
     * isNull
     * @param value
     * @param message
     * @throws BusinessException
     */
    public static void isNull(Object value, String message) {
        if(value == null){
            throw new BusinessException(message);
        }
    }

    public static void isNull(Object value,int code, String message) {
        if(value == null){
            throw new BusinessException(code, message);
        }
    }

    public static void isNull(Object value, ErrorCode errorCode) {
        if(value == null){
            throw new BusinessException(errorCode);
        }
    }

    /**
     * notNull
     * @param value
     * @param message
     * @throws BusinessException
     */
    public static void notNull(Object value, String message) {
        if(value != null){
            throw new BusinessException(message);
        }
    }

    public static void notNull(Object value,int code, String message) {
        if(value != null){
            throw new BusinessException(code, message);
        }
    }

    public static void notNull(Object value, ErrorCode errorCode) {
        if(value != null){
            throw new BusinessException(errorCode);
        }
    }

    /**
     * isBlank
     * @param value
     * @param message
     * @throws BusinessException
     */
    public static void isBlank(String value, String message) {
        if(!StringUtils.hasText(value)){
            throw new BusinessException(message);
        }
    }

    public static void isBlank(String value,int code, String message) {
        if(!StringUtils.hasText(value)){
            throw new BusinessException(code, message);
        }
    }

    public static void isBlank(String value, ErrorCode errorCode) {
        if(!StringUtils.hasText(value)){
            throw new BusinessException(errorCode);
        }
    }

}
