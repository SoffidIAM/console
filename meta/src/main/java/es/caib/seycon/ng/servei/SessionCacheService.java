//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import org.springframework.transaction.annotation.Transactional;

import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import roles.Tothom;

@Depends({ConfiguracioService.class})
@Service (grantees={Tothom.class},
		simple=true,
		translatedName="SessionCacheService",
		translatedPackage="com.soffid.iam.service")
public abstract class SessionCacheService {
	@Description("Creates a new session object")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public String createSession( )
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation
	@Description("Retrieves current session object id")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public String getCurrentSessionId( )
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}
	
	@Operation
	@Description("Creates a new session object")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public String setSession( String sessionId )
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation
	@Description("Clears session object")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public String clearSession()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	
	@Operation(translated="getObject")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Object getObject(
		java.lang.String tag)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Operation(translated="putObject")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void putObject(
		java.lang.String tag, 
		java.lang.Object value)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
}
