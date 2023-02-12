package com.soffid.iam.web.profile;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.Locales;
import org.zkoss.util.TimeZones;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import java.text.SimpleDateFormat;
import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Password;
import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.service.ejb.PreferencesService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.common.ChangePass;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.OtpPageHandler;
import com.soffid.iam.web.zk.ConfigureUserSettings;

import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.InvalidPasswordException;
import es.caib.zkib.component.Databox;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Application;


public class ProfileHandler extends FrameHandler {

	public ProfileHandler() throws InternalErrorException {
		super();
	}

	public void afterCompose() {
		Application.registerPage(this);
		// Locale
		Locale locale = Locales.getCurrent();		
		Databox lb = (Databox) getFellow("idioma");
		List<String> values = new LinkedList<>();
	    for (int i = 0; i < com.soffid.iam.web.Languages.langs.length; i++)
	    {
	    	values.add(com.soffid.iam.web.Languages.langs[i]+":"+
	    			com.soffid.iam.web.Languages.descriptions[i]); 
	    }
	    lb.setValues(values);
	    lb.setValue(locale.getLanguage());

	    // Timezone
	    TimeZone tz = TimeZones.getCurrent();
		lb = (Databox) getFellow("timezone");
		values = new LinkedList<>();
		List<TimeZone> timezones = new LinkedList<>();
		Set<String> names=new HashSet<>();
		String current = null;
	    for (String id: TimeZone.getAvailableIDs())
	    {
	    	String displayName = TimeZone.getTimeZone(id).getDisplayName();
			if (!names.contains(displayName))
			{
				names.add(displayName);
				if (tz != null && displayName.equals(tz.getDisplayName()))
					timezones.add(tz);
				else
					timezones.add(TimeZone.getTimeZone(id));
					
			}
	    }
	    final long now = System.currentTimeMillis();
	    timezones.sort(new Comparator<TimeZone>() {
			@Override
			public int compare(TimeZone o1, TimeZone o2) {
				return o1.getOffset(now) - o2.getOffset(now);
			}
		});
	    
	    
	    for (TimeZone timezone: timezones)
	    {
	    	String s;
	    	int offset = timezone.getOffset(System.currentTimeMillis())/60000;
	    	if (offset == 0) s = "(GMT)";
	    	else { 
	    		if (offset > 0) s = "(GMT+";
	    		else {s = "(GMT-"; offset = -offset;}
	    		int hours = offset / 60;
	    		int minutes = offset % 60;
	    		if (minutes == 0)
	    			s = s + hours + ")";
	    		else
	    			s = s +hours+":"+minutes+")";
	    	}
	    				
			values.add(timezone.getID()+":"+ timezone.getDisplayName()+" "+s);
	    }
	    lb.setValues(values);
	    lb.setValue(tz.getID());
	    
	    lb = (Databox) getFellow("dateformat");
	    lb.setValue(DateFormats.getDateFormatString());

	    lb = (Databox) getFellow("timeformat");
	    lb.setValue(DateFormats.getTimeFormatString());
	    
	    updateSample();

		if (new OtpPageHandler().needsOtp(this))
			setVisible(false);
	}

	public void updateSample() {
	    Databox lb = (Databox) getFellow("dateformat");
	    Databox slb = (Databox) getFellow("sampledateformat");
	    String f = (String) lb.getValue();
	    try {
	    	String t = new SimpleDateFormat(f).format(new Date());
	    	slb.setValue(t);
	    	lb.setWarning(null, "");
	    } catch (Exception e) {
	    	lb.setWarning(null, e.getMessage());
	    	slb.setValue("");
	    }

	    lb = (Databox) getFellow("timeformat");
	    slb = (Databox) getFellow("sampletimeformat");
	    f = (String) lb.getValue();
	    try {
	    	String t = new SimpleDateFormat(f).format(new Date());
	    	slb.setValue(t);
	    	lb.setWarning(null, "");
	    } catch (Exception e) {
	    	lb.setWarning(null, e.getMessage());
	    	slb.setValue("");
	    }
	    
	}

