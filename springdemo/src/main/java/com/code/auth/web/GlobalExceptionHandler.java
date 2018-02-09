package com.code.auth.web;

import com.code.auth.domain.PlatformReturn;
import com.code.auth.exception.CodeException;
import com.code.auth.exception.ExceptionCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * 统一异常处理
 * @author caicy
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CodeException.class)
    @ResponseBody
    public PlatformReturn codeExceptionHandler(CodeException e, NativeWebRequest request){
        return PlatformReturn.failure(e);
    }

    /**
     * 捕获未知错误
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public PlatformReturn codeExceptionHandler(Exception e, NativeWebRequest request){
        return PlatformReturn.failure(new CodeException(ExceptionCode.UNKOWN_ERROR));
    }

}
