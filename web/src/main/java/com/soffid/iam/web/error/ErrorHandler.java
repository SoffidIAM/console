package com.soffid.iam.web.error;

import javax.ejb.EJBException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.soffid.iam.common.security.ObserveObligationException;
import com.soffid.iam.web.obligation.ObligationManager;

import es.caib.zkib.zkiblaf.Missatgebox;

public class ErrorHandler extends Window implements AfterCompose {

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog("HtmlError");

	public ErrorHandler() {
	}

	public void showStack() {
		Textbox exceptionLabel = (Textbox)getFellow("exception");
		Div arrow = (Div) getFellow("collapser");
		if (exceptionLabel.isVisible()) {
			exceptionLabel.setVisible(false);
			arrow.setSclass("collapser");
		} else {
			exceptionLabel.setVisible(true);
			arrow.setSclass("collapser open");
		}
	}

	@Override
	public void afterCompose() {
		Execution execution = Executions.getCurrent();
		HttpServletRequest req = (HttpServletRequest) execution.getNativeRequest();

		Throwable e = (Throwable) req.getAttribute("javax.servlet.error.exception");
		String c = null;
		String msg = null;
		Label messageLabel = (Label)getFellow("missatge");
		Textbox exceptionLabel = (Textbox)getFellow("exception");
		if (e instanceof Throwable)
		{
		    Throwable original = (Throwable) e;
		    e = getRootException(e);

		    
		    if (e instanceof ObserveObligationException) {
		    	ObligationManager om = new ObligationManager();
		    	if (om.getNextObligation() != null)
		    	{
		    		try {
		    			om.handleNextObligation();
		    			detach();
		    			return;
		    		} catch (Exception e2) {
		    			original = e = e2;
		    		    e = getRootException(e);
		    		}
		    	}
		    }
		    
		    if (e instanceof javax.security.auth.login.LoginException )
			{
				messageLabel.setValue ( Labels.getLabel("error.SessionExpired") );
				getFellow("closeButton").setVisible(false);
				getFellow("resetButton").setVisible(false);
				getFellow("resetButton2").setVisible(true);
				resetSession ();
				return;
			}
			else
			{
				msg = e.getMessage();
				if (msg == null)
					msg = e.toString();
				if (original != e && original instanceof es.caib.seycon.ng.exception.InternalErrorException)
					messageLabel.setValue( msg );
				else
					messageLabel.setValue( e.getClass().getSimpleName()+": "+msg );
				
			} 
			c = es.caib.seycon.ng.exception.SoffidStackTrace.getStackTrace(original);
			log.warn (Labels.getLabel("error.NoPrevist")+ " " 
					+execution.getDesktop().getRequestPath()+" ["+(execution.getUserPrincipal() == null? "nobody": execution.getUserPrincipal().getName())+"] "+
				"[Remote="+execution.getRemoteAddr()+"]",
				original);
		}
		else
		{
			messageLabel.setValue((String) req.getAttribute("javax.servlet.error.message") );
			c = req.getAttribute("javax.servlet.error.exception_type") +
					 "\n" +
					 req.getAttribute("javax.servlet.error.exception");				
		}
		exceptionLabel.setValue(c);
	}

	public Throwable getRootException(Throwable e) {
		boolean noTeAutoritzacions;
		Throwable cause = null;
		do {
			if ( e instanceof javax.ejb.EJBException ) 
				cause = ((EJBException)e).getCausedByException ();
			else if (e instanceof SecurityException || e instanceof javax.ejb.AccessLocalException) {
				cause = e.getCause ();
				noTeAutoritzacions = true;
			}
			else
				cause = e.getCause ();
			if (cause == null || cause == e)
				break;
			e = cause;
		} while (true);
		return e;
	}

	void resetSession() {
		Executions.sendRedirect(null);
		Execution ex = Executions.getCurrent();
		javax.servlet.http.HttpServletRequest req = (javax.servlet.http.HttpServletRequest) ex.getNativeRequest();
		javax.servlet.http.HttpSession httpSession = req.getSession();
		httpSession.invalidate();
	}

	public void reset(Event ev) {
		Missatgebox.confirmaOK_CANCEL(org.zkoss.util.resource.Labels.getLabel("error.SiReinicia"),
				org.zkoss.util.resource.Labels.getLabel("error.zul.Reinicia"),
				(event) -> {
					if (event.getName().equals("onOK"))
						Executions.sendRedirect(null);
				});
	}
}
