package es.caib.bpm.exception;

public class InvalidParameterException extends BPMException 
{

	public InvalidParameterException(int errorCode) 
	{
		super(errorCode);
	}

	public InvalidParameterException(String message, int errorCode) {
		super(message, errorCode);
	}

	public InvalidParameterException(String message, Throwable cause, int errorCode) {
		super(message, cause, errorCode);
	}

	public InvalidParameterException(Throwable cause, int errorCode) {
		super(cause, errorCode);
	}
}
