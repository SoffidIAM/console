//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.servei;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service ( serverOnly=true,
	 serverPath="/SEU/LogonService",
	 serverRole="SEU_CONSOLE",
	 translatedPackage="com.soffid.iam.sync.service",
	 translatedName="ConsoleLogonService")
@Depends ({LogonService.class})
public abstract class ConsoleLogonService {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PasswordValidation validatePassword(
		java.lang.String user, 
		@Nullable java.lang.String passwordDomain, 
		java.lang.String password)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.rmi.RemoteException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
