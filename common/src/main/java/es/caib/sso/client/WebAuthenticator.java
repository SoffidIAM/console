/*
 * Created on 22/04/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package es.caib.sso.client;

import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.UnknownHostException;


/**
 * @author u07286
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WebAuthenticator extends Authenticator {

	/* (non-Javadoc)
	 * @see java.net.Authenticator#getPasswordAuthentication()
	 */
	protected PasswordAuthentication getPasswordAuthentication() {
		String host = getRequestingHost();
		InetAddress addr;
		try {
			addr = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			return null;
		}
		String port = Integer.toString(getRequestingPort());
		String schema = getSsoSchema (addr.getHostAddress(), port); 
		
		if ( "http".equalsIgnoreCase(schema) || //$NON-NLS-1$
			 "https".equalsIgnoreCase(schema)) //$NON-NLS-1$
		{
			return createPasswordAuthentication();
		}
		else
			return null;
	}
	
	protected PasswordAuthentication createPasswordAuthentication ()
	{
		return new PasswordAuthentication (getUserName(), getPassword().toCharArray());
	}
	
	protected String[] getUserAndPassword ()
	{
		return new String[] { getUserName(), getPassword() };
	}

	protected String[] getSchemeUserAndPassword (String ip, String port)
	{
		return new String[] { getSsoSchema(ip,port), getUserName(), getPassword() };
	}


	private native String getSsoSchema ( String ip, String port );
	private native String getUserName ();
	private native String getPassword ();
	
}
