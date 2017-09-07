// Copyright (c) 2000 Govern  de les Illes Balears
package com.soffid.iam.api;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * Clase que permite serializar de forma segura una contraseña. Utiliza un
 * algoritmo de encriptación bidireccional
 * <P>
 * 
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.1 $
 */

// $Log: Password.java,v $
// Revision 1.1.2.1  2012-09-18 06:11:59  u07286
// Moure class Password
//
// Revision 1.1.2.1  2012-05-16 10:33:38  u07286
// Reestructuració de paquets seycon antics
//
// Revision 1.6  2011-04-05 12:02:32  u07286
// Generador automàtico de hash
//
// Revision 1.5 2010-03-15 10:23:31 u07286
// Movido a tag HEAD
//
// Revision 1.2.2.2 2009-06-16 11:23:01 u07286
// Merge a seycon-3.0.15
//
// Revision 1.2.2.1 2009-03-23 07:52:00 u89559
// *** empty log message ***
//
// Revision 1.3 2009-02-16 08:08:25 u89559
// *** empty log message ***
//
// Revision 1.2 2008-03-13 08:38:34 u07286
// Creado tipo de agente "local"
// Creado agente JBPM
// Creado entorno de test
//
// Revision 1.1 2007-09-06 12:51:10 u89559
// [T252]
//
// Revision 1.3 2004-03-15 12:08:04 u07286
// Conversion UTF-8
//
// Revision 1.2 2004/03/15 11:57:48 u07286
// Agregada documentacion JavaDoc
//
@JsonDeserialize(using=PasswordDeserializer.class)
@JsonSerialize(using=PasswordSerializer.class)
public class Password extends Object implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -311519020977094460L;
    /** Contraseña almacenada en formato cifrado */
    protected String password;

    /**
     * Constructor
     */
    public Password() {
        password = null;
    }

    /**
     * Constructor a partir de la contraseña en claro
     * 
     * @param s
     *            contraseña en claro
     */
    public Password(String s) {
        if (s == null)
            password = null;
        else
            password = crypt(s);
    }

    /**
     * Serializar
     * 
     * @return contraseña cifrada
     */
    public String toString() {
        return password;
    }

    /**
     * Des-serializar
     * 
     * @param contraseña
     *            cifrada
     * @return objeto con contraseña cifrada en su interior
     */
    static public Password decode(String s) {
        Password p = new Password(null);
        p.password = s;
        return p;
    }

    /** carácteres válidos en base 64 */
    static private String base64Array = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" //$NON-NLS-1$
            + "abcdefghijklmnopqrstuvwxyz" + "0123456789+/"; //$NON-NLS-1$ //$NON-NLS-2$

    /** Convierte un array de caracteres a base 64 */
    public static String toBase64(byte source[]) {
        int i;
        int j = 0;
        int len = source.length;
        String result = ""; //$NON-NLS-1$
        for (i = 0; i < len; i += 3) {
            int c1, c2, c3;
            int index;
            c1 = source[i];
            if (i + 1 < len)
                c2 = source[i + 1];
            else
                c2 = 0;
            if (i + 2 < len)
                c3 = source[i + 2];
            else
                c3 = 0;
            if (c1 < 0)
                c1 = c1 + 256;
            if (c2 < 0)
                c2 = c2 + 256;
            if (c3 < 0)
                c3 = c3 + 256;
            index = (c1 & 0xfc) >> 2;
            result = result + base64Array.charAt(index);
            index = ((c1 & 0x03) << 4) | ((c2 & 0xf0) >> 4);
            result = result + base64Array.charAt(index);
            if (i + 1 >= len)
                result = result + "="; //$NON-NLS-1$
            else {
                index = ((c2 & 0x0f) << 2) | ((c3 & 0xc0) >> 6);
                result = result + base64Array.charAt(index);
            }
            if (i + 2 >= len)
                result = result + '=';
            else {
                index = (c3 & 0x3f);
                result = result + base64Array.charAt(index);
            }
        }
        return result;
    }

    /** Genera un array de caracteres a partir de su codificación base 64 */
    public static char[] fromBase64(String source) {
        int i;
        int j = 0;
        int len = source.length();
        int len2 = (len / 4) * 3;
        if (source.endsWith("==")) //$NON-NLS-1$
            len2 = len2 - 2;
        else if (source.endsWith("=")) //$NON-NLS-1$
            len2 = len2 - 1;
        char result[] = new char[len2];
        for (i = 0; i < len; i += 4) {
            char c1, c2, c3, c4;
            int c1d, c2d, c3d, c4d;
            c1 = source.charAt(i);
            c2 = source.charAt(i + 1);
            c3 = source.charAt(i + 2);
            c4 = source.charAt(i + 3);
            c1d = base64Array.indexOf(c1);
            c2d = base64Array.indexOf(c2);
            c3d = base64Array.indexOf(c3);
            c4d = base64Array.indexOf(c4);
            result[j++] = (char) ((c1d << 2) | ((c2d & 0x30) >> 4));
            if (c3 != '=') {
                result[j++] = (char) (((c2d & 0x0f) << 4) | ((c3d & 0x3c) >> 2));
                if (c4 != '=') {
                    result[j++] = (char) (((c3d & 0x03) << 6) | c4d);
                }
            }
        }
        return result;
    }

    /**
     * Obtener la contraseña sin cifrar
     * 
     * @return contraseña en claro
     */
    public String getPassword() {
        if (password == null)
            return null;
        else
            return uncrypt(password);
    }

    private String crypt(String source) {
        char contents[] = source.toCharArray();
        byte result[] = new byte[contents.length + 2];
        int b1 = (int) java.lang.Math.floor(256.0 * java.lang.Math.random());
        int b2 = (int) java.lang.Math.floor(256.0 * java.lang.Math.random());
        result[0] = (byte) b1;
        result[1] = (byte) b2;
        int i;
        // System.out.println ("crypt");
        for (i = 0; i < contents.length; i++) {
            int c = contents[i];
            // System.out.print ("b1 = ");
            // System.out.print (b1);
            // System.out.print (" b2 = ");
            // System.out.print (b2);
            // System.out.print (" c = ");
            // System.out.print (c);
            c = ((c ^ b1) + b2);
            // System.out.print ("->");
            // System.out.println (c);
            result[i + 2] = (byte) c;
            b1 = (b1 + b2) % 256;
            b2 = b2 ^ c;
        }
        return toBase64(result);
    }

    private String uncrypt(String source) {
        // System.out.println ("uncrypt");
        char contents[] = fromBase64(source);
        char result[] = new char[contents.length - 2];
        int b1 = contents[0];
        int b2 = contents[1];
        int i;
        for (i = 0; i < result.length; i++) {
            int c = contents[i + 2];
            // System.out.print ("b1 = ");
            // System.out.print (b1);
            // System.out.print (" b2 = ");
            // System.out.print (b2);
            // System.out.print (" c = ");
            // System.out.print (c);
            while (b2 > c)
                c = c + 256;
            c = c - b2;
            c = (c ^ b1);
            // System.out.print ("->");
            // System.out.println (c);
            result[i] = (char) c;
            b1 = (b1 + b2) % 256;
            b2 = (b2 ^ (int) contents[i + 2]);
        }
        return new String(result);
    }

    public static void main(String args[]) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        String s = args[0];
        es.caib.seycon.ng.comu.Password p = new es.caib.seycon.ng.comu.Password(s);
        java.lang.System.out.println("Coded  =" + p.toString()); //$NON-NLS-1$
        java.lang.System.out.println("Decoded=" + p.getPassword()); //$NON-NLS-1$
        java.lang.System.out.println("Base 64=" + Password.toBase64(s.getBytes("UTF-8"))); //$NON-NLS-1$ //$NON-NLS-2$
        MessageDigest digest = MessageDigest.getInstance("SHA-1"); //$NON-NLS-1$
        byte bytes[] = digest.digest(s.getBytes());
        java.lang.System.out.println("HASH = " + Password.toBase64(bytes)); //$NON-NLS-1$

    }

    private static MessageDigest digest;

    public String getHash() {
        try {
            if (digest == null) {
                digest = MessageDigest.getInstance("SHA-1"); //$NON-NLS-1$
            }
            synchronized (digest) {
                byte bytes[] = digest.digest(getPassword().getBytes());
                return toBase64(bytes);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
        	java.lang.System.err
                    .println("Unable to use SHA-1 encryption algorithm. Password cache disabled."); //$NON-NLS-1$
            return "**??**"; //$NON-NLS-1$
        }
    }

	public static Password toPassword(es.caib.seycon.ng.comu.Password password2)
	{
		if (password2 == null)
			return null;
		Password p = new Password ();
		p.password = password2.toString();
		return p;
	}
}


class PasswordSerializer extends JsonSerializer<Password>
{

	@Override
	public void serialize(Password password, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException
	{
		generator.writeString(password.getPassword());
	}

}

class PasswordDeserializer extends JsonDeserializer<Password>
{

	@Override
	public Password deserialize(JsonParser parser, DeserializationContext ctx)
			throws IOException, JsonProcessingException
	{
		String s = parser.getText();
		return new Password(s);
	}

}
