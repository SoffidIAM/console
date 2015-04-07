// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.SystemGroupEntity;
import com.soffid.iam.model.UserDomainEntity;
import com.soffid.iam.model.UserTypeSystemEntity;
import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.GrupDispatcher;
import es.caib.seycon.ng.comu.TipusUsuariDispatcher;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;
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

/**
 * @see es.caib.seycon.ng.model.DispatcherEntity
 */
public class SystemEntityDaoImpl extends com.soffid.iam.model.SystemEntityDaoBase {

    private void auditarDispatcher(String accio, String bbdd) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setBbdd(bbdd);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObjecte("SC_DISPAT"); //$NON-NLS-1$

        AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
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
            auditarDispatcher("C", dispatcherEntity.getCode()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("SystemEntityDaoImpl.0"), dispatcherEntity.getCode(), message));
        }
    }

    public void update(com.soffid.iam.model.SystemEntity dispatcherEntity) throws RuntimeException {
        try {
            super.update(dispatcherEntity);
            getSession(false).flush();
            auditarDispatcher("U", dispatcherEntity.getCode()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("SystemEntityDaoImpl.1"), dispatcherEntity.getCode(), message));
        }
    }

	public void remove(com.soffid.iam.model.SystemEntity dispatcherEntity) throws RuntimeException {
		try
		{
			String codiDispatcher = dispatcherEntity.getCode();
			getAccountEntityDao().remove(dispatcherEntity.getAccounts());
			dispatcherEntity.getAccounts().clear();

			getRoleEntityDao().remove(dispatcherEntity.getRole());
			dispatcherEntity.getRole().clear();

			super.remove(dispatcherEntity);
			getSession(false).flush();
			auditarDispatcher("D", codiDispatcher); //$NON-NLS-1$
		}
		catch (Throwable e)
		{
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("SystemEntityDaoImpl.2"), dispatcherEntity.getCode(), message));
		}
	}

    public void toDispatcher(com.soffid.iam.model.SystemEntity sourceEntity, es.caib.seycon.ng.comu.Dispatcher targetVO) {
        super.toDispatcher(sourceEntity, targetVO);

        // Fem les transformacions necessàries
        targetVO.setSegur(new Boolean(sourceEntity.getSafe().compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setBasRol(new Boolean(sourceEntity.getBaseRole().compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setControlAccess(new Boolean(sourceEntity.getEnableAccessControl().compareTo("S") == 0)); //$NON-NLS-1$

        // Tipus d'usuari
        if (sourceEntity.getUserType() != null) {
            // convertim els tipus d'usuari a string separada per comes
            String tipus = ""; //$NON-NLS-1$
            for (Iterator it = sourceEntity.getUserType().iterator(); it.hasNext(); ) {
                tipus += ((UserTypeSystemEntity) it.next()).getUserType().getCode();
                if (it.hasNext()) tipus += ",";
            }
            targetVO.setRelacioLaboral(tipus);
        }

        // Domini de contrasenyes (i d'usuaris per transitivitat)
        if (sourceEntity.getDomain() != null) {// de contrasenyes
            PasswordDomainEntity domini = sourceEntity.getDomain();
            targetVO.setIdDominiContrasenyes(domini.getId());
            targetVO.setDominiContrasenyes(domini.getCode());
        }
        if (sourceEntity.getUserDomain() != null)
        {
            targetVO.setDominiUsuaris(sourceEntity.getUserDomain().getCode());
        }
        
        // convertim els grups a string separada per comes
        if (sourceEntity.getSystemGroup() != null) {
            String grups = ""; //$NON-NLS-1$
            for (Iterator it = sourceEntity.getSystemGroup().iterator(); it.hasNext(); ) {
                grups += ((SystemGroupEntity) it.next()).getGroup().getCode();
                if (it.hasNext()) grups += ",";
            }
            targetVO.setGrups(grups);
        }
        if (sourceEntity.getReplicaDatabases().isEmpty())
        	targetVO.setDatabaseReplicaId(null);
        else
        	targetVO.setDatabaseReplicaId(sourceEntity.getReplicaDatabases().iterator().next().getId());
        	
    }

    /**
     * @see es.caib.seycon.ng.model.DispatcherEntityDao#toDispatcher(es.caib.seycon.ng.model.DispatcherEntity)
     */
    public es.caib.seycon.ng.comu.Dispatcher toDispatcher(final com.soffid.iam.model.SystemEntity entity) {
        Dispatcher dispatcher = super.toDispatcher(entity);
        // toDispatcherCustom(entity, dispatcher);
        return dispatcher;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.SystemEntity loadDispatcherEntityFromDispatcher(es.caib.seycon.ng.comu.Dispatcher dispatcher) {
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
    public com.soffid.iam.model.SystemEntity dispatcherToEntity(es.caib.seycon.ng.comu.Dispatcher dispatcher) {
        com.soffid.iam.model.SystemEntity entity = this.loadDispatcherEntityFromDispatcher(dispatcher);
        this.dispatcherToEntity(dispatcher, entity, true);
        return entity;
    }

    public void dispatcherToEntity(es.caib.seycon.ng.comu.Dispatcher sourceVO, com.soffid.iam.model.SystemEntity targetEntity, boolean copyIfNull) {
        super.dispatcherToEntity(sourceVO, targetEntity, copyIfNull);

        // Fem les transformacions de VO A Entity
        Boolean esSegur = sourceVO.getSegur();
        if (esSegur != null) {
            targetEntity.setSafe(esSegur.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setSafe("N"); //$NON-NLS-1$
        }
        Boolean basatEnRol = sourceVO.getBasRol();
        if (basatEnRol != null) {
            targetEntity.setBaseRole(basatEnRol.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setBaseRole("N"); //$NON-NLS-1$
        }

        Boolean controlAcces = sourceVO.getControlAccess();
        if (controlAcces != null) {
            targetEntity.setEnableAccessControl(controlAcces.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setEnableAccessControl("N"); //$NON-NLS-1$
        }

        if (sourceVO.getDominiContrasenyes() == null) {
            if (sourceVO.getIdDominiContrasenyes() == null)
                targetEntity.setDomain(null);
            else
                targetEntity.setDomain(getPasswordDomainEntityDao().load(sourceVO.getIdDominiContrasenyes()));
        } else
            targetEntity.setDomain(getPasswordDomainEntityDao().findByCode(sourceVO.getDominiContrasenyes()));
        
        UserDomainEntity du;
		if (sourceVO.getDominiUsuaris() == null)
        	targetEntity.setUserDomain(null);
        else
        {
        	du = getUserDomainEntityDao().findByCode(sourceVO.getDominiUsuaris());
        	if (du == null)
        		throw new IllegalArgumentException(
        				String.format("service.dominiUsuaris[%s]",  //$NON-NLS-1$
        						sourceVO.getDominiUsuaris()));
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
