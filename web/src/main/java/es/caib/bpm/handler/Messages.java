package es.caib.bpm.handler;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages
{
	private static final String BUNDLE_NAME = "es.caib.bpm.handler.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

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
