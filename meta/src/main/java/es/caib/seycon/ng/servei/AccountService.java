//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.model.AccountAttributeEntity;
import com.soffid.iam.model.AccountMetadataEntity;
import com.soffid.iam.model.JumpServerGroupEntity;
import com.soffid.iam.model.VaultFolderEntity;
import com.soffid.iam.service.AsyncRunnerService;
import com.soffid.iam.service.PamSecurityHandlerService;
import com.soffid.iam.service.VaultService;
import com.soffid.iam.service.impl.AttributeValidationService;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.bpm.servei.BpmEngine;
import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.PasswordValidation;
import es.caib.seycon.ng.comu.SeyconAgentTaskLog;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import roles.account_attribute_query;
import roles.account_attribute_update;
import roles.account_query;

@Service (translatedName="AccountService", serverPath="/seycon/accountService", serverRole="agent",
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
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	AutoritzacioService.class,
	AccountAttributeEntity.class, AccountMetadataEntity.class,
	AuditoriaService.class,
	BpmEngine.class,
	VaultService.class,
	AsyncRunnerService.class,
	AttributeValidationService.class,
	VaultFolderEntity.class,
	JumpServerGroupEntity.class,
	PamSecurityHandlerService.class})
public abstract class AccountService {

	/// listUserAccounts

	@Description ("Gets the accounts bound to this user")
	@Operation ( grantees={roles.user_create.class,roles.user_update.class,roles.user_query.class},
			translated="listUserAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.UserAccount> listUserAccounts(
		es.caib.seycon.ng.comu.Usuari usuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}


	////////// findUserAccounts
	@Description("Gets the accounts for a user on a single system name")
	@Operation ( grantees={roles.user_query.class},
			translated="findUsersAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.UserAccount> findUserAccounts(
		java.lang.String userName, 
		java.lang.String dispatcherName)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	////////// findUserAccounts
	@Description("Gets the non single-user accounts for a user")
	@Operation ( grantees={roles.user_query.class},
			translated="findSharedAccountsByUser")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.Account> findSharedAccountsByUser(String userName) {
	 return null;
	}
	
	////////// findUserAccounts
	@Description("Gets the non single-user accounts history for a user")
	@Operation ( grantees={roles.user_query.class},
			translated="findSharedAccountsHistoryByUser")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.AccountHistory> findSharedAccountsHistoryByUser(String userName) {
	 return null;
	}
	
	/////////////////
	@Operation ( grantees={roles.user_create.class,roles.user_update.class},
			translated="createAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.UserAccount createAccount(
		es.caib.seycon.ng.comu.Usuari usuari, 
		es.caib.seycon.ng.comu.Dispatcher dispatcher, 
		@Nullable java.lang.String name)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.NeedsAccountNameException, es.caib.seycon.ng.exception.AccountAlreadyExistsException {
	 return null;
	}
	
