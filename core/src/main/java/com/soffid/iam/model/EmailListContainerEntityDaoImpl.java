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
import com.soffid.iam.model.EmailDomainEntity;
import com.soffid.iam.model.EmailListContainerEntity;
import com.soffid.iam.model.EmailListEntity;
import com.soffid.iam.model.TaskEntity;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.RelacioLlistaCorreu;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * @see es.caib.seycon.ng.model.RelacioLlistaCorreuEntity
 */
public class EmailListContainerEntityDaoImpl extends
        com.soffid.iam.model.EmailListContainerEntityDaoBase {

    public void create(com.soffid.iam.model.EmailListContainerEntity relacioLlistaCorreu) throws RuntimeException {
        try {
            EmailDomainEntity dominiCorreuEntity = relacioLlistaCorreu.getContain().getDomain();
            if (dominiCorreuEntity != null) {
                String obsolet = dominiCorreuEntity.getObsolete();
                if (obsolet != null && obsolet.compareTo("S") == 0) { //$NON-NLS-1$
					throw new SeyconException(String.format(Messages.getString("EmailListContainerEntityDaoImpl.0"), relacioLlistaCorreu.getContain().getName(), dominiCorreuEntity.getCode()));
                }
            }

            dominiCorreuEntity = relacioLlistaCorreu.getPertain().getDomain();
            if (dominiCorreuEntity != null) {
                String obsolet = dominiCorreuEntity.getObsolete();
                if (obsolet != null && obsolet.compareTo("S") == 0) { //$NON-NLS-1$
					throw new SeyconException(String.format(Messages.getString("EmailListContainerEntityDaoImpl.1"), relacioLlistaCorreu.getPertain().getName(), dominiCorreuEntity.getCode()));
                }
            }

            super.create(relacioLlistaCorreu);
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_LIST_ALIAS);
            tasque.setAlias(relacioLlistaCorreu.getContain().getName());
            if (relacioLlistaCorreu.getContain().getDomain() != null)
                tasque.setMailDomain(relacioLlistaCorreu.getContain().getDomain().getCode());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();

            // Creem auditoria
            auditarLlistaDeCorreu("C", relacioLlistaCorreu.getContain().getName(), relacioLlistaCorreu.getContain().getDomain() != null ? relacioLlistaCorreu.getContain().getDomain().getCode() : "", relacioLlistaCorreu.getPertain().getName(), relacioLlistaCorreu.getPertain().getDomain() != null ? relacioLlistaCorreu.getPertain().getDomain().getCode() : ""); //$NON-NLS-1$

        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("EmailListContainerEntityDaoImpl.2"), relacioLlistaCorreu.getContain().getName(), relacioLlistaCorreu.getPertain().getName(), message));
        }
    }

    public void remove(com.soffid.iam.model.EmailListContainerEntity relacioLlistaCorreu) throws RuntimeException {
        try {
            String nomLListaConte = relacioLlistaCorreu.getContain().getName();
            String dominiLlistaConte = relacioLlistaCorreu.getContain().getDomain() != null ? relacioLlistaCorreu.getContain().getDomain().getCode() : ""; //$NON-NLS-1$
            String nomLlistaPertany = relacioLlistaCorreu.getPertain().getName();
            String dominiLlistaPertany = relacioLlistaCorreu.getPertain().getDomain() != null ? relacioLlistaCorreu.getPertain().getDomain().getCode() : ""; //$NON-NLS-1$

            super.remove(relacioLlistaCorreu);
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_LIST_ALIAS);
            tasque.setAlias(relacioLlistaCorreu.getContain().getName());
            if (relacioLlistaCorreu.getContain().getDomain() != null)
                tasque.setMailDomain(relacioLlistaCorreu.getContain().getDomain().getCode());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();

            // Creem auditoria
            auditarLlistaDeCorreu("D", nomLListaConte, dominiLlistaConte, //$NON-NLS-1$
                    nomLlistaPertany, dominiLlistaPertany);
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("EmailListContainerEntityDaoImpl.3"), relacioLlistaCorreu.getContain().getName(), relacioLlistaCorreu.getPertain().getName(), message));
        }
    }

    public void toRelacioLlistaCorreu(com.soffid.iam.model.EmailListContainerEntity source, es.caib.seycon.ng.comu.RelacioLlistaCorreu target) {
        super.toRelacioLlistaCorreu(source, target);
        toRelacioLlistaCorreuCustom(source, target);
    }

    public void toRelacioLlistaCorreuCustom(com.soffid.iam.model.EmailListContainerEntity source, es.caib.seycon.ng.comu.RelacioLlistaCorreu target) {
        target.setNomLlistaCorreuConte(source.getContain().getName());
        target.setNomLlistaCorreuPertany(source.getPertain().getName());
        EmailDomainEntity dominiCorreuConte = source.getContain().getDomain();
        EmailDomainEntity dominiCorreuPertany = source.getPertain().getDomain();
        if (dominiCorreuConte != null) {
            target.setCodiDominiCorreuConte(dominiCorreuConte.getCode());
        }
        if (dominiCorreuPertany != null) {
            target.setCodiDominiCorreuPertany(dominiCorreuPertany.getCode());
        }
    }

    /**
     * @see es.caib.seycon.ng.model.RelacioLlistaCorreuEntityDao#toRelacioLlistaCorreu(es.caib.seycon.ng.model.RelacioLlistaCorreuEntity)
     */
    public es.caib.seycon.ng.comu.RelacioLlistaCorreu toRelacioLlistaCorreu(final com.soffid.iam.model.EmailListContainerEntity entity) {
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
    private com.soffid.iam.model.EmailListContainerEntity loadRelacioLlistaCorreuEntityFromRelacioLlistaCorreu(es.caib.seycon.ng.comu.RelacioLlistaCorreu relacioLlistaCorreu) {
        com.soffid.iam.model.EmailListContainerEntity relacioLlistaCorreuEntity = null;
        if (relacioLlistaCorreu.getId() != null) {
            relacioLlistaCorreuEntity = load(relacioLlistaCorreu.getId());
        }
        if (relacioLlistaCorreuEntity == null) {
            relacioLlistaCorreuEntity = newEmailListContainerEntity();
        }
        return relacioLlistaCorreuEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.RelacioLlistaCorreuEntityDao#relacioLlistaCorreuToEntity(es.caib.seycon.ng.comu.RelacioLlistaCorreu)
     */
    public com.soffid.iam.model.EmailListContainerEntity relacioLlistaCorreuToEntity(es.caib.seycon.ng.comu.RelacioLlistaCorreu relacioLlistaCorreu) {
        com.soffid.iam.model.EmailListContainerEntity entity = this.loadRelacioLlistaCorreuEntityFromRelacioLlistaCorreu(relacioLlistaCorreu);
        this.relacioLlistaCorreuToEntity(relacioLlistaCorreu, entity, true);
        return entity;
    }

    public void relacioLlistaCorreuToEntityCustom(es.caib.seycon.ng.comu.RelacioLlistaCorreu source, com.soffid.iam.model.EmailListContainerEntity target) {
        String nomLlistaCorreuConte = source.getNomLlistaCorreuConte();
        String codiDominiConte = source.getCodiDominiCorreuConte();
        EmailListEntity llistaCorreuEntityConte = getEmailListEntityDao().findByNameAndDomainCode(nomLlistaCorreuConte, codiDominiConte);
        if (llistaCorreuEntityConte != null) {
            target.setContain(llistaCorreuEntityConte);
        } else {
			throw new SeyconException(String.format(Messages.getString("EmailListContainerEntityDaoImpl.4"), //$NON-NLS-1$
					nomLlistaCorreuConte, 
					codiDominiConte));
        }

        String nomLlistaCorreuPertany = source.getNomLlistaCorreuPertany();
        String codiDominiPertany = source.getCodiDominiCorreuPertany();
        EmailListEntity llistaCorreuEntityPertany = getEmailListEntityDao().findByNameAndDomainCode(nomLlistaCorreuPertany, "null".equals(codiDominiPertany) ? null : codiDominiPertany);
        if (llistaCorreuEntityPertany != null) {
            target.setPertain(llistaCorreuEntityPertany);
        } else {
			throw new SeyconException(String.format(Messages.getString("EmailListContainerEntityDaoImpl.5"),  //$NON-NLS-1$
					nomLlistaCorreuPertany));
        }
    }

    /**
     * @see es.caib.seycon.ng.model.RelacioLlistaCorreuEntityDao#relacioLlistaCorreuToEntity(es.caib.seycon.ng.comu.RelacioLlistaCorreu,
     *      es.caib.seycon.ng.model.RelacioLlistaCorreuEntity)
     */
    public void relacioLlistaCorreuToEntity(es.caib.seycon.ng.comu.RelacioLlistaCorreu source, com.soffid.iam.model.EmailListContainerEntity target, boolean copyIfNull) {
        super.relacioLlistaCorreuToEntity(source, target, copyIfNull);
        relacioLlistaCorreuToEntityCustom(source, target);
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof EmailListContainerEntity) {
                EmailListContainerEntity entity = (EmailListContainerEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof EmailListContainerEntity) {
                EmailListContainerEntity entity = (EmailListContainerEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof EmailListContainerEntity) {
                EmailListContainerEntity entity = (EmailListContainerEntity) obj;
                this.remove(entity);
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
        AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    public void update(EmailListContainerEntity relacioLlistaCorreuEntity) {
        String nomLListaConte = relacioLlistaCorreuEntity.getContain().getName();
        String dominiLlistaConte = relacioLlistaCorreuEntity.getContain().getDomain() != null ? relacioLlistaCorreuEntity.getContain().getDomain().getCode() : ""; //$NON-NLS-1$
        String nomLlistaPertany = relacioLlistaCorreuEntity.getPertain().getName();
        String dominiLlistaPertany = relacioLlistaCorreuEntity.getPertain().getDomain() != null ? relacioLlistaCorreuEntity.getPertain().getDomain().getCode() : ""; //$NON-NLS-1$

        try {
            super.update(relacioLlistaCorreuEntity);
            getSession(false).flush();

            // Creem auditoria
            auditarLlistaDeCorreu("U", nomLListaConte, dominiLlistaConte, //$NON-NLS-1$
                    nomLlistaPertany, dominiLlistaPertany);
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("EmailListContainerEntityDaoImpl.6"), nomLListaConte, nomLlistaPertany, message)); //$NON-NLS-1$
        }

    }

}
