package es.caib.bpm.handler;

import com.soffid.iam.web.bpm.BPMApplication;

import com.soffid.iam.bpm.service.ejb.BpmEngine;
import com.soffid.iam.bpm.api.TaskInstance;
import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.xml.handler.PersistenceHandler;

public class TaskHandler implements PersistenceHandler {

	public void doDelete(DataContext node) throws Exception {
		throw new Exception (Messages.getString("TaskHandler.DeleteNotAllowed")); //$NON-NLS-1$
	}

	public void doInsert(DataContext node) throws Exception {
		throw new Exception (Messages.getString("TaskHandler.InsertNotAllowed")); //$NON-NLS-1$
	}

	public void doUpdate(DataContext node) throws Exception {
		BpmEngine engine = BPMApplication.getEngine();
		TaskInstance ti = (TaskInstance) node.getData();
		engine.update(ti);
	}

	public boolean isSuitable(DataContext node) {
		return true;
	}

}
