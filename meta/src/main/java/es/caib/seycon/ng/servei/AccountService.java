//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.Usuari;

import org.springframework.transaction.annotation.Transactional;

@Service (translatedName="AccountService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.AccountEntity.class,
	es.caib.seycon.ng.model.UserAccountEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.servei.UsuariService.class,
	es.caib.seycon.ng.model.AccountAccessEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.RolAccountEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	es.caib.seycon.ng.model.DominiUsuariEntity.class,
	es.caib.seycon.ng.servei.DispatcherService.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.TipusUsuariEntity.class,
	es.caib.seycon.ng.servei.AplicacioService.class,
	es.caib.seycon.ng.servei.InternalPasswordService.class,
	es.caib.seycon.ng.model.ServerEntity.class,
	es.caib.seycon.ng.servei.DominiUsuariService.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class})
public abstract class AccountService {

	@Operation ( grantees={Roles.user_create.class,Roles.user_update.class,Roles.user_query.class},
			translated="listUserAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.UserAccount> listUserAccounts(
		es.caib.seycon.ng.comu.Usuari usuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.user_query.class},
			translated="findUsersAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.UserAccount> findUserAccounts(
		java.lang.String userName, 
		java.lang.String dispatcherName)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.user_create.class,Roles.user_update.class},
			translated="createAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.UserAccount createAccount(
		es.caib.seycon.ng.comu.Usuari usuari, 
		es.caib.seycon.ng.comu.Dispatcher dispatcher, 
		@Nullable java.lang.String name)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.NeedsAccountNameException, es.caib.seycon.ng.exception.AccountAlreadyExistsException {
	 return null;
	}
	@Operation ( grantees={Roles.user_create.class,Roles.user_update.class},
			translated="removeAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void removeAccount(
		es.caib.seycon.ng.comu.UserAccount account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={Roles.agent_query.class,Roles.agent_update.class},
			translated="listNonUserAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.Account> listNonUserAccounts(
		es.caib.seycon.ng.comu.Dispatcher dispatcher, 
		@Nullable java.lang.String nom)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.agent_update.class,Roles.account_create.class},
			translated="createAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Account createAccount(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.AccountAlreadyExistsException {
	 return null;
	}
	@Operation ( grantees={Roles.agent_update.class,Roles.account_update.class},
			translated="updateAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateAccount(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.AccountAlreadyExistsException {
	}
	@Operation ( grantees={Roles.user_create.class,Roles.user_query.class,
			Roles.user_update.class,Roles.agent_query.class,
			Roles.agent_update.class},
			translated="findAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Account findAccount(
		java.lang.String accountName, 
		java.lang.String dispatcherName)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.agent_update.class,Roles.actor_delete.class},
			translated="removeAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void removeAccount(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation (translated="generateUserAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void generateUserAccounts(
		java.lang.String user)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={Roles.user_query.class},
			translated="findUserAccountsByDomain")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.UserAccount> findUserAccountsByDomain(
		java.lang.String user, 
		java.lang.String passwordDomain)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.agent_update.class},
			translated="gessAccountName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String gessAccountName(
		java.lang.String userName, 
		java.lang.String dispatcherName)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.agent_update.class},
			translated="renameAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void renameAccount(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.AccountAlreadyExistsException {
	}
	@Operation ( grantees={Roles.account_query.class},
			translated="findAccountsByCriteria")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.Account> findAccountsByCriteria(
		es.caib.seycon.ng.comu.AccountCriteria criteria)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="getAccountUsers")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.String> getAccountUsers(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.Tothom.class},
			translated="getUserGrantedAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Account> getUserGrantedAccounts(
		es.caib.seycon.ng.comu.Usuari usuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="updateAccountLastUpdate")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateAccountLastUpdate(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation (translated="updateAccountPasswordDate")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateAccountPasswordDate(
		es.caib.seycon.ng.comu.Account account, 
		@Nullable java.lang.Long passwordTerm)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={Roles.Tothom.class},
			translated="queryAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password queryAccountPassword(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.Tothom.class},
			translated="setAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void setAccountPassword(
		es.caib.seycon.ng.comu.Account account, 
		es.caib.seycon.ng.comu.Password password)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	@Operation ( grantees={Roles.Tothom.class},
			translated="setHPAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void setHPAccountPassword(
		es.caib.seycon.ng.comu.Account account, 
		es.caib.seycon.ng.comu.Password password, 
		java.util.Date untilDate, 
		boolean force)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	
	@Operation ( grantees={Roles.Tothom.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void checkinHPAccount(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}


	@Operation ( grantees={Roles.Tothom.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public Usuari getHPAccountOwner(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation ( grantees={Roles.Tothom.class},
			translated="getUserAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.UserAccount> getUserAccounts(
		es.caib.seycon.ng.comu.Usuari usuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="load")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Account load(
		java.lang.Long identifier)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={Roles.user_query.class,Roles.account_query.class,
			Roles.Tothom.class},
			translated="isUpdatePending")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isUpdatePending(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Operation (translated="updateAccountPasswordDate2")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateAccountPasswordDate2(
		es.caib.seycon.ng.comu.Account account, 
		@Nullable java.util.Date expirationDate)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation (translated="findAccountsNearToExpire")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.Account> findAccountsNearToExpire(
		java.util.Date currentDate, 
		java.util.Date limitDate, 
		@Nullable java.util.Collection<es.caib.seycon.ng.comu.AccountType> accTypes, 
		@Nullable java.util.Collection<es.caib.seycon.ng.comu.TipusUsuari> userTypes)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
