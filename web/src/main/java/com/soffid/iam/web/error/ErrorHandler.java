package com.soffid.iam.web.error;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.soffid.iam.common.security.ObserveObligationException;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.MailUtils;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.obligation.ObligationManager;

import es.caib.bpm.toolkit.exception.UserWorkflowException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.SoffidStackTrace;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.zkiblaf.Missatgebox;

public class ErrorHandler extends Window implements AfterCompose {

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog("HtmlError");
	private String stackTrace;
	private Date originalDate;

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
		originalDate = new Date();
		Execution execution = Executions.getCurrent();
		HttpServletRequest req = (HttpServletRequest) execution.getNativeRequest();

		Throwable e = (Throwable) req.getAttribute("javax.servlet.error.exception");
		String c = null;
		String msg = null;
		Label messageLabel = (Label)getFellow("missatge");
		Textbox exceptionLabel = (Textbox)getFellow("exception");
		if (e instanceof Throwable)
		{
			StringBuffer msgBuffer = new StringBuffer();
		    Throwable original = (Throwable) e;
		    e = getRootException(e, msgBuffer);

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
		    			msgBuffer = new StringBuffer();
		    		    e = getRootException(e, msgBuffer);
		    		}
		    	}
		    }
		    
		    msg = msgBuffer.toString();
		    
		    if (e instanceof javax.security.auth.login.LoginException )
			{
				getFellow("categoryDiv").setVisible(false);
				messageLabel.setValue ( Labels.getLabel("error.SessionExpired") );
				getFellow("closeButton").setVisible(false);
				getFellow("resetButton").setVisible(false);
				getFellow("resetButton2").setVisible(true);
				resetSession ();
				return;
			}
			else
			{
				if (msg == null || msg.trim().isEmpty())
					msg = e.toString();
				((Label)getFellow("category")).setValue(e.getClass().getSimpleName());
				if (e instanceof es.caib.seycon.ng.exception.InternalErrorException ||
						e instanceof SeyconException ||
						e instanceof UserWorkflowException ||
						e instanceof RuntimeException) {
					getFellow("categoryDiv").setVisible(false);
					messageLabel.setValue( e.getMessage() );
				}
				else if (e instanceof SecurityException)
					messageLabel.setValue( Labels.getLabel("error.securityException")+ ": "+  msg );
				else if (e instanceof Exception)
					messageLabel.setValue( SoffidStackTrace.generateEndUserDescription((Exception) original) );
				else
					messageLabel.setValue(msg);
				
			} 
			c = es.caib.seycon.ng.exception.SoffidStackTrace.getStackTrace(original);
			log.warn (Labels.getLabel("error.NoPrevist")+ " " 
					+execution.getDesktop().getRequestPath()+" ["+(execution.getUserPrincipal() == null? "nobody": execution.getUserPrincipal().getName())+"] "+
				"[Remote="+execution.getRemoteAddr()+"]",
				original);
			
			String notification = ConfigurationCache.getProperty("soffid.error.notification");
			if (notification != null && ! Security.isUserInRole("authorization:all")) {
				getFellow("techDataDiv").setVisible(false);
				getFellow("notify").setVisible(true);
			    ByteArrayOutputStream out = new ByteArrayOutputStream();
			    original.printStackTrace( new PrintStream(out));
			    stackTrace = out.toString();
	    		response(null ,
	    				new AuScript(this, "setTimeout( function() {"
	    						+ "html2canvas(document.body).then(function(canvas) {"
	    						+ "var t = document.getElementById('"+getFellow("pageimage").getUuid()+"');"
	    						+ "var t2 = document.getElementById('"+getFellow("pageurl").getUuid()+"');"
	    						+ "t.value=canvas.toDataURL('image/png');"
	    						+ "zkTxbox.onupdate(t);"
	    						+ "t2.value=document.location.href;"
	    						+ "zkTxbox.onupdate(t2);"
	    				+"}, 500)"
	    				+ "});"));
			    	
			}
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
	
	public void notify (Event event) throws UnknownHostException, InternalErrorException, NamingException {
		String notification = ConfigurationCache.getProperty("soffid.error.notification");
		
		Textbox tb = (Textbox) getFellow("pageimage");
		String img = tb.getValue();
		img = img.substring(img.indexOf("base64,") + 7);
		byte[] b = es.caib.seycon.util.Base64.decode(img);
		tb = (Textbox) getFellow("pageurl");
		String url = tb.getValue();

		String text = "<html><body><p>This is the error stack of an unexpected issue</p>" 
				+ "<p>User: " + encode(Security.getCurrentTenantName()) + "\\" + encode(Security.getCurrentAccount()) + "</p>" 
				+ "<p>Page: " + encode(url) + "</p>" 
				+ "<p>Date: " + encode(DateFormats.getDateTimeFormat().format(originalDate)) + "</p>"
				+ "<p>Console: " + encode(InetAddress.getLocalHost().getHostName()) + "</p>" 
				+ "<p>Stack trace: <pre>" + encode(stackTrace) + "</pre></p>"
				+ "<p>Browser content:</p>" + "<p><img width='250px' src='cid:image'></p><p>Regards, <br><br>Soffid</p></body></html>";
		
		Session session = MailUtils.getSession(null);

		MimeMessage msg = null;
		msg = new MimeMessage(session);

		// -- Create a new message --

		// -- Set the FROM and TO fields --
		try {

			String from = ConfigurationCache.getProperty("mail.from");
			msg.setFrom(new InternetAddress(from));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(notification));
			// -- Set the subject and body text --
			msg.setSubject("Soffid error "+
					new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+" "+
					Security.getCurrentTenantName()+"/"+Security.getCurrentAccount(), "UTF-8");

			MimeMultipart multipart = new MimeMultipart("related");

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(text, "text/html; charset=UTF-8");

			multipart.addBodyPart(messageBodyPart);

			// second part (the image)
			messageBodyPart = new MimeBodyPart();

			ByteArrayDataSource dataSrc = new javax.mail.util.ByteArrayDataSource(b, "image/png");

			messageBodyPart.setDataHandler(new javax.activation.DataHandler(dataSrc));
			messageBodyPart.setHeader("Content-ID", "<image>");

			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);

			// put everything together
			msg.setContent(multipart);

			// -- Set some other header information --
			msg.setHeader("X-Mailer", "SoffidMailer"); //$NON-NLS-1$ //$NON-NLS-2$
			msg.setSentDate(new Date());

			// -- Send the message --
			Transport.send(msg);

		} catch (AddressException e) {
			Missatgebox.avis(Labels.getLabel("error.cannotsendmail"));
		} catch (MessagingException e) {
			Missatgebox.avis(Labels.getLabel("error.cannotsendmail"));
		}
		
		Missatgebox.avis(Labels.getLabel("error.notified"));
		detach();
	}

	private String encode(String s) {
		return s.replace("&", "&amp;")
				.replace("<", "&lt;")
				.replace(">", "&gt;")
				.replace("\n", "<br>");
	}

	public Throwable getRootException(Throwable e, StringBuffer msgBuffer) {
		Throwable cause = null;
		String lastMessage = e.getMessage();
		int lastPos = 0;
		msgBuffer.append(e.getMessage());
		do {
			if ( e instanceof javax.ejb.EJBException ) 
				cause = ((EJBException)e).getCausedByException ();
			else if (e instanceof SecurityException || e instanceof javax.ejb.AccessLocalException) {
				cause = e.getCause ();
			}
			else
				cause = e.getCause ();
			if (cause == null || cause == e)
				break;
			if (lastMessage.equals(cause.toString()))
				msgBuffer.delete(lastPos, msgBuffer.length());
			String m = cause.getMessage();
			if ( m == null ) m = cause.getClass().getSimpleName();
			if (! (cause instanceof EJBException)) {
				// Remove previous message
				if (! msgBuffer.toString().contains(m))
				{
					lastMessage = m;
					lastPos = msgBuffer.length();
					if (msgBuffer.length() > 0)
						msgBuffer.append("\ncaused by: ");
					msgBuffer.append(m);
				}
			}
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
