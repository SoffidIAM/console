package es.caib.seycon.ng.exception;

public class VerifySignatureException extends Exception{
	/**
	   * Creates new <code>VerifySignatureException</code>.
	   */
	  public VerifySignatureException() {
		  super(Messages.getString("VerifySignatureException.0")); //$NON-NLS-1$
	  }
}
