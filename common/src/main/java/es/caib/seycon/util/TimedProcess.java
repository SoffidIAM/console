// Copyright (c) 2000 Govern  de les Illes Balears
// 
// Conjunto de clases que implementa un proceso de sistema operativo con tiempo
// máximo de ejecución
//
// $Log: TimedProcess.java,v $
// Revision 1.1.2.2  2012-09-10 08:45:41  u07286
// Refactoring
//
// Revision 1.1.2.1  2012-05-16 10:33:38  u07286
// Reestructuració de paquets seycon antics
//
// Revision 1.1  2007-09-06 12:51:10  u89559
// [T252]
//
// Revision 1.4  2004-03-15 12:08:07  u07286
// Conversion UTF-8
//
// Revision 1.3  2004/03/15 11:57:51  u07286
// Agregada documentacion JavaDoc
//

package es.caib.seycon.util;
import java.io.*;




/** 
 * Thread que controla la finalización del proceso de sistema operativo en
 * el tiempo establecido.
 * Trasncurrido el timeout destruirá el proceso para que el padre pueda 
 * continuar con su ejecución.
 */
class TimeoutThread extends Thread
{
  /** tiempo máximo de espera */
  long timeout ;
  /** proceso de sistema operativo */
  Process process;
  /** true si se ha alcanzado el timeout */
  boolean timedOut;
  /* Instanciar el thread */
  protected TimeoutThread (Process p, long timeout)
  {
     this.timeout = timeout;
//     parentThread = java.lang.Thread.currentThread();
     process = p;
     timedOut = false;
  }
  /** Ejecutar el thread */
  public void run()
  {
    try {
      sleep (timeout);
      timedOut = true;
      process.destroy ();
    } catch (InterruptedException e) {
    } catch (ThreadDeath e) {
    }
  }
  /** true si se ha alcanzado el timeout */
  public boolean isTimedOut () { return timedOut; }
}

/** Thread que consume la salida del proceso
 */
class InputStreamThread extends Thread
{
  /** Corriente de entrada procedente del proceso */
  private InputStream is;
  /** Corriente de salida donde volcar una copia de la entrada */
  private PrintStream os;
  /** Cadena donde se vuelca el contenido de la salida */
  private ByteArrayOutputStream output;
  /** Constructor
   * @param is corriente de salida del proceso ( entrada para Java )
   * @param os corriente de salida donde se copia el resultado
   */
  // Tarea que interrumpirá al padre al cabo de timeout milisegundos
  protected InputStreamThread (InputStream is, PrintStream os)
  {
     this.is = is;
     this.os = os;
     output = new ByteArrayOutputStream(); //$NON-NLS-1$
  }
  /** Bucle principal del thread. Consumira la corriente y la almacena 
   * internamente
   */
  public void run()
  {
    int i;

//    os.println ("Abriendo stream");
    try {
      i = is.read ();
      while (i >= 0)
      {
        output.write(i);
        if (os != null) os.write (i);
//        os.flush();
        i = is.read ();
      }
    } catch (IOException e) {
      System.err.println (Messages.getString("TimedProcess.ReadStreamError")); //$NON-NLS-1$
      e.printStackTrace (System.err);
    }
//    os.println ("Cerrando stream");
  }

  /** recuperar la salida producida por el proceso de sistema operativo
   * @return salida producido 
   */
  public String getOutput () {
	String out = output.toString();
	if (out.length() > 0 && out.charAt(out.length()-1) == '\n')
      return out.substring (0, out.length() - 1);
    else
      return out;
  }
}

