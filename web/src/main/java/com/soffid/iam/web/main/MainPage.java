package com.soffid.iam.web.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.ext.AfterCompose;

import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.utils.Security;

import es.caib.bpm.filters.WorkflowInterceptor;
import es.caib.zkib.component.Div;
import es.caib.zkib.zkiblaf.Missatgebox;


public class MainPage extends Div implements AfterCompose {
	boolean hideApp = false;
	@Override
	public void setPage (Page page) {
		super.setPage(page);
		SoffidPrincipal p = Security.getSoffidPrincipal();
		if (p != null && p.getUserName() == null &&
				!p.getName().endsWith("\\anonymous")) {
			page.setVariable("initialPage", "");
			getDesktop().getSession().setAttribute("paginaActual", "");
			Missatgebox.avis("Service accounts are not authorized to use Soffid console",
					(event) -> {
						HttpServletRequest req = (HttpServletRequest) getDesktop().getExecution().getNativeRequest();
						HttpSession session = req.getSession();
						getDesktop().getExecution().sendRedirect("/anonymous/logout.zul");
						session.invalidate();
					});
			hideApp =true;
		} else {
			String initialPage = "main/menu.zul";
			Execution exec = Executions.getCurrent();
			HttpServletRequest req = (HttpServletRequest) exec.getNativeRequest();
			String target = (String) req.getAttribute("$soffid$target");
	//		if (target != null) initialPage = target;
			page.setVariable("initialPage", initialPage);
			getDesktop().getSession().setAttribute("paginaActual", target);
		}
	}

	@Override
	public void afterCompose() {
		if ("true".equals(System.getProperty("soffid.fail-safe")))  {
			getFellow("app").getFellow("fail-safe-mode").setVisible(true);
		}
		if (hideApp)
			getFellow("app").detach();
	}

	public void onTimer () {
		
	}
}
