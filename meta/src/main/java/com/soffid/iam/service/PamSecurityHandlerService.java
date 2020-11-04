package com.soffid.iam.service;

import java.util.Date;

import com.soffid.iam.api.PamSecurityCheck;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Service;

import es.caib.bpm.servei.BpmEngine;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.UserAccountEntity;

@Service(internal = true)
@Depends({BpmEngine.class, UserAccountEntity.class})
public class PamSecurityHandlerService {
	public void checkPermission (AccountEntity account, String action)
		{ }

	public PamSecurityCheck getObligations (AccountEntity account, String action)
		{ return null; }

	public PamSecurityCheck checkPermissionImpl (AccountEntity account, String action)
		{ return null; }
}
