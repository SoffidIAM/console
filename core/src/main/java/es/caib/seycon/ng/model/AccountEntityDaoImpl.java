package es.caib.seycon.ng.model;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.api.AccessControlList;
import com.soffid.iam.model.AccountAttributeEntity;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountAccessLevelEnumEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.criteria.CriteriaSearchConfiguration;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.Security;


public class AccountEntityDaoImpl extends AccountEntityDaoBase
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
		AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(
						auditoria);
		getAuditoriaEntityDao().create(auditoriaEntity);
	}

    @Override
	public void create (AccountEntity entity)
	{
		super.create(entity);
		auditar("C", entity.getName(), entity.getDispatcher().getCodi()); //$NON-NLS-1$
	}


	@Override
	public void update (AccountEntity entity)
	{
		try {
			handleUpdate(entity, "U");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public void remove (AccountEntity entity)
	{
		getAccountAccessEntityDao().remove(new LinkedList<AccountAccessEntity>(entity.getAcl()));
		entity.getAcl().clear();
		getUserAccountEntityDao().remove(new LinkedList<UserAccountEntity>(entity.getUsers()));
		entity.getUsers().clear();
		getAccountPasswordEntityDao().remove(new LinkedList<AccountPasswordEntity>(entity.getPasswords()));
		entity.getPasswords().clear();
		getRolAccountEntityDao().remove(new LinkedList<RolAccountEntity>(entity.getRoles()));
		entity.getRoles().clear();
		for (AuditoriaEntity aud: getAuditoriaEntityDao().query(
						"select aud from es.caib.seycon.ng.model.AuditoriaEntity as aud where aud.accountAssoc.id=:id" //$NON-NLS-1$
						, new Parameter[] {new Parameter("id", entity.getId())} ) ) //$NON-NLS-1$
		{
			aud.setAccountAssoc(null);
			getAuditoriaEntityDao().update(aud);
		}
		super.remove(entity);
		auditar("D", entity.getName(), entity.getDispatcher().getCodi()); //$NON-NLS-1$
	}


	public AccountEntity accountToEntity(Account instance)
	{
		AccountEntity entity = load (instance.getId());
		if (entity == null)
			entity = newAccountEntity();
		super.accountToEntity(instance, entity, true);
		return entity;
	}

	@Override
	public void toAccount(AccountEntity source, Account target)
	{
		super.toAccount(source, target);
		// Incompatible types source.dispatcher and target.dispatcher
		// Missing attribute grantedGroups on entity
		// Missing attribute grantedUsers on entity
		// Missing attribute grantedRoles on entity
		target.setDispatcher(source.getDispatcher().getCodi());
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
			for (UserAccountEntity uae: source.getUsers())
			{
				ownerUsers.add( getUsuariEntityDao().toUsuari(uae.getUser()));
			}
		}
		else
		{
			for (AccountAccessEntity acl: source.getAcl())
			{
				// Users
				if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_USER))
					grups.add(getGrupEntityDao().toGrup(acl.getGroup()));
				if (acl.getRol() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_USER))
					roles.add(getRolEntityDao().toRol(acl.getRol()));
				if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_USER))
					usuaris.add(getUsuariEntityDao().toUsuari(acl.getUser()));
				// Managers
				if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_MANAGER))
					managerGrups.add(getGrupEntityDao().toGrup(acl.getGroup()));
				if (acl.getRol() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_MANAGER))
					managerRoles.add(getRolEntityDao().toRol(acl.getRol()));
				if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_MANAGER))
					managerUsers.add(getUsuariEntityDao().toUsuari(acl.getUser()));
				// Users
				if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_OWNER))
					ownerGrups.add(getGrupEntityDao().toGrup(acl.getGroup()));
				if (acl.getRol() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_OWNER))
					ownerRoles.add(getRolEntityDao().toRol(acl.getRol()));
				if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_OWNER))
					ownerUsers.add(getUsuariEntityDao().toUsuari(acl.getUser()));
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

		target.setPasswordPolicy(source.getPasswordPolicy().getCodi());
		if (source.getType() == AccountType.USER)
		{
			for (UserAccountEntity uae: source.getUsers())
			{
				target.setPasswordPolicy(uae.getUser().getTipusUsuari().getCodi());
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
	}

	private AccountAccessLevelEnum getAccessLevel(AccountEntity source) throws InternalErrorException {
		if (Security.isDisableAllSecurityForEver())
			return AccountAccessLevelEnum.ACCESS_OWNER;
		
		String u = Security.getCurrentUser();
		if ( u != null)
		{
			UsuariEntity ue = getUsuariEntityDao().findByCodi(u);
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
				if (entry.getRol() != null)
					acl.getRoles().add(entry.getRol().getId());
			}
		}
		return acl;
	}

	@Override
	public void accountToEntity(Account source, AccountEntity target,
			boolean copyIfNull)
	{
		super.accountToEntity(source, target, copyIfNull);
		DispatcherEntity dispatcher = getDispatcherEntityDao().findByCodi(source.getDispatcher());
		if (dispatcher == null)
			throw new IllegalArgumentException(String.format(Messages.getString("AccountEntityDaoImpl.WrongDispatcher"),  //$NON-NLS-1$
					source.getDispatcher(),
					source.getName(),
					source.getDispatcher()));
		target.setDispatcher(dispatcher);
		TipusUsuariEntity tipus = getTipusUsuariEntityDao().findByCodi(source.getPasswordPolicy());
		if (tipus == null)
			throw new IllegalArgumentException(String.format(Messages.getString("AccountEntityDaoImpl.WrongPassword"),  //$NON-NLS-1$
					source.getPasswordPolicy(),
					source.getName(),
					source.getDispatcher()));
		target.setPasswordPolicy(tipus);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountEntity> findSharedAccounts(
			CriteriaSearchConfiguration criteria, String name, String dispatcher)
	{
		try
		{
			StringBuffer where = new StringBuffer();
			where.append("select ac from es.caib.seycon.nt.model.AccountEntityDaoImpl as ac where ac.type != '"+AccountType.USER+"' "); //$NON-NLS-1$ //$NON-NLS-2$
			if (name != null && ! name.isEmpty())
				where.append (" and ac.name like :name"); //$NON-NLS-1$
			if (dispatcher != null && ! dispatcher.isEmpty())
				where.append (" and ac.dispatcher.codi like :dispatcher"); //$NON-NLS-1$
			org.hibernate.Query queryObject = super.getSession(false).createQuery(where.toString());
			if (name != null && ! name.isEmpty())
				queryObject.setParameter("name", name); //$NON-NLS-1$
			if (dispatcher != null && ! dispatcher.isEmpty())
				queryObject.setParameter("dispatcher", dispatcher); //$NON-NLS-1$
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			@SuppressWarnings("rawtypes")
			java.util.List<AccountEntity> results = queryObject.list();
			return (java.util.List<es.caib.seycon.ng.model.AccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	
	

	@Override
	protected void handlePropagateChanges(AccountEntity account) throws Exception 
	{
    	if (account.getType().equals(AccountType.USER))
    	{
			for (UserAccountEntity uac: account.getUsers())
			{
				getUserAccountEntityDao().propagateChanges (uac);
			}
    	} else {
    		TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
    		tasque.setData(new Timestamp(System.currentTimeMillis()));
			tasque.setTransa(TaskHandler.UPDATE_ACCOUNT);
			tasque.setBd(account.getDispatcher().getCodi());
			tasque.setCoddis(account.getDispatcher().getCodi());
			tasque.setUsuari(account.getName());
			getTasqueEntityDao().create(tasque);
    	}
    }

	@Override
	protected void handleUpdate(AccountEntity entity, String auditType)
			throws Exception {
		super.update(entity);
		if (auditType != null)
			auditar(auditType, entity.getName(), entity.getDispatcher().getCodi()); //$NON-NLS-1$
	}

}
