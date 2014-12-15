package com.soffid.iam.crypt;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.KeyParameter;

import es.caib.seycon.util.Base64;

public class Crypt
{
	static SecureRandom random = new SecureRandom();

	public static String hash (String algorithm, String text)
	{
		if (algorithm == null || algorithm.trim().length() == 0)
			return text;
		else if ("bcrypt".equalsIgnoreCase(algorithm))
		{
			String salt = BCrypt.gensalt();
			return BCrypt.hashpw(text, salt);
		}
		else if ("pBKDF2Sha256".equalsIgnoreCase(algorithm))
		{
			String salt;
			try
			{
				salt = genSalt(8);
				int iterations = 1200;
				return ""+iterations+"$"+salt+"$"+pBKDF2Sha256(text, salt, iterations);
			}
			catch (UnsupportedEncodingException e)
			{
				throw new RuntimeException(e);
			}
		}
		else if ("pBKDF2Sha1".equalsIgnoreCase(algorithm) ||
				"pBKDF2".equalsIgnoreCase(algorithm))
		{
			String salt;
			try
			{
				salt = genSalt(8);
				int iterations = 1200;
				return ""+iterations+"$"+salt+"$"+pBKDF2Sha1(text, salt, iterations);
			}
			catch (UnsupportedEncodingException e)
			{
				throw new RuntimeException(e);
			}
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

	public static String pBKDF2Sha256 (String text, String utf8Salt, int iterations ) throws UnsupportedEncodingException
	{
		return pBKDF2Sha256(text, Base64.decode(utf8Salt), iterations);
    }
	
	public static String pBKDF2Sha256 (String text, byte []salt, int iterations ) throws UnsupportedEncodingException
	{
		PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator(new SHA256Digest());
		gen.init(text.getBytes("UTF-8"), salt, iterations);
		byte[] dk = ((KeyParameter) gen.generateDerivedParameters(256)).getKey();
		return Base64.encodeBytes(dk);
    }

	public static String pBKDF2Sha1(String text, String utf8Salt, int iterations ) throws UnsupportedEncodingException
	{
		return pBKDF2Sha256(text, Base64.decode(utf8Salt), iterations);
    }
	
	public static String pBKDF2Sha1 (String text, byte []salt, int iterations ) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    KeySpec keyspec = new PBEKeySpec("password".toCharArray(), salt, 1000, 128);
	    Key key = factory.generateSecret(keyspec);
		byte[] dk = key.getEncoded();
		return Base64.encodeBytes(dk);
    }

	public static byte [] genSaltBytes ( ) throws UnsupportedEncodingException
	{
		return genSaltBytes (8);
	}

	public static byte [] genSaltBytes ( int size ) throws UnsupportedEncodingException
	{
		StringBuffer rs = new StringBuffer();
		byte rnd[] = new byte[size];

		random.nextBytes(rnd);

		return rnd;
    }
	
	public static String genSalt () throws UnsupportedEncodingException
	{
		return Base64.encodeBytes(genSaltBytes());
	}

	public static String genSalt (int size) throws UnsupportedEncodingException
	{
		return Base64.encodeBytes(genSaltBytes(size));
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
