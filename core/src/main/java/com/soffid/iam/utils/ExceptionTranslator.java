package com.soffid.iam.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.soffid.tools.db.persistence.XmlReader;
import com.soffid.tools.db.schema.Database;

public class ExceptionTranslator {
	ExceptionTranslator instance = new ExceptionTranslator();	
	
	public static String VALOR_CAMP_MASSA_GRAN = Messages.getString("ExceptionTranslator.BigValueToImput"); //$NON-NLS-1$

	
	private ExceptionTranslator(){			
	}
	
	public static String translate(Throwable e){
		LogFactory.getLog(ExceptionTranslator.class).warn("Internal error: ", e);
		String sqlError = e.getMessage();
		if(e.getCause() != null){
			sqlError = e.getCause().getMessage();
		}
		if (sqlError != null) {
		    return getErrorMessage(sqlError);
		} else
		    return "";
	}

	private static String getErrorMessage(String sqlError)
	{
		String missatgeError = getPKUKError(sqlError);
		if(missatgeError == null){
			missatgeError = getFKError(sqlError);
		}
		if(missatgeError == null){
			missatgeError = getValueTooLargeForColumn(sqlError);
		}
		if(missatgeError == null){
			missatgeError = String.format(Messages.getString("ExceptionTranslator.UnknownError"), sqlError);  //$NON-NLS-1$
		}
		return missatgeError;
	}
	
	private static String getValueTooLargeForColumn(String sqlError){
		if(sqlError!=null && sqlError.contains("value too large for column")){  //$NON-NLS-1$
			return VALOR_CAMP_MASSA_GRAN;
		}
		return null;
	}
	
	private static String getPKUKError(String sqlError){
		try {
			String regexp = "([a-zA-Z]{3})_(PK|UK)_"; //$NON-NLS-1$
			Pattern pattern = 
				Pattern.compile(regexp);			
			Matcher matcher = 
	            pattern.matcher(sqlError);
			matcher.find();
            String codiTipus = matcher.group(1);            
            return String.format(Messages.getString("ExceptionTranslator.AlreadyExists"), //$NON-NLS-1$
            		(codiTipus), matcher.group(0), sqlError);
		} catch (Exception e) {
			return null;
		}
	}
	
	private static String getFKError(String sqlError){
		try {
			String regexp = "([a-zA-Z]{3})_([a-zA-Z]{3}[2]?)_FK"; //$NON-NLS-1$
			Pattern pattern = 
				Pattern.compile(regexp);			
			Matcher matcher = 
	            pattern.matcher(sqlError);
			matcher.find();
            String from = matcher.group(1);
            String to = matcher.group(2);
            return String.format(Messages.getString("ExceptionTranslator.DeleteError"), //$NON-NLS-1$
            		(to), (from), matcher.group(0), sqlError);
		} catch (Exception e) {
			return null;
		}
	}

}

