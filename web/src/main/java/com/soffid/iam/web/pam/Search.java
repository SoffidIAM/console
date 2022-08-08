package com.soffid.iam.web.pam;


import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.TimeZones;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.A;
import org.zkoss.zhtml.Text;
import org.zkoss.zhtml.impl.AbstractTag;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.PamSession;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Databox;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class Search extends FrameHandler 
{
	public Search() throws InternalErrorException {
		super();
	}

	private List<PamSession> sessions;

	public void afterCompose ()
	{
		super.afterCompose();
	}
	
	public void buscar() throws RemoteException, InterruptedException,
		CreateException, NamingException, WrongValueException, BPMException,
		InternalErrorException
	{
		String jsg = (String) ((Databox)getFellow("jumpServerGroup")).getValue();
		String url = (String) ((Databox)getFellow("url")).getValue();
		String text = (String) ((Databox)getFellow("text")).getValue();
		String user = (String) ((Databox)getFellow("user")).getValue();
		Date sd = (Date) ((Databox)getFellow("startDate")).getValue();
		Date sd2 = (Date) ((Databox)getFellow("startDate2")).getValue();

		
		sessions = EJBLocator.getPamSessionService().search(jsg, url, text, user, sd, sd2);
			
		if(sessions.size() == 0)
		{
			Missatgebox.avis(Labels.getLabel("observacion.resultadoVacio")); //$NON-NLS-1$
		}
			
		//Limpiamos el resultado y la imagen
		DataTable resultadoBusqueda = (DataTable)this.getFellow("listbox"); //$NON-NLS-1$
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		DateFormat df = DateFormats.getDateTimeFormat();
		for (PamSession session: sessions)
		{
			JSONObject o = new JSONObject( session );
			if (session.getServerStart() != null) {
				o.put("serverStart", session.getServerStart().getTime());
				o.put("serverStart_datetime", df.format(session.getServerStart()));
			} else {
				o.put("serverStart_datetime", "");				
			}
			if (session.getServerEnd() != null) {
				o.put("serverEnd", session.getServerEnd().getTime());
				o.put("serverEnd_datatime", df.format(session.getServerEnd()));
			} else {
				o.put("serverEnd_datetime", "");				
			}
			if (sb.length() > 1) sb.append(",");
			sb.append(o.toString());
		} 
		sb.append("]");
		resultadoBusqueda.setData(sb.toString());
	}
	
	@Override
	public void hideDetails() throws CommitException {
		super.hideDetails();
	}
	

	public void openSession(Event event) {
		showDetails();
		DataTable lb = (DataTable) getListbox();
		int position = lb.getSelectedIndex();
		if (position >= 0 && position < sessions.size()) {
			PamSession session = sessions.get(position);
			if (session != null)
			{
				String desktopId = getDesktop().getId();
				getDesktop().getSession().setAttribute("pam-session-"+desktopId, session);

				setDatabox("pamSession.jumpServerGroup", session.getJumpServerGroup());
				setDatabox("pamSession.path", session.getPath());
				setDatabox("pamSession.user", session.getUser());
				setDatabox("pamSession.serverStart", session.getServerStart());
				setDatabox("pamSession.serverEnd", session.getServerEnd());
				setDatabox("pamSession.serverUrl", session.getServerUrl());
				setDatabox("pamSession.accountName", session.getAccountName());
	
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
				response(null, new AuScript(this, "initVideos("+videos.toString()+","+captions.toString()+","+chapterStart.toString()+"," +session.getServerStart().getTime()+","+session.getServerEnd().getTime()+")"));
				
				Div bookmarks = (Div) getFellow("bookmarks");
				while (bookmarks.getChildren().size()>1) bookmarks.getLastChild().detach();
				final SimpleDateFormat timeFormat = new SimpleDateFormat(DateFormats.getTimeFormatString());
				timeFormat.setTimeZone(TimeZones.getCurrent());
				for ( Long bookmark: session.getBookmarks()) {
					Date d = new Date(bookmark.longValue()*1000L);
					String s = timeFormat.format(d);
					AbstractTag l = new A();
					l.appendChild(new Text(s));
					l.setDynamicProperty("href", "#");
					l.setDynamicProperty("onclick", "doJumpVideoTo("+bookmark+"000, true);");
					l.setSclass("bookmark");
					bookmarks.appendChild(l);
				}
			}
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


	private void setDatabox(final String compId, Object value) {
		((Databox) getFellow(compId)).setValue(value);
	}

	@Override
	public void onPageAttached(Page newpage, Page oldpage) {
		super.onPageAttached(newpage, oldpage);
		getNamespace().setVariable("timeFormat", es.caib.zkib.component.DateFormats.getTimeFormatString(), true);
	}
}
