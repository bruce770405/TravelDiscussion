package javaserver.error;

import javaserver.error.errorcode.IErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * Restful api 用 exception.
 * </p>
 *
 * @author BruceHsu
 * @version 1.0, 2018年6月4日
 */
@Getter
public class RestfulException extends RuntimeException {

    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String errorMessage;

    private HttpStatus httpStatus;

    public RestfulException() {
        super();
    }

    public RestfulException(IErrorCode errorCode) {
        super();
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getMessage();
        this.httpStatus = errorCode.getStatus();
    }

    public RestfulException(String errorMessage, HttpStatus httpStatus, Exception e) {
        super();
        this.errorCode = "9999";
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
