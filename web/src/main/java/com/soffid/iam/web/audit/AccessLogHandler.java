package com.soffid.iam.web.audit;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.XPathUtils;


public class AccessLogHandler extends FrameHandler {

	public AccessLogHandler() throws InternalErrorException {
		super();
	}

	
	public void afterCompose() {
		super.afterCompose();
		javax.servlet.http.HttpServletRequest req = (javax.servlet.http.HttpServletRequest) org.zkoss.zk.ui.Executions.getCurrent().getNativeRequest();
		String filter = req.getParameter("filter");
		if (filter == null) {
			SearchBox searchBox = (SearchBox) getFellow("searchBox");
			java.util.Calendar c = java.util.Calendar.getInstance();
			c.add(java.util.Calendar.DAY_OF_MONTH, -6);
			c.set(java.util.Calendar.HOUR, 0);
			c.set(java.util.Calendar.MINUTE, 0);
			c.set(java.util.Calendar.SECOND, 0);
			c.set(java.util.Calendar.MILLISECOND, 0);
			searchBox.addAttribute("startDate").setDateSearchInterval(c.getTime(), null);
		}
	}
	
	public void openSession(Event event) throws UnsupportedEncodingException {
		String sessionId = (String) XPathUtils.eval(getListbox(), "sessionId");
		String jumpServerGroup = (String) XPathUtils.eval(getListbox(), "jumpServerGroup");
		if (sessionId != null && jumpServerGroup != null) {
			Execution execution = Executions.getCurrent();
			execution.sendRedirect(
					"/vault/query_pam_session.zul?"+
					"sessionId="+(sessionId)+
					"&jumpServerGroup="+(jumpServerGroup),
					"_blank");
			
		}
	}
}
