// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import com.soffid.iam.api.Audit;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.SystemGroupEntity;
import com.soffid.iam.model.UserDomainEntity;
import com.soffid.iam.model.UserTypeSystemEntity;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.lang.reflect.Array;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.hibernate.Query;

/**
 * @see es.caib.seycon.ng.model.DispatcherEntity
 */
public class SystemEntityDaoImpl extends com.soffid.iam.model.SystemEntityDaoBase {

    private void auditarDispatcher(String accio, String bbdd) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setDatabase(bbdd);
        auditoria.setAuthor(codiUsuari);
        auditoria.setObject("SC_DISPAT"); //$NON-NLS-1$

        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    public void create(com.soffid.iam.model.SystemEntity dispatcherEntity) throws RuntimeException {
        try {
            Collection<UserTypeSystemEntity> tipusUsuari = dispatcherEntity.getUserType();
            Collection<SystemGroupEntity> grups = dispatcherEntity.getSystemGroup();
            dispatcherEntity.setSystemGroup(null);
            dispatcherEntity.setUserType(null);
            
            super.create(dispatcherEntity);
            getSession(false).flush();

            // Creem les dependències de grupDispatcher i tipusUusariDispatcher
            if (grups != null) {
                for (SystemGroupEntity grup : grups) {
                    grup.setSystem(dispatcherEntity);
                    getSystemGroupEntityDao().create(grup);
                }
                dispatcherEntity.setSystemGroup(grups);
            }

            // I els tipus d'usuari
            if (tipusUsuari != null) {
                for (UserTypeSystemEntity tipus : tipusUsuari) {
                    tipus.setSystem(dispatcherEntity);
                    getUserTypeSystemEntityDao().create(tipus);
                }
                dispatcherEntity.setUserType(tipusUsuari);
            }
            auditarDispatcher("C", dispatcherEntity.getName()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("SystemEntityDaoImpl.0"), dispatcherEntity.getName(), message));
        }
    }

    public void update(com.soffid.iam.model.SystemEntity dispatcherEntity) throws RuntimeException {
        try {
            super.update(dispatcherEntity);
            getSession(false).flush();
            auditarDispatcher("U", dispatcherEntity.getName()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("SystemEntityDaoImpl.1"), dispatcherEntity.getName(), message));
        }
    }

	public void remove(com.soffid.iam.model.SystemEntity dispatcherEntity) throws RuntimeException {
		try
		{
	 		// Remove account attributes
			getSession().createQuery("delete from com.soffid.iam.model.AccountAttributeEntity where metadata.id in "
					+ "(select ame.id from com.soffid.iam.model.AccountMetadataEntity as ame where ame.system.id=:system)")
				.setLong("system", dispatcherEntity.getId())
				.executeUpdate();
	 		// Remove accounts
			getSession().createQuery("delete from com.soffid.iam.model.AccountEntity where system.id=:system")
				.setLong("system", dispatcherEntity.getId())
				.executeUpdate();
			// Remove roles
			getSession().createQuery("delete from com.soffid.iam.model.RoleEntity where system.id=:system")
				.setLong("system", dispatcherEntity.getId())
				.executeUpdate();
			// Remove metadata
			getSession().createQuery("delete from com.soffid.iam.model.AccountMetadataEntity where system.id=:system")
				.setLong("system", dispatcherEntity.getId())
				.executeUpdate();
			// Remove tasklog
			getSession().createQuery("delete from com.soffid.iam.model.TaskLogEntity where system.id=:system")
				.setLong("system", dispatcherEntity.getId())
				.executeUpdate();
			
			for ( EntryPointEntity ep: dispatcherEntity.getEntryPoints())
			{
				ep.setSystem(null);
				getEntryPointEntityDao().update(ep);
			}
			
			String codiDispatcher = dispatcherEntity.getName();
			super.remove(dispatcherEntity);
			getSession(false).flush();
			auditarDispatcher("D", codiDispatcher); //$NON-NLS-1$
		}
		catch (Throwable e)
		{
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("SystemEntityDaoImpl.2"), dispatcherEntity.getName(), message));
		}
	}

    public void toSystem(com.soffid.iam.model.SystemEntity sourceEntity, com.soffid.iam.api.System targetVO) {
        super.toSystem(sourceEntity, targetVO);

        // Fem les transformacions necessàries
        targetVO.setTrusted("S".equals(sourceEntity.getTrusted())); //$NON-NLS-1$
        targetVO.setRolebased("S".equals(sourceEntity.getRoleBased())); //$NON-NLS-1$
        targetVO.setAccessControl("S".equals(sourceEntity.getEnableAccessControl())); //$NON-NLS-1$

        // Tipus d'usuari
        if (sourceEntity.getUserType() != null) {
            // convertim els tipus d'usuari a string separada per comes
            String tipus = ""; //$NON-NLS-1$
            for (Iterator it = sourceEntity.getUserType().iterator(); it.hasNext(); ) {
                tipus += ((UserTypeSystemEntity) it.next()).getUserType().getName();
                if (it.hasNext()) tipus += ",";
            }
            targetVO.setUserTypes(tipus);
        }

        // Domini de contrasenyes (i d'usuaris per transitivitat)
        if (sourceEntity.getPasswordDomain() != null) {// de contrasenyes
            PasswordDomainEntity domini = sourceEntity.getPasswordDomain();
            targetVO.setPasswordsDomainId(domini.getId());
            targetVO.setPasswordsDomain(domini.getName());
        }
        if (sourceEntity.getUserDomain() != null)
        {
            targetVO.setUsersDomain(sourceEntity.getUserDomain().getName());
        }
        
        // convertim els grups a string separada per comes
        targetVO.setGroupsList(new LinkedList<>());
        if (sourceEntity.getSystemGroup() != null) {
            String grups = ""; //$NON-NLS-1$
            for (Iterator it = sourceEntity.getSystemGroup().iterator(); it.hasNext(); ) {
                SystemGroupEntity systemGroupEntity = (SystemGroupEntity) it.next();
				grups += systemGroupEntity.getGroup().getName();
                if (it.hasNext()) grups += ",";
                targetVO.getGroupsList().add(systemGroupEntity.getGroup().getName());
            }
            targetVO.setGroups(grups);
        }
       	targetVO.setDatabaseReplicaId(null);
       	targetVO.setTenant (sourceEntity.getTenant().getName());
       	if (sourceEntity.getGenerateTasksOnLoad() == null)
       		targetVO.setGenerateTasksOnLoad(true);
       	else
       		targetVO.setGenerateTasksOnLoad(sourceEntity.getGenerateTasksOnLoad().booleanValue());
       	
       	if ( ! Security.isUserInRole(Security.AUTO_AGENT_UPDATE))
       	{
    		targetVO.setParam0(null);
    		targetVO.setParam1(null);
    		targetVO.setParam2(null);
    		targetVO.setParam3(null);
    		targetVO.setParam4(null);
    		targetVO.setParam5(null);
    		targetVO.setParam6(null);
    		targetVO.setParam7(null);
    		targetVO.setParam8(null);
    		targetVO.setParam9(null);
    		targetVO.setBlobParam(null);
       	}
    }

    /**
     * @see es.caib.seycon.ng.model.DispatcherEntityDao#toDispatcher(es.caib.seycon.ng.model.DispatcherEntity)
     */
    public com.soffid.iam.api.System toSystem(final com.soffid.iam.model.SystemEntity entity) {
        com.soffid.iam.api.System dispatcher = super.toSystem(entity);
        // toDispatcherCustom(entity, dispatcher);
        return dispatcher;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.SystemEntity loadDispatcherEntityFromDispatcher(com.soffid.iam.api.System dispatcher) {
        com.soffid.iam.model.SystemEntity dispatcherEntity = null;
        if (dispatcher.getId() != null) {
            dispatcherEntity = load(dispatcher.getId());
        }

        if (dispatcherEntity == null) {
            dispatcherEntity = newSystemEntity();
        }
        return dispatcherEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.DispatcherEntityDao#dispatcherToEntity(es.caib.seycon.ng.Dispatcher)
     */
    public com.soffid.iam.model.SystemEntity systemToEntity(com.soffid.iam.api.System dispatcher) {
        com.soffid.iam.model.SystemEntity entity = this.loadDispatcherEntityFromDispatcher(dispatcher);
        this.systemToEntity(dispatcher, entity, true);
        return entity;
    }

    public void systemToEntity(com.soffid.iam.api.System sourceVO, com.soffid.iam.model.SystemEntity targetEntity, boolean copyIfNull) {
        super.systemToEntity(sourceVO, targetEntity, copyIfNull);

        // Fem les transformacions de VO A Entity
        Boolean esSegur = sourceVO.getTrusted();
        if (esSegur != null) {
            targetEntity.setTrusted(esSegur.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setTrusted("N"); //$NON-NLS-1$
        }
        Boolean basatEnRol = sourceVO.getRolebased();
        if (basatEnRol != null) {
            targetEntity.setRoleBased(basatEnRol.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setRoleBased("N"); //$NON-NLS-1$
        }

        Boolean controlAcces = sourceVO.getAccessControl();
        if (controlAcces != null) {
            targetEntity.setEnableAccessControl(controlAcces.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setEnableAccessControl("N"); //$NON-NLS-1$
        }

        if (sourceVO.getPasswordsDomain() == null) {
            if (sourceVO.getPasswordsDomainId() == null)
                targetEntity.setPasswordDomain(null);
            else
                targetEntity.setPasswordDomain(getPasswordDomainEntityDao().load(sourceVO.getPasswordsDomainId()));
        } else
            targetEntity.setPasswordDomain(getPasswordDomainEntityDao().findByName(sourceVO.getPasswordsDomain()));
        
        UserDomainEntity du;
		if (sourceVO.getUsersDomain() == null)
        	targetEntity.setUserDomain(null);
        else
        {
        	du = getUserDomainEntityDao().findByName(sourceVO.getUsersDomain());
        	if (du == null)
        		throw new IllegalArgumentException(String.format("service.dominiUsuaris[%s]", sourceVO.getUsersDomain()));
        	targetEntity.setUserDomain(du);
        }
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof SystemEntity) {
                SystemEntity entity = (SystemEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof SystemEntity) {
                SystemEntity entity = (SystemEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof SystemEntity) {
                SystemEntity entity = (SystemEntity) obj;
                this.remove(entity);
            }
        }
    }


    @Override
    public java.util.List<com.soffid.iam.model.SystemEntity> findActives(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria) {
    	List<SystemEntity> actius = super.findActives(queryString, criteria);
    	
    	for (Iterator<SystemEntity> it = actius.iterator(); it.hasNext(); ) if (it.next().getUrl().isEmpty()) it.remove();
    					
    	return actius;
	}
}
