/**
 * 
 */
package com.soffid.iam.model.identity;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author areina
 *
 */
public class Messages
{
	private static final String BUNDLE_NAME = "es.caib.seycon.ng.model.identity.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
					.getBundle(BUNDLE_NAME);

	private Messages ()
	{
	}

	public static String getString (String key)
	{
		try
		{
            return com.soffid.iam.lang.MessageFactory.getString(BUNDLE_NAME, key);
		}
		catch (MissingResourceException e)
		{
			return '!' + key + '!';
		}
	}
}
