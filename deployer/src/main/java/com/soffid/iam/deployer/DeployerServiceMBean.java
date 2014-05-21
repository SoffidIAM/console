package com.soffid.iam.deployer;

import java.util.List;

import org.jboss.system.ServiceMBean;

public interface DeployerServiceMBean 
	extends org.jboss.deployment.scanner.DeploymentScannerMBean 
{ 

	// Operations

	public void start() throws Exception;

	public void stop();

	public void redeploy() throws Exception;

	public abstract List<String> getCoreModules();

	public abstract List<String> getInitClasses();
	
	
	public boolean isFailSafe ();
	public void setFailSafe(boolean failSafe);

}
