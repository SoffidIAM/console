/*
 * HostMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
 
package es.caib.seycon.ng.sync.intf;

import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.exception.InternalErrorException;


/** 
 * Interfaz remoto de gestión de máquinas
 *
 * @author  $Author: u07286 $
 * @version  $Revision: 1.1.2.2 $
 */

// $Log: HostMgr.java,v $
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

public interface HostMgr extends java.rmi.Remote {
  /** Actualizar los datos de una máquina.
   * El agente deberá consultar los datos al servidor SEYCON.
   * @param host código de la máquina
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otro error
   */
  public void updateHost (Maquina maquina) 
    throws java.rmi.RemoteException, 
            InternalErrorException;
  /** 
   * Borrar los hosts que han sido dados de baja
   * El agente deberá correlacionar sus datos con los datos al servidor SEYCON.
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otro error
   */
  public void removeHost (String name) 
    throws java.rmi.RemoteException, 
            InternalErrorException;
}
