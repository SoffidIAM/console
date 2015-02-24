package es.caib.seycon.ng.servei;

import java.security.Principal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.naming.NamingException;

import org.apache.commons.logging.LogFactory;
import org.jboss.invocation.pooled.server.ServerThread;
import org.mortbay.log.Log;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.soffid.iam.api.User;
import com.soffid.iam.api.UserDomain;
import com.soffid.iam.reconcile.common.ReconcileAccount;

import bsh.EvalError;
import bsh.Interpreter;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountCriteria;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.PolicyCheckResult;
import es.caib.seycon.ng.comu.PoliticaContrasenya;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.comu.SeyconServerInfo;
import es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration;
import es.caib.seycon.ng.comu.TipusUsuari;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariGrup;
import es.caib.seycon.ng.comu.sso.NameParser;
import es.caib.seycon.ng.config.Config;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.exception.NotAllowedException;
import es.caib.seycon.ng.exception.UnknownUserException;
import es.caib.seycon.ng.model.AccountAccessEntity;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.AccountEntityDao;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.DispatcherEntityDao;
import es.caib.seycon.ng.model.DominiUsuariEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.ServerEntity;
import es.caib.seycon.ng.model.ServerEntityDao;
import es.caib.seycon.ng.model.TaskLogEntity;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.model.TipusUsuariEntity;
import es.caib.seycon.ng.model.TipusUsuariEntityDao;
import es.caib.seycon.ng.model.UserAccountEntity;
import es.caib.seycon.ng.model.UserAccountEntityDao;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.UsuariGrupEntity;
import es.caib.seycon.ng.remote.RemoteServiceLocator;
import es.caib.seycon.ng.remote.URLManager;
import es.caib.seycon.ng.servei.account.AccountNameGenerator;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.sync.servei.SecretStoreService;
import es.caib.seycon.ng.sync.servei.SyncStatusService;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.Security;

public class AccountServiceImpl extends AccountServiceBase implements ApplicationContextAware
{

	private ApplicationContext applicationContext;

	private UsuariEntity getUser(String usuari)
	{
		UsuariEntity ue = getUsuariEntityDao().findByCodi(usuari);
		if (ue != null)
		{
			Collection<UsuariEntity> filtrat = 
				AutoritzacionsUsuari.filtraUsuariEntityCanQuery(Collections.singleton(ue));
			if (filtrat.isEmpty())
				ue = null;
		}
		return ue;
	}
	
	@Override
	protected List<UserAccount> handleListUserAccounts(Usuari usuari)
			throws Exception
	{
		LinkedList<UserAccount> result = new LinkedList<UserAccount> ();
		UsuariEntity ue = getUser(usuari.getCodi());
		if (ue != null)
		{
			for (UserAccountEntity ua: ue.getAccounts())
			{
				if (ua.getAccount().getType().equals(AccountType.USER))
					result.add(getUserAccountEntityDao().toUserAccount(ua));
			}
		}
		return result;
	}

	@Override
	protected UserAccount handleCreateAccount(Usuari usuari, Dispatcher dispatcher,
			String name) throws Exception
	{
		UsuariEntity ue = getUser(usuari.getCodi());
		DispatcherEntity de = getDispatcherEntityDao ().load(dispatcher.getId());
		UserAccountEntity uae = generateAccount(name, ue, de);
		return getUserAccountEntityDao().toUserAccount(uae);
	}

	private UserAccountEntity generateAccount(String name,
			UsuariEntity ue, DispatcherEntity de) throws NeedsAccountNameException,
			EvalError, InternalErrorException, AccountAlreadyExistsException
	{
		boolean nullName = false;
		if (name == null)
		{
			nullName = true;
			List<AccountEntity> existing = getAccountEntityDao().findByUsuariAndDispatcher(ue.getCodi(), de.getCodi());
			if (! existing.isEmpty())
				throw new NeedsAccountNameException(String.format(Messages.getString("AccountServiceImpl.AlreadyUserAccount"),  //$NON-NLS-1$
						ue.getCodi(), de.getCodi()));
			// Search if already has a user name for this user domain
			
			name = gessAccountName(ue.getCodi(), de.getCodi());
							
			if (name == null)
				throw new NeedsAccountNameException(Messages.getString("AccountServiceImpl.AccountNameRequired")); //$NON-NLS-1$
		}
		AccountEntity acc = getAccountEntityDao().findByNameAndDispatcher(name, de.getCodi());
		if (acc != null)
		{
			if (acc.getType().equals (AccountType.IGNORED) && acc.getAcl().isEmpty() &&
				Security.isUserInRole(Security.AUTO_ACCOUNT_UPDATE))
			{
				acc.setType(AccountType.USER);
	    		acc.setDescription(ue.getFullName());
	    		acc.setPasswordPolicy( ue.getTipusUsuari() );
	    		getAccountEntityDao().update(acc);
			}
			else
				throw new AccountAlreadyExistsException(
						String.format(Messages.getString("AccountServiceImpl.AccountAlreadyExists"), //$NON-NLS-1$
						name+"@"+de.getCodi()));
		} else {
    		acc = getAccountEntityDao().newAccountEntity();
    		acc.setDescription(ue.getFullName());
    		acc.setDispatcher(de);
    		acc.setName(name);
    		acc.setType(AccountType.USER);
    		acc.setPasswordPolicy( ue.getTipusUsuari() );
    		getAccountEntityDao().create(acc);
		}

		UserAccountEntity uae = getUserAccountEntityDao().newUserAccountEntity();
		uae.setAccount(acc);
		uae.setUser(ue);
		getUserAccountEntityDao().create(uae);
		acc.getUsers().add(uae);
		ue.getAccounts().add(uae);
		createAccountTask(uae.getAccount());
		return uae;
	}

	@Override
	protected void handleRemoveAccount(UserAccount account) throws Exception
	{
		UsuariEntity ue = getUser(account.getUser());
		if (ue != null)
		{
			AccountEntity acc = getAccountEntityDao().load(account.getId());
			if (acc == null)
				throw new InternalErrorException (String.format(Messages.getString("AccountServiceImpl.UnknownAccount"), account.getId())); //$NON-NLS-1$
		
			if (!acc.getType().equals(AccountType.USER))
				throw new InternalErrorException (String.format(Messages.getString("AccountServiceImpl.NotPersonalAccount"), account.getId())); //$NON-NLS-1$
				
			Collection<UserAccountEntity> list = acc.getUsers();
			if (list.size() != 1)
				throw new InternalErrorException (String.format(Messages.getString("AccountServiceImpl.MoreThanOneUserAccount"), account.getId())); //$NON-NLS-1$
			
			if (!acc.getRoles().isEmpty())
				throw new NotAllowedException(String.format(Messages.getString("AccountServiceImpl.CannotDeleteAccount"), account.getName(), account.getDispatcher())); //$NON-NLS-1$
			
			UserAccountEntity ua = list.iterator().next();
			
			createAccountTask(acc);
			getAccountEntityDao().remove(acc);
		}
	}


