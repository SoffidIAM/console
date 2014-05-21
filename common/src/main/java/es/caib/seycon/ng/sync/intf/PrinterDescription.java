/*
 * PrinterDescription.java
 *
 * Created on May 8, 2000, 11:17 AM
 */
 
package es.caib.seycon.ng.sync.intf;

/** 
 * Información acerca de una impresora (SC_IMPRES)
 *
 * @author  $Author: u07286 $
 * @version $Revision: 1.1.2.1 $
 */

// $Log: PrinterDescription.java,v $
// Revision 1.1.2.1  2012-09-25 08:57:19  u07286
// Multiples canvis
//
// Revision 1.1.2.1  2012-05-16 10:33:38  u07286
// Reestructuració de paquets seycon antics
//
// Revision 1.1  2007-09-06 12:51:10  u89559
// [T252]
//
// Revision 1.3  2004-03-15 12:08:05  u07286
// Conversion UTF-8
//
// Revision 1.2  2004/03/15 11:57:48  u07286
// Agregada documentacion JavaDoc
//

public class PrinterDescription extends Object implements java.io.Serializable {

  /** Creates new PrinterDescription */
  public PrinterDescription () {
  }
  /** host que alberga la cola */
  public String HostName;
  /** nombre de la cola */
  public String QueueName;
  /** nombre que tiene en el AS-400 @deprecated */
  public String ASQueueName;
  /** nombre que tiene en SAP @deprecated */
  public String SAPQueueName;
  /** Usuarios que deben acceder a ella */
  public String Users [];
  /** Grupos que deben acceder a ella */
  public String Groups [];
  /** Volcado de datos
   * @param stream corriente de salida
   */
  public void dump (java.io.PrintStream stream) {
    stream.println ("Host        : "+HostName); //$NON-NLS-1$
    stream.println ("Queue       : "+QueueName); //$NON-NLS-1$
    stream.println ("SAP Queue   : "+SAPQueueName); //$NON-NLS-1$
    stream.println ("AS/400 Queue: "+ASQueueName); //$NON-NLS-1$
  }
}