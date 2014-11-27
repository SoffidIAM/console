// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.hibernate.Hibernate;
import org.jbpm.db.hibernate.HibernateHelper;

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.LlistaCorreuUsuari;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.LlistaCorreuUsuariEntity
 */
public class LlistaCorreuUsuariEntityDaoImpl extends
        es.caib.seycon.ng.model.LlistaCorreuUsuariEntityDaoBase {

    /**
     * Creem auditoria de les llistes de correu d'usuari (no existeix al entity)
     * 
     * @param accio
     * @param nomLlistaDeCorreu
     * @param codiUsuariLlista
     * @param dominiLlistaCorreu
     */
    private void auditarLlistaDeCorreuUsuari(String accio,
            LlistaCorreuUsuariEntity llistaCorreuUsuari) {
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        LlistaCorreuEntity llista = llistaCorreuUsuari.getLlistaDeCorreu();
        if (llista != null) {
            auditoria.setLlistaCorreu(llista.getNom());
            DominiCorreuEntity domini = llista.getDomini();
            if (domini != null)
                auditoria.setDominiCorreu(domini.getCodi());
        }
        if (llistaCorreuUsuari.getUsuari() != null)
            auditoria.setUsuari(llistaCorreuUsuari.getUsuari().getCodi());
        auditoria.setAutor(Security.getCurrentAccount()); //$NON-NLS-1$
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_USULCO"); //$NON-NLS-1$
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
                .auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    public void create(
            es.caib.seycon.ng.model.LlistaCorreuUsuariEntity llistaCorreuUsuari)
            throws RuntimeException {
        try {
            DominiCorreuEntity dominiCorreu = llistaCorreuUsuari
                    .getLlistaDeCorreu().getDomini();
            if (dominiCorreu != null) {
                if (dominiCorreu.getObsolet().compareTo("S") == 0) { //$NON-NLS-1$
                    throw new SeyconException(
                            Messages.getString("LlistaCorreuUsuariEntityDaoImpl.0")); //$NON-NLS-1$
                }
            }
            super.create(llistaCorreuUsuari);
            if (Hibernate.isInitialized(llistaCorreuUsuari.getLlistaDeCorreu().getLlistaDeCorreuUsuari()))
            		llistaCorreuUsuari.getLlistaDeCorreu().getLlistaDeCorreuUsuari().add(llistaCorreuUsuari);
            
            if (Hibernate.isInitialized(llistaCorreuUsuari.getUsuari().getLlistaDeCorreuUsuari()))
            		llistaCorreuUsuari.getUsuari().getLlistaDeCorreuUsuari().add(llistaCorreuUsuari);
            
            createTask(llistaCorreuUsuari);
            getSession(false).flush();

            // Creem auditoria
            auditarLlistaDeCorreuUsuari("C", llistaCorreuUsuari); //$NON-NLS-1$

        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("LlistaCorreuUsuariEntityDaoImpl.1"),  //$NON-NLS-1$
					llistaCorreuUsuari.getLlistaDeCorreu().getNom(), 
					llistaCorreuUsuari.getUsuari().getCodi(), 
					message));
        }
    }

    private void createTask(es.caib.seycon.ng.model.LlistaCorreuUsuariEntity llistaCorreuUsuari) {
        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.UPDATE_LIST_ALIAS);
        tasque.setAlies(llistaCorreuUsuari.getLlistaDeCorreu().getNom());
        if (llistaCorreuUsuari.getLlistaDeCorreu().getDomini() != null)
            tasque.setDomcor(llistaCorreuUsuari.getLlistaDeCorreu().getDomini().getCodi());
        getTasqueEntityDao().create(tasque);
    }

    public void update(LlistaCorreuUsuariEntity llistaCorreuUsuari) {
        super.update(llistaCorreuUsuari);
        createTask(llistaCorreuUsuari);
        getSession(false).flush();
        // Creem auditoria
        auditarLlistaDeCorreuUsuari("U", llistaCorreuUsuari); //$NON-NLS-1$

    }

    public void remove(
            es.caib.seycon.ng.model.LlistaCorreuUsuariEntity llistaCorreuUsuari)
            throws RuntimeException {
        try {
            if (Hibernate.isInitialized(llistaCorreuUsuari.getLlistaDeCorreu().getLlistaDeCorreuUsuari()))
        		llistaCorreuUsuari.getLlistaDeCorreu().getLlistaDeCorreuUsuari().remove(llistaCorreuUsuari);
        
            if (Hibernate.isInitialized(llistaCorreuUsuari.getUsuari().getLlistaDeCorreuUsuari()))
        		llistaCorreuUsuari.getUsuari().getLlistaDeCorreuUsuari().remove(llistaCorreuUsuari);
            super.remove(llistaCorreuUsuari);
            createTask(llistaCorreuUsuari);
            getSession(false).flush();
            // Creem auditoria
            auditarLlistaDeCorreuUsuari("D", llistaCorreuUsuari); //$NON-NLS-1$

        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("LlistaCorreuUsuariEntityDaoImpl.2"),  //$NON-NLS-1$
					llistaCorreuUsuari.getLlistaDeCorreu().getNom(),
					llistaCorreuUsuari.getUsuari().getCodi(), 
					message));
        }
    }

    public void toLlistaCorreuUsuari(
            es.caib.seycon.ng.model.LlistaCorreuUsuariEntity source,
            es.caib.seycon.ng.comu.LlistaCorreuUsuari target) {
        super.toLlistaCorreuUsuari(source, target);
        toLlistaCorreuUsuariCustom(source, target);
    }

    public void toLlistaCorreuUsuariCustom(
            es.caib.seycon.ng.model.LlistaCorreuUsuariEntity source,
            es.caib.seycon.ng.comu.LlistaCorreuUsuari target) {
        LlistaCorreuEntity llistaCorreu = source.getLlistaDeCorreu();
        target.setNomLlistaCorreu(llistaCorreu.getNom());
        DominiCorreuEntity dominiCorreu = llistaCorreu.getDomini();
        if (dominiCorreu != null) {
            target.setCodiDomini(dominiCorreu.getCodi());
        }
        UsuariEntity usuari = source.getUsuari();
        target.setCodiUsuari(usuari.getCodi());
        String nomComplert = usuari.getNom() != null ? usuari.getNom() : ""; //$NON-NLS-1$
        nomComplert += usuari.getPrimerLlinatge() != null ? " " //$NON-NLS-1$
                + usuari.getPrimerLlinatge() : ""; //$NON-NLS-1$
        nomComplert += usuari.getSegonLlinatge() != null ? " " //$NON-NLS-1$
                + usuari.getSegonLlinatge() : ""; //$NON-NLS-1$
        target.setNomComplert(nomComplert);
    }

    /**
     * @see es.caib.seycon.ng.model.LlistaCorreuUsuariEntityDao#toLlistaCorreuUsuari(es.caib.seycon.ng.model.LlistaCorreuUsuariEntity)
     */
    public es.caib.seycon.ng.comu.LlistaCorreuUsuari toLlistaCorreuUsuari(
            final es.caib.seycon.ng.model.LlistaCorreuUsuariEntity entity) {
        return super.toLlistaCorreuUsuari(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.LlistaCorreuUsuariEntity loadLlistaCorreuUsuariEntityFromLlistaCorreuUsuari(
            es.caib.seycon.ng.comu.LlistaCorreuUsuari llistaCorreuUsuari) {
        LlistaCorreuUsuariEntity llistaCorreuUsuariEntity = null;
        if (llistaCorreuUsuari.getId() != null) {
            llistaCorreuUsuariEntity = load(llistaCorreuUsuari.getId());
        }
        if (llistaCorreuUsuariEntity == null) {
            llistaCorreuUsuariEntity = newLlistaCorreuUsuariEntity();
        }
        return llistaCorreuUsuariEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.LlistaCorreuUsuariEntityDao#llistaCorreuUsuariToEntity(es.caib.seycon.ng.comu.LlistaCorreuUsuari)
     */
    public es.caib.seycon.ng.model.LlistaCorreuUsuariEntity llistaCorreuUsuariToEntity(
            es.caib.seycon.ng.comu.LlistaCorreuUsuari llistaCorreuUsuari) {
        es.caib.seycon.ng.model.LlistaCorreuUsuariEntity entity = this
                .loadLlistaCorreuUsuariEntityFromLlistaCorreuUsuari(llistaCorreuUsuari);
        this.llistaCorreuUsuariToEntity(llistaCorreuUsuari, entity, true);
        return entity;
    }

    public void llistaCorreuUsuariToEntityCustom(
            es.caib.seycon.ng.comu.LlistaCorreuUsuari source,
            es.caib.seycon.ng.model.LlistaCorreuUsuariEntity target) {
        String codiUsuari = source.getCodiUsuari();
        UsuariEntity usuari = getUsuariEntityDao().findByCodi(codiUsuari);
        if (usuari != null) {
            target.setUsuari(usuari);
        } else {
			throw new SeyconException(String.format(Messages.getString("LlistaCorreuUsuariEntityDaoImpl.3"), codiUsuari)); //$NON-NLS-1$
        }

        String nomLlistaCorreu = source.getNomLlistaCorreu();
        String codiDomini = source.getCodiDomini();
        LlistaCorreuEntity llistaCorreu = getLlistaCorreuEntityDao()
                .findByNomAndCodiDomini(nomLlistaCorreu, codiDomini);
        if (llistaCorreu != null) {
            target.setLlistaDeCorreu(llistaCorreu);
        } else {
			throw new SeyconException(String.format(Messages.getString("LlistaCorreuUsuariEntityDaoImpl.4") //$NON-NLS-1$
					+ (codiDomini == null ? "" : Messages.getString("LlistaCorreuUsuariEntityDaoImpl.5")) + Messages.getString("LlistaCorreuUsuariEntityDaoImpl.6"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					nomLlistaCorreu, 
					codiDomini));
        }
    }

    /**
     * @see es.caib.seycon.ng.model.LlistaCorreuUsuariEntityDao#llistaCorreuUsuariToEntity(es.caib.seycon.ng.comu.LlistaCorreuUsuari,
     *      es.caib.seycon.ng.model.LlistaCorreuUsuariEntity)
     */
    public void llistaCorreuUsuariToEntity(
            es.caib.seycon.ng.comu.LlistaCorreuUsuari source,
            es.caib.seycon.ng.model.LlistaCorreuUsuariEntity target,
            boolean copyIfNull) {
        super.llistaCorreuUsuariToEntity(source, target, copyIfNull);
        llistaCorreuUsuariToEntityCustom(source, target);
    }

    public java.util.List find(
            final java.lang.String queryString,
            final es.caib.seycon.ng.model.Parameter[] parameters) {
        try {
            java.util.List results = new QueryBuilder().query(this,
                    queryString, parameters);
            return results;
        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof LlistaCorreuUsuariEntity) {
                    LlistaCorreuUsuariEntity entity = (LlistaCorreuUsuariEntity) obj;
                    this.create(entity); // cridem al mètode 1 per 1
                }
            }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof LlistaCorreuUsuariEntity) {
                    LlistaCorreuUsuariEntity entity = (LlistaCorreuUsuariEntity) obj;
                    this.update(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof LlistaCorreuUsuariEntity) {
                    LlistaCorreuUsuariEntity entity = (LlistaCorreuUsuariEntity) obj;
                    this.remove(entity);// cridem al mètode 1 per 1
                }
            }
    }

}
