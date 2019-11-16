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
import com.soffid.iam.model.ApplicationDomainEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * @see es.caib.seycon.ng.model.DominiAplicacioEntity
 */
public class ApplicationDomainEntityDaoImpl extends
        com.soffid.iam.model.ApplicationDomainEntityDaoBase {

    private void auditarDominiAplicacio(String accio, String codiDomini,
            String codiAplicacio) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setDomain(codiDomini);
        auditoria.setApplication(codiAplicacio);
        auditoria.setAuthor(codiUsuari);
        auditoria.setObject("SC_DOMAPP"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    public void create(com.soffid.iam.model.ApplicationDomainEntity dominiAplicacio) throws RuntimeException {
        try {
            super.create(dominiAplicacio);
            getSession(false).flush();
            InformationSystemEntity aplicacioEntity = dominiAplicacio.getInformationSystem();
            auditarDominiAplicacio("C", dominiAplicacio.getName(), aplicacioEntity == null ? null : aplicacioEntity.getName());
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

            throw new SeyconException(Messages.getString("ApplicationDomainEntityDaoImpl.4") + dominiAplicacio.getName() + "\'.\n" + message); //$NON-NLS-1$
        }
    }

    public void remove(com.soffid.iam.model.ApplicationDomainEntity dominiAplicacio) throws RuntimeException {
        try {
            String nomDominiAplicacio = dominiAplicacio.getName();
            InformationSystemEntity aplicacioEntity = dominiAplicacio.getInformationSystem();
            String codiAplicacio = aplicacioEntity == null ? null : aplicacioEntity.getName();
            super.remove(dominiAplicacio);
            getSession(false).flush();
            auditarDominiAplicacio("D", nomDominiAplicacio, codiAplicacio); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(Messages.getString("ApplicationDomainEntityDaoImpl.7") + dominiAplicacio.getName() + "\'.\n" + message); //$NON-NLS-1$
        }
    }

    public void update(com.soffid.iam.model.ApplicationDomainEntity dominiAplicacio) throws RuntimeException {
        try {
            super.update(dominiAplicacio);
            getSession(false).flush();
            InformationSystemEntity aplicacioEntity = dominiAplicacio.getInformationSystem();
            auditarDominiAplicacio("U", dominiAplicacio.getName(), aplicacioEntity == null ? null : aplicacioEntity.getName());
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

            throw new SeyconException(Messages.getString("ApplicationDomainEntityDaoImpl.10") + dominiAplicacio.getName() + "\'.\n" + message); //$NON-NLS-1$
        }
    }

    public void toDomain(com.soffid.iam.model.ApplicationDomainEntity source, com.soffid.iam.api.Domain target) {
        super.toDomain(source, target);
        toDominiCustom(source, target);
    }

    private void toDominiCustom(com.soffid.iam.model.ApplicationDomainEntity source, com.soffid.iam.api.Domain target) {
        target.setName(source.getName());
        InformationSystemEntity aplicacioEntity = source.getInformationSystem();
        if (aplicacioEntity != null) {
            target.setExternalCode(aplicacioEntity.getName());
        }
        target.setDescription(source.getDescription());
    }

    /**
     * @see es.caib.seycon.ng.model.DominiAplicacioEntityDao#toDomini(es.caib.seycon.ng.model.DominiAplicacioEntity)
     */
    public com.soffid.iam.api.Domain toDomain(final com.soffid.iam.model.ApplicationDomainEntity entity) {
        try {
            return super.toDomain(entity);
        } catch (SeyconException e) {
            // no és un domini d'aplicació
            return null;
        }
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.ApplicationDomainEntity loadDominiAplicacioEntityFromDomini(com.soffid.iam.api.Domain domini) {
        ApplicationDomainEntity dominiAplicacioEntity = null;
        if (domini.getId() != null) {
            dominiAplicacioEntity = load(domini.getId());
        }
        if (dominiAplicacioEntity == null) {
            dominiAplicacioEntity = newApplicationDomainEntity();
        }
        return dominiAplicacioEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.DominiAplicacioEntityDao#dominiToEntity(es.caib.seycon.ng.comu.Domini)
     */
    public com.soffid.iam.model.ApplicationDomainEntity domainToEntity(com.soffid.iam.api.Domain domini) {
        com.soffid.iam.model.ApplicationDomainEntity entity = this.loadDominiAplicacioEntityFromDomini(domini);
        this.domainToEntity(domini, entity, true);
        return entity;
    }

    public void dominiToEntityCustom(com.soffid.iam.api.Domain source, com.soffid.iam.model.ApplicationDomainEntity target) {
        String codiExtern = source.getExternalCode();
        if (codiExtern == null) {
            throw new SeyconException(Messages.getString("ApplicationDomainEntityDaoImpl.12")); //$NON-NLS-1$
        }
        InformationSystemEntity aplicacioEntity = getInformationSystemEntityDao().findByCode(codiExtern);
        if (aplicacioEntity == null) {
        	throw new SeyconException(String.format(Messages.getString("ApplicationDomainEntityDaoImpl.0"), codiExtern)); //$NON-NLS-1$
            //throw new SeyconException("Aplicacio amb codi '" + codiExtern //$NON-NLS-1$
            //      + "' no trobada."); //$NON-NLS-1$
        }
        target.setInformationSystem(aplicacioEntity);

        target.setName(source.getName());

        /*
         * String nomRol = source.getNomRol(); if (nomRol != null) { RolEntity
         * rolEntity = getRolEntityDao().findByNom(nomRol); if (rolEntity ==
         * null) { throw new SeyconException("Rol amb nom '" + nomRol +
         * "' no trobat."); } else { AplicacioEntity apliacacioEntity =
         * rolEntity.getAplicacio(); if
         * (aplicacioEntity.getCodi().compareTo(codiExtern) != 0) { throw new
         * SeyconException( "El domini és de l'aplicació amb codi '" +
         * codiExtern + "' i el rol de l'aplicació amb codi '" +
         * aplicacioEntity.getCodi() + "'."); } } target.setRol(rolEntity);
         * rolEntity.setTipusDomini(TipusDomini.DOMINI_APLICACIO); }
         */
    }

    /**
     * @see es.caib.seycon.ng.model.DominiAplicacioEntityDao#dominiToEntity(es.caib.seycon.ng.comu.Domini,
     *      es.caib.seycon.ng.model.DominiAplicacioEntity)
     */
    public void domainToEntity(com.soffid.iam.api.Domain source, com.soffid.iam.model.ApplicationDomainEntity target, boolean copyIfNull) {
        super.domainToEntity(source, target, copyIfNull);
        dominiToEntityCustom(source, target);
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof ApplicationDomainEntity) {
                ApplicationDomainEntity entity = (ApplicationDomainEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof ApplicationDomainEntity) {
                ApplicationDomainEntity entity = (ApplicationDomainEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof ApplicationDomainEntity) {
                ApplicationDomainEntity entity = (ApplicationDomainEntity) obj;
                this.remove(entity);
            }
        }
    }
}
