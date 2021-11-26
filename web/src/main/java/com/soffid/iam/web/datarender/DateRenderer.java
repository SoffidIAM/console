package com.soffid.iam.web.datarender;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.Locales;

import java.util.TimeZone;
import com.soffid.iam.EJBLocator;
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.exception.InternalErrorException;

public class DateRenderer {
	public TimeZone getUserTimezone() {
		try {
			String tz = EJBLocator.getPreferencesService().findMyPreference("timezone");
			if (tz != null)
				return TimeZone.getTimeZone(tz);
		} catch (Exception e) {
		}
		try {
			String tz = ConfigurationCache.getProperty("soffid.timezone");
			if (tz != null)
				return TimeZone.getTimeZone(tz);
		} catch (Exception e) {
		}
		return TimeZone.getDefault();
	}
	
	public String getDateFormat() {
		try {
			String tz = EJBLocator.getPreferencesService().findMyPreference("dateformat");
			if (tz != null)
				return tz;
		} catch (Exception e) {
		}
		try {
			String tz = ConfigurationCache.getProperty("soffid.dateformat");
			if (tz != null)
				return tz;
		} catch (Exception e) {
		}
		final DateFormat df = DateFormat.getDateInstance(
				DateFormat.DEFAULT, Locales.getCurrent());
		if (df instanceof SimpleDateFormat) {
			final String fmt = ((SimpleDateFormat)df).toPattern();
			if (fmt != null && !"M/d/yy h:mm a".equals(fmt))
				return fmt; //note: JVM use "M/d/yy h:mm a" if not found!
		}
		return "yyyy-MM-dd";
	}

	public String getDateTimeFormat() {
		return getDateFormat()+" HH:mm:SS";
	}
}
