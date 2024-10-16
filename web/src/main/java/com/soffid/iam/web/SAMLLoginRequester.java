package com.soffid.iam.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opensaml.saml.common.xml.SAMLConstants;

import com.soffid.iam.api.SamlRequest;
import com.soffid.iam.filter.TenantExtractor;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.exception.InternalErrorException;

public class SAMLLoginRequester extends HttpServlet {
	Log log = LogFactory.getLog(getClass());
		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (req.getPathInfo() == null || req.getPathInfo().isEmpty()  || req.getPathInfo().equals("/"))
				initialRequest(req, resp);
			else if (req.getPathInfo().equals("/metadata"))
				dumpMetadata(req, resp);
			else if (req.getPathInfo().equals("/log/post"))
				authenticate(req, resp);
			else
				super.service(req, resp);
		} catch (Exception e) {
			log.warn("Internal error generating SAML page", e);
			throw new ServletException("Internal error generating page: "+e.toString());
		}
	}

	protected void initialRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InternalErrorException, NamingException, CreateException {
		String hostName = new TenantExtractor().getTenant(req);
		String context = getContext(req);

		SamlRequest saml = EJBLocator.getSamlService().generateSamlRequest(hostName, context);
		
		if (saml.getMethod().equals(SAMLConstants.SAML2_REDIRECT_BINDING_URI))
		{
			StringBuffer url = new StringBuffer(saml.getUrl()) ;
			boolean first = true;
			for (String key : saml.getParameters().keySet())
			{
				String value = saml.getParameters().get(key);
				url.append( first ? "?": "&")
					.append( URLEncoder.encode(key, "UTF-8"))
					.append('=')
					.append( URLEncoder.encode(value, "UTF-8"));
				first = false;
			}
			resp.sendRedirect( url.toString());
		}
		else
		{
			resp.setContentType("application/xhtml+xml");
			resp.setCharacterEncoding("UTF-8");
			ServletOutputStream out = resp.getOutputStream();
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
			out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" >");
			out.println("<body onload='document.getElementById(\"s\").setAttribute(\"style\",\"display:none\");document.getElementById(\"f\").submit();'>");
			out.print("<form id='f' action='");
			out.print(saml.getUrl());
			out.print("' method='POST'>");
			out.println("<p>Redirecting to "+saml.getUrl()+"... </p>");
			for (String key : saml.getParameters().keySet())
			{
				String value = saml.getParameters().get(key);
				out.print("<input type='hidden' name='");
				out.print(encodeAttribute(key));
				out.print("' value='");
				out.print(encodeAttribute(value));
				out.println("'/>");
			}
			out.print("<input id='s' type='submit' value='Continue'/>");

			out.print("</form></body></html>");
		}
	}

	protected void dumpMetadata(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InternalErrorException, NamingException, CreateException {
		String hostName = new TenantExtractor().getTenant(req);

		String metadata = EJBLocator.getSamlService().generateMetadata(hostName);

		resp.setContentType("application/xml");
		resp.setCharacterEncoding("UTF-8");
		ServletOutputStream out = resp.getOutputStream();
		out.println(metadata);
	}

	private String encodeAttribute(String key) {
		if (key == null)
			return "";
		return key.replaceAll("'", "&apos;")
				.replaceAll("\"", "&quote;")
				.replaceAll("\n", "");
	}

	protected void authenticate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InternalErrorException, NamingException, CreateException {
		log.info("Receiving SAML response");
		String hostName = new TenantExtractor().getTenant(req);
		
		String context = getRedirectPage(req);
		
		Map<String,String> params = new HashMap<String, String>();
		for ( Enumeration e = req.getParameterNames(); e.hasMoreElements(); )
		{
			String tag = (String) e.nextElement();
			String value = req.getParameter(tag);
			params.put(tag, value);
		}
		String[] token = EJBLocator.getSamlService().authenticate(hostName, context, SAMLConstants.SAML2_POST_BINDING_URI, params);
		if (token == null)
			resp.sendRedirect( context );
		else
		{
			req.getSession().setAttribute("samlLoginToken", token);
			req.getServletContext().getRequestDispatcher("/anonymous/login.zul").forward(req, resp);
		}
	}

	private String getContext(HttpServletRequest req) {
		String context = req.getContextPath();
		if (context.isEmpty())
			context = "/";
		return context;
	}

	private String getRedirectPage(HttpServletRequest req) {
		String context = req.getContextPath();
		return context + "/index.zul";
	}
}
