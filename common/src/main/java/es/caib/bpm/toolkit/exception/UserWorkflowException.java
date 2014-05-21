package es.caib.bpm.toolkit.exception;

/**
 * Excepcion especifica para indicar una condicion de error que debe ser mostrada
 * al usuario con un messagebox.
 */
public class UserWorkflowException extends WorkflowException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserWorkflowException() {
		super();
	}

	public UserWorkflowException(String message, Throwable tw) {
		super(message, tw);
	}

	public UserWorkflowException(String message) {
		super(message);
	}

	public UserWorkflowException(Throwable tw) {
		super(tw);
	}
}
