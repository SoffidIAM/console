package es.caib.bpm.ui.inbox;

import java.util.List;

import com.soffid.iam.web.inbox.InboxHandler;

import es.caib.bpm.exception.BPMException;
import com.soffid.iam.bpm.service.ejb.BpmEngine;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Application;

public class RunningTasksHandler extends InboxHandler {
	public RunningTasksHandler() throws InternalErrorException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List getTasks(BpmEngine engine) throws BPMException, InternalErrorException {
		List tareas;
		tareas = engine.findGroupTasks();
		return tareas;
	}

}
