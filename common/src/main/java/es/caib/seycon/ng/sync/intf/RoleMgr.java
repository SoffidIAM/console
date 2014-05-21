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

// $Log: RoleMgr.java,v $
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
// Revision 1.3  2004-03-15 12:08:05  u07286
// Conversion UTF-8
//
// Revision 1.2  2004/03/15 11:57:48  u07286
// Agregada documentacion JavaDoc
//

public interface RoleMgr extends java.rmi.Remote {
  /** Actualizar los datos de un rol.
   * El agente SEYCON deberá consultar los datos al servidor y sincronizarlos
   * con los existentes en el susbsistema de su compentencia
   * @param role nombre del rol
   * @param bd nombre del agente (o base de datos) en el que se define
   * @throws java.rmi.RemoteException error al contactar con el servidor
   * @throws InternalErrorException cualquier otra causa
   */
  public void updateRole (Rol rol)
    throws java.rmi.RemoteException,
            InternalErrorException;
  /**
   * Borra el rol cuando ya no es necesario
   * 
   * @param rolName  - Nombre del rol
   * @param dispatcher - Dispatcher asociado
   */
  public void removeRole (String rolName, String dispatcher)
		    throws java.rmi.RemoteException,
            InternalErrorException;

}

