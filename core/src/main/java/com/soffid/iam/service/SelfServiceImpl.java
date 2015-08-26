/**
 * 
 */
/**
 * 
 */
/**
 * 
 */
package com.soffid.iam.service;

import es.caib.seycon.ng.servei.*;

import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.PasswordStatus;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.api.UserData;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.service.EntryPointService;
import com.soffid.iam.service.NetworkService;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import com.soffid.iam.api.Password;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

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
		User u = getCurrentUser();
		Collection<Account> accounts = new LinkedList<Account>();
		for (Account acc: getAccountService().getUserGrantedAccounts(u, AccountAccessLevelEnum.ACCESS_MANAGER))
		{
			if (!acc.getType().equals(AccountType.IGNORED))
				accounts.add (acc);
		}
		return accounts;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleSetAccountPassword(es.caib.seycon.ng.comu.Account, es.caib.seycon.ng.comu.Password)
	 */
	@Override
	protected void handleSetAccountPassword (Account account, Password password)
					throws Exception
	{
		getAccountService().setAccountPassword(account, password);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleSetHPAccountPassword(es.caib.seycon.ng.comu.Account, es.caib.seycon.ng.comu.Password, java.util.Date, boolean)
	 */
	@Override
	protected void handleSetHPAccountPassword (Account account, Password password,
					Date untilDate, boolean force) throws Exception
	{
		getAccountService().setHPAccountPassword(account, password, untilDate, force);
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
			return getApplicationService().findUserRolesByUserName(u.getUserName());
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
            if (p.getMenu().equals("S")) punts.add(p); else {
                if (!pes.canExecute(p)) {
                    p.getExecutions().clear();
                } else {
                    for (Iterator it = p.getExecutions().iterator(); it.hasNext(); ) {
                        AccessTreeExecution epe = (AccessTreeExecution) it.next();
                        if (!epe.getScope().equals(ambit)) it.remove();
                    }
                }
                if (!p.getExecutions().isEmpty() || p.getVisible().equals("S")) punts.add(p);
            }
        }
		return punts;
	}
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfService#queryAccountPassword(es.caib.seycon.ng.comu.Account)
	 */
	public Password handleQueryAccountPassword (Account account) throws InternalErrorException
	{
		Password p = getAccountService().queryAccountPassword(account);
		return p;
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
		User usuari = getUserService().getCurrentUser();
		return getUserService().findUserDataByUserName(usuari.getUserName());
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
}
