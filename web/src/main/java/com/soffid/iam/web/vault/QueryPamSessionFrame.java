package com.soffid.iam.web.vault;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Html;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.PamSession;
import com.soffid.iam.web.component.Frame;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DateFormats;

public class QueryPamSessionFrame extends Frame implements AfterCompose {


	@Override
	public void afterCompose() {
		super.afterCompose();
		
		PamSession session = (PamSession) getNamespace().getVariable("pamSession", true);
		if (session != null)
		{
			Div d = (Div) getFellow("videos");
			
			Div div = new Div();
			Label l2 = new Label("To view keystrokes, enable the subtitles display in the video player");
			l2.setStyle("display: block; color: #c0c0c0; margin-top: 10px; margin-bottom: 10px;");
			div.appendChild(l2);
			d.appendChild(div);

			List<Long> chapters = session.getChapters();
			Collections.sort(chapters);
			for (Long chapter: chapters)
			{
				Date start = new Date ( chapter.longValue() * 1000L );
				String startText =  DateFormats.getDateTimeFormat().format(start);
				String msg = String.format("%s", startText);

				String ctx = getDesktop().getExecution().getContextPath();
				div = new Div();
				div.setStyle("display: inline-block; margin: 10px;");
				Label l1 = new Label(msg);
				l1.setStyle("display: block; margin-top: 15px;");
				div.appendChild(l1);
				Html html = new Html();
				html.setContent("<video width=\"320\" height=\"240\" controls>\n" + 
						"  <source src=\""+ctx+"/pam/Video/" + getDesktop().getId()+"/"+chapter+"\" type=\"video/ogg\">\n" + 
						"  <track src=\""+ctx+"/pam/Subtitles/" + getDesktop().getId()+"/"+chapter+"\" kind='subtitles' label='Keystrokes' >\n" + 
						"Your browser does not support the video tag.\n" + 
						"</video>");
				div.appendChild(html);
				d.appendChild(div);
			}
		}
	}

}
