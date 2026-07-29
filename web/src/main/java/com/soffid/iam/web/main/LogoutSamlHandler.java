package com.soffid.iam.web.main;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.LogFactory;
import org.opensaml.saml.common.xml.SAMLConstants;
import org.zkoss.util.resource.Labels;
import org.zkoss.xml.XMLs;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zul.Html;

import com.soffid.iam.api.SamlRequest;

import es.caib.seycon.ng.exception.InternalErrorException;
import com.soffid.iam.ServiceLocator;

public class LogoutSamlHandler extends Html {
	@Override
	public void onPageAttached(Page newpage, Page oldpage) {
		super.onPageAttached(newpage, oldpage);
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String params = req.getParameter("samlLogout");
		StringBuilder sb = new StringBuilder();
		if (params != null) {
			String hostName = req.getHeader("Host");
			SamlRequest r;
			try {
				r = ServiceLocator.instance().getSamlService().generateLogoutRequest(hostName, params);
				sb.append("<p><form action='")
				.append(r.getUrl())
				.append("' ");
				if (r.getMethod().equals(SAMLConstants.SAML2_POST_BINDING_URI))	
					sb.append("method='POST'");
				sb.append(">");
				for (Entry<String, String> param: r.getParameters().entrySet()) {
					sb.append("<input type='hidden' value='")
					.append( 
						Base64.getEncoder().encodeToString(
								param.getValue().toString().getBytes(StandardCharsets.UTF_8)))
					.append("' name='")
					.append(param.getKey())
					.append("' />");
				}
				sb.append("<input type='submit' value='")
				.append( XMLs.encodeAttribute(Labels.getLabel("LogoutServlet.global")))
				.append("' /> </form></p>");
			} catch (InternalErrorException e) {
				LogFactory.getLog(getClass())
					.warn("Cannot generate SAML Logout request");
			}
		}
		newpage.setVariable("globalLogout", sb.toString());
	}

}
