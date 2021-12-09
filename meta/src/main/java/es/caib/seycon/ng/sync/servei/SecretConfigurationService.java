//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.servei;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.ServerInstanceEntity;

import org.springframework.transaction.annotation.Transactional;

@Service ( internal=true,
	 serverOnly=true,
	 translatedName="SecretConfigurationService",
	 translatedPackage="com.soffid.iam.sync.service")
@Depends ({es.caib.seycon.ng.model.ServerEntity.class, ServerInstanceEntity.class})
public abstract class SecretConfigurationService {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.security.PrivateKey getPrivateKey()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Server> getAllServers()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Server getCurrentServer()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void changeAuthToken()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean validateAuthToken(
		java.lang.String token)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
}
