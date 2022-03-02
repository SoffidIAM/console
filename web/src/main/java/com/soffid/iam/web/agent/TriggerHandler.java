package com.soffid.iam.web.agent;

import java.io.IOException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;

import com.soffid.iam.web.popup.Editor;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.XPathUtils;

public class TriggerHandler extends Tabpanel {
	public void addTrigger(Event event) throws Exception {
		es.caib.zkib.binder.BindContext bindCtx = XPathUtils.getComponentContext(event.getTarget());							                                   
		XPathUtils.createPath(bindCtx.getDataSource(), bindCtx.getXPath());
	}
	
	public void removeTrigger(Event event) {
		es.caib.zkib.binder.BindContext bindCtx = XPathUtils.getComponentContext (event.getTarget());
		XPathUtils.removePath (bindCtx.getDataSource(), bindCtx.getXPath());
	}
	
	public void focusTextarea(Event event) {
		Textbox self = (Textbox) event.getTarget();
		String v = self.getValue();
		if (v.length() > 50 || v.indexOf ('\n') >= 0)
		{
			self.setMultiline(true);
			self.setHeight("5em");
			self.setStyle("resize: vertical");
		}
	}
	
	
	public void editTrigger (final Event event) throws ComponentNotFoundException, InternalErrorException, NamingException, CreateException, IOException {
		Editor.edit((Textbox) event.getTarget().getPreviousSibling(),
					    new com.soffid.iam.web.agent.ScriptEnviroment().getLoadTriggerVars(event.getTarget()));
	}
	

}
