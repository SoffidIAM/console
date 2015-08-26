//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.servei;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service ( internal=true,
	 serverOnly=true,
	 serverRole="agent",
	 translatedName="LogCollectorService",
	 translatedPackage="com.soffid.iam.sync.service")
@Depends ({es.caib.seycon.ng.model.RegistreAccesEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.ServeiEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class})
public abstract class LogCollectorService {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Date getLastLogEntryDate(
		java.lang.String dispatcher)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerLogon(
		@Nullable java.lang.String dispatcher, 
		java.lang.String sessionId, 
		java.util.Date date, 
		java.lang.String user, 
		java.lang.String server, 
		@Nullable java.lang.String client, 
		java.lang.String protocol, 
		@Nullable java.lang.String info)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException, es.caib.seycon.ng.exception.UnknownHostException, es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerFailedLogon(
		java.lang.String dispatcher, 
		java.lang.String sessionId, 
		java.util.Date date, 
		java.lang.String user, 
		java.lang.String server, 
		@Nullable java.lang.String client, 
		java.lang.String protocol, 
		@Nullable java.lang.String info)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException, es.caib.seycon.ng.exception.UnknownHostException, es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerLogoff(
		@Nullable java.lang.String dispatcher, 
		java.lang.String sessionId, 
		java.util.Date date, 
		java.lang.String user, 
		java.lang.String server, 
		@Nullable java.lang.String client, 
		java.lang.String protocol, 
		@Nullable java.lang.String info)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownHostException, es.caib.seycon.ng.exception.UnknownUserException, es.caib.seycon.ng.exception.InternalErrorException {
	}
}
