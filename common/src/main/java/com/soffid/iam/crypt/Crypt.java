package com.soffid.iam.crypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import es.caib.seycon.util.Base64;

public class Crypt
{
	public static String hash (String algorithm, String text)
	{
		if (algorithm == null || algorithm.trim().length() == 0)
			return text;
		else if ("bcrypt".equalsIgnoreCase(algorithm))
		{
			String salt = BCrypt.gensalt();
			return BCrypt.hashpw(text, salt);
		}
		else
		{
			try {
				MessageDigest digest = MessageDigest.getInstance(algorithm.toUpperCase());
				byte b [] = digest.digest(text.getBytes("UTF-8"));
				return Base64.encodeBytes(b);
			} catch (java.security.NoSuchAlgorithmException e) {
				throw new IllegalArgumentException("Unknown algorithm "+algorithm);
			}
			catch (UnsupportedEncodingException e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	public static boolean verify (String algorithm, String text, String hash)
	{
		if (algorithm == null || algorithm.trim().length() == 0)
			return text == null ? hash == null: text.equals(hash);
		else if ("bcrypt".equalsIgnoreCase(algorithm))
		{
			return BCrypt.checkpw(text, hash);
		}
		else
		{
			try {
				MessageDigest digest = MessageDigest.getInstance(algorithm.toUpperCase());
				byte b [] = digest.digest(text.getBytes("UTF-8"));
				String hash2 = Base64.encodeBytes(b);
				return hash2.equals (hash);
			} catch (java.security.NoSuchAlgorithmException e) {
				throw new IllegalArgumentException("Unknown algorithm "+algorithm);
			}
			catch (UnsupportedEncodingException e)
			{
				throw new RuntimeException(e);
			}
		}
	}
}