	@Override
	protected List<es.caib.seycon.ng.comu.Account> handleListNonUserAccounts(
			Dispatcher dispatcher, String nom) throws Exception
	{
		List<AccountEntity>accounts = getAccountEntityDao().findSharedAccounts (dispatcher.getCodi(), nom);
		return getAccountEntityDao().toAccountList(accounts);
	}

	@Override
	protected Account handleCreateAccount(es.caib.seycon.ng.comu.Account account)
			throws Exception
	{
		AccountEntity acc = getAccountEntityDao().findByNameAndDispatcher(account.getName(), account.getDispatcher());
		if (acc != null)
		{
			throw new AccountAlreadyExistsException(
				String.format(Messages.getString("AccountServiceImpl.AccountAlreadyExists"), //$NON-NLS-1$
				account.getName()+"@"+account.getDispatcher()));
		}
		if (account.getType().equals(AccountType.IGNORED) || account.getType().equals(AccountType.PRIVILEGED) ||
				account.getType().equals(AccountType.SHARED))
		{
			acc = getAccountEntityDao().newAccountEntity();
			acc.setAcl(new HashSet<AccountAccessEntity>());
			acc.setDescription(account.getDescription());
			acc.setDispatcher(getDispatcherEntityDao().findByCodi(account.getDispatcher()));
			acc.setName(account.getName());
			acc.setType(account.getType());
			TipusUsuariEntity tu = getTipusUsuariEntityDao().findByCodi(account.getPasswordPolicy());
			if (tu == null)
				throw new InternalErrorException (String.format(Messages.getString("AccountServiceImpl.InvalidPolicy"), account.getPasswordPolicy())); //$NON-NLS-1$
			acc.setPasswordPolicy( tu );
			getAccountEntityDao().create(acc);
			updateAcl (acc, account);
			createAccountTask(acc);
			return getAccountEntityDao().toAccount(acc);
		}
		else
		{
			throw new InternalErrorException (String.format(Messages.getString("AccountServiceImpl.InvalidAccountType"), account.getType().toString())); //$NON-NLS-1$
		}
	}

	private Collection<Grup> getAclGrupCollectionForLevel (Account account, AccountAccessLevelEnum level)
	{
		if (level == AccountAccessLevelEnum.ACCESS_MANAGER)
			return account.getManagerGroups();
		else if (level == AccountAccessLevelEnum.ACCESS_USER)
			return account.getGrantedGroups();
		else
			return account.getOwnerGroups();
	}

	private Collection<Usuari> getAclUsuariCollectionForLevel (Account account, AccountAccessLevelEnum level)
	{
		if (level == AccountAccessLevelEnum.ACCESS_MANAGER)
			return account.getManagerUsers();
		else if (level == AccountAccessLevelEnum.ACCESS_USER)
			return account.getGrantedUsers();
		else
			return account.getOwnerUsers();
	}

	private Collection<Rol> getAclRolCollectionForLevel (Account account, AccountAccessLevelEnum level)
	{
		if (level == AccountAccessLevelEnum.ACCESS_MANAGER)
			return account.getManagerRoles();
		else if (level == AccountAccessLevelEnum.ACCESS_USER)
			return account.getGrantedRoles();
		else
			return account.getOwnerRoles();
	}

	private void updateAcl(AccountEntity acc, es.caib.seycon.ng.comu.Account account)
	{
		AccountAccessLevelEnum levels[] = new AccountAccessLevelEnum[] {AccountAccessLevelEnum.ACCESS_USER,
				AccountAccessLevelEnum.ACCESS_MANAGER, AccountAccessLevelEnum.ACCESS_OWNER
		};
		if (account.getGrantedGroups() == null)
			account.setGrantedGroups(Collections.EMPTY_LIST);
		if (account.getManagerGroups() == null)
			account.setManagerGroups(Collections.EMPTY_LIST);
		if (account.getOwnerGroups() == null)
			account.setOwnerGroups(Collections.EMPTY_LIST);
		if (account.getGrantedUsers() == null)
			account.setGrantedUsers(Collections.EMPTY_LIST);
		if (account.getManagerUsers() == null)
			account.setManagerUsers(Collections.EMPTY_LIST);
		if (account.getOwnerUsers() == null)
			account.setOwnerUsers(Collections.EMPTY_LIST);
		if (account.getGrantedRoles() == null)
			account.setGrantedRoles(Collections.EMPTY_LIST);
		if (account.getManagerRoles() == null)
			account.setManagerRoles(Collections.EMPTY_LIST);
		if (account.getOwnerRoles() == null)
			account.setOwnerRoles(Collections.EMPTY_LIST);
		@SuppressWarnings("unchecked")
		List<Grup> newgrups []= new List[] {
			new LinkedList<Grup>(account.getGrantedGroups()),
			new LinkedList<Grup>(account.getManagerGroups()),
			new LinkedList<Grup>(account.getOwnerGroups())
		};
		@SuppressWarnings("unchecked")
		List<Rol> newroles []= new List[] {
			new LinkedList<Rol>(account.getGrantedRoles()),
			new LinkedList<Rol>(account.getManagerRoles()),
			new LinkedList<Rol>(account.getOwnerRoles())
		};
		@SuppressWarnings("unchecked")
		List<Usuari> newusers []= new List[] {
			new LinkedList<Usuari>(account.getGrantedUsers()),
			new LinkedList<Usuari>(account.getManagerUsers()),
			new LinkedList<Usuari>(account.getOwnerUsers())
		};
		// Remove grants
		for (Iterator<AccountAccessEntity> aclIterator = acc.getAcl().iterator(); aclIterator.hasNext();)
		{
			AccountAccessEntity access = aclIterator.next();
			for (int index = 0; index < levels.length; index++)
			{
				if (levels[index] == access.getLevel())
				{
					boolean found = false;
					if (access.getGroup() != null)
					{
						for (Iterator<Grup> it = newgrups[index].iterator(); !found && it.hasNext();)
						{
							Grup g = it.next();
							if (g.getId().equals (access.getGroup().getId()))
							{
								it.remove();
								found = true;
							}
						}
					}
					else if (access.getRol() != null)
					{
						for (Iterator<Rol> it = newroles[index].iterator(); !found && it.hasNext();)
						{
							Rol r = it.next();
							if (r.getId().equals (access.getRol().getId()))
							{
								it.remove();
								found = true;
							}
						}
					}
					else if (access.getUser() != null)
					{
						for (Iterator<Usuari> it = newusers[index].iterator(); !found && it.hasNext();)
						{
							Usuari u = it.next();
							if (u.getId().equals (access.getUser().getId()))
							{
								it.remove();
								found = true;
							}
						}
					}
					if (!found)
					{
						aclIterator.remove();
						notifyAccountPasswordChange(access.getAccount(),
							access.getGroup(), access.getRol(), access.getUser());
						getAccountAccessEntityDao().remove(access);
					}
				}
			}
		}
		// Add new groups
		for (int index = 0 ; index < levels.length; index++)
		{
			for (Grup g: newgrups[index]) {
				GrupEntity ge = getGrupEntityDao().load(g.getId());
				if (ge != null)
				{
					AccountAccessEntity access = getAccountAccessEntityDao().newAccountAccessEntity();
					access.setGroup(ge);
					access.setAccount(acc);
					access.setLevel(levels[index]);
					getAccountAccessEntityDao().create(access);
					acc.getAcl().add(access);
					
					notifyAccountPasswordChange(acc, ge, null, null);
				}
			}
			// Add new roles
			for (Rol r: newroles[index]) {
				RolEntity re = getRolEntityDao().load(r.getId());
				if (re != null)
				{
					AccountAccessEntity access = getAccountAccessEntityDao().newAccountAccessEntity();
					access.setRol(re);
					access.setAccount(acc);
					access.setLevel(levels[index]);
					getAccountAccessEntityDao().create(access);
					acc.getAcl().add(access);
					
					notifyAccountPasswordChange(acc, null, re, null);
				}
			}
			// Add new users
			for (Usuari u: newusers[index]) {
				UsuariEntity ue = getUsuariEntityDao().load(u.getId());
				if (ue != null)
				{
					AccountAccessEntity access = getAccountAccessEntityDao().newAccountAccessEntity();
					access.setUser(ue);
					access.setAccount(acc);
					access.setLevel(levels[index]);
					getAccountAccessEntityDao().create(access);
					acc.getAcl().add(access);
					
					notifyAccountPasswordChange(acc, null, null, ue);
				}
			}
		}
	}

