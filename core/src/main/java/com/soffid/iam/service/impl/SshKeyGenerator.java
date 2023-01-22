package com.soffid.iam.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.crypto.util.OpenSSHPrivateKeyUtil;
import org.bouncycastle.crypto.util.OpenSSHPublicKeyUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;
import org.bouncycastle.jcajce.spec.OpenSSHPrivateKeySpec;
import org.bouncycastle.jcajce.spec.OpenSSHPublicKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import es.caib.seycon.util.Base64;

public class SshKeyGenerator {
	private int key_size;
	private AsymmetricKeyParameter privateKeyParameters;
	private AsymmetricKeyParameter publicKeyParameters;
	String pemType;

	public void generateKey(int size) throws NoSuchAlgorithmException, InvalidKeySpecException {
		generate(size);
	}
		
	public void generateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
		generate(4096);
	}

	
	public void loadKey(String s) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
		PEMParser r = new PEMParser(new StringReader(s));
		PemObject pemObject = r.readPemObject();
		if (pemObject == null)
		{
			throw new IOException ("Cannot parse PEM object");
		} 
		else if (pemObject.getType().contains("PRIVATE KEY")) {
			pemType = pemObject.getType();
			byte[] data = pemObject.getContent();
			parse(data);
		}
		else
			throw new IOException("Unsupported PEM object "+pemObject.getType());
	}
	
	private void parse(byte[] plain) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
	    privateKeyParameters = OpenSSHPrivateKeyUtil.parsePrivateKeyBlob(plain);
        AsymmetricCipherKeyPair keyPair = null;
        if (privateKeyParameters instanceof RSAPrivateCrtKeyParameters)
        {
        	pemType = "RSA PRIVATE KEY";
            publicKeyParameters = new RSAKeyParameters(false, ((RSAPrivateCrtKeyParameters) privateKeyParameters).getModulus(), 
            		((RSAPrivateCrtKeyParameters) privateKeyParameters).getPublicExponent());
        }
        else if (privateKeyParameters instanceof Ed25519PrivateKeyParameters)
        {
        	pemType = "SSH2 PRIVATE KEY";
            publicKeyParameters = ((Ed25519PrivateKeyParameters)privateKeyParameters).generatePublicKey();
        }
        else if (privateKeyParameters instanceof ECPrivateKeyParameters)
        {
        	pemType = "EC PRIVATE KEY";
            final ECPrivateKeyParameters ec = (ECPrivateKeyParameters) privateKeyParameters;
			ECPoint q = ec.getParameters().getG().multiply(
            		ec.getD());
            publicKeyParameters = new ECPublicKeyParameters(q, ec.getParameters());
        }
        else
            throw new RuntimeException("The key type "+privateKeyParameters.getClass()+" is not supported.");
	}


	void generate(int key_size) throws NoSuchAlgorithmException, InvalidKeySpecException {
		pemType = "RSA PRIVATE KEY";
		this.key_size = key_size;
		
		RSAKeyPairGenerator g = new RSAKeyPairGenerator();
		g.init(new RSAKeyGenerationParameters(BigInteger.valueOf(0x10001),
				new SecureRandom(), key_size, 
				PrimeCertaintyCalculator.getDefaultCertainty(key_size)));
		AsymmetricCipherKeyPair pair = g.generateKeyPair();
		privateKeyParameters = pair.getPrivate();
		publicKeyParameters = pair.getPublic();
	}


	private void parseKey(RSAPublicKey pubKey, RSAPrivateKey prvKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		RSAPrivateCrtKey cert = (RSAPrivateCrtKey) prvKey;
		if (pubKey == null) {
			RSAPublicKeySpec publicKeySpec = new java.security.spec.RSAPublicKeySpec(cert.getModulus(), cert.getPublicExponent());

		    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		    pubKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
		}

	}

	public String getPrivateKeyString() throws IOException {
		byte[] plain = OpenSSHPrivateKeyUtil.encodePrivateKey(privateKeyParameters);		
		String prv = es.caib.seycon.util.Base64.encodeBytes(plain, 0, plain.length,
				es.caib.seycon.util.Base64.DONT_BREAK_LINES);

		StringBuffer s = new StringBuffer();
		
		s.append("-----BEGIN "+pemType+"-----\n");
		int i = 0;
		while (i < prv.length()) {
			if (i + 64 < prv.length()) {
				s.append(prv.substring(i, i + 64));
			} else {
				s.append(prv.substring(i));
			}
			s.append("\n");
			i += 64;
		}
		s.append("-----END "+pemType+"-----\n");
		return s.toString();
	}

	public String getPublicKeyString(String comment) throws IOException {
		byte[] pubblob = OpenSSHPublicKeyUtil.encodePublicKey(publicKeyParameters);
		
		OpenSSHPublicKeySpec spec = new OpenSSHPublicKeySpec(pubblob);
		
		return spec.getType()+" "+Base64.encodeBytes(pubblob, Base64.DONT_BREAK_LINES) + " " + comment;
	}

}
