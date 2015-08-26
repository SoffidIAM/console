package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserTypeEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.sync.engine.TaskHandler;
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
		Auditoria auditoria = new Auditoria();
		auditoria.setAccio(accio);
		auditoria.setAccount(account);
		auditoria.setBbdd(dispatcher);
		auditoria.setAutor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
		auditoria.setObjecte("SC_ACCOUN"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
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
		target.setDispatcher(source.getSystem().getName());
		Collection<Grup> grups = new LinkedList<Grup>();
		Collection<Rol> roles = new LinkedList<Rol>();
		Collection<Usuari> usuaris = new LinkedList<Usuari>();
		Collection<Grup> managerGrups = new LinkedList<Grup>();
		Collection<Rol> managerRoles = new LinkedList<Rol>();
		Collection<Usuari> managerUsers = new LinkedList<Usuari>();
		Collection<Grup> ownerGrups = new LinkedList<Grup>();
		Collection<Rol> ownerRoles = new LinkedList<Rol>();
		Collection<Usuari> ownerUsers = new LinkedList<Usuari>();
		if ( source.getType().equals (AccountType.USER))
		{
			for (com.soffid.iam.model.UserAccountEntity uae : source.getUsers()) {
                ownerUsers.add(getUserEntityDao().toUsuari(uae.getUser()));
            }
		}
		else
		{
			for (com.soffid.iam.model.AccountAccessEntity acl : source.getAcl()) {
                if (acl.getGroup() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_USER)) 
                	grups.add(getGroupEntityDao().toGrup(acl.getGroup()));
                if (acl.getRole() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_USER)) 
                	roles.add(getRoleEntityDao().toRol(acl.getRole()));
                if (acl.getUser() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_USER)) 
                	usuaris.add(getUserEntityDao().toUsuari(acl.getUser()));
                if (acl.getGroup() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_MANAGER))
                	managerGrups.add(getGroupEntityDao().toGrup(acl.getGroup()));
                if (acl.getRole() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_MANAGER)) 
                	managerRoles.add(getRoleEntityDao().toRol(acl.getRole()));
                if (acl.getUser() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_MANAGER)) 
                	managerUsers.add(getUserEntityDao().toUsuari(acl.getUser()));
                if (acl.getGroup() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_OWNER)) 
                	ownerGrups.add(getGroupEntityDao().toGrup(acl.getGroup()));
                if (acl.getRole() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_OWNER)) 
                	ownerRoles.add(getRoleEntityDao().toRol(acl.getRole()));
                if (acl.getUser() != null & acl.getLevel().equals(AccountAccessLevelEnum.ACCESS_OWNER)) 
                	ownerUsers.add(getUserEntityDao().toUsuari(acl.getUser()));
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
		for ( AccountAttributeEntity att: source.getAttributes())
		{
			DadaUsuari vd = getAccountAttributeEntityDao().toDadaUsuari(att);
			if (vd.getValorDadaDate() != null)
				target.getAttributes().put(vd.getCodiDada(), vd.getValorDadaDate());
			else if (vd.getValorDada() != null)
				target.getAttributes().put(vd.getCodiDada(), vd.getValorDada());
		}
		
		HashMap<String, Object> atts = new HashMap<String, Object>();
		target.setAttributes(atts);
		// Now assign attributes
		for ( AccountAttributeEntity att: source.getAttributes())
		{
			DadaUsuari vd = getAccountAttributeEntityDao().toDadaUsuari(att);
			if (vd.getBlobDataValue() != null)
				atts.put(vd.getCodiDada(), vd.getBlobDataValue());
			else if (vd.getValorDadaDate() != null)
				atts.put(vd.getCodiDada(), vd.getValorDadaDate());
			else if (vd.getValorDada() != null)
				atts.put(vd.getCodiDada(), vd.getValorDada());
		}
	}

	@Override
    public void accountToEntity(Account source, com.soffid.iam.model.AccountEntity target, boolean copyIfNull) {
		super.accountToEntity(source, target, copyIfNull);
		SystemEntity dispatcher = getSystemEntityDao().findByName(source.getDispatcher());
		if (dispatcher == null)
			throw new IllegalArgumentException(String.format(Messages.getString("AccountEntityDaoImpl.WrongDispatcher"),  //$NON-NLS-1$
					source.getDispatcher(),
					source.getName(),
					source.getDispatcher()));
		target.setSystem(dispatcher);
		UserTypeEntity tipus = getUserTypeEntityDao().findByName(source.getPasswordPolicy());
		if (tipus == null)
			throw new IllegalArgumentException(String.format(Messages.getString("AccountEntityDaoImpl.WrongPassword"),  //$NON-NLS-1$
					source.getPasswordPolicy(),
					source.getName(),
					source.getDispatcher()));
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
