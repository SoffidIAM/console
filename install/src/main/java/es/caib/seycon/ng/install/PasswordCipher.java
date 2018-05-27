package es.caib.seycon.ng.install;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.install4j.runtime.util.Base64;

public class PasswordCipher {
	private static final byte[] _3desData = { (byte) 0x32, (byte) 0x0a, (byte) 0x9b, (byte) 0x04, (byte) 0xb4,
			(byte) 0x04, (byte) 0xc9, (byte) 0xf7, (byte) 0x33, (byte) 0xc9, (byte) 0x9d, (byte) 0x1f, (byte) 0xb8,
			(byte) 0xd0, (byte) 0xda, (byte) 0xb7, (byte) 0xd2, (byte) 0xb4, (byte) 0x02, (byte) 0x62, (byte) 0xdc,
			(byte) 0x42, (byte) 0x4c, (byte) 0x9c };

	private static final SecretKeySpec KEY = new SecretKeySpec(_3desData, "DESede");

	/**
	 * The name of the transformation defines Triple-DES encryption
	 */
	private static final String TRANSFORMATION = "DESede";

	/**
	 * @throws RuntimeException
	 *             in any case of error.
	 * @see org.apache.openejb.cipher.PasswordCipher#encrypt(String)
	 */
	public static String encrypt(final String plainPassword) {
		if (null == plainPassword || plainPassword.length() == 0) {
			throw new IllegalArgumentException("plainPassword cannot be null nor empty.");
		}

		final byte[] plaintext = plainPassword.getBytes();
		try {
			// Get a 3DES Cipher object
			final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			// Set it into encryption mode
			cipher.init(Cipher.ENCRYPT_MODE, KEY);

			// Encrypt data
			final byte[] cipherText = cipher.doFinal(plaintext);
			return Base64.encode(cipherText);

		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}
