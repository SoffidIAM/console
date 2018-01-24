package es.caib.bpm.ui.main;

import java.io.Serializable;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.api.CustomObjectType;

import es.caib.zkib.zkiblaf.Application;

public class CustomObjectListener implements EventListener, Serializable {
	private static final long serialVersionUID = 1L;
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
