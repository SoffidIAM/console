package es.caib.sso.client;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
/**
 * Thread para localizar un servidor de nombres
 */
class HttpContextFactoryThread
      implements Runnable
{
  /**
   * Propiedades de conexión
   */
  Hashtable env;
  /**
   * Objeto al cual notificar la ejecución
   */
  HttpNamingContextFactory notify;
  /**
   * Constructor
   */
  HttpContextFactoryThread (Hashtable hash, HttpNamingContextFactory notify)
  {
    env = hash;
    this.notify = notify;
  }

  public void run () 
  {

    if (HttpNamingContextFactory.debug)
      System.out.println (String.format(Messages.getString("HttpNamingContextFactory.HttpContextFactoryThreadStarted"), env.get(Context.PROVIDER_URL))); //$NON-NLS-1$
    Context ctx = null;
    javax.naming.NamingException e = null;
    try {
      ctx = new org.jboss.naming.HttpNamingContextFactory().getInitialContext (env);
    } catch (NamingException e2) {
      e = e2;
    }
    synchronized (notify)
    {
      if (notify.ctx == null)
      {
        notify.ctxException = e;
        if (ctx != null) {
          notify.ctx = ctx;
          notify.notify();
        }
      }
    }
    if (HttpNamingContextFactory.debug)
      System.out.println (String.format(Messages.getString("HttpNamingContextFactory.HttpContextFactoryThreadFinished"), env.get(Context.PROVIDER_URL))); //$NON-NLS-1$
  }
}

/**
 * Thread que lanza un thread para cada servidor HTTP posible
 */
class HttpContextFactoryMultiThread
implements Runnable 
{
  /**
   * Propiedades de conexión
   */
  Hashtable env;
  /**
   * Objeto al cual notificar la ejecución
   */
  HttpNamingContextFactory notify;
  /**
   * Constructor
   */
  HttpContextFactoryMultiThread (Hashtable hash, HttpNamingContextFactory notify)
  {
    env = hash;
    this.notify = notify;
  }

  /**
   * Lanzar un thread para cada URL 
   */
  public void run () 
  {
    if (HttpNamingContextFactory.debug)
      System.out.println (Messages.getString("HttpNamingContextFactory.HttpContextFactoryMultiThreadStarted")); //$NON-NLS-1$
    Vector threads = new Vector ();
    StringTokenizer tokenizer =
      new StringTokenizer ((String) env.get(Context.PROVIDER_URL),
                           ", "); //$NON-NLS-1$
    while (tokenizer.hasMoreTokens())
    {
      String url = tokenizer.nextToken();
      Hashtable env2 = (Hashtable) env.clone();
      env2.put(Context.PROVIDER_URL, url);
      Thread t = new Thread ( new HttpContextFactoryThread ( env2, notify));
      threads.add(t);
      t.start();
    }
    for ( int i = 0; i < threads.size(); i++)
    {
      Thread t = (Thread) threads.get(i);
      try { t.join(); } catch ( java.lang.InterruptedException e) {}
    }
    synchronized (notify)
    {
      if (notify.ctx == null)
      {
        notify.notify();
      }
    }
    if (HttpNamingContextFactory.debug)
      System.out.println (Messages.getString("HttpNamingContextFactory.HttpContextFactoryMultiThreadFinished")); //$NON-NLS-1$
  }
}

/**
 * Clase principal
 */

public class HttpNamingContextFactory 
  implements InitialContextFactory

{
  /**
   * debug
   */
  protected static boolean debug = true;
  /**
   * Contexto inicial ya obtenido
   */
  Context ctx;
  /**
   * Excepción que impide obtener el contexto
   */
  javax.naming.NamingException ctxException;
  /**
   * Constructor
   */
  public HttpNamingContextFactory()
  {
    ctx = null;
    ctxException = null;
  }
  public Context getInitialContext(Hashtable hash) throws NamingException
  {
    if (debug) System.out.println (Messages.getString("HttpNamingContextFactory.HttpContextFactoryStarted")); //$NON-NLS-1$
 
    if ( ctx == null )
    {
      synchronized (this)
      {
        Thread t = new Thread(new HttpContextFactoryMultiThread ( hash, this));
        t.start ();
        try {this.wait();} catch (InterruptedException e) {}
      }
    }
    if (debug) System.out.println (Messages.getString("HttpNamingContextFactory.HttpContextFactoryFinished")); //$NON-NLS-1$
    if ( ctx == null && ctxException == null)
     throw new javax.naming.NamingException (Messages.getString("HttpNamingContextFactory.ObtainInitialContextError"));  //$NON-NLS-1$
    else if ( ctx == null && ctxException != null)
     throw ctxException;
    else
     return ctx;

  }

  {
    debug = (System.getProperty ("es.caib.jboss.naming.debug") != null); //$NON-NLS-1$
  }
}

