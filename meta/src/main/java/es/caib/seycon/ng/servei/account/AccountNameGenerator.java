//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei.account;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.DominiUsuariEntity;

import org.springframework.transaction.annotation.Transactional;

@Service ( internal=true, translatedName="AccountNameGenerator",
	translatedPackage="com.soffid.iam.service.account")
public abstract class AccountNameGenerator {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getAccountName(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DispatcherEntity dispatcher,
		DominiUsuariEntity userDomain)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean needsAccount(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DispatcherEntity dispatcher)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
}
