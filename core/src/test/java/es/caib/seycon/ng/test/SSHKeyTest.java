package es.caib.seycon.ng.test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.soffid.iam.service.impl.SshKeyGenerator;

import junit.framework.TestCase;

public class SSHKeyTest extends TestCase {
	public void testGenerate() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		SshKeyGenerator g = new SshKeyGenerator();
		g.generateKey();
		
		String s = g.getPrivateKeyString();
		String pub = g.getPublicKeyString("Test");
		
		System.out.println(s);
		System.out.println(pub);
	}

	public void testRead() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
		String key = "-----BEGIN RSA PRIVATE KEY-----\n"
				+ "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\n"
				+ "-----END RSA PRIVATE KEY-----\n";
		SshKeyGenerator g = new SshKeyGenerator();
		g.loadKey(key);

		String s = g.getPrivateKeyString();
		String pub = g.getPublicKeyString("Test");
		
		System.out.println(s);
		System.out.println(pub);
	}
	
	public void testRead2() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
		String key = "-----BEGIN OPENSSH PRIVATE KEY-----\n"
				+ "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\n"
				+ "-----END OPENSSH PRIVATE KEY-----";
		SshKeyGenerator g = new SshKeyGenerator();
		g.loadKey(key);

		String s = g.getPrivateKeyString();
		String pub = g.getPublicKeyString("Test");
		
		System.out.println(s);
		System.out.println(pub);
	}

	public void testGenerateAndRead() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		SshKeyGenerator g = new SshKeyGenerator();
		g.generateKey();
		
		String s = g.getPrivateKeyString();
		
		System.out.println(s);
		System.out.println(g.getPublicKeyString("Test"));

		SshKeyGenerator g2 = new SshKeyGenerator();
		g2.loadKey(s);
		
		String priv = g2.getPrivateKeyString();
		String pub = g2.getPublicKeyString("Test");
		
		System.out.println(priv);
		System.out.println(pub);
	}

}
