/*
 * MailAliasMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
 
package es.caib.seycon.ng.sync.intf;

import es.caib.seycon.ng.comu.LlistaCorreu;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;

/** 
 * Interfaz remoto de gestión de listas de correo
 *
 * @author  $Author: u07286 $
 * @version  $Revision: 1.1.2.3 $
 */

// $Log: MailAliasMgr.java,v $
// Revision 1.1.2.3  2012-11-05 09:50:00  u07286
// Noves interficies
//
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
// Revision 1.3  2004-03-15 12:08:04  u07286
// Conversion UTF-8
//
// Revision 1.2  2004/03/15 11:57:47  u07286
// Agregada documentacion JavaDoc
// 

public interface MailAliasMgr extends java.rmi.Remote {
  public void updateUserAlias(String useKey, Usuari user) throws InternalErrorException;
  public void removeUserAlias(String userKey) throws InternalErrorException;
  public void updateListAlias(LlistaCorreu llista) throws InternalErrorException;
  public void removeListAlias(String nomLlista, String domini) throws InternalErrorException;
}
