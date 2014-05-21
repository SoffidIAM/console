package es.caib.bpm.toolkit.exception;

public abstract class WorkflowException extends Exception {

	public WorkflowException() {
		super();
	}

	public WorkflowException(String message, Throwable tw) {
		super(message, tw);
	}

	public WorkflowException(String message) {
		super(message);
	}

	public WorkflowException(Throwable tw) {
		super(tw);
	}
}
