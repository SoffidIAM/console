/**
 * 
 */
package com.soffid.iam.reconcile.service;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author areina
 *
 */
public class Messages
{
	private static final String BUNDLE_NAME = "com.soffid.iam.reconcile.service.messages"; //$NON-NLS-1$

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
