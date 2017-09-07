// Copyright (c) 2000 Govern  de les Illes Balears
package es.caib.seycon.ng.comu;

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
public class Password extends com.soffid.iam.api.Password
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Password() {
    }

    public Password(String s) {
    	super (s);
    }
    
    public static Password decode (String s)
    {
    	return new Password ( com.soffid.iam.api.Password.decode(s).getPassword());
    }

	public static Password toPassword(com.soffid.iam.api.Password password2)
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
