package es.caib.bpm.exception;

/**
 * Representa una excepcion del motor de workflow.
 * 
 */
public class BPMException extends Exception {

	public BPMException(int errorCode) {
		super();
		
		this.setErrorCode(errorCode);
	}

	public BPMException(String message, Throwable cause, int errorCode) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public BPMException(String message, int errorCode) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public BPMException(Throwable cause, int errorCode) {
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
