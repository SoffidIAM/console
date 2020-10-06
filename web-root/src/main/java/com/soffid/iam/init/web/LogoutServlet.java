package com.soffid.iam.init.web;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.*;
import java.util.Locale;

@WebServlet(name="custom.svg", urlPatterns="/logout/*")
public class LogoutServlet extends HttpServlet {

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(req, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String v_logoutTo = request.getParameter("logoutTo"); //$NON-NLS-1$
		if (v_logoutTo == null) {
			v_logoutTo = ""; //$NON-NLS-1$
		}
		
		String lang = "ca"; //$NON-NLS-1$
		
		//if (equest.getLocale()!=null) lang = equest.getLocale().getLanguage();
		//if (lang==null) lang ="ca"; //per defecte

		String msg_logout = Messages.getString(request, "LogoutServlet.3"); //$NON-NLS-1$
		String msg_tornar = Messages.getString(request, "LogoutServlet.4"); //$NON-NLS-1$

		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"; //$NON-NLS-1$
		html += "<html xmlns=\"http://www.w3.org/1999/xhtml\">" //$NON-NLS-1$
				+ "<head><meta http-equiv=\"Pragma\" content=\"no-cache\" />" //$NON-NLS-1$
				+ "<meta http-equiv=\"Expires\" content=\"-1\" />" //$NON-NLS-1$
				+ "<title>SOFFID IAM</title>" //$NON-NLS-1$
				+ "<style type=\"text/css\">" //$NON-NLS-1$
				+ "body {background-color: #EEE6DD;font-family:Arial,Helvetica,sans-serif;font-size:0.9em;margin:0px;font-size:small;} " //$NON-NLS-1$
				+ "table.messagebox {margin: 8px;}" //$NON-NLS-1$
				+ "div.ocult{ visibility:hidden; top:45%; width:100%;} " //$NON-NLS-1$
				+ "div.centered{ visibility: visible; position:absolute; top:45%; width:100%; text-align:center; ;z-index:324234;} " //$NON-NLS-1$
				+ ".g {color:gray; font-size: 12px;} " //$NON-NLS-1$
				+ "span {font-size:0.9em;} " //$NON-NLS-1$
				+ ".titol_tanca {color:#666666;cursor: pointer;font-family: Arial;font-size: 11px; font-weight: bold;text-decoration:none;} "  //$NON-NLS-1$
				+ ".titol_tanca:hover {text-decoration: underline;}" //$NON-NLS-1$
				+ ".cuadre {border: #bdd3ea 2px solid; background-color:white; padding:20px 40px; display:inline-block; width:400px;}" //$NON-NLS-1$
				+ ".byebye {padding: 5px 5px 20px 5px;}" //$NON-NLS-1$
				+ "</style>" //$NON-NLS-1$
				+ "</head>" //$NON-NLS-1$
				+ "<body>" //$NON-NLS-1$
				// + "<div class=\"embedded\"><div class=\"wc-embedded-none\">"
				+ "<div class=\"centered\"><div class=\"cuadre\"><div class=\"byebye\">" //$NON-NLS-1$
				+ "<span>"+msg_logout+"</span>" //$NON-NLS-1$ //$NON-NLS-2$
				+ "</div><span class=\"g\">&lt;&lt; </span>" //$NON-NLS-1$
				+ "<a href=\"" //$NON-NLS-1$
				+ "../" + v_logoutTo //$NON-NLS-1$
				+ "\" class=\"titol_tanca\">"+msg_tornar+"</a></div></div></div>" //$NON-NLS-1$ //$NON-NLS-2$
				// + "</div></div>"
				+ "</body></html>"; //$NON-NLS-1$

		response.setContentType("text/html; charset=UTF-8"); //$NON-NLS-1$

		PrintWriter printWriter = response.getWriter();
		printWriter.println(html);
		printWriter.flush();
		response.flushBuffer();

		HttpSession s = request.getSession(false);
		if (s != null)
			s.invalidate();
		
		
		/*
		 * SessionManager sessionManager = new SessionManager();
		 * sessionManager.endSession(request, response); HttpSession httpSession
		 * = request.getSession(); httpSession.invalidate();
		 * response.sendRedirect("index.zul");
		 */
	}
}
