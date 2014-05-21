package org.jbpm.identity.assignment;

import org.jbpm.JbpmException;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;
import org.jbpm.taskmgmt.exe.SwimlaneInstance;

public class ExpressionAssignmentHandler implements AssignmentHandler {
	public String expression;
	
	public void assign(Assignable assignable, ExecutionContext executionContext)
			throws Exception {
		String term = expression;
		String result = null;
	    if (term.equalsIgnoreCase("previous")) { //$NON-NLS-1$
	    	result = executionContext.getJbpmContext().getActorId();
	    	assignable.setActorId(result);
	    } else if ( (term.startsWith("swimlane(")) //$NON-NLS-1$
	         && (term.endsWith(")")) ) { //$NON-NLS-1$
	      String swimlaneName = term.substring(9,term.length()-1).trim();
	      SwimlaneInstance swimlaneInstance = executionContext
	              .getTaskMgmtInstance()
	              .getSwimlaneInstance(swimlaneName);
	        if (swimlaneInstance==null) {
	        	throw new JbpmException(String.format(Messages.getString("ExpressionAssignmentHandler.NoSwimlaneInstance"), swimlaneName)); //$NON-NLS-1$
	        }
	        assignable.setActorId(swimlaneInstance.getActorId());
	    } else if ( (term.startsWith("variable(")) //$NON-NLS-1$
	                && (term.endsWith(")")) ) { //$NON-NLS-1$
	      String variableName = term.substring(9,term.length()-1).trim();
	      String actor = (String) executionContext.getVariable(variableName);
	      assignable.setActorId(actor);
	    } else if ( (term.startsWith("user(")) //$NON-NLS-1$
	                && (term.endsWith(")")) ) { //$NON-NLS-1$
	      String userName = term.substring(5,term.length()-1).trim();
	      assignable.setActorId(userName);
	    } else if ( (term.startsWith("group(")) //$NON-NLS-1$
	                && (term.endsWith(")")) ) { //$NON-NLS-1$
	      String groupName = term.substring(6,term.length()-1).trim();
	      assignable.setActorId(groupName);
	    } else {
	      throw new JbpmException(String.format(Messages.getString("ExpressionAssignmentHandler.NoInterpretExpression"), expression)); //$NON-NLS-1$
	    }
	}

}