	@Override
	protected void handleUpdateAccount(es.caib.seycon.ng.comu.Account account)
			throws Exception
	{
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		
		if (! account.getType().equals( ae.getType() ) )
		{
			if (ae.getType().equals(AccountType.USER))
			{
				account.setOwnerUsers(new LinkedList<Usuari>());
				for (UserAccountEntity ua: ae.getUsers())
				{
					Usuari u = getUsuariService().getUserInfo(ua.getUser().getCodi());
					getUserAccountEntityDao().remove(ua);
					account.getOwnerUsers().add(u);
				}
				
			}
			if (account.getType().equals(AccountType.USER))
			{
				if (account.getOwnerUsers().size() != 1 ||
					!account.getOwnerRoles().isEmpty() ||
					!account.getOwnerGroups().isEmpty() ||
					!account.getManagerUsers().isEmpty() ||
					!account.getManagerRoles().isEmpty() ||
					!account.getManagerGroups().isEmpty() ||
					!account.getGrantedUsers().isEmpty() ||
					!account.getGrantedRoles().isEmpty() ||
					!account.getGrantedGroups().isEmpty())
					throw new InternalErrorException(Messages.getString("AccountServiceImpl.CannotChangeSharedAccount")); //$NON-NLS-1$
				
				Usuari owner = account.getOwnerUsers().iterator().next();
				
				UsuariEntity ue = getUsuariEntityDao().load(owner.getId());
				UserAccountEntity uae = getUserAccountEntityDao().newUserAccountEntity();
				uae.setAccount(ae);
				uae.setUser(ue);
				getUserAccountEntityDao().create(uae);
				account.setDescription(owner.getFullName());

				createUserTask(ue);

			}
			ae.setType(account.getType());
		}

		if (! account.getName().equals(ae.getName()))
		{
			if (getAccountEntityDao().findByNameAndDispatcher(account.getName(), ae.getDispatcher().getCodi()) != null)
				throw new AccountAlreadyExistsException(
						String.format(Messages.getString("AccountServiceImpl.AccountAlreadyExists"), //$NON-NLS-1$
						account.getName()+"@"+ae.getDispatcher().getCodi()));
		}
		getAccountEntityDao().accountToEntity(account, ae, false);

		if (account.getType().equals(AccountType.USER))
			removeAcl (ae);
		else
			updateAcl(ae, account);
		getAccountEntityDao().update(ae);
		createAccountTask(ae);
	}

	private void createUserTask(UsuariEntity ue) {
		TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
		tasque.setTransa(TaskHandler.UPDATE_USER);
		tasque.setUsuari(ue.getCodi());
		getTasqueEntityDao().create(tasque);
	}

	private void removeAcl(AccountEntity ae) {
		for (AccountAccessEntity aae: ae.getAcl())
		{
			getAccountAccessEntityDao().remove(aae);
		}
	}

	@Override
	protected void handleRemoveAccount(es.caib.seycon.ng.comu.Account account)
			throws Exception
	{
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		for (AccountAccessEntity aae: ae.getAcl())
		{
			getAccountAccessEntityDao().remove(aae);
		}
		createAccountTask(ae);
		getAccountEntityDao().remove(ae);
	}

	private void createAccountTask(AccountEntity ae)
	{
		if (! ae.getType().equals(AccountType.IGNORED))
		{
			TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
			tasque.setTransa(TaskHandler.UPDATE_ACCOUNT);
			tasque.setUsuari(ae.getName());
			tasque.setCoddis(ae.getDispatcher().getCodi());
			getTasqueEntityDao().create(tasque);
		}
	}
	
