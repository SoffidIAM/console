package com.soffid.iam.bpm.model;

import org.jbpm.module.def.ModuleDefinition;
import org.jbpm.module.exe.ModuleInstance;

import com.soffid.iam.utils.Security;

public class TenantModuleDefinition extends ModuleDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Long tenantId;
	
	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public ModuleInstance createInstance() {
		TenantModule ti = new TenantModule ();
		ti.setTenantId(getTenantId());
		ti.setInitiator(Security.getCurrentUser());
		return ti;
	}

}
