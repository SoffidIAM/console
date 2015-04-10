/**
 * 
 */
/**
 * 
 */
package es.caib.seycon.ng.servei;

import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserEntity;
import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
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
			return getAplicacioService().findRolsUsuarisByCodiUsuari(u.getCodi());
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
		return getAccountService().queryAccountPassword(account);
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
				for (UserAccountEntity uae : ae.getUsers()) {
                    UserEntity user = uae.getUser();
                    PasswordDomainEntity domain = ae.getSystem().getPasswordDomain();
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
                    if (!account.getDispatcher().equals(other.getDispatcher())) {
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
		Usuari usuari = getUsuariService().getCurrentUsuari();
		return getUsuariService().findDadesUsuariByCodiUsuari(usuari.getCodi());
	}
	@Override
	protected DadaUsuari handleUpdateUserAttribute(DadaUsuari attribute)
			throws Exception {
		return getDadesAddicionalsService().update(attribute);
	}
	@Override
	protected TipusDada handleGetDataTypeDescription(String attName)
			throws Exception {
		return getDadesAddicionalsService().findTipusDadaByCodi(attName);
	}
	
	@Override
	protected Collection<PuntEntrada> handleFindEntryPoints(String name) throws Exception {
		return getPuntEntradaService().findPuntsEntrada("%"+name+"%", "%", "%", "%", "%", "%");
	}
}
