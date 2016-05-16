package com.soffid.iam.deployer;

public interface DeployerService {

	public void redeploy() throws Exception;

	public void setFailSafe(boolean failSafe);

	public boolean isFailSafe();

}
