package com.soffid.iam.web.user;

import java.io.IOException;
import java.net.URLEncoder;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;

import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.popup.SelectColumnsHandler;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Application;


public class UserSessionHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;

	public UserSessionHandler() throws NamingException, CreateException {
	}
	
	public void afterCompose() {
		listboxPath = (String) Executions.getCurrent().getArg().get("listbox");
	}
	
	public void downloadCsv(Event event) {
		getListbox().download();
	}

	private DataTable getListbox() {
		return (DataTable) getFellow("listbox");
	}

	public void changeColumns(Event event) throws IOException {
		SelectColumnsHandler.startWizard((DynamicColumnsDatatable) getListbox());
	}
	
	public void refresh(Event event) throws Exception {
		try {
			DataNodeCollection coll = (DataNodeCollection) XPathUtils.eval( Path.getComponent(listboxPath), "/session");
			coll.refresh();
		} catch (Exception e) {
			// No user selected
		}
	}
}
