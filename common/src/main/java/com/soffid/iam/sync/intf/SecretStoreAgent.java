package com.soffid.iam.sync.intf;

import java.rmi.RemoteException;

import com.soffid.iam.exception.InternalErrorException;
import com.soffid.iam.sync.api.Secret;

public interface SecretStoreAgent {
    public String generateUserTicket (String user) throws RemoteException, InternalErrorException;
    
    public Secret[] getSecrets (String user) throws RemoteException, InternalErrorException;
}
