/*
 * AccessLogMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
 
package es.caib.seycon.ng.sync.intf;

import java.util.Collection;
import java.util.List;

import es.caib.seycon.ng.exception.InternalErrorException;

/** Interfaz remoto para recuperar logs de acceso al sistema.
 * Se llama periódicamente desde el SeyconServer, siempre que el parámetro
 * server.getlogs tenga un valor true (o no definido)
 * 
 * 
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.2 $
 */

 // $Log: AccessLogMgr.java,v $
 // Revision 1.1.2.2  2012-10-01 06:21:20  u07286
 // Reestructuració de paquets
 //
 // Revision 1.1.2.1  2012-09-25 08:57:19  u07286
 // Multiples canvis
 //
 // Revision 1.1.2.2  2012-09-18 06:11:08  u07286
 // Canvi paquet excepcions
 //
 // Revision 1.1.2.1  2012-05-16 10:33:38  u07286
 // Reestructuració de paquets seycon antics
 //
 // Revision 1.1  2007-09-06 12:51:10  u89559
 // [T252]
 //
 // Revision 1.6  2004-03-15 12:08:02  u07286
 // Conversion UTF-8
 //
 // Revision 1.5  2004/03/15 11:57:45  u07286
 // Agregada documentacion JavaDoc
 //
 // Revision 1.4  2004/02/23 10:51:43  u07286
 // *** empty log message ***
 //

 public interface AccessLogMgr extends java.rmi.Remote {
  /** Recoger logs hasta la fecha actual.
   * El agente SEYCON debe recuperar desde la fecha inicial pasada como parámetro
   * hasta el instante actual.
   * No obstante, si el agente puede decidir no enviar todos los registros de
   * acceso (debido al volumen de datos a enviar), debe asegurarse que se envían
   * siempre los mas antíguos; es decir, si quedan logs sin enviar deben ser
   * siempre los más recientes, nunca los más antíguos
   * 
   * @exception InternalErrorException Se ha producido algun error dependiente 
   * del agente. El sistema reintentará recuperar los logs en otra ocasión
   * @exception java.rmi.RemoteException Error de comunicaciones
   * @param From Fecha desde la cual se desean recuperar logs.
   * @return array con los registros de acceso
   */
  public Collection<LogEntry> getLogFromDate (java.util.Date From) 
    throws java.rmi.RemoteException, 
            InternalErrorException;
}

