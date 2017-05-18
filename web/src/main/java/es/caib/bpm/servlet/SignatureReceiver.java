package es.caib.bpm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.http.WebManager;

public class SignatureReceiver extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1953314858030033610L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		throw new ServletException("Disabled feature");
	}

	public void service(Page page) {
		throw new UiException("Disabled feature");
	}

}
