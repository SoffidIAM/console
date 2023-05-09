package com.soffid.iam.web.main;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jbpm.graph.exe.Execution;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Window;

import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.ui.Executions;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.Frame;
import com.soffid.iam.web.component.Menu3;

import es.caib.bpm.filters.WorkflowInterceptor;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Frameable;
import es.caib.zkib.zkiblaf.Missatgebox;


public class MainWindow extends Window {
	@Override
	public void setPage (Page page) {
	}
	
	public void menu (Event evt) throws Exception {
		Menu3 menu3 = (Menu3) getFellow("menu3");
		menu3.open();
	}
	
	public void help (Event event) {
		Frameable frameInfo = (Frameable) Application.getActiveFrame();
		String url = "https://bookstack.soffid.com";
		if (frameInfo != null && frameInfo instanceof Frame) {
			Frame frame = (Frame) frameInfo;
			if (frame.getHelp() != null && !frame.getHelp().isEmpty() )
				url = frame.getHelp();
			
		}
		org.zkoss.zk.ui.Executions.getCurrent().sendRedirect(url, "_blank");
	}

	@Override
	public void setParent(Component parent) {
		super.setParent(parent);
		SoffidPrincipal p = Security.getSoffidPrincipal();
		String usuari = p.getName();
		String nom = p.getFullName();
		if (usuari.startsWith("master\\"))
			usuari = usuari.substring(7);
		getPage().getNamespace().setVariable("usuari", usuari, true);
		getPage().getNamespace().setVariable("nom", nom, true);
	}
	
	public void profile(Event event) {
		es.caib.zkib.zkiblaf.Application.call("self/profile.zul");
	}

	public void logout(Event event) {
		Missatgebox.confirmaOK_CANCEL(
				Labels.getLabel("zkiblaf.tancarSessioConfirm"), //$NON-NLS-1$
				Labels.getLabel("zkiblaf.tancarSessioTitle"), //$NON-NLS-1$
				new EventListener() {

					public void onEvent(Event event) throws Exception {
						if ("onOK".equals(event.getName())) { //$NON-NLS-1$
							HttpServletRequest req = (HttpServletRequest) getDesktop().getExecution().getNativeRequest();
							HttpSession session = req.getSession();
							Object nestedPrincipal = session.getAttribute(WorkflowInterceptor.SOFFID_NESTED_PRINCIPAL);
							if (nestedPrincipal != null )
							{
								session.removeAttribute(WorkflowInterceptor.SOFFID_NESTED_PRINCIPAL);
								session.removeAttribute(WorkflowInterceptor.SOFFID_NESTED_TENANT);
								session.removeAttribute(WorkflowInterceptor.SOFFID_NESTED_PERMISSIONS);
								getDesktop().getExecution().sendRedirect("/index.zul");
							}
							else
							{
								getDesktop().getExecution().sendRedirect("/anonymous/logout.zul");
								session.invalidate();
							}
						}
					}
					
				});						

	}
	
	public void goHome(Event ev) {
		SoffidPrincipal p = Security.getSoffidPrincipal();
		if (Arrays.binarySearch(p.getSoffidRoles(),"SOFFID_ADMIN") >= 0) {
			Application.jumpTo("/config/wheel.zul");
		} else {
			Application.jumpTo("/main/menu.zul");
		}
	}
}
