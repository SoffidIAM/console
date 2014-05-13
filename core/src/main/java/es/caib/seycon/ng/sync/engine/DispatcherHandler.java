package es.caib.seycon.ng.sync.engine;

import com.soffid.iam.api.ScheduledTask;

import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.sync.intf.KerberosAgent;

public abstract class DispatcherHandler {
    public int getInternalId() {
        return internalId;
    }

    public void setInternalId(int internalId) {
        this.internalId = internalId;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) throws InternalErrorException {
        this.dispatcher = dispatcher;
    }

    int internalId;
    Dispatcher dispatcher;
    
    public abstract boolean applies (TaskHandler task);

    public abstract boolean isComplete(TaskHandler task) ;

    public abstract boolean isError(TaskHandler task) ;

    public abstract boolean isActive() ;
    
    public abstract void sanityCheck();
    
    public abstract boolean isConnected();
    
    public abstract void reconfigure();
    
    public abstract KerberosAgent getKerberosAgent();
    
    public abstract Exception getConnectException();

    public abstract Object getRemoteAgent() ;
    
    public abstract String getAgentVersion();
    
    public abstract void processOBTask (TaskHandler task) throws InternalErrorException;

	public abstract void doReconcile (ScheduledTask task);

	public abstract void doAuthoritativeImport (ScheduledTask task);

}
