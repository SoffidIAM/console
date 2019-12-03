package com.soffid.iam.web.vault;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.util.Clients;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.LaunchType;
import com.soffid.iam.api.NewPamSession;

import es.caib.seycon.ng.exception.InternalErrorException;

public class LaunchHelper {
	public void launchAccount( Account account ) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException
	{
		String url = account.getLoginUrl();
		
		if ( url == null || url.trim().isEmpty())
			url = (String) account.getAttributes().get("SSO:URL");
		if (url != null)
		{
			if ( account.getLaunchType() == LaunchType.LAUNCH_TYPE_PAM)
				launchPamAccount(account, url);
			else if (account.getLaunchType() == LaunchType.LAUNCH_TYPE_WEBSSO)
				launchWssoAccount(account, url);
			else
				launchStandardAccount(account, url);
		}
	}

	private void launchPamAccount(Account account, String url) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		NewPamSession s = EJBLocator.getPamSessionService().createJumpServerSession(account);
		if (s == null)
			throw new InternalErrorException("Unable to start session");
		else
		{
			URL u = s.getUrl();
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

	private void launchWssoAccount(Account account, String url) throws InternalErrorException, NamingException, CreateException {
		String name = account.getLoginName() == null ? account.getName() : account.getLoginName();
		com.soffid.iam.api.Password password = com.soffid.iam.EJBLocator.getSelfService().queryAccountPasswordBypassPolicy(account);
		org.json.JSONObject j = new org.json.JSONObject();
		for (String att: account.getAttributes().keySet())
		{
			if (att.startsWith("SSO:") &&
					!att.equals("SSO:URL") && 
					!att.equals("SSO:Server"))
			{
				try {
					String value = (String) account.getAttributes().get(att);
					String[] values = value.split("=");
					if (values.length == 2)
					{
						j.put( java.net.URLDecoder.decode(values[0], "UTF-8"), 
								java.net.URLDecoder.decode(values[1], "UTF-8"));
					}
				} catch (Exception e) {}
			}
		}
		try {
			j.put("url", url);
			j.put("account", name);
			j.put("password", password.getPassword());
			Clients.evalJavaScript("launchSsoUrl("+j.toString()+");");
		} catch (Exception e) {
			Clients.evalJavaScript("window.open('"+encodeJS(url)+"', '_blank');");
		}
	}

	private void launchStandardAccount(Account account, String url) {
		Clients.evalJavaScript("window.open('"+encodeJS(url)+"', '_blank');");
	}

	public String encodeJS(String url) {
		return url.replaceAll("\\\\","\\\\\\\\").replaceAll("'", "\\'");
	}
}
