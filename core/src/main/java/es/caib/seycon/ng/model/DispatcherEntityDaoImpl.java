// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

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

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.GrupDispatcher;
import es.caib.seycon.ng.comu.TipusUsuariDispatcher;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.DispatcherEntity
 */
public class DispatcherEntityDaoImpl extends es.caib.seycon.ng.model.DispatcherEntityDaoBase {

    private void auditarDispatcher(String accio, String bbdd) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setBbdd(bbdd);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObjecte("SC_DISPAT"); //$NON-NLS-1$

        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    public void create(es.caib.seycon.ng.model.DispatcherEntity dispatcherEntity)
            throws RuntimeException {
        try {
            Collection<TipusUsuariDispatcherEntity> tipusUsuari = dispatcherEntity.getTipusUsuari();
            Collection<GrupDispatcherEntity> grups = dispatcherEntity.getGrupDispatcher();
            dispatcherEntity.setGrupDispatcher(null);
            dispatcherEntity.setTipusUsuari(null);
            
            super.create(dispatcherEntity);
            getSession(false).flush();

            // Creem les dependències de grupDispatcher i tipusUusariDispatcher
            if (grups != null) {
                for (GrupDispatcherEntity grup : grups) {
                    grup.setDispatcher(dispatcherEntity);
                    getGrupDispatcherEntityDao().create(grup);
                }
                dispatcherEntity.setGrupDispatcher(grups);
            }

            // I els tipus d'usuari
            if (tipusUsuari != null) {
                for (TipusUsuariDispatcherEntity tipus : tipusUsuari) {
                    tipus.setDispatcher(dispatcherEntity);
                    //tipus.setDispatcher(dispatcherEntity);
                    getTipusUsuariDispatcherEntityDao().create(tipus);
                }
                dispatcherEntity.setTipusUsuari(tipusUsuari);
            }
            auditarDispatcher("C", dispatcherEntity.getCodi()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(
                    Messages.getString("DispatcherEntityDaoImpl.0"), dispatcherEntity.getCodi(), //$NON-NLS-1$
                    message));
        }
    }

    public void update(es.caib.seycon.ng.model.DispatcherEntity dispatcherEntity)
            throws RuntimeException {
        try {
            super.update(dispatcherEntity);
            getSession(false).flush();
            auditarDispatcher("U", dispatcherEntity.getCodi()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(
                    Messages.getString("DispatcherEntityDaoImpl.1"), //$NON-NLS-1$
                    dispatcherEntity.getCodi(), message));
        }
    }

