/*
 * AccessLogMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
 
package com.soffid.iam.sync.intf;

import java.util.Collection;
import java.util.List;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.sync.intf.LogEntry;

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
  public Collection<? extends LogEntry> getLogFromDate (java.util.Date since) 
    throws java.rmi.RemoteException, 
            InternalErrorException;
}

