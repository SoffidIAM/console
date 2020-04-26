package es.caib.bpm.toolkit;

import java.net.URLEncoder;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalObject;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;

import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskInstance;

public class BPMApplication {
    public static BpmEngine getEngine() throws CreateException, NamingException {
    	Execution e = Executions.getCurrent();
    	Session sesion = e.getDesktop().getSession();
        return EJBContainer.getEJBContainer(sesion).getEngine();
    }

    
    public static String getTaskURL (TaskInstance task) 
    {
    	if (task.isDummyTask())
        	return "/wf/task.zul?def="+task.getProcessDefinition(); //$NON-NLS-1$
    	else
    		return "/wf/task.zul?id="+task.getId(); //$NON-NLS-1$
    }
    
    public static String getMassiveTaskURL (TaskInstance task, String transition) 
    {
    	return "/wf/task.zul?id="+task.getId()+"&massiveExecutionTransition="+URLEncoder.encode(transition); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    public static String getProcessURL (ProcessInstance process) 
    {
    	return "/wf/process.zul?id="+process.getId(); //$NON-NLS-1$
    }

}
