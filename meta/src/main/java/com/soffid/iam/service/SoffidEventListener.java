//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.service;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service ( internal=true,
	 translatedName="SoffidEventListener",
	 translatedPackage="com.soffid.iam.service")
public abstract class SoffidEventListener {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public void onUserChange(
		es.caib.seycon.ng.model.UsuariEntity user)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public void onGrant(
		es.caib.seycon.ng.model.RolAccountEntity grant)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public void onRevoke(
		es.caib.seycon.ng.model.RolAccountEntity grant)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
}
