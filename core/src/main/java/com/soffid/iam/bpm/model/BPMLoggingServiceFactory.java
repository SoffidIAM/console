package com.soffid.iam.bpm.model;

import org.jbpm.logging.db.DbLoggingServiceFactory;
import org.jbpm.svc.Service;

public class BPMLoggingServiceFactory extends DbLoggingServiceFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void close() {
		super.close();
	}

	public Service openService() {
		return new BPMLoggingService();
	}

	public BPMLoggingServiceFactory() {
	}

}
