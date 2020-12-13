/*
 * RoleMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
 
package es.caib.seycon.ng.sync.intf;

import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.exception.InternalErrorException;

/** 
 * Interfaz remoto de gestión de roles
 * 
 * @author  $Author: u07286 $
 * @version $Revision: 1.1.2.2 $
 */


public interface RoleMgr extends java.rmi.Remote {
	  /** Update role attributes.
	   * 
	   * The agent can ask sync server for more information and apply changes to the
	   * target system
	   * 
	   * @param rol Role object
	   * @throws java.rmi.RemoteException error al contactar con el servidor
	   * @throws InternalErrorException cualquier otra causa
	   */
  public void updateRole (Rol rol)
    throws java.rmi.RemoteException,
            InternalErrorException;
  /**
   * Removes the role when is no longer required
   * 
   * @param rolName role name
   * @param dispatcher system name
   * @throws java.rmi.RemoteException
   * @throws InternalErrorException
   */
  public void removeRole (String rolName, String dispatcher)
		    throws java.rmi.RemoteException,
            InternalErrorException;

}

