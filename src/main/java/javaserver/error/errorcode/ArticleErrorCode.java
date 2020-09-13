package javaserver.error.errorcode;


import org.springframework.http.HttpStatus;

/**
 * <p> TODO description</p>
 *
 * @author BruceHsu
 * @version 1.0, 2018年5月27日
 */
public enum ArticleErrorCode implements IErrorCode {

    ARTICLE_SAVE_FAIL("0001", "article save fail", HttpStatus.BAD_REQUEST),

    ARTICLE_DELETE_FAIL_BECAUSE_DONT_HAVE_AUTH("0002", "dont have auth to delete article", HttpStatus.BAD_REQUEST),

    ARTICLE_NOT_EXISTED("0003", "article not existed", HttpStatus.BAD_REQUEST);

    private String errorCode;

    private String message;

    private HttpStatus httpStatus;


    ArticleErrorCode(String errorCode, String message, HttpStatus httpStatus) {
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



 