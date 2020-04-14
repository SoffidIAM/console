package com.soffid.iam.web.main;

import org.jbpm.graph.exe.Execution;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Window;

import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.ui.Executions;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.Menu3;


public class MainWindow extends Window {
	@Override
	public void setPage (Page page) {
	}
	
	public void menu (Event evt) throws Exception {
		Menu3 menu3 = (Menu3) getFellow("menu3");
		menu3.open();
	}
	
	public void help (Event event) {
		org.zkoss.zk.ui.Executions.getCurrent().sendRedirect("https://confluence.soffid.com", "_blank");
	}

	@Override
	public void setParent(Component parent) {
		super.setParent(parent);
		SoffidPrincipal p = Security.getSoffidPrincipal();
		String usuari = p.getName();
		String nom = p.getFullName();
		getPage().getNamespace().setVariable("usuari", usuari, true);
		getPage().getNamespace().setVariable("nom", nom, true);
	}
}
