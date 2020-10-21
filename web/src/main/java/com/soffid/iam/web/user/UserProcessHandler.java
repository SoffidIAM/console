package com.soffid.iam.web.user;

import java.net.URLEncoder;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.zkiblaf.Application;


public class UserProcessHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;

	public UserProcessHandler() throws NamingException, CreateException {
	}
	
	public void afterCompose() {
		listboxPath = (String) Executions.getCurrent().getArg().get("listbox");
	}
	
	public void openProcess(Event e) {
		DataTable dt = (DataTable) getFellow("listbox");
		
		Long id = (Long) dt.getJXPathContext().getValue("id");
		
		Application.call("/wf/process.zul?id="+id);
	}
	
}
