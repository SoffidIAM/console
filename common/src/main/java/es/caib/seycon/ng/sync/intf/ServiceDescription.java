/*
 * PrinterDescription.java
 *
 * Created on May 8, 2000, 11:17 AM
 */
 
package es.caib.seycon.ng.sync.intf;

/** 
 * Información de servicio
 * 
 * @author  $Author: u07286 $
 * @version $Revision: 1.1.2.1 $
 * 
 */

// $Log: ServiceDescription.java,v $
// Revision 1.1.2.1  2012-09-25 08:57:19  u07286
// Multiples canvis
//
// Revision 1.1.2.1  2012-05-16 10:33:38  u07286
// Reestructuració de paquets seycon antics
//
// Revision 1.1  2007-09-06 12:51:10  u89559
// [T252]
//
// Revision 1.3  2004-03-15 12:08:06  u07286
// Conversion UTF-8
//
// Revision 1.2  2004/03/15 11:57:49  u07286
// Agregada documentacion JavaDoc
//

public class ServiceDescription extends Object implements java.io.Serializable {

  /** Crear un Service Description */
  public ServiceDescription() {
  }
  /** Nombre del servicio */
  public String ServiceName;
  /** Nombre del host */
  public String HostName;
  /** Volcar el contenido @param stream corriente de salida */
  public void dump (java.io.PrintStream stream) {
    stream.println (Messages.getString("ServiceDescription.Service")+ServiceName); //$NON-NLS-1$
    stream.println (Messages.getString("ServiceDescription.Host")+HostName); //$NON-NLS-1$
  }
}