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

		
		Session session = WebManager.getSession(getServletContext(), req);
		java.io.InputStream in = req.getInputStream ();
		java.io.ObjectInputStream oin = new java.io.ObjectInputStream (in);
		Object object;
		try {
			object = oin.readObject();
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
		session.setAttribute("tmp-signatura", object); //$NON-NLS-1$
	}

	public void service(Page page) {
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		
		Object object;
		try {
			java.io.InputStream in = req.getInputStream ();
			java.io.ObjectInputStream oin = new java.io.ObjectInputStream (in);
			object = oin.readObject();
		} catch (Exception e) {
			throw new UiException (e);
		}

		Execution exec = Executions.getCurrent();
		exec.setAttribute("signatura", object); //$NON-NLS-1$
	}

}
