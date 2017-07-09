package com.soffid.iam.service;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import es.caib.seycon.ng.common.DelegationStatus;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.RolAccountEntity;
import es.caib.seycon.ng.model.UserAccountEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.utils.Security;

public class EntitlementDelegationServiceImpl extends EntitlementDelegationServiceBase {

	@Override
	protected RolAccount handleDelegate(RolAccount rolAccount, String user, String account, Date since, Date until) throws Exception {
		RolAccountEntity ra = getRolAccountEntityDao().load(rolAccount.getId());
		if (!checkOwnership (ra))
			throw new SecurityException(String.format("User is not owner of entitlement #%d", rolAccount.getId()));
		
		List<UserAccount> accounts = getAccountService().findUserAccounts(user, ra.getAccount().getDispatcher().getCodi());
		
		UserAccount acc = null;
		if (accounts.isEmpty() && account == null)
		{
			UsuariEntity userEntity = getUsuariEntityDao().findByCodi(user);
			if (userEntity == null)
				throw new InternalErrorException(String.format("Unknown user %s", user));
			Usuari userObject = getUsuariEntityDao().toUsuari(userEntity);
			Dispatcher d = getDispatcherEntityDao().toDispatcher(ra.getAccount().getDispatcher());
			getAccountService().createAccount(userObject, d, null);
		}
		else
		{
			for (Iterator<UserAccount> it = accounts.iterator(); it.hasNext();)
			{
				acc = it.next();
				if (acc.getName().equals(account))
					break;
				acc = null;
			}
		}
		
		if (acc == null)
			throw new InternalErrorException( String.format("Account %s does not exist", account));
		
		AccountEntity targetAccountEntity = getAccountEntityDao().load(acc.getId());
		
		if (ra.getOwnerAccount() == null)
			ra.setOwnerAccount(ra.getAccount());
		ra.setDelegateAccount(targetAccountEntity);
		String auditType;
		if (new Date().after(since))
		{
			ra.setDelegationStatus(DelegationStatus.DELEGATION_ACTIVE);
			ra.setAccount(targetAccountEntity);
			getAccountEntityDao().propagateChanges(ra.getOwnerAccount());
			auditType = "l";
		}
		else
		{
			ra.setDelegationStatus(DelegationStatus.DELEGATION_PENDING);
			auditType = "L";
		}
		ra.setDelegateSince(since);
		ra.setDelegateUntil(until);
		getRolAccountEntityDao().update(ra, auditType);
		
		return getRolAccountEntityDao().toRolAccount(ra);
	}

	private boolean checkOwnership(RolAccountEntity ra) {
		if (checkAccountOwnership ( ra.getOwnerAccount() ))
			return true;
		

		if (ra.getOwnerAccount() == null && checkAccountOwnership ( ra.getAccount() ))
			return true;
		
		return false;
		
	}

	private boolean checkAccountOwnership(AccountEntity account) {
		if (account == null)
			return false;
		
		for (UserAccountEntity user : account.getUsers())
		{
			if (user.getUser() != null && user.getUser().getCodi().equals(Security.getCurrentUser()))
				return true;
		}
		return false;
	}

	@Override
	protected List<String> handleFindAccountsToDelegate(RolAccount rolAccount, String user) throws Exception {
		RolAccountEntity ra = getRolAccountEntityDao().load(rolAccount.getId());
		if (!checkOwnership (ra))
			throw new SecurityException(String.format("User is not owner of entitlement #%d", rolAccount.getId()));
		
		List<UserAccount> accounts = getAccountService().findUserAccounts(user, ra.getAccount().getDispatcher().getCodi());
		List<String> accountNames = new LinkedList<String>();
		
		for (UserAccount account: accounts)
		{
			accountNames.add(account.getName());
		}
		return accountNames;
	}

	@Override
	protected RolAccount handleCancelDelegation(RolAccount rolAccount) throws Exception {
		RolAccountEntity ra = getRolAccountEntityDao().load(rolAccount.getId());
		if (!checkOwnership (ra))
			throw new SecurityException(String.format("User is not owner of entitlement #%d", rolAccount.getId()));
		
		ra.setDelegationStatus(null);
		ra.setAccount(ra.getOwnerAccount());
		ra.setDelegateAccount(null);
		ra.setDelegateSince(null);
		ra.setDelegateUntil(null);
		getRolAccountEntityDao().update(ra, "m");
		return getRolAccountEntityDao().toRolAccount(ra);
	}

	@Override
	protected List<RolAccount> handleFindDelegationsToAccept() throws Exception {
		List<RolAccount> r = new LinkedList<RolAccount>();
		for (RolAccountEntity rolAccountEntity : getRolAccountEntityDao().findRolAccountToStartDelegation(Security.getCurrentUser(), new Date())) {
			r.add(getRolAccountEntityDao().toRolAccount(rolAccountEntity));
		}
		return r;
	}

	@Override
	protected RolAccount handleAcceptDelegation(RolAccount ra) throws Exception {
		RolAccountEntity raEntity = getRolAccountEntityDao().load(ra.getId());
		if (! checkAccountOwnership(raEntity.getDelegateAccount()))
			throw new SecurityException(String.format("User is not owner of entitlement #%d", ra.getId()));
			
		if (raEntity.getDelegationStatus().equals(DelegationStatus.DELEGATION_PENDING));
		{
			raEntity.setDelegationStatus(DelegationStatus.DELEGATION_ACTIVE);
			raEntity.setAccount(raEntity.getDelegateAccount());
			getRolAccountEntityDao().update(raEntity, "l");
		}
		return getRolAccountEntityDao().toRolAccount(raEntity);
	}

	@Override
	protected void handleRevertExpiredDelegations() throws Exception {
		List<RolAccount> r = new LinkedList<RolAccount>();
		for (RolAccountEntity rolAccountEntity : getRolAccountEntityDao().findRolAccountToEndDelegation(Security.getCurrentUser(), new Date())) {
			rolAccountEntity.setAccount( rolAccountEntity.getOwnerAccount());
			rolAccountEntity.setDelegateSince(null);
			rolAccountEntity.setDelegateUntil(null);
			rolAccountEntity.setDelegateAccount(null);
			rolAccountEntity.setDelegationStatus(null);
			getRolAccountEntityDao().update(rolAccountEntity,"M");
		}
	}

	@Override
	protected List<RolAccount> handleFindActiveDelegations() throws Exception {
		List<RolAccount> r = new LinkedList<RolAccount>();
		for (RolAccountEntity rolAccountEntity : getRolAccountEntityDao().findDelegatedRolAccounts(Security.getCurrentUser())) {
			r.add( getRolAccountEntityDao().toRolAccount(rolAccountEntity));
		}
		
		return r;
	}

}
