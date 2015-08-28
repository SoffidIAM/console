package com.soffid.iam.service.impl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.x509.X509CertificateStructure;
import org.bouncycastle.asn1.x509.X509Name;


public class CertificateParser {

	String nif;
	String fullName;
	String givenName;
	String surName;
	String issuer;
	private boolean personaJuridica;

	private static DERObjectIdentifier NIFOID = new DERObjectIdentifier ("1.3.6.1.4.1.18838.1.1"); //$NON-NLS-1$

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
		ASN1InputStream asn1is = new ASN1InputStream ( cert );
		DERObject obj = asn1is.readObject ();
		X509CertificateStructure certificate = new X509CertificateStructure ((ASN1Sequence) obj);
		// Procesar el SUBJECT NAME
		X509Name certIssuer = certificate.getIssuer();
		issuer = ""; //$NON-NLS-1$
		java.util.Vector v = certIssuer.getOIDs ();
		java.util.Vector value = certIssuer.getValues ();
		for ( int i = 0; i < v.size(); i++)
		{
			if (v.get(i).equals (X509Name.CN))
			{
				issuer = value.get(i).toString ();
			}
		}
		
		X509Name name = certificate.getSubject();
		personaJuridica = false;
		v = name.getOIDs ();
		value = name.getValues ();
		for ( int i = 0; i < v.size(); i++)
		{
			if (v.get(i).equals (X509Name.CN))
				processName (value.get(i).toString());
			if (v.get(i).equals (X509Name.SURNAME) && surName == null)
				surName = value.get(i).toString();
			if (v.get(i).equals (X509Name.GIVENNAME) && givenName == null)
				givenName = value.get(i).toString();
			if (v.get(i).equals (X509Name.SN))
			{
				nif = value.get(i).toString();
			}
			if (v.get(i).equals (NIFOID))
			{
				personaJuridica = true;
			}
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
