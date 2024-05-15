package study.board.exception;

public class DuplicateBoardNameException extends RuntimeException{
    public DuplicateBoardNameException() {
        super();
    }

    public DuplicateBoardNameException(String message) {
        super(message);
    }

    public DuplicateBoardNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateBoardNameException(Throwable cause) {
        super(cause);
    }

    protected DuplicateBoardNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
