package com.soffid.iam.bpm.service.impl;

import org.hibernate.Session;
import org.jbpm.persistence.db.DbPersistenceServiceFactory;

import com.soffid.iam.spring.CustomSession;
import com.soffid.iam.spring.JbpmSession;

public class DbPersistenceService extends
		org.jbpm.persistence.db.DbPersistenceService {
	public DbPersistenceService(
			DbPersistenceServiceFactory persistenceServiceFactory) {
		super(persistenceServiceFactory);
	}

	@Override
	public org.jbpm.db.GraphSession getGraphSession() {
		if (graphSession == null) {
			Session session = getSession();
			if (session != null) {
				graphSession = new GraphSession(session);
			}
		}
		return graphSession;
	}

	public org.jbpm.db.TaskMgmtSession getTaskMgmtSession() {
		if (taskMgmtSession == null) {
			Session session = getSession();
			if (session != null) {
				taskMgmtSession = new TaskMgmtSession(session);
			}
		}
		return taskMgmtSession;
	}

	@Override
	public Session getSession() {
		return new JbpmSession( super.getSession() );
	}

}
