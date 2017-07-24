package es.caib.bpm.handler;

import java.util.Collection;

import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.BPMApplication;
import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.xml.handler.FinderHandler;

public class InboxFinder implements FinderHandler {

	public Collection find(DataContext ctx) throws Exception {
		BpmEngine e = BPMApplication.getEngine();
		return e.findMyTasks();
	}

	public boolean isSuitable(DataContext node) {
		return true;
	}

}
