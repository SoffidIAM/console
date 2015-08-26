package es.caib.seycon.ng.test;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class TSLTest {

	public static void main (String args [] )
	{
		System.out.println ("hola");
		URL url;
		try {
			System.out.println("XXX");
			url = new URL("https://maquetador.apsl.net/");
			URLConnection c = url.openConnection();
			InputStream in = c.getInputStream();
			in.close();
			System.out.println("END");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
 }
