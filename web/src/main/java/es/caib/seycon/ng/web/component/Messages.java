/**
 * 
 */
package es.caib.seycon.ng.web.component;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author (C) Soffid 2013
 *
 */
public class Messages {
	private static final String BUNDLE_NAME = "es.caib.seycon.ng.web.component.messages"; //$NON-NLS-1$

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
