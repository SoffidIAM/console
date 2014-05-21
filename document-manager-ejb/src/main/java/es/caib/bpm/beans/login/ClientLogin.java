package es.caib.bpm.beans.login;

import java.io.IOException;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class ClientLogin {
	
	/**
	 * Construye el login de cliente.
	 * 
	 * @param username
	 * @param password
	 */
	public ClientLogin(String username, String password) {
		this.username= username;
		this.password= password;
	}
	
	/**
	 * Performs the JAAS client side login and returns the subject
	 * @return
	 * @throws LoginException
	 */
	public Subject login() throws LoginException {
		Subject ret = null;
		
		lc = new LoginContext(ClientLogin.SEYCON_DOMAIN, new ClientCallBackHandler(this.username, this.password));
		
		lc.login();
		ret = lc.getSubject();

	    return ret;
	}

	/**
	 * A sample implementation of the CallbackHandler Interface that handles
	 * username and password callbacks
	 */
	private class ClientCallBackHandler implements CallbackHandler {
	  private String user,pass;
	  
	  /**
	   *  Constructor that takes username and password
	   */
	  public ClientCallBackHandler(String user, String pass) {
		this.user = user;
		this.pass = pass;
	  }
	  
	  
	  /**
	   * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback[])
	   */
	  public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		int len = callbacks.length;
		Callback cb;
		
		for(int i=0;i<len;i++) {
		  cb = callbacks[i];
		  if(cb instanceof NameCallback) {
		    NameCallback ncb = (NameCallback)cb;
		    ncb.setName(user);
		  } else 
		  if (cb instanceof PasswordCallback) {
		    PasswordCallback pcb = (PasswordCallback)cb;
		    pcb.setPassword(pass.toCharArray());
		  } else {
		    throw new UnsupportedCallbackException
	            (cb, "Don't know what to do with this!!");
		  }
		}//end of for-loop
	  }
	}
	
	/**
	 * Realiza el logout del usuario validado.
	 * 
	 * @throws LoginException 
	 */
	public void logout() throws LoginException
	{
		lc.logout();
	}
	
	private LoginContext lc= null;
	private String username= null;
	private String password= null;
	private static final String WORKFLOW_DOMAIN= "bpm-domain";
	private static final String SEYCON_DOMAIN= "client-login";
}