	@Override
	public void onPageAttached(Page newpage, Page oldpage) {
		super.onPageAttached(newpage, oldpage);
		try {
			String lastIp = EJBLocator.getPreferencesService().findMyPreference("last_ip");
			String lastLogin = EJBLocator.getPreferencesService().findMyPreference("last_login");
			if (lastLogin != null) {
				setVariable("lastIp", lastIp, true);
				setVariable("lastLogin", DateFormats.getDateTimeFormat().format(new Date(Long.parseLong(lastLogin))), true);
			}
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	@Override
	public void apply(Event ev) throws CommitException {
		super.apply(ev);
		
		try {
			PreferencesService svc = EJBLocator.getPreferencesService();
			
			Session session = getDesktop().getSession();
			Databox lb = (Databox) getFellow("idioma");
			String lang = (String) lb.getValue();
			Locales.setThreadLocal(new Locale(lang));
			svc.updateMyPreference("lang", lang);
			session.setAttribute(ConfigureUserSettings.SESSIO_IDIOMA, lang);
			session.setAttribute(Attributes.PREFERRED_LOCALE, new Locale(lang));

			
			lb = (Databox) getFellow("timezone");
			String tz = (String) lb.getValue();
			TimeZones.setThreadLocal(TimeZone.getTimeZone(tz));
			svc.updateMyPreference("timezone", tz);
			session.setAttribute(ConfigureUserSettings.SESSIO_TIMEZONE, TimeZone.getTimeZone(tz));

			
			lb = (Databox) getFellow("dateformat");
			String h = (String) lb.getValue().toString().trim();

			lb = (Databox) getFellow("timeformat");
			String m = (String) lb.getValue().toString().trim();

			DateFormats.setThreadLocal(new String[] {h, m});
			svc.updateMyPreference("dateformat", h);
			session.setAttribute(ConfigureUserSettings.SESSIO_DATEFORMAT, h);
			svc.updateMyPreference("timeformat", m);
			session.setAttribute(ConfigureUserSettings.SESSIO_TIMEFORMAT, m);
			
			Application.goBack();
		} catch (Exception e) {
			throw new UiException(e);
		}

	}
	
	@Override
	public void undo(Event ev) throws CommitException {
		Application.goBack();
	}
	
	public void setPassword(Event event) {
		Window wnd = (Window) getFellow("newPassword");
		Databox p0 = (Databox) wnd.getFellow("p0");
		Databox p1 = (Databox) wnd.getFellow("p1");
		Databox p2 = (Databox) wnd.getFellow("p2");
		p0.setValue("");
		p1.setValue("");
		p2.setValue("");
		p0.setWarning(null, "");
		p1.setWarning(null, "");
		p2.setWarning(null, "");
		
		Databox l = (Databox) wnd.getFellow("policy");
		String policy = "";
		try {
			String system = EJBLocator.getPasswordService().getDefaultDispatcher();
			policy = EJBLocator.getPasswordService().getPolicyDescription(Security.getCurrentAccount(), system);
		} catch (Exception e) {
			policy = "";
		}
		l.setValue(policy);
		
		wnd.doHighlighted();
	}
	
	public void onCancelPassword() {
		Window wnd = (Window) getFellow("newPassword");
		Databox p1 = (Databox) wnd.getFellow("p1");
		Databox p2 = (Databox) wnd.getFellow("p2");
		p1.setValue("");
		p2.setValue("");
		wnd.setVisible(false);
		getModel().refresh();
	}
	
	public void onSetPassword() throws InternalErrorException, NamingException, CreateException, BadPasswordException, InvalidPasswordException {
		Window wnd = (Window) getFellow("newPassword");
		Databox p0 = (Databox) wnd.getFellow("p0");
		Databox p1 = (Databox) wnd.getFellow("p1");
		Databox p2 = (Databox) wnd.getFellow("p2");
		String pp0 = (String) p0.getValue();
		String pp1 = (String) p1.getValue();
		String pp2 = (String) p2.getValue();
		if (pp0 == null || pp0.trim().isEmpty())
		{
			p0.setWarning(null, "Please, enter current password");
		}
		else if (pp1 == null || pp1.trim().isEmpty())
		{
			p1.setWarning(null, "Please, enter a password");
		}
		else if (pp2 == null || pp2.trim().isEmpty())
		{
			p1.setWarning(null,  "");
			p2.setWarning(null, "Please, enter the password twice");
		}
		else if ( ! pp2.equals(pp1)) 
		{
			p1.setWarning(null,  "");
			p2.setWarning(null, "Please, enter the password twice");
		}
		else
		{
			p0.setWarning(null,  "");
			p1.setWarning(null,  "");
			p2.setWarning(null,  "");
			
			new ChangePass().changePassword(Security.getCurrentUser(), pp0, pp1);
			es.caib.zkib.zkiblaf.Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("accounts.setPassword.msg"));
			onCancelPassword();
		}
	}


	public void nextPassword() {
		Window wnd = (Window) getFellow("newPassword");
		Databox p2 = (Databox) wnd.getFellow("p1");
		p2.focus();
	}

	public void nextPassword2() {
		Window wnd = (Window) getFellow("newPassword");
		Databox p2 = (Databox) wnd.getFellow("p2");
		p2.focus();
	}
}
