package com.soffid.iam.utils;

import java.util.Calendar;

import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.exception.InternalErrorException;

public class TimeOutUtils {

	public final static String PROPERTY_TIMEOUT = "soffid.ui.timeout"; //$NON-NLS-1$
	private final static String MESSAGE_RAISETIMEOUT = "TimeOutUtils.checkTimeOut.raiseTimeOut"; //$NON-NLS-1$
	private final static String MESSAGE_NONPROPERTY = "TimeOutUtils.checkTimeOut.nonPropertyTimeOut"; //$NON-NLS-1$
	private long GLOBAL_TIMEOUT;
	private Calendar initialTime = null;

	public TimeOutUtils() throws InternalErrorException {
		GLOBAL_TIMEOUT = getGlobalTimeOut();
		initialTime = Calendar.getInstance();
	}

	public boolean timedOut ()
	{
		Calendar currentTime = Calendar.getInstance();
		long miliseconds = currentTime.getTimeInMillis() - initialTime.getTimeInMillis();
		return miliseconds > GLOBAL_TIMEOUT;
	}
	
	public void checkTimeOut() throws InternalErrorException {
		if (timedOut())
			generateException();
	}

	public static void generateException() throws InternalErrorException {
		throw new InternalErrorException(String.format(Messages.getString(MESSAGE_RAISETIMEOUT), getGlobalTimeOut()));
	}

	public static long getGlobalTimeOut() throws InternalErrorException {
		try {
			return Integer.parseInt(ConfigurationCache.getMasterProperty(PROPERTY_TIMEOUT));
		} catch (Exception e) {
			return 10000;
		}
	}
}
