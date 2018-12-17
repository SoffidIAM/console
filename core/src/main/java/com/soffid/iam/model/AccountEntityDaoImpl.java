package com.soffid.iam.model;

import com.soffid.iam.api.AccessControlList;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserData;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserTypeEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.spring.DummyCache;
import com.soffid.iam.spring.JCSCacheProvider;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;

import java.io.Serializable;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.behavior.ICacheAccess;

public class AccountEntityDaoImpl extends
		com.soffid.iam.model.AccountEntityDaoBase {
	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(getClass());
	 
	private void auditar(String accio, String account, String dispatcher) {

		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setAccount(account);
		auditoria.setDatabase(dispatcher);
		auditoria.setAuthor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar
				.getInstance().getTime()));
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
		try {
			getAuditEntityDao().unlinkAccounts(entity);
		} catch (InternalErrorException e) {
			throw new RuntimeException(e);
		}
		super.remove(entity);
		auditar("D", entity.getName(), entity.getSystem().getName()); //$NON-NLS-1$
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
			AccountCacheEntry entry = (AccountCacheEntry) getCache().get(source.getId());
			if ( entry != null)
			{
				fetchFromCache(target, entry);
				return ;
			}
			super.toAccount(source, target);
			// Incompatible types source.dispatcher and target.dispatcher
			// Missing attribute grantedGroups on entity
			// Missing attribute grantedUsers on entity
			// Missing attribute grantedRoles on entity
			target.setSystem(source.getSystem().getName());
			Collection<Group> grups = new LinkedList<Group>();
			Collection<Role> roles = new LinkedList<Role>();
			Collection<User> usuaris = new LinkedList<User>();
			Collection<Group> managerGrups = new LinkedList<Group>();
			Collection<Role> managerRoles = new LinkedList<Role>();
			Collection<User> managerUsers = new LinkedList<User>();
			Collection<Group> ownerGrups = new LinkedList<Group>();
			Collection<Role> ownerRoles = new LinkedList<Role>();
			Collection<User> ownerUsers = new LinkedList<User>();
			if (source.getType().equals(AccountType.USER)) {
				for (com.soffid.iam.model.UserAccountEntity uae : source.getUsers()) {
					ownerUsers.add(getUserEntityDao().toUser(uae.getUser()));
				}
			} else {
				for (com.soffid.iam.model.AccountAccessEntity acl : source.getAcl()) {
					if (acl.getGroup() != null
							& acl.getLevel().equals(
									AccountAccessLevelEnum.ACCESS_USER))
						grups.add(getGroupEntityDao().toGroup(acl.getGroup()));
					if (acl.getRole() != null
							& acl.getLevel().equals(
									AccountAccessLevelEnum.ACCESS_USER))
						roles.add(getRoleEntityDao().toRole(acl.getRole()));
					if (acl.getUser() != null
							& acl.getLevel().equals(
									AccountAccessLevelEnum.ACCESS_USER))
						usuaris.add(getUserEntityDao().toUser(acl.getUser()));
					if (acl.getGroup() != null
							& acl.getLevel().equals(
									AccountAccessLevelEnum.ACCESS_MANAGER))
						managerGrups.add(getGroupEntityDao()
								.toGroup(acl.getGroup()));
					if (acl.getRole() != null
							& acl.getLevel().equals(
									AccountAccessLevelEnum.ACCESS_MANAGER))
						managerRoles.add(getRoleEntityDao().toRole(acl.getRole()));
					if (acl.getUser() != null
							& acl.getLevel().equals(
									AccountAccessLevelEnum.ACCESS_MANAGER))
						managerUsers.add(getUserEntityDao().toUser(acl.getUser()));
					if (acl.getGroup() != null
							& acl.getLevel().equals(
									AccountAccessLevelEnum.ACCESS_OWNER))
						ownerGrups.add(getGroupEntityDao().toGroup(acl.getGroup()));
					if (acl.getRole() != null
							& acl.getLevel().equals(
									AccountAccessLevelEnum.ACCESS_OWNER))
						ownerRoles.add(getRoleEntityDao().toRole(acl.getRole()));
					if (acl.getUser() != null
							& acl.getLevel().equals(
									AccountAccessLevelEnum.ACCESS_OWNER))
						ownerUsers.add(getUserEntityDao().toUser(acl.getUser()));
				}
			}
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
			target.setAttributes(new HashMap<String, Object>());
			Map<String, Object> attributes = target.getAttributes();
			for (AccountAttributeEntity att : source.getAttributes()) {
				UserData vd = getAccountAttributeEntityDao().toUserData(att);
				if (att.getMetadata().getMultiValued() != null && att.getMetadata().getMultiValued().booleanValue())
				{
					LinkedList<Object> r = (LinkedList<Object>) attributes.get(vd.getAttribute());
					if (r == null)
					{
						r = new LinkedList<Object>();
						attributes.put(vd.getAttribute(), r);
					}
					if (vd.getDateValue() != null)
						r.add(vd.getDateValue());
					else if (vd.getValue() != null)
						r.add(vd.getValue());
					else if (vd.getBlobDataValue() != null)
						r.add(vd.getBlobDataValue());
				}
				else
				{
					if (vd.getDateValue() != null)
						attributes
								.put(vd.getAttribute(), vd.getDateValue());
					else if (vd.getValue() != null)
						attributes.put(vd.getAttribute(), vd.getValue());
					else if (vd.getBlobDataValue() != null)
						attributes.put(vd.getAttribute(), vd.getBlobDataValue());
				}
			}
	
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
				target.setDisabled(target.getStatus() != AccountStatus.ACTIVE);
			}

			storeCacheEntry(source, target);
	
		} catch (InternalErrorException e) {
			throw new RuntimeException(e);
		}
	}

	private void storeCacheEntry(AccountEntity source, Account target) throws InternalErrorException {
		AccountCacheEntry entry = new AccountCacheEntry();
		entry.account = new Account(target);
		entry.timeStamp = System.currentTimeMillis();

		entry.ownerAcl = new HashSet<String>(
				getACLService()
					.expandACLAccounts(
							generateAcl (source, AccountAccessLevelEnum.ACCESS_OWNER)));
		entry.managerAcl = new HashSet<String>(
				getACLService()
					.expandACLAccounts(
							generateAcl (source, AccountAccessLevelEnum.ACCESS_MANAGER)));
		entry.userAcl = new HashSet<String>(
				getACLService()
					.expandACLAccounts(
							generateAcl (source, AccountAccessLevelEnum.ACCESS_USER)));

		String currentUser = Security.getCurrentAccount();
		if (entry.ownerAcl.contains(currentUser))
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_OWNER);
		else if (entry.managerAcl.contains(currentUser))
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_MANAGER);
		else if (entry.userAcl.contains(currentUser))
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_USER);
		else
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_NONE);

		getCache().put(source.getId(), entry);
	}

	private void fetchFromCache(Account target, AccountCacheEntry entry)
			throws InternalErrorException {
		target.setAttributes( new HashMap<String, Object>( entry.account.getAttributes()) );
		target.setDescription(entry.account.getDescription());
		target.setDisabled(entry.account.isDisabled());
		target.setSystem(entry.account.getSystem());
		target.setGrantedGroups(entry.account.getGrantedGroups());
		target.setGrantedRoles(entry.account.getGrantedRoles());
		target.setGrantedUsers(entry.account.getGrantedUsers());
		target.setId(entry.account.getId());
		target.setInheritNewPermissions(entry.account.isInheritNewPermissions());
		target.setLastPasswordSet(entry.account.getLastPasswordSet());
		target.setLastUpdated(entry.account.getLastUpdated());
		target.setLoginUrl(entry.account.getLoginUrl());
		target.setManagerGroups(entry.account.getManagerGroups());
		target.setManagerRoles(entry.account.getManagerRoles());
		target.setManagerUsers(entry.account.getManagerUsers());
		target.setName(entry.account.getName());
		target.setOwnerGroups(entry.account.getOwnerGroups());
		target.setOwnerRoles(entry.account.getOwnerRoles());
		target.setOwnerUsers(entry.account.getOwnerUsers());
		target.setPasswordExpiration(entry.account.getPasswordExpiration());
		target.setPasswordPolicy(entry.account.getPasswordPolicy());
		target.setType(entry.account.getType());
		target.setVaultFolder(entry.account.getVaultFolder());
		target.setVaultFolderId(entry.account.getVaultFolderId());
		target.setStatus(entry.account.getStatus());
		String currentUser = Security.getCurrentAccount();
		if (entry.ownerAcl.contains(currentUser))
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_OWNER);
		else if (entry.managerAcl.contains(currentUser))
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_MANAGER);
		else if (entry.userAcl.contains(currentUser))
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_USER);
		else
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_NONE);
	}


	private AccountAccessLevelEnum getAccessLevel(AccountEntity source) throws InternalErrorException {
		if (Security.isSyncServer())
			return AccountAccessLevelEnum.ACCESS_OWNER;
		
		String u = Security.getCurrentUser();
		if ( u != null)
		{
			UserEntity ue = getUserEntityDao().findByUserName(u);
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
		}
		return AccountAccessLevelEnum.ACCESS_NONE;
	}

	protected AccessControlList generateAcl(AccountEntity source,
			AccountAccessLevelEnum al) {
		AccessControlList acl = new AccessControlList();
		for ( AccountAccessEntity entry: source.getAcl())
		{
			if (entry.getLevel().equals (al))
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
			target.setDisabled( target.getStatus() != AccountStatus.ACTIVE);
		}
		SystemEntity dispatcher = getSystemEntityDao().findByName(
				source.getSystem());
		if (dispatcher == null)
			throw new IllegalArgumentException(String.format(
					Messages.getString("AccountEntityDaoImpl.WrongDispatcher"),
					source.getSystem(), source.getName(), source.getSystem()));
		target.setSystem(dispatcher);
		UserTypeEntity tipus = getUserTypeEntityDao().findByName(
				source.getPasswordPolicy());
		if (tipus == null)
			throw new IllegalArgumentException(String.format(
					Messages.getString("AccountEntityDaoImpl.WrongPassword"),
					source.getPasswordPolicy(), source.getName(),
					source.getSystem()));
		target.setPasswordPolicy(tipus);
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
		String[] split = text.trim().split(" +");
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
				.append(")");
		}
		return query(sb.toString(), params);
	}
	
	public void handleRemoveFromCache (AccountEntity entity) 
	{
		getCache().remove(entity.getId());
	}

}


class AccountCacheEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long timeStamp;
	Account account;
	HashSet<String> ownerAcl;
	HashSet<String> managerAcl;
	HashSet<String> userAcl;
}
