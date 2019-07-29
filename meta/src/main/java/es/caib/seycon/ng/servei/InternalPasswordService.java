//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.sync.servei.ConsoleLogonService;

@Service ( internal=true,
		translatedName="InternalPasswordService",
		translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.ContrasenyaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.sync.servei.TaskQueue.class,
	ConsoleLogonService.class,
	es.caib.seycon.ng.model.PoliticaContrasenyaEntity.class,
	es.caib.seycon.ng.model.DominiContrasenyaEntity.class,
	es.caib.seycon.ng.model.DominiUsuariEntity.class,
	es.caib.seycon.ng.model.TipusUsuariEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	es.caib.seycon.ng.model.AccountPasswordEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class,
	TenantEntity.class,
	SeyconServerService.class})

public abstract class InternalPasswordService {

	@Operation(translated="checkPolicy")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PolicyCheckResult checkPolicy(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.PoliticaContrasenyaEntity politica, 
		es.caib.seycon.ng.comu.Password password)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="checkAccountPolicy")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PolicyCheckResult checkAccountPolicy(
		es.caib.seycon.ng.model.AccountEntity account, 
		es.caib.seycon.ng.comu.Password password)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="storePassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void storePassword(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity passwordDomain, 
		es.caib.seycon.ng.comu.Password password, 
		boolean mustChange)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="storeAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void storeAccountPassword(
		java.lang.String account, 
		java.lang.String dispatcher, 
		java.lang.String password, 
		boolean mustChange, 
		@Nullable java.util.Date expirationDate)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="storePassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void storePassword(
		java.lang.String user, 
		java.lang.String passwordDomain, 
		java.lang.String password, 
		boolean mustChange)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="storeAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void storeAccountPassword(
		es.caib.seycon.ng.model.AccountEntity account, 
		es.caib.seycon.ng.comu.Password password, 
		boolean mustChange, 
		@Nullable java.util.Date expirationDate)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="disableExpiredPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void disableExpiredPasswords()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="disableUntrustedPasswords")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void disableUntrustedPasswords()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="checkPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PasswordValidation checkPassword(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity passwordDomain, 
		es.caib.seycon.ng.comu.Password password, 
		boolean checkTrusted, 
		boolean checkExpired)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="checkAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PasswordValidation checkAccountPassword(
		es.caib.seycon.ng.model.AccountEntity account, 
		es.caib.seycon.ng.comu.Password password, 
		boolean checkTrusted, 
		boolean checkExpired)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="confirmPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void confirmPassword(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity passwordDomain, 
		es.caib.seycon.ng.comu.Password password)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="confirmAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void confirmAccountPassword(
		es.caib.seycon.ng.model.AccountEntity account, 
		es.caib.seycon.ng.comu.Password password)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="checkPin")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean checkPin(
		es.caib.seycon.ng.model.UsuariEntity user, 
		java.lang.String pin)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Operation(translated="isOldPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isOldPassword(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity passwordDomain, 
		es.caib.seycon.ng.comu.Password password)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Operation(translated="isOldAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isOldAccountPassword(
		es.caib.seycon.ng.model.AccountEntity account, 
		es.caib.seycon.ng.comu.Password password)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Operation(translated="generateNewPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password generateNewPassword(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity passDomain, 
		boolean mustBeChanged)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="generateNewAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password generateNewAccountPassword(
		es.caib.seycon.ng.model.AccountEntity account, 
		boolean mustBeChanged)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="getPasswordsStatus")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.EstatContrasenya getPasswordsStatus(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity domini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="getAccountPasswordsStatus")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.EstatContrasenya getAccountPasswordsStatus(
		es.caib.seycon.ng.model.AccountEntity account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="getExpiredPasswords")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.EstatContrasenya> getExpiredPasswords(
		java.util.Date desde, 
		java.util.Date finsa, 
		es.caib.seycon.ng.model.TipusUsuariEntity tipusUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="generateFakePassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password generateFakePassword(
		@Nullable es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity passDomain)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="generateFakeAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password generateFakeAccountPassword(
		@Nullable es.caib.seycon.ng.model.AccountEntity account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="storeAndForwardPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void storeAndForwardPassword(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity passwordDomain, 
		es.caib.seycon.ng.comu.Password password, 
		boolean mustChange)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="storeAndForwardAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void storeAndForwardAccountPassword(
		es.caib.seycon.ng.model.AccountEntity account, 
		es.caib.seycon.ng.comu.Password password, 
		boolean mustChange, 
		@Nullable java.util.Date expirationDate)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="isAccountPasswordExpired")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isAccountPasswordExpired(
		es.caib.seycon.ng.model.AccountEntity account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Operation(translated="isPasswordExpired")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isPasswordExpired(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity passwordDomain)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Operation(translated="existsPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean existsPassword(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity passwordDomain)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Operation(translated="existsAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean existsAccountPassword(
		es.caib.seycon.ng.model.AccountEntity account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Operation(translated="getDefaultDispatcher")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getDefaultDispatcher()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="checkPolicy")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PolicyCheckResult checkPolicy(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity passwordDomain, 
		es.caib.seycon.ng.comu.Password password)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="getPolicyDescription")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getPolicyDescription(
		es.caib.seycon.ng.model.PoliticaContrasenyaEntity politica)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="checkPolicy")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PolicyCheckResult checkPolicy(
		es.caib.seycon.ng.model.PoliticaContrasenyaEntity policy, 
		es.caib.seycon.ng.comu.Password password)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="updateExpiredPasswords")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean updateExpiredPasswords(
		es.caib.seycon.ng.model.UsuariEntity usuari, 
		boolean externalAuth)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Operation(translated="enumExpiredPasswords")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.model.DominiContrasenyaEntity> enumExpiredPasswords(
		es.caib.seycon.ng.model.UsuariEntity usuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="storeAndSynchronizePassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void storeAndSynchronizePassword(
		es.caib.seycon.ng.model.UsuariEntity user, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity passwordDomain, 
		es.caib.seycon.ng.comu.Password password, 
		boolean mustChange)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="storeAndSynchronizeAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void storeAndSynchronizeAccountPassword(
		es.caib.seycon.ng.model.AccountEntity account, 
		es.caib.seycon.ng.comu.Password password, 
		boolean mustChange, 
		@Nullable java.util.Date expirationDate)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="storeAndForwardPasswordById")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void storeAndForwardPasswordById(
		long user, 
		long passwordDomain, 
		es.caib.seycon.ng.comu.Password password, 
		boolean mustChange)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="storeAndForwardAccountPasswordById")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void storeAndForwardAccountPasswordById(
		long account, 
		es.caib.seycon.ng.comu.Password password, 
		boolean mustChange, 
		@Nullable java.util.Date expirationDate)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="getPasswordsStatusById")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.EstatContrasenya getPasswordsStatusById(
		long user, 
		long domini)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="getAccountPasswordsStatusById")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.EstatContrasenya getAccountPasswordsStatusById(
		long account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
}
