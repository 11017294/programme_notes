package com.chen.notes_web.global.restControllerAdvice;

import com.chen.notes_web.exception.BusinessException;
import com.chen.notes_web.exception.ErrorCode;
import com.chen.notes_web.vo.BaseResponse;
import com.chen.notes_web.vo.ResultUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <p>
 *
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-09-16 14-25
 */
@RestControllerAdvice("com.chen.notes_web.controller")
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 类和方法上被标注，不进行拦截封装
//        if (methodParameter.getDeclaringClass().isAnnotationPresent(NotControllerResponseAdvice.class)
//                || methodParameter.getMethod().isAnnotationPresent(NotControllerResponseAdvice .class)){
//            return false;
//        }
        return !methodParameter.getParameterType().isAssignableFrom(BaseResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String类型不能直接包装
        if (methodParameter.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVo里后转换为json串进行返回
                return objectMapper.writeValueAsString(ResultUtils.success(data));
            } catch (JsonProcessingException e) {
                throw new BusinessException(ErrorCode.RESPONSE_PACK_ERROR);
            }
        }
        // 否则直接包装成ResultVo返回
        return ResultUtils.success(data);
    }

}
