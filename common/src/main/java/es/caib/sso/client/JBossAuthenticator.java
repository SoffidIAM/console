/*
 * Created on 07/09/2005
 *
 */
package es.caib.sso.client;

import java.io.IOException;
import java.util.HashMap;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * @author u07286
 *
 */
public class JBossAuthenticator {
	private static boolean initialized = false;
	/**
	 * 
	 */
	public JBossAuthenticator() {
		super();
	}

	public void doLogin () throws LoginException
	{
		WebAuthenticator auth = new WebAuthenticator ();
		String data [] = auth.getUserAndPassword();
		doLogin (data[0], data[1]);
	}

	public void doLogin (String user, String password) throws LoginException
	{
	    if ( ! initialized )
	    {
	      ClientLoginConfiguration config = new ClientLoginConfiguration ();
	      Configuration.setConfiguration(config);
	      initialized = true;
	    }
        LoginContext lc = new LoginContext ("other",  //$NON-NLS-1$
                             new MyCallbackHandler(user ,password));
        lc.login ();
	}
}

/**
 * @author u07286
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
class ClientLoginConfiguration extends Configuration {
  public AppConfigurationEntry[] getAppConfigurationEntry(String p0)
  {
    AppConfigurationEntry app[] = new AppConfigurationEntry [ 1 ];
    app [ 0 ] = new AppConfigurationEntry (
        "org.jboss.security.ClientLoginModule", //$NON-NLS-1$
        AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
        new HashMap()
       );
    return app;
  }

  public void refresh()
  {
  }
  
}
/**
 * Callback para insertar usuario y contraseña
 */
class MyCallbackHandler extends Object implements CallbackHandler
{
  String user;
  String password;

  MyCallbackHandler (String user , String password) 
  {
    this.user = user;
    this.password = password;
  }
  
  public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
  {
     for (int i = 0; i < callbacks.length; i++) {
        if (callbacks[i] instanceof TextOutputCallback) {
          // display the message according to the specified type
          TextOutputCallback toc = (TextOutputCallback)callbacks[i];
          switch (toc.getMessageType()) {
          case TextOutputCallback.INFORMATION:
              System.out.println(toc.getMessage());
              break;
          case TextOutputCallback.ERROR:
              System.out.println(String.format(Messages.getString("JBossAuthenticator.ErrorMessage"), toc.getMessage()));  //$NON-NLS-1$
              break;
          case TextOutputCallback.WARNING:
              System.out.println(String.format(Messages.getString("JBossAuthenticator.WarningMessage"), toc.getMessage())); //$NON-NLS-1$
              break;
          default:
              throw new IOException(String.format(Messages.getString("JBossAuthenticator.UnsupportedMessageType"), toc.getMessageType())); //$NON-NLS-1$
          }

        } else if (callbacks[i] instanceof NameCallback) {
          // prompt the user for a username
          NameCallback nc = (NameCallback)callbacks[i];
          nc.setName(user);
        } else if (callbacks[i] instanceof PasswordCallback) {
          PasswordCallback pc = (PasswordCallback)callbacks[i];
          pc.setPassword(password.toCharArray());
        } else {
          throw new UnsupportedCallbackException
            (callbacks[i], Messages.getString("JBossAuthenticator.UnrecognizedCallback"));  //$NON-NLS-1$
        }
     }
   }

  
}


