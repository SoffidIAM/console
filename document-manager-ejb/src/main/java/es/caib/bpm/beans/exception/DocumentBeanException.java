package es.caib.bpm.beans.exception;

/**
 * 
 */
public class DocumentBeanException extends Exception 
{

	public DocumentBeanException(int errorCode) 
	{
		super();
		
		this.errorCode= errorCode;
	}

	public DocumentBeanException(String message, Throwable cause, int errorCode) 
	{
		super(message, cause);
		
		this.errorCode= errorCode;
	}

	public DocumentBeanException(String message, int errorCode) 
	{
		super(message);
		
		this.errorCode= errorCode;
	}

	public DocumentBeanException(Throwable cause, int errorCode) 
	{
		super(cause);
		
		this.errorCode= errorCode;
	}

	public int getErrorCode() 
	{
		return errorCode;
	}
	
	public int errorCode= 0;
}
