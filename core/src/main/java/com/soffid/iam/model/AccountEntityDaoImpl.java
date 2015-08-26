package com.soffid.iam.model;

import com.soffid.iam.api.Account;
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
import com.soffid.iam.sync.engine.TaskHandler;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.model.*;
import es.caib.seycon.ng.utils.Security;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AccountEntityDaoImpl extends com.soffid.iam.model.AccountEntityDaoBase
{

	private void auditar (String accio, String account, String dispatcher)
	{

		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setAccount(account);
		auditoria.setDatabase(dispatcher);
		auditoria.setAuthor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar.getInstance().getTime()));
		auditoria.setObject("SC_ACCOUN"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

    @Override
    public void create(com.soffid.iam.model.AccountEntity entity) {
		super.create(entity);
		auditar("C", entity.getName(), entity.getSystem().getName()); //$NON-NLS-1$
	}


	@Override
    public void update(com.soffid.iam.model.AccountEntity entity) {
		try {
			handleUpdate(entity, "U");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
    public void remove(com.soffid.iam.model.AccountEntity entity) {
		getAccountAccessEntityDao().remove(new LinkedList<com.soffid.iam.model.AccountAccessEntity>(entity.getAcl()));
		entity.getAcl().clear();
		getUserAccountEntityDao().remove(new LinkedList<com.soffid.iam.model.UserAccountEntity>(entity.getUsers()));
		entity.getUsers().clear();
		getAccountPasswordEntityDao().remove(new LinkedList<com.soffid.iam.model.AccountPasswordEntity>(entity.getPasswords()));
		entity.getPasswords().clear();
		getRoleAccountEntityDao().remove(new LinkedList<RoleAccountEntity>(entity.getRoles()));
		entity.getRoles().clear();
		for (AuditEntity aud : getAuditEntityDao().query(
				"select aud from com.soffid.iam.model.AccountEntity as aud "
				+ "where aud.accountAssoc.id=:id", new Parameter[]{new Parameter("id", entity.getId())})) {
            aud.setAccountAssoc(null);
            getAuditEntityDao().update(aud);
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
    public void toAccount(com.soffid.iam.model.AccountEntity source, Account target) {
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
		if ( source.getType().equals (AccountType.USER))
		{
			for (com.soffid.iam.model.UserAccountEntity uae : source.getUsers()) {
                ownerUsers.add(getUserEntityDao().toUser(uae.getUser()));
            }
		}
		else
		{
			for (com.soffid.iam.model.AccountAccessEntity acl : source.getAcl()) {
                if (acl.getGroup() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_USER)) grups.add(getGroupEntityDao().toGroup(acl.getGroup()));
                if (acl.getRole() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_USER)) roles.add(getRoleEntityDao().toRole(acl.getRole()));
                if (acl.getUser() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_USER)) usuaris.add(getUserEntityDao().toUser(acl.getUser()));
                if (acl.getGroup() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_MANAGER)) managerGrups.add(getGroupEntityDao().toGroup(acl.getGroup()));
                if (acl.getRole() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_MANAGER)) managerRoles.add(getRoleEntityDao().toRole(acl.getRole()));
                if (acl.getUser() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_MANAGER)) managerUsers.add(getUserEntityDao().toUser(acl.getUser()));
                if (acl.getGroup() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_OWNER)) ownerGrups.add(getGroupEntityDao().toGroup(acl.getGroup()));
                if (acl.getRole() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_OWNER)) ownerRoles.add(getRoleEntityDao().toRole(acl.getRole()));
                if (acl.getUser() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_OWNER)) ownerUsers.add(getUserEntityDao().toUser(acl.getUser()));
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
		if (source.getType() == AccountType.USER)
		{
			for (com.soffid.iam.model.UserAccountEntity uae : source.getUsers()) {
                target.setPasswordPolicy(uae.getUser().getUserType().getName());
            }
		}
		target.setAttributes(new HashMap<String, Object>());
		for (AccountAttributeEntity att : source.getAttributes()) {
            UserData vd = getAccountAttributeEntityDao().toUserData(att);
            if (vd.getDateValue() != null) target.getAttributes().put(vd.getAttribute(), vd.getDateValue()); else if (vd.getValue() != null) target.getAttributes().put(vd.getAttribute(), vd.getValue());
        }
		
		HashMap<String, Object> atts = new HashMap<String, Object>();
		target.setAttributes(atts);
		// Now assign attributes
		for (AccountAttributeEntity att : source.getAttributes()) {
            UserData vd = getAccountAttributeEntityDao().toUserData(att);
            if (vd.getBlobDataValue() != null) atts.put(vd.getAttribute(), vd.getBlobDataValue()); else if (vd.getDateValue() != null) atts.put(vd.getAttribute(), vd.getDateValue()); else if (vd.getValue() != null) atts.put(vd.getAttribute(), vd.getValue());
        }
	}

	@Override
    public void accountToEntity(Account source, com.soffid.iam.model.AccountEntity target, boolean copyIfNull) {
		super.accountToEntity(source, target, copyIfNull);
		SystemEntity dispatcher = getSystemEntityDao().findByName(source.getSystem());
		if (dispatcher == null)
			throw new IllegalArgumentException(String.format(Messages.getString("AccountEntityDaoImpl.WrongDispatcher"), source.getSystem(), source.getName(), source.getSystem()));
		target.setSystem(dispatcher);
		UserTypeEntity tipus = getUserTypeEntityDao().findByName(source.getPasswordPolicy());
		if (tipus == null)
			throw new IllegalArgumentException(String.format(Messages.getString("AccountEntityDaoImpl.WrongPassword"), source.getPasswordPolicy(), source.getName(), source.getSystem()));
		target.setPasswordPolicy(tipus);
	}

	@SuppressWarnings(value = "unchecked")
    @Override
    public List<com.soffid.iam.model.AccountEntity> findSharedAccounts(CriteriaSearchConfiguration criteria, String name, String dispatcher) {
		try
		{
			StringBuffer where = new StringBuffer();
			where.append("select ac from com.soffid.iam.model.AccountEntity as ac "
					+ "where ac.type != '"+AccountType.USER+"' "); //$NON-NLS-1$ //$NON-NLS-2$
			if (name != null && ! name.isEmpty())
				where.append (" and ac.name like :name"); //$NON-NLS-1$
			if (dispatcher != null && ! dispatcher.isEmpty())
				where.append (" and ac.system.name like :dispatcher"); //$NON-NLS-1$
			org.hibernate.Query queryObject = super.getSession(false).createQuery(where.toString());
			if (name != null && ! name.isEmpty())
				queryObject.setParameter("name", name); //$NON-NLS-1$
			if (dispatcher != null && ! dispatcher.isEmpty())
				queryObject.setParameter("dispatcher", dispatcher); //$NON-NLS-1$
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			@SuppressWarnings(value = "rawtypes")
            java.util.List<com.soffid.iam.model.AccountEntity> results = queryObject.list();
			return (java.util.List<com.soffid.iam.model.AccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	
	

	@Override
    protected void handlePropagateChanges(com.soffid.iam.model.AccountEntity account) throws Exception {
    	if (account.getType().equals(AccountType.USER))
    	{
			for (com.soffid.iam.model.UserAccountEntity uac : account.getUsers()) {
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
		if (auditType != null)
			auditar(auditType, entity.getName(), entity.getSystem().getName()); //$NON-NLS-1$
	}
}