	public void remove (es.caib.seycon.ng.model.DispatcherEntity dispatcherEntity)
					throws RuntimeException
	{
		try
		{
			String codiDispatcher = dispatcherEntity.getCodi();
			getAccountEntityDao().remove(dispatcherEntity.getAccounts());
			dispatcherEntity.getAccounts().clear();

			getRolEntityDao().remove(dispatcherEntity.getRol());
			dispatcherEntity.getRol().clear();

			super.remove(dispatcherEntity);
			getSession(false).flush();
			auditarDispatcher("D", codiDispatcher); //$NON-NLS-1$
		}
		catch (Throwable e)
		{
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(
							Messages.getString("DispatcherEntityDaoImpl.2"), //$NON-NLS-1$
							dispatcherEntity.getCodi(), message));
		}
	}

    public void toDispatcher(es.caib.seycon.ng.model.DispatcherEntity sourceEntity,
            es.caib.seycon.ng.comu.Dispatcher targetVO) {
        super.toDispatcher(sourceEntity, targetVO);

        // Fem les transformacions necessàries
        targetVO.setSegur(new Boolean(sourceEntity.getSegur().compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setBasRol(new Boolean(sourceEntity.getBasRol().compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setControlAccess(new Boolean(sourceEntity.getControlAcces().compareTo("S") == 0)); //$NON-NLS-1$

        // Tipus d'usuari
        if (sourceEntity.getTipusUsuari() != null) {
            // convertim els tipus d'usuari a string separada per comes
            String tipus = ""; //$NON-NLS-1$
            for (Iterator it = sourceEntity.getTipusUsuari().iterator(); it.hasNext();) {
                tipus += ((TipusUsuariDispatcherEntity) it.next()).getTipusUsuari().getCodi();
                if (it.hasNext())
                    tipus += ","; //$NON-NLS-1$
            }
            targetVO.setRelacioLaboral(tipus);
        }

        // Domini de contrasenyes (i d'usuaris per transitivitat)
        if (sourceEntity.getDomini() != null) {// de contrasenyes
            DominiContrasenyaEntity domini = sourceEntity.getDomini();
            targetVO.setIdDominiContrasenyes(domini.getId());
            targetVO.setDominiContrasenyes(domini.getCodi());
        }
        if (sourceEntity.getDominiUsuari() != null)
        {
            targetVO.setDominiUsuaris(sourceEntity.getDominiUsuari().getCodi());
        }
        
        // convertim els grups a string separada per comes
        if (sourceEntity.getGrupDispatcher() != null) {
            String grups = ""; //$NON-NLS-1$
            for (Iterator it = sourceEntity.getGrupDispatcher().iterator(); it.hasNext();) {
                grups += ((GrupDispatcherEntity) it.next()).getGrup().getCodi();
                if (it.hasNext())
                    grups += ","; //$NON-NLS-1$
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
    public es.caib.seycon.ng.comu.Dispatcher toDispatcher(
            final es.caib.seycon.ng.model.DispatcherEntity entity) {
        Dispatcher dispatcher = super.toDispatcher(entity);
        // toDispatcherCustom(entity, dispatcher);
        return dispatcher;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.DispatcherEntity loadDispatcherEntityFromDispatcher(
            es.caib.seycon.ng.comu.Dispatcher dispatcher) {
        es.caib.seycon.ng.model.DispatcherEntity dispatcherEntity = null;
        if (dispatcher.getId() != null) {
            dispatcherEntity = load(dispatcher.getId());
        }

        if (dispatcherEntity == null) {
            dispatcherEntity = newDispatcherEntity();
        }
        return dispatcherEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.DispatcherEntityDao#dispatcherToEntity(es.caib.seycon.ng.Dispatcher)
     */
    public es.caib.seycon.ng.model.DispatcherEntity dispatcherToEntity(
            es.caib.seycon.ng.comu.Dispatcher dispatcher) {
        es.caib.seycon.ng.model.DispatcherEntity entity = this
                .loadDispatcherEntityFromDispatcher(dispatcher);
        this.dispatcherToEntity(dispatcher, entity, true);
        return entity;
    }

    public void dispatcherToEntity(es.caib.seycon.ng.comu.Dispatcher sourceVO,
            es.caib.seycon.ng.model.DispatcherEntity targetEntity, boolean copyIfNull) {
        super.dispatcherToEntity(sourceVO, targetEntity, copyIfNull);

        // Fem les transformacions de VO A Entity
        Boolean esSegur = sourceVO.getSegur();
        if (esSegur != null) {
            targetEntity.setSegur(esSegur.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setSegur("N"); //$NON-NLS-1$
        }
        Boolean basatEnRol = sourceVO.getBasRol();
        if (basatEnRol != null) {
            targetEntity.setBasRol(basatEnRol.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setBasRol("N"); //$NON-NLS-1$
        }

        Boolean controlAcces = sourceVO.getControlAccess();
        if (controlAcces != null) {
            targetEntity.setControlAcces(controlAcces.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setControlAcces("N"); //$NON-NLS-1$
        }

        if (sourceVO.getDominiContrasenyes() == null) {
            if (sourceVO.getIdDominiContrasenyes() == null)
                targetEntity.setDomini(null);
            else
                targetEntity.setDomini(getDominiContrasenyaEntityDao().load(
                                sourceVO.getIdDominiContrasenyes()));
        } else
            targetEntity.setDomini(
                            getDominiContrasenyaEntityDao().
                                findByCodi(sourceVO.getDominiContrasenyes()));
        
        DominiUsuariEntity du;
		if (sourceVO.getDominiUsuaris() == null)
        	targetEntity.setDominiUsuari(null);
        else
        {
        	du = getDominiUsuariEntityDao().findByCodi(sourceVO.getDominiUsuaris());
        	if (du == null)
        		throw new IllegalArgumentException(
        				String.format("service.dominiUsuaris[%s]",  //$NON-NLS-1$
        						sourceVO.getDominiUsuaris()));
        	targetEntity.setDominiUsuari(du);
        }
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof DispatcherEntity) {
                    DispatcherEntity entity = (DispatcherEntity) obj;
                    this.create(entity); // cridem al mètode 1 per 1
                }
            }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof DispatcherEntity) {
                    DispatcherEntity entity = (DispatcherEntity) obj;
                    this.update(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof DispatcherEntity) {
                    DispatcherEntity entity = (DispatcherEntity) obj;
                    this.remove(entity);// cridem al mètode 1 per 1
                }
            }
    }


    @Override
	public java.util.List<es.caib.seycon.ng.model.DispatcherEntity> findActius(final java.lang.String queryString, es.caib.seycon.ng.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
    	List<DispatcherEntity> actius = super.findActius(queryString, criteria);
    	
    	for (Iterator<DispatcherEntity> it = actius.iterator(); it.hasNext();)
    		if (it.next().getUrl().isEmpty())
    			it.remove();
    					
    	return actius;
	}
}
