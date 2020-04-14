package com.soffid.iam.web.config;

import org.zkoss.zk.ui.Page;

import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;

public class ParameterHandler extends FrameHandler {
	private boolean isMaster;
	private boolean canCreateParameter;
	private boolean canUpdateParameter;
	private boolean canDeleteParameter;
	private boolean canQueryParameter;

	public ParameterHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();

		isMaster = com.soffid.iam.utils.Security.getCurrentTenantName().equals ( masterTenant.getName() );
		canCreateParameter = Security.isUserInRole("parameter:create");
		canUpdateParameter = Security.isUserInRole("parameter:update");
		canDeleteParameter = Security.isUserInRole("parameter:delete");
		canQueryParameter = Security.isUserInRole("parameter:query");;
		
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		getNamespace().setVariable("isMaster", isMaster, true);
		getNamespace().setVariable("canCreateParameter", canCreateParameter, true);
		getNamespace().setVariable("canUpdateParameter", canUpdateParameter, true);
		getNamespace().setVariable("canDeleteParameter", canDeleteParameter, true);
		getNamespace().setVariable("canQueryParameter", canQueryParameter, true);
	}
		

	public void onChangeDades() {
	}
	
}
