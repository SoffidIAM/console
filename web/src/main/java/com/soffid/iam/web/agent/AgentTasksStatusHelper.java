package com.soffid.iam.web.agent;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.System;
import com.soffid.iam.service.SystemScheduledTasks;

import es.caib.seycon.ng.exception.InternalErrorException;

public class AgentTasksStatusHelper {
	private ScheduledTask importTask;
	private ScheduledTask impactTask;
	private ScheduledTask reconcileTask;

	public static AgentTasksStatusHelper getStatus(String agent) throws NamingException, CreateException {
		AgentTasksStatusHelper r = new AgentTasksStatusHelper();
		System system;
		try {
			system = EJBLocator.getDispatcherService().findDispatcherByName(agent);
			for (ScheduledTask task: EJBLocator.getScheduledTaskService().listTasks())
			{
				if ( system.getId().toString().equals(task.getParams()))
				{
					if (task.getHandlerName().equals(SystemScheduledTasks.AUTHORITATIVE_DATA_IMPORT))
						r.importTask = task;
					if (task.getHandlerName().equals(SystemScheduledTasks.RECONCILE_DISPATCHER))
						r.reconcileTask = task;
					if (task.getHandlerName().equals(SystemScheduledTasks.DISPATCHER_IMPACT))
						r.impactTask = task;
				}
				
			}
		} catch (InternalErrorException e) {
		}
		return r;
	}

	
	public ScheduledTask getImportTask() {
		return importTask;
	}

	
	public void setImportTask(ScheduledTask importTask) {
		this.importTask = importTask;
	}

	
	public ScheduledTask getImpactTask() {
		return impactTask;
	}

	
	public void setImpactTask(ScheduledTask impactTask) {
		this.impactTask = impactTask;
	}

	
	public ScheduledTask getReconcileTask() {
		return reconcileTask;
	}

	
	public void setReconcileTask(ScheduledTask reconcileTask) {
		this.reconcileTask = reconcileTask;
	}
}
