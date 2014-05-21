
// Copyright (c) 2000 Govern  de les Illes Balears
package es.caib.seycon.util;
import java.rmi.*;

import es.caib.seycon.ng.comu.Password;
import java.util.*;
import java.net.Socket;
import java.net.InetAddress;
import java.io.*;



/**
 * Ejecutar consultas al servidor SEYCON
 * <P>
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.3 $
 */
public class Query extends Object implements Runnable
{
  /** resultado final (en caso de error) */
  static String result;
  /** semáforo para implementar exclusión mútua */
  static Object lock = new Object ();
  /** por defecto modo espartano */
  static boolean verbose=false;
  /** por defecto sin información de debug */
  static boolean debug=false;
  /** Thread principal que ejecuta el main */
  static Thread masterThread;
  /** Número de threads que quedan ejecutándose */
  static int runningThreads ;
  /** Consulta a realizar */
  String query;
  /** Host al cual consultar */
  String host;
  /**
   * Constructor
   * @param host máquina a la cual consultar
   * @param query consulta a realizar
   */
  public Query(String host, String query)
  {
    this.host = host;
    this.query = query;
  }

  /**
   * Procedimiento principal del Thread.
   * Intenta ejecutar la consulta. Si el resultado es positivo, se finaliza
   * la máquina virtual Java.
   * En caso contrario se anota el error y se notifica al thread padre
   **/
  public void run ()
  {
    String r = ""; //$NON-NLS-1$
    try {
      if ( debug ) System.err.println (String.format(Messages.getString("Query.TryConnectHost"), host)); //$NON-NLS-1$
      Socket s = new Socket (host, 559);
      s.setSoTimeout(120000);
//      s.connect(new InetAddress(host,559),5000);
      if ( debug ) System.err.println (String.format(Messages.getString("Query.HostConnected"), host)); //$NON-NLS-1$
      PrintWriter out = 
        new PrintWriter (new OutputStreamWriter (s.getOutputStream()),true);
      BufferedReader in =
        new BufferedReader (new InputStreamReader(s.getInputStream()));
      if ( debug ) System.err.println (String.format(Messages.getString("Query.QueryingHost"), query, host)); //$NON-NLS-1$
      out.println ("getData|"+query);  //$NON-NLS-1$
      if ( debug ) System.err.println (String.format(Messages.getString("Query.WaitingHost"), host)); //$NON-NLS-1$
      char ch[] = new char[256];
      int c = in.read (ch,0,3);
      r = new String (ch, 0, c);
      if (debug) System.err.println (String.format(Messages.getString("Query.GotHost"), r, host)); //$NON-NLS-1$
      if (r.startsWith ("OK|"))  //$NON-NLS-1$
      {
        synchronized (lock)
        {
          int columns = -1;
          int column = 0;
          boolean end = false;
          if (! verbose) System.out.print (r);
          while ( !end && (c = in.read (ch)) > 0) 
          {
            r = new String (ch, 0, c);
            if (!verbose)
            {
              System.out.print (r);
              if (r.endsWith("\n")) end = true; //$NON-NLS-1$
            } else 
            {
              int i;
              if (columns == -1) {
                i = r.indexOf("|"); //$NON-NLS-1$
                columns = Integer.decode(r.substring(0,i)).intValue ();
                i = i + 1;
              } else
                i = 0;
              while ( !end && i < c )
              {
                if ( ch[i] == '\n')
                {
                  end = true;
                }
                else if ( ch[i] == '|')
                {
                  column ++;
                  if ( column == columns)
                  {
                    column = 0;
                    System.out.println();
                    System.out.flush();
                  }
                  else
                  {
                    System.out.print ("\t"); //$NON-NLS-1$
                  }
                } else 
                {
                  System.out.print (ch[i]);
                }
                i++;
              }
            }
          }
          if (verbose) System.out.println();
          System.exit(0);
        }
          
      }
      else
        r = r + in.readLine ();
    } catch (Exception e)
    {
      if ( debug )  e.printStackTrace ();
      r = "ERROR|"+e.toString();  //$NON-NLS-1$
    }
    synchronized (lock)
    {
      result = r;
      runningThreads = runningThreads - 1;
      masterThread.interrupt();
    }
  }


  /** Método principal. 
   * Espera los siguientes argumentos:<br>
   * <b>-v</b> (opcional) modo verbose<br>
   * <b>-d</b> (opcional) modo debug<br>
   * <b>/path/to/query</b> pregunta a realizar al servidor SEYCON
   * 
   * @param args argumentos
   */
  public static void main(String[] args)
  {
     int i;
     String path = null ;

     for (i = 0 ; i < args.length; i ++)
     {
       if (args[i].equals( "-v") ) verbose = true; //$NON-NLS-1$
       else if (args[i].equals( "-d") ) debug = true; //$NON-NLS-1$
       else path = args[i];
     }
     if (path == null) 
     {
       System.out.println (Messages.getString("Query.SeyconQueryUseHelp")); //$NON-NLS-1$
       System.exit ( 1) ;
     }
     
 
     String list = System.getProperty ("sso.server.list"); //$NON-NLS-1$
     if (list == null)
     {
       System.err.println (Messages.getString("Query.SSOServerListNotFound")); //$NON-NLS-1$
       System.exit (1);
     }
     StringTokenizer tokenizer = new StringTokenizer(list, ","); //$NON-NLS-1$
         
     result = "Timeout";  //$NON-NLS-1$
     masterThread = Thread.currentThread();
     runningThreads = 0;
     while (tokenizer.hasMoreTokens())
     {
       String name = tokenizer.nextToken();
       if ( debug ) System.err.println (String.format(Messages.getString("Query.QueryingOn"), name));   //$NON-NLS-1$
       runningThreads = runningThreads + 1;
       new Query (name, path).run ();
       try {
         Thread.currentThread().sleep (5000);
       } catch (InterruptedException e) {}
     }
     boolean retry = true ;
     while (runningThreads > 0  && retry) {
       try {
         retry = false;
         Thread.currentThread().sleep(15000);
       } catch (InterruptedException e) {
         retry = true;
       }
     }
     synchronized ( lock )
     {
       System.err.println (result);
       System.exit(2);
     }
  }
}

