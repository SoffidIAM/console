package com.soffid.iam.web.bpm;

import java.net.URLEncoder;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.service.ejb.BpmEngine;

import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskInstance;

public class BPMApplication {
    public static BpmEngine getEngine() throws CreateException, NamingException {
    	return EJBLocator.getBpmEngine();
    }

    
    public static String getTaskURL (TaskInstance task) 
    {
    	if (task.isDummyTask())
        	return "/wf/task.zul?def="+task.getProcessDefinition(); //$NON-NLS-1$
    	else
    		return "/wf/task.zul?id="+task.getId(); //$NON-NLS-1$
    }
    
    public static String getTaskURL (com.soffid.iam.bpm.api.TaskInstance task) 
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

    public static String getProcessURL (com.soffid.iam.bpm.api.ProcessInstance process) 
    {
    	return "/wf/process.zul?id="+process.getId(); //$NON-NLS-1$
    }

}
