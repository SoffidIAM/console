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
  /** Fetches logs until current date
   * 
   * @param since Starting date to gather logs
   * 
   * @exception InternalErrorException Se ha producido algun error dependiente 
   * del agente. El sistema reintentará recuperar los logs en otra ocasión
   * @exception java.rmi.RemoteException Error de comunicaciones
   * @return List of log entries to load
   */
  public Collection<? extends LogEntry> getLogFromDate (java.util.Date since) 
    throws java.rmi.RemoteException, 
            InternalErrorException;
}

