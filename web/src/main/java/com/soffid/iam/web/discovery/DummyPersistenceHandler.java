package com.soffid.iam.web.discovery;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.NetworkAuthorization;

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
		EJBLocator.getNetworkService().update((Network) node.getData());
	}

}
