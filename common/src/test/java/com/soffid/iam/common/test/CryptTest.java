package com.soffid.iam.common.test;

import java.io.UnsupportedEncodingException;

import com.soffid.iam.crypt.Crypt;

import junit.framework.TestCase;

public class CryptTest extends TestCase
{

	public void testPBKDF2 () throws UnsupportedEncodingException
	{
		String s = "password";
		byte salt[] = "prueba".getBytes();
		String result = Crypt.pBKDF2Sha256(s, salt, 1000);
		System.out.println ("Hash="+result);
	}

	public void testPBKDF2b () throws UnsupportedEncodingException
	{
		String s = "password";
		byte salt[] = Crypt.genSaltBytes();
		String result = Crypt.pBKDF2Sha256(s, salt, 1000);
		System.out.println ("Hash2="+result);
	}
}
