package com.code.auth.exception;

public class CodeException extends RuntimeException {

    private static final long serialVersionUID = -5519288243296538690L;

    private int errorCode = 0;

    public CodeException(ExceptionCode errorCode, String errorMsg) {
        super(errorCode.getErrorMessage() + " " + errorMsg);
        this.errorCode = errorCode.getErrorCode();
    }

    public CodeException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public CodeException(ExceptionCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode.getErrorCode();
    }

    public CodeException(ExceptionCode errorCode, Throwable thrower) {
        super(errorCode.getErrorMessage(), thrower);
        this.errorCode = errorCode.getErrorCode();
    }

    @Override
    public String getMessage() {
        if (this.errorCode < 1) {
            return super.getMessage();
        }
        return "error code :" + errorCode + ", error message:"
                + super.getMessage();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
