package com.soffid.iam.web.component;

import java.util.HashMap;
import java.util.regex.Pattern;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.resource.spi.SecurityException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.sys.ExecutionCtrl;
import org.zkoss.zul.Window;

import com.soffid.iam.api.Challenge;
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.exception.InternalErrorException;

public class OtpPageHandler {
	private static final String OTP_WINDOW_ID = "___________otp_window_top_screen_____________";
	private static final String OTP_TIME_SESSION_ATTR = "$Soffid-otp-time$";
	Component component;
	Component parent;
	Window otpWindow;
	String page;
	boolean optional;
	Challenge challenge;
	private Page parentPage;
	HiddenComponent hidden = new HiddenComponent();
			
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Challenge getChallenge() {
		return challenge;
	}
	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}
		
	public boolean isOptional() {
		return optional;
	}
	public void setOptional(boolean optional) {
		this.optional = optional;
	}
	
	
	public boolean hasPreviousOtp ()
	{
		Long lastToken = (Long) Sessions.getCurrent().getAttribute(OTP_TIME_SESSION_ATTR);
		if (lastToken == null)
			return false;
		String timeout = ConfigurationCache.getProperty("soffid.otp.timeout");
		if (timeout == null || timeout.trim().isEmpty())
			timeout = "3"; // Minimum of three seconds
		
		long to = 1000 * Long.parseLong(timeout);
		
		if (to <= 0 || lastToken.longValue() + to > System.currentTimeMillis())
			return true;
		
		return false;
	}
	
	public void enable ()
	{
		hidden.setEnabled(true);
		component.setVisible(true);
		component.invalidate();
		hidden.invalidate();

		otpWindow.detach();
		
		Long l = new Long (System.currentTimeMillis());
		Sessions.getCurrent().setAttribute(OTP_TIME_SESSION_ATTR, l);
	}
	
	public boolean requestOtp () throws InternalErrorException, NamingException, CreateException, SecurityException
	{
		challenge = new com.soffid.iam.api.Challenge();
		com.soffid.iam.api.User u = com.soffid.iam.EJBLocator.getUserService().getCurrentUser();
		if (u == null)
		{
			if (optional)
				return false;
			else
				throw new es.caib.seycon.ng.exception.InternalErrorException("Unknown user");
		}
		
		challenge.setUser(u);
		challenge = com.soffid.iam.EJBLocator.getOTPValidationService().selectToken(challenge);
		if (challenge.getCardNumber() == null)
		{
			if (optional)
				return false;
			else
				throw new SecurityException("Second factor authentication required, but no token is available");
		}
		else
		{
			showOtpDialog ();
			return true;
		}
	}
	
	public void showOtpDialog () throws InternalErrorException, NamingException, CreateException, SecurityException
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
			
		map.put("handler", this);

		otpWindow = new Window();
		otpWindow.setId(OTP_WINDOW_ID);
		if (component == null) {
			Page p = Executions.getCurrent().getDesktop().getPage(page);
			otpWindow.setPage(p);
		} 
		else if (component.getParent() == null) {
			otpWindow.setPageBefore(component.getPage(), component);
			hidden.setPageBefore(component.getPage(), component);
			component.setParent(hidden);
		}
		else {
			component.getParent().insertBefore(otpWindow, component);
			component.getParent().insertBefore(hidden, component);
			component.setParent(hidden);
		}
		Executions.getCurrent().createComponents("/popup/otp.zul", otpWindow, map);
	}
	
	public boolean needsOtp (Component comp) 
	{
		Page p = comp.getPage();
		if (p == null)
			return false;
		
		return needsOtp( comp, p.getRequestPath());
	}
	
	public boolean needsOtp (Component comp, String resource) 
	{
		if (hasPreviousOtp())
			return false;
		if (otpInProgress(comp))
			return false;
		
		component = comp;
		parent = comp.getParent();
		parentPage = comp.getPage();
		page = resource;
		try
		{
			if (page != null && ! page.isEmpty())
			{
				String paths = ConfigurationCache.getProperty("soffid.otp.required");
				if (paths != null && ! paths.trim().isEmpty())
				{
					for (String path: paths.split("[,\\s]+") )
					{
						String p2 = path.startsWith("/") ? path : "/"+path;
						if (p2.equalsIgnoreCase(page) || Pattern.matches(p2, page))
						{
							optional = false;
							if (requestOtp())
								return true;
						}
					}
				}
	
				paths = ConfigurationCache.getProperty("soffid.otp.optional");
				if (paths != null && ! paths.trim().isEmpty())
				{
					for (String path: paths.split("[,\\s]+") )
					{
						String p2 = path.startsWith("/") ? path : "/"+path;
						if (p2.equalsIgnoreCase(page) || Pattern.matches(p2, page))
						{
							optional = true;
							if (requestOtp())
								return true;
						}
					}
				}
	
			}
			return false;
		} catch (Exception e) {
			throw new UiException(e);
		}
	}
	private boolean otpInProgress(Component comp) {
		Page p = comp == null ? Executions.getCurrent().getDesktop().getPage(page): comp.getPage();
		if (p == null)
			return false;
		Component w = p.getFellowIfAny(OTP_WINDOW_ID);
		if (w == null)
			return false;
		else
			return w.isVisible();
	}
	
	public Window getOtpWindow() {
		return otpWindow;
	}
	public void setOtpWindow(Window otpWindow) {
		this.otpWindow = otpWindow;
	}
	
}
