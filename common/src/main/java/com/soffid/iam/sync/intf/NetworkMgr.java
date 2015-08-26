/*
 * NetworkMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
 
package com.soffid.iam.sync.intf;

import es.caib.seycon.ng.exception.InternalErrorException;

public interface NetworkMgr extends java.rmi.Remote {
  /** Actualizar el inventario de redes
   * El agente deberá consultar los datos al servidor SEYCON.
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otro error
   */
  public void updateNetworks () 
    throws java.rmi.RemoteException, 
            InternalErrorException;
}
