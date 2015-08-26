package com.soffid.iam.sync.engine;

import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.sync.intf.KerberosAgent;

import es.caib.seycon.ng.exception.InternalErrorException;

public abstract class DispatcherHandler {
    public int getInternalId() {
        return internalId;
    }

    public void setInternalId(int internalId) {
        this.internalId = internalId;
    }

    public com.soffid.iam.api.System getSystem() {
        return system;
    }

    public void setSystem(com.soffid.iam.api.System dispatcher) throws InternalErrorException {
        this.system = dispatcher;
    }

    int internalId;
    com.soffid.iam.api.System system;
    
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

	/**
	 * Executes a single action
	 * 
	 * @return false if a delay should be performed
	 * 
	 * @throws InternalErrorException
	 */
	public abstract boolean runStep () throws InternalErrorException;
}
