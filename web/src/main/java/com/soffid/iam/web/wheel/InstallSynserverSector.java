package com.soffid.iam.web.wheel;

import java.util.Collection;
import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Server;

import es.caib.zkib.zkiblaf.Missatgebox;

public class InstallSynserverSector extends Sector {

	public InstallSynserverSector(String tag) {
		super(tag);
	}

	@Override
	public boolean isDone() {
		try {
			Collection<Server> list = EJBLocator.getDispatcherService().findAllServers();
			return list.size() > 0;
		}
		catch (Exception e) {
			return true;
		}
	}

}
