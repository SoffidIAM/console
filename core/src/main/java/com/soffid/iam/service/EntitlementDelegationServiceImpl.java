package com.soffid.iam.service;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserEntity;

import com.soffid.iam.api.DelegationStatus;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;

public class EntitlementDelegationServiceImpl extends EntitlementDelegationServiceBase {

	@Override
	protected RoleAccount handleDelegate(RoleAccount roleAccount, String user, String account, Date since, Date until) throws Exception {
		RoleAccountEntity ra = getRoleAccountEntityDao().load(roleAccount.getId());
		if (!checkOwnership (ra))
			throw new SecurityException(String.format("User is not owner of entitlement #%d", roleAccount.getId()));
		
		Security.nestedLogin(Security.ALL_PERMISSIONS);
		try
		{
			List<UserAccount> accounts = getAccountService().findUsersAccounts(user, ra.getAccount().getSystem().getName());
			
			UserAccount acc = null;
			if (accounts.isEmpty() && account == null)
			{
				UserEntity userEntity = getUserEntityDao().findByUserName(user);
				if (userEntity == null)
					throw new InternalErrorException(String.format("Unknown user %s", user));
				User userObject = getUserEntityDao().toUser(userEntity);
				com.soffid.iam.api.System d = getSystemEntityDao().toSystem(ra.getAccount().getSystem());
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
			getRoleAccountEntityDao().update(ra, auditType);
			
			return getRoleAccountEntityDao().toRoleAccount(ra);
		} finally {
			Security.nestedLogoff();
		}
	}

	private boolean checkOwnership(RoleAccountEntity ra) {
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
			if (user.getUser() != null && user.getUser().getUserName().equals(Security.getCurrentUser()))
				return true;
		}
		return false;
	}

	@Override
	protected List<String> handleFindAccountsToDelegate(RoleAccount roleAccount, String user) throws Exception {
		RoleAccountEntity ra = getRoleAccountEntityDao().load(roleAccount.getId());
		if (!checkOwnership (ra))
			throw new SecurityException(String.format("User is not owner of entitlement #%d", roleAccount.getId()));
		
		List<UserAccount> accounts = getAccountService().findUsersAccounts(user, ra.getAccount().getSystem().getName());
		List<String> accountNames = new LinkedList<String>();
		
		for (UserAccount account: accounts)
		{
			accountNames.add(account.getName());
		}
		return accountNames;
	}

	@Override
	protected RoleAccount handleCancelDelegation(RoleAccount roleAccount) throws Exception {
		RoleAccountEntity ra = getRoleAccountEntityDao().load(roleAccount.getId());
		if (!checkOwnership (ra))
			throw new SecurityException(String.format("User is not owner of entitlement #%d", roleAccount.getId()));
		
		ra.setDelegationStatus(null);
		ra.setAccount(ra.getOwnerAccount());
		ra.setDelegateAccount(null);
		ra.setDelegateSince(null);
		ra.setDelegateUntil(null);
		getRoleAccountEntityDao().update(ra, "m");
		return getRoleAccountEntityDao().toRoleAccount(ra);
	}

	@Override
	protected List<RoleAccount> handleFindDelegationsToAccept() throws Exception {
		List<RoleAccount> r = new LinkedList<RoleAccount>();
		for (RoleAccountEntity roleAccountEntity : getRoleAccountEntityDao().findRoleAccountToStartDelegation(Security.getCurrentUser(), new Date())) {
			r.add(getRoleAccountEntityDao().toRoleAccount(roleAccountEntity));
		}
		return r;
	}

	@Override
	protected RoleAccount handleAcceptDelegation(RoleAccount ra) throws Exception {
		RoleAccountEntity raEntity = getRoleAccountEntityDao().load(ra.getId());
		if (! checkAccountOwnership(raEntity.getDelegateAccount()))
			throw new SecurityException(String.format("User is not owner of entitlement #%d", ra.getId()));
			
		if (raEntity.getDelegationStatus().equals(DelegationStatus.DELEGATION_PENDING));
		{
			raEntity.setDelegationStatus(DelegationStatus.DELEGATION_ACTIVE);
			raEntity.setAccount(raEntity.getDelegateAccount());
			getRoleAccountEntityDao().update(raEntity, "l");
		}
		return getRoleAccountEntityDao().toRoleAccount(raEntity);
	}

	@Override
	protected void handleRevertExpiredDelegations() throws Exception {
		List<RoleAccount> r = new LinkedList<RoleAccount>();
		for (RoleAccountEntity roleAccountEntity : getRoleAccountEntityDao().findRoleAccountToEndDelegation(Security.getCurrentUser(), new Date())) {
			roleAccountEntity.setAccount( roleAccountEntity.getOwnerAccount());
			roleAccountEntity.setDelegateSince(null);
			roleAccountEntity.setDelegateUntil(null);
			roleAccountEntity.setDelegateAccount(null);
			roleAccountEntity.setDelegationStatus(null);
			getRoleAccountEntityDao().update(roleAccountEntity,"M");
		}
	}

	@Override
	protected List<RoleAccount> handleFindActiveDelegations() throws Exception {
		List<RoleAccount> r = new LinkedList<RoleAccount>();
		for (RoleAccountEntity roleAccountEntity : getRoleAccountEntityDao().findDelegatedRolAccounts(Security.getCurrentUser())) {
			r.add( getRoleAccountEntityDao().toRoleAccount(roleAccountEntity));
		}
		
		return r;
	}

}
