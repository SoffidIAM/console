package com.soffid.iam.service.cron;

import java.io.PrintWriter;
import java.sql.SQLException;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.service.TaskHandler;

import es.caib.seycon.ng.exception.InternalErrorException;

public class NetworkIntelligenceVerifyDomainsTask implements TaskHandler {

	private ScheduledTask task;

	public void run(PrintWriter out) throws SQLException, InternalErrorException {
		ServiceLocator.instance().getNetworkIntelligenceService().verifyDomains(out);
	}

	public void setTask(ScheduledTask task) {
		this.task = task;
	}

	public ScheduledTask getTask() {
		return task;
	}
}
