package com.code.auth.exception;

public enum ExceptionCode {
    //系统错误
    UNKOWN_ERROR(10000,"未知错误" ),
    //用户模块错误
    USERNAME_IS_EXIST(20001,"用户名以存在!"),
    USERINFO_IS_NULL(20002,"用户信息为空");

    private int  errorCode;
    private String errorMessage;

    ExceptionCode(int  errorCode, String errorMessage)
    {
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }



}