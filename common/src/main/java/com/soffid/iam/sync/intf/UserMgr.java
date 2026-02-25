/*
 * UserMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
package com.soffid.iam.sync.intf;

import java.rmi.RemoteException;

import com.soffid.iam.base.api.Account;
import com.soffid.iam.base.api.User;
import com.soffid.iam.exception.InternalErrorException;

/** 
 * Interfaz remoto de gestión de usuarios (y contraseñas)
 * 
 * @author  $Author: u07286 $
 * @version $Revision: 1.1.2.2 $
 */
 
public interface UserMgr extends java.rmi.Remote {
  /**Update user data.
   * 
   * @param account account data
   * @param user user data
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otra causa
   */
  public void updateUser (com.soffid.iam.base.api.Account account, User user) 
    throws java.rmi.RemoteException, 
            InternalErrorException;
  /** 
   * Update shared account data
   * 
   * @param account Account information
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otra causa
   */
  public void updateUser (com.soffid.iam.base.api.Account account) 
    throws java.rmi.RemoteException, 
            InternalErrorException;
  /**
   * Delete a user or account
   * 
   * @param userName
   * @throws RemoteException
   * @throws InternalErrorException
   */
  public void removeUser (String userName, String externalId) throws RemoteException, InternalErrorException;
  /** 
   * Updates account password.
   * 
   * @param userName account name
   * @param userData option user info (non shared accounts)
   * @param password password to set
   * @param mustchange true if user must change password at next login
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otra causa
   */
  public void updateUserPassword (String userName, User userData, com.soffid.iam.am.api.Password password, boolean mustchange)
    throws java.rmi.RemoteException, 
            InternalErrorException;

  /** 
   * 
   * Verifyfies the user password
   * 
   * @param userName account name
   * @param password password to test
   * 
   * @return true si la contraseña es válida
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otra causa
   */
  public boolean validateUserPassword (String userName, com.soffid.iam.am.api.Password password)
    throws java.rmi.RemoteException,
            InternalErrorException;
  
}