/**
 * Proceso de sistema operativo con tiempo máximo de ejecución.
 * Se ejecutan simultáneamente el proceso de sistema operativo y tres threads:
 * <li>thread timeout: cancelará el proceso si transcurrido el tiempo máximo de
 * ejecución, éste no ha finalizado</li>
 * <li>consumidor de salida estándar: lee la salida estándar del proceso y la
 * almacena en memoria</li>
 * <li>consumidor de error estándra: lee el error estándar del proceso y lo 
 * almacena en memoria</li>
 * <li>proceso de sistema operativo: ejecuta el proceso indicado en el método 
 * exec (sincrónamente) o execNoWait (asíncronamente)</li>
 * Los threads consumidores son opcionales en las llamadas asíncronas y
 * automáticos en la llamada síncrona.<BR>
 * Para realizar una llamada síncrona, la clase invocante ejecutará:<BR><BR>
 * <CODE>Process p = new Process ( 10000 ); // 10 segundos de timpo límite<BR>
 * try { <BR>
 * &nbsp;&nbsp;&nbsp;int result = p.exec ("/usr/ucb/whoami"); // Proceso de sistema operativo<BR>
 * } catch (TimedOutExceptin e) {  <BR>
 * &nbsp;&nbsp;&nbsp;   ... <BR>
 * }<BR></CODE><BR><BR>
 * Para realizar una llamada asíncrona, la clase invocante ejecutará:<BR><BR>
 * <CODE>Process p = new Process ( 10000 ); // 10 segundos de timpo límite<BR>
 * try { <BR>
 * &nbsp;&nbsp;&nbsp;int result = p.execNoWait ("/usr/ucb/whoami"); // Proceso de sistema operativo<BR>
 * &nbsp;&nbsp;&nbsp;p.consumeOutput (); // (opcionalmente) se instanciará el thread consumidor de
 * la salida estándar <BR>
 * &nbsp;&nbsp;&nbsp;p.consumeError (); // (opcionalmente) se instanciará el thread consumidor del
 * error estándar <BR>
 * &nbsp;&nbsp;&nbsp;p.getInputStream (); // (opcionalmente) se usará la entrada estándar <BR>
 * &nbsp;&nbsp;&nbsp;... <BR>
 * p.join (); // Esperar a la finalización ( o timeout ) del proceso <BR>
 * } catch (TimedOutExceptin e) {  <BR>
 * &nbsp;&nbsp;&nbsp;... <BR>
 * }<BR></CODE>
 * 
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.2 $
 */


public class TimedProcess extends Object
{
  /** tiempo máximo en milisegundos */
  long timeout;
  /** resultado de la ejecución del proceso */
  int result = -1;
  /** contenido de la salida estándar */
  protected String output;
  /** contenido del error estándar */
  protected String error;
  /** Proceso de sistema operativo */
  Process process;
  /** Thread que controla el time-out */
  TimeoutThread timeoutThread ;
  /** Thread que lee del error estándar */
  InputStreamThread errorThread;
  /** Thread que lee de la salida estándar */
  InputStreamThread outputThread;
  /** Activar la información de debug */
  boolean debug;
private String[] environment;
  /**
   * Construir un nuevo TimedProcess
   * @param timeout tiempo máximo de ejecución (en milisegundos)
   */
  public TimedProcess(long timeout)
  {
    this.timeout = timeout;
    output = ""; //$NON-NLS-1$
    error = ""; //$NON-NLS-1$
    errorThread = null;
    outputThread = null;
    timeoutThread = null;
    process = null;
    String s = System.getProperty ("seycon.exec.debug"); //$NON-NLS-1$
    if (s != null) debug = true;
    else debug = false;
  }

  /**
   * Instancia y arranca el thread que leerá y almacenará la salida estándar
   */

  public void consumeOutput ()
  {
      if (debug)
        outputThread = new InputStreamThread (getOutputStream (), System.out);
      else
        outputThread = new InputStreamThread (getOutputStream (), null);
      outputThread.start ();
  }

  /**
   * Instancia y arranca el thread que leerá y almacenará el error estándar
   */

  public void consumeError ()
  {
      if (debug)
        errorThread = new InputStreamThread (getErrorStream (), System.err);
      else
        errorThread = new InputStreamThread (getErrorStream (), null);
      errorThread.start ();
  }

  /**
   * Alternativamente al método consumeOutput, el usuario puede obtener la salida
   * estándar del proceso y tratarla de forma alternativa. Su uso se recomienda
   * en el caso de procesos que generen un gran cantidad de salida estandar
   * @return corriente de entrada que conecta con la salida estándar del proceso
   */

  public InputStream getOutputStream()
  {
    return process.getInputStream();
  }

  /**
   * Alternativamente al método consumeError, el usuario puede obtener el error
   * estándar del proceso y tratarlo de forma alternativa. Su uso se recomienda
   * en el caso de procesos que generen un gran cantidad de error estándar
   * @return corriente de entrada que conecta con el error estándar del proceso
   */
  public InputStream getErrorStream()
  {
    return process.getErrorStream();
  }

  /**
   * El usuario puede obtener la entrada estándar del proceso para comunicarse
   * con él.
   * @return corriente de salida que conecta con la entrada estándar del proceso
   */
  public OutputStream getInputStream ()
  {
    return process.getOutputStream();
  }

