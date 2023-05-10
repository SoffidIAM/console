/*
 * UserMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
package com.soffid.iam.sync.intf;

import java.rmi.RemoteException;

import es.caib.seycon.ng.exception.InternalErrorException;

/** 
 * Interfaz remoto de gestión de usuarios (y contraseñas)
 * 
 * @author  $Author: u07286 $
 * @version $Revision: 1.1.2.2 $
 */
 
public interface IndexMgr {
	void index (String objectClass, long id) throws InternalErrorException;
}
