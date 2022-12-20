package com.soffid.iam.addon.scim.rest;

import java.util.MissingResourceException;

import com.soffid.iam.lang.MessageFactory;

public class Messages
{
	private static final String BUNDLE_NAME = "com.soffid.iam.addon.scim.rest.messages"; //$NON-NLS-1$

	private Messages() {}

	public static String getString(String key) {
		try {
			return MessageFactory.getString(BUNDLE_NAME,key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
