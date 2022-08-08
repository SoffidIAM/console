package com.soffid.iam.web.vault;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.zkoss.text.DateFormats;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
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
			
			if (session != null)
			{
				List<Long> chapters = session.getChapters();
				chapters = removeDummyChapters(chapters);
				Collections.sort(chapters);
				JSONArray captions = new JSONArray();
				JSONArray videos = new JSONArray();
				JSONArray chapterStart = new JSONArray();
				Long previous = null;
				for (Long chapter: chapters)
				{
					if (previous == null || !chapter.equals(previous)) {
						Date start = new Date ( chapter.longValue() * 1000L );
	
						String ctx = getDesktop().getExecution().getContextPath();
						String caption = ctx+"/pam/Subtitles/" + getDesktop().getId()+"/"+chapter;
						String video = ctx+"/pam/Video/" + getDesktop().getId()+"/"+chapter;
						if (captions.length() == 0) {
							getNamespace().setVariable("firstVideo", video, true);
							getNamespace().setVariable("firstCaption", caption, true);
						}
						captions.put(caption);
						videos.put(video);
						chapterStart.put(start.getTime());
						previous = chapter;
					}
				}
				getNamespace().setVariable("timeFormat", es.caib.zkib.component.DateFormats.getTimeFormatString(), true);
				getNamespace().setVariable("videos", videos.toString(), true);
				getNamespace().setVariable("captions", captions.toString(), true);
				getNamespace().setVariable("chapterStart", chapterStart.toString(), true);
				getNamespace().setVariable("videoStart", session.getServerStart().getTime(), true);
				getNamespace().setVariable("videoEnd", session.getServerEnd().getTime(), true);
			}

		} catch (Exception e) {
			String error = SoffidStackTrace.generateShortDescription (e);
			getNamespace().setVariable("error", error, true);
			log.warn("Error retrieving session information", e);
		}
	}

	private List<Long> removeDummyChapters(List<Long> chapters) {
		if (chapters.isEmpty())
			return chapters;
		
		List<Long> l = new LinkedList<>();
		Iterator<Long> it = chapters.iterator();
		Long previous = it.next();
		while (it.hasNext()) {
			Long current = it.next();
			if (current.longValue() - previous.longValue() > 500) // Minimum half a second
				l.add(previous);
			previous = current;
		}
		l.add(previous);
		return l ;
	}

}
