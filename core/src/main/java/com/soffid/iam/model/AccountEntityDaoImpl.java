package com.soffid.iam.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.jcs.access.behavior.ICacheAccess;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.api.AccessControlList;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.PasswordValidation;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.spring.JCSCacheProvider;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;

public class AccountEntityDaoImpl extends
		com.soffid.iam.model.AccountEntityDaoBase {
	public static final String PREVIOUS_STATUS_ATTRIBUTE = "$soffid$previous-status";
	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(getClass());
	 
	private void auditar(String accio, String account, String dispatcher) {

		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setAccount(account);
		auditoria.setDatabase(dispatcher);
		auditoria.setAuthor(codiUsuari);
		auditoria.setObject("SC_ACCOUN"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(
				auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	@Override
	public void create(com.soffid.iam.model.AccountEntity entity) {
		super.create(entity);
		auditar("C", entity.getName(), entity.getSystem().getName()); //$NON-NLS-1$
		getCache().remove(entity.getId());
	}

	@Override
	public void update(com.soffid.iam.model.AccountEntity entity) {
		try {
			handleUpdate(entity, "U");
			getCache().remove(entity.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remove(com.soffid.iam.model.AccountEntity entity) {
		getCache().remove(entity.getId());
		getAccountAccessEntityDao().remove(
				new LinkedList<com.soffid.iam.model.AccountAccessEntity>(entity
						.getAcl()));
		entity.getAcl().clear();
		getUserAccountEntityDao().remove(
				new LinkedList<com.soffid.iam.model.UserAccountEntity>(entity
						.getUsers()));
		entity.getUsers().clear();
		getAccountPasswordEntityDao().remove(
				new LinkedList<com.soffid.iam.model.AccountPasswordEntity>(
						entity.getPasswords()));
		entity.getPasswords().clear();
		getRoleAccountEntityDao().remove(
				new LinkedList<RoleAccountEntity>(entity.getRoles()));
		entity.getRoles().clear();
        getNetworkDiscoveryAccountEntityDao().remove(entity.getNetworkDiscovery());
        entity.getNetworkDiscovery().clear();
        AccountSnapshotEntity snapshot = entity.getSnapshot();
        
		try {
			getAuditEntityDao().unlinkAccounts(entity);
		} catch (InternalErrorException e) {
			throw new RuntimeException(e);
		}
		super.remove(entity);
		auditar("D", entity.getName(), entity.getSystem().getName()); //$NON-NLS-1$
		if (snapshot != null) {
			getAccountSnapshotEntityDao().remove(snapshot);
		}
	}

	public com.soffid.iam.model.AccountEntity accountToEntity(Account instance) {
		com.soffid.iam.model.AccountEntity entity = load(instance.getId());
		if (entity == null)
			entity = newAccountEntity();
		super.accountToEntity(instance, entity, true);
		return entity;
	}

	@Override
	public void toAccount(com.soffid.iam.model.AccountEntity source,
			Account target) {
		try {
			Log log = LogFactory.getLog(getClass());
			AccountCacheEntry entry = (AccountCacheEntry) getCache().get(source.getId());
			if ( entry != null)
			{
				fetchFromCache(target, entry);
				return ;
			}
			log.info("serialize 0");
			super.toAccount(source, target);
			log.info("serialize 1");
			// Incompatible types source.dispatcher and target.dispatcher
			// Missing attribute grantedGroups on entity
			// Missing attribute grantedUsers on entity
			// Missing attribute grantedRoles on entity
			target.setSystem(source.getSystem().getName());
			target.setPasswordStatus(source.getPasswordStatus() == null ? 
				null:
				PasswordValidation.valueOf(source.getPasswordStatus()));
			target.setHasSnapshot(source.getSnapshot() != null);
			Collection<String> grups = new LinkedList<String>();
			Collection<String> roles = new LinkedList<String>();
			Collection<String> usuaris = new LinkedList<String>();
			Collection<String> managerGrups = new LinkedList<String>();
			Collection<String> managerRoles = new LinkedList<String>();
			Collection<String> managerUsers = new LinkedList<String>();
			Collection<String> ownerGrups = new LinkedList<String>();
			Collection<String> ownerRoles = new LinkedList<String>();
			Collection<String> ownerUsers = new LinkedList<String>();
			if (source.getType().equals(AccountType.USER)) {
				for (com.soffid.iam.model.UserAccountEntity uae : source.getUsers()) {
					ownerUsers.add(uae.getUser().getUserName());
				}
			} else {
				for (com.soffid.iam.model.AccountAccessEntity acl : source.getAcl()) {
					if ( ! Boolean.TRUE.equals(acl.getDisabled()))
					{
						if (acl.getGroup() != null
								& acl.getLevel().equals(
										AccountAccessLevelEnum.ACCESS_USER))
							grups.add(acl.getGroup().getName());
						if (acl.getRole() != null
								& acl.getLevel().equals(
										AccountAccessLevelEnum.ACCESS_USER))
							roles.add(acl.getRole().getName()+"@"+acl.getRole().getSystem().getName());
						if (acl.getUser() != null
								& acl.getLevel().equals(
										AccountAccessLevelEnum.ACCESS_USER))
							usuaris.add(acl.getUser().getUserName());
						if (acl.getGroup() != null
								& acl.getLevel().equals(
										AccountAccessLevelEnum.ACCESS_MANAGER))
							managerGrups.add(acl.getGroup().getName());
						if (acl.getRole() != null
								& acl.getLevel().equals(
										AccountAccessLevelEnum.ACCESS_MANAGER))
							managerRoles.add(acl.getRole().getName()+"@"+acl.getRole().getSystem().getName());
						if (acl.getUser() != null
								& acl.getLevel().equals(
										AccountAccessLevelEnum.ACCESS_MANAGER))
							managerUsers.add(acl.getUser().getUserName());
						if (acl.getGroup() != null
								& acl.getLevel().equals(
										AccountAccessLevelEnum.ACCESS_OWNER))
							ownerGrups.add(acl.getGroup().getName());
						if (acl.getRole() != null
								& acl.getLevel().equals(
										AccountAccessLevelEnum.ACCESS_OWNER))
							ownerRoles.add(acl.getRole().getName()+"@"+acl.getRole().getSystem().getName());
						if (acl.getUser() != null
								& acl.getLevel().equals(
										AccountAccessLevelEnum.ACCESS_OWNER))
							ownerUsers.add(acl.getUser().getUserName());
					}
				}
			}
			log.info("serialize 2");
			target.setGrantedGroups(grups);
			target.setGrantedRoles(roles);
			target.setGrantedUsers(usuaris);
	
			target.setManagerGroups(managerGrups);
			target.setManagerRoles(managerRoles);
			target.setManagerUsers(managerUsers);
	
			target.setOwnerGroups(ownerGrups);
			target.setOwnerRoles(ownerRoles);
			target.setOwnerUsers(ownerUsers);
	
			target.setPasswordPolicy(source.getPasswordPolicy().getName());
			if (source.getType() == AccountType.USER) {
				for (com.soffid.iam.model.UserAccountEntity uae : source.getUsers()) {
					target.setPasswordPolicy(uae.getUser().getUserType().getName());
				}
			}
			log.info("serialize 3");
			target.setAttributes(new HashMap<String, Object>());
			Map<String, Object> attributes = target.getAttributes();
			for (AccountAttributeEntity att : source.getAttributes()) {
				final Object objectValue = att.getObjectValue();
				if (objectValue != null) {
					if (att.getMetadata().getMultiValued() != null && att.getMetadata().getMultiValued().booleanValue())
					{
						LinkedList<Object> r = (LinkedList<Object>) attributes.get(att.getMetadata().getName());
						if (r == null)
						{
							r = new LinkedList<Object>();
							attributes.put(att.getMetadata().getName(), r);
						}
						if (objectValue != null)
							r.add(objectValue);
					}
					else
					{
						attributes.put(att.getMetadata().getName(),objectValue);
					}
				}
			}
			if (source.getSnapshot() != null && Security.isSyncServer()) {
				attributes.put(PREVIOUS_STATUS_ATTRIBUTE, source.getSnapshot().getData());
			}
			for (Object o: attributes.values())
			{
				if (o != null && o instanceof List) Collections.sort((List) o);
			}

			Object o = attributes.get("SSO:URL");
			if (o != null) target.setLoginUrl(o.toString());
	
			// Assign vault folder
			if (source.getFolder() == null)
			{
				target.setVaultFolder(null);
				target.setVaultFolderId(null);
			}
			else
			{
				target.setVaultFolder(source.getFolder().getName());
				target.setVaultFolderId(source.getFolder().getId());
			}
				
	
			try {
				target.setAccessLevel(getAccessLevel ( source));
			} catch (InternalErrorException e) {
				throw new RuntimeException(e);
			}

			if (target.getStatus() == null)
			{
				target.setStatus( target.isDisabled() ? AccountStatus.DISABLED : AccountStatus.ACTIVE);
			}
			else
			{
				target.setDisabled(target.getStatus() != AccountStatus.ACTIVE && target.getStatus() != AccountStatus.FORCED_ACTIVE);
			}

			if (source.getJumpServerGroup() != null)
				target.setJumpServerGroup(source.getJumpServerGroup().getName());
			
			if (target.getLoginName() == null || target.getLoginName().trim().isEmpty())
				target.setLoginName(target.getName());

			if ("com.soffid.iam.sync.sso.agent.SSOAgent".equals(source.getSystem().getClassName())) {
				if (target.getServerType() == null) 
					target.setServerType( (String) target.getAttributes().get("type") );
				if (target.getServerName() == null) 
					target.setServerName( (String) target.getAttributes().get("SSO:Server") );
				if (target.getLoginUrl() == null) 
					target.setLoginUrl( (String) target.getAttributes().get("SSO:URL") );
			}

			log.info("serialize 4");
			storeCacheEntry(source, target);
	
			if (source.getType() == AccountType.PRIVILEGED) 
				for (UserAccountEntity ua: source.getUsers())
					target.setLockedBy(ua.getUser().getUserName());
		} catch (InternalErrorException e) {
			throw new RuntimeException(e);
		}
	}

	private void storeCacheEntry(AccountEntity source, Account target) throws InternalErrorException {
		AccountCacheEntry entry = new AccountCacheEntry();
		entry.account = new Account(target);
		entry.timeStamp = System.currentTimeMillis();

		entry.aclOwner = new AccessControlList();
		entry.aclManager = new AccessControlList();
		entry.aclUser = new AccessControlList();
		
		for (AccountAccessEntity aa: source.getAcl()) {
			AccessControlList acl = 
					aa.getLevel() == AccountAccessLevelEnum.ACCESS_OWNER ? entry.aclOwner :
						aa.getLevel() == AccountAccessLevelEnum.ACCESS_MANAGER ? entry.aclManager :
							aa.getLevel() == AccountAccessLevelEnum.ACCESS_USER ? entry.aclUser :
								null;
			if (acl != null) {
				if (aa.getGroup() != null) 
					acl.getGroups().add(aa.getGroup().getId());
				if (aa.getRole() != null)
					acl.getRoles().add(aa.getRole().getId());
				if (aa.getUser() != null)
					acl.getUsers().add(aa.getUser().getId());
			}
		}

		entry.account.setAccessLevel(AccountAccessLevelEnum.ACCESS_NONE);
		
		getCache().put(source.getId(), entry);
	}

	private void fetchFromCache(Account target, AccountCacheEntry entry)
			throws InternalErrorException {
		target.setAttributes( new HashMap<String, Object>( entry.account.getAttributes()) );
		target.setCreated(entry.account.getCreated());
		target.setDescription(entry.account.getDescription());
		target.setDisabled(entry.account.isDisabled());
		target.setGrantedGroups(entry.account.getGrantedGroups());
		target.setGrantedRoles(entry.account.getGrantedRoles());
		target.setGrantedUsers(entry.account.getGrantedUsers());
		target.setId(entry.account.getId());
		target.setInheritNewPermissions(entry.account.isInheritNewPermissions());
		target.setLastPasswordSet(entry.account.getLastPasswordSet());
		target.setLastLogin(entry.account.getLastLogin());
		target.setLastUpdated(entry.account.getLastUpdated());
		target.setLastChange(entry.account.getLastChange());
		target.setLoginUrl(entry.account.getLoginUrl());
		target.setManagerGroups(entry.account.getManagerGroups());
		target.setManagerRoles(entry.account.getManagerRoles());
		target.setManagerUsers(entry.account.getManagerUsers());
		target.setName(entry.account.getName());
		target.setOldName(entry.account.getOldName());
		target.setOwnerGroups(entry.account.getOwnerGroups());
		target.setOwnerRoles(entry.account.getOwnerRoles());
		target.setOwnerUsers(entry.account.getOwnerUsers());
		target.setPasswordExpiration(entry.account.getPasswordExpiration());
		target.setPasswordPolicy(entry.account.getPasswordPolicy());
		target.setStatus(entry.account.getStatus());
		target.setSystem(entry.account.getSystem());
		target.setType(entry.account.getType());
		target.setVaultFolder(entry.account.getVaultFolder());
		target.setVaultFolderId(entry.account.getVaultFolderId());
		target.setLoginName(entry.account.getLoginName());
		target.setLoginUrl(entry.account.getLoginUrl());
		target.setServerName(entry.account.getServerName());
		target.setServerType(entry.account.getServerType());
		target.setHasSnapshot(entry.account.isHasSnapshot());
		String currentUser = Security.getCurrentAccount();
		if (getACLService().isCurrentUserIncluded(entry.aclOwner))
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_OWNER);
		else if (getACLService().isCurrentUserIncluded(entry.aclManager))
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_MANAGER);
		else if (getACLService().isCurrentUserIncluded(entry.aclUser))
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_USER);
		else
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_NONE);
		target.setLaunchType(entry.account.getLaunchType());
		target.setJumpServerGroup(entry.account.getJumpServerGroup());
		target.setPasswordExpiration(entry.account.getPasswordExpiration());
		target.setLockedBy(entry.account.getLockedBy());
	}


	private AccountAccessLevelEnum getAccessLevel(AccountEntity source) throws InternalErrorException {
		if (Security.isSyncServer())
			return AccountAccessLevelEnum.ACCESS_OWNER;
		
		String u = Security.getCurrentUser();
		if ( u != null)
		{
			return handleGetAccessLevel(source, u);
		}
		return AccountAccessLevelEnum.ACCESS_NONE;
	}

	protected AccessControlList generateAcl(AccountEntity source,
			AccountAccessLevelEnum al) {
		AccessControlList acl = new AccessControlList();
		for ( AccountAccessEntity entry: source.getAcl())
		{
			if (entry.getLevel().equals (al) && ! Boolean.TRUE.equals(entry.getDisabled()))
			{
				if (entry.getUser() != null)
					acl.getUsers().add(entry.getUser().getId());
				if (entry.getGroup() != null)
					acl.getGroups().add(entry.getGroup().getId());
				if (entry.getRole() != null)
					acl.getRoles().add(entry.getRole().getId());
			}
		}
		return acl;
	}

	@Override
	public void accountToEntity(Account source,
			com.soffid.iam.model.AccountEntity target, boolean copyIfNull) {
		super.accountToEntity(source, target, copyIfNull);
		if (target.getStatus() == null)
		{
			target.setStatus( target.isDisabled() ? AccountStatus.DISABLED : AccountStatus.ACTIVE);
		}
		else
		{
			target.setDisabled( target.getStatus() != AccountStatus.ACTIVE && target.getStatus() != AccountStatus.FORCED_ACTIVE);
		}
		SystemEntity dispatcher = getSystemEntityDao().findByName(
				source.getSystem());
		if (dispatcher == null)
			throw new IllegalArgumentException(String.format(
					Messages.getString("AccountEntityDaoImpl.WrongDispatcher"),
					source.getSystem(), source.getName(), source.getSystem()));
		target.setSystem(dispatcher);
		target.setPasswordStatus(source.getPasswordStatus() == null ? null : source.getPasswordStatus().toString());
		UserTypeEntity tipus = getUserTypeEntityDao().findByName(
				source.getPasswordPolicy());
		if (tipus == null)
			throw new IllegalArgumentException(String.format(
					Messages.getString("AccountEntityDaoImpl.WrongPassword"),
					source.getPasswordPolicy(), source.getName(),
					source.getSystem()));
		target.setPasswordPolicy(tipus);
		if ( source.getJumpServerGroup() == null || source.getJumpServerGroup().trim().isEmpty())
			target.setJumpServerGroup(null);
		else
		{
			JumpServerGroupEntity jsg = getJumpServerGroupEntityDao().findByName(source.getJumpServerGroup());
			if (jsg == null)
				throw new IllegalArgumentException(
						String.format("Jump server group [%s] does not exist", 
								source.getJumpServerGroup()));
			target.setJumpServerGroup(jsg);
		}
		if (target.getLoginName() == null || target.getLoginName().trim().isEmpty() ||
				target.getLoginName().equals(target.getOldName()))
			target.setLoginName(source.getName());
	}

	@SuppressWarnings(value = "unchecked")
	@Override
	public List<com.soffid.iam.model.AccountEntity> findSharedAccounts(
			CriteriaSearchConfiguration criteria, String name, String dispatcher) {
		try {
			StringBuffer where = new StringBuffer();
			where.append("select ac from com.soffid.iam.model.AccountEntity as ac "
					+ "where ac.type != '" + AccountType.USER + "' "
							+ "and ac.system.tenant.id=:tenantId"); //$NON-NLS-1$ //$NON-NLS-2$
			if (name != null && !name.isEmpty())
				where.append(" and ac.name like :name"); //$NON-NLS-1$
			if (dispatcher != null && !dispatcher.isEmpty())
				where.append(" and ac.system.name like :dispatcher"); //$NON-NLS-1$
			org.hibernate.Query queryObject = super.getSession(false)
					.createQuery(where.toString());
			queryObject.setParameter("tenantId", Security.getCurrentTenantId());
			if (name != null && !name.isEmpty())
				queryObject.setParameter("name", name); //$NON-NLS-1$
			if (dispatcher != null && !dispatcher.isEmpty())
				queryObject.setParameter("dispatcher", dispatcher); //$NON-NLS-1$
			if (criteria != null && criteria.getMaximumResultSize() != null) {
				queryObject.setMaxResults(criteria.getMaximumResultSize()
						.intValue());
			}
			@SuppressWarnings(value = "rawtypes")
			java.util.List<com.soffid.iam.model.AccountEntity> results = queryObject
					.list();
			return (java.util.List<com.soffid.iam.model.AccountEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	@Override
	protected void handlePropagateChanges(
			com.soffid.iam.model.AccountEntity account) throws Exception {
		if (account.getType().equals(AccountType.USER)) {
			for (com.soffid.iam.model.UserAccountEntity uac : account
					.getUsers()) {
				getUserAccountEntityDao().propagateChanges(uac);
			}
		} else {
			TaskEntity tasque = getTaskEntityDao().newTaskEntity();
			tasque.setDate(new Timestamp(System.currentTimeMillis()));
			tasque.setTransaction(TaskHandler.UPDATE_ACCOUNT);
			tasque.setDb(account.getSystem().getName());
			tasque.setSystemName(account.getSystem().getName());
			tasque.setUser(account.getName());
			getTaskEntityDao().create(tasque);
		}
	}

	@Override
	protected void handleUpdate(AccountEntity entity, String auditType)
			throws Exception {
		super.update(entity);
		getCache().remove(entity.getId());
		if (auditType != null)
			auditar(auditType, entity.getName(), entity.getSystem().getName()); //$NON-NLS-1$
	}

	private ICacheAccess<Long, AccountCacheEntry> cache;
	private ICacheAccess<Long, AccountCacheEntry> getCache()
	{ 
		if (cache == null)
		{
			cache = JCSCacheProvider.buildCache(AccountCacheEntry.class.getName());
		}
		return cache;
	}

	@Override
	public Collection<AccountEntity> findByText(CriteriaSearchConfiguration criteria, String text) {
		String[] split = ScimHelper.split(text);
		Parameter[] params = new Parameter[split.length + 1];
		
		StringBuffer sb = new StringBuffer("select u "
				+ "from com.soffid.iam.model.AccountEntity as u "
				+ "where u.system.tenant.id = :tenantId");
		params[0] = new Parameter("tenantId", Security.getCurrentTenantId());
		for (int i = 0; i < split.length; i++)
		{
			sb.append(" and ");
			params[i+1] = new Parameter("param"+i, "%"+split[i].toUpperCase()+"%");
			sb.append("(upper(u.name) like :param")
				.append(i)
				.append(" or upper(u.description) like :param")
				.append(i)
				.append(" or upper(u.system.name) like :param")
				.append(i)
				.append(" or upper(u.loginName) like :param")
				.append(i)
				.append(")");
		}
		return query(sb.toString(), params);
	}
	
	public void handleRemoveFromCache (AccountEntity entity) 
	{
		getCache().remove(entity.getId());
	}

	@Override
	protected AccountAccessLevelEnum handleGetAccessLevel(AccountEntity source, String user) throws InternalErrorException {
		UserEntity ue = getUserEntityDao().findByUserName(user);
		if (ue != null)
		{
			if (source.getType().equals(AccountType.USER))
			{
				for (UserAccountEntity uac: source.getUsers())
				{
					if (uac.getUser().getId().equals (ue.getId()))
						return AccountAccessLevelEnum.ACCESS_OWNER;
				}
			}
			for (AccountAccessLevelEnum al: new AccountAccessLevelEnum [] {
					AccountAccessLevelEnum.ACCESS_OWNER,
					AccountAccessLevelEnum.ACCESS_MANAGER,
					AccountAccessLevelEnum.ACCESS_USER})
			{
				AccessControlList acl = generateAcl (source, al);
				if ( getACLService().isUserIncluded(ue.getId(), acl))
					return al;
			}
		}		
		return AccountAccessLevelEnum.ACCESS_NONE;
	}
}


class AccountCacheEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long timeStamp;
	Account account;
	AccessControlList aclOwner;
	AccessControlList aclManager;
	AccessControlList aclUser;
}
