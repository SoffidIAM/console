package com.soffid.iam.bpm.task;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.TaskInstanceFactory;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class BPMTaskInstanceFactory implements TaskInstanceFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaskInstance createTaskInstance(ExecutionContext executionContext) {
		return new BPMTaskInstance();
	}

}
