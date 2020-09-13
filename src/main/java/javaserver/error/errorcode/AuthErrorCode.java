package javaserver.error.errorcode;

import org.springframework.http.HttpStatus;

public enum AuthErrorCode implements IErrorCode {


    AUTH_VALIDATE_FAIL("0001", "auth validate fail", HttpStatus.BAD_REQUEST),

    ACCOUNT_HAS_EXISTED("0002", "account has existed , register fail", HttpStatus.BAD_REQUEST);

    private String errorCode;

    private String message;

    private HttpStatus httpStatus;


    AuthErrorCode(String errorCode, String message, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getStatus() {
        return this.httpStatus;
    }
}
