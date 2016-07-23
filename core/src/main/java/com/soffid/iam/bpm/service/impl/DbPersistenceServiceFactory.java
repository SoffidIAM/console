package com.soffid.iam.bpm.service.impl;

import org.jbpm.svc.Service;

public class DbPersistenceServiceFactory extends org.jbpm.persistence.db.DbPersistenceServiceFactory {

	@Override
	public Service openService() {
	    return new DbPersistenceService(this);
	}

}
