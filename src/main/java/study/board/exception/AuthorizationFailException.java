package study.board.exception;

public class AuthorizationFailException extends RuntimeException{
    public AuthorizationFailException() {
        super();
    }

    public AuthorizationFailException(String message) {
        super(message);
    }

    public AuthorizationFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizationFailException(Throwable cause) {
        super(cause);
    }

    protected AuthorizationFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
