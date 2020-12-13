/*
 * RoleMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
 
package com.soffid.iam.sync.intf;

import com.soffid.iam.api.Role;

import es.caib.seycon.ng.exception.InternalErrorException;

public interface RoleMgr extends java.rmi.Remote {
  /** Update role attributes.
   * 
   * The agent can ask sync server for more information and apply changes to the
   * target system
   * 
   * @param role Role object
   * @throws java.rmi.RemoteException error al contactar con el servidor
   * @throws InternalErrorException cualquier otra causa
   */
  public void updateRole (Role role)
    throws java.rmi.RemoteException,
            InternalErrorException;
  /**
   * Borra el rol cuando ya no es necesario
   * 
   * @param rolName  - Role name
   * @param dispatcher - Target system
   */
  public void removeRole (String rolName, String dispatcher)
		    throws java.rmi.RemoteException,
            InternalErrorException;

}

