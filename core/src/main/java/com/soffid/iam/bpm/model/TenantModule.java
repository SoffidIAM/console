package com.soffid.iam.bpm.model;

import org.jbpm.module.exe.ModuleInstance;

public class TenantModule extends ModuleInstance {
	Long tenantId;
	
	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

}
