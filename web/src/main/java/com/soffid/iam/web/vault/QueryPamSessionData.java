package com.soffid.iam.web.vault;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Label;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.PamSession;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.Frame;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SoffidStackTrace;

public class QueryPamSessionData extends Label implements AfterCompose {
	Log log = LogFactory.getLog(getClass());
	private PamSession session;

	@Override
	public void afterCompose() {
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String sessionId = (String) req.getParameter("sessionId");
		String jumpServer = (String) req.getParameter("jumpServerGroup");
		
		try {
			session = EJBLocator.getPamSessionService().findSession(jumpServer, sessionId);
			User user = null;
			if (session != null && session.getUser() != null)
				user = EJBLocator.getUserService().findUserByUserName(session.getUser());
			getNamespace().setVariable("user", user == null ? new User(): user, true);
			getNamespace().setVariable("pamSession", session, true);
			String desktopId = getDesktop().getId();
			getDesktop().getSession().setAttribute("pam-session-"+desktopId, session);
		} catch (Exception e) {
			String error = SoffidStackTrace.generateShortDescription (e);
			getNamespace().setVariable("error", error, true);
			log.warn("Error retrieving session information", e);
		}
	}

}
