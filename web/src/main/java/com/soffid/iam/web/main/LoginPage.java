package com.soffid.iam.web.main;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zul.Html;

import com.soffid.iam.web.zk.BPMLabelLocator;
import com.soffid.iam.web.zk.V2LabelLocator;

import es.caib.seycon.ng.exception.InternalErrorException;


public class LoginPage extends Html {
	private String error;
	public LoginPage() throws IOException, InternalErrorException {
		Execution execution = Executions.getCurrent();
		javax.servlet.http.HttpServletRequest req = (HttpServletRequest) execution.getNativeRequest();
	
		if (org.zkoss.util.resource.Labels.getLabel("login.lblUser") == null)
		{
			org.zkoss.util.resource.Labels.register(new V2LabelLocator());
			org.zkoss.util.resource.Labels.register(new BPMLabelLocator());
		}
		
		String failed = req.getParameter("failed");
		error = "";
	    if (failed != null && ! failed.isEmpty())
	    {
	      error = "\u26a0 Login failed";
	    }
	    if (req.getRequestURL().toString().endsWith("/zkau")) {
            ((HttpServletResponse) execution.getNativeResponse()).sendError(401, "Unauthenticatd ajax request");
        	return;
        }
	    
		try {
			com.soffid.iam.web.zk.ConfigureUserSettings.configuraLocale(null); 
		} catch (Throwable th) {
		}		    

	}

	@Override
	public void onPageAttached(Page newpage, Page oldpage) {
		super.onPageAttached(newpage, oldpage);
		setVariable("error", error, false);
		boolean autoLogin = false;		                  
		Execution currentExecution = Executions.getCurrent();
		HttpServletRequest req = (HttpServletRequest) currentExecution.getNativeRequest();
		try {
			String tenant = new com.soffid.iam.filter.TenantExtractor().getTenant(req);
			boolean saml = "true".equals(com.soffid.iam.utils.ConfigurationCache.getTenantProperty(tenant, "soffid.auth.saml"));
			if (saml)
			{
				String user = "";
				String password = "";

				Session session = getDesktop().getSession();
				String[] token = (String[]) session.getAttribute("samlLoginToken");
				user = token != null && token.length == 2 ? token[0]: "";
				password = token != null && token.length == 2 ? token [1]: "";

				autoLogin = !user.isEmpty() && !password.isEmpty();
				session.removeAttribute("samlLoginToken");
				if (!autoLogin)
				{
					boolean classic = ! "false".equals(com.soffid.iam.utils.ConfigurationCache.getTenantProperty(tenant, "soffid.auth.classic"));
					if (!classic)
					{
						response("hide-loginbox", new org.zkoss.zk.au.out.AuScript(this, "document.getElementById('loginbox').setAttribute(\"style\",\"display: none\");"));
						currentExecution.sendRedirect( "/saml");
					}
				} else {
					setVariable("user", user, false);
					setVariable("password", password, false);
				}
				setVariable("autoLogin", autoLogin, false);
			} else {
				setVariable("autoLogin", false, false);
			}
			setVariable("saml", saml, false);
		} catch (InternalErrorException e) {
			throw new UiException(e);
		}
	}
	
}
