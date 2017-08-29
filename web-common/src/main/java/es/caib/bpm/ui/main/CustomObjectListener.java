package es.caib.bpm.ui.main;

import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Treeitem;

import com.soffid.iam.api.CustomObjectType;

import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.BPMApplication;
import es.caib.bpm.vo.ProcessDefinition;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskInstance;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Missatgebox;

public class CustomObjectListener implements EventListener {
	private CustomObjectType type;
	
	

	public CustomObjectListener(CustomObjectType type) {
		super();
		this.type = type;
	}

	public void onEvent(Event event) throws Exception {
		event.getTarget().getFellow("menu").setVisible(false); //$NON-NLS-1$
        
        Application.call("");
	}

}
