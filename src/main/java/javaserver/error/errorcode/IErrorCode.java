package javaserver.error.errorcode;

import org.springframework.http.HttpStatus;

public interface IErrorCode {

    String getErrorCode();

    String getMessage();

    HttpStatus getStatus();
}
