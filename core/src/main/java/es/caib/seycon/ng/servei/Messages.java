package es.caib.seycon.ng.servei;

import java.util.MissingResourceException;

public class Messages {
	private static final String BUNDLE_NAME = "es.caib.seycon.ng.servei.messages"; //$NON-NLS-1$

	private Messages() {
	}

	public static String getString(String key) {
		try {
            return com.soffid.iam.lang.MessageFactory.getString(BUNDLE_NAME, key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
 