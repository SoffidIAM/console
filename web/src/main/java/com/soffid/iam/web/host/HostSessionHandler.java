package com.soffid.iam.web.host;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Div;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.XPathUtils;


public class HostSessionHandler extends Div  {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	public HostSessionHandler() throws NamingException, CreateException {
	}
	
	public DataTable getListbox() {
		return (DataTable) getFellow("listbox");
	}

	
	public String getListboxPath() {
		return listboxPath;
	}

	
	public void setListboxPath(String listboxPath) {
		this.listboxPath = listboxPath;
	}
	
	public void downloadCsv(Event event) {
		Component lb = getListbox();
		if (lb instanceof DataTable)
			((DataTable) lb).download();
	}

	public void refresh(Event event) throws Exception {
		DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue(Path.getComponent(getListboxPath()), "/session");
		coll.refresh();
	}
}
