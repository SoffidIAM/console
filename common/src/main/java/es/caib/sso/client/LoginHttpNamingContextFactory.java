package es.caib.sso.client;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.security.auth.login.LoginException;
//import javax.security.auth.callback.CallbackHandler;

/**
 * Clase principal
 */

public class LoginHttpNamingContextFactory 
  extends HttpNamingContextFactory

{
  static boolean initialized = false;
  /**
   * Obtener contexto
   */
  public Context getInitialContext(Hashtable hash) throws NamingException
  {
  	JBossAuthenticator auth = new JBossAuthenticator();
    try {
      	if (hash.get("es.caib.seycon.autologin") != null) //$NON-NLS-1$
      		auth.doLogin();
      	else
      		auth.doLogin((String)hash.get("java.naming.security.principal"), //$NON-NLS-1$
                         (String)hash.get("java.naming.security.credentials")); //$NON-NLS-1$
    } catch (LoginException e) {
      throw new NamingException (e.toString ());
    }
    return super.getInitialContext (hash);
  }
}