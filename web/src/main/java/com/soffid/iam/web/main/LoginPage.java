package com.soffid.iam.web.main;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zul.Html;

import com.soffid.iam.web.zk.BPMLabelLocator;
import com.soffid.iam.web.zk.V2LabelLocator;

import es.caib.seycon.ng.exception.InternalErrorException;


public class LoginPage extends Html {
	private String error;

	public LoginPage() throws IOException, InternalErrorException {
		Execution execution = Executions.getCurrent();
		javax.servlet.http.HttpServletRequest req = (HttpServletRequest) execution.getNativeRequest();
		String tenant = new com.soffid.iam.filter.TenantExtractor().getTenant(req);
		boolean saml = "true".equals(com.soffid.iam.utils.ConfigurationCache.getTenantProperty(tenant, "soffid.auth.saml"));
		boolean autoLogin = false;		                  
		String user = "";
		String password = "";
		String rootPath = req.getContextPath();
		if (saml)
		{
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
					execution.sendRedirect( execution.getContextPath()+"/saml");
				}
			}
		}
	
		if (org.zkoss.util.resource.Labels.getLabel("login.lblUser") == null)
		{
			org.zkoss.util.resource.Labels.register(new V2LabelLocator());
			org.zkoss.util.resource.Labels.register(new BPMLabelLocator());
		}
		
		String failed = req.getParameter("failed");
		error = "";
	    if (failed != null && ! failed.isEmpty())
	    {
	      error = "Login failed";
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
	}
	
}
