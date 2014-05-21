package es.caib.bpm.nas.exception;

/**
 * Representa una problema en la comunicacion con el NAS.
 * 
 */
public class NASException extends Exception 
{
	public NASException() 
	{
		super();
	}

	public NASException(String message) 
	{
		super(message);
	}

	public NASException(Throwable cause) 
	{
		super(cause);
	}

	public NASException(String message, Throwable cause) 
	{
		super(message, cause);
	}

}
