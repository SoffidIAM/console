package es.caib.seycon.ng.model;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Usuari;
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
		super.update(entity);
		auditar("U", entity.getName(), entity.getDispatcher().getCodi()); //$NON-NLS-1$
	}


	@Override
	public void remove (AccountEntity entity)
	{
		getAccountAccessEntityDao().remove(entity.getAcl());
		entity.getAcl().clear();
		getUserAccountEntityDao().remove(entity.getUsers());
		entity.getUsers().clear();
		getAccountPasswordEntityDao().remove(entity.getPasswords());
		entity.getPasswords().clear();
		getRolAccountEntityDao().remove(entity.getRoles());
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
		for (AccountAccessEntity acl: source.getAcl())
		{
			if (acl.getGroup() != null)
				grups.add(getGrupEntityDao().toGrup(acl.getGroup()));
			if (acl.getRol() != null)
				roles.add(getRolEntityDao().toRol(acl.getRol()));
			if (acl.getUser() != null)
				usuaris.add(getUsuariEntityDao().toUsuari(acl.getUser()));
		}
		target.setGrantedGroups(grups);
		target.setGrantedRoles(roles);
		target.setGrantedUsers(usuaris);
		target.setPasswordPolicy(source.getPasswordPolicy().getCodi());
		if (source.getType() == AccountType.USER)
		{
			for (UserAccountEntity uae: source.getUsers())
			{
				target.setPasswordPolicy(uae.getUser().getTipusUsuari().getCodi());
			}
		}
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
			java.util.List results = queryObject.list();
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

}
