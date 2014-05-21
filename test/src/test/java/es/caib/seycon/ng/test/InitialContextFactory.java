package es.caib.seycon.ng.test;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class InitialContextFactory {

	public static Context getInitialContext () throws NamingException
	{
		Hashtable props = new Hashtable( );
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.NamingContextFactory"); //$NON-NLS-1$
		props.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099"); //$NON-NLS-1$
		Context ctx = new InitialContext(props);
		return ctx;
	}
}
