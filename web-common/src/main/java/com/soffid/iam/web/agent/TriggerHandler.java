package com.soffid.iam.web.agent;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.SoffidObjectType;
import com.soffid.iam.api.System;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.sync.engine.intf.GetObjectResults;
import com.soffid.iam.web.component.TreeCollapse;

import es.caib.seycon.ng.comu.AttributeDirection;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.DataGrid;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.zkiblaf.ImageClic;
import es.caib.zkib.zkiblaf.Missatgebox;

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
	
	
	public void editTrigger (final Event event) throws ComponentNotFoundException, InternalErrorException, NamingException, CreateException {
	    Events.sendEvent(new Event ("onEdit", 
	    		getDesktop().getPage("editor").getFellow("top"),
	    		new Object[] {
					    event.getTarget().getPreviousSibling(),
					    new com.soffid.iam.web.agent.ScriptEnviroment().getLoadTriggerVars(event.getTarget())
				}
	    ));
	}
	

}
