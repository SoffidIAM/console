package com.soffid.iam.sync.engine;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Map;

import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.SoffidObjectType;
import com.soffid.iam.sync.engine.intf.GetObjectResults;
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

    public abstract com.soffid.iam.sync.engine.intf.DebugTaskResults debugTask (TaskHandler task) throws InternalErrorException;

	public abstract void doReconcile (ScheduledTask task, PrintWriter out);

	public abstract void doReconcile (String account, PrintWriter out, boolean debug) throws Exception;

	public abstract void doAuthoritativeImport (ScheduledTask task, PrintWriter out);

	public abstract void doImpact (ScheduledTask task, PrintWriter out);

	public abstract GetObjectResults  getSoffidObject(String systemName, SoffidObjectType type, String object1,
			String object2) throws Exception;

	public abstract GetObjectResults  getNativeObject(String systemName, SoffidObjectType type, String object1,
			String object2) throws Exception;

	public abstract Collection<Map<String, Object>> invoke(String verb, String command,
			Map<String, Object> params) throws Exception ;
	/**
	 * Executes a single action
	 * 
	 * @return false if a delay should be performed
	 * 
	 * @throws InternalErrorException
	 */
	public abstract boolean runStep () throws InternalErrorException;

	public abstract String parseKerberosToken(String domain, String serviceName, byte[] keytab, byte[] token) throws Exception ;

}
