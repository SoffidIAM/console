package com.soffid.iam.service;

import java.io.EOFException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.hibernate.Hibernate;
import org.jbpm.JbpmContext;
import org.jbpm.jpdl.el.ELException;
import org.jbpm.jpdl.el.FunctionMapper;
import org.jbpm.jpdl.el.VariableResolver;
import org.jbpm.jpdl.el.impl.ExpressionEvaluatorImpl;
import org.jbpm.logging.exe.LoggingInstance;
import org.json.JSONException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.AccountHistory;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.AsyncProcessTracker;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.CredentialTypeEnum;
import com.soffid.iam.api.DisableObjectRule;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.HostService;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordPolicy;
import com.soffid.iam.api.PasswordValidation;
import com.soffid.iam.api.PolicyCheckResult;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.SyncAgentTaskLog;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.api.UserData;
import com.soffid.iam.api.UserType;
import com.soffid.iam.bpm.model.AuthenticationLog;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.interp.Evaluator;
import com.soffid.iam.model.AccountAccessEntity;
import com.soffid.iam.model.AccountAttributeEntity;
import com.soffid.iam.model.AccountAttributeEntityDaoImpl;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AccountEntityDao;
import com.soffid.iam.model.AccountEntityDaoImpl;
import com.soffid.iam.model.AccountMetadataEntity;
import com.soffid.iam.model.AccountSnapshotEntity;
import com.soffid.iam.model.CustomDialect;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.QueryBuilder;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.ServerEntity;
import com.soffid.iam.model.ServerEntityDao;
import com.soffid.iam.model.ServerInstanceEntity;
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
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.remote.RemoteServiceLocator;
import com.soffid.iam.security.SoffidPrincipalImpl;
import com.soffid.iam.service.account.AccountNameGenerator;
import com.soffid.iam.service.impl.ObjectVariableResolver;
import com.soffid.iam.service.impl.SshKeyGenerator;
import com.soffid.iam.service.impl.bshjail.SecureInterpreter;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.sync.service.SyncStatusService;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import bsh.EvalError;
import es.caib.bpm.vo.PredefinedProcessType;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountCriteria;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.exception.NotAllowedException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.SoffidStackTrace;

public class AccountServiceImpl extends com.soffid.iam.service.AccountServiceBase implements ApplicationContextAware
{
	org.apache.commons.logging.Log log = LogFactory.getLog(getClass());
			
	private ApplicationContext applicationContext;

	private UserEntity getUser(String usuari) throws InternalErrorException {
		UserEntity ue = getUserEntityDao().findByUserName(usuari);
		if (ue != null)
		{
			if (!getAuthorizationService().hasPermission(Security.AUTO_USER_QUERY, ue))
				ue = null;
		}
		return ue;
	}
	
	@Override
    protected List<UserAccount> handleListUserAccounts(User usuari) throws Exception {
		LinkedList<UserAccount> result = new LinkedList<UserAccount> ();
		UserEntity ue = getUser(usuari.getUserName());
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
    protected UserAccount handleCreateAccount(User usuari, com.soffid.iam.api.System dispatcher, String name) throws Exception {
		UserEntity ue = getUser(usuari.getUserName());
		SystemEntity de = getSystemEntityDao().load(dispatcher.getId());
		if (de.getUsage() != null && ! "IAM".equals(de.getUsage())) //$NON-NLS-1$
			throw new InternalErrorException(
					String.format(Messages.getString("AccountServiceImpl.1"), //$NON-NLS-1$
							de.getName()));

		UserAccountEntity uae = generateAccount(name, ue, de, true);
		createAccountTask(uae.getAccount());
		return getUserAccountEntityDao().toUserAccount(uae);
	}

	private UserAccountEntity generateAccount(String name, UserEntity ue, SystemEntity de, boolean force) throws Exception {
		UserAccountEntity uae ;
		if (name == null)
		{
			List<AccountEntity> existing = getAccountEntityDao().findByUserAndSystem(ue.getUserName(), de.getName());
			for (AccountEntity accountEntity: existing) {
				if ( ! accountEntity.isDisabled())
					throw new NeedsAccountNameException(String.format(Messages.getString("AccountServiceImpl.AlreadyUserAccount"), ue.getUserName(), de.getName())); //$NON-NLS-1$
			}
			// Search if already has a user name for this user domain
			
			if (!force && ! needsAccount(ue.getUserName(), de.getName()))
				return null;
			
			name = guessAccountName(ue.getUserName(), de.getName());
							
			if (name == null)
				throw new NeedsAccountNameException(Messages.getString("AccountServiceImpl.AccountNameRequired")+" ("+ue.getUserName()+" / "+de.getName()+") "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		AccountEntity acc = getAccountEntityDao().findByNameAndSystem(name, de.getName());
		if (acc != null)
		{
			if (acc.getType().equals (AccountType.IGNORED) && /* Unmanaged account with no owner */ 
				(acc.getAcl() == null || acc.getAcl().isEmpty()) &&
				Security.isUserInRole(Security.AUTO_ACCOUNT_UPDATE) ||
				
				(acc.getType().equals (AccountType.IGNORED) || 
						
				acc.getType().equals (AccountType.SHARED) ) &&
				acc.getAcl().size() == 1  &&  /* Account already belongs to the user and only to the user*/
				acc.getAcl().iterator().next().getUser() == ue &&
				acc.getAcl().iterator().next().getLevel()  == AccountAccessLevelEnum.ACCESS_OWNER  ||
				
				AccountStatus.REMOVED.equals ( acc.getStatus()) )

			{
				if (AccountStatus.REMOVED.equals(acc.getStatus())) {
					getSignalService().signalAccount("https://schemas.openid.net/secevent/risc/event-type/account-purged", 
							acc.getName(), acc.getSystem().getName(), new String[] {});
				}
				acc.setType(AccountType.USER);
	    		acc.setDescription(ue.getFullName());
	    		acc.setPasswordPolicy(ue.getUserType());

	    		com.soffid.iam.api.System dispatcher = getDispatcherService().findDispatcherByName(de.getName());
	    		if (getDispatcherService().isUserAllowed(dispatcher, ue.getUserName()))
	    		{
	    			if (acc.isDisabled())
	    				audit("E", acc); //$NON-NLS-1$
	    			acc.setDisabled( false );
	    			acc.setStatus(AccountStatus.ACTIVE);
	    		} else {
	    			if (! acc.isDisabled())
	    				audit("e", acc); //$NON-NLS-1$
	    			acc.setDisabled( true );
	    			if (acc.getStatus() == AccountStatus.ACTIVE)
	    				acc.setStatus(AccountStatus.DISABLED);
	    		}
	    		
	    		getAccountEntityDao().update(acc);
	    		uae = bindAccountToUser(ue, acc);
			}
			else
				throw new AccountAlreadyExistsException(String.format(Messages.getString("AccountServiceImpl.AccountAlreadyExists"), name + "@" + de.getName())); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
    		acc = getAccountEntityDao().newAccountEntity();
    		acc.setDescription(ue.getFullName());
    		acc.setSystem(de);
    		acc.setName(name);
    		acc.setType(AccountType.USER);
    		acc.setPasswordPolicy(ue.getUserType());
    		acc.setPasswordPolicy( ue.getUserType() );
    		if ( "S".equals(ue.getActive()) ) //$NON-NLS-1$
    		{
	    		acc.setDisabled(false);
	    		acc.setStatus(AccountStatus.ACTIVE);
    		} else {
	    		acc.setDisabled(true);
	    		acc.setStatus(AccountStatus.REMOVED);
    		}
    		getAccountEntityDao().create(acc);
    		uae = bindAccountToUser(ue, acc);
    		if (! acc.isDisabled())
    			audit("C", acc); //$NON-NLS-1$
		}


		return uae;
	}

	private UserAccountEntity bindAccountToUser(UserEntity ue, AccountEntity acc) {
		UserAccountEntity uae = null;
		if (acc.getUsers() != null && ! acc.getUsers().isEmpty())
		{
			for (UserAccountEntity uae2: new LinkedList<UserAccountEntity> ( acc.getUsers()))
			{
				if (uae2.getAccount() == acc)
				{
					uae = uae2;
				}
				else
				{
					getUserAccountEntityDao().remove(uae2);
					acc.getUsers().remove(uae2);
				}
			}
		}
		if (uae == null)
		{
			uae = getUserAccountEntityDao().newUserAccountEntity();
			uae.setAccount(acc);
			uae.setUser(ue);
			getUserAccountEntityDao().create(uae);
			acc.getUsers().add(uae);
			ue.getAccounts().add(uae);
		}
		return uae;
	}

	@Override
	protected void handleRemoveAccount(UserAccount account) throws Exception
	{
		AccountEntity acc = getAccountEntityDao().load(account.getId());
		if (acc == null) 
			return;
		if (acc == null)
			throw new InternalErrorException (String.format(Messages.getString("AccountServiceImpl.UnknownAccount"), account.getId())); //$NON-NLS-1$
	
		if (!acc.getType().equals(AccountType.USER))
			throw new InternalErrorException (String.format(Messages.getString("AccountServiceImpl.NotPersonalAccount"), account.getId())); //$NON-NLS-1$
			
		Collection<UserAccountEntity> list = acc.getUsers();
		if (list.size() != 1)
			throw new InternalErrorException (String.format(Messages.getString("AccountServiceImpl.MoreThanOneUserAccount"), account.getId())); //$NON-NLS-1$
		
//			if (!acc.getRoles().isEmpty())
//				throw new NotAllowedException(String.format(Messages.getString("AccountServiceImpl.CannotDeleteAccount"), account.getName(), account.getSystem())); //$NON-NLS-1$
		
		UserAccountEntity ua = list.iterator().next();
		
		createAccountTask(acc);
		for (RoleAccountEntity ra: new LinkedList<RoleAccountEntity> (acc.getRoles()))
		{
			getRoleAccountEntityDao().remove(ra);
		}
		acc.getRoles().clear();
		getAccountEntityDao().remove(acc);
	}


	@Override
    protected List<com.soffid.iam.api.Account> handleListNonUserAccounts(com.soffid.iam.api.System dispatcher, String nom) throws Exception {
		List<AccountEntity> accounts = getAccountEntityDao().findSharedAccounts(dispatcher.getName(), nom);
		return getAccountEntityDao().toAccountList(accounts);
	}

	@Override
    protected Account handleCreateAccount(com.soffid.iam.api.Account account) throws Exception {
		String ssoSystem = ConfigurationCache.getProperty("AutoSSOSystem"); //$NON-NLS-1$
		if (account.getName().equals("?") && account.getSystem().equals(ssoSystem)) { //$NON-NLS-1$
			account.setName( Long.toString( findLastAccount(ssoSystem) ));
		}
		AccountEntity acc = getAccountEntityDao().findByNameAndSystem(account.getName(), account.getSystem());
		if (acc != null)
		{
			throw new AccountAlreadyExistsException(String.format(Messages.getString("AccountServiceImpl.AccountAlreadyExists"), account.getName() + "@" + account.getSystem())); //$NON-NLS-1$ //$NON-NLS-2$
		}
		acc = getAccountEntityDao().newAccountEntity();
		acc.setAcl(new HashSet<AccountAccessEntity>());
		acc.setDescription(account.getDescription());
		acc.setSystem(getSystemEntityDao().findByName(account.getSystem()));
		acc.setName(account.getName());
		acc.setType(account.getType());
		acc.setInheritNewPermissions(account.isInheritNewPermissions());
		acc.setLoginName(account.getLoginName());
		acc.setLoginUrl(account.getLoginUrl());
		acc.setServerName(account.getServerName());
		acc.setServerType(account.getServerType());
		acc.setLaunchType(account.getLaunchType());
		acc.setCredentialType(account.getCredentialType());
		if (account.getJumpServerGroup() != null && !account.getJumpServerGroup().trim().isEmpty())
			acc.setJumpServerGroup( getJumpServerGroupEntityDao().findByName(account.getJumpServerGroup()) );
		acc.setCreated(new Date());
		acc.setDisabled(account.isDisabled());
		acc.setStatus(account.getStatus() == null? (account.isDisabled() ? AccountStatus.DISABLED : AccountStatus.ACTIVE): account.getStatus());
		UserTypeEntity tu = getUserTypeEntityDao().findByName(account.getPasswordPolicy());
		if (tu == null)
			throw new InternalErrorException (String.format(Messages.getString("AccountServiceImpl.InvalidPolicy"), account.getPasswordPolicy())); //$NON-NLS-1$
		acc.setPasswordPolicy( tu );
		getAccountEntityDao().create(acc);
		if (acc.getType() == AccountType.USER)
		{
			if (account.getOwnerUsers() == null || account.getOwnerUsers().size() != 1 ||
				(account.getOwnerRoles() != null && !account.getOwnerRoles().isEmpty()) ||
				(account.getOwnerGroups() != null && !account.getOwnerGroups().isEmpty()) ||
				(account.getManagerUsers() != null && !account.getManagerUsers().isEmpty()) ||
				(account.getManagerRoles() != null && !account.getManagerRoles().isEmpty()) ||
				(account.getManagerGroups() != null && !account.getManagerGroups().isEmpty()) ||
				(account.getGrantedUsers() != null && !account.getGrantedUsers().isEmpty()) ||
				(account.getGrantedRoles() != null && !account.getGrantedRoles().isEmpty()) ||
				(account.getGrantedGroups() != null && !account.getGrantedGroups().isEmpty()))
				throw new InternalErrorException(Messages.getString("AccountServiceImpl.CannotChangeSharedAccount")); //$NON-NLS-1$
					
			if (acc.getSystem().getUsage() != null && ! "IAM".equals(acc.getSystem().getUsage())) //$NON-NLS-1$
				throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.1"),  acc.getSystem().getName()) ); //$NON-NLS-1$

			String owner = account.getOwnerUsers().iterator().next();
			
			UserEntity ue = getUserEntityDao().findByUserName(owner);
			UserAccountEntity uae = getUserAccountEntityDao().newUserAccountEntity();
			uae.setAccount(acc);
			uae.setUser(ue);
			getUserAccountEntityDao().create(uae);
			
			account.setDescription(ue.getFullName());
			
			com.soffid.iam.api.System dispatcher = getDispatcherService().findDispatcherByName(account.getSystem());
			account.setDisabled(!getDispatcherService().isUserAllowed(dispatcher, owner));
			
			createUserTask(ue);
				
			account = getAccountEntityDao().toAccount(acc);
		}
		else
		{
			updateAcl (acc, account);
		
			account.setId(acc.getId());
			account = getVaultService().addToFolder(account);
		}
		
		account.setId(acc.getId());

		if (! acc.isDisabled())
			audit("E", acc); //$NON-NLS-1$

		createAccountTask(acc);
		
		SoffidPrincipalImpl.clearCache();
		return account;
	}

	private long findLastAccount (String system) throws InternalErrorException
	{
		long bits = 0;
		long top = 0;
		long attempt = 1;
		/**
		 * Find radix the first account with number = 2 ^ radix
		 */
		do
		{
			AccountEntity acc = getAccountEntityDao().findByNameAndSystem( Long.toString(attempt), system);
			if (acc == null) break;
			top = attempt;
			attempt = attempt + attempt;
			bits ++ ;
		} while (true);
		/**
		 * Now look for the other bits
		 * top exists
		 * attempt does not exist
		 */
		long step = top;
		while (bits > 1)
		{
			step = step / 2;
			attempt = top + step;
			AccountEntity acc = getAccountEntityDao().findByNameAndSystem(Long.toString(attempt), system);
			if (acc != null) top = attempt;
			bits --;
		}
		return top+1;
	}

	@Override
    protected Account handleCreateAccount2(com.soffid.iam.api.Account account) throws Exception {
		Map<String, Object> attributes ;
		if (account.getAttributes () == null)
		    attributes = new HashMap<String, Object>(  );
		else
		    attributes = new HashMap<String, Object>( account.getAttributes() );
		Account acc = handleCreateAccount(account);
		if (attributes != null && ! attributes.isEmpty())
		{
			acc.setAttributes(attributes);
			AccountEntity entity = getAccountEntityDao().load(acc.getId());
			updateAccountAttributes( account, entity );
			getAccountEntityDao().removeFromCache(entity);
		}
		return acc;
	}
	
	@Override
    protected Account handleUpdateAccount2(com.soffid.iam.api.Account account) throws Exception {
		Map<String, Object> attributes = account.getAttributes() == null ?
				null :
				new HashMap<String, Object>( account.getAttributes() );
		Account acc = handleUpdateAccount(account);
		if (attributes != null && ! attributes.isEmpty())
		{
			acc.setAttributes(attributes);
			AccountEntity entity = getAccountEntityDao().load(acc.getId());
			updateAccountAttributes( account, entity );
			getAccountEntityDao().removeFromCache(entity);
		}
		return acc;
	}

	private void updateAccountAttributes (Account app, AccountEntity entity) throws InternalErrorException
	{
		if (app.getAttributes() == null)
			app.setAttributes(new HashMap<String, Object>());
		
//		if ("com.soffid.iam.sync.sso.agent.SSOAgent".equals(entity.getSystem().getClassName())) {
//			app.getAttributes().put("SSO:Server", app.getServerName());
//			app.getAttributes().put("SSO:URL", app.getLoginUrl());
//			app.getAttributes().put("type", app.getServerType());
//		}
		
		LinkedList<AccountAttributeEntity> entities = new LinkedList<AccountAttributeEntity> (entity.getAttributes());
		HashSet<String> keys = new HashSet<String>();
		for (String key: app.getAttributes().keySet() )
		{
			if (!key.equals(AccountEntityDaoImpl.PREVIOUS_STATUS_ATTRIBUTE)) {
				Object v = app.getAttributes().get(key);
				if (v == null)
				{
					// Do nothing
				}
				else
				{
					AccountMetadataEntity metadata = findMetadata (entity, key);
					if (metadata != null) {
						if (v instanceof Collection)
						{
							Collection l = (Collection) v;
							for (Object o: (Collection) v)
							{
								if (o != null)
								{
									updateAccountAttribute(entity, entities, key, metadata, o);
								}
							}
						}
						else
						{
							updateAccountAttribute(entity, entities, key, metadata, v);
						}						
					} else {
						MetaDataEntity commonMetadata = findCommonMetadata(entity, key);
						if (commonMetadata != null) {
							if (v instanceof Collection)
							{
								Collection l = (Collection) v;
								for (Object o: (Collection) v)
								{
									if (o != null)
									{
										updateAccountAttribute(entity, entities, key, commonMetadata, o);
									}
								}
							}
							else
							{
								updateAccountAttribute(entity, entities, key, commonMetadata, v);
							}
						} else {
							throw new InternalErrorException(String.format("Unable to find metadada for attribute %s", key)); //$NON-NLS-1$
						}
					}
				}
			}
		}
		entity.getAttributes().removeAll(entities);
		getAccountEntityDao().update(entity);

		Collection<AccountMetadataEntity> md = entity.getSystem().getMetaData();
		
		for ( AccountMetadataEntity m: md)
		{
			Object o = app.getAttributes().get(m.getName());
			if ( o == null || "".equals(o)) //$NON-NLS-1$
			{
				if (m.getRequired() != null && m.getRequired().booleanValue())
					throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.18"), m.getLabel())); //$NON-NLS-1$
			} else {
				if (m.getUnique() != null && m.getUnique().booleanValue())
				{
					Collection<String> l = (Collection<String>) ( o instanceof Collection? (Collection) o: Collections.singletonList(o) );
					for (String v: l)
					{
						List<AccountAttributeEntity> p = getAccountAttributeEntityDao().findByNameAndValue(app.getSystem(), m.getName(), v);
						if (p.size() > 1)
							throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.19"), //$NON-NLS-1$
									m.getLabel(), v));
					}
				}
			}
		}
		
		for ( MetaDataEntity m: getMetaDataEntityDao().findByObjectTypeAndName(Account.class.getName(), null))
		{
			if (Boolean.FALSE.equals(m.getBuiltin())) {
				Object o = app.getAttributes().get(m.getName());
				if ( o == null || "".equals(o)) //$NON-NLS-1$
				{
					if (m.getRequired() != null && m.getRequired().booleanValue())
						throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.18"), m.getLabel())); //$NON-NLS-1$
				} else {
					if (m.getUnique() != null && m.getUnique().booleanValue())
					{
						Collection<String> l = (Collection<String>) ( o instanceof Collection? (Collection) o: Collections.singletonList(o) );
						for (String v: l)
						{
							List<AccountAttributeEntity> p = getAccountAttributeEntityDao().findByNameAndValue(app.getSystem(), m.getName(), v);
							if (p.size() > 1)
								throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.19"), //$NON-NLS-1$
										m.getLabel(), v));
						}
					}
				}
			}
		}