	@Operation
	@Transactional(readOnly = true, noRollbackFor={java.lang.Exception.class})
	public String predictAccountName(Long userId, String dispatcher, Long domainId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.NeedsAccountNameException, es.caib.seycon.ng.exception.AccountAlreadyExistsException {
	 return null;
	}
	
	@Operation ( grantees={roles.user_create.class,roles.user_update.class},
			translated="removeAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void removeAccount(
		es.caib.seycon.ng.comu.UserAccount account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	@Description("Search shareds account on a system")
	@Operation ( grantees={roles.agent_query.class,roles.agent_update.class},
			translated="listNonUserAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.Account> listNonUserAccounts(
		es.caib.seycon.ng.comu.Dispatcher dispatcher, 
		@Nullable java.lang.String nom)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Description ("Creates a shared account")
	@Operation ( grantees={roles.agent_update.class,roles.account_create.class},
			translated="createAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Account createAccount(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.AccountAlreadyExistsException {
	 return null;
	}
	
	@Description ("Creates a shared account, including its attributes")
	@Operation ( grantees={roles.agent_update.class,roles.account_create.class},
			translated="createAccount2")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Account createAccount2(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.AccountAlreadyExistsException {
	 return null;
	}
	
	@Description("Updates a shared account")
	@Operation ( grantees={roles.agent_update.class,roles.account_update.class},
			translated="updateAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public Account updateAccount(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.AccountAlreadyExistsException {
		return null;
	}
	
	@Description("Updates a shared account, including its attributes")
	@Operation ( grantees={roles.agent_update.class,roles.account_update.class},
			translated="updateAccount2")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public Account updateAccount2(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.AccountAlreadyExistsException {
		return null;
	}
	
	@Description ("Finds an account by name and system")
	@Operation ( grantees={roles.user_create.class,roles.user_query.class,
			roles.user_update.class,roles.agent_query.class,
			roles.agent_update.class},
			translated="findAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Account findAccount(
		java.lang.String accountName, 
		java.lang.String dispatcherName)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Description ("Finds an account by id")
	@Operation ( grantees={roles.user_create.class,roles.user_query.class,
			roles.user_update.class,roles.agent_query.class,
			roles.agent_update.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Account findAccountById(long id)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	

	@Description("Removes a shared account")
	@Operation ( grantees={roles.agent_update.class,roles.account_delete.class},
			translated="removeAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void removeAccount(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	//////////
	@Description ("Creates user accounts depending on user domain rules")
	@Operation (translated="generateUserAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void generateUserAccounts(
		java.lang.String user)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	//////////////
	@Description ("Find the user accounts for a user and password domain")
	@Operation ( grantees={roles.user_query.class},
			translated="findUserAccountsByDomain")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.UserAccount> findUserAccountsByDomain(
		java.lang.String user, 
		java.lang.String passwordDomain)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	/////////////
	@Description("Generates the account name for a user domain")
	@Transactional(noRollbackFor={java.lang.Exception.class})
	public java.lang.String guessAccountNameForDomain(
		java.lang.String userName, 
		java.lang.String domainName)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	/////////////
	@Description("Generates the account name for a user and system")
	@Operation ( grantees={roles.agent_update.class},
			translated="guessAccountName")
	@Transactional(noRollbackFor={java.lang.Exception.class})
	public java.lang.String gessAccountName(
		java.lang.String userName, 
		java.lang.String dispatcherName)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Description("Checks if an account should be created for a user and system")
	@Operation ( grantees={roles.agent_update.class},
			translated="needsAccount")
	@Transactional(noRollbackFor={java.lang.Exception.class})
	public boolean needsAccount(
		java.lang.String userName, 
		java.lang.String dispatcherName)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	
	//////////
	@Description("Renames a user account")
	@Operation ( grantees={roles.agent_update.class},
			translated="renameAccount")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void renameAccount(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.AccountAlreadyExistsException {
	}
	@Operation ( grantees={roles.account_query.class},
			translated="findAccountsByCriteria")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<es.caib.seycon.ng.comu.Account> findAccountsByCriteria(
		es.caib.seycon.ng.comu.AccountCriteria criteria)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	//////////////////////////////
	
	@Description("Gets the users than currently owns the account. \n"
			+ "For single user accounts, it's the single user.\n"
			+ "For privileged and shared accounts they are de acl members")
	@Operation (translated="getAccountUsers")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.String> getAccountUsers(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	//////////////////////////////
	
	@Description("Gets the users than currently matches the desired access level for the account. \n"
			+ "For single user accounts, it's the single user.\n"
			+ "For privileged and shared accounts they are de acl members")
	@Operation (translated="getAccountUsers")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.String> getAccountUsers(
		es.caib.seycon.ng.comu.Account account, AccountAccessLevelEnum level)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	
	@Description("Gets the users than currently can use the account from console")
	@Operation ( grantees={roles.Tothom.class},translated="getUserGrantedAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Account> getUserGrantedAccounts(
		es.caib.seycon.ng.comu.Usuari usuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Description("Gets the users than currently can use the account with the desired access level")
	@Operation ( grantees={roles.Tothom.class},translated="getUserGrantedAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Account> getUserGrantedAccounts(
		es.caib.seycon.ng.comu.Usuari usuari, AccountAccessLevelEnum level)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	///////////////////
	@Description("Updates the accounts last update property")
	@Operation (translated="updateAccountLastUpdate")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateAccountLastUpdate(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	////
	@Description("Updates the account password property")
	@Operation (translated="updateAccountPasswordDate")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateAccountPasswordDate(
		es.caib.seycon.ng.comu.Account account, 
		@Nullable java.lang.Long passwordTerm)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	///
	@Description ("Gets the account password")
	@Operation ( grantees={roles.Tothom.class},
			translated="queryAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password queryAccountPassword(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Description ("Gets the account password bypassing passowrd policy. Used for SSO")
	public Password queryAccountPasswordBypassPolicy (long accountId, AccountAccessLevelEnum level)
	{
		return null;
	}

	@Description ("Checks if there is a password available")
	@Operation ( grantees={roles.Tothom.class} )
	public boolean isAccountPasswordAvailable (long accountId)
	{
		return false;
	}

	///
	@Description("Sets the account password")
	@Operation ( grantees={roles.Tothom.class},
			translated="setAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void setAccountPassword(
		es.caib.seycon.ng.comu.Account account, 
		es.caib.seycon.ng.comu.Password password)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	@Description("Sets the account temporary password")
	@Operation ( grantees={roles.Tothom.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void setAccountTemporaryPassword(
		es.caib.seycon.ng.comu.Account account, 
		es.caib.seycon.ng.comu.Password password)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	@Description("Generates a temporary password for the account")
	@Operation ( grantees={roles.Tothom.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public Password generateAccountTemporaryPassword(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException { return null;
	}

	///
	@Description ("Sets the high privileged account password. Returns false if the action is waiting for approval")
	@Operation ( grantees={roles.Tothom.class},
			translated="setHPAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean setHPAccountPassword(
		es.caib.seycon.ng.comu.Account account, 
		es.caib.seycon.ng.comu.Password password, 
		java.util.Date untilDate, 
		boolean force)
		throws es.caib.seycon.ng.exception.InternalErrorException {
		return false;
	}


	void registerAccountReservationProcess (Account account, String user, Long processId) {}
	
	void grantAcccountToUser(Account account, String user, Long processId, Date until) {}

	//
	@Description ("Unlocks a high privileged account")
	@Operation ( grantees={roles.Tothom.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void checkinHPAccount(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Description("Unlocks expired high privileged accounts")
	public void checkinHPAccounts() {}
	
	@Description("Gets the current privileged account owner")
	@Operation ( grantees={roles.Tothom.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public Usuari getHPAccountOwner(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Description("Gets the account a user has")
	@Operation ( grantees={roles.Tothom.class},
			translated="getUserAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.UserAccount> getUserAccounts(
		es.caib.seycon.ng.comu.Usuari usuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	
	@Description("Gets an account by id")
	@Operation (translated="load")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Account load(
		java.lang.Long identifier)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Description("Identifies if there is any pending change to apply")
	@Operation ( grantees={roles.user_query.class,roles.account_query.class,
			roles.Tothom.class},
			translated="isUpdatePending")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isUpdatePending(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	
	@Description("Identifies if there is any pending change. 0 means no change pending, 1 task is on hald, 2 means synchronization in progress, 3 means error")
	@Operation ( grantees={roles.user_query.class,roles.account_query.class,
			roles.Tothom.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public int isUpdatePendingExtended(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return 0;
	}
	
	public Collection<SeyconAgentTaskLog> getActiveTasks(Account account) {
		return null;
	}


	@Description("Updates account password set and expiration")
	@Operation (translated="updateAccountPasswordDate2")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateAccountPasswordDate2(
		es.caib.seycon.ng.comu.Account account, 
		@Nullable java.util.Date expirationDate)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Description("Gets accounts near to expire")
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
	
	@Description("Gets account attributes")
	@Operation (grantees={account_attribute_query.class})
	public List<DadaUsuari> getAccountAttributes (Account acc) {
		return null;
	}

	@Description("Creates an account attributes")
	@Operation (grantees={account_attribute_update.class})
	public DadaUsuari createAccountAttribute (DadaUsuari attribute) {
		return null;
	}

	@Description("Updates an account attributes")
	@Operation (grantees={account_attribute_update.class})
	public DadaUsuari updateAccountAttribute (DadaUsuari attribute) {
		return null;
	}

	@Description("Deletes an account attributes")
	@Operation (grantees={account_attribute_update.class})
	public void removeAccountAttribute (DadaUsuari attribute) {
	}

	@Operation(grantees = { account_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Account> findAccountByJsonQuery(
			@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { account_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public PagedResult<Account> findAccountByJsonQuery(
			@Nullable String query,
			@Nullable Integer first, @Nullable Integer num)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { account_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<es.caib.seycon.ng.comu.Account> findAccountByJsonQueryAsync(
			@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.account_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Account> findAccountByText(
			@Nullable String text)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.account_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<es.caib.seycon.ng.comu.Account> findAccountByTextAsync(
			@Nullable String text)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}


	@Operation(grantees = { roles.account_query.class })
	public PagedResult<Account> findAccountByTextAndJsonQuery(
			@Nullable String text,
			@Nullable String jsonQuery,
			@Nullable Integer start, @Nullable Integer pageSize) {
		return null;
	}

	@Operation(grantees = { roles.account_query.class })
	public AsyncList<Account> findAccountByTextAndJsonQueryAsync(
			@Nullable String text,
			@Nullable String jsonQuery) {
		return null;
	}

	@Operation
	@Transactional(readOnly=true)
	public java.util.Collection<String> findAccountNames(String system)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation
	public void synchronizeAccount(String accountName, String system)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	@Operation(grantees= {roles.account_update.class} )
	public PasswordValidation checkPasswordSynchronizationStatus(Account account) throws InternalErrorException {
		return null;
	}

}
