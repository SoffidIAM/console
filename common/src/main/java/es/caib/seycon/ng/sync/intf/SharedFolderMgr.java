/*
 * SharedFolderMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
 
package es.caib.seycon.ng.sync.intf;

import es.caib.seycon.ng.exception.InternalErrorException;

/** 
 * Interfaz remoto de gestión de carpetas compartidas
 * 
 * @author  $Author: u07286 $
 * @version $Revision: 1.1.2.2 $
 * 
 */

// $Log: SharedFolderMgr.java,v $
// Revision 1.1.2.2  2012-11-05 09:50:00  u07286
// Noves interficies
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
// Revision 1.3  2004-03-15 12:08:06  u07286
// Conversion UTF-8
//
// Revision 1.2  2004/03/15 11:57:49  u07286
// Agregada documentacion JavaDoc
//

public interface SharedFolderMgr extends java.rmi.Remote {
  /** carpeta de usuario */
  public final static int userFolderType = 1;
  /** carpeta de grupo */
  public final static int groupFolderType = 2;
  /** Creación de una carpeta compartida.
   * El agente deberá consultar con el servidor si necesita información
   * adicional (cuotas, etc...)
   * @param folder código del usuario o grupo
   * @param type tipo de carpeta: userFolderType o groupFolderType
   * @throws java.rmi.RemoteException error de comunicaciones
   * @throws InternalErrorException cualquier otra causa
   */
  public void createFolder (String folder, int type) 
    throws java.rmi.RemoteException, 
            InternalErrorException;
}