		byte[] previousStatus = (byte[]) app.getAttributes().get(AccountEntityDaoImpl.PREVIOUS_STATUS_ATTRIBUTE);
		if (previousStatus != null) {
			if (entity.getSnapshot() == null) {
				AccountSnapshotEntity s = getAccountSnapshotEntityDao().newAccountSnapshotEntity();
				s.setData(previousStatus);
				getAccountSnapshotEntityDao().create(s);
				entity.setSnapshot(s);
				getAccountEntityDao().update(entity);
			} else {
				entity.getSnapshot().setData(previousStatus);
				getAccountSnapshotEntityDao().update(entity.getSnapshot());
			}
			app.setHasSnapshot(true);
		}
		else if (entity.getSnapshot() != null)
		{
			AccountSnapshotEntity s = entity.getSnapshot();
			entity.setSnapshot(null);
			getAccountEntityDao().update(entity);
			getAccountSnapshotEntityDao().remove(s);
			app.setHasSnapshot(false);
		}
	}

	private void updateAccountAttribute(AccountEntity entity, LinkedList<AccountAttributeEntity> attributes, String key,
			AccountMetadataEntity metadata, Object value) throws InternalErrorException {
		AccountAttributeEntity aae = findAccountAttributeEntity ( attributes, key, value);
		if (aae == null)
		{
			getAttributeValidationService().validate(metadata, value);
			aae = getAccountAttributeEntityDao().newAccountAttributeEntity();
			aae.setAccount(entity);
			aae.setSystemMetadata(metadata);
			aae.setObjectValue(value);
			getAccountAttributeEntityDao().create(aae);
			entity.getAttributes().add(aae);
			entity.setLastChange(new Date());
		}
		else
			attributes.remove(aae);
	}

	private void updateAccountAttribute(AccountEntity entity, 
			LinkedList<AccountAttributeEntity> attributes, String key,
			MetaDataEntity metadata, Object value) throws InternalErrorException {
		AccountAttributeEntity aae = findAccountAttributeEntity ( attributes, key, value);
		if (aae == null)
		{
			getAttributeValidationService().validate(metadata, value);
			aae = getAccountAttributeEntityDao().newAccountAttributeEntity();
			aae.setAccount(entity);
			aae.setMetadata(metadata);
			aae.setObjectValue(value);
			getAccountAttributeEntityDao().create(aae);
			entity.getAttributes().add(aae);
			entity.setLastChange(new Date());
		}
		else
			attributes.remove(aae);
	}


	private AccountAttributeEntity findAccountAttributeEntity(LinkedList<AccountAttributeEntity> entities, String key,
			Object o) {
		for (AccountAttributeEntity aae: entities)
		{
			if (aae.getMetadata() != null && aae.getMetadata().getName().equals(key) ||
				aae.getSystemMetadata() != null && aae.getSystemMetadata().getName().equals(key))
			{
				if (aae.getObjectValue() != null && aae.getObjectValue().equals(o))
					return aae;
			}
		}
		return null;
	}

	private MetaDataEntity findCommonMetadata(AccountEntity entity, String key) throws InternalErrorException {
		for ( MetaDataEntity m: getMetaDataEntityDao().findByObjectTypeAndName(Account.class.getName(), key)) {
			if (Boolean.FALSE.equals(m.getBuiltin()))
				return m;
		}
		return null;
	}
	
	private AccountMetadataEntity findMetadata(AccountEntity entity, String key) throws InternalErrorException {
		for (AccountMetadataEntity m: entity.getSystem().getMetaData())
			if (m.getName().equals(key))
				return m;
		return null;
	}

	private Collection<String> getAclGrupCollectionForLevel(Account account, AccountAccessLevelEnum level) {
		if (level == AccountAccessLevelEnum.ACCESS_MANAGER)
			return account.getManagerGroups();
		else if (level == AccountAccessLevelEnum.ACCESS_USER)
			return account.getGrantedGroups();
		else
			return account.getOwnerGroups();
	}

	private Collection<String> getAclUsuariCollectionForLevel(Account account, AccountAccessLevelEnum level) {
		if (level == AccountAccessLevelEnum.ACCESS_MANAGER)
			return account.getManagerUsers();
		else if (level == AccountAccessLevelEnum.ACCESS_USER)
			return account.getGrantedUsers();
		else
			return account.getOwnerUsers();
	}

	private Collection<String> getAclRolCollectionForLevel(Account account, AccountAccessLevelEnum level) {
		if (level == AccountAccessLevelEnum.ACCESS_MANAGER)
			return account.getManagerRoles();
		else if (level == AccountAccessLevelEnum.ACCESS_USER)
			return account.getGrantedRoles();
		else
			return account.getOwnerRoles();
	}

	private boolean updateAcl(AccountEntity acc, com.soffid.iam.api.Account account) 
	{
		boolean anyChange = false;
		
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
		@SuppressWarnings(value = "unchecked")
        List<String>[] newgrups = new List[]{new LinkedList<String>(account.getGrantedGroups()), 
        		new LinkedList<String>(account.getManagerGroups()), 
        		new LinkedList<String>(account.getOwnerGroups())};
		@SuppressWarnings(value = "unchecked")
        List<String>[] newroles = new List[]{new LinkedList<String>(account.getGrantedRoles()), 
        		new LinkedList<String>(account.getManagerRoles()), 
        		new LinkedList<String>(account.getOwnerRoles())};
		@SuppressWarnings(value = "unchecked")
        List<String>[] newusers = new List[]{new LinkedList<String>(account.getGrantedUsers()), 
        		new LinkedList<String>(account.getManagerUsers()), 
        		new LinkedList<String>(account.getOwnerUsers())};
		// Remove grants
		for (Iterator<AccountAccessEntity> aclIterator = acc.getAcl().iterator(); aclIterator.hasNext(); ) {
            AccountAccessEntity access = aclIterator.next();
            if ( ! Boolean.TRUE.equals(access.getDisabled()))
            {
	            for (int index = 0; index < levels.length; index++) {
	                if (levels[index] == access.getLevel()) {
	                    boolean found = false;
	                    if (access.getGroup() != null) {
	                        for (Iterator<String> it = newgrups[index].iterator(); !found && it.hasNext(); ) {
	                            String g = it.next();
	                            if (g.equals(access.getGroup().getName())) {
	                                it.remove();
	                                found = true;
	                            }
	                        }
	                    } else if (access.getRole() != null) {
	                        for (Iterator<String> it = newroles[index].iterator(); !found && it.hasNext(); ) {
	                            String r = it.next();
	                            if (r.equals(access.getRole().getName()+"@"+access.getRole().getSystem().getName())) { //$NON-NLS-1$
	                                it.remove();
	                                found = true;
	                            }
	                        }
	                    } else if (access.getUser() != null) {
	                        for (Iterator<String> it = newusers[index].iterator(); !found && it.hasNext(); ) {
	                            String u = it.next();
	                            if (u.equals(access.getUser().getUserName())) {
	                                it.remove();
	                                found = true;
	                            }
	                        }
	                    }
	                    if (!found) {
	                    	anyChange = true;
	
	                        notifyAccountPasswordChange(access.getAccount(), access.getGroup(), access.getRole(), access.getUser());
	                        if ( ConfigurationCache.isHistoryEnabled())
	                        {
	                        	if ( ! Boolean.TRUE.equals(access.getDisabled()))
	                        	{
	                        		access.setDisabled(true);
	                        		access.setEnd(new Date());
	                        		getAccountAccessEntityDao().update(access);
	                        	}
	                        }
	                        else
	                        {                        	
	                        	aclIterator.remove();
	                        	getAccountAccessEntityDao().remove(access);
	                        }
	                    }
	                }
	            }
            }
        }
		// Add new groups
		for (int index = 0; index < levels.length; index++) {
            for (String g : newgrups[index]) {
                GroupEntity ge = getGroupEntityDao().findByName(g);
                if (ge != null) {
					anyChange = true;

                    AccountAccessEntity access = getAccountAccessEntityDao().newAccountAccessEntity();
                    access.setGroup(ge);
                    access.setAccount(acc);
                    access.setLevel(levels[index]);
                    access.setDisabled(false);
                    access.setStart(new Date());
                    getAccountAccessEntityDao().create(access);
                    acc.getAcl().add(access);
                    notifyAccountPasswordChange(acc, ge, null, null);
                }
            }
            for (String r : newroles[index]) {
                RoleEntity re = getRoleEntityDao().findByShortName(r);
                if (re != null) {
					anyChange = true;

                    AccountAccessEntity access = getAccountAccessEntityDao().newAccountAccessEntity();
                    access.setRole(re);
                    access.setAccount(acc);
                    access.setLevel(levels[index]);
                    access.setDisabled(false);
                    access.setStart(new Date());
                    getAccountAccessEntityDao().create(access);
                    acc.getAcl().add(access);
                    notifyAccountPasswordChange(acc, null, re, null);
                }
            }
            for (String u : newusers[index]) {
                UserEntity ue = getUserEntityDao().findByUserName(u);
                if (ue != null) {
					anyChange = true;

                    AccountAccessEntity access = getAccountAccessEntityDao().newAccountAccessEntity();
                    access.setUser(ue);
                    access.setAccount(acc);
                    access.setLevel(levels[index]);
                    access.setDisabled(false);
                    access.setStart(new Date());
                    getAccountAccessEntityDao().create(access);
                    acc.getAcl().add(access);
                    notifyAccountPasswordChange(acc, null, null, ue);
                }
            }
        }
		return anyChange;
    }

    @Override
    protected Account handleUpdateAccount(com.soffid.iam.api.Account account) throws Exception {
		boolean anyChange = false;
		
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		
		if (account.getStatus() == null)
			account.setStatus( account.isDisabled() ? AccountStatus.DISABLED: AccountStatus.ACTIVE);
		else
			account.setDisabled( account.getStatus() != AccountStatus.ACTIVE && account.getStatus() != AccountStatus.FORCED_ACTIVE);

		// Check if resend domain password when user account is enabled
		if (ae.getType()==AccountType.USER) {
			if (!account.isDisabled() && ae.isDisabled()) {
				if (ae.getSecrets()!=null && !ae.getSecrets().trim().isEmpty()) {
					if (ae.getSystem().getUrl()!=null) {
						for (ServerEntity se : getServerEntityDao().loadAll()) {
							if (se.getType().equals(ServerType.MASTERSERVER)) {
								String user = ae.getUsers().iterator().next().getUser().getUserName();
								String passwordDomain = ae.getSystem().getPasswordDomain().getName();
								if (se.getInstances().isEmpty()) {
									resendUserPasswordNow(user, passwordDomain, se.getUrl(), se.getAuth());
								} else {
									for (ServerInstanceEntity si: se.getInstances()) {
										resendUserPasswordNow(user, passwordDomain, si.getUrl(), si.getAuth());
										break;
									}
								}
							}
						}
					}
				}
			}
		}

		if (AccountStatus.REMOVED.equals(ae.getStatus()) && !account.getStatus().equals(AccountStatus.REMOVED))
		{
			audit("C", ae); //$NON-NLS-1$
		}
		else if ( ! AccountStatus.REMOVED.equals(ae.getStatus()) && account.getStatus().equals(AccountStatus.REMOVED))
		{
			audit("D", ae); //$NON-NLS-1$
			getSignalService().signalAccount("https://schemas.openid.net/secevent/risc/event-type/account-purged", 
					account.getName(), account.getSystem(), new String[] {});
		}
		else if ( ! AccountStatus.ARCHIVED.equals(ae.getStatus()) && account.getStatus().equals(AccountStatus.ARCHIVED))
		{
			getSignalService().signalAccount("https://schemas.openid.net/secevent/risc/event-type/account-disabled", 
					account.getName(), account.getSystem(), new String[] {});
			audit("I", ae); //$NON-NLS-1$
		}
		else if ((! ae.isDisabled() || AccountStatus.ARCHIVED == ae.getStatus()) && account.isDisabled())
		{
			anyChange = true;
			audit("e", ae); //$NON-NLS-1$
			if (account.getStatus() == AccountStatus.LOCKED)
				getSignalService().signalAccount("https://schemas.openid.net/secevent/risc/event-type/credential-compromise", 
						account.getName(), account.getSystem(), new String[] {});
			else
				getSignalService().signalAccount("https://schemas.openid.net/secevent/risc/event-type/account-disabled", 
					account.getName(), account.getSystem(), new String[] {});
		}
		else if (ae.isDisabled() && !account.isDisabled())
		{
			audit("E", ae); //$NON-NLS-1$
			anyChange = true;
			getSignalService().signalAccount("https://schemas.openid.net/secevent/risc/event-type/account-enabled", 
					account.getName(), account.getSystem(), new String[] {});
		}
		if (! account.getType().equals( ae.getType() ) )
		{
			anyChange = true;
			if (ae.getType().equals(AccountType.USER))
			{
				account.setOwnerUsers(new LinkedList<String>());
				for (UserAccountEntity ua : ae.getUsers()) {
                    String u = (ua.getUser().getUserName());
                    getUserAccountEntityDao().remove(ua);
                    account.getOwnerUsers().add(u);
                    if (Hibernate.isInitialized(ua.getUser())) 
                    	ua.getUser().getAccounts().clear();
				}
				ae.getUsers().clear();
				if (ae.getSystem().isMainSystem() || ae.getSystem().getUrl() == null) {
					ae.setLastPasswordSet(null);
					ae.setPasswordExpiration(null);
					account.setLastPasswordSet(null);
					account.setPasswordExpiration(null);
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
				
				String owner = account.getOwnerUsers().iterator().next();
				
				UserEntity ue = getUserEntityDao().findByUserName(owner);
				UserAccountEntity uae = getUserAccountEntityDao().newUserAccountEntity();
				uae.setAccount(ae);
				uae.setUser(ue);
				getUserAccountEntityDao().create(uae);
				
				account.setDescription(ue.getFullName());
				
				com.soffid.iam.api.System dispatcher = getDispatcherService().findDispatcherByName(account.getSystem());
				account.setDisabled(!getDispatcherService().isUserAllowed(dispatcher, owner));
				
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

		if (ae.getType().equals(AccountType.USER)) {
			for (UserAccountEntity ua: ae.getUsers()) {
				if (!"S".equals(ua.getUser().getActive())) { //$NON-NLS-1$
					Issue i = new Issue();
					i.setType("enabled-account-on-disabled-user"); //$NON-NLS-1$
					i.setCreated(new Date());
					i.setAccount(account.getName()+"@"+account.getSystem()); //$NON-NLS-1$
					IssueUser iu = new IssueUser();
					iu.setUserId(ua.getUser().getId());
					iu.setUserName(ua.getUser().getUserName());
					i.setUsers(Arrays.asList(iu));
					i.setHash(account.getId().toString());
					getIssueService().createInternalIssue(i);
				}
			}
		}

		if (! account.getName().equals(ae.getName()))
		{
			if (getAccountEntityDao().findByNameAndSystem(account.getName(), ae.getSystem().getName()) != null)
				throw new AccountAlreadyExistsException(String.format(Messages.getString("AccountServiceImpl.AccountAlreadyExists"), account.getName() + "@" + ae.getSystem().getName())); //$NON-NLS-1$ //$NON-NLS-2$
			anyChange = true;
			account.setOldName(ae.getName());
			getMetaDataEntityDao().renameAttributeValues(TypeEnumeration.ACCOUNT_TYPE, 
					ae.getName()+"@"+ae.getSystem().getName(),  //$NON-NLS-1$
					account.getName()+"@"+account.getSystem()); //$NON-NLS-1$
			getSignalService().signalAccount("https://schemas.openid.net/secevent/risc/event-type/identifier-changed", 
					account.getName(), account.getSystem(), new String[] {});
		}
		if ( (ae.getDescription() == null ? account.getDescription() != null: ! ae.getDescription().equals( account.getDescription())) ||
				(ae.getStatus() == null ? account.getStatus() != null : !ae.getStatus().equals(account.getStatus())) ||
				ae.isDisabled() != account.isDisabled() ||
				(ae.getLoginUrl() == null ? account.getLoginUrl() != null : ! ae.getLoginUrl().equals(account.getLoginUrl())) ||
				(ae.getLoginName() == null ? account.getLoginName() != null : ! ae.getLoginName().equals(account.getLoginName())) ||
				! ae.getPasswordPolicy().getName().equals(account.getPasswordPolicy()) ||
				(ae.getServerType() == null ? account.getServerType() != null : ! ae.getServerType().equals(account.getServerType())) ||
				(ae.getServerName() == null ? account.getServerName() != null : ! ae.getServerName().equals(account.getServerName()))
				)
			anyChange = true;

		if ( account.getLaunchType() == null && ae.getLaunchType() != null ||
				account.getLaunchType() != null && ! account.getLaunchType().equals(ae.getLaunchType()))
		{
			anyChange = true;
		}
		if (account.getJumpServerGroup() == null || account.getJumpServerGroup().trim().isEmpty())
		{
			if (ae.getJumpServerGroup() != null)
				anyChange = true;
		}
		else if (ae.getJumpServerGroup() == null)
		{
			anyChange = true;
		}
		else if (! ae.getJumpServerGroup().getName().equals(account.getJumpServerGroup()))
		{
			anyChange = true;
		}

		

		getAccountEntityDao().accountToEntity(account, ae, false);

		if (account.getType().equals(AccountType.USER)) {
			if (ae.getSystem().getUsage() != null && ! "IAM".equals(ae.getSystem().getUsage())) //$NON-NLS-1$
				throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.1"), ae.getSystem().getName()) ); //$NON-NLS-1$
			removeAcl (ae);
		}
		else if (updateAcl(ae, account))
		{
			anyChange = true;
		}
			
		if (anyChange)
			ae.setLastChange(new Date());
		
		getAccountEntityDao().update(ae, anyChange ? "U": null); //$NON-NLS-1$

		account = getVaultService().addToFolder(account);

		if (anyChange) {
			createAccountTask(ae);
			SoffidPrincipalImpl.clearCache();
		}		
		return account;
	}

	private void createUserTask(UserEntity ue) {
		TaskEntity tasque = getTaskEntityDao().newTaskEntity();
		tasque.setTransaction(TaskHandler.UPDATE_USER);
		tasque.setUser(ue.getUserName());
		getTaskEntityDao().create(tasque);
	}

	private void removeAcl(AccountEntity ae) {
		for (AccountAccessEntity aae: new LinkedList<AccountAccessEntity> (ae.getAcl()))
		{
			getAccountAccessEntityDao().remove(aae);
		}
	}

	@Override
    protected void handleRemoveAccount(com.soffid.iam.api.Account account) throws Exception {
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		createAccountTask(ae);
		for (UserAccountEntity userAccount: ae.getUsers())
		{
			getUserAccountEntityDao().remove(userAccount);
		}
		ae.getUsers().clear();
		for (RoleAccountEntity ra:  new LinkedList<RoleAccountEntity>(ae.getRoles()))
		{
			getRoleAccountEntityDao().remove(ra);
		}
		ae.getRoles().clear();
		for (IssueEntity issue: ae.getEvents()) {
			issue.setAccount(null);
			getIssueEntityDao().update(issue);
		}

		getAccountEntityDao().remove(ae);
		
		getSignalService().signalAccount("https://schemas.openid.net/secevent/risc/event-type/account-purged", 
				account.getName(), account.getSystem(), new String[] {});
	}

	private void createAccountTask(AccountEntity ae)
	{
		if (! ae.getType().equals(AccountType.IGNORED))
		{
			TaskEntity tasque = getTaskEntityDao().newTaskEntity();
			tasque.setTransaction(TaskHandler.UPDATE_ACCOUNT);
			tasque.setUser(ae.getName());
			tasque.setSystemName(ae.getSystem().getName());
			tasque.setDb(ae.getSystem().getName());
			getTaskEntityDao().create(tasque);
		}
	}
	
	private void notifyAccountPasswordChange(AccountEntity ae, GroupEntity ge, RoleEntity rolEntity, UserEntity usuariEntity) {
		if (! ae.getType().equals(AccountType.IGNORED))
		{
			TaskEntity tasque = getTaskEntityDao().newTaskEntity();
			tasque.setTransaction(TaskHandler.NOTIFY_PASSWORD_CHANGE);
			tasque.setSystemName(ae.getSystem().getName());
			tasque.setDb(ae.getSystem().getName());
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
		
		if ( !AutoritzacionsUsuari.hasQueryAccount())
		{
			Account account = getAccountEntityDao().toAccount(acc);
			if  (account.getAccessLevel() == AccountAccessLevelEnum.ACCESS_NONE ||
					account.getAccessLevel() == AccountAccessLevelEnum.ACCESS_NAVIGATE)
				return null;
			if (acc.getType().equals (AccountType.USER) && acc.getUsers().size() == 1)
				return getUserAccountEntityDao().toUserAccount(acc.getUsers().iterator().next());
			else
				return account;
		}
		else
		{
			if (acc.getType().equals (AccountType.USER) && acc.getUsers().size() == 1)
			{
				return getUserAccountEntityDao().toUserAccount(acc.getUsers().iterator().next());
			}
			else
				return getAccountEntityDao().toAccount(acc);
		}
	}

	@Override
	protected Account handleFindAccount(String accountAndDispatcher)
			throws Exception
	{
		int i = accountAndDispatcher.lastIndexOf("@"); //$NON-NLS-1$
		if (i < 0) return null;
		return handleFindAccount(accountAndDispatcher.substring(0, i), accountAndDispatcher.substring(i+1));
	}

	@Override
    protected List<UserAccount> handleFindUsersAccounts(String userName, String dispatcherName) throws Exception {
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
		
		Collection<RoleGrant> perms = getApplicationService().findEffectiveRoleGrantByUser(ue.getId());
        List<AccountEntity> accounts = getAccountEntityDao().findByUser(ue.getId());
		
		SystemEntityDao disDao = getSystemEntityDao();
		for (SystemEntity disEntity : disDao.findByUsage("IAM")) { //$NON-NLS-1$
            if (disEntity.getManualAccountCreation() != null && disEntity.getManualAccountCreation().booleanValue()) {
                for (AccountEntity acc : accounts) if (acc.getSystem() == disEntity) {
                    if ((acc.getStatus() == AccountStatus.LOCKED || acc.getStatus() == AccountStatus.DISABLED) && 
                    		"S".equals(ue.getActive())) { //$NON-NLS-1$
                        acc.setDisabled(false);
						acc.setStatus(AccountStatus.ACTIVE);
                        getAccountEntityDao().update(acc);
                        SoffidPrincipalImpl.clearCache();
                        audit("E", acc); //$NON-NLS-1$
                    }
                    if ( (acc.getStatus() == AccountStatus.ACTIVE ||
                    		acc.getStatus() == AccountStatus.FORCED_ACTIVE ) && !"S".equals(ue.getActive())) { //$NON-NLS-1$
                        acc.setDisabled(true);
						acc.setStatus(AccountStatus.DISABLED);
                        getAccountEntityDao().update(acc);
                        SoffidPrincipalImpl.clearCache();
                        audit("e", acc); //$NON-NLS-1$
                    }
                }
            } else if (disEntity.isMainSystem() || disEntity.getUrl() != null) {
                com.soffid.iam.api.System dis = disDao.toSystem(disEntity);
                String description = ue.getFullName();
                if (description.length() > 50) description = description.substring(0, 47) + "..."; //$NON-NLS-1$
                LinkedList<AccountEntity> accs = new LinkedList<AccountEntity>();
                for (AccountEntity account: accounts)
                	if (account.getSystem() == disEntity)
                		accs.add(account);
                if ("S".equals(ue.getActive()) && getDispatcherService().isUserAllowed(dis, user, perms)) { //$NON-NLS-1$
                    if (accs.isEmpty()) {
                        try {
                            generateAccount(null, ue, disEntity, false);
                            SoffidPrincipalImpl.clearCache();
                        } catch (Exception e) {
                            LogFactory.getLog(getClass()).warn(String.format(Messages.getString("AccountServiceImpl.ErrorGeneratinAccount"), user, dis.getName()), e); //$NON-NLS-1$
                        }
                    } else {
                        for (AccountEntity acc : accs) {
                            if ( acc.getStatus() != AccountStatus.FORCED_ACTIVE &&
                            		acc.getStatus() != AccountStatus.FORCED_DISABLED &&
                            		acc.getStatus() != AccountStatus.ACTIVE ) {
                                acc.setDisabled(false);
                                acc.setDescription(description);
    							acc.setStatus(AccountStatus.ACTIVE);
                                getAccountEntityDao().update(acc);
                                audit("E", acc); //$NON-NLS-1$
                                SoffidPrincipalImpl.clearCache();
                            }
                            if (!description.equals(acc.getDescription())) {
                                acc.setDescription(description);
                                getAccountEntityDao().update(acc);
                            }
                        }
                    }
                } else if (!accs.isEmpty()) {
                    for (AccountEntity acc : accs) {
                    	if (! "S".equals(ue.getActive()) && acc.getStatus() == AccountStatus.FORCED_ACTIVE || //$NON-NLS-1$
                    			acc.getStatus() == AccountStatus.ACTIVE )
                    	{
                            acc.setDisabled(true);
    						acc.setStatus(AccountStatus.DISABLED);
                            acc.setDescription(description);
                            getAccountEntityDao().update(acc);
                            audit("e", acc); //$NON-NLS-1$
                            SoffidPrincipalImpl.clearCache();
                   		}
                        if (! description.equals(acc.getDescription())) {
                            acc.setDescription(description);
                            getAccountEntityDao().update(acc);
                        }
                    }
                }
            } else if ( "N".equals(ue.getActive())) { // Disable accounts in offline systems //$NON-NLS-1$
                LinkedList<AccountEntity> accs = new LinkedList<AccountEntity>();
                for (AccountEntity account: accounts) {
                	if (account.getSystem() == disEntity && 
                			(account.getStatus() == AccountStatus.FORCED_ACTIVE || account.getStatus() == AccountStatus.ACTIVE ))
                	{
                        account.setDisabled(true);
						account.setStatus(AccountStatus.DISABLED);
                        getAccountEntityDao().update(account);
                        audit("e", account); //$NON-NLS-1$
                        SoffidPrincipalImpl.clearCache();
               		}
                }
            }
        }

		for (AccountEntity account: getAccountEntityDao().query(
				  "select account " //$NON-NLS-1$
				+ "from com.soffid.iam.model.AccountEntity as account " //$NON-NLS-1$
				+ "join account.system as system " //$NON-NLS-1$
				+ "join account.users as users " //$NON-NLS-1$
				+ "where account.disabled=:dis and system.usage=:usage and " //$NON-NLS-1$
				+ "users.user.id = :user", new Parameter[] { //$NON-NLS-1$
						new Parameter("dis", false), //$NON-NLS-1$
						new Parameter("usage", "PAM"), //$NON-NLS-1$ //$NON-NLS-2$
						new Parameter("user", ue.getId()) //$NON-NLS-1$
				})) {
            account.setDisabled(true);
			account.setStatus(AccountStatus.DISABLED);
            getAccountEntityDao().update(account);
            audit("e", account); //$NON-NLS-1$
            SoffidPrincipalImpl.clearCache();
            createAccountTask(account);
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
			getSignalService().signalAccount("https://schemas.openid.net/secevent/risc/event-type/identifier-changed", 
					account.getName(), account.getSystem(), new String[] {});
			if (accountEntity.getOldName() == null)
				accountEntity.setOldName(accountEntity.getName());
			accountEntity.setName(account.getName());
			dao.update(accountEntity);
			createAccountTask(accountEntity);
		}
		else if (! oldAccount.getId().equals(accountEntity.getId()))
		{
			throw new AccountAlreadyExistsException(account.getName() + "@" + account.getSystem()); //$NON-NLS-1$
		}
	}

	private void addGroups(HashMap<String, Group> groups, GroupEntity grup) {
		if (!groups.containsKey(grup.getName()))
		{
			Group grupVO = getGroupEntityDao().toGroup(grup);
			groups.put(grup.getName(), grupVO);
			if (grup.getParent() != null)
				addGroups(groups, grup.getParent());	
		}
	}

	@Override
	protected String handleGuessAccountName(String userName, String dispatcherName)
			throws Exception
	{
		SystemEntity dispatcher = getSystemEntityDao().findByName(dispatcherName);
		
		UserDomainEntity du = getUserDomainEntityDao().findBySytem(dispatcherName);
		// Search if already has a user name for this user domain
		
		UserEntity ue = getUserEntityDao().findByUserName(userName);
		return guessAccountName(dispatcherName, du, ue);
	}

	private String guessAccountName(String dispatcherName, UserDomainEntity du, UserEntity ue)
			throws Exception {
		if (ue == null)
			return null;

		String userName = ue.getUserName();
		
		if (du.getType().equals(TipusDominiUsuariEnumeration.PRINCIPAL))
			return userName;
		else if (du.getType().equals(TipusDominiUsuariEnumeration.SHELL))
		{
			Object o = evalExpression(du, ue, dispatcherName, du.getBshExpr(), "User domain "+du.getName()); //$NON-NLS-1$
			if (o != null && ! (o instanceof String))
				throw new InternalErrorException(
						String.format(Messages.getString("AccountServiceImpl.53"), //$NON-NLS-1$
								du.getName(), o.toString()));
			return (String) o;
		}
		else if (du.getType().equals(TipusDominiUsuariEnumeration.SPRINGCLASS))
		{
			Object obj = applicationContext.getBean(du.getBeanGenerator());
			if (obj == null)
				throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.UknownBeanForDomain"), du.getBeanGenerator(), du.getName())); //$NON-NLS-1$
			if (! (obj instanceof AccountNameGenerator))
				throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.BeanNotImplementNameGenerator"), du.getBeanGenerator())); //$NON-NLS-1$
			AccountNameGenerator generator = (AccountNameGenerator) obj;
			SystemEntity de = dispatcherName == null ? null: getSystemEntityDao().findByName(dispatcherName);
			return generator.getAccountName(ue, de, du);
		}
		else
			return null;
	}

	@Override
	protected boolean handleNeedsAccount(String userName, String dispatcherName)
			throws Exception
	{
		SystemEntity dispatcher = getSystemEntityDao().findByName(dispatcherName);
		if (dispatcher.getManualAccountCreation() != null && dispatcher.getManualAccountCreation().booleanValue())
			return false;
		
		UserDomainEntity du = getUserDomainEntityDao().findBySytem(dispatcherName);
		UserEntity ue = getUserEntityDao().findByUserName(userName);
		
		return needsAccount(dispatcherName, du, ue);
	}


	private Object evalExpression(UserDomainEntity du, UserEntity ue, String dispatcherName,
			String expression, String label) throws Exception {
		User userVO = getUserEntityDao().toUser(ue);
		SystemEntity de = dispatcherName == null ? null : getSystemEntityDao().findByName(dispatcherName);
		
		HashMap<String, String> attributes;
		HashMap<String, Group> groups;
			
		attributes = new HashMap<String, String>();
		for (UserDataEntity dada : ue.getUserData()) {
            attributes.put(dada.getDataType().getName(), dada.getValue());
        }
				
		groups = new HashMap<String, Group>();
		addGroups(groups, ue.getPrimaryGroup());
		for (UserGroupEntity grup : ue.getSecondaryGroups()) 
			if (! Boolean.TRUE.equals(grup.getDisabled()))
				addGroups(groups, grup.getGroup());

		Map<String,Object> vars = new HashMap();
		vars.put("attributes", attributes); //$NON-NLS-1$
		vars.put("groups", groups); //$NON-NLS-1$
		vars.put("groupsList", groups.keySet()); //$NON-NLS-1$
		vars.put("user", userVO); //$NON-NLS-1$
		vars.put("userDomain", getUserDomainEntityDao().toUserDomain(du)); //$NON-NLS-1$
		vars.put("system", de == null ? null : getSystemEntityDao().toSystem(de)); //$NON-NLS-1$
		if (! Evaluator.instance().isSecure()) {
			vars.put("dominiEntity", du); //$NON-NLS-1$
			vars.put("dispatcherEntity", de); //$NON-NLS-1$
			vars.put("usuariEntity", ue); //$NON-NLS-1$
			vars.put("applicationContext", applicationContext); //$NON-NLS-1$
			vars.put("dao", getAccountEntityDao()); //$NON-NLS-1$
		}				
		return Evaluator.instance().evaluate(expression, vars, label);
	}

	@Override
	protected List<Account> handleFindAccountsByCriteria(AccountCriteria criteria)
		throws Exception
	{
		AccountEntityDao dao = getAccountEntityDao();
		int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
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
				if (! Boolean.TRUE.equals(aae.getDisabled()))
				{
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
		}
		
		return users;
	}

	/**
	 * @param rol
	 * @param users
	 * @throws InternalErrorException 
	 */
	private void addRolMembers(RoleEntity rol, Set<String> users) throws InternalErrorException {
		Collection<RoleGrant> grants = getApplicationService().findEffectiveRoleGrantsByRoleId(rol.getId());
		for (RoleGrant grant : grants) {
            if (grant.getUser() != null) users.add(grant.getUser());
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
			if (! Boolean.TRUE.equals(ug.getDisabled()))
				users.add(ug.getUser().getUserName());
        }
		for (GroupEntity child : group.getChildren()) {
            addGroupMembers(child, users);
        }
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleGetUserGrantedAccounts(es.caib.seycon.ng.comu.Usuari)
	 */
	@Override
    protected Collection<Account> handleGetUserGrantedAccounts(User usuari) throws Exception {
		return handleGetUserGrantedAccounts(usuari, AccountAccessLevelEnum.ACCESS_USER);
	}

	@Override
    protected Collection<Long> handleGetUserGrantedAccountIds(User usuari) throws Exception {
		Set<Long> vos = new HashSet<Long>();
		AccountAccessLevelEnum level = AccountAccessLevelEnum.ACCESS_USER;
		
		if (!AutoritzacionsUsuari.hasQueryAccount())
		{
			User caller = getUserService().getCurrentUser();
			if (caller != null && ! caller.getId().equals( usuari.getId()) )
				throw new SecurityException(Messages.getString("AccountServiceImpl.PermissionDenied")); //$NON-NLS-1$
		}
		Collection<RoleGrant> grants = getApplicationService().findEffectiveRoleGrantByUser(usuari.getId());
		for (RoleGrant rg : grants) {
            RoleEntity r = getRoleEntityDao().load(rg.getRoleId());
            for (AccountAccessEntity aae : r.getAccountAccess()) {
                if (! Boolean.TRUE.equals(aae.getDisabled()) && isGreaterOrIqualThan(aae.getLevel(), level))  {
                	vos.add(aae.getAccount().getId());
                }
            }
        }
		
		
		UserEntity ue = getUserEntityDao().load(usuari.getId());
		addGrantedAccounts(ue.getPrimaryGroup(), null, vos, level);
		for (UserGroupEntity ug : ue.getSecondaryGroups()) {
			if (! Boolean.TRUE.equals(ug.getDisabled()))
				addGrantedAccounts(ug.getGroup(), null, vos, level);
        }
		
		for (AccountAccessEntity aae: ue.getAccountAccess())
		{
			if (! Boolean.TRUE.equals(aae.getDisabled()) && isGreaterOrIqualThan(aae.getLevel(), level))
			{
				vos.add(aae.getAccount().getId());
			}
		}
		
		for (UserAccountEntity uae: ue.getAccounts())
		{
			if (uae.getAccount().getType().equals (AccountType.USER)) {
				vos.add(uae.getAccount().getId());
			}
		}
		return vos;
	}

	/**
	 * @param grupPrimari
	 * @param accounts
	 * @param vos 
	 */
	private void addGrantedAccounts(GroupEntity grup, Map<AccountEntity, AccountAccessLevelEnum> accounts, Set<Long> ids, AccountAccessLevelEnum level) {
		for (AccountAccessEntity aae: grup.getAccountAccess())
		{
			if (!Boolean.TRUE.equals(aae.getDisabled()) && isGreaterOrIqualThan(aae.getLevel(), level))
			{
				if (accounts != null) {
	            	AccountAccessLevelEnum current = accounts.get(aae.getAccount());
	            	if (current == null ||
	            			isGreaterOrIqualThan(aae.getLevel(), current))
	           			accounts.put(aae.getAccount(), aae.getLevel());
				} 
				if (ids != null)
					ids.add(aae.getAccount().getId());
			}
		}
		if (grup.getParent() != null)
			addGrantedAccounts(grup.getParent(), accounts, ids, level);
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
			ae.setOldName(null);
			ae.setLastUpdated(new Date());
			getAccountEntityDao().update(ae, "A"); //$NON-NLS-1$
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
			getAccountEntityDao().update(ae, null);
			ae.setPasswordStatus(PasswordValidation.PASSWORD_GOOD.toString());
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
			ae.setPasswordStatus(PasswordValidation.PASSWORD_GOOD.toString());
			getAccountEntityDao().update(ae, null);
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
		UserDomainService dominiUsuariService = getUserDomainService();
		PasswordPolicy politica = dominiUsuariService.findPolicyByTypeAndPasswordDomain(ae.getPasswordPolicy().getName(), ae.getSystem().getPasswordDomain().getName());
		if (politica == null)
			throw new BadPasswordException(Messages.getString("AccountServiceImpl.NoPolicyDefined")); //$NON-NLS-1$
		if (!politica.isAllowPasswordQuery())
			throw new BadPasswordException(Messages.getString("AccountServiceImpl.NotAllowedToQueryPassword")); //$NON-NLS-1$
		

		AccountAccessLevelEnum level = ae.getType() == AccountType.PRIVILEGED ? 
				AccountAccessLevelEnum.ACCESS_OWNER :
				AccountAccessLevelEnum.ACCESS_MANAGER;
		return handleQueryAccountPasswordBypassPolicy(account.getId(), level);
	}

	@Override
	protected Password handleQueryAccountPasswordBypassPolicy(long accountId, AccountAccessLevelEnum level)
			throws InternalErrorException, Exception {
		User usuari = getUserService().getCurrentUser();
		
		AccountEntity acc = getAccountEntityDao().load(accountId);
		if (acc == null)
			return null;
		
		ServerEntityDao dao = getServerEntityDao();
		Exception lastException = null;
		for (ServerEntity se : dao.loadAll()) {
            if (se.getType().equals(ServerType.MASTERSERVER)) {
            	if (se.getInstances().isEmpty()) {
	                try {
	                    Password p = getPassword(level, usuari, acc, se.getUrl(), se.getAuth());
	                    if (p != null)
	                    	return p;
	                } catch (Exception e) {
	                    lastException = e;
	                }
            	} else {
            		for (ServerInstanceEntity si: se.getInstances()) {
            			try {
		                    Password p = getPassword(level, usuari, acc, si.getUrl(), si.getAuth());
		                    if (p != null)
		                    	return p;
		                } catch (Exception e) {
		                    lastException = e;
		                }
            		}
            	}
            }
        }
		if (lastException != null)
			throw lastException;
		return null;
	}

	public Password getPassword(AccountAccessLevelEnum level, User usuari, AccountEntity acc, 
			String url, String auth) throws IOException, InternalErrorException {
        RemoteServiceLocator rsl = new RemoteServiceLocator(url);
        rsl.setAuthToken(auth);
		rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount()); //$NON-NLS-1$
		SyncStatusService sss = rsl.getSyncStatusService();
		Password p = sss.getAccountPassword(usuari.getUserName(), acc.getId(), level);
		if (p != null) {
		    Audit audit = new Audit();
		    audit.setAction("S"); //$NON-NLS-1$
		    audit.setObject("SSO"); //$NON-NLS-1$
		    audit.setAuthor(Security.getCurrentUser());
		    audit.setCalendar(Calendar.getInstance());
		    audit.setAccount(acc.getName());
		    audit.setDatabase(acc.getSystem().getName());
		    audit.setApplication("-"); //$NON-NLS-1$
		    getAuditService().create(audit);
		}
		return p;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleSetAccountPassword(es.caib.seycon.ng.comu.Account, es.caib.seycon.ng.comu.Password)
	 */
	@Override
	protected void handleSetAccountPassword (Account account, Password password)
					throws Exception
	{
		setAccountPasswordInternal(account, password, false, true);
	}

	@Override
	protected Password handleSetAccountPassword (Account account, Password password, boolean temporary, boolean online)
					throws Exception
	{
		return setAccountPasswordInternal(account, password, temporary, online);
	}

	private Password setAccountPasswordInternal(Account account, Password password, boolean temporary, boolean online)
			throws InternalErrorException, BadPasswordException, Exception {
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		
		Password result = null;
		checkCanSetPassword(account, ae);
		
		InternalPasswordService ips = getInternalPasswordService();
		/// Now, do the job
		if (password == null)
		{
			log.info("generating new password"); //$NON-NLS-1$
			result = ips.generateFakeAccountPassword(ae);
	        if (online) { 
	        	sendPasswordNow (ae, result, temporary);
	        }
	        else {
	    		getInternalPasswordService().storeAndForwardAccountPassword(ae, result, temporary, null);
	        }
		}
		else
		{
			result = password;
	        PolicyCheckResult check = ips.checkAccountPolicy(ae, password);
	        if (! check.isValid()) {
	            throw new BadPasswordException(check.getReason());
	        }
	        if (online)
	        	sendPasswordNow (ae, password, temporary);
	        else if (ae.getCredentialType() == null || ae.getCredentialType() == CredentialTypeEnum.CT_PASSWORD)
	    		getInternalPasswordService().storeAndForwardAccountPassword(ae, password, temporary, null);
	        else
	        	throw new InternalErrorException("Secret store is offline. Try again later");
		}
		// Now, audit
		audit("P", ae); //$NON-NLS-1$
		
		return result;
	}

	private void sendPasswordNow(AccountEntity account, Password password, boolean temporary ) throws InternalErrorException {
		if ( ! account.isDisabled() && account.getSystem().getUrl() != null || 
				account.getCredentialType() != null && account.getCredentialType() != CredentialTypeEnum.CT_PASSWORD)
		{
			for (ServerEntity se : getServerEntityDao().loadAll()) {
	            if (se.getType().equals(ServerType.MASTERSERVER)) {
	            	if (se.getInstances().isEmpty()) {
	            		if (sendPasswordNow(account, password, temporary, se.getUrl(), se.getAuth())) 
	            			return;
	            	} else {
	            		for (ServerInstanceEntity si: se.getInstances()) {
		            		if (sendPasswordNow(account, password, temporary, si.getUrl(), si.getAuth())) 
		            			return;
	            			
	            		}
	            	}
	            }
	        }
		}
		getInternalPasswordService().storeAndForwardAccountPassword(account, password, temporary, null);
	}

	private boolean sendPasswordNow(AccountEntity account, Password password, boolean temporary, String url, String auth)
			throws InternalErrorException {
		SyncStatusService sss = null;
		try {
		    RemoteServiceLocator rsl = new com.soffid.iam.remote.RemoteServiceLocator(url);
		    rsl.setAuthToken(auth);
			rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount()); //$NON-NLS-1$
		    sss = rsl.getSyncStatusService();
		} catch (Exception e) {
			log.warn("Error sending password", e); //$NON-NLS-1$
		}
		if (sss != null)
		{
			sss.setAccountPassword(account.getName(), account.getSystem().getName(), password, temporary);
			return true;
		} else
			return false;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleSetHPAccountPassword(es.caib.seycon.ng.comu.Account, es.caib.seycon.ng.comu.Password, boolean)
	 */
	@Override
	protected boolean handleSetHPAccountPassword (Account account, Password password,
					java.util.Date date, boolean force) throws Exception
	{
		AccountEntity ae = getAccountEntityDao().load(account.getId());

		if (! ae.getType().equals(AccountType.PRIVILEGED))
		{
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NotAuthorizedChangePassword"))); //$NON-NLS-1$
		}


		String principal = Security.getCurrentAccount();
		com.soffid.iam.service.InternalPasswordService ips = getInternalPasswordService();
		String dispatcher = ips.getDefaultDispatcher();
		AccountEntity caller = getAccountEntityDao().findByNameAndSystem(principal, dispatcher);
		if (caller == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.AccountNotFound"), principal, dispatcher)); //$NON-NLS-1$
		UserEntity callerUe = getUserForAccount(caller);
		if (callerUe == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.UserNotFoundForAccount"), principal, dispatcher)); //$NON-NLS-1$

		if (! force )
		{
			if ( ! ae.getUsers().isEmpty())
			{
				UserEntity currentUser = ae.getUsers().iterator().next().getUser();
				if (! currentUser.getId().equals(callerUe.getId()))
					throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.62"), currentUser.getUserName())); //$NON-NLS-1$
			}
		}
		if (! Security.isUserInRole(Security.AUTO_ACCOUNT_HP_PASSWORD))
		{
			// Check if policy allows user change
			UserDomainService dominiUsuariService = getUserDomainService();
			PasswordPolicy politica = dominiUsuariService.findPolicyByTypeAndPasswordDomain(
							ae.getPasswordPolicy().getName(), 
							ae.getSystem().getPasswordDomain().getName());
			if (politica == null)
				throw new BadPasswordException(Messages.getString("AccountServiceImpl.NoPolicyDefined")); //$NON-NLS-1$
			if (!politica.isAllowPasswordChange())
				throw new BadPasswordException(Messages.getString("AccountServiceImpl.NotAllowedToChangePassword")); //$NON-NLS-1$
			PolicyCheckResult pcr = ips.checkAccountPolicy(ae, password);
			if (!pcr.isValid())
				throw new BadPasswordException(pcr.getReason());
			
			// Check user authorization
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
					Collection<String> users = handleGetAccountUsers(account, AccountAccessLevelEnum.ACCESS_OWNER);
					boolean found = false;
					for (String user : users) {
                        if (user.equals(callerUe.getUserName())) {
                            found = true;
                            break;
                        }
                    }
					if (!found)
					{
						// Try to request throw workflow
						users = handleGetAccountUsers(account, AccountAccessLevelEnum.ACCESS_MANAGER);
						found = false;
						for (String user : users) {
	                        if (user.equals(callerUe.getUserName())) {
	                        	// Launch workflow
	            				if ( startHPWorkflow(account, password, date)) 
	            					return false;
	                        }
						}
						throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NotAuthorizedChangePassForAccount"))); //$NON-NLS-1$
					}
				}
				else if (ae.getType().equals(AccountType.SHARED))
				{
					throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NotChangePasswordAccountShared"))); //$NON-NLS-1$
				}
			}
		}
		
		/// Now, do the job
        PolicyCheckResult check = ips.checkAccountPolicy(ae, password);
        if (! check.isValid()) {
            throw new BadPasswordException(check.getReason());
        }
        sendPasswordNow(ae, password, false);
//		ips.storeAndForwardAccountPassword(ae, password, false, date);
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
		
		return true;
	}
	
	private boolean startHPWorkflow(Account acc, Password p, Date until) throws InternalErrorException {
		List def = getBpmEngine().findProcessDefinitions(null, PredefinedProcessType.PRIVILEGED_ACCOUNT);
		if (def.isEmpty())
			return false;
		JbpmContext ctx = getBpmEngine().getContext();
		try {
			com.soffid.iam.bpm.api.ProcessDefinition pd = (com.soffid.iam.bpm.api.ProcessDefinition) def.get(0);
			org.jbpm.graph.exe.ProcessInstance pi = ctx.newProcessInstance(pd.getName());
			LoggingInstance li = (LoggingInstance) pi.getInstance(LoggingInstance.class);
			if (li == null) {
				li = new LoggingInstance();
				pi.addInstance(li);
			}
			AuthenticationLog log = new AuthenticationLog();
			log.setToken(pi.getRootToken());
			log.setActorId(Security.getCurrentUser());
			li.startCompositeLog(log);
			pi.getContextInstance().createVariable("requester", Security.getCurrentUser()); //$NON-NLS-1$
			pi.getContextInstance().createVariable("requesterFullName", Security.getSoffidPrincipal().getFullName()); //$NON-NLS-1$
			pi.getContextInstance().createVariable("account", acc.getId()); //$NON-NLS-1$
			pi.getContextInstance().createVariable("accountSystem", acc.getSystem()); //$NON-NLS-1$
			pi.getContextInstance().createVariable("accountName", acc.getName()); //$NON-NLS-1$
			pi.getContextInstance().createVariable("password", p.toString()); //$NON-NLS-1$
			pi.getContextInstance().createVariable("until", until); //$NON-NLS-1$
			pi.signal();
			ctx.save(pi);
			if (li != null) {
				li.endCompositeLog();
			}
			return true;
		} finally {
			ctx.close();
		}
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
		else
		{
			for (UserAccountEntity uae: acc.getUsers())
			{
				if ( Boolean.TRUE.equals( uae.getApproved()) &&
						uae.getUntilDate() != null &&
						uae.getUntilDate().after(new Date()))
					ue = uae.getUser();
			}
		}
		return ue;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountService#getUserAccounts(es.caib.seycon.ng.comu.Usuari)
	 */
	public Collection<UserAccount> handleGetUserAccounts(User usuari) throws InternalErrorException {
		if (!AutoritzacionsUsuari.hasQueryAccount())
		{
			User caller = getUserService().getCurrentUser();
			if (caller != null && caller.getId() != usuari.getId())
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
		

		if (accEntity.getSystem().getUrl() == null)
			return false;

		Calendar c = Calendar.getInstance();
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
                                if (!"S".equals(tl.getCompleted())) return true; //$NON-NLS-1$
                            }
                        }
                        if (!found) return true;
                    }
                }
            }
		}
		return false;
		
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleIsUpdatePending(es.caib.seycon.ng.comu.Account)
	 */
	@Override
	protected int handleIsUpdatePendingExtended (Account account) throws Exception
	{
		int status = 0;
		AccountEntity accEntity = getAccountEntityDao().load(account.getId());
		if (accEntity == null)
			return 0;

		if (accEntity.getSystem().getUrl() == null ||
			account.getType() == AccountType.IGNORED ||
			Boolean.TRUE.equals(accEntity.getPasswordPolicy().getUnmanaged())) 
			return 0;
		
		List<TaskEntity> coll = getTaskEntityDao().findByAccount(accEntity.getName(), accEntity.getSystem().getName());
		for (TaskEntity tasque : coll) {
            if (tasque.getTransaction().equals(TaskHandler.UPDATE_ACCOUNT) || tasque.getTransaction().equals(TaskHandler.UPDATE_ACCOUNT_PASSWORD) || tasque.getTransaction().equals(TaskHandler.PROPAGATE_ACCOUNT_PASSWORD)) {
            	if ("X".equals( tasque.getStatus()) && status < 1) //$NON-NLS-1$
            		status = 1;
            	else if ("E".equals( tasque.getStatus()) && status < 3) //$NON-NLS-1$
            		status = 3;
            	else if (status < 2) 
            		status = 2;
            }
        }

		if (accEntity.getType().equals(AccountType.USER))
		{
			for (UserAccountEntity ua : accEntity.getUsers()) {
                coll = getTaskEntityDao().findByUser(ua.getUser().getUserName());
                for (TaskEntity tasque : coll) {
                    if (tasque.getTransaction().equals(TaskHandler.UPDATE_USER) || accEntity.getSystem().getPasswordDomain().getName().equals(tasque.getPasswordsDomain()) && (tasque.getTransaction().equals(TaskHandler.UPDATE_USER_PASSWORD) || tasque.getTransaction().equals(TaskHandler.PROPAGATE_PASSWORD))) {
                    	if ("X".equals( tasque.getStatus())) //$NON-NLS-1$
                    	{
                    		if (status < 1) status = 1;
                    	} else {
                    		boolean found = false;
	                        for (TaskLogEntity tl : tasque.getLogs()) {
	                            if (tl.getSystem().getId().equals(accEntity.getSystem().getId())) {
	                                found = true;
	                                if (!"S".equals(tl.getCompleted())) { //$NON-NLS-1$
	                                	if (tl.getExecutionsNumber() != null && tl.getExecutionsNumber().longValue() > 1L && status < 3)
	                                		status = 3;
	                                	else if (status < 2) 
	                                		status = 2;
	                                }
	                            }
	                        }
	                        if (!found && status < 2) status = 2 ;
                    	}
                    }
                }
            }
		}
		return status;
		
	}


	final static Collection<String> traceableAccountActions = Arrays.asList("E", "M", "I", "C", "D", "e");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	private void audit(String action, AccountEntity account) throws Exception {

        String codiUsuariCanvi = Security.getCurrentAccount();
        // Fem un nestedlogin per obtindre autorització per fer auditoria

        Audit auditoria = new Audit();
        auditoria.setAction(action); //$NON-NLS-1$
        auditoria.setAccount(account.getName());
        auditoria.setDatabase(account.getSystem().getName());
        auditoria.setAuthor(codiUsuariCanvi);
        auditoria.setCalendar(Calendar.getInstance());
        auditoria.setObject("SC_ACCOUN"); //$NON-NLS-1$
        if (account.getType().equals (AccountType.USER))
        {
        	for (UserAccountEntity ua : account.getUsers()) auditoria.setUser(ua.getUser().getUserName());
        }

    	if (auditoria.getObject().equals(Messages.getString("AccountServiceImpl.81")) && traceableAccountActions.contains(auditoria.getAction())) { //$NON-NLS-1$
    		auditoria.setSearchIndex("ACC#"+account.getId()); //$NON-NLS-1$
    	}
        getAuditService().create(auditoria);
    }

    /* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleFindAccountsNearToExpire(java.util.Date, java.util.Date, java.util.Collection, java.util.Collection)
	 */
	@Override
    protected List<Account> handleFindAccountsNearToExpire(Date currentDate, Date limitDate, Collection<AccountType> accTypes, Collection<UserType> userTypes) throws Exception {
		String query;												// Query to execute
		String element;												// Element to add to query
		List<Account> accountList = new LinkedList<Account>();		// Accounts list
		List<Parameter> paramsList = new LinkedList<Parameter>();	// Parameters list
		Collection accountEntities = null;							// Accounts entities
		Iterator it = null;											// Iterator to manage list
		
		query = "select account " + //$NON-NLS-1$
			"from com.soffid.iam.model.AccountEntity as account " + //$NON-NLS-1$
			"where account.passwordExpiration between :currentDate and :limitDate and " //$NON-NLS-1$
			+ "account.system.tenant.id = :tenantId "; //$NON-NLS-1$
		
		paramsList.add(new Parameter("currentDate", currentDate)); //$NON-NLS-1$
		paramsList.add(new Parameter("limitDate", limitDate)); //$NON-NLS-1$
		paramsList.add(new Parameter("tenantId", Security.getCurrentTenantId())); //$NON-NLS-1$
		
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
			
			Iterator<UserType> it2 = userTypes.iterator();
			while (it2.hasNext())
			{
				UserType element2 = it2.next();
				query += "passwordPolicy.codi = :passwordPolicy" + element2.getCode(); //$NON-NLS-1$
				paramsList.add(new Parameter("passwordPolicy" + element2.getCode(), element2.getCode())); //$NON-NLS-1$
				
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
    protected User handleGetHPAccountOwner(Account account) throws Exception {
		AccountEntity ae = getAccountEntityDao().load(account.getId());

		if (! ae.getType().equals(AccountType.PRIVILEGED))
		{
			return null;
		}


		String principal = Security.getCurrentAccount();
		com.soffid.iam.service.InternalPasswordService ips = getInternalPasswordService();
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
					Collection<String> users = handleGetAccountUsers(account, AccountAccessLevelEnum.ACCESS_USER);
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
			return getUserEntityDao().toUser(currentUser);
		}
	}

	@Override
	protected void handleCheckinHPAccount(Account account) throws Exception {
		AccountEntity ae = getAccountEntityDao().load(account.getId());

		if (! ae.getType().equals(AccountType.PRIVILEGED))
		{
			throw new InternalErrorException(Messages.getString("AccountServiceImpl.85")); //$NON-NLS-1$
		}

		
		String principal = Security.getCurrentAccount();
		com.soffid.iam.service.InternalPasswordService ips = getInternalPasswordService();
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
				throw new SecurityException(Messages.getString("AccountServiceImpl.86")); //$NON-NLS-1$
			}
		}
	}

	@Override
	protected void handleCheckinHPAccounts() throws Exception {
		List<AccountEntity> accounts = getAccountEntityDao().query("" //$NON-NLS-1$
				+ "select acc\n" +  //$NON-NLS-1$
				"from   com.soffid.iam.model.AccountEntity as acc\n" +  //$NON-NLS-1$
				"join   acc.system as dispatcher\n" +  //$NON-NLS-1$
				"join   acc.users as uac\n" +  //$NON-NLS-1$
				"join   uac.user as user\n" +  //$NON-NLS-1$
				"where acc.type='P' and dispatcher.tenant.id=:tenantId\n" +  //$NON-NLS-1$
				"order by dispatcher.name, acc.name, acc.loginName", //$NON-NLS-1$
				new Parameter[] {
						new Parameter("tenantId", Security.getCurrentTenantId()) //$NON-NLS-1$
				});

		Date now = new Date();
		for (AccountEntity account: accounts) {
			for (UserAccountEntity uae: new LinkedList<UserAccountEntity>(account.getUsers()))
			{
				if (uae.getUntilDate().before(now)) {
					getUserAccountEntityDao().remove(uae);
					audit("R", account); //$NON-NLS-1$
					Password p = getInternalPasswordService().generateFakeAccountPassword(account);
					getInternalPasswordService().storeAndForwardAccountPassword(account, p, false, null);
				}
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
    protected Collection<Account> handleGetUserGrantedAccounts(User usuari, AccountAccessLevelEnum level) throws Exception {
		if (!AutoritzacionsUsuari.hasQueryAccount())
		{
			User caller = getUserService().getCurrentUser();
			if (caller != null && ! caller.getId().equals( usuari.getId()) )
				throw new SecurityException(Messages.getString("AccountServiceImpl.PermissionDenied")); //$NON-NLS-1$
		}
		Collection<RoleGrant> grants = getApplicationService().findEffectiveRoleGrantByUser(usuari.getId());
		Map<AccountEntity, AccountAccessLevelEnum> accounts = new HashMap<AccountEntity,AccountAccessLevelEnum>();
		for (RoleGrant rg : grants) {
            RoleEntity r = getRoleEntityDao().load(rg.getRoleId());
            for (AccountAccessEntity aae : r.getAccountAccess()) {
                if (! Boolean.TRUE.equals(aae.getDisabled()) && isGreaterOrIqualThan(aae.getLevel(), level))  {
                	AccountAccessLevelEnum current = accounts.get(aae.getAccount());
                	if (current == null ||
                			isGreaterOrIqualThan(aae.getLevel(), current))
               			accounts.put(aae.getAccount(), aae.getLevel());
                }
            }
        }
		
		
		UserEntity ue = getUserEntityDao().load(usuari.getId());
		addGrantedAccounts(ue.getPrimaryGroup(), accounts, null, level);
		for (UserGroupEntity ug : ue.getSecondaryGroups()) {
			if (! Boolean.TRUE.equals(ug.getDisabled()))
				addGrantedAccounts(ug.getGroup(), accounts, null, level);
        }
		
		for (AccountAccessEntity aae: ue.getAccountAccess())
		{
			if (! Boolean.TRUE.equals(aae.getDisabled()) && isGreaterOrIqualThan(aae.getLevel(), level))
			{
            	AccountAccessLevelEnum current = accounts.get(aae.getAccount());
            	if (current == null ||
            			isGreaterOrIqualThan(aae.getLevel(), current))
           			accounts.put(aae.getAccount(), aae.getLevel());
				
			}
		}
		
		for (UserAccountEntity uae: ue.getAccounts())
		{
			if (uae.getAccount().getType().equals (AccountType.USER)) {
     			accounts.put(uae.getAccount(), AccountAccessLevelEnum.ACCESS_OWNER);
			}
		}
		List<Account> vos = new LinkedList<Account>();
		for ( Entry<AccountEntity, AccountAccessLevelEnum> entry: accounts.entrySet())
		{
			AccountEntity accEntity = entry.getKey();
			if (accEntity.getStatus() != AccountStatus.REMOVED)
			{
				Account acc = getAccountEntityDao().toAccount(accEntity);
				if (accEntity.getType().equals(AccountType.USER)) {
					for (UserAccountEntity uac: accEntity.getUsers())
						acc = getUserAccountEntityDao().toUserAccount(uac);
				}
				acc.setAccessLevel(entry.getValue());
				vos.add(acc);
			}
		}
		return vos;
	}

	@Override
    protected List<UserData> handleGetAccountAttributes(Account acc) throws Exception {
		AccountEntity accountEntity = getAccountEntityDao().load(acc.getId());
		if (accountEntity == null)
			return Collections.emptyList();
		else
		{
			List<AccountMetadataEntity> metaList = getAccountMetadataEntityDao().findBySystem(accountEntity.getSystem().getName());
			Collections.sort(metaList, new Comparator<AccountMetadataEntity>() {
				public int compare(AccountMetadataEntity o1, AccountMetadataEntity o2) {
					return o1.getOrder().compareTo(o2.getOrder());
				}
			});
			LinkedList<UserData> result = new LinkedList<UserData>();
			for (AccountMetadataEntity metadata : metaList) {
                boolean found = false;
                for (AccountAttributeEntity data : accountEntity.getAttributes()) {
                    if (data.getSystemMetadata() == metadata) {
                        found = true;
                        AttributeVisibilityEnum vis = data.getAttributeVisibility();
                        if (vis.equals(AttributeVisibilityEnum.EDITABLE) || vis.equals(AttributeVisibilityEnum.READONLY)) result.add(getAccountAttributeEntityDao().toUserData(data));
//                        break;
                    }
                }
                if (!found) {
                    AccountAttributeEntity data = getAccountAttributeEntityDao().newAccountAttributeEntity();
                    data.setSystemMetadata(metadata);
                    data.setAccount(accountEntity);
                    if (data.getAttributeVisibility().equals(AttributeVisibilityEnum.EDITABLE) || data.getAttributeVisibility().equals(AttributeVisibilityEnum.READONLY)) {
                        UserData d = getAccountAttributeEntityDao().toUserData(data);
                        result.add(d);
                    }
                }
            }
			return result;
		}
	}

	private void auditChange(UserData dadaUsuari, AccountEntity account) throws InternalErrorException {
		Audit audit = new Audit();
		audit.setObject("SC_ACCATT"); //$NON-NLS-1$
		audit.setAction("U"); //$NON-NLS-1$
		audit.setAccount(dadaUsuari.getAccountName());
		audit.setDatabase(dadaUsuari.getSystemName());
		audit.setCalendar(Calendar.getInstance());
		audit.setConfigurationParameter(dadaUsuari.getAttribute());
		audit.setAuthor(Security.getCurrentAccount());
		if (account.getType().equals (AccountType.USER))
		{
			for (UserAccountEntity uae : account.getUsers()) {
                audit.setObject("SC_DADUSU"); //$NON-NLS-1$
                audit.setUser(uae.getUser().getUserName());
            }
		}
		getAuditService().create(audit);
	}


	@Override
    protected UserData handleCreateAccountAttribute(UserData attribute) throws Exception {
		AccountEntity accountEntity = getAccountEntityDao().findByNameAndSystem(attribute.getAccountName(), attribute.getSystemName());
		if (accountEntity == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.AccountNotFound"), attribute.getAccountName(), attribute.getSystemName())); //$NON-NLS-1$

		
		AccountMetadataEntity meta = getAccountMetadataEntityDao().findByName(attribute.getSystemName(), 
				attribute.getAttribute());
		if (meta == null)
		{
			MetaDataEntity meta0 = findCommonMetadata(accountEntity, attribute.getAttribute());
			if (meta0 == null)
				throw new InternalErrorException(Messages.getString("AccountServiceImpl.99") + attribute.getAttribute()); //$NON-NLS-1$
		}
		AccountAttributeEntity entity = getAccountAttributeEntityDao().userDataToEntity(attribute);
		AttributeVisibilityEnum visibility = entity.getAttributeVisibility();
		if ( visibility == AttributeVisibilityEnum.EDITABLE)
		{
			if (attribute.getId() == null)
			{
				getAccountAttributeEntityDao().create(entity);
			} else {
				getAccountAttributeEntityDao().update(entity);
			}
			createAccountTask(accountEntity);
			auditChange(attribute, accountEntity);
			return getAccountAttributeEntityDao().toUserData(entity);
		}
		else
		{
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.102"), attribute.getAttribute())); //$NON-NLS-1$
		}
	}

	@Override
    protected UserData handleUpdateAccountAttribute(UserData attribute) throws Exception {
		return handleCreateAccountAttribute (attribute);
	}

	@Override
    protected void handleRemoveAccountAttribute(UserData attribute) throws Exception {
		AccountEntity accountEntity = getAccountEntityDao().findByNameAndSystem(attribute.getAccountName(), attribute.getSystemName());
		if (accountEntity == null)
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.AccountNotFound"), attribute.getAccountName(), attribute.getSystemName())); //$NON-NLS-1$

		AccountMetadataEntity meta = getAccountMetadataEntityDao().findByName(attribute.getSystemName(), attribute.getAttribute());
		if (meta == null)
		{
			throw new InternalErrorException(Messages.getString("AccountServiceImpl.99") + attribute.getAttribute()); //$NON-NLS-1$
		}
		AccountAttributeEntity entity = getAccountAttributeEntityDao().userDataToEntity(attribute);
		AttributeVisibilityEnum visibility = entity.getAttributeVisibility();
		if ( visibility == AttributeVisibilityEnum.EDITABLE)
		{
			createAccountTask(accountEntity);
			getAccountAttributeEntityDao().remove(entity);
			auditChange(attribute, accountEntity);
		}
		else
		{
			throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.102"), attribute.getAttribute())); //$NON-NLS-1$
		}
	}

	@Override
	protected Account handleFindAccountById(long id) throws Exception {
		AccountEntity acc = getAccountEntityDao().load(id);
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
	protected Collection<Account> handleFindAccountByJsonQuery(String query) throws InternalErrorException, Exception {
		AsyncList<Account> result = new AsyncList<Account>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());
		findByJsonQuery(result, query, new CriteriaSearchConfiguration());
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return result.get();
	}
	

	@Override
	protected PagedResult handleFindAccountByJsonQuery(String query, Integer first, Integer max) throws InternalErrorException, Exception {
		AsyncList<Account> result = new AsyncList<Account>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());
		CriteriaSearchConfiguration cs = new CriteriaSearchConfiguration();
		cs.setFirstResult(first);
		cs.setMaximumResultSize(max);
		PagedResult<Account> r = findByJsonQuery(result, query, cs);
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return r;
	}

	@Override
	protected AsyncList<Account> handleFindAccountByJsonQueryAsync(final String query) throws Exception {
		final AsyncList<Account> result = new AsyncList<Account>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					findByJsonQuery(result, query, new CriteriaSearchConfiguration());
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}
	
	protected PagedResult<Account> findByJsonQuery ( AsyncList<Account> result, String query,
			CriteriaSearchConfiguration cs) 
			throws Exception
	{
		// Register virtual attributes for additional data
		AdditionalDataJSONConfiguration.registerVirtualAttributes();

		AbstractExpression expr = ExpressionParser.parse(query);
		expr.setOracleWorkaround( CustomDialect.isOracle());
		HQLQuery hql = expr.generateHSQLString(com.soffid.iam.api.Account.class);
		String qs = hql.getWhereString().toString();
		if (qs.isEmpty())
			qs = "o.system.tenant.id = :tenantId"; //$NON-NLS-1$
		else
			qs = "("+qs+") and o.system.tenant.id = :tenantId"; //$NON-NLS-1$ //$NON-NLS-2$
		hql.setWhereString(new StringBuffer(qs));
		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[1+params.size()];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		paramArray[i++] = new Parameter("tenantId", Security.getCurrentTenantId()); //$NON-NLS-1$
		
		
		@SuppressWarnings("unchecked")
		List <AccountEntity> r = ( List <AccountEntity>) new QueryBuilder()
				.query( hql.toString(), 
						paramArray,
						cs == null ?  new CriteriaSearchConfiguration() : cs);
		PagedResult<Account> pagedResult = new PagedResult<Account>();
		int totalResults = 0;
		for (AccountEntity ue :r) {
			if (result.isCancelled())
				return null;
			Account u = getAccountEntityDao().toAccount(ue);
			if (!hql.isNonHQLAttributeUsed() || expr.evaluate(u)) {
				if (getAuthorizationService().hasPermission(
						Security.AUTO_ACCOUNT_QUERY, ue)) {
					result.add(u);
				}
			}
		}
		pagedResult.setResources(result);
		pagedResult.setStartIndex( cs.getFirstResult() != null ? cs.getFirstResult(): 0);
		pagedResult.setItemsPerPage( cs.getMaximumResultSize());
		if ( cs.getMaximumResultSize()  != null) {
			@SuppressWarnings("unchecked")
			List <Long> ll = ( List <Long>) new QueryBuilder()
					.query( hql.toCountString(), 
							paramArray);
			for ( Long l: ll ) {
				pagedResult.setTotalResults( new Integer(l.intValue()) );
			}
		} else {
			pagedResult.setTotalResults(totalResults);
		}
		return pagedResult;
	}

	@Override
	protected Collection<Account> handleFindAccountByText(String text) throws Exception {
		LinkedList<Account> result = new LinkedList<Account>();
		for (AccountEntity ue : getAccountEntityDao().findByText(text)) {
			Account u = getAccountEntityDao().toAccount(ue);
			if (getAuthorizationService().hasPermission(
					Security.AUTO_ACCOUNT_QUERY, ue)) {
				result.add(u);
			}
		}

		return result;
	}

	@Override
	protected AsyncList<Account> handleFindAccountByTextAsync(final String text) throws Exception {
		final AsyncList<Account> result = new AsyncList<Account>();
		getAsyncRunnerService().run(
				new Runnable() {
					public void run () {
						try {
							for (AccountEntity e : getAccountEntityDao().findByText(text)) {
								if (result.isCancelled())
									return;
								Account v = getAccountEntityDao().toAccount(e);
								if (getAuthorizationService().hasPermission(
										Security.AUTO_ACCOUNT_QUERY, v)) {
									result.add(v);
								}
							}
						} catch (InternalErrorException e) {
							throw new RuntimeException(e);
						}
					}
				}, result);
		return result;
	}
	
	@Override
	protected String handlePredictAccountName(Long userId, String dispatcher, Long domainId) throws Exception {
		UserDomainEntity du = getUserDomainEntityDao().load(domainId);
		// Search if already has a user name for this user domain
		
		UserEntity ue = getUserEntityDao().load(userId);
		boolean needs = false;
		
		needs = needsAccount(dispatcher, du, ue);
		if (needs)
			return guessAccountName(dispatcher, du, ue);
		else
			return null;
	}

	private boolean needsAccount(String dispatcher, UserDomainEntity du, UserEntity ue)
			throws Exception {
		boolean needs = false;
		if (ue == null)
			needs = false;
		else if (du.getType().equals(TipusDominiUsuariEnumeration.PRINCIPAL))
			needs = true;
		else if (du.getType().equals(TipusDominiUsuariEnumeration.SHELL))
		{
			if (du.getBshExprCreate() == null || 
					du.getBshExprCreate().replace('\r', ' ').replace('\t', ' ').replace('\n', ' ').isEmpty())
				needs = true;
			else
			{
				Object o = evalExpression(du, ue, dispatcher, du.getBshExprCreate(), Messages.getString("AccountServiceImpl.107")+du.getName()); //$NON-NLS-1$
				if (o == null || ! (o instanceof Boolean))
					throw new InternalErrorException(
							String.format(Messages.getString("AccountServiceImpl.108"), //$NON-NLS-1$
									du.getName(), o == null ? "null": o.toString())); //$NON-NLS-1$
				needs = ((Boolean)o).booleanValue();
			}
		}
		else if (du.getType().equals(TipusDominiUsuariEnumeration.SPRINGCLASS))
		{
			Object obj = applicationContext.getBean(du.getBeanGenerator());
			if (obj == null)
				throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.UknownBeanForDomain"), du.getBeanGenerator(), du.getName())); //$NON-NLS-1$
			if (! (obj instanceof AccountNameGenerator))
				throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.BeanNotImplementNameGenerator"), du.getBeanGenerator())); //$NON-NLS-1$
			AccountNameGenerator generator = (AccountNameGenerator) obj;
			SystemEntity de = getSystemEntityDao().findByName(dispatcher);
			needs = generator.needsAccount(ue, de);
		}
		return needs;
	}

	@Override
	protected Collection<String> handleFindAccountNames(String system) throws Exception {
		return getAccountEntityDao().findAcountNames(system);
	}

	@Override
	protected String handleGuessAccountNameForDomain(String userName, String domainName) throws Exception {
		UserDomainEntity du = getUserDomainEntityDao().findByName(domainName);
		// Search if already has a user name for this user domain
		
		UserEntity ue = getUserEntityDao().findByUserName(userName);
		return guessAccountName(null, du, ue);
	}

	public void handleSetAccountTemporaryPassword(Account account, Password password)
			throws Exception {
		setAccountPasswordInternal(account, password, true, true);
	}

	public Password handleGenerateAccountTemporaryPassword(Account account)
			throws  Exception {
		return setAccountPasswordInternal(account, null, true, true);
	}

	public Password handleGenerateAccountPassword(Account account)
			throws  Exception {
		return setAccountPasswordInternal(account, null, false, true);
	}

	@Override
	protected List<Account> handleFindSharedAccountsByUser(String userName) throws Exception {
		List<Account> accounts = new LinkedList<Account>();
		User u = getUserService().findUserByUserName(userName);
		if (u == null)
			return accounts;
		for (Account acc: handleGetUserGrantedAccounts(u, AccountAccessLevelEnum.ACCESS_USER))
		{
			if (!acc.getType().equals(AccountType.USER))
			{
				accounts.add (acc);
			}
		}
		return accounts;
		
	}

	
	@Override
	protected List<AccountHistory> handleFindSharedAccountsHistoryByUser(String userName) throws Exception {
		Set<Long> ids = new HashSet<Long>();
		List<AccountHistory> accounts = new LinkedList<AccountHistory>();
		User u = getUserService().findUserByUserName(userName);
		if (u == null)
			return accounts;
		if (!AutoritzacionsUsuari.hasQueryAccount())
		{
			User caller = getUserService().getCurrentUser();
			if (caller != null && caller.getId() != u.getId())
				throw new SecurityException(Messages.getString("AccountServiceImpl.PermissionDenied")); //$NON-NLS-1$
		}
		
		// Role granted accounts
		Collection<RoleAccount> grants = getApplicationService().findUserRolesHistoryByUserName(userName);
		for (RoleAccount rg : grants) {
            RoleEntity r = getRoleEntityDao().findByNameAndSystem(rg.getRoleName(), rg.getSystem());
            for (AccountAccessEntity aae : r.getAccountAccess()) {
                if (isGreaterOrIqualThan(aae.getLevel(), AccountAccessLevelEnum.ACCESS_USER) &&
                		!ids.contains(aae.getAccount().getId())) 
                {
                	AccountHistory h = new AccountHistory();
                	h.setStart( h.getStart() == null || 
                			aae.getStart() != null && rg.getStartDate().after(aae.getStart()) ? 
                					aae.getStart():
                					rg.getStartDate());
                	h.setEnd( h.getEnd() == null || 
                			aae.getEnd() != null && rg.getEndDate().before(aae.getEnd()) ? 
                					aae.getEnd():
                					rg.getEndDate());
                	h.setAccount( getAccountEntityDao().toAccount(aae.getAccount()));
                	h.setLevel(aae.getLevel());
                	accounts.add(h);
                	ids.add(aae.getAccount().getId());
                }
            }
        }

		// User granted accounts
		UserEntity ue = getUserEntityDao().load(u.getId());
        for (AccountAccessEntity aae : ue.getAccountAccess()) {
            if (isGreaterOrIqualThan(aae.getLevel(), AccountAccessLevelEnum.ACCESS_USER) &&
            		!ids.contains(aae.getAccount().getId())) 
            {
            	AccountHistory h = new AccountHistory();
            	h.setStart( aae.getStart());
            	h.setEnd( aae.getEnd());
            	h.setAccount( getAccountEntityDao().toAccount(aae.getAccount()));
            	h.setLevel(aae.getLevel());
            	accounts.add(h);
            	ids.add(aae.getAccount().getId());
            }
        }
		
		// Group granted accounts
		Collection<UserGroupEntity> groups = ue.getSecondaryGroups();
		for (UserGroupEntity rg : groups) {
            for (AccountAccessEntity aae : rg.getGroup().getAccountAccess()) {
                if (isGreaterOrIqualThan(aae.getLevel(), AccountAccessLevelEnum.ACCESS_USER) &&
                		!ids.contains(aae.getAccount().getId())) 
                {
                	AccountHistory h = new AccountHistory();
                	h.setStart( h.getStart() == null || 
                			aae.getStart() != null && rg.getStart().after(aae.getStart()) ? 
                					aae.getStart():
                					rg.getStart());
                	h.setEnd( h.getEnd() == null || 
                			aae.getEnd() != null && rg.getEnd().before(aae.getEnd()) ? 
                					aae.getEnd():
                					rg.getEnd());
                	h.setAccount( getAccountEntityDao().toAccount(aae.getAccount()));
                	h.setLevel(aae.getLevel());
                	accounts.add(h);
                	ids.add(aae.getAccount().getId());
                }
            }
        }
		return accounts;
	}

	@Override
	protected void handleSynchronizeAccount(String accountName, String system) throws Exception {
		AccountEntity acc = getAccountEntityDao().findByNameAndSystem(accountName, system);
		if (acc != null && ! acc.getType().equals(AccountType.IGNORED))
		{
			TaskEntity tasque = getTaskEntityDao().newTaskEntity();
			tasque.setTransaction(TaskHandler.UPDATE_ACCOUNT);
			tasque.setUser(acc.getName());
			tasque.setSystemName(acc.getSystem().getName());
			tasque.setDb(acc.getSystem().getName());
			getTaskEntityDao().createForce(tasque);
		}
	}

	@Override
	protected PasswordValidation handleCheckPasswordSynchronizationStatus(Account account) throws Exception {
		Exception lastException = null;
		for (ServerEntity se : getServerEntityDao().loadAll()) {
            if (se.getType().equals(ServerType.MASTERSERVER)) {
            	if (se.getInstances().isEmpty()) {
            		try {
            			PasswordValidation status = getAccountSynchronizationStatus(account, se.getUrl(), se.getAuth());
            			if (status != null) return status;
            		} catch (Exception e) {
            			lastException = e;
            		}
            	} else {
            		for (ServerInstanceEntity si: se.getInstances()) {
                		try {
                			PasswordValidation status = getAccountSynchronizationStatus(account, si.getUrl(), si.getAuth());
                			if (status != null) return status;
                		} catch (Exception e) {
                			lastException = e;
                		}
            			
            		}
            	}
            }
        }
		if (lastException != null)
			throw lastException;
		return null;
	}

	public PasswordValidation getAccountSynchronizationStatus(Account account, String url, String auth) throws IOException, InternalErrorException {
		RemoteServiceLocator rsl = new com.soffid.iam.remote.RemoteServiceLocator(url);
		rsl.setAuthToken(auth);
		rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount()); //$NON-NLS-1$
		SyncStatusService sss = rsl.getSyncStatusService();
		PasswordValidation status = sss.checkPasswordSynchronizationStatus(account.getName(), account.getSystem());
		if (status != null) {
			AccountEntity entity = getAccountEntityDao().load(account.getId());
			getAccountEntityDao().removeFromCache( entity );
		}
		return status;
	}

	@Override
	protected boolean handleIsAccountPasswordAvailable(long accountId) throws Exception {
		AccountEntity entity = getAccountEntityDao().load(accountId);
		if (entity != null && entity.getSecrets() != null && ! entity.getSecrets().trim().isEmpty())
			return true;
		else
			return false;
	}

	@Override
	protected AsyncList<Account> handleFindAccountByTextAndJsonQueryAsync(final String text, final String jsonQuery)
			throws Exception {
		final AsyncList<Account> result = new AsyncList<Account>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindAccountByTextAndJsonQuery(text, jsonQuery, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	private PagedResult<Account> doFindAccountByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize,
			List<Account> result) throws UnsupportedEncodingException, ClassNotFoundException, InternalErrorException, 
				EvalException, JSONException, ParseException, TokenMgrError {
		AdditionalDataJSONConfiguration.registerVirtualAttributes();
		final AccountEntityDao dao = getAccountEntityDao();
		final AuthorizationService authorizationService = getAuthorizationService();
		ScimHelper h = new ScimHelper(Account.class);
		h.setPrimaryAttributes(new String[] { "name", "description", "loginName", "system"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("system.tenant.id"); //$NON-NLS-1$
		h.setGenerator((entity) -> {
			Account u = dao.toAccount((AccountEntity) entity);
			try {
				if (authorizationService.hasPermission(
						Security.AUTO_ACCOUNT_QUERY, (AccountEntity) entity)) {
					return u;
				} else {
					return null;
				}
			} catch (InternalErrorException e) {
				return null;
			}
		}); 
		h.search(text, jsonQuery, (Collection) result); 
		PagedResult<Account> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}

	@Override
	protected PagedResult<Account> handleFindAccountByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize) throws Exception {
		final LinkedList<Account> result = new LinkedList<Account>();
		return doFindAccountByTextAndJsonQuery(text, jsonQuery, start, pageSize, result);
	}

	@Override
	protected Collection<SyncAgentTaskLog> handleGetActiveTasks(Account account) throws Exception {
		List<SyncAgentTaskLog> r = new LinkedList<>();
		int status = 0;
		AccountEntity accEntity = getAccountEntityDao().load(account.getId());
		if (accEntity == null)
			return r;

		if (accEntity.getSystem().getUrl() == null ||
			account.getType() == AccountType.IGNORED ||
			Boolean.TRUE.equals(accEntity.getPasswordPolicy().getUnmanaged())) 
			return r;
		
		List<TaskEntity> coll = getTaskEntityDao().findByAccount(accEntity.getName(), accEntity.getSystem().getName());
		for (TaskEntity task : coll) {
            if (task.getTransaction().equals(TaskHandler.UPDATE_ACCOUNT) || task.getTransaction().equals(TaskHandler.UPDATE_ACCOUNT_PASSWORD) || task.getTransaction().equals(TaskHandler.PROPAGATE_ACCOUNT_PASSWORD)) {
				SyncAgentTaskLog tl;
				tl = new SyncAgentTaskLog();
				tl.setTaskId(task.getId());
				tl.setAgentCode(accEntity.getSystem().getName());
				tl.setComplete( "X".equals(task.getStatus()) ? "ON HOLD": "PENDING"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				tl.setCreationDate(Calendar.getInstance());
				tl.getCreationDate().setTime(task.getDate());
				tl.setExecutionsNumber(0L);
				tl.setPriority(task.getPriority());
				tl.setTaskDescription(task.toString());
				for ( TaskLogEntity tle: task.getLogs()) {
					if (tle.getLastExecution() != null) {
						tl.setLastExecution(tle.getLastExecution());
						tl.setLastExecutionDate(Calendar.getInstance());
						tl.getLastExecutionDate().setTime(new Date(tle.getLastExecution()));
					}
					if (tle.getNextExecution() != null) {
						tl.setNextExecution(tle.getNextExecution());
						tl.setNextExecutionDate(Calendar.getInstance());
						tl.getNextExecutionDate().setTime(new Date(tle.getNextExecution()));	        								
					}
					if ("S".equals(tle.getCompleted())) { //$NON-NLS-1$
						tl.setComplete("DONE"); //$NON-NLS-1$
					} else if (tle.getMessage() != null) {
						tl.setComplete("ERROR"); //$NON-NLS-1$
						tl.setMessage(tle.getMessage());
					}
					tl.setExecutionsNumber(tle.getExecutionsNumber());
                } 
				r.add(tl);
            }
        }

		
		if (accEntity.getType().equals(AccountType.USER))
		{
			for (UserAccountEntity ua : accEntity.getUsers()) {
                coll = getTaskEntityDao().findByUser(ua.getUser().getUserName());
                for (TaskEntity task : coll) {
                    if (task.getTransaction().equals(TaskHandler.UPDATE_USER) || 
                    		accEntity.getSystem().getPasswordDomain().getName().equals(task.getPasswordsDomain()) && 
                    		(task.getTransaction().equals(TaskHandler.UPDATE_USER_PASSWORD) || task.getTransaction().equals(TaskHandler.PROPAGATE_PASSWORD))) {
                    	if ("X".equals( task.getStatus())) //$NON-NLS-1$
                    	{
                    		SyncAgentTaskLog tl;
                    		tl = new SyncAgentTaskLog();
                    		tl.setTaskId(task.getId());
                    		tl.setComplete( "ON HOLD"); //$NON-NLS-1$
                    		tl.setCreationDate(Calendar.getInstance());
                    		tl.getCreationDate().setTime(task.getDate());
                    		tl.setExecutionsNumber(0L);
                    		tl.setPriority(task.getPriority());
                    		tl.setTaskDescription(task.toString());
                    		r.add(tl);
                    	} else {
            				SyncAgentTaskLog tl;
            				tl = new SyncAgentTaskLog();
            				tl.setTaskId(task.getId());
            				tl.setComplete( "PENDING"); //$NON-NLS-1$
            				tl.setAgentCode(accEntity.getSystem().getName());
            				tl.setCreationDate(Calendar.getInstance());
            				tl.getCreationDate().setTime(task.getDate());
            				tl.setExecutionsNumber(0L);
            				tl.setPriority(task.getPriority());
            				tl.setTaskDescription(task.toString());
            				r.add(tl);
            				boolean add = true;
            				for (TaskLogEntity tle : task.getLogs()) {
            					if (tle.getSystem() == accEntity.getSystem()) {
	        						if ("S".equals(tle.getCompleted())) add = false; //$NON-NLS-1$
	        						else {
	        							if (tle.getLastExecution() != null) {
	        								tl.setLastExecution(tle.getLastExecution());
	        								tl.setLastExecutionDate(Calendar.getInstance());
	        								tl.getLastExecutionDate().setTime(new Date(tle.getLastExecution()));
	        							}
	        							if (tle.getNextExecution() != null) {
	        								tl.setNextExecution(tle.getNextExecution());
	        								tl.setNextExecutionDate(Calendar.getInstance());
	        								tl.getNextExecutionDate().setTime(new Date(tle.getNextExecution()));	        								
	        							}
	        							if ("S".equals(tle.getCompleted())) { //$NON-NLS-1$
	        								tl.setComplete("DONE"); //$NON-NLS-1$
	        							} else if (tle.getMessage() != null) {
	        								tl.setComplete("ERROR"); //$NON-NLS-1$
	        								tl.setMessage(tle.getMessage());
	        							}
	        							tl.setExecutionsNumber(tle.getExecutionsNumber());
	        						}
            					}
            				}
            				if ( add )
            					r.add(tl);
                    	}
                    }
                }
			}
        }
        return r;
	}

	@Override
	protected void handleGrantAcccountToUser(Account account, String user, Long processId, Date until)
			throws Exception {
		AccountEntity accountEntity = getAccountEntityDao().load(account.getId());
		if (accountEntity == null)
			return;
		for (UserAccountEntity uac: new LinkedList<>(accountEntity.getUsers())) {
			if (uac.getWorkflowId().equals(processId) &&
					uac.getUser().getUserName().equals(user) &&
					uac.getApproved() == null) {
				uac.setApproved(true);
				uac.setUntilDate(until);
				getUserAccountEntityDao().update(uac);
			}
		}
	}

	@Override
	protected void handleRegisterAccountReservationProcess(Account account, String user, Long processId)
			throws Exception {
		AccountEntity accountEntity = getAccountEntityDao().load(account.getId());
		if (accountEntity == null)
			return;
		UserEntity userEntity = getUserEntityDao().findByUserName(user);
		if (userEntity == null)
			return;
		UserAccountEntity uac = getUserAccountEntityDao().newUserAccountEntity();
		uac.setUser(userEntity);
		uac.setApproved(null);
		uac.setAccount(accountEntity);
		uac.setWorkflowId(processId);
		getUserAccountEntityDao().create(uac);
	}

	@Override
	protected Collection<HostService> handleFindAccountServices(Account account) throws Exception {
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		if (entity == null)
			return null;
		return getHostServiceEntityDao().toHostServiceList(entity.getServices());
	}

	@Override
	protected Account handleRemoveAccountSnapshot(Account account) throws Exception {
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		if (ae.getSnapshot() != null) {
			AccountSnapshotEntity s = ae.getSnapshot();
			ae.setSnapshot(null);
			getAccountEntityDao().update(ae);
			getAccountSnapshotEntityDao().remove(s);
		}
		AccountAttributeEntity l = getAccountAttributeEntityDao().findByName(ae.getSystem().getName(), ae.getName(), AccountEntityDaoImpl.PREVIOUS_STATUS_ATTRIBUTE);
		if (l != null)
			getAccountAttributeEntityDao().remove(l);
		account.setHasSnapshot(false);
		return account;
	}

	@Override
	protected Account handleGenerateAccountSshPrivateKey(Account account) throws Exception {
		SshKeyGenerator gen = new SshKeyGenerator();
		gen.generateKey();
		return handleSetAccountSshPrivateKey(account, gen.getPrivateKeyString());
	}

	@Override
	protected Account handleSetAccountSshPrivateKey(Account account, String privateKey) throws Exception {
		Exception lastException = null;
		boolean ok = false;
		for (ServerEntity se : getServerEntityDao().loadAll()) {
            if (se.getType().equals(ServerType.MASTERSERVER)) {
				if (se.getInstances().isEmpty()) {
	                try {
	                    setAccountSshPrivateKey(account, privateKey, se.getUrl(), se.getAuth());
	                    ok = true;
	                    break;
	                } catch (Exception e) {
	                    lastException = e;
	                }
            	} else {
            		for (ServerInstanceEntity si: se.getInstances()) {
            			try {
    	                    setAccountSshPrivateKey(account, privateKey, si.getUrl(), si.getAuth());
    	                    ok = true;
    	                    break;
		                } catch (Exception e) {
		                    lastException = e;
		                }
            		}
            	}
            }
        }
		if (lastException != null && !ok)
			throw lastException;
		
		if (account.getCredentialType() == null || 
				account.getCredentialType() == CredentialTypeEnum.CT_SSHKEY ||
				account.getCredentialType() == CredentialTypeEnum.CT_PASSWORD) {
			SshKeyGenerator g = new SshKeyGenerator();
			g.loadKey(privateKey);
			account.setSshPublicKey(g.getPublicKeyString(account.getLoginName()+"@"+account.getSystem()));
		}

		return account;
	}

	private void setAccountSshPrivateKey(Account account, String privateKey, String url, String auth) throws InternalErrorException, IOException {
        RemoteServiceLocator rsl = new RemoteServiceLocator(url);
        rsl.setAuthToken(auth);
		rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount()); //$NON-NLS-1$
		SyncStatusService sss = rsl.getSyncStatusService();
		sss.setAccountSshPrivateKey(account.getName(), account.getSystem(), new Password(privateKey));
	    Audit audit = new Audit();
	    audit.setAction("S"); //$NON-NLS-1$
	    audit.setObject("SC_ACCOUN"); //$NON-NLS-1$
	    audit.setAuthor(Security.getCurrentUser());
	    audit.setCalendar(Calendar.getInstance());
	    audit.setAccount(account.getName());
	    audit.setDatabase(account.getSystem());
	    getAuditService().create(audit);
	}

	@Override
	protected Password handleQueryAccountSshKey (Account account) throws Exception
	{
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		// Check if policy allows user change
		UserDomainService dominiUsuariService = getUserDomainService();
		PasswordPolicy politica = dominiUsuariService.findPolicyByTypeAndPasswordDomain(ae.getPasswordPolicy().getName(), ae.getSystem().getPasswordDomain().getName());
		if (politica == null)
			throw new BadPasswordException(Messages.getString("AccountServiceImpl.NoPolicyDefined")); //$NON-NLS-1$
		if (!politica.isAllowPasswordQuery())
			throw new BadPasswordException(Messages.getString("AccountServiceImpl.NotAllowedToQueryPassword")); //$NON-NLS-1$
		

		AccountAccessLevelEnum level = ae.getType() == AccountType.PRIVILEGED ? 
				AccountAccessLevelEnum.ACCESS_OWNER :
				AccountAccessLevelEnum.ACCESS_MANAGER;
		return handleQueryAccountSshKeyBypassPolicy(account.getId(), level);
	}

	@Override
	protected Password handleQueryAccountSshKeyBypassPolicy(long accountId, AccountAccessLevelEnum level)
			throws InternalErrorException, Exception {
		User usuari = getUserService().getCurrentUser();
		
		AccountEntity acc = getAccountEntityDao().load(accountId);
		if (acc == null)
			return null;
		
		ServerEntityDao dao = getServerEntityDao();
		Exception lastException = null;
		for (ServerEntity se : dao.loadAll()) {
            if (se.getType().equals(ServerType.MASTERSERVER)) {
            	if (se.getInstances().isEmpty()) {
	                try {
	                    Password p = getSshKey(level, usuari, acc, se.getUrl(), se.getAuth());
	                    if (p != null)
	                    	return p;
	                } catch (Exception e) {
	                    lastException = e;
	                }
            	} else {
            		for (ServerInstanceEntity si: se.getInstances()) {
            			try {
		                    Password p = getSshKey(level, usuari, acc, si.getUrl(), si.getAuth());
		                    if (p != null)
		                    	return p;
  	                } catch (Exception e) {
		                    lastException = e;
		                }
            		}
            	}
            }
        }
		if (lastException != null)
			throw lastException;
		return null;
	}

	public Password getSshKey(AccountAccessLevelEnum level, User usuari, AccountEntity acc, 
			String url, String auth) throws IOException, InternalErrorException {
        RemoteServiceLocator rsl = new RemoteServiceLocator(url);
        rsl.setAuthToken(auth);
		rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount()); //$NON-NLS-1$
		SyncStatusService sss = rsl.getSyncStatusService();
		Password p = sss.getAccountSshKey(usuari.getUserName(), acc.getId(), level);
		if (p != null) {
		    Audit audit = new Audit();
		    audit.setAction("H"); //$NON-NLS-1$
		    audit.setObject("SSO"); //$NON-NLS-1$
		    audit.setAuthor(Security.getCurrentUser());
		    audit.setCalendar(Calendar.getInstance());
		    audit.setAccount(acc.getName());
		    audit.setDatabase(acc.getSystem().getName());
		    audit.setApplication("-"); //$NON-NLS-1$
		    getAuditService().create(audit);
		}
		return p;
	}

	@Override
	protected boolean handleHasAccountSshKey(Account account) throws Exception {
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		if (entity.getSecrets() == null)
			return false;
		String id = "ssh."; //$NON-NLS-1$
		for (String secret: entity.getSecrets().split(",")) //$NON-NLS-1$
		{
			if (secret.startsWith(id))
				return true;
		}
		return false;
	}

	@Override
	protected void handleSendAccountPassword(Account account) throws Exception {
		AccountEntity ae = getAccountEntityDao().load(account.getId());
		
		Password result = null;
		
		checkCanSetPassword(account, ae);
		
		if ( ! ae.isDisabled() && ae.getSystem().getUrl() != null)
		{
			for (ServerEntity se : getServerEntityDao().loadAll()) {
	            if (se.getType().equals(ServerType.MASTERSERVER)) {
	            	if (se.getInstances().isEmpty()) {
	            		if (resendAccountPasswordNow(ae, se.getUrl(), se.getAuth()))
	            			return;
	            	} else {
	            		for (ServerInstanceEntity si: se.getInstances()) {
		            		if (resendAccountPasswordNow(ae, si.getUrl(), si.getAuth()))
		            			return;
	            			
	            		}
	            	}
	            }
	        }
		}
	}

	private boolean resendAccountPasswordNow(AccountEntity account, String url, String auth)
			throws InternalErrorException {
		SyncStatusService sss = null;
		try {
		    RemoteServiceLocator rsl = new com.soffid.iam.remote.RemoteServiceLocator(url);
		    rsl.setAuthToken(auth);
			rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount()); //$NON-NLS-1$
		    sss = rsl.getSyncStatusService();
		} catch (Exception e) {
			log.warn("Error sending password", e); //$NON-NLS-1$
		}
		if (sss != null)
		{
			sss.resendAccountPassword(account.getId());
			return true;
		} else
			return false;
	}

	private boolean resendUserPasswordNow(String user, String passwordDomain, String url, String auth) throws InternalErrorException {
		SyncStatusService sss = null;
		try {
		    RemoteServiceLocator rsl = new com.soffid.iam.remote.RemoteServiceLocator(url);
		    rsl.setAuthToken(auth);
			rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount()); //$NON-NLS-1$
		    sss = rsl.getSyncStatusService();
		    if (sss!=null) {
				sss.resendUserPassword(user, passwordDomain);
				return true;
			}
		} catch (Exception e) {
			log.warn("Error trying to resend password domain, e=", e); //$NON-NLS-1$
		}
		return false;
	}

	private void checkCanSetPassword(Account account, AccountEntity ae)
			throws InternalErrorException, BadPasswordException {
		String principal = Security.getCurrentAccount();
		com.soffid.iam.service.InternalPasswordService ips = getInternalPasswordService();
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
			if (! caller.getId().equals( ae.getId()))
			{
				UserEntity callerUe = getUserForAccount(caller);
				if (ae.getType().equals(AccountType.USER))
				{
					UserEntity ue2 = getUserForAccount(ae);
					if (ue2 != null)
					{
						if (callerUe == null)
							throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NoChangePasswordAuthorized"))); //$NON-NLS-1$
						
						if (!callerUe.getId().equals(ue2.getId()) && !getAuthorizationService().hasPermission(Security.AUTO_USER_SET_PASSWORD, ue2))
							throw new SecurityException(String.format(Messages.getString("AccountServiceImpl.NoChangePasswordAuthorized"))); //$NON-NLS-1$
					}
					else
						throw new InternalErrorException(Messages.getString("AccountServiceImpl.AccounNotBounForUser")); //$NON-NLS-1$
				}
				else if (ae.getType().equals(AccountType.IGNORED) && 
						! ae.getSystem().getName().equals( ConfigurationCache.getProperty("AutoSSOSystem")) ) //$NON-NLS-1$
				{
					throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.NoAuthorizedChangePassAccDisabled"))); //$NON-NLS-1$
				}
				else if (ae.getType().equals(AccountType.SHARED))
				{
					if (callerUe == null)
						throw new InternalErrorException(String.format(Messages.getString("AccountServiceImpl.NoChangePasswordAuthorized"))); //$NON-NLS-1$
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
			UserDomainService dominiUsuariService = getUserDomainService();
			PasswordPolicy politica = dominiUsuariService.findPolicyByTypeAndPasswordDomain(ae.getPasswordPolicy().getName(), ae.getSystem().getPasswordDomain().getName());
			if (politica == null)
				throw new BadPasswordException(Messages.getString("AccountServiceImpl.NoPolicyDefined")); //$NON-NLS-1$
			if (!politica.isAllowPasswordChange())
				throw new BadPasswordException(Messages.getString("AccountServiceImpl.NotAllowedToChangePassword")); //$NON-NLS-1$
		}
	}

	@Override
	protected AsyncProcessTracker handleDisableAccounts(String scimQuery, List<DisableObjectRule> rules) throws Exception {
		AsyncProcessTracker t = new AsyncProcessTracker();
		t.setStart(new Date());
		t.setProgress((float)0.0);
		SoffidPrincipal principal = Security.getSoffidPrincipal();
		new Thread( () -> {
			Security.nestedLogin(principal);
			try {
				getAsyncRunnerService().runNewTransaction(() -> {
					HashSet<Long> processedUsers = new HashSet<>();
					int steps = 0;
					for (DisableObjectRule rule: rules) {
						if (rule.getCriteria() != null && rule.getAction() != null)
							applyRule (t, scimQuery, rule, processedUsers, null);
						steps ++ ;
						t.setProgress((float)steps / rules.size());
					}
					return null;
				});
			} catch (InternalErrorException e) {
				log.warn("Error processing task", e); //$NON-NLS-1$
				t.setErrorMessage(SoffidStackTrace.generateShortDescription(e));
			} finally {
				Security.nestedLogoff();				
				t.setEnd(new Date());
				t.setFinished(true);
			}
		}).start();
		return t;
	}

	@Override
	protected AsyncProcessTracker handleDisableAccountsPreview(String scimQuery, List<DisableObjectRule> rules, List<Object[]> actions) throws Exception {
		AsyncProcessTracker t = new AsyncProcessTracker();
		t.setStart(new Date());
		t.setProgress((float)0.0);
		getAsyncRunnerService().run(() -> {
			try {
				HashSet<Long> processedUsers = new HashSet<>();
				int steps = 0;
				for (DisableObjectRule rule: rules) {
					try {
						if (rule.getCriteria() != null)
							applyRule (t, scimQuery, rule, processedUsers, actions);
						steps ++ ;
						t.setProgress((float)steps / rules.size());
					} catch (Exception e) {
						log.warn("Error processing task", e); //$NON-NLS-1$
						t.setErrorMessage(SoffidStackTrace.generateShortDescription(e));
					}
				}
			} finally {
				t.setEnd(new Date());
				t.setFinished(true);
			}
		}, new AsyncList<>());
		return t;
	}

	private void applyRule(AsyncProcessTracker t, String scimQuery, DisableObjectRule rule, HashSet<Long> processedUsers, List<Object[]> actions) throws UnsupportedEncodingException, ClassNotFoundException, JSONException, InternalErrorException, EvalException, ParseException, TokenMgrError {
		String query;
		ScimHelper h = new ScimHelper(Account.class);
		h.setTenantFilter("system.tenant.id"); //$NON-NLS-1$
		if ("P".equals(rule.getCriteria())) { //$NON-NLS-1$
			HashMap m = new HashMap<>();
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, - rule.getParameter());
			m.put("limit", c.getTime()); //$NON-NLS-1$
			h.setExtraWhere("o.passwordExpiration < :limit"); //$NON-NLS-1$
			h.setExtraParameters(m);
		}
		else if ("L".equals(rule.getCriteria())) { //$NON-NLS-1$
			HashMap m = new HashMap<>();
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, - rule.getParameter());
			m.put("limit", c.getTime()); //$NON-NLS-1$
			h.setExtraWhere("o.lastLogin < :limit"); //$NON-NLS-1$
			h.setExtraParameters(m);
		} else {
			return;
		}
		
		
		h.setOrder("o.id"); //$NON-NLS-1$
		h.setPageSize(100);
		AsyncList<Object> list = new AsyncList<>();
		h.setGenerator((data) -> {
			if (t.isCancelled())
				list.cancel();
			AccountEntity account = (AccountEntity) data;
			if (! processedUsers.contains(account.getId())) {
				processedUsers.add(account.getId());
				if (actions != null) {
					synchronized(actions) {
						actions.add(new Object[] {account.getName(), account.getSystem().getName(), account.getDescription(), rule.getAction()});
					}
				} else {
					try {
						processAction (account, rule);
					} catch (Exception e) {
						throw new SeyconException("Error processing account "+account.getName()+" @ "+account.getSystem().getName(), e); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
			}
			return null;
		});
		h.search(null, scimQuery, list);
	}

	private void processAction(AccountEntity user, DisableObjectRule rule) throws Exception {
		Account userObject = getAccountEntityDao().toAccount(user);
		if (rule.getAction().equals("E")) { //$NON-NLS-1$
			List<String> actors = new LinkedList<>();
			
			try {
				if (rule.getEmailCopy() != null && ! rule.getEmailCopy().trim().isEmpty()) {
					String actorsString = replace(rule.getEmailCopy(), userObject);
					if (actorsString.startsWith("[") && actorsString.endsWith("]")) //$NON-NLS-1$ //$NON-NLS-2$
						actorsString = actorsString.substring(1, actorsString.length()-1);
					for (String actor: actorsString.split("[ ,]+")) { //$NON-NLS-1$
						actors.add(actor);
					}
				}
				else {
					actors.addAll(userObject.getOwnerUsers());
					actors.addAll(userObject.getOwnerRoles());
					actors.addAll(userObject.getOwnerGroups());
				}
				
				if (rule.getEmailBody() != null && !rule.getEmailBody().trim().isEmpty() &&
						rule.getEmailSubject() != null && !rule.getEmailSubject().trim().isEmpty())
					getMailService().sendHtmlMailToActors(actors.toArray(new String[actors.size()]),
						replace(rule.getEmailSubject(), userObject),
						replace(rule.getEmailBody(), userObject));
			} catch (InternalErrorException e) {
				log.warn("Error sending notification email to "+actors, e); //$NON-NLS-1$
			}
		}
		if (rule.getAction().equals("D")) { //$NON-NLS-1$
			if (userObject.getStatus() != AccountStatus.FORCED_DISABLED ) {
				userObject.setStatus(AccountStatus.FORCED_DISABLED);
				handleUpdateAccount2(userObject);
			}
		}
		if (rule.getAction().equals("R")) { //$NON-NLS-1$
			if (userObject.getStatus() != AccountStatus.REMOVED ) {
				userObject.setStatus(AccountStatus.REMOVED);
				handleUpdateAccount2(userObject);
			}
		}
	}

	private String replace(String text, Account user) {
		
		text = text.replace("#{", "${"); //$NON-NLS-1$ //$NON-NLS-2$
		
		VariableResolver pResolver = new ObjectVariableResolver (user);
		ExpressionEvaluatorImpl ee = new ExpressionEvaluatorImpl();
		FunctionMapper functions  = null;
		return (String) ee.evaluate(text, String.class, pResolver , functions);
		
	}
	

}

