package es.caib.bpm.entity;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.jbpm.graph.log.ActionLog;
import org.jbpm.logging.db.DbLoggingService;
import org.jbpm.logging.log.ProcessLog;

public class BPMLoggingService extends DbLoggingService {

	public void log(ProcessLog processLog) {
		if (processLog instanceof ActionLog)
		{
			ActionLog l = (ActionLog) processLog;
			if (l.getException() != null && l.getException().length() > 2000)
			{
				l.setException(new ExceptionWrapper(l.getException()));
			}
			super.log(l);
		}
		else
			super.log(processLog);
	}

}


class ExceptionWrapper extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	
	public ExceptionWrapper (String message) {
		this.message = message;
	}

	public void printStackTrace(PrintStream stream) {
		stream.print (message.substring(0, 2000));
		stream.println (" ..."); //$NON-NLS-1$
	}

	public void printStackTrace(PrintWriter stream) {
		stream.print (message.substring(0, 2000));
		stream.println (" ..."); //$NON-NLS-1$
	}
	
	
}