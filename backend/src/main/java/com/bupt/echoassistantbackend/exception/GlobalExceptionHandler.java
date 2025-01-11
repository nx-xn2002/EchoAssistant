package com.bupt.echoassistantbackend.exception;


import com.bupt.echoassistantbackend.common.BaseResponse;
import com.bupt.echoassistantbackend.common.ErrorCode;
import com.bupt.echoassistantbackend.common.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author nx
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<String> businessExceptionHandler(BusinessException e) {
        log.error("business error:", e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<String> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtime error:", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }
}
