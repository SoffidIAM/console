package es.caib.bpm.ui.main;

import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Treeitem;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.BPMApplication;
import es.caib.bpm.vo.ProcessDefinition;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskInstance;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Missatgebox;

public class CreateProcessListener implements EventListener {
	private ProcessDefinition def;
	
	

	public CreateProcessListener(ProcessDefinition def) {
		super();
		this.def = def;
	}

	public void onEvent(Event event) throws Exception {
		BpmEngine engine = BPMApplication.getEngine();
		ProcessInstance instance= engine.newProcess(def);
		
        List tasks = engine.getPendingTasks(instance);

		Treeitem item = (Treeitem) event.getTarget().getFellow("availableprocesses"); //$NON-NLS-1$
		//item.setOpen(false);
		
		event.getTarget().getFellow("menu").setVisible(false); //$NON-NLS-1$
        
        if (tasks != null && tasks.size() > 0) {
            TaskInstance task = (TaskInstance) tasks.get(0);
            task = engine.startTask(task);
            Application.call(BPMApplication.getTaskURL(task));
        } else {
        	Missatgebox.info(Labels.getLabel("nuevoProceso.msgCreacionProceso") + " " + instance.getId()); //$NON-NLS-1$ //$NON-NLS-2$
        }
	}

}
