package es.caib.bpm.nas.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.twmacinta.util.MD5;

import es.caib.bpm.nas.NASManager;

public class CryptoUtil {

	private static final char[] HEX_CHARS = {'0', '1', '2', '3',
		   '4', '5', '6', '7',
		   '8', '9', 'a', 'b',
		   'c', 'd', 'e', 'f',};

	/**
	 * Aplica el algoritmo de MD5 al archivo y genera el hash.
	 * 
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static byte[] hashMD5(File file) throws IOException {
		return MD5.getHash(file);
	}
	
	/**
	 * Aplica el algoritmo de SHA1 al archivo y genera el hash.
	 * 
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] hashSHA1(File file) throws IOException, NoSuchAlgorithmException {
		MessageDigest digester= null;
		
        FileInputStream streamLectura= null;
        int leidos= 0;
        byte[] buffer= new byte[2048];
		byte[] resultado= null;
		
		try
		{
			digester= MessageDigest.getInstance("SHA-1");
			streamLectura= new FileInputStream(file);
			
	        while((leidos= streamLectura.read(buffer))!= -1)
	        {
	            digester.update(buffer, 0, leidos);
	        }
	        
	        resultado= digester.digest();
		}
		finally
		{
			streamLectura.close();
		}
		
		return resultado;
	}
	
	/**
	* Turns array of bytes into string representing each byte as
	* unsigned hex number.
	* 
	* @param hash	Array of bytes to convert to hex-string
	* @return	Generated hex string
	*/
	public static String asHex (byte hash[]) {
		char buf[] = new char[hash.length * 2];
		
		for (int i = 0, x = 0; i < hash.length; i++) {
			buf[x++] = HEX_CHARS[(hash[i] >>> 4) & 0xf];
			buf[x++] = HEX_CHARS[hash[i] & 0xf];
		}
			return new String(buf);
	}
	
	private static Logger log= Logger.getLogger(CryptoUtil.class);
}
