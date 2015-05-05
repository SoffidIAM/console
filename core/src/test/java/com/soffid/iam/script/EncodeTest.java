package com.soffid.iam.script;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

import junit.framework.TestCase;

public class EncodeTest extends TestCase {

	public void testEncoding () throws UnsupportedEncodingException
	{
		String mail = "\"John Smíth ñç\" <jsmith@nowhere.com>";
		String encoded = MimeUtility.encodeText(mail);
		System.out.println (encoded);

		String encoded2 = MimeUtility.encodeWord(mail);
		System.out.println (encoded2);
	}

	public void testEncoding2 () throws UnsupportedEncodingException
	{
		String mail = "John Smíth <jsmith@nowhere.com>";
		String encoded = MimeUtility.encodeText(mail);
		System.out.println (encoded);

		String encoded2 = MimeUtility.encodeWord(mail);
		System.out.println (encoded2);
	}

	public void testEncoding3 () throws UnsupportedEncodingException
	{
		String encoded = new InternetAddress("jsmith@nowhere.com", "John Smith ñ ç á é").toString();
		System.out.println (encoded);
	}
}
