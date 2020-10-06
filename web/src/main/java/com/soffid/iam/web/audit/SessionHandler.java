package com.soffid.iam.web.audit;

import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.exception.InternalErrorException;


public class SessionHandler extends FrameHandler {

	public SessionHandler() throws InternalErrorException {
		super();
	}

	
	public void afterCompose() {
		super.afterCompose();
		javax.servlet.http.HttpServletRequest req = (javax.servlet.http.HttpServletRequest) org.zkoss.zk.ui.Executions.getCurrent().getNativeRequest();
		String filter = req.getParameter("filter");
		if (filter == null) {
			SearchBox searchBox = (SearchBox) getFellow("searchBox");
			java.util.Calendar c = java.util.Calendar.getInstance();
			c.add(java.util.Calendar.DAY_OF_MONTH, -7);
			searchBox.addAttribute("calendar").setDateSearchInterval(c.getTime(), null);
		}
	}
}
