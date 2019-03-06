//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import org.springframework.transaction.annotation.Transactional;

import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

@Service ( translatedName="SessionService",
	translatedPackage="com.soffid.iam.service",
	serverPath="/seycon/SessioService", serverRole="agent")
@Depends ({es.caib.seycon.ng.model.RegistreAccesEntity.class,
	es.caib.seycon.ng.model.ServeiEntity.class,
	es.caib.seycon.ng.model.SessioEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class})
public abstract class SessioService {

	@Operation (translated="registerSession")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Sessio registerSessio(
		java.lang.String codiUsuari, 
		java.lang.String nomMaquina, 
		@Nullable java.lang.String nomAquinaClient, 
		int port, 
		@Nullable java.lang.String key,
		@Nullable String authenticationMethod)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="registerWebSession")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Sessio registraSessioWeb(
		java.lang.String codiUsuari, 
		java.lang.String nomMaquina, 
		java.lang.String nomMaquinaClient, 
		java.lang.String url,
		@Nullable String authenticationMethod)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Operation(translated="getSession")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Sessio getSession(
		long id, 
		java.lang.String key)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="sessionKeepAlive")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sessioKeepAlive(
		es.caib.seycon.ng.comu.Sessio session)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation (translated="destroySession")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void destroySessio(
		es.caib.seycon.ng.comu.Sessio sessio)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="updateTransientKey")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String updateTransientKey(
		long id, 
		java.lang.String key)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="cleanTransientKey")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void cleanTransientKey(
		long id, 
		java.lang.String key)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="getActiveSessions")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Sessio> getActiveSessions()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="getActiveSessions")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Sessio> getActiveSessions(
		long idUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getSessionByHost")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Sessio getSessioByHost(
		long id, 
		java.lang.String hostIp)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
