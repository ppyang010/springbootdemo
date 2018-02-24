package com.code.auth.exception;

public enum ExceptionCode {
    //前三位模块
    //系统错误
    UNKOWN_ERROR(10000,"未知错误" ),
    INFO_IS_NULL(10001,"对应实体信息为空！"),
    //用户模块错误
    USERNAME_IS_EXIST(20101,"用户名以存在!"),
    USERINFO_IS_NULL(20102,"用户信息为空"),
    //角色模块异常
    ROLENAME_IS_EXIST(20201,"角色名已存在!"),
    //权限魔窟异常
    PERMISSIONS_IS_EXIST(203101,"权限已存在")
    ;

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