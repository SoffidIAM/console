package com.soffid.iam.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

public class SshKeyGenerator {
	private int key_size;

	public void generateKey() throws NoSuchAlgorithmException {
		generate(4096);
	}

	
	public void loadKey(String s) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
		PemReader r = new PemReader(new StringReader(s));
		PemObject pemObject = r.readPemObject();
		if (pemObject == null)
		{
			throw new IOException ("Cannot parse PEM object");
		} 
		else if (pemObject.getType().equals("RSA PRIVATE KEY")) {
			byte[] data = pemObject.getContent();
			parse(data);
		}
		else
			throw new IOException("Unsupported PEM object "+pemObject.getType());
	}
	
	private void parse(byte[] plain) throws IOException {
        int index=0;
        int length=0;

        /*
          Key must be in the following ASN.1 DER encoding,
          RSAPrivateKey ::= SEQUENCE {
            version           Version,
            modulus           INTEGER,  -- n
            publicExponent    INTEGER,  -- e
            privateExponent   INTEGER,  -- d
            prime1            INTEGER,  -- p
            prime2            INTEGER,  -- q
            exponent1         INTEGER,  -- d mod (p-1)
            exponent2         INTEGER,  -- d mod (q-1)
            coefficient       INTEGER,  -- (inverse of q) mod p
            otherPrimeInfos   OtherPrimeInfos OPTIONAL
          }
        */

        index++; // SEQUENCE
        length=plain[index++]&0xff;
        if((length&0x80)!=0){
          int foo=length&0x7f; length=0;
          while(foo-->0){ length=(length<<8)+(plain[index++]&0xff); }
        }

        if(plain[index]!=0x02) throw new IOException("Error parsing RSA Key");
        index++; // INTEGER
        length=plain[index++]&0xff;
        if((length&0x80)!=0){
          int foo=length&0x7f; length=0;
          while(foo-->0){ length=(length<<8)+(plain[index++]&0xff); }
        }
        index+=length;

        index++;
        length=plain[index++]&0xff;
        if((length&0x80)!=0){
          int foo=length&0x7f; length=0;
          while(foo-->0){ length=(length<<8)+(plain[index++]&0xff); }
        }
        n_array=new byte[length];
        System.arraycopy(plain, index, n_array, 0, length);
        index+=length;

        index++;
        length=plain[index++]&0xff;
        if((length&0x80)!=0){
          int foo=length&0x7f; length=0;
          while(foo-->0){ length=(length<<8)+(plain[index++]&0xff); }
        }
        pub_array=new byte[length];
        System.arraycopy(plain, index, pub_array, 0, length);
        index+=length;

        index++;
        length=plain[index++]&0xff;
        if((length&0x80)!=0){
          int foo=length&0x7f; length=0;
          while(foo-->0){ length=(length<<8)+(plain[index++]&0xff); }
        }
        prv_array=new byte[length];
        System.arraycopy(plain, index, prv_array, 0, length);
        index+=length;

        index++;
        length=plain[index++]&0xff;
        if((length&0x80)!=0){
          int foo=length&0x7f; length=0;
          while(foo-->0){ length=(length<<8)+(plain[index++]&0xff); }
        }
        p_array=new byte[length];
        System.arraycopy(plain, index, p_array, 0, length);
        index+=length;

        index++;
        length=plain[index++]&0xff;
        if((length&0x80)!=0){
          int foo=length&0x7f; length=0;
          while(foo-->0){ length=(length<<8)+(plain[index++]&0xff); }
        }
        q_array=new byte[length];
        System.arraycopy(plain, index, q_array, 0, length);
        index+=length;

        index++;
        length=plain[index++]&0xff;
        if((length&0x80)!=0){
          int foo=length&0x7f; length=0;
          while(foo-->0){ length=(length<<8)+(plain[index++]&0xff); }
        }
        ep_array=new byte[length];
        System.arraycopy(plain, index, ep_array, 0, length);
        index+=length;

        index++;
        length=plain[index++]&0xff;
        if((length&0x80)!=0){
          int foo=length&0x7f; length=0;
          while(foo-->0){ length=(length<<8)+(plain[index++]&0xff); }
        }
        eq_array=new byte[length];
        System.arraycopy(plain, index, eq_array, 0, length);
        index+=length;

        index++;
        length=plain[index++]&0xff;
        if((length&0x80)!=0){
          int foo=length&0x7f; length=0;
          while(foo-->0){ length=(length<<8)+(plain[index++]&0xff); }
        }
        c_array=new byte[length];
        System.arraycopy(plain, index, c_array, 0, length);
        index+=length;

        if(n_array!=null){
          key_size = (new java.math.BigInteger(n_array)).bitLength();
        }

	}


	void generate(int key_size) throws NoSuchAlgorithmException {
		this.key_size = key_size;
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(key_size, new SecureRandom());
		KeyPair pair = keyGen.generateKeyPair();

		RSAPublicKey pubKey = (RSAPublicKey) pair.getPublic();
		RSAPrivateKey prvKey = (RSAPrivateKey) pair.getPrivate();
		parseKey(pubKey, prvKey);
	}


	private void parseKey(RSAPublicKey pubKey, RSAPrivateKey prvKey) {
		RSAPrivateCrtKey cert = (RSAPrivateCrtKey) prvKey;

		pub_array = pubKey.getPublicExponent().toByteArray();
		prv_array = prvKey.getPrivateExponent().toByteArray();
		n_array = prvKey.getModulus().toByteArray();

		p_array = cert.getCrtCoefficient().toByteArray();
		q_array = cert.getPrimeQ().toByteArray();
		ep_array = cert.getPrimeExponentP().toByteArray();
		eq_array = cert.getPrimeExponentQ().toByteArray();
		c_array = cert.getCrtCoefficient().toByteArray();
	}

	private byte[] pub_array;
	private byte[] prv_array;
	private byte[] n_array;
	private byte[] p_array;
	private byte[] q_array;
	private byte[] ep_array;
	private byte[] eq_array;
	private byte[] c_array;

	byte[] getPrivateKey() {
		int content = 1 + countLength(1) + 1 + // INTEGER
				1 + countLength(n_array.length) + n_array.length + // INTEGER N
				1 + countLength(pub_array.length) + pub_array.length + // INTEGER pub
				1 + countLength(prv_array.length) + prv_array.length + // INTEGER prv
				1 + countLength(p_array.length) + p_array.length + // INTEGER p
				1 + countLength(q_array.length) + q_array.length + // INTEGER q
				1 + countLength(ep_array.length) + ep_array.length + // INTEGER ep
				1 + countLength(eq_array.length) + eq_array.length + // INTEGER eq
				1 + countLength(c_array.length) + c_array.length; // INTEGER c

		int total = 1 + countLength(content) + content; // SEQUENCE

		byte[] plain = new byte[total];
		int index = 0;
		index = writeSEQUENCE(plain, index, content);
		index = writeINTEGER(plain, index, new byte[1]); // 0
		index = writeINTEGER(plain, index, n_array);
		index = writeINTEGER(plain, index, pub_array);
		index = writeINTEGER(plain, index, prv_array);
		index = writeINTEGER(plain, index, p_array);
		index = writeINTEGER(plain, index, q_array);
		index = writeINTEGER(plain, index, ep_array);
		index = writeINTEGER(plain, index, eq_array);
		index = writeINTEGER(plain, index, c_array);
		return plain;
	}

	int writeSEQUENCE(byte[] buf, int index, int len) {
		buf[index++] = 0x30;
		index = writeLength(buf, index, len);
		return index;
	}

	int writeINTEGER(byte[] buf, int index, byte[] data) {
		buf[index++] = 0x02;
		index = writeLength(buf, index, data.length);
		System.arraycopy(data, 0, buf, index, data.length);
		index += data.length;
		return index;
	}

	int writeOCTETSTRING(byte[] buf, int index, byte[] data) {
		buf[index++] = 0x04;
		index = writeLength(buf, index, data.length);
		System.arraycopy(data, 0, buf, index, data.length);
		index += data.length;
		return index;
	}

	int writeDATA(byte[] buf, byte n, int index, byte[] data) {
		buf[index++] = n;
		index = writeLength(buf, index, data.length);
		System.arraycopy(data, 0, buf, index, data.length);
		index += data.length;
		return index;
	}

	int writeLength(byte[] data, int index, int len) {
		int i = countLength(len) - 1;
		if (i == 0) {
			data[index++] = (byte) len;
			return index;
		}
		data[index++] = (byte) (0x80 | i);
		int j = index + i;
		while (i > 0) {
			data[index + i - 1] = (byte) (len & 0xff);
			len >>>= 8;
			i--;
		}
		return j;
	}

	int countLength(int len) {
		int i = 1;
		if (len <= 0x7f)
			return i;
		while (len > 0) {
			len >>>= 8;
			i++;
		}
		return i;
	}

	public String getPrivateKeyString() {
		byte[] plain = getPrivateKey();
		byte[][] _iv = new byte[1][];
		byte[] encoded = plain;
		byte[] iv = _iv[0];
		String prv = es.caib.seycon.util.Base64.encodeBytes(encoded, 0, encoded.length,
				es.caib.seycon.util.Base64.DONT_BREAK_LINES);

		StringBuffer s = new StringBuffer();

		s.append("-----BEGIN RSA PRIVATE KEY-----\n");
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
		s.append("-----END RSA PRIVATE KEY-----\n");
		return s.toString();
	}

	public String getPublicKeyString(String comment) {
		byte[] pubblob = getPublicKeyBlob();
		String pub = es.caib.seycon.util.Base64.encodeBytes(pubblob, es.caib.seycon.util.Base64.DONT_BREAK_LINES);
		return "ssh-rsa " + pub + " " + comment;
	}

	private byte[] getPublicKeyBlob() {
		byte[] prefix = "ssh-rsa".getBytes(StandardCharsets.UTF_8);
		byte[] data = new byte[prefix.length + pub_array.length + n_array.length + 12] ;
		
		putInt (data, 0, prefix.length);
		System.arraycopy(prefix, 0, data, 4, prefix.length);
		
		putInt (data, 4+prefix.length, pub_array.length);
		System.arraycopy(pub_array, 0, data, 8+prefix.length, pub_array.length);
		
		putInt (data, 8+prefix.length + pub_array.length, n_array.length);
		System.arraycopy(n_array, 0, data, 12+prefix.length + pub_array.length, n_array.length);
		
		return data;
	}

	private void putInt(byte[] data, int i, int val) {
	    data[i+0]=(byte)(val >>> 24);
	    data[i+1]=(byte)(val >>> 16);
	    data[i+2]=(byte)(val >>> 8);
	    data[i+3]=(byte)(val);
	}

}
