package es.caib.seycon.ng.servei;

import bsh.EvalError;
import bsh.Interpreter;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserDomain;
import com.soffid.iam.model.AccountAccessEntity;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AccountEntityDao;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.ServerEntity;
import com.soffid.iam.model.ServerEntityDao;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.SystemEntityDao;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.TaskLogEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserAccountEntityDao;
import com.soffid.iam.model.UserDataEntity;
import com.soffid.iam.model.UserDomainEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.UserTypeEntity;
import com.soffid.iam.model.UserTypeEntityDao;
import com.soffid.iam.reconcile.common.ReconcileAccount;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountCriteria;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.comu.DadaUsuari;
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
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.exception.NotAllowedException;
import es.caib.seycon.ng.exception.UnknownUserException;
import com.soffid.iam.model.Parameter;
import es.caib.seycon.ng.remote.RemoteServiceLocator;
import es.caib.seycon.ng.remote.URLManager;
import es.caib.seycon.ng.servei.account.AccountNameGenerator;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.sync.servei.SecretStoreService;
import es.caib.seycon.ng.sync.servei.SyncStatusService;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.Security;
import java.security.Principal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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

public class AccountServiceImpl extends AccountServiceBase implements ApplicationContextAware
{

	private ApplicationContext applicationContext;