  /** 
   * Ejecutar el proceso de forma asíncrona. La llamada finaliza de forma
   * casi inmediata y el proceso se inicia de fondo.
   * @param command proceso de sistema operativo a ejecutar
   * @throws IOException error ejecutando el proceso.
   */
  public void execNoWait (String command)
         throws IOException

  {
    process = null;
    error = ""; //$NON-NLS-1$
    output = ""; //$NON-NLS-1$

    if (debug)
      System.out.println (String.format(Messages.getString("TimedProcess.RunningCommand"), command));  //$NON-NLS-1$
    if (environment == null)
    	process = java.lang.Runtime.getRuntime ().
              exec (command);
    else
    	process = java.lang.Runtime.getRuntime ().
        	exec (command, environment);
    timeoutThread = new TimeoutThread (process, timeout);
    timeoutThread.start ();
  }

  public String[] getEnvironment()
  {
	return environment;
  }

  public void setEnvironment(String[] environment)
  {
	this.environment = environment;
  }

/** 
   * Ejecutar el proceso de forma asíncrona. La llamada finaliza de forma
   * casi inmediata y el proceso se inicia de fondo.
   * @param command proceso (con parámetros) de sistema operativo a ejecutar
   * @throws IOException error ejecutando el proceso.
   */
  public void execNoWait (String command[])
         throws IOException

  {
    process = null;
    error = ""; //$NON-NLS-1$
    output = ""; //$NON-NLS-1$
    int i;

    if (debug )
    {
      System.out.print (Messages.getString("TimedProcess.Running")); //$NON-NLS-1$
      for ( i = 0 ; i < command.length; i++)
        System.out.print (command[i]+" "); //$NON-NLS-1$
      System.out.println ();
    }
    if (environment == null)
    	process = java.lang.Runtime.getRuntime ().
              exec (command);
    else
    	process = java.lang.Runtime.getRuntime ().
              exec (command, environment);
    timeoutThread = new TimeoutThread (process, timeout);
    timeoutThread.start ();
  }

  /** 
   * Espera a la finalización del proceso que se arrancó mediante execNoWait
   * @return código de error del proceso
   * @throws TimedOutException el proceso no ha finalizado en el tiempo definido
   */ 
  public int join () throws TimedOutException
  {
    int result = -1;
    try {
      if (process == null ) result = -1 ;
      else result = process.waitFor ();
      timeoutThread.stop();
      if ( errorThread != null) errorThread.join  (timeout);
      if ( outputThread != null) outputThread.join (timeout);
    } catch (InterruptedException e) {
    } finally {
      if (process != null) process.destroy();
    }
    if (timeoutThread != null && timeoutThread.isTimedOut())
    {
      throw new TimedOutException();
    }
    if ( errorThread != null ) error = errorThread.getOutput ();
    if ( outputThread != null ) output = outputThread.getOutput ();
    System.out.flush ();
    System.err.flush ();
    return result;
  }

  /** 
   * Ejecutar el proceso de forma síncrona. La llamada finalizará cuando el 
   * proceso haya finalizado, o bien haya sido cancelado por expiración del
   * tiempo asignado
   * @param command proceso de sistema operativo a ejecutar
   * @throws TimedOutException el proceso no ha finalizado en el tiempo definido
   * @throws IOException error ejecutando el proceso.
   */
  public int exec (String command)
         throws IOException, TimedOutException

  {
    execNoWait (command);
    consumeOutput ();
    consumeError ();
    return join ();
  }

  /** 
   * Ejecutar el proceso de forma síncrona. La llamada finalizará cuando el 
   * proceso haya finalizado, o bien haya sido cancelado por expiración del
   * tiempo asignado
   * @param command proceso (con parámetros) de sistema operativo a ejecutar
   * @throws TimedOutException el proceso no ha finalizado en el tiempo definido
   * @throws IOException error ejecutando el proceso.
   */
  public int exec (String command[])
         throws IOException, TimedOutException

  {
    execNoWait (command);
    consumeOutput ();
    consumeError ();
    return join ();
  }

  /** 
   * Obtener la salida estándar del proceso. Para su uso es necesario haber
   * usado la forma sincrona o bien haber invocado el método consumeOutput tras
   * la llamad asíncrona
   * @return salida estandar del proceso
   */
  public String getOutput () { return output; }

  /** 
   * Obtener el error estándar del proceso. Para su uso es necesario haber
   * usado la forma sincrona o bien haber invocado el método consumeError tras
   * la llamad asíncrona
   * @return error estandar del proceso
   */
  public String getError () {return error;}
}

