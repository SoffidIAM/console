package es.caib.bpm.exception;

/**
 * Representa una excepcion de la gestion de usuarios.
 */
public class UserManagementException extends Exception {

	public UserManagementException(int errorCode) {
		super();
		
		this.setErrorCode(errorCode);
	}

	public UserManagementException(String message, Throwable cause, int errorCode) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public UserManagementException(String message, int errorCode) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public UserManagementException(Throwable cause, int errorCode) {
		super(cause);
		this.setErrorCode(errorCode);
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	int errorCode= 0;
}
