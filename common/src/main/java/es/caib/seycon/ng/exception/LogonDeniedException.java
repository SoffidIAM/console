package es.caib.seycon.ng.exception;

public class LogonDeniedException extends Exception {

    public LogonDeniedException() {
        super();
    }

    public LogonDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogonDeniedException(String message) {
        super(message);
    }

    public LogonDeniedException(Throwable cause) {
        super(cause);
    }

}
