/**
 * 
 */
package es.caib.seycon.ng.servei;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.MetadataScope;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.EstatContrasenya;
import es.caib.seycon.ng.comu.ExecucioPuntEntrada;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.PuntEntrada;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariGrup;
import es.caib.seycon.ng.comu.Xarxa;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.DadaUsuariEntity;
import es.caib.seycon.ng.model.DominiContrasenyaEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.UserAccountEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.utils.Security;

/**
 * @author bubu
 *
 */
public class SelfServiceImpl extends SelfServiceBase
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
		Usuari u = getCurrentUsuari();
		Security.nestedLogin(u.getCodi(), new String[] {
			Security.AUTO_USER_ROLE_QUERY+Security.AUTO_ALL,
			Security.AUTO_ACCOUNT_QUERY,
			Security.AUTO_ACCOUNT_QUERY+Security.AUTO_ALL
		});
		try {
			Dispatcher mainDispatcher = getDispatcherService().findSoffidDispatcher();
			Collection<Account> accounts = new LinkedList<Account>();
			for (Account acc: getAccountService().getUserAccounts(u))
			{
				if (acc.getType().equals(AccountType.USER))
				{
					Dispatcher d = getDispatcherService().findDispatcherByCodi(acc.getDispatcher());
					if (d != null && d.getUrl() != null && d.getUrl().trim().length() > 0 ||
							acc.getDispatcher().equals (mainDispatcher.getCodi()))
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
		getAccountService().setAccountPassword(account, password);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleSetHPAccountPassword(es.caib.seycon.ng.comu.Account, es.caib.seycon.ng.comu.Password, java.util.Date, boolean)
	 */
	@Override
	protected boolean handleSetHPAccountPassword (Account account, Password password,
					Date untilDate, boolean force) throws Exception
	{
		return getAccountService().setHPAccountPassword(account, password, untilDate, force);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleGetCurrentUsuari()
	 */
	@Override
	protected Usuari handleGetCurrentUsuari () throws Exception
	{
		return getUsuariService().getCurrentUsuari();
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleFindRolsByCodiUsuari()
	 */
	@Override
	protected Collection<RolAccount> handleFindRolAccounts () throws Exception
	{
		Usuari u = getCurrentUsuari();
		Security.nestedLogin(u.getCodi(), new String[] {
			Security.AUTO_USER_ROLE_QUERY+Security.AUTO_ALL
		});
		try {
			LinkedList<RolAccount> ra = new LinkedList<RolAccount> (getAplicacioService().findRolsUsuarisByCodiUsuari(u.getCodi()));
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
	protected Collection<UsuariGrup> handleFindUsuariGrupsByCodiUsuari ()
					throws Exception
	{
		Usuari u = getCurrentUsuari();
		return getGrupService().findUsuariGrupsByCodiUsuari(u.getCodi());
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
		XarxaService xs = getXarxaService();
		String ip = getRemoteIp();
		if (ip == null)
			return Messages.getString("SelfServiceImpl.UnknownHost"); //$NON-NLS-1$
        Maquina maq = xs.findMaquinaByIp(ip);
        if (maq != null) 
        {
            Xarxa xarxa = xs.findXarxaByCodi(maq.getCodiXarxa());
            if (xarxa != null && xarxa.getNormalitzada().booleanValue())
            	return String.format (Messages.getString("SelfServiceImpl.LANClientHost"),maq.getNom(), ip); //$NON-NLS-1$
            else
            	return String.format (Messages.getString("SelfServiceImpl.WANClientHost"), maq.getNom(), ip); //$NON-NLS-1$
        }
        else
        	return String.format(Messages.getString("SelfServiceImpl.IPHost"), ip); //$NON-NLS-1$
	}
	
	private String getAmbit () throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InternalErrorException
	{
		
		String ip = getRemoteIp();
		XarxaService xs = getXarxaService();
		if (ip == null)
			return "I"; //$NON-NLS-1$
		else
		{
	        String ambit = "I"; //$NON-NLS-1$
	        Maquina maq = xs.findMaquinaByIp(ip);
	        if (maq != null) {
	            Xarxa xarxa = xs.findXarxaByCodi(maq.getCodiXarxa());
	            if (xarxa != null && xarxa.getNormalitzada().booleanValue())
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
	protected PuntEntrada handleFindRoot () throws Exception
	{
		return getPuntEntradaService().findRoot();
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleFindChildren(es.caib.seycon.ng.comu.PuntEntrada)
	 */
	@Override
	protected Collection<PuntEntrada> handleFindChildren (PuntEntrada puntEntrada)
					throws Exception
	{
		PuntEntradaService pes = getPuntEntradaService();
		LinkedList<PuntEntrada> punts = new LinkedList<PuntEntrada>();
		Collection<PuntEntrada> puntsOriginal = pes.findChildren(puntEntrada);
		String ambit = getAmbit ();
		for (PuntEntrada p: puntsOriginal)
		{
			if (p.getMenu().equals("S")) //$NON-NLS-1$
				punts.add(p);
			else
			{
				if (!pes.canExecute(p))
				{
					p.getExecucions().clear();
				} else {
					for (Iterator it = p.getExecucions().iterator(); it.hasNext();)
					{
						ExecucioPuntEntrada epe = (ExecucioPuntEntrada) it.next();
						if (! epe.getAmbit().equals(ambit) )
							it.remove();
					}
				}
				if (!p.getExecucions().isEmpty() || p.getVisible().equals("S")) //$NON-NLS-1$
					punts.add(p);
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
	protected EstatContrasenya handlePasswordsStatus (Account account)
					throws Exception
	{
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
			InternalPasswordService ips = getInternalPasswordService();
			AccountEntity ae = getAccountEntityDao().accountToEntity(account);
			if (account instanceof UserAccount)
			{
				for (UserAccountEntity uae: ae.getUsers())
				{
					UsuariEntity user= uae.getUser();
					DominiContrasenyaEntity domain = ae.getDispatcher().getDomini();
					return ips.getPasswordsStatus(user, domain);
				}
				return null;
			}
			else
			{
    			EstatContrasenya ec = ips.getAccountPasswordsStatus(ae);
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
		DominiContrasenyaEntity domini = acce.getDispatcher().getDomini();
		Collection<Account> others = new LinkedList<Account>();
		Collection<Account> othersDef = new LinkedList<Account>();
		String o = new String();
		others = getUserAccounts();
		for(Account other: others)
		{
			if(other.getType().equals(AccountType.USER))
	    	{
				AccountEntity acceOther = getAccountEntityDao().accountToEntity(other);
				DominiContrasenyaEntity dominiOther = acceOther.getDispatcher().getDomini();
				if(domini.equals(dominiOther))
				{
    	    		if(!account.getDispatcher().equals(other.getDispatcher()))
    	   			{
    	   				othersDef.add(other);
    	   			}
				}
	   		}
		}
		for(Account oa: othersDef)
		{
			o = o + oa.getName() + " (" + oa.getDispatcher() + ")" + ", "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		if(o.length() > 1)
			o = o.substring(0, o.lastIndexOf(','));
		return o;
	}
	
	
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SelfServiceBase#handleGetDispatcherInformation(java.lang.String)
	 */
	@Override
	protected Dispatcher handleGetDispatcherInformation (String dispatcherCode)
					throws Exception
	{
		Dispatcher dispatcher = new Dispatcher();
		Dispatcher complet = getDispatcherService().findDispatcherByCodi(dispatcherCode);
		dispatcher.setCodi(complet.getCodi());
		dispatcher.setDescription(complet.getDescription());
		dispatcher.setReadOnly(complet.getControlAccess());
		dispatcher.setDominiContrasenyes(complet.getDominiContrasenyes());
		
		return dispatcher;
	}
	@Override
	protected Collection<DadaUsuari> handleGetUserAttributes() throws Exception {
		UsuariEntity usuari = getUsuariEntityDao().findByCodi(Security.getCurrentUser());
		Collection<DadaUsuariEntity> dades = usuari.getDadaUsuari();
		LinkedList<DadaUsuari> result = new LinkedList<DadaUsuari>();
		
		List<TipusDadaEntity> tipusDades = getTipusDadaEntityDao().loadAll();
		Collections.sort(tipusDades, new Comparator<TipusDadaEntity>(){
			public int compare(TipusDadaEntity o1, TipusDadaEntity o2) {
				return o1.getOrdre().compareTo(o2.getOrdre());
			}	
		});
		
		Iterator<TipusDadaEntity> tipusDadesIterator = tipusDades.iterator();
		while (tipusDadesIterator.hasNext()) {
			TipusDadaEntity tipusDada = tipusDadesIterator.next();
			Iterator<DadaUsuariEntity> dadesIterator = dades.iterator();
			boolean teTipusDada = false;
			while (dadesIterator.hasNext()) {
				DadaUsuariEntity dada = dadesIterator.next();
				if (dada.getTipusDada().getCodi().equals(tipusDada.getCodi()))
				{
					teTipusDada = true;
					if (Security.isDisableAllSecurityForEver() ||
							! dada.getAttributeVisibility().equals(AttributeVisibilityEnum.HIDDEN))
						result.add(getDadaUsuariEntityDao().toDadaUsuari(dada));
				}
			}
			if (!teTipusDada) {
				DadaUsuariEntity dus = getDadaUsuariEntityDao().newDadaUsuariEntity();
				dus.setUsuari(usuari);
				dus.setTipusDada(tipusDada);
				if (Security.isDisableAllSecurityForEver() ||
						! dus.getAttributeVisibility().equals(AttributeVisibilityEnum.HIDDEN))
				{
					result.add ( getDadaUsuariEntityDao().toDadaUsuari(dus));
				}
			}
		}

		return result;
	}
	@Override
	protected DadaUsuari handleUpdateUserAttribute(DadaUsuari attribute)
			throws Exception {
		return getDadesAddicionalsService().update(attribute);
	}
	@Override
	protected TipusDada handleGetDataTypeDescription(String systemName, String attName)
			throws Exception {
		if (systemName == null)
			return getDadesAddicionalsService().findTipusDadaByCodi(attName);
		else
			return getDadesAddicionalsService().findSystemDataType(systemName, attName);
	}
	
	@Override
	protected Collection<PuntEntrada> handleFindEntryPoints(String name) throws Exception {
		return getPuntEntradaService().findPuntsEntrada("%"+name+"%", "%", "%", "%", "%", "%");
	}
	@Override
	protected List<Account> handleGetSharedAccounts(String filter)
			throws Exception {
		Dispatcher mainDispatcher = getDispatcherService().findSoffidDispatcher();
		Usuari u = getCurrentUsuari();
		List<Account> accounts = new LinkedList<Account>();
		Security.nestedLogin(Security.getCurrentUser(), 
				new String [] { Security.AUTO_ACCOUNT_QUERY});
		try 
		{
			for (Account acc: getAccountService().getUserGrantedAccounts(u, AccountAccessLevelEnum.ACCESS_USER))
			{
				if (!acc.getType().equals(AccountType.IGNORED) &&
						!acc.getType().equals(AccountType.USER))
				{
					Dispatcher d = getDispatcherService().findDispatcherByCodi(acc.getDispatcher());
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
		Account acc = getAccountService().findAccount(account.getName(), account.getDispatcher());
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
			throw new SecurityException (String.format("Not authorized to update account %s on %s",acc.getName(), acc.getDispatcher()));
		}
	}
	@Override
	protected DadaUsuari handleUpdateSharedAccountData(DadaUsuari data)
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
			throw new SecurityException (String.format("Not authorized to update account %s on %s",acc.getName(), acc.getDispatcher()));
		}
	}

	@Override
	protected DadaUsuari handleCreateSharedAccountData(DadaUsuari data)
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
			throw new SecurityException (String.format("Not authorized to update account %s on %s",acc.getName(), acc.getDispatcher()));
		}
	}

	@Override
	protected List<DadaUsuari> handleGetAccountAttributes(Account account)
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
			throw new SecurityException (String.format("Not authorized to update account %s on %s",acc.getName(), acc.getDispatcher()));
		}
	}
}
