package es.caib.seycon.ng.exception;

public class TooManySessionsException extends LogonDeniedException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TooManySessionsException() {
    }

    public TooManySessionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManySessionsException(String message) {
        super(message);
    }

    public TooManySessionsException(Throwable cause) {
        super(cause);
    }

}
