package com.soffid.iam.web.application;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;

import es.caib.zkib.component.DataTable;


public class ApplicationUserHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	public ApplicationUserHandler() throws NamingException, CreateException {
	}
	
	public void afterCompose() {
	}
	
	
	public void downloadCsv(Event event) {
		getListbox().download();
	}

	private DataTable getListbox() {
		return (DataTable) getFellow("listbox");
	}

	
	public String getListboxPath() {
		return listboxPath;
	}

	
	public void setListboxPath(String listboxPath) {
		this.listboxPath = listboxPath;
	}


}