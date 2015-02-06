package com.soffid.web;

import java.io.IOException;

import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.pdf.codec.Base64.OutputStream;

public class ErrorServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = null;
		String message = null;
		String type = null;
		String reason = null;
		Object codeObj = req.getAttribute("javax.servlet.error.status_code");
		Object messageObj = req.getAttribute("javax.servlet.error.message");
		Object typeObj = req.getAttribute("javax.servlet.error.exception_type");
		Throwable th = (Throwable) req
				.getAttribute("javax.servlet.error.exception");
		String uri = (String) req
				.getAttribute("javax.servlet.error.request_uri");

		if (uri == null) {
			uri = req.getRequestURI(); // in case there's no URI given
		}

		// Convert the attributes to string values
		if (codeObj != null)
			code = codeObj.toString();
		if (messageObj != null)
			message = messageObj.toString();
		if (typeObj != null)
			type = typeObj.toString();

		// The error reason is either the status code or exception type
		if (code.equals("404"))
			reason = "Page not found";
		else if (code.equals("500"))
			reason = "Internal Error";
		else
			reason = (code != null ? code : type);

		org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory
				.getLog("HtmlError");

		if (th == null)
			log.warn("Error on " + uri + ": " + reason + " - " + message);
		else {
			Throwable root = th;
			do {
				if (root instanceof javax.ejb.EJBException) {
					if (((EJBException) root).getCausedByException() == null)
						break;
					else
						root = ((EJBException) root).getCausedByException();
				} else if (root.getCause() == null || root.getCause() == root)
					break;
				else
					root = root.getCause();
			} while (true);
			message = message + " " + root.toString();
			log.warn("Error on " + uri + ": " + reason + " - " + message, th);
			if (root instanceof javax.security.auth.login.LoginException) {
				try {
					Class c = Class
							.forName("es.caib.loginModule.util.SessionManager"); //$NON-NLS-1$
					Object sessionManager = c.newInstance();
					java.lang.reflect.Method m = c
							.getMethod(
									"endSession", //$NON-NLS-1$
									new Class[] {
											javax.servlet.http.HttpServletRequest.class,
											javax.servlet.http.HttpServletResponse.class });

					m.invoke(sessionManager, new Object[] { req, resp });
				} catch (Exception e) {

				}
				try {
					resp.addHeader("Location", "/");
					resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
					javax.servlet.http.HttpSession httpSession = req
							.getSession();
					httpSession.invalidate();
					return;
				} catch (Exception e) {

				}
			}
		}
		ServletOutputStream out = resp.getOutputStream();
		out.println("<html><link rel=\"stylesheet\" type=\"text/css\" href=\"/zkau/web/zul/css/normmoz.css.dsp\"/>"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"/zkau/web/zkex/zul/css/layout.css.dsp\"/>"
				+ "<body><p style=\"height:100px; \"/>"
				+ "<div style=\"margin-left:auto; margin-right: auto; width:40em; text-align:center; margin-top:auto; margin-bottom:auto; min-height:10em; vertical-align:middle;\">"
				+ "<p><img src=\"/anonymous/logo.png\" alt=\"Soffid logo\" /> </p>"
				+ "<p style=\"height:20px; \"/>"
				+ "<p>Our apologies</p>"
				+ "<p style=\"height:20px; \"/>"
				+ "<p>An error has been ocurred processing your request</p>"
				+ "<p style=\"color:red\">"
				+ encode(reason)
				+ " : "
				+ encode(message) + "</p>" + "</div></html</body>");
	}

	String encode(String src) {
		return src.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;").replaceAll("\n", "<br/>");
	}

}
