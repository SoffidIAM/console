//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.servei;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service ( serverOnly=true,
	 serverPath="/seycon/LogonService",
	 translatedName="LogonService",
	 serverRole="agent",
	 translatedPackage="com.soffid.iam.sync.service")
@Depends ({es.caib.seycon.ng.servei.InternalPasswordService.class,
	es.caib.seycon.ng.sync.servei.TaskQueue.class,
	es.caib.seycon.ng.model.DominiUsuariEntity.class,
	es.caib.seycon.ng.model.DominiContrasenyaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.ContrasenyaEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.sync.servei.TaskGenerator.class,
	es.caib.seycon.ng.servei.SessioService.class,
	es.caib.seycon.ng.sync.servei.ServerService.class,
	es.caib.seycon.ng.model.XarxaEntity.class,
	es.caib.seycon.ng.servei.XarxaService.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.servei.PasswordService.class})
public abstract class LogonService {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public void changePassword(
		java.lang.String user, 
		@Nullable java.lang.String domain, 
		java.lang.String oldPassword, 
		java.lang.String newPassword)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.BadPasswordException, es.caib.seycon.ng.exception.InvalidPasswordException, java.rmi.RemoteException, es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void propagatePassword(
		java.lang.String user, 
		@Nullable java.lang.String domain, 
		java.lang.String password)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.rmi.RemoteException, es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean mustChangePassword(
		java.lang.String user, 
		@Nullable java.lang.String domain)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.rmi.RemoteException, es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Challenge requestChallenge(
		int type, 
		java.lang.String user, 
		@Nullable java.lang.String domain, 
		java.lang.String host, 
		@Nullable java.lang.String clientHost, 
		int cardSupport)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.rmi.RemoteException, es.caib.seycon.ng.exception.LogonDeniedException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Sessio responseChallenge(
		es.caib.seycon.ng.comu.Challenge result)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.rmi.RemoteException, es.caib.seycon.ng.exception.LogonDeniedException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PasswordValidation validatePassword(
		java.lang.String user, 
		@Nullable java.lang.String passwordDomain, 
		java.lang.String password)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.rmi.RemoteException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean validatePIN(
		java.lang.String user, 
		java.lang.String pin)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.rmi.RemoteException, es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getPasswordPolicy(
		java.lang.String user, 
		@Nullable java.lang.String domain)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
