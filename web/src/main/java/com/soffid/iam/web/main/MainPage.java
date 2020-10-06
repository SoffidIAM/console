package com.soffid.iam.web.main;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.ext.AfterCompose;

import es.caib.zkib.component.Div;


public class MainPage extends Div implements AfterCompose {
	@Override
	public void setPage (Page page) {
		super.setPage(page);
		String initialPage = "main/menu.zul";
		Execution exec = Executions.getCurrent();
		HttpServletRequest req = (HttpServletRequest) exec.getNativeRequest();
		String target = (String) req.getAttribute("$soffid$target");
//		if (target != null) initialPage = target;
		page.setVariable("initialPage", initialPage);
		getDesktop().getSession().setAttribute("paginaActual", target);
	}

	@Override
	public void afterCompose() {
		if ("true".equals(System.getProperty("soffid.fail-safe")))  {
			getFellow("app").getFellow("fail-safe-mode").setVisible(true);
		}
		
	}

	public void onTimer () {
		
	}
}
