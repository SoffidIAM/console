/**
 * 
 */
package com.soffid.iam.service;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author areina
 *
 */
public class Messages
{
	private static final String BUNDLE_NAME = "com.soffid.iam.service.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
					.getBundle(BUNDLE_NAME);

	private Messages ()
	{
	}

	public static String getString(String key)
	{
		try
		{
            return es.caib.seycon.ng.comu.lang.MessageFactory.getString(BUNDLE_NAME, key);
		}
		catch (MissingResourceException e)
		{
			return '!' + key + '!';
		}
	}
}
