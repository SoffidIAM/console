/*
 * RoleMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
 
package com.soffid.iam.sync.intf;

import com.soffid.iam.api.Role;

import es.caib.seycon.ng.exception.InternalErrorException;

public interface RoleMgr extends java.rmi.Remote {
  /** Actualizar los datos de un rol.
   * El agente SEYCON deberá consultar los datos al servidor y sincronizarlos
   * con los existentes en el susbsistema de su compentencia
   * @param role nombre del rol
   * @param bd nombre del agente (o base de datos) en el que se define
   * @throws java.rmi.RemoteException error al contactar con el servidor
   * @throws InternalErrorException cualquier otra causa
   */
  public void updateRole (Role rol)
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

