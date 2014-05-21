package es.caib.seycon.ng.sync.intf;

import java.rmi.RemoteException;

import es.caib.seycon.ng.comu.sso.Secret;
import es.caib.seycon.ng.exception.InternalErrorException;


public interface SecretStoreAgent {
    public String generateUserTicket (String user) throws RemoteException, InternalErrorException;
    
    public Secret[] getSecrets (String user) throws RemoteException, InternalErrorException;
}
