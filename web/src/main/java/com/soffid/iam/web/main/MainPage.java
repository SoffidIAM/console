package com.soffid.iam.web.main;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.ext.AfterCompose;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

import es.caib.bpm.filters.WorkflowInterceptor;
import es.caib.seycon.ng.exception.InternalErrorException;
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
			try {
				final MenuParser menuParser = new MenuParser();
				List<MenuOption> menu = menuParser.getMenus("console.yaml");
				MenuOption option = menuParser.findMenu(menu, "config/wheel.zul");
				if (option != null) {
					String showWheel = EJBLocator.getPreferencesService().findMyPreference("wheel-tips");
					if (!"false".equals(showWheel))
						initialPage = "config/wheel.zul";
				}
				
			} catch (Exception e1) {
			}
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
