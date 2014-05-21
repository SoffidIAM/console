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
public class WebAuthenticator13 extends WebAuthenticator {

	/* (non-Javadoc)
	 * @see java.net.Authenticator#getPasswordAuthentication()
	 */
	protected PasswordAuthentication getPasswordAuthentication() {
		return createPasswordAuthentication();
	}
		
}
