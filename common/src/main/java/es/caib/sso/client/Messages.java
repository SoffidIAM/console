package es.caib.sso.client;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages
{
	private static final String BUNDLE_NAME = "es.caib.sso.client.messages"; //$NON-NLS-1$

	private Messages()
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
