package com.soffid.iam.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.security.auth.x500.X500Principal;

import org.apache.commons.collections.map.HashedMap;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.AttributeTypeAndValue;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.X509CertificateStructure;


public class CertificateParser {

	String nif;
	String fullName;
	String givenName;
	String surName;
	String issuer;
	private boolean personaJuridica;

	public CertificateParser(X509Certificate cert) throws CertificateEncodingException, IOException {
		super();
		parse (cert);
	}

	public CertificateParser(byte[] cert) throws CertificateEncodingException, IOException {
		super();
		parse (cert);
	}


	private void processName (String cn)
	{
		if ( cn != null && cn.startsWith("NOMBRE ")) //$NON-NLS-1$
		{
			int i = cn.indexOf(" - "); //$NON-NLS-1$
			if ( i > 0 && cn.substring(i).startsWith(" - NIF ")) //$NON-NLS-1$
			{
				nif = cn.substring(i + 7);
				fullName = cn.substring(7, i);
				personaJuridica = false;
			}
		}
		// Buscar el NIF en la entidad
		else if ( cn != null && cn.startsWith("ENTIDAD ")) //$NON-NLS-1$
		{
			personaJuridica = true;
			int i = cn.indexOf(" - "); //$NON-NLS-1$
			if ( i > 0 && cn.substring(i).startsWith(" - CIF ")) //$NON-NLS-1$
			{
				int j = cn.indexOf(" - ", i+8); //$NON-NLS-1$
				int k = cn.indexOf(" - NIF ", i+8); //$NON-NLS-1$
				if ( j > 0 && k > 0) {
					nif = cn.substring(i + 7, j);
					fullName = cn.substring(7, i);
				}
			}
		}
		else if (cn != null)
		{
			fullName = cn;
			// Parse del DNIE
			if (issuer.indexOf ("DNIE") >= 0) //$NON-NLS-1$
			{
				int i1 = cn.indexOf(',');
				int i2 = cn.indexOf('(');
				if (i1 > 0 && i2 > i1)
				{
					surName = cn.substring(0, i1).trim();
				}
				if (i1 > 0 && i2 > i1)
				{
					givenName = cn.substring(i1+1, i2).trim();
				}
			}
		}
	}
	
	private void parse (X509Certificate cert) throws CertificateEncodingException, IOException
	{
		byte b [] = cert.getEncoded ();
		parse(b);
	}

	/**
	 * @param cert
	 * @throws IOException
	 */
	private void parse(byte[] cert) throws IOException {
		try {
			CertificateFactory fac=CertificateFactory.getInstance("X509");
			ByteArrayInputStream in=new ByteArrayInputStream(cert);
			X509Certificate certificate=(X509Certificate)fac.generateCertificate(in);

			personaJuridica = false;
			
			X500Principal i = certificate.getIssuerX500Principal();
			HashMap<String,String> map = new HashMap<String, String>();
			for ( String s: i.getName().split(", *"))
			{
				if (s.toLowerCase().startsWith("cn="))
					issuer = s.substring(3);
			}
			
			Principal name = certificate.getSubjectDN();
			personaJuridica = false;
			for ( String s: name.getName().split(", *"))
			{
				if (s.toLowerCase().startsWith("cn="))
					processName (s.substring(3));
				if (s.toLowerCase().startsWith("sn="))
					surName = s.substring(3);
				if (s.toLowerCase().startsWith("givenname="))
					givenName = s.substring(10);
				if (s.toLowerCase().startsWith("sn="))
					nif = s.substring(3);
			}
		} catch (CertificateException e) {
			throw new IOException(e);
		}
	}

	/**
	 * @return Returns the nif.
	 */
	public String getNif() {
		return nif;
	}
	
	public String getGivenName ()
	{
		if (givenName != null)
			return givenName;
		else
		{
			String parse [] = new NameParser().parse(fullName, 3);
			return parse[2];
		}
	}

	public String getFirstSurName ()
	{
		if (surName != null)
		{
			String parse [] = new NameParser().parse(surName, 2);
			return parse[0];
		}
		else
		{
			String parse [] = new NameParser().parse(fullName, 3);
			return parse[0];
		}
	}

	public String getSecondSurName ()
	{
		if (surName != null)
		{
			String parse [] = new NameParser().parse(surName, 2);
			return parse[1];
		}
		else
		{
			String parse [] = new NameParser().parse(fullName, 3);
			return parse[1];
		}
	}
	
	public static void main (String args[])
	{
		try {
			FileInputStream in = new FileInputStream(args[0]);
			int i = in.available();
			byte b[] = new byte[i];
			for (i = 0; i < b.length; i++)
				b[i] = (byte) in.read();
			CertificateParser p = new CertificateParser (b);
			System.out.println ("NIF="+p.getNif()); //$NON-NLS-1$
			System.out.println ("GN="+p.getGivenName()); //$NON-NLS-1$
			System.out.println ("SN="+p.getFirstSurName()); //$NON-NLS-1$
			System.out.println ("SN2="+p.getSecondSurName()); //$NON-NLS-1$
		} catch (CertificateEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
