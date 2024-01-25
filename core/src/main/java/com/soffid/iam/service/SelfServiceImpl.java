package com.soffid.iam.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordStatus;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.api.UserData;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserDataEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 *
 */
public class SelfServiceImpl extends com.soffid.iam.service.SelfServiceBase
{
	private Class clientIpValveClass = null;
	private Method getClientIpMethod;
	public SelfServiceImpl ()
	{
		super ();
		try
		{
			clientIpValveClass = Class.forName("es.caib.loginModule.auth.ClientIPValve"); //$NON-NLS-1$
			getClientIpMethod = clientIpValveClass.getMethod("getClientIP"); //$NON-NLS-1$
		}
		catch (ClassNotFoundException e)
		{
		}
		catch (NoClassDefFoundError e)
		{
		}
		catch (NoSuchMethodException e)
		{
		}
	}
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleGetUserAccounts()
	 */
	@Override
	protected Collection<Account> handleGetUserAccounts () throws Exception
	{
		String filter = ConfigurationCache.getProperty("selfservice.account.filter");
				
		User u = getCurrentUser();
		
		Security.nestedLogin(u.getUserName(), new String[] {
			Security.AUTO_USER_ROLE_QUERY+Security.AUTO_ALL,
			Security.AUTO_ACCOUNT_QUERY,
			Security.AUTO_ACCOUNT_QUERY+Security.AUTO_ALL
		});
		try {
			com.soffid.iam.api.System mainDispatcher = getDispatcherService().findSoffidDispatcher();
			Collection<Account> accounts = new LinkedList<Account>();
			for (Account acc: getAccountService().getUserAccounts(u))
			{
				if (acc.getType().equals(AccountType.USER) && (
						filter == null || filter.trim().isEmpty() ||
						Pattern.matches(filter, acc.getSystem()))) 
				{
					com.soffid.iam.api.System d = getDispatcherService().findDispatcherByName(acc.getSystem());
					if (d != null && d.getUrl() != null && d.getUrl().trim().length() > 0 ||
						acc.getSystem().equals (mainDispatcher.getName()))
					{
						accounts.add (acc);
					}
				}
			}
			return accounts;
		} finally {
			Security.nestedLogoff();
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleSetAccountPassword(es.caib.seycon.ng.comu.Account, es.caib.seycon.ng.comu.Password)
	 */
	@Override
	protected void handleSetAccountPassword (Account account, Password password)
					throws Exception
	{
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		getPamSecurityHandlerService().checkPermission(entity, "setPassword");
			getAccountService().setAccountPassword(account, password);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleSetHPAccountPassword(es.caib.seycon.ng.comu.Account, es.caib.seycon.ng.comu.Password, java.util.Date, boolean)
	 */
	@Override
	protected boolean handleSetHPAccountPassword (Account account, Password password,
					Date untilDate, boolean force) throws Exception
	{
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		getPamSecurityHandlerService().checkPermission(entity, "setPassword");
		return getAccountService().setHPAccountPassword(account, password, untilDate, force);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleGetCurrentUsuari()
	 */
	@Override
    protected User handleGetCurrentUser() throws Exception {
		return getUserService().getCurrentUser();
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleFindRolsByCodiUsuari()
	 */
	@Override
    protected Collection<RoleAccount> handleFindRoleAccounts() throws Exception {
		User u = getCurrentUser();
		Security.nestedLogin(u.getUserName(), new String[]{Security.AUTO_USER_ROLE_QUERY + Security.AUTO_ALL});
		try {
			LinkedList<RoleAccount> ra = new LinkedList<RoleAccount> (getApplicationService().findUserRolesByUserNameNoSoD(u.getUserName()));
			ra.addAll( getEntitlementDelegationService().findActiveDelegations() );
			return ra;
		} finally {
			Security.nestedLogoff();
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleFindUsuariGrupsByCodiUsuari()
	 */
	@Override
    protected Collection<GroupUser> handleFindUserGroupsByUserName() throws Exception {
		User u = getCurrentUser();
		return getGroupService().findUsersGroupByUserName(u.getUserName());
	}


	private String getRemoteIp () throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		
		if (clientIpValveClass == null || getClientIpMethod == null)
			return null;
		else
	        return (String) getClientIpMethod.invoke(null);
	}
	
	protected String handleGetClientHost() throws InternalErrorException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		NetworkService xs = getNetworkService();
		String ip = getRemoteIp();
		if (ip == null)
			return Messages.getString("SelfServiceImpl.UnknownHost"); //$NON-NLS-1$
        Host maq = xs.findHostByIp(ip);
        if (maq != null) 
        {
            Network xarxa = xs.findNetworkByName(maq.getNetworkCode());
            if (xarxa != null && xarxa.getLanAccess().booleanValue())
            	return String.format(Messages.getString("SelfServiceImpl.LANClientHost"), maq.getName(), ip); //$NON-NLS-1$
            else
            	return String.format(Messages.getString("SelfServiceImpl.WANClientHost"), maq.getName(), ip); //$NON-NLS-1$
        }
        else
        	return String.format(Messages.getString("SelfServiceImpl.IPHost"), ip); //$NON-NLS-1$
	}
	
	private String getAmbit () throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InternalErrorException
	{
		
		String ip = getRemoteIp();
		NetworkService xs = getNetworkService();
		if (ip == null)
			return "I"; //$NON-NLS-1$
		else
		{
	        String ambit = "I"; //$NON-NLS-1$
	        Host maq = xs.findHostByIp(ip);
	        if (maq != null) {
	            Network xarxa = xs.findNetworkByName(maq.getNetworkCode());
	            if (xarxa != null && xarxa.getLanAccess().booleanValue())
	                ambit = "L"; //$NON-NLS-1$
	            else
	                ambit = "W"; //$NON-NLS-1$
	        }
	        return ambit;
		}
	}
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleFindRoot()
	 */
	@Override
    protected AccessTree handleFindRoot() throws Exception {
		return getEntryPointService().findRoot();
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleFindChildren(es.caib.seycon.ng.comu.PuntEntrada)
	 */
	@Override
    protected Collection<AccessTree> handleFindChildren(AccessTree puntEntrada) throws Exception {
		EntryPointService pes = getEntryPointService();
		LinkedList<AccessTree> punts = new LinkedList<AccessTree>();
		Collection<AccessTree> puntsOriginal = pes.findChildren(puntEntrada);
		String ambit = getAmbit ();
		for (AccessTree p : puntsOriginal) {
            if (p.isMenu()) punts.add(p); 
            else {
                if (!pes.canExecute(p)) {
                    p.getExecutions().clear();
                } else {
                    for (Iterator it = p.getExecutions().iterator(); it.hasNext(); ) {
                        AccessTreeExecution epe = (AccessTreeExecution) it.next();
                        if (!epe.getScope().equals(ambit)) it.remove();
                    }
                }
                if (!p.getExecutions().isEmpty() || p.isVisible()) punts.add(p);
            }
        }
		return punts;
	}
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfService#queryAccountPassword(es.caib.seycon.ng.comu.Account)
	 */
	public Password handleQueryAccountPassword (Account account) throws InternalErrorException
	{
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		getPamSecurityHandlerService().checkPermission(entity, "queryPassword");
		return getAccountService().queryAccountPassword(account);
	}
	
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfService#queryAccountPassword(es.caib.seycon.ng.comu.Account)
	 */
	public Password handleQueryAccountPasswordBypassPolicy (Account account) throws InternalErrorException
	{
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		getPamSecurityHandlerService().checkPermission(entity, "queryPasswordBypassPolicy");
		return getAccountService().queryAccountPasswordBypassPolicy(account.getId(), AccountAccessLevelEnum.ACCESS_USER);
	}
	
	public Password handleQueryAccountSshKey (Account account) throws InternalErrorException
	{
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		getPamSecurityHandlerService().checkPermission(entity, "querySshKey");
		return getAccountService().queryAccountSshKey(account);
	}
	
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfService#queryAccountPassword(es.caib.seycon.ng.comu.Account)
	 */
	public Password handleQueryAccountSshKeyBypassPolicy (Account account) throws InternalErrorException
	{
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		getPamSecurityHandlerService().checkPermission(entity, "querySshBypassPolicy");
		return getAccountService().queryAccountSshKeyBypassPolicy(account.getId(), AccountAccessLevelEnum.ACCESS_USER);
	}
	
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handlePasswordsStatus(es.caib.seycon.ng.comu.Account, es.caib.seycon.ng.comu.Usuari)
	 */
	@Override
    protected PasswordStatus handlePasswordsStatus(Account account) throws Exception {
		Collection<Account> acol = getUserAccounts();
		boolean found = false;
		for(Account a: acol)
		{
			if(a.getId().equals(account.getId()))
			{
				found = true;
			}
		}
		
		if(found){
			com.soffid.iam.service.InternalPasswordService ips = getInternalPasswordService();
			AccountEntity ae = getAccountEntityDao().accountToEntity(account);
			if (account instanceof UserAccount)
			{
				for (UserAccountEntity uae : ae.getUsers()) {
                    UserEntity user = uae.getUser();
                    PasswordDomainEntity domain = ae.getSystem().getPasswordDomain();
                    return ips.getPasswordsStatus(user, domain);
                }
				return null;
			}
			else
			{
    			PasswordStatus ec = ips.getAccountPasswordsStatus(ae);
    			return ec;
			}
		}
		else
			return null;	
	}
	

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleQueryOtherAffectedAccounts(es.caib.seycon.ng.comu.Account)
	 */
	@Override
	protected String handleQueryOtherAffectedAccounts (Account account) throws Exception
	{
		AccountEntity acce = getAccountEntityDao().accountToEntity(account);
		PasswordDomainEntity domini = acce.getSystem().getPasswordDomain();
		Collection<Account> others = new LinkedList<Account>();
		Collection<Account> othersDef = new LinkedList<Account>();
		String o = new String();
		others = getUserAccounts();
		for (Account other : others) {
            if (other.getType().equals(AccountType.USER)) {
                AccountEntity acceOther = getAccountEntityDao().accountToEntity(other);
                PasswordDomainEntity dominiOther = acceOther.getSystem().getPasswordDomain();
                if (domini.equals(dominiOther)) {
                    if (!account.getSystem().equals(other.getSystem())) {
                        othersDef.add(other);
                    }
                }
            }
        }
		for (Account oa : othersDef) {
            o = o + oa.getName() + " (" + oa.getSystem() + ")" + ", ";
        }
		if(o.length() > 1)
			o = o.substring(0, o.lastIndexOf(','));
		return o;
	}
	
	
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleGetDispatcherInformation(java.lang.String)
	 */
	@Override
    protected com.soffid.iam.api.System handleGetDispatcherInformation(String dispatcherCode) throws Exception {
		com.soffid.iam.api.System dispatcher = new com.soffid.iam.api.System();
		com.soffid.iam.api.System complet = getDispatcherService().findDispatcherByName(dispatcherCode);
		dispatcher.setName(complet.getName());
		dispatcher.setDescription(complet.getDescription());
		dispatcher.setReadOnly(complet.getAccessControl());
		dispatcher.setPasswordsDomain(complet.getPasswordsDomain());
		
		return dispatcher;
	}
	@Override
    protected Collection<UserData> handleGetUserAttributes() throws Exception {
		UserEntity usuari = getUserEntityDao().findByUserName(Security.getCurrentUser());
		Collection<UserDataEntity> dades = usuari.getUserData();
		LinkedList<UserData> result = new LinkedList<UserData>();
		
		List<MetaDataEntity> tipusDades = getMetaDataEntityDao().findDataTypesByScopeAndName(MetadataScope.USER, null);
		Collections.sort(tipusDades, new Comparator<MetaDataEntity>(){
			public int compare(MetaDataEntity o1, MetaDataEntity o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}	
		});
		
		Iterator<MetaDataEntity> tipusDadesIterator = tipusDades.iterator();
		while (tipusDadesIterator.hasNext()) {
			MetaDataEntity metaData = tipusDadesIterator.next();
			Iterator<UserDataEntity> dadesIterator = dades.iterator();
			boolean teMetaData = false;
			while (dadesIterator.hasNext()) {
				UserDataEntity dada = dadesIterator.next();
				if (dada.getDataType().getName().equals(metaData.getName())) {
					if (Security.isSyncServer() || !dada.getAttributeVisibility().equals(AttributeVisibilityEnum.HIDDEN)) {
						teMetaData = true;
						result.add(getUserDataEntityDao().toUserData(dada));
					}
				}
			}
			if (!teMetaData) {
				UserDataEntity dus = getUserDataEntityDao().newUserDataEntity();
				dus.setUser(usuari);
				dus.setDataType(metaData);
				if (! dus.getAttributeVisibility().equals(AttributeVisibilityEnum.HIDDEN))
				{
					result.add ( getUserDataEntityDao().toUserData(dus));
				}
			}
		}

		return result;
	}
	@Override
    protected UserData handleUpdateUserAttribute(UserData attribute) throws Exception {
		return getAdditionalDataService().update(attribute);
	}
	@Override
    protected DataType handleGetDataTypeDescription(String systemName, String attName) throws Exception {
		if (systemName == null)
			return getAdditionalDataService().findDataTypeByName(attName);
		else
			return getAdditionalDataService().findSystemDataType(systemName, attName);
	}
	
	@Override
    protected Collection<AccessTree> handleFindEntryPoints(String name) throws Exception {
		return getEntryPointService().findApplicationAccessByFilter("%" + name + "%", "%", "%", "%", "%", "%");
	}
	@Override
	protected List<Account> handleGetSharedAccounts(String filter)
			throws Exception {
		com.soffid.iam.api.System mainDispatcher = getDispatcherService().findSoffidDispatcher();
		User u = getCurrentUser();
		List<Account> accounts = new LinkedList<Account>();
		Security.nestedLogin(Security.getCurrentUser(), 
				new String [] { Security.AUTO_ACCOUNT_QUERY});
		try 
		{
			for (Account acc: getAccountService().getUserGrantedAccounts(u, AccountAccessLevelEnum.ACCESS_MANAGER))
			{
				if (!acc.getType().equals(AccountType.IGNORED) &&
						!acc.getType().equals(AccountType.USER))
				{
					System d = getDispatcherService().findDispatcherByName(acc.getSystem());
					if (matchFilter (filter, acc))
						accounts.add (acc);
				}
			}
		} finally {
			Security.nestedLogoff();
		}
		return accounts;
		
	}
	private boolean matchFilter(String filter, Account acc) {
		if (filter == null)
			return true;
		
		filter = filter.toLowerCase();
		
		if (acc.getName() != null && acc.getName().toLowerCase().contains(filter))
			return true;

		if (acc.getDescription() != null && acc.getDescription().toLowerCase().contains(filter))
			return true;

		if (acc.getAttributes() != null)
		{
			for (Object value: acc.getAttributes().values())
			{
				if (value != null && value.toString().toLowerCase().contains(filter))
					return true;
			}
		}
		return false;
	}

	@Override
	protected Account handleUpdateSharedAccount(Account account)
			throws Exception {
		Account acc = getAccountService().findAccount(account.getName(), account.getSystem());
		if (acc == null || ! acc.getId().equals(account.getId()))
			throw new InternalErrorException ("Account not found");
		
		if (acc.getAccessLevel().equals(AccountAccessLevelEnum.ACCESS_MANAGER) ||
				acc.getAccessLevel().equals(AccountAccessLevelEnum.ACCESS_OWNER))
		{
			if (acc.getAccessLevel().equals(AccountAccessLevelEnum.ACCESS_MANAGER))
			{
				account.setGrantedGroups(acc.getGrantedGroups());
				account.setGrantedRoles(acc.getGrantedRoles());
				account.setGrantedUsers(acc.getGrantedUsers());
			}
			Security.nestedLogin(Security.getCurrentUser(), 
					new String [] { Security.AUTO_ACCOUNT_QUERY,
						Security.AUTO_ACCOUNT_UPDATE});
			try 
			{
				getAccountService().updateAccount(account);
			} finally {
				Security.nestedLogoff();
			}
			return account;
		}
		else
		{
			throw new SecurityException (String.format("Not authorized to update account %s on %s",acc.getName(), acc.getSystem()));
		}
	}
	@Override
	protected UserData handleUpdateSharedAccountData(UserData data)
			throws Exception {
		Account acc = getAccountService().findAccount(data.getAccountName(), data.getSystemName());
		if (acc == null )
			throw new InternalErrorException ("Account not found");
		
		if (acc.getAccessLevel().equals(AccountAccessLevelEnum.ACCESS_MANAGER) ||
				acc.getAccessLevel().equals(AccountAccessLevelEnum.ACCESS_OWNER))
		{
			
			Security.nestedLogin(Security.getCurrentUser(), 
					new String [] { Security.AUTO_ACCOUNT_QUERY,
						Security.AUTO_ACCOUNT_UPDATE});
			try 
			{
				return getAccountService().updateAccountAttribute(data);
			} finally {
				Security.nestedLogoff();
			}
		}
		else
		{
			throw new SecurityException (String.format("Not authorized to update account %s on %s",acc.getName(), acc.getSystem()));
		}
	}

	@Override
	protected UserData handleCreateSharedAccountData(UserData data)
			throws Exception {
		Account acc = getAccountService().findAccount(data.getAccountName(), data.getSystemName());
		if (acc == null )
			throw new InternalErrorException ("Account not found");
		
		if (acc.getAccessLevel().equals(AccountAccessLevelEnum.ACCESS_MANAGER) ||
				acc.getAccessLevel().equals(AccountAccessLevelEnum.ACCESS_OWNER))
		{
			return getAccountService().createAccountAttribute(data);
		}
		else
		{
			throw new SecurityException (String.format("Not authorized to update account %s on %s",acc.getName(), acc.getSystem()));
		}
	}

	@Override
	protected List<UserData> handleGetAccountAttributes(Account account)
			throws Exception {
		Account acc = getAccountService().findAccountById(account.getId());
		if (acc == null )
			throw new InternalErrorException ("Account not found");
		
		if (acc.getAccessLevel().equals(AccountAccessLevelEnum.ACCESS_MANAGER) ||
				acc.getAccessLevel().equals(AccountAccessLevelEnum.ACCESS_OWNER))
		{
			return getAccountService().getAccountAttributes(acc);
		}
		else
		{
			throw new SecurityException (String.format("Not authorized to update account %s on %s",acc.getName(), acc.getSystem()));
		}
	}
	@Override
	protected void handleCheckCanSetAccountPassword(Account account) throws Exception {
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		getPamSecurityHandlerService().checkPermission(entity, "setPassword");
	}
	@Override
	protected Password handleGenerateAccountTemporaryPassword(Account account) throws Exception {
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		getPamSecurityHandlerService().checkPermission(entity, "setPassword");
		return getAccountService().generateAccountTemporaryPassword(account);
	}
	@Override
	protected void handleCheckinHPAccount(Account account) throws Exception {
//		AccountEntity entity = getAccountEntityDao().load(account.getId());
//		getPamSecurityHandlerService().checkPermission(entity, "setPassword");
		getAccountService().checkinHPAccount(account);
	}
	@Override
	protected Account handleGetAccountById(long id) throws Exception {
		Account acc = getAccountService().findAccountById(id);
		if (acc == null || 
				acc.getAccessLevel() == AccountAccessLevelEnum.ACCESS_NAVIGATE ||
				acc.getAccessLevel() == AccountAccessLevelEnum.ACCESS_NONE)
			return null;
		else
			return acc;
	}
	
}
