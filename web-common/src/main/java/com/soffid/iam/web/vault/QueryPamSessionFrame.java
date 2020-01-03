package com.soffid.iam.web.vault;

import java.text.SimpleDateFormat;
import java.util.Date;

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

public class QueryPamSessionFrame extends Frame implements AfterCompose {


	@Override
	public void afterCompose() {
		super.afterCompose();
		
		PamSession session = (PamSession) getNamespace().getVariable("pamSession", true);
		if (session != null)
		{
			Grid g = (Grid) getFellow("grid");
			for (Long chapter: session.getChapters())
			{
				Row row = new Row();
				g.getRows().appendChild(row);
				Date start = new Date ( chapter.longValue() * 1000L );
				String startText = new SimpleDateFormat(Labels.getLabel("auditoria.dateFormat")).format(start);
				String msg = String.format("Recording at %s", startText);
				Div div = new Div();
				Label l2 = new Label("To view keystrokes, enable the subtitles display in the video player");
				l2.setStyle("display: block; color: #c0c0c0");
				div.appendChild(l2);
				row.appendChild(div);
				
				div = new Div();
				Html html = new Html();
				html.setContent("<video width=\"320\" height=\"240\" controls>\n" + 
						"  <source src=\"/pam/Video/" + getDesktop().getId()+"/"+chapter+"\" type=\"video/ogg\">\n" + 
						"  <track src=\"/pam/Subtitles/" + getDesktop().getId()+"/"+chapter+"\" kind='subtitles' label='Keystrokes' >\n" + 
						"Your browser does not support the video tag.\n" + 
						"</video>");
				div.appendChild(html);
				Label l1 = new Label(msg);
				l1.setStyle("display: block");
				div.appendChild(l1);
				row.appendChild(div);
			}
		}
	}

}
