package com.soffid.iam.web.discovery;

import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.xml.handler.PersistenceHandler;

public class DummyPersistenceHandler implements PersistenceHandler {

	@Override
	public boolean isSuitable(DataContext node) {
		return true;
	}

	@Override
	public void doInsert(DataContext node) throws Exception {
		
	}

	@Override
	public void doDelete(DataContext node) throws Exception {
	}

	@Override
	public void doUpdate(DataContext node) throws Exception {
	}

}
