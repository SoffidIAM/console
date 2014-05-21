// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.RelacioLlistaCorreu;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.RelacioLlistaCorreuEntity
 */
public class RelacioLlistaCorreuEntityDaoImpl extends
        es.caib.seycon.ng.model.RelacioLlistaCorreuEntityDaoBase {

    public void create(
            es.caib.seycon.ng.model.RelacioLlistaCorreuEntity relacioLlistaCorreu)
            throws RuntimeException {
        try {
            DominiCorreuEntity dominiCorreuEntity = relacioLlistaCorreu
                    .getConte().getDomini();
            if (dominiCorreuEntity != null) {
                String obsolet = dominiCorreuEntity.getObsolet();
                if (obsolet != null && obsolet.compareTo("S") == 0) { //$NON-NLS-1$
					throw new SeyconException(String.format(
							Messages.getString("RelacioLlistaCorreuEntityDaoImpl.0"), //$NON-NLS-1$
							relacioLlistaCorreu.getConte().getNom(), 
							dominiCorreuEntity.getCodi()));
                }
            }

            dominiCorreuEntity = relacioLlistaCorreu.getPertany().getDomini();
            if (dominiCorreuEntity != null) {
                String obsolet = dominiCorreuEntity.getObsolet();
                if (obsolet != null && obsolet.compareTo("S") == 0) { //$NON-NLS-1$
					throw new SeyconException(String.format(
							Messages.getString("RelacioLlistaCorreuEntityDaoImpl.1"), //$NON-NLS-1$
							relacioLlistaCorreu.getPertany().getNom(), 
							dominiCorreuEntity.getCodi()));
                }
            }

            super.create(relacioLlistaCorreu);
            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_LIST_ALIAS);
            tasque.setAlies(relacioLlistaCorreu.getConte().getNom());
            if (relacioLlistaCorreu.getConte().getDomini() != null)
                tasque.setDomcor(relacioLlistaCorreu.getConte().getDomini().getCodi());
            getTasqueEntityDao().create(tasque);
            getSession(false).flush();

            // Creem auditoria
            auditarLlistaDeCorreu(
                    "C", //$NON-NLS-1$
                    relacioLlistaCorreu.getConte().getNom(),
                    relacioLlistaCorreu.getConte().getDomini() != null ? relacioLlistaCorreu
                            .getConte().getDomini().getCodi()
                            : "", //$NON-NLS-1$
                    relacioLlistaCorreu.getPertany().getNom(),
                    relacioLlistaCorreu.getPertany().getDomini() != null ? relacioLlistaCorreu
                            .getPertany().getDomini().getCodi()
                            : ""); //$NON-NLS-1$

        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RelacioLlistaCorreuEntityDaoImpl.2"),  //$NON-NLS-1$
					relacioLlistaCorreu.getConte().getNom(), 
					relacioLlistaCorreu.getPertany().getNom(), 
					message));
        }
    }

    public void remove(
            es.caib.seycon.ng.model.RelacioLlistaCorreuEntity relacioLlistaCorreu)
            throws RuntimeException {
        try {
            String nomLListaConte = relacioLlistaCorreu.getConte().getNom();
            String dominiLlistaConte = relacioLlistaCorreu.getConte()
                    .getDomini() != null ? relacioLlistaCorreu.getConte()
                    .getDomini().getCodi() : ""; //$NON-NLS-1$
            String nomLlistaPertany = relacioLlistaCorreu.getPertany().getNom();
            String dominiLlistaPertany = relacioLlistaCorreu.getPertany()
                    .getDomini() != null ? relacioLlistaCorreu.getPertany()
                    .getDomini().getCodi() : ""; //$NON-NLS-1$

            super.remove(relacioLlistaCorreu);
            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_LIST_ALIAS);
            tasque.setAlies(relacioLlistaCorreu.getConte().getNom());
            if (relacioLlistaCorreu.getConte().getDomini() != null)
                tasque.setDomcor(relacioLlistaCorreu.getConte().getDomini().getCodi());
            getTasqueEntityDao().create(tasque);
            getSession(false).flush();

            // Creem auditoria
            auditarLlistaDeCorreu("D", nomLListaConte, dominiLlistaConte, //$NON-NLS-1$
                    nomLlistaPertany, dominiLlistaPertany);
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RelacioLlistaCorreuEntityDaoImpl.3"),  //$NON-NLS-1$
					relacioLlistaCorreu.getConte().getNom(), 
					relacioLlistaCorreu.getPertany().getNom(), 
					message));
        }
    }

    public void toRelacioLlistaCorreu(
            es.caib.seycon.ng.model.RelacioLlistaCorreuEntity source,
            es.caib.seycon.ng.comu.RelacioLlistaCorreu target) {
        super.toRelacioLlistaCorreu(source, target);
        toRelacioLlistaCorreuCustom(source, target);
    }

    public void toRelacioLlistaCorreuCustom(
            es.caib.seycon.ng.model.RelacioLlistaCorreuEntity source,
            es.caib.seycon.ng.comu.RelacioLlistaCorreu target) {
        target.setNomLlistaCorreuConte(source.getConte().getNom());
        target.setNomLlistaCorreuPertany(source.getPertany().getNom());
        DominiCorreuEntity dominiCorreuConte = source.getConte().getDomini();
        DominiCorreuEntity dominiCorreuPertany = source.getPertany()
                .getDomini();
        if (dominiCorreuConte != null) {
            target.setCodiDominiCorreuConte(dominiCorreuConte.getCodi());
        }
        if (dominiCorreuPertany != null) {
            target.setCodiDominiCorreuPertany(dominiCorreuPertany.getCodi());
        }
    }

    /**
     * @see es.caib.seycon.ng.model.RelacioLlistaCorreuEntityDao#toRelacioLlistaCorreu(es.caib.seycon.ng.model.RelacioLlistaCorreuEntity)
     */
    public es.caib.seycon.ng.comu.RelacioLlistaCorreu toRelacioLlistaCorreu(
            final es.caib.seycon.ng.model.RelacioLlistaCorreuEntity entity) {
        RelacioLlistaCorreu relacioLlistaCorreu = super
                .toRelacioLlistaCorreu(entity);
        toRelacioLlistaCorreuCustom(entity, relacioLlistaCorreu);
        return relacioLlistaCorreu;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.RelacioLlistaCorreuEntity loadRelacioLlistaCorreuEntityFromRelacioLlistaCorreu(
            es.caib.seycon.ng.comu.RelacioLlistaCorreu relacioLlistaCorreu) {
        es.caib.seycon.ng.model.RelacioLlistaCorreuEntity relacioLlistaCorreuEntity = null;
        if (relacioLlistaCorreu.getId() != null) {
            relacioLlistaCorreuEntity = load(relacioLlistaCorreu.getId());
        }
        if (relacioLlistaCorreuEntity == null) {
            relacioLlistaCorreuEntity = newRelacioLlistaCorreuEntity();
        }
        return relacioLlistaCorreuEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.RelacioLlistaCorreuEntityDao#relacioLlistaCorreuToEntity(es.caib.seycon.ng.comu.RelacioLlistaCorreu)
     */
    public es.caib.seycon.ng.model.RelacioLlistaCorreuEntity relacioLlistaCorreuToEntity(
            es.caib.seycon.ng.comu.RelacioLlistaCorreu relacioLlistaCorreu) {
        es.caib.seycon.ng.model.RelacioLlistaCorreuEntity entity = this
                .loadRelacioLlistaCorreuEntityFromRelacioLlistaCorreu(relacioLlistaCorreu);
        this.relacioLlistaCorreuToEntity(relacioLlistaCorreu, entity, true);
        return entity;
    }

    public void relacioLlistaCorreuToEntityCustom(
            es.caib.seycon.ng.comu.RelacioLlistaCorreu source,
            es.caib.seycon.ng.model.RelacioLlistaCorreuEntity target) {
        String nomLlistaCorreuConte = source.getNomLlistaCorreuConte();
        String codiDominiConte = source.getCodiDominiCorreuConte();
        LlistaCorreuEntity llistaCorreuEntityConte = getLlistaCorreuEntityDao()
                .findByNomAndCodiDomini(nomLlistaCorreuConte, codiDominiConte);
        if (llistaCorreuEntityConte != null) {
            target.setConte(llistaCorreuEntityConte);
        } else {
			throw new SeyconException(String.format(Messages.getString("RelacioLlistaCorreuEntityDaoImpl.4"), //$NON-NLS-1$
					nomLlistaCorreuConte, 
					codiDominiConte));
        }

        String nomLlistaCorreuPertany = source.getNomLlistaCorreuPertany();
        String codiDominiPertany = source.getCodiDominiCorreuPertany();
        LlistaCorreuEntity llistaCorreuEntityPertany = getLlistaCorreuEntityDao()
                .findByNomAndCodiDomini(
                        nomLlistaCorreuPertany,
                        "null".equals(codiDominiPertany) ? null //$NON-NLS-1$
                                : codiDominiPertany);
        if (llistaCorreuEntityPertany != null) {
            target.setPertany(llistaCorreuEntityPertany);
        } else {
			throw new SeyconException(String.format(Messages.getString("RelacioLlistaCorreuEntityDaoImpl.5"),  //$NON-NLS-1$
					nomLlistaCorreuPertany));
        }
    }

    /**
     * @see es.caib.seycon.ng.model.RelacioLlistaCorreuEntityDao#relacioLlistaCorreuToEntity(es.caib.seycon.ng.comu.RelacioLlistaCorreu,
     *      es.caib.seycon.ng.model.RelacioLlistaCorreuEntity)
     */
    public void relacioLlistaCorreuToEntity(
            es.caib.seycon.ng.comu.RelacioLlistaCorreu source,
            es.caib.seycon.ng.model.RelacioLlistaCorreuEntity target,
            boolean copyIfNull) {
        super.relacioLlistaCorreuToEntity(source, target, copyIfNull);
        relacioLlistaCorreuToEntityCustom(source, target);
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof RelacioLlistaCorreuEntity) {
                    RelacioLlistaCorreuEntity entity = (RelacioLlistaCorreuEntity) obj;
                    this.create(entity); // cridem al mètode 1 per 1
                }
            }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof RelacioLlistaCorreuEntity) {
                    RelacioLlistaCorreuEntity entity = (RelacioLlistaCorreuEntity) obj;
                    this.update(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof RelacioLlistaCorreuEntity) {
                    RelacioLlistaCorreuEntity entity = (RelacioLlistaCorreuEntity) obj;
                    this.remove(entity);// cridem al mètode 1 per 1
                }
            }
    }

    private void auditarLlistaDeCorreu(String accio, String nomLlistaConte,
            String dominiLlistaConte, String nomLlistaPertany,
            String nomDominiPertany) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        // Afegim les dues llistas
        // la contenidora
        auditoria.setLlistaCorreu(nomLlistaConte);
        auditoria.setDomini(dominiLlistaConte);
        // i la continguda (o que pertany)
        auditoria.setLlistaCorreuPertany(nomLlistaPertany);
        auditoria.setDominiCorreuPertany(nomDominiPertany);

        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_LCOLCO"); //$NON-NLS-1$
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
                .auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    public void update(RelacioLlistaCorreuEntity relacioLlistaCorreuEntity) {
        String nomLListaConte = relacioLlistaCorreuEntity.getConte().getNom();
        String dominiLlistaConte = relacioLlistaCorreuEntity.getConte()
                .getDomini() != null ? relacioLlistaCorreuEntity.getConte()
                .getDomini().getCodi() : ""; //$NON-NLS-1$
        String nomLlistaPertany = relacioLlistaCorreuEntity.getPertany()
                .getNom();
        String dominiLlistaPertany = relacioLlistaCorreuEntity.getPertany()
                .getDomini() != null ? relacioLlistaCorreuEntity.getPertany()
                .getDomini().getCodi() : ""; //$NON-NLS-1$

        try {
            super.update(relacioLlistaCorreuEntity);
            getSession(false).flush();

            // Creem auditoria
            auditarLlistaDeCorreu("U", nomLListaConte, dominiLlistaConte, //$NON-NLS-1$
                    nomLlistaPertany, dominiLlistaPertany);
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RelacioLlistaCorreuEntityDaoImpl.6"), nomLListaConte, nomLlistaPertany, message)); //$NON-NLS-1$
        }

    }

}