	private void notifyAccountPasswordChange(AccountEntity ae, GrupEntity ge,
		RolEntity rolEntity, UsuariEntity usuariEntity)
	{
		if (! ae.getType().equals(AccountType.IGNORED))
		{
			TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
			tasque.setTransa(TaskHandler.NOTIFY_PASSWORD_CHANGE);
			tasque.setCoddis(ae.getDispatcher().getCodi());
			
			// Check notify to group
			if (ge != null)
				tasque.setGrup(ge.getId().toString());
			
			// Check notify to role
			if (rolEntity != null)
				tasque.setRole(rolEntity.getId().toString());
			
			// Check notify to user
			if (usuariEntity != null)
				tasque.setUsuari(usuariEntity.getCodi());
			
			getTasqueEntityDao().create(tasque);
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException
	{
		this.applicationContext = applicationContext;
	}

	@Override
	protected Account handleFindAccount(String accountName, String dispatcherName)
			throws Exception
	{
		AccountEntity acc = getAccountEntityDao().findByNameAndDispatcher(accountName, dispatcherName);
		if (acc == null)
			return null;
		
		if (acc.getType().equals (AccountType.USER) && acc.getUsers().size() == 1)
		{
			return getUserAccountEntityDao().toUserAccount(acc.getUsers().iterator().next());
		}
		else
			return getAccountEntityDao().toAccount(acc);
	}

	@Override
	protected List<UserAccount> handleFindUserAccounts(String userName,
			String dispatcherName) throws Exception
	{
		if (!AutoritzacionsUsuari.hasQueryAccount())
		{
			return Collections.emptyList();
		}
		else
		{
    		List<UserAccountEntity> accounts = getUserAccountEntityDao().findByUserAndDispatcher (userName, dispatcherName);
    		return getUserAccountEntityDao().toUserAccountList(accounts);
		}
	}

	@Override
	protected void handleGenerateUserAccounts(String user) throws Exception
	{
		UsuariEntity ue = getUsuariEntityDao().findByCodi(user);
		if (ue == null)
			return;
		
		DispatcherEntityDao disDao = getDispatcherEntityDao();
		for (DispatcherEntity disEntity: disDao.loadAll())
		{
			if (disEntity.getManualAccountCreation() != null && disEntity.getManualAccountCreation().booleanValue())
			{
    			List<AccountEntity> accs = getAccountEntityDao().findByUsuariAndDispatcher(user, disEntity.getCodi());
				for (AccountEntity acc : accs)
				{
					if (acc.isDisabled() )
					{
						acc.setDisabled(false);
						getAccountEntityDao().update(acc);
					}
				}
			}
			else if (disEntity.isMainDispatcher() || disEntity.getUrl() != null)
			{
    			Dispatcher dis = disDao.toDispatcher(disEntity);
    			List<AccountEntity> accs = getAccountEntityDao().findByUsuariAndDispatcher(user, dis.getCodi());
    			String description = ue.getCodi()+" - "+ue.getFullName(); //$NON-NLS-1$
    			if (description.length() > 50)
    				description = description.substring(0, 47) + "..."; //$NON-NLS-1$
    			if ("S".equals(ue.getActiu()) && getDispatcherService().isUserAllowed(dis, user)) //$NON-NLS-1$
    			{
    				if (accs.isEmpty())
    				{
    					try
    					{
    						generateAccount(null, ue, disEntity);
    					}
    					catch (Exception e)
    					{
    						LogFactory.getLog(getClass()).warn(
    								String.format(Messages.getString("AccountServiceImpl.ErrorGeneratinAccount"), user, dis.getCodi()),  //$NON-NLS-1$
    								e);
    					}
    				} 
    				else
    				{
    					for (AccountEntity acc : accs)
    					{
    						if (acc.isDisabled() || ! description.equals (acc.getDescription()))
    						{
    							acc.setDisabled(false);
    							acc.setDescription(description);
    							getAccountEntityDao().update(acc);
    						}
    					}
    				}
    			}
    			else if (! accs.isEmpty())
    			{
    				for (AccountEntity acc : accs)
    				{
    					if (! acc.isDisabled() || ! description.equals (acc.getDescription()))
    					{
    						acc.setDisabled(true);
    						acc.setDescription(description);
    						getAccountEntityDao().update(acc);
    					}
    				}
    			}
			}
		}
	}

	@Override
	protected List<UserAccount> handleFindUserAccountsByDomain(String user,
			String passwordDomain) throws Exception
	{
		List<AccountEntity> entities = getAccountEntityDao().findByUserAndDomain(user, passwordDomain);
		List<UserAccount> accounts = new LinkedList<UserAccount>();
		for ( AccountEntity acc: entities)
		{
			if (acc.getType().equals(AccountType.USER))
			{
				accounts.addAll( getUserAccountEntityDao().toUserAccountList(acc.getUsers()));
			}
		}
		return accounts;
	}

	@Override
	protected void handleRenameAccount(Account account) throws Exception
	{
		AccountEntityDao dao = getAccountEntityDao();

		AccountEntity accountEntity = dao.load(account.getId());
		
		AccountEntity oldAccount = dao.findByNameAndDispatcher(account.getName(), accountEntity.getDispatcher().getCodi());
		if (oldAccount == null)
		{
			createAccountTask(accountEntity);
			accountEntity.setName(account.getName());
			dao.update(accountEntity);
			createAccountTask(accountEntity);
		}
		else if (! oldAccount.getId().equals(accountEntity.getId()))
		{
			throw new AccountAlreadyExistsException(account.getName() + "@" + account.getDispatcher());
		}
	}

	@Override
	protected String handleGessAccountName(String userName, String dispatcherName)
			throws Exception
	{
		DispatcherEntity dispatcher = getDispatcherEntityDao().findByCodi(dispatcherName);
		if (dispatcher.getManualAccountCreation() != null && dispatcher.getManualAccountCreation().booleanValue())
			return null;
		
		DominiUsuariEntity du = getDominiUsuariEntityDao().findByDispatcher (dispatcherName);
		// Search if already has a user name for this user domain
		
		UsuariEntity ue = getUsuariEntityDao().findByCodi(userName);
		if (ue == null)
			return null;

		if (du.getTipus().equals (TipusDominiUsuariEnumeration.PRINCIPAL))
			return userName;
		else if (du.getTipus().equals(TipusDominiUsuariEnumeration.SHELL))
		{
			Interpreter interpreter = new Interpreter();
			DispatcherEntity de = getDispatcherEntityDao().findByCodi(dispatcherName);
			interpreter.set("usuariEntity", ue); //$NON-NLS-1$
			interpreter.set("user", User.toUser(getUsuariEntityDao().toUsuari(ue))); //$NON-NLS-1$
			interpreter.set("dominiEntity", du); //$NON-NLS-1$
			interpreter.set("userDomain", UserDomain.toUserDomain(getDominiUsuariEntityDao().toDominiUsuari(du))); //$NON-NLS-1$
			interpreter.set("dispatcherEntity", de); //$NON-NLS-1$
			interpreter.set("system", com.soffid.iam.api.System.toSystem(getDispatcherEntityDao().toDispatcher(de))); //$NON-NLS-1$
			interpreter.set("dao", getAccountEntityDao()); //$NON-NLS-1$
			return (String) interpreter.eval(du.getBshExpr());
		}
		else if (du.getTipus().equals(TipusDominiUsuariEnumeration.SPRINGCLASS))
		{
			Object obj = applicationContext.getBean(du.getBeanGenerator());
			if (obj == null)
				throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.UknownBeanForDomain"), du.getBeanGenerator(), du.getCodi())); //$NON-NLS-1$
			if (! (obj instanceof AccountNameGenerator))
				throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.BeanNotImplementNameGenerator"), du.getBeanGenerator())); //$NON-NLS-1$
			AccountNameGenerator generator = (AccountNameGenerator) obj;
			DispatcherEntity de = getDispatcherEntityDao().findByCodi(dispatcherName);
			return generator.getAccountName(ue, de);
		}
		else
			return null;
	}

	@Override
	protected List<Account> handleFindAccountsByCriteria(AccountCriteria criteria)
		throws Exception
	{
		AccountEntityDao dao = getAccountEntityDao();
		int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
		if ((criteria.getDescription() != null) &&
			criteria.getDescription().equals("%")) //$NON-NLS-1$
		{
			criteria.setDescription(null);
		}
		
		if ((criteria.getDispatcher() != null) &&
			criteria.getDispatcher().equals("%")) //$NON-NLS-1$
		{
			criteria.setDispatcher(null);
		}
		
		if ((criteria.getExcludeType() != null) &&
			criteria.getExcludeType().equals("%")) //$NON-NLS-1$
		{
			criteria.setExcludeType(null);
		}
		
		if ((criteria.getGrantedGroups() != null) &&
			criteria.getGrantedGroups().equals("%")) //$NON-NLS-1$
		{
			criteria.setGrantedGroups(null);
		}
		
		if ((criteria.getGrantedRoles() != null) &&
			criteria.getGrantedRoles().equals("%")) //$NON-NLS-1$
		{
			criteria.setGrantedRoles(null);
		}
		
		if ((criteria.getGrantedUsers() != null) &&
			criteria.getGrantedUsers().equals("%")) //$NON-NLS-1$
		{
			criteria.setGrantedUsers(null);
		}
		
		if ((criteria.getName() != null) && criteria.getName().equals("%")) //$NON-NLS-1$
		{
			criteria.setName(null);
		}
		
		List<AccountEntity> result = dao.findByCriteria(criteria);
		
		// Check maximum number of results
		if (result.size() > limitResults)
		{
			return dao.toAccountList(result).subList(0, limitResults);
		}
		
		return dao.toAccountList(result);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountService#getAccountUsers(es.caib.seycon.ng.comu.Account)
	 */
	public Collection<String> handleGetAccountUsers (Account account) throws InternalErrorException
	{
		return handleGetAccountUsers(account, AccountAccessLevelEnum.ACCESS_OWNER);
	}
	
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountService#getAccountUsers(es.caib.seycon.ng.comu.Account)
	 */
	public Collection<String> handleGetAccountUsers (Account account, AccountAccessLevelEnum level) throws InternalErrorException
	{
		Set<String> users = new HashSet<String>();
		AccountEntity acc = getAccountEntityDao().load(account.getId());
		if (acc.getType().equals(AccountType.USER))
		{
			for (UserAccountEntity uae: acc.getUsers())
			{
				users.add(uae.getUser().getCodi());
			}
		} else {
			for (AccountAccessEntity aae: acc.getAcl())
			{
				if (aae.getGroup() != null && isGreaterOrIqualThan(aae.getLevel(), level))
				{
					addGroupMembers(aae.getGroup(), users);
				}
				if (aae.getRol() != null && isGreaterOrIqualThan(aae.getLevel(), level))
				{
					addRolMembers (aae.getRol(), users);
				}
				if (aae.getUser() != null && isGreaterOrIqualThan(aae.getLevel(), level))
				{
					users.add(aae.getUser().getCodi());
				}
			}
		}
		
		return users;
	}

	/**
	 * @param rol
	 * @param users
	 * @throws InternalErrorException 
	 */
	private void addRolMembers (RolEntity rol, Set<String> users) throws InternalErrorException
	{
		Collection<RolGrant> grants = getAplicacioService().findEffectiveRolGrantsByRolId(rol.getId());
		for (RolGrant grant: grants)
		{
			if ( grant.getUser() != null)
				users.add (grant.getUser());
		}
	}

	/**
	 * @param group
	 * @param users
	 */
	private void addGroupMembers (GrupEntity group, Set<String> users)
	{
		for (UsuariEntity u: group.getUsuarisGrupPrimari())
		{
			users.add(u.getCodi());
		}
		for (UsuariGrupEntity ug: group.getUsuarisGrupSecundari())
		{
			users.add (ug.getUsuari().getCodi());
		}
		for (GrupEntity child: group.getFills())
		{
			addGroupMembers(child, users);
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleGetUserGrantedAccounts(es.caib.seycon.ng.comu.Usuari)
	 */
	@Override
	protected Collection<Account> handleGetUserGrantedAccounts (Usuari usuari)
					throws Exception
	{
		return handleGetUserGrantedAccounts(usuari, AccountAccessLevelEnum.ACCESS_USER);
	}

	/**
	 * @param grupPrimari
	 * @param accounts
	 */
	private void addGrantedAccounts (GrupEntity grup, Set<AccountEntity> accounts, AccountAccessLevelEnum level)
	{
		for (AccountAccessEntity aae: grup.getAccountAccess())
		{
			if (isGreaterOrIqualThan(aae.getLevel(), level))
			{
				accounts.add(aae.getAccount());
			}
		}
		if (grup.getPare() != null)
			addGrantedAccounts(grup.getPare(), accounts, level);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleUpdateAccountLastUpdate(es.caib.seycon.ng.comu.Account)
	 */
	@Override
	protected void handleUpdateAccountLastUpdate (Account account) throws Exception
	{
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		
		if (ae != null)
		{
			ae.setLastUpdated(new Date());
			getAccountEntityDao().update(ae);
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleUpdateAccountPasswordDate(es.caib.seycon.ng.comu.Account, java.lang.Long)
	 */
	@Override
	protected void handleUpdateAccountPasswordDate (Account account, Long passwordTerm)
					throws Exception
	{
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		
		if (ae != null)
		{
			if (passwordTerm != null)
			{
				Calendar expiration = Calendar.getInstance();
				expiration.add(Calendar.DAY_OF_MONTH, passwordTerm.intValue());
				ae.setPasswordExpiration(expiration.getTime());
			}
			else
				ae.setPasswordExpiration(null);
			ae.setLastPasswordSet(new Date());
			getAccountEntityDao().update(ae);
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleUpdateAccountPasswordDate(es.caib.seycon.ng.comu.Account, java.lang.Long)
	 */
	@Override
	protected void handleUpdateAccountPasswordDate2 (Account account, Date passwordTerm)
					throws Exception
	{
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		
		if (ae != null ) {
			ae.setPasswordExpiration(passwordTerm);
			ae.setLastPasswordSet(new Date());
			getAccountEntityDao().update(ae);
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleQueryAccountPassword(es.caib.seycon.ng.comu.Account)
	 */
	@Override
	protected Password handleQueryAccountPassword (Account account) throws Exception
	{
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		// Check if policy allows user change
		DominiUsuariService dominiUsuariService = getDominiUsuariService();
		PoliticaContrasenya politica = dominiUsuariService.findPoliticaByTipusAndDominiContrasenyas(
						ae.getPasswordPolicy().getCodi(), 
						ae.getDispatcher().getDomini().getCodi());
		if (politica == null)
			throw new BadPasswordException(Messages.getString("AccountServiceImpl.NoPolicyDefined")); //$NON-NLS-1$
		if (!politica.isAllowPasswordQuery())
			throw new BadPasswordException(Messages.getString("AccountServiceImpl.NotAllowedToQueryPassword")); //$NON-NLS-1$
		

		Usuari usuari = AutoritzacionsUsuari.getCurrentUsuari();
		
		
		ServerEntityDao dao = getServerEntityDao();
		Exception lastException = null;
		for (ServerEntity se: dao.loadAll())
		{
			if (se.getType().equals(ServerType.MASTERSERVER))
			{
    			try {
    		        RemoteServiceLocator rsl = new RemoteServiceLocator(se.getNom());
    	            rsl.setAuthToken(se.getAuth());
    	            
    	            SyncStatusService sss = rsl.getSyncStatusService();
    	            Password p = sss.getAccountPassword(usuari.getCodi(), account.getId());
    	            if (p != null)
    	            	return p;
    			} catch (Exception e)
    			{
    				lastException = e;
    			}
			}
		}
		if (lastException != null)
			throw lastException;
		return null;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleSetAccountPassword(es.caib.seycon.ng.comu.Account, es.caib.seycon.ng.comu.Password)
	 */
	@Override
	protected void handleSetAccountPassword (Account account, Password password)
					throws Exception
	{
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		String principal = Security.getPrincipal().getName();
		InternalPasswordService ips = getInternalPasswordService ();
		
		if (ae.getType().equals(AccountType.PRIVILEGED))
		{
			throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.NotAuthorizedToChangePassword"))); //$NON-NLS-1$
		}

		if (! Security.isUserInRole(Security.AUTO_ACCOUNT_PASSWORD))
		{
			String dispatcher = ips.getDefaultDispatcher();
			AccountEntity caller = getAccountEntityDao().findByNameAndDispatcher(principal, dispatcher);
			if (caller == null)
				throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.AccountNotFound"), principal, dispatcher)); //$NON-NLS-1$
			if (caller.getId() != ae.getId())
			{
				UsuariEntity callerUe = getUserForAccount(caller);
				if (ae.getType().equals(AccountType.USER))
				{
					UsuariEntity ue2 = getUserForAccount(ae);
					if (ue2 != null)
					{
						if (callerUe == null)
							throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NoChangePasswordAuthorized"))); //$NON-NLS-1$
						
						if (! callerUe.getId().equals(ue2.getId()) &&
							!AutoritzacionsUsuari.canUpdateUserPassword(ue2.getGrupPrimari().getCodi()))
							throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NoChangePasswordAuthorized"))); //$NON-NLS-1$
					}
					else
						throw new InternalErrorException(Messages.getString("AccountServiceImpl.AccounNotBounForUser")); //$NON-NLS-1$
				}
				else if (ae.getType().equals(AccountType.IGNORED))
				{
					throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NoAuthorizedChangePassAccDisabled"))); //$NON-NLS-1$
				}
				else if (ae.getType().equals(AccountType.SHARED))
				{
					if (callerUe == null)
						throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NoChangePasswordAuthorized"))); //$NON-NLS-1$
					Collection<String> users = handleGetAccountUsers(account, AccountAccessLevelEnum.ACCESS_MANAGER);
					boolean found = false;
					for ( String user: users)
					{
						if (user.equals(callerUe.getCodi()))
						{
							found = true;
							break;
						}
					}

					if (!found)
						throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NotAuthorizedChangePassForAccount"))); //$NON-NLS-1$
				}
			}
			// Check if policy allows user change
			DominiUsuariService dominiUsuariService = getDominiUsuariService();
			PoliticaContrasenya politica = dominiUsuariService.findPoliticaByTipusAndDominiContrasenyas(
							ae.getPasswordPolicy().getCodi(), 
							ae.getDispatcher().getDomini().getCodi());
			if (politica == null)
				throw new BadPasswordException(Messages.getString("AccountServiceImpl.NoPolicyDefined")); //$NON-NLS-1$
			if (!politica.isAllowPasswordChange())
				throw new BadPasswordException(Messages.getString("AccountServiceImpl.NotAllowedToChangePassword")); //$NON-NLS-1$
		}
		
		/// Now, do the job
        PolicyCheckResult check = ips.checkAccountPolicy(ae, password);
        if (! check.isValid()) {
            throw new BadPasswordException(check.getReason());
        }
		ips.storeAndForwardAccountPassword(ae, password, false, null);
		// Now, audit
		audit("P", ae); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleSetHPAccountPassword(es.caib.seycon.ng.comu.Account, es.caib.seycon.ng.comu.Password, boolean)
	 */
	@Override
	protected void handleSetHPAccountPassword (Account account, Password password,
					java.util.Date date, boolean force) throws Exception
	{
		AccountEntity ae = getAccountEntityDao().load(account.getId());

		if (! ae.getType().equals(AccountType.PRIVILEGED))
		{
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NotAuthorizedChangePassword"))); //$NON-NLS-1$
		}


		String principal = Security.getPrincipal().getName();
		InternalPasswordService ips = getInternalPasswordService ();
		String dispatcher = ips.getDefaultDispatcher();
		AccountEntity caller = getAccountEntityDao().findByNameAndDispatcher(principal, dispatcher);
		if (caller == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.AccountNotFound"), principal, dispatcher)); //$NON-NLS-1$
		UsuariEntity callerUe = getUserForAccount(caller);
		if (callerUe == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.UserNotFoundForAccount"), principal, dispatcher)); //$NON-NLS-1$

		if (! Security.isUserInRole(Security.AUTO_ACCOUNT_PASSWORD))
		{
			if (caller.getId() != ae.getId())
			{
				if (ae.getType().equals(AccountType.USER))
				{
					throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NoChangeUserPasswordAuthorized"))); //$NON-NLS-1$
				}
				else if (ae.getType().equals(AccountType.IGNORED))
				{
					throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NoAuthorizedChangePassAccDisabled"))); //$NON-NLS-1$
				}
				else if (ae.getType().equals(AccountType.PRIVILEGED))
				{
					if (callerUe == null)
						throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NoChangePasswordAuthorized"))); //$NON-NLS-1$
					Collection<String> users = handleGetAccountUsers(account, AccountAccessLevelEnum.ACCESS_MANAGER);
					boolean found = false;
					for ( String user: users)
					{
						if (user.equals(callerUe.getCodi()))
						{
							found = true;
							break;
						}
					}
					if (!found)
						throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NotAuthorizedChangePassForAccount"))); //$NON-NLS-1$
				}
				else if (ae.getType().equals(AccountType.SHARED))
				{
					throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NotChangePasswordAccountShared"))); //$NON-NLS-1$
				}
			}
			// Check if policy allows user change
			DominiUsuariService dominiUsuariService = getDominiUsuariService();
			PoliticaContrasenya politica = dominiUsuariService.findPoliticaByTipusAndDominiContrasenyas(
							ae.getPasswordPolicy().getCodi(), 
							ae.getDispatcher().getDomini().getCodi());
			if (politica == null)
				throw new BadPasswordException(Messages.getString("AccountServiceImpl.NoPolicyDefined")); //$NON-NLS-1$
			if (!politica.isAllowPasswordChange())
				throw new BadPasswordException(Messages.getString("AccountServiceImpl.NotAllowedToChangePassword")); //$NON-NLS-1$
		}
		
		if (! force )
		{
			if ( ! ae.getUsers().isEmpty())
			{
				UsuariEntity currentUser = ae.getUsers().iterator().next().getUser();
				if (! currentUser.getId().equals(callerUe.getId()))
					throw new SecurityException(String.format("Cannot change password. The current owner is %s", currentUser.getCodi()));
			}
		}
		/// Now, do the job
        PolicyCheckResult check = ips.checkAccountPolicy(ae, password);
        if (! check.isValid()) {
            throw new BadPasswordException(check.getReason());
        }
		ips.storeAndForwardAccountPassword(ae, password, false, date);
//		ae.setPasswordExpiration(date);
		
		// Remove previous assignments
		UserAccountEntityDao dao = getUserAccountEntityDao();
		for (UserAccountEntity uae: ae.getUsers())
		{
			dao.remove(uae);
		}
		// Register current assignment
		UserAccountEntity uae = getUserAccountEntityDao().newUserAccountEntity();
		uae.setAccount(ae);
		uae.setUser(callerUe);
		uae.setUntilDate(date);
		dao.create(uae);
		// Now, audit
		audit("P", ae); //$NON-NLS-1$
	}
	
	private UsuariEntity getUserForAccount (AccountEntity acc)
	{
		UsuariEntity ue = null;
		if (acc.getType().equals(AccountType.USER))
		{
			for (UserAccountEntity uae: acc.getUsers())
			{
				ue = uae.getUser();
			}
		}
		return ue;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountService#getUserAccounts(es.caib.seycon.ng.comu.Usuari)
	 */
	public Collection<UserAccount> handleGetUserAccounts (Usuari usuari)
		 			throws InternalErrorException
	{
		if (!AutoritzacionsUsuari.hasQueryAccount())
		{
			Usuari caller = AutoritzacionsUsuari.getCurrentUsuari();
			if (caller.getId() != usuari.getId())
				throw new SecurityException(Messages.getString("AccountServiceImpl.PermissionDenied")); //$NON-NLS-1$
		}
		UsuariEntity ue = getUsuariEntityDao().load(usuari.getId());
		Collection<UserAccount> list = new LinkedList<UserAccount>();
		UserAccountEntityDao ueaDao = getUserAccountEntityDao();
		for (UserAccountEntity uae: ue.getAccounts())
		{
			if (uae.getAccount().getType().equals (AccountType.USER))
				list.add (ueaDao.toUserAccount(uae));
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleLoad(java.lang.Long)
	 */
	@Override
	protected Account handleLoad (Long identifier) throws Exception
	{
		AccountEntity accountEntity = getAccountEntityDao().load(identifier);
		if ( accountEntity == null)
			return null;
		if (accountEntity.getType().equals(AccountType.USER))
		{	
			for (UserAccountEntity userAccountEntity: accountEntity.getUsers())
				return getUserAccountEntityDao().toUserAccount(userAccountEntity);
			return null;
		}
		else
		{
			return getAccountEntityDao().toAccount(accountEntity);
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleIsUpdatePending(es.caib.seycon.ng.comu.Account)
	 */
	@Override
	protected boolean handleIsUpdatePending (Account account) throws Exception
	{
		AccountEntity accEntity = getAccountEntityDao().load(account.getId());
		if (accEntity == null)
			return false;
		
		Account account2 = getAccountEntityDao().toAccount(accEntity);
		account.setLastPasswordSet(account2.getLastPasswordSet());
		account.setLastUpdated(account2.getLastUpdated());
		account.setPasswordExpiration(account2.getPasswordExpiration());

		List<TasqueEntity> coll = getTasqueEntityDao().findByAccount (accEntity.getName(), accEntity.getDispatcher().getCodi());
		for (TasqueEntity tasque: coll)
		{
			if (tasque.getTransa().equals (TaskHandler.UPDATE_ACCOUNT) ||
				tasque.getTransa().equals (TaskHandler.UPDATE_ACCOUNT_PASSWORD) ||
				tasque.getTransa().equals (TaskHandler.PROPAGATE_ACCOUNT_PASSWORD))
				return true;
		}

		if (accEntity.getType().equals(AccountType.USER))
		{
			for (UserAccountEntity ua: accEntity.getUsers())
			{
				coll = getTasqueEntityDao().findByUser(ua.getUser().getCodi());
				for (TasqueEntity tasque: coll)
				{
					if ( tasque.getTransa().equals (TaskHandler.UPDATE_USER)  
						 || accEntity.getDispatcher().getDomini().getCodi().equals(tasque.getDominiContrasenyes()) &&
						 		(tasque.getTransa().equals (TaskHandler.UPDATE_USER_PASSWORD) ||
								 tasque.getTransa().equals (TaskHandler.PROPAGATE_PASSWORD))
						 )
					{
						// Check tasklog => If not found or is not complete
						boolean found = false;
						for (TaskLogEntity tl: tasque.getLogs())
						{
							if (tl.getDispatcher().getId().equals (accEntity.getDispatcher().getId()))
							{
								found = true;
								if (! "S".equals(tl.getComplet())) //$NON-NLS-1$
									return true;
							}
						}
						if (!found)
							return true;
					}
				}
			}
		}
		return false;
		
	}

    private void audit(String action, AccountEntity account) throws Exception {

        String codiUsuariCanvi = Security.getCurrentAccount();
        // Fem un nestedlogin per obtindre autorització per fer auditoria

        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(action); //$NON-NLS-1$
        auditoria.setAccount(account.getName());
        auditoria.setBbdd(account.getDispatcher().getCodi());
        auditoria.setAutor(codiUsuariCanvi);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(Calendar.getInstance().getTime()));
        auditoria.setObjecte("SC_ACCOUN"); //$NON-NLS-1$
        if (account.getType().equals (AccountType.USER))
        {
        	for (UserAccountEntity ua: account.getUsers())
        		auditoria.setUsuari(ua.getUser().getCodi());
        }

        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    /* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleFindAccountsNearToExpire(java.util.Date, java.util.Date, java.util.Collection, java.util.Collection)
	 */
	@Override
	protected List<Account> handleFindAccountsNearToExpire (Date currentDate,
		Date limitDate, Collection<AccountType> accTypes,
		Collection<TipusUsuari> userTypes) throws Exception
	{
		String query;												// Query to execute
		String element;												// Element to add to query
		List<Account> accountList = new LinkedList<Account>();		// Accounts list
		List<Parameter> paramsList = new LinkedList<Parameter>();	// Parameters list
		Collection accountEntities = null;							// Accounts entities
		Iterator it = null;											// Iterator to manage list
		
		query = "select account " + //$NON-NLS-1$
			"from es.caib.seycon.ng.model.AccountEntity as account " + //$NON-NLS-1$
			"where account.passwordExpiration between :currentDate and :limitDate"; //$NON-NLS-1$
		
		paramsList.add(new Parameter("currentDate", currentDate)); //$NON-NLS-1$
		paramsList.add(new Parameter("limitDate", limitDate)); //$NON-NLS-1$
		
		// Check accounts types to search
		if (accTypes != null && !accTypes.isEmpty())
		{
			query += " and ("; //$NON-NLS-1$
			
			it = accTypes.iterator();
			while (it.hasNext())
			{
				element = it.next().toString();
				query += "type = :type" + element; //$NON-NLS-1$
				paramsList.add(new Parameter("type" + element, element)); //$NON-NLS-1$
				
				// Check last element
				if (it.hasNext())
				{
					query += " or "; //$NON-NLS-1$
				}
			}
			
			query += ") "; //$NON-NLS-1$
		}
		
		// Check user types to search
		if (userTypes != null && !userTypes.isEmpty())
		{
			query += " and ("; //$NON-NLS-1$
			
			Iterator<TipusUsuari> it2 = userTypes.iterator();
			while (it2.hasNext())
			{
				TipusUsuari element2 = it2.next();
				query += "passwordPolicy.codi = :passwordPolicy" + element2.getCodi(); //$NON-NLS-1$
				paramsList.add(new Parameter("passwordPolicy" + element2.getCodi(), //$NON-NLS-1$
					element2.getCodi()));
				
				// Check last element
				if (it.hasNext())
				{
					query += " or "; //$NON-NLS-1$
				}
			}
			
			query += ") "; //$NON-NLS-1$
		}
		
		query += " order by account.passwordExpiration"; //$NON-NLS-1$
		
		accountEntities = getAccountEntityDao().query(query,
			paramsList.toArray(new Parameter[0]));
		
		if (accountEntities != null && !accountEntities.isEmpty())
		{
			accountList = getAccountEntityDao().toAccountList(accountEntities);
			
			accountList = SetAccountUserTypes(accountList);
		}
		
		return accountList;
	}

	/**
	 * Functionality to add users types description in
	 * the password policy section for each account in list.
	 * @param accountList Account list to process.
	 * @return Account list processed.
	 */
	private List<Account> SetAccountUserTypes (List<Account> accountList)
	{
		TipusUsuariEntityDao userTypeDAO = getTipusUsuariEntityDao();
		List<Account> accounts = new LinkedList<Account>();
		
		for (Account account : accountList)
		{
			account.setPasswordPolicy(userTypeDAO
				.findByCodi(account.getPasswordPolicy()).getDescripcio());
			
			accounts.add(account);
		}

		return accounts;
	}

	@Override
	protected Usuari handleGetHPAccountOwner(Account account) throws Exception {
		AccountEntity ae = getAccountEntityDao().load(account.getId());

		if (! ae.getType().equals(AccountType.PRIVILEGED))
		{
			return null;
		}


		String principal = Security.getPrincipal().getName();
		InternalPasswordService ips = getInternalPasswordService ();
		String dispatcher = ips.getDefaultDispatcher();
		AccountEntity caller = getAccountEntityDao().findByNameAndDispatcher(principal, dispatcher);
		if (caller == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.AccountNotFound"), principal, dispatcher)); //$NON-NLS-1$
		UsuariEntity callerUe = getUserForAccount(caller);
		if (callerUe == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.UserNotFoundForAccount"), principal, dispatcher)); //$NON-NLS-1$

		if (! Security.isUserInRole(Security.AUTO_ACCOUNT_PASSWORD))
		{
			if (caller.getId() != ae.getId())
			{
				if (ae.getType().equals(AccountType.USER))
				{
					return null;
				}
				else if (ae.getType().equals(AccountType.IGNORED))
				{
					return null;
				}
				else if (ae.getType().equals(AccountType.PRIVILEGED))
				{
					if (callerUe == null)
						throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NoChangePasswordAuthorized"))); //$NON-NLS-1$
					Collection<String> users = handleGetAccountUsers(account, AccountAccessLevelEnum.ACCESS_MANAGER);
					boolean found = false;
					for ( String user: users)
					{
						if (user.equals(callerUe.getCodi()))
						{
							found = true;
							break;
						}
					}
					if (!found)
						return null;
				}
				else if (ae.getType().equals(AccountType.SHARED))
				{
					return null;
				}
			}
		}
		
		if ( ae.getUsers().isEmpty())
			return null;
		else
		{
			UsuariEntity currentUser = ae.getUsers().iterator().next().getUser();
			return getUsuariEntityDao().toUsuari(currentUser);
		}
	}

	@Override
	protected void handleCheckinHPAccount(Account account) throws Exception {
		AccountEntity ae = getAccountEntityDao().load(account.getId());

		if (! ae.getType().equals(AccountType.PRIVILEGED))
		{
			throw new InternalErrorException("Trying to check in a non privileged account");
		}

		
		String principal = Security.getPrincipal().getName();
		InternalPasswordService ips = getInternalPasswordService ();
		String dispatcher = ips.getDefaultDispatcher();
		AccountEntity caller = getAccountEntityDao().findByNameAndDispatcher(principal, dispatcher);
		if (caller == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.AccountNotFound"), principal, dispatcher)); //$NON-NLS-1$
		UsuariEntity callerUe = getUserForAccount(caller);
		if (callerUe == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.UserNotFoundForAccount"), principal, dispatcher)); //$NON-NLS-1$

		if ( ! ae.getUsers().isEmpty())
		{
			UserAccountEntity uae = ae.getUsers().iterator().next();
			UsuariEntity currentUser = uae.getUser();
			if (currentUser.getId().equals (callerUe.getId()))
			{
				getUserAccountEntityDao().remove(uae);
			}
			else
			{
				throw new SecurityException("Trying to checkin a not owned account");
			}
		}
	}

	private boolean isGreaterOrIqualThan (AccountAccessLevelEnum first, AccountAccessLevelEnum second)
	{
		if (first.equals(second))
			return true;
		else if (first == AccountAccessLevelEnum.ACCESS_OWNER)
			return true;
		else if (second == AccountAccessLevelEnum.ACCESS_USER)
			return true;
		else
			return false;
	}
	
	@Override
	protected Collection<Account> handleGetUserGrantedAccounts(Usuari usuari,
			AccountAccessLevelEnum level) throws Exception {
		if (!AutoritzacionsUsuari.hasQueryAccount())
		{
			Usuari caller = AutoritzacionsUsuari.getCurrentUsuari();
			if (caller.getId() != usuari.getId())
				throw new SecurityException(Messages.getString("AccountServiceImpl.PermissionDenied")); //$NON-NLS-1$
		}
		Collection<RolGrant> grants = getAplicacioService().findEffectiveRolGrantByUser(usuari.getId());
		Set<AccountEntity> accounts = new HashSet<AccountEntity>();
		for (RolGrant rg: grants)
		{
			RolEntity r = getRolEntityDao().load(rg.getIdRol());
			for (AccountAccessEntity aae: r.getAccountAccess())
			{
				if (isGreaterOrIqualThan(aae.getLevel(), level))
						accounts.add(aae.getAccount());
			}
		}
		UsuariEntity ue = getUsuariEntityDao().load(usuari.getId());
		addGrantedAccounts (ue.getGrupPrimari(), accounts, level);
		for (UsuariGrupEntity ug: ue.getGrupsSecundaris())
		{
			addGrantedAccounts (ug.getGrup(), accounts, level);
		}
		
		for (AccountAccessEntity aae: ue.getAccountAccess())
		{
			if (isGreaterOrIqualThan(aae.getLevel(), level))
				accounts.add(aae.getAccount());
		}
		
		for (UserAccountEntity uae: ue.getAccounts())
		{
			if (uae.getAccount().getType().equals (AccountType.USER))
				accounts.add(uae.getAccount());
		}
		List<Account> vos = new LinkedList<Account>();
		for ( AccountEntity accEntity: accounts)
		{
			if (!accEntity.isDisabled())
			{
				if (accEntity.getType().equals(AccountType.USER))
					vos.addAll(getUserAccountEntityDao().toUserAccountList(accEntity.getUsers()));
				else
					vos.add(getAccountEntityDao().toAccount(accEntity));
			}
		}
		return vos;
	}
}