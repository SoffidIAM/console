package es.caib.bpm.ui.inbox;

import java.util.List;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Application;

public class RunningTasksHandler extends InboxHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void onCreate () throws Exception
	{
		Application.setTitle(org.zkoss.util.resource.Labels.getLabel("inbox.lblFiltroTareas2")); //$NON-NLS-1$
		super.onCreate();
	}
	
	@Override
	public List getTasks(BpmEngine engine) throws BPMException, InternalErrorException {
		List tareas;
		tareas = engine.findGroupTasks();
		return tareas;
	}

}
