package es.caib.seycon;


public class InvalidPasswordException extends es.caib.seycon.ng.exception.BadPasswordException {
    @Deprecated
    public InvalidPasswordException() {
        super();
    }

    @Deprecated
    public InvalidPasswordException(String msg) {
        super(msg);
    }

}
