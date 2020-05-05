package com.soffid.iam.bpm.service.impl;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.jbpm.context.log.VariableLog;
import org.jbpm.graph.log.ActionLog;
import org.jbpm.logging.db.DbLoggingService;
import org.jbpm.logging.log.ProcessLog;
import org.jbpm.taskmgmt.log.SwimlaneLog;

public class BPMLoggingService extends DbLoggingService {

	public void log(ProcessLog processLog) {
		if (processLog instanceof ActionLog)
		{
			// Ignore
			ActionLog l = (ActionLog) processLog;
			if (l.getException() != null && l.getException().length() > 2000)
			{
				l.setException(new ExceptionWrapper(l.getException()));
			}
			removeChildren(processLog);
			super.log(l);
		}
		else if ( processLog instanceof VariableLog) {
			// Ignore variable assignment logs
		}
		else if ( processLog instanceof SwimlaneLog) {
			// Ignore swimlane assignment logs
		}
		else {
			removeChildren(processLog);
			super.log(processLog);
		}
	}

	private void removeChildren(ProcessLog processLog) {
		List children = processLog.getChildren();
				
		if (children != null) {
			for(Iterator it = children.iterator(); it.hasNext();) {
				ProcessLog log = (ProcessLog) it.next();
				if (log instanceof VariableLog ||
						log instanceof SwimlaneLog ) {
					it.remove();
				} else {
					removeChildren(log);
				}
			}
		}
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