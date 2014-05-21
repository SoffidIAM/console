package es.caib.bpm.toolkit.exception;

/**
 * Excepcion especifica para indicar una condicion de error del sistema.
 * 
 */
public class SystemWorkflowException extends WorkflowException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemWorkflowException() {
		super();
	}

	public SystemWorkflowException(String message, Throwable tw) {
		super(message, tw);
	}

	public SystemWorkflowException(String message) {
		super(message);
	}

	public SystemWorkflowException(Throwable tw) {
		super(tw);
	}

}
