/*
 * UserMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
// $Log: UserMgr.java,v $
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
// Revision 1.3  2004-03-15 12:08:09  u07286
// Conversion UTF-8
//
// Revision 1.2  2004/03/15 11:57:53  u07286
// Agregada documentacion JavaDoc
//

package es.caib.seycon.ng.sync.intf;

import java.rmi.RemoteException;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;

/** 
 * Interfaz remoto de gestión de usuarios (y contraseñas)
 * 
 * @author  $Author: u07286 $
 * @version $Revision: 1.1.2.2 $
 */
 
public interface UserMgr extends java.rmi.Remote {
  /**Update user data.
   * 
   * @param user código de usuario
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otra causa
   */
  public void updateUser (String userName, Usuari userData) 
    throws java.rmi.RemoteException, 
            InternalErrorException;
  /** 
   * Update shared account data
   * 
   * @param user código de usuario
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otra causa
   */
  public void updateUser (String accountName, String description) 
    throws java.rmi.RemoteException, 
            InternalErrorException;
  /**
   * Delete a user or account
   * 
   * @param userName
   * @throws RemoteException
   * @throws InternalErrorException
   */
  public void removeUser (String userName) throws RemoteException, InternalErrorException;
  /** 
   * Updates account password.
   * 
   * @param user account name
   * @param userData option user info (non shared accounts)
   * @param password password to set
   * @param mustchange true if user must change password at next login
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otra causa
   */
  public void updateUserPassword (String userName, Usuari userData, Password password, boolean mustchange)
    throws java.rmi.RemoteException, 
            InternalErrorException;

  /** 
   * 
   * Verifyfies the user password
   * 
   * @param user account name
   * @param password password to test
   * 
   * @return true si la contraseña es válida
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otra causa
   */
  public boolean validateUserPassword (String userName, Password password)
    throws java.rmi.RemoteException,
            InternalErrorException;
}

