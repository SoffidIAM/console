package com.soffid.iam.web.audit;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;

import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.XPathUtils;


public class SessionHandler extends FrameHandler {

	public SessionHandler() throws InternalErrorException {
		super();
	}

	
	public void afterCompose() {
		super.afterCompose();
	}
	
	public void refresh() {
		getModel().refresh();
	}
	
	public void open(Event event) throws MalformedURLException, UnsupportedEncodingException {
		String monitor = (String) XPathUtils.eval(getListbox(), "monitorUrl");
		if (monitor != null) {
			URL u = new URL(monitor);
			StringBuffer sb = new StringBuffer();
			String targetUrl = u.getProtocol()+"://"+u.getHost()+ ( u.getPort() > 0 ? ":"+u.getPort(): "" ) + u.getPath();
			
			sb.append("var f=document.getElementById(\"pamLauncherForm\");");
			sb.append("f.action = \""+encodeJS(targetUrl)+"\";");
			for (String part: (u.getQuery() != null && !u.getQuery().trim().isEmpty()? u.getQuery().split("&"): new String[0]))
			{
				int i = part.indexOf("=");
				String tag = i > 0? part.substring(0, i): part;
				String value = i >0? part.substring(i+1): "";
				sb.append("f.elements.namedItem(\""+encodeJS( URLDecoder.decode( tag, "UTF-8") )+"\").value=\""+
						URLDecoder.decode(encodeJS(value), "UTF-8")+"\";");
			}
			sb.append("f.submit();");
			Clients.evalJavaScript(sb.toString());

		}
	}
	
	public String encodeJS(String url) {
		return url.replaceAll("\\\\","\\\\\\\\").replaceAll("'", "\\'");
	}

}
