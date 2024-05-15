package study.board.exception;

public class NoBoardException extends RuntimeException{
    public NoBoardException() {
        super();
    }

    public NoBoardException(String message) {
        super(message);
    }

    public NoBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoBoardException(Throwable cause) {
        super(cause);
    }

    protected NoBoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
