/*
 * Created on 22/04/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package es.caib.sso.client;

import java.net.Authenticator;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author u07286
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SingleSignOnFactory {
	public static void register ()
	{
		AccessController.doPrivileged(
				new PrivilegedAction () {
					/* Carga el autenticador WEB
					 * @see java.security.PrivilegedAction#run()
					 * @return null
					 */
					public Object run() {
						System.loadLibrary("javasso"); //$NON-NLS-1$
						Class authenticator;
						try {
							if ( System.getProperty("java.vm.version").startsWith("1.3")) //$NON-NLS-1$ //$NON-NLS-2$
								authenticator = Class.forName("es.caib.sso.client.WebAuthenticator13"); //$NON-NLS-1$
							else
								authenticator = Class.forName("es.caib.sso.client.WebAuthenticator"); //$NON-NLS-1$
							Authenticator.setDefault((Authenticator)authenticator.newInstance());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}
				}
			);
	}
	
	public static void unregister ()
	{
		AccessController.doPrivileged(
			new PrivilegedAction () {
				/* Carga el autenticador WEB
				 * @see java.security.PrivilegedAction#run()
				 * @return null
				 */
				public Object run() {
					Authenticator.setDefault(null);
					return null;
				}
			}
		);
	}

}
