package com.code.auth.web;

import com.code.auth.domain.PlatformReturn;
import com.code.auth.exception.CodeException;
import com.code.auth.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 * @author caicy
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param e
     * @param request
     * @return
     */
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
        //其实可以在参数中直接获取这个对象 这里只是进行一次尝试
        HttpServletRequest httpServletRequest = request.getNativeRequest(HttpServletRequest.class);
        log.error("未知异常！！！ url={} !!! error message is {}", httpServletRequest.getRequestURL().toString(),e.toString());
        return PlatformReturn.failure(new CodeException(ExceptionCode.UNKOWN_ERROR));
    }

    /**
     *权限异常捕获
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public String authExceptionHandler(Exception e, NativeWebRequest request){
        return "/403";
    }
}
