package es.caib.seycon.ng.sync.intf;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * Gestiona els accessos dels usuaris a les bases de dades mitjançant un trigger
 * i una taula de base de dades per gestionar accés
 * 
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.2 $
 * 
 */
/**
 * @author u88683
 * 
 */
public interface AccessControlMgr {

    /**
     * Gestión del control de acceso
     * 
     * @throws java.rmi.RemoteException
     * @throws InternalErrorException
     */
    public void updateAccessControl() throws java.rmi.RemoteException, InternalErrorException;

}