	private UserEntity getUser(String usuari) {
		UserEntity ue = getUserEntityDao().findByUserName(usuari);
		if (ue != null)
		{
			Collection<UserEntity> filtrat = AutoritzacionsUsuari.filtraUsuariEntityCanQuery(Collections.singleton(ue));
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
		UserEntity ue = getUser(usuari.getCodi());
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
		UserEntity ue = getUser(usuari.getCodi());
		SystemEntity de = getSystemEntityDao().load(dispatcher.getId());
		UserAccountEntity uae = generateAccount(name, ue, de);
		return getUserAccountEntityDao().toUserAccount(uae);
	}

	private UserAccountEntity generateAccount(String name, UserEntity ue, SystemEntity de) throws NeedsAccountNameException, EvalError, InternalErrorException, AccountAlreadyExistsException {
		boolean nullName = false;
		if (name == null)
		{
			nullName = true;
			List<AccountEntity> existing = getAccountEntityDao().findByUserAndSystem(ue.getUserName(), de.getName());
			if (! existing.isEmpty())
				throw new NeedsAccountNameException(String.format(Messages.getString("AccountServiceImpl.AlreadyUserAccount"), ue.getUserName(), de.getName()));
			// Search if already has a user name for this user domain
			
			name = gessAccountName(ue.getUserName(), de.getName());
							
			if (name == null)
				throw new NeedsAccountNameException(Messages.getString("AccountServiceImpl.AccountNameRequired")); //$NON-NLS-1$
		}
		AccountEntity acc = getAccountEntityDao().findByNameAndSystem(name, de.getName());
		if (acc != null)
		{
			if (acc.getType().equals (AccountType.IGNORED) && acc.getAcl().isEmpty() &&
				Security.isUserInRole(Security.AUTO_ACCOUNT_UPDATE))
			{
				acc.setType(AccountType.USER);
	    		acc.setDescription(ue.getFullName());
	    		acc.setPasswordPolicy(ue.getUserType());

	    		Dispatcher dispatcher = getDispatcherService().findDispatcherByCodi(de.getName());
	    		acc.setDisabled( ! getDispatcherService().isUserAllowed(dispatcher, ue.getUserName()));
	    		
	    		getAccountEntityDao().update(acc);
			}
			else
				throw new AccountAlreadyExistsException(String.format(Messages.getString("AccountServiceImpl.AccountAlreadyExists"), name + "@" + de.getName()));
		} else {
    		acc = getAccountEntityDao().newAccountEntity();
    		acc.setDescription(ue.getFullName());
    		acc.setSystem(de);
    		acc.setName(name);
    		acc.setType(AccountType.USER);
    		acc.setPasswordPolicy(ue.getUserType());
    		acc.setDisabled(! "S".equals(ue.getActive()));
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
		UserEntity ue = getUser(account.getUser());
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
		AccountEntity acc = getAccountEntityDao().findByNameAndSystem(account.getName(), account.getDispatcher());
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
			acc.setSystem(getSystemEntityDao().findByName(account.getDispatcher()));
			acc.setName(account.getName());
			acc.setType(account.getType());
			UserTypeEntity tu = getUserTypeEntityDao().findByName(account.getPasswordPolicy());
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
					else if (access.getRole() != null)
					{
						for (Iterator<Rol> it = newroles[index].iterator(); !found && it.hasNext();)
						{
							Rol r = it.next();
							if (r.getId().equals (access.getRole().getId()))
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
							access.getGroup(), access.getRole(), access.getUser());
						getAccountAccessEntityDao().remove(access);
					}
				}
			}
		}
		// Add new groups
		for (int index = 0; index < levels.length; index++) {
            for (Grup g : newgrups[index]) {
                GroupEntity ge = getGroupEntityDao().load(g.getId());
                if (ge != null) {
                    AccountAccessEntity access = getAccountAccessEntityDao().newAccountAccessEntity();
                    access.setGroup(ge);
                    access.setAccount(acc);
                    access.setLevel(levels[index]);
                    getAccountAccessEntityDao().create(access);
                    acc.getAcl().add(access);
                    notifyAccountPasswordChange(acc, ge, null, null);
                }
            }
            for (Rol r : newroles[index]) {
                RoleEntity re = getRoleEntityDao().load(r.getId());
                if (re != null) {
                    AccountAccessEntity access = getAccountAccessEntityDao().newAccountAccessEntity();
                    access.setRole(re);
                    access.setAccount(acc);
                    access.setLevel(levels[index]);
                    getAccountAccessEntityDao().create(access);
                    acc.getAcl().add(access);
                    notifyAccountPasswordChange(acc, null, re, null);
                }
            }
            for (Usuari u : newusers[index]) {
                UserEntity ue = getUserEntityDao().load(u.getId());
                if (ue != null) {
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
				for (UserAccountEntity ua : ae.getUsers()) {
                    Usuari u = getUsuariService().getUserInfo(ua.getUser().getUserName());
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
				
				UserEntity ue = getUserEntityDao().load(owner.getId());
				UserAccountEntity uae = getUserAccountEntityDao().newUserAccountEntity();
				uae.setAccount(ae);
				uae.setUser(ue);
				getUserAccountEntityDao().create(uae);
				
				account.setDescription(owner.getFullName());
				
				Dispatcher dispatcher = getDispatcherService().findDispatcherByCodi(account.getDispatcher());
				account.setDisabled( !  getDispatcherService().isUserAllowed(dispatcher, owner.getCodi()) );
				
				createUserTask(ue);

			} else {
				// Remove rules from granted roles
				for (RoleAccountEntity ra : ae.getRoles()) {
                    if (ra.getRule() != null) {
                        ra.setRule(null);
                        getRoleAccountEntityDao().update(ra);
                    }
                }
			}
			ae.setType(account.getType());
		}

		if (! account.getName().equals(ae.getName()))
		{
			if (getAccountEntityDao().findByNameAndSystem(account.getName(), ae.getSystem().getName()) != null)
				throw new AccountAlreadyExistsException(String.format(Messages.getString("AccountServiceImpl.AccountAlreadyExists"), account.getName() + "@" + ae.getSystem().getName()));
		}
		getAccountEntityDao().accountToEntity(account, ae, false);

		if (account.getType().equals(AccountType.USER))
			removeAcl (ae);
		else
			updateAcl(ae, account);
		getAccountEntityDao().update(ae);
		createAccountTask(ae);
	}

	private void createUserTask(UserEntity ue) {
		TaskEntity tasque = getTaskEntityDao().newTaskEntity();
		tasque.setTransaction(TaskHandler.UPDATE_USER);
		tasque.setUser(ue.getUserName());
		getTaskEntityDao().create(tasque);
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
		createAccountTask(ae);
		getAccountEntityDao().remove(ae);
	}

	private void createAccountTask(AccountEntity ae)
	{
		if (! ae.getType().equals(AccountType.IGNORED))
		{
			TaskEntity tasque = getTaskEntityDao().newTaskEntity();
			tasque.setTransaction(TaskHandler.UPDATE_ACCOUNT);
			tasque.setUser(ae.getName());
			tasque.setSystemName(ae.getSystem().getName());
			getTaskEntityDao().create(tasque);
		}
	}
	
	private void notifyAccountPasswordChange(AccountEntity ae, GroupEntity ge, RoleEntity rolEntity, UserEntity usuariEntity) {
		if (! ae.getType().equals(AccountType.IGNORED))
		{
			TaskEntity tasque = getTaskEntityDao().newTaskEntity();
			tasque.setTransaction(TaskHandler.NOTIFY_PASSWORD_CHANGE);
			tasque.setSystemName(ae.getSystem().getName());
			
			// Check notify to group
			if (ge != null)
				tasque.setGroup(ge.getId().toString());
			
			// Check notify to role
			if (rolEntity != null)
				tasque.setRole(rolEntity.getId().toString());
			
			// Check notify to user
			if (usuariEntity != null)
				tasque.setUser(usuariEntity.getUserName());
			
			getTaskEntityDao().create(tasque);
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
		AccountEntity acc = getAccountEntityDao().findByNameAndSystem(accountName, dispatcherName);
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
		UserEntity ue = getUserEntityDao().findByUserName(user);
		if (ue == null)
			return;
		
		SystemEntityDao disDao = getSystemEntityDao();
		for (SystemEntity disEntity : disDao.loadAll()) {
            if (disEntity.getManualAccountCreation() != null && disEntity.getManualAccountCreation().booleanValue()) {
                List<AccountEntity> accs = getAccountEntityDao().findByUserAndSystem(user, disEntity.getName());
                for (AccountEntity acc : accs) {
                    if (acc.isDisabled()) {
                        acc.setDisabled(false);
                        getAccountEntityDao().update(acc);
                    }
                }
            } else if (disEntity.isMainSystem() || disEntity.getUrl() != null) {
                Dispatcher dis = disDao.toDispatcher(disEntity);
                List<AccountEntity> accs = getAccountEntityDao().findByUserAndSystem(user, dis.getCodi());
                String description = ue.getUserName() + " - " + ue.getFullName();
                if (description.length() > 50) description = description.substring(0, 47) + "...";
                if ("S".equals(ue.getActive()) && getDispatcherService().isUserAllowed(dis, user)) {
                    if (accs.isEmpty()) {
                        try {
                            generateAccount(null, ue, disEntity);
                        } catch (Exception e) {
                            LogFactory.getLog(getClass()).warn(String.format(Messages.getString("AccountServiceImpl.ErrorGeneratinAccount"), user, dis.getCodi()), e);
                        }
                    } else {
                        for (AccountEntity acc : accs) {
                            if (acc.isDisabled() || !description.equals(acc.getDescription())) {
                                acc.setDisabled(false);
                                acc.setDescription(description);
                                getAccountEntityDao().update(acc);
                            }
                        }
                    }
                } else if (!accs.isEmpty()) {
                    for (AccountEntity acc : accs) {
                        if (!acc.isDisabled() || !description.equals(acc.getDescription())) {
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
		
		AccountEntity oldAccount = dao.findByNameAndSystem(account.getName(), accountEntity.getSystem().getName());
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

	private void addGroups(HashMap<String, Group> groups, GroupEntity grup) {
		if (!groups.containsKey(grup.getName()))
		{
			Grup grupVO = getGroupEntityDao().toGrup(grup);
			groups.put(grup.getName(), Group.toGroup(grupVO));
			if (grup.getParent() != null)
				addGroups(groups, grup.getParent());	
		}
	}

	@Override
	protected String handleGessAccountName(String userName, String dispatcherName)
			throws Exception
	{
		SystemEntity dispatcher = getSystemEntityDao().findByName(dispatcherName);
		if (dispatcher.getManualAccountCreation() != null && dispatcher.getManualAccountCreation().booleanValue())
			return null;
		
		UserDomainEntity du = getUserDomainEntityDao().findBySytem(dispatcherName);
		// Search if already has a user name for this user domain
		
		UserEntity ue = getUserEntityDao().findByUserName(userName);
		if (ue == null)
			return null;

		if (du.getType().equals(TipusDominiUsuariEnumeration.PRINCIPAL))
			return userName;
		else if (du.getType().equals(TipusDominiUsuariEnumeration.SHELL))
		{
			return evalExpression(du, ue, dispatcherName);
		}
		else if (du.getType().equals(TipusDominiUsuariEnumeration.SPRINGCLASS))
		{
			Object obj = applicationContext.getBean(du.getBeanGenerator());
			if (obj == null)
				throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.UknownBeanForDomain"), du.getBeanGenerator(), du.getName())); //$NON-NLS-1$
			if (! (obj instanceof AccountNameGenerator))
				throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.BeanNotImplementNameGenerator"), du.getBeanGenerator())); //$NON-NLS-1$
			AccountNameGenerator generator = (AccountNameGenerator) obj;
			SystemEntity de = getSystemEntityDao().findByName(dispatcherName);
			return generator.getAccountName(ue, de);
		}
		else
			return null;
	}

	private String evalExpression(UserDomainEntity du, UserEntity ue, String dispatcherName) throws EvalError {
		User userVO = User.toUser(getUserEntityDao().toUsuari(ue));
		Interpreter interpreter = new Interpreter();
		SystemEntity de = getSystemEntityDao().findByName(dispatcherName);
		
		HashMap<String, String> attributes;
		HashMap<String, Group> groups;
			
		attributes = new HashMap<String, String>();
		for (UserDataEntity dada : ue.getUserData()) {
            attributes.put(dada.getDataType().getName(), dada.getValue());
        }
				
		groups = new HashMap<String, Group>();
		addGroups(groups, ue.getPrimaryGroup());
		for (UserGroupEntity grup : ue.getSecondaryGroups()) addGroups(groups, grup.getGroup());
			
		interpreter.set("attributes", attributes); //$NON-NLS-1$
		interpreter.set("groups", groups); //$NON-NLS-1$
		interpreter.set("groupsList", groups.keySet()); //$NON-NLS-1$
		interpreter.set("applicationContext", applicationContext); //$NON-NLS-1$
		interpreter.set("usuariEntity", ue); //$NON-NLS-1$
		interpreter.set("user", userVO); //$NON-NLS-1$
		interpreter.set("dominiEntity", du); //$NON-NLS-1$
		interpreter.set("userDomain", UserDomain.toUserDomain(getUserDomainEntityDao().toDominiUsuari(du))); //$NON-NLS-1$
		interpreter.set("dispatcherEntity", de); //$NON-NLS-1$
		interpreter.set("system", com.soffid.iam.api.System.toSystem(getSystemEntityDao().toDispatcher(de))); //$NON-NLS-1$
		interpreter.set("dao", getAccountEntityDao()); //$NON-NLS-1$
				
		return (String) interpreter.eval(du.getBshExpr());
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
			for (UserAccountEntity uae : acc.getUsers()) {
                users.add(uae.getUser().getUserName());
            }
		} else {
			for (AccountAccessEntity aae : acc.getAcl()) {
                if (aae.getGroup() != null && isGreaterOrIqualThan(aae.getLevel(), level)) {
                    addGroupMembers(aae.getGroup(), users);
                }
                if (aae.getRole() != null && isGreaterOrIqualThan(aae.getLevel(), level)) {
                    addRolMembers(aae.getRole(), users);
                }
                if (aae.getUser() != null && isGreaterOrIqualThan(aae.getLevel(), level)) {
                    users.add(aae.getUser().getUserName());
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
	private void addRolMembers(RoleEntity rol, Set<String> users) throws InternalErrorException {
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
	private void addGroupMembers(GroupEntity group, Set<String> users) {
		for (UserEntity u : group.getPrimaryGroupUsers()) {
            users.add(u.getUserName());
        }
		for (UserGroupEntity ug : group.getSecondaryGroupUsers()) {
            users.add(ug.getUser().getUserName());
        }
		for (GroupEntity child : group.getChildrens()) {
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
	private void addGrantedAccounts(GroupEntity grup, Set<AccountEntity> accounts, AccountAccessLevelEnum level) {
		for (AccountAccessEntity aae: grup.getAccountAccess())
		{
			if (isGreaterOrIqualThan(aae.getLevel(), level))
			{
				accounts.add(aae.getAccount());
			}
		}
		if (grup.getParent() != null)
			addGrantedAccounts(grup.getParent(), accounts, level);
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
		PoliticaContrasenya politica = dominiUsuariService.findPoliticaByTipusAndDominiContrasenyas(ae.getPasswordPolicy().getName(), ae.getSystem().getPasswordDomain().getName());
		if (politica == null)
			throw new BadPasswordException(Messages.getString("AccountServiceImpl.NoPolicyDefined")); //$NON-NLS-1$
		if (!politica.isAllowPasswordQuery())
			throw new BadPasswordException(Messages.getString("AccountServiceImpl.NotAllowedToQueryPassword")); //$NON-NLS-1$
		

		Usuari usuari = AutoritzacionsUsuari.getCurrentUsuari();
		
		
		ServerEntityDao dao = getServerEntityDao();
		Exception lastException = null;
		for (ServerEntity se : dao.loadAll()) {
            if (se.getType().equals(ServerType.MASTERSERVER)) {
                try {
                    RemoteServiceLocator rsl = new RemoteServiceLocator(se.getName());
                    rsl.setAuthToken(se.getAuth());
                    SyncStatusService sss = rsl.getSyncStatusService();
                    Password p = sss.getAccountPassword(usuari.getCodi(), account.getId());
    	            if (p != null)
    	            {
    	        		Auditoria audit = new Auditoria();
    	        		audit.setAccio("S");
    	        		audit.setObjecte("SSO");
    	        		audit.setAutor(Security.getCurrentUser());
    	        		audit.setCalendar(Calendar.getInstance());
    	        		audit.setAccount(account.getName());
    	        		audit.setBbdd(account.getDispatcher());
    	        		audit.setData("-");
    	        		getAuditoriaService().create(audit);
    	            	return p;
					}
                } catch (Exception e) {
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
			AccountEntity caller = getAccountEntityDao().findByNameAndSystem(principal, dispatcher);
			if (caller == null)
				throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.AccountNotFound"), principal, dispatcher)); //$NON-NLS-1$
			if (caller.getId() != ae.getId())
			{
				UserEntity callerUe = getUserForAccount(caller);
				if (ae.getType().equals(AccountType.USER))
				{
					UserEntity ue2 = getUserForAccount(ae);
					if (ue2 != null)
					{
						if (callerUe == null)
							throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NoChangePasswordAuthorized"))); //$NON-NLS-1$
						
						if (!callerUe.getId().equals(ue2.getId()) && !AutoritzacionsUsuari.canUpdateUserPassword(ue2.getPrimaryGroup().getName()))
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
					for (String user : users) {
                        if (user.equals(callerUe.getUserName())) {
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
			PoliticaContrasenya politica = dominiUsuariService.findPoliticaByTipusAndDominiContrasenyas(ae.getPasswordPolicy().getName(), ae.getSystem().getPasswordDomain().getName());
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
		AccountEntity caller = getAccountEntityDao().findByNameAndSystem(principal, dispatcher);
		if (caller == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.AccountNotFound"), principal, dispatcher)); //$NON-NLS-1$
		UserEntity callerUe = getUserForAccount(caller);
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
					for (String user : users) {
                        if (user.equals(callerUe.getUserName())) {
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
			PoliticaContrasenya politica = dominiUsuariService.findPoliticaByTipusAndDominiContrasenyas(ae.getPasswordPolicy().getName(), ae.getSystem().getPasswordDomain().getName());
			if (politica == null)
				throw new BadPasswordException(Messages.getString("AccountServiceImpl.NoPolicyDefined")); //$NON-NLS-1$
			if (!politica.isAllowPasswordChange())
				throw new BadPasswordException(Messages.getString("AccountServiceImpl.NotAllowedToChangePassword")); //$NON-NLS-1$
		}
		
		if (! force )
		{
			if ( ! ae.getUsers().isEmpty())
			{
				UserEntity currentUser = ae.getUsers().iterator().next().getUser();
				if (! currentUser.getId().equals(callerUe.getId()))
					throw new SecurityException(String.format("Cannot change password. The current owner is %s", currentUser.getUserName()));
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
		audit("H", ae); //$NON-NLS-1$
		audit("P", ae); //$NON-NLS-1$
	}
	
	private UserEntity getUserForAccount(AccountEntity acc) {
		UserEntity ue = null;
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
		UserEntity ue = getUserEntityDao().load(usuari.getId());
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

		List<TaskEntity> coll = getTaskEntityDao().findByAccount(accEntity.getName(), accEntity.getSystem().getName());
		for (TaskEntity tasque : coll) {
            if (tasque.getTransaction().equals(TaskHandler.UPDATE_ACCOUNT) || tasque.getTransaction().equals(TaskHandler.UPDATE_ACCOUNT_PASSWORD) || tasque.getTransaction().equals(TaskHandler.PROPAGATE_ACCOUNT_PASSWORD)) return true;
        }

		if (accEntity.getType().equals(AccountType.USER))
		{
			for (UserAccountEntity ua : accEntity.getUsers()) {
                coll = getTaskEntityDao().findByUser(ua.getUser().getUserName());
                for (TaskEntity tasque : coll) {
                    if (tasque.getTransaction().equals(TaskHandler.UPDATE_USER) || accEntity.getSystem().getPasswordDomain().getName().equals(tasque.getPasswordsDomain()) && (tasque.getTransaction().equals(TaskHandler.UPDATE_USER_PASSWORD) || tasque.getTransaction().equals(TaskHandler.PROPAGATE_PASSWORD))) {
                        boolean found = false;
                        for (TaskLogEntity tl : tasque.getLogs()) {
                            if (tl.getSystem().getId().equals(accEntity.getSystem().getId())) {
                                found = true;
                                if (!"S".equals(tl.getCompleted())) return true;
                            }
                        }
                        if (!found) return true;
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
        auditoria.setBbdd(account.getSystem().getName());
        auditoria.setAutor(codiUsuariCanvi);
        auditoria.setCalendar(Calendar.getInstance());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(Calendar.getInstance().getTime()));
        auditoria.setObjecte("SC_ACCOUN"); //$NON-NLS-1$
        if (account.getType().equals (AccountType.USER))
        {
        	for (UserAccountEntity ua : account.getUsers()) auditoria.setUsuari(ua.getUser().getUserName());
        }

        getAuditoriaService().create (auditoria);
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
			"from com.soffid.iam.model.AccountEntity as account " + //$NON-NLS-1$
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
		UserTypeEntityDao userTypeDAO = getUserTypeEntityDao();
		List<Account> accounts = new LinkedList<Account>();
		
		for (Account account : accountList) {
            account.setPasswordPolicy(userTypeDAO.findByName(account.getPasswordPolicy()).getDescription());
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
		AccountEntity caller = getAccountEntityDao().findByNameAndSystem(principal, dispatcher);
		if (caller == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.AccountNotFound"), principal, dispatcher)); //$NON-NLS-1$
		UserEntity callerUe = getUserForAccount(caller);
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
					for (String user : users) {
                        if (user.equals(callerUe.getUserName())) {
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
			UserEntity currentUser = ae.getUsers().iterator().next().getUser();
			return getUserEntityDao().toUsuari(currentUser);
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
		AccountEntity caller = getAccountEntityDao().findByNameAndSystem(principal, dispatcher);
		if (caller == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.AccountNotFound"), principal, dispatcher)); //$NON-NLS-1$
		UserEntity callerUe = getUserForAccount(caller);
		if (callerUe == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.UserNotFoundForAccount"), principal, dispatcher)); //$NON-NLS-1$

		if ( ! ae.getUsers().isEmpty())
		{
			UserAccountEntity uae = ae.getUsers().iterator().next();
			UserEntity currentUser = uae.getUser();
			if (currentUser.getId().equals (callerUe.getId()))
			{
				getUserAccountEntityDao().remove(uae);
				audit("R", ae); //$NON-NLS-1$
				Password p = ips.generateFakeAccountPassword(ae);
				ips.storeAndForwardAccountPassword(ae, p, false, null);
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
		for (RolGrant rg : grants) {
            RoleEntity r = getRoleEntityDao().load(rg.getIdRol());
            for (AccountAccessEntity aae : r.getAccountAccess()) {
                if (isGreaterOrIqualThan(aae.getLevel(), level)) accounts.add(aae.getAccount());
            }
        }
		UserEntity ue = getUserEntityDao().load(usuari.getId());
		addGrantedAccounts(ue.getPrimaryGroup(), accounts, level);
		for (UserGroupEntity ug : ue.getSecondaryGroups()) {
            addGrantedAccounts(ug.getGroup(), accounts, level);
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
