/*
 * GroupMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
 
package com.soffid.iam.sync.intf;

import com.soffid.iam.api.Group;

import es.caib.seycon.ng.exception.InternalErrorException;


public interface GroupMgr extends java.rmi.Remote {
  /**
   * Updates a group in the target system
   * 
   * The agent can retrieve additional information from the Serverservice
   * 
   * @param group Group object
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otro error
   */
  public void updateGroup (Group group)
    throws java.rmi.RemoteException,
            InternalErrorException;

  /**
   * Removes a group from the target system
   * 
   * @param group Group name
   *    * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otro error
   */
  public void removeGroup (String group)
    throws java.rmi.RemoteException,
            InternalErrorException;

}

