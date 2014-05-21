package es.caib.seycon.ng.sync.intf;

import java.rmi.RemoteException;

import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.InternalErrorException;

public interface IbsalutMgr {
    void updateIbsalutPassword (String user, Password p) throws InternalErrorException, RemoteException;
}
