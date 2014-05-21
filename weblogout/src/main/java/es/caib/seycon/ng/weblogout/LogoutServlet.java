package es.caib.seycon.ng.weblogout;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.Locale;

import es.caib.loginModule.util.SessionManager;

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

		String v_logoutTo = request.getParameter("logoutTo");
		if (v_logoutTo == null) {
			v_logoutTo = "";
		}
		
		String lang = "ca";
		
		//if (equest.getLocale()!=null) lang = equest.getLocale().getLanguage();
		//if (lang==null) lang ="ca"; //per defecte

		String msg_logout = "Your Soffid session has been closed.";
		String msg_tornar = "Log in again";

		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
		html += "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
				+ "<head><meta http-equiv=\"Pragma\" content=\"no-cache\" />"
				+ "<meta http-equiv=\"Expires\" content=\"-1\" />"
				+ "<title>SOFFID IAM</title>"
				+ "<style type=\"text/css\">"
				+ "body {background-color: #EEE6DD;font-family:Arial,Helvetica,sans-serif;font-size:0.9em;margin:0px;font-size:small;} "
				+ "table.messagebox {margin: 8px;}"
				+ "div.ocult{ visibility:hidden; top:45%; width:100%;} "
				+ "div.centered{ visibility: visible; position:absolute; top:45%; width:100%; text-align:center; ;z-index:324234;} "
				+ ".g {color:gray; font-size: 12px;} "
				+ "span {font-size:0.9em;} "
				+ ".titol_tanca {color:#666666;cursor: pointer;font-family: Arial;font-size: 11px; font-weight: bold;text-decoration:none;} " 
				+ ".titol_tanca:hover {text-decoration: underline;}"
				+ ".cuadre {border: #bdd3ea 2px solid; background-color:white; padding:20px 40px; display:inline-block; width:400px;}"
				+ ".byebye {padding: 5px 5px 20px 5px;}"
				+ "</style>"
				+ "</head>"
				+ "<body>"
				// + "<div class=\"embedded\"><div class=\"wc-embedded-none\">"
				+ "<div class=\"centered\"><div class=\"cuadre\"><div class=\"byebye\">"
				+ "<span>"+msg_logout+"</span>"
				+ "</div><span class=\"g\">&lt;&lt; </span>"
				+ "<a href=\""
				+ "../" + v_logoutTo
				+ "\" class=\"titol_tanca\">"+msg_tornar+"</a></div></div></div>"
				// + "</div></div>"
				+ "</body></html>";

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter printWriter = response.getWriter();
		printWriter.println(html);
		printWriter.flush();
		response.flushBuffer();

		/*
		 * SessionManager sessionManager = new SessionManager();
		 * sessionManager.endSession(request, response); HttpSession httpSession
		 * = request.getSession(); httpSession.invalidate();
		 * response.sendRedirect("index.zul");
		 */
	}
}
