// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.DominiAplicacioEntity
 */
public class DominiAplicacioEntityDaoImpl extends
        es.caib.seycon.ng.model.DominiAplicacioEntityDaoBase {

    private void auditarDominiAplicacio(String accio, String codiDomini,
            String codiAplicacio) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setDomini(codiDomini);
        auditoria.setAplicacio(codiAplicacio);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_DOMAPP"); //$NON-NLS-1$
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
                .auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    public void create(
            es.caib.seycon.ng.model.DominiAplicacioEntity dominiAplicacio)
            throws RuntimeException {
        try {
            super.create(dominiAplicacio);
            getSession(false).flush();
            AplicacioEntity aplicacioEntity = dominiAplicacio.getAplicacio();
            auditarDominiAplicacio("C", dominiAplicacio.getNom(), //$NON-NLS-1$
                    aplicacioEntity == null ? null : aplicacioEntity.getCodi());
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

            throw new SeyconException(
                    Messages.getString("DominiAplicacioEntityDaoImpl.4") //$NON-NLS-1$
                            + dominiAplicacio.getNom() + "'.\n" + message); //$NON-NLS-1$
        }
    }

    public void remove(
            es.caib.seycon.ng.model.DominiAplicacioEntity dominiAplicacio)
            throws RuntimeException {
        try {
            String nomDominiAplicacio = dominiAplicacio.getNom();
            AplicacioEntity aplicacioEntity = dominiAplicacio.getAplicacio();
            String codiAplicacio = aplicacioEntity == null ? null
                    : aplicacioEntity.getCodi();
            super.remove(dominiAplicacio);
            getSession(false).flush();
            auditarDominiAplicacio("D", nomDominiAplicacio, codiAplicacio); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(
                    Messages.getString("DominiAplicacioEntityDaoImpl.7") //$NON-NLS-1$
                            + dominiAplicacio.getNom() + "'.\n" + message); //$NON-NLS-1$
        }
    }

    public void update(
            es.caib.seycon.ng.model.DominiAplicacioEntity dominiAplicacio)
            throws RuntimeException {
        try {
            super.update(dominiAplicacio);
            getSession(false).flush();
            AplicacioEntity aplicacioEntity = dominiAplicacio.getAplicacio();
            auditarDominiAplicacio("U", dominiAplicacio.getNom(), //$NON-NLS-1$
                    aplicacioEntity == null ? null : aplicacioEntity.getCodi());
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

            throw new SeyconException(
                    Messages.getString("DominiAplicacioEntityDaoImpl.10") //$NON-NLS-1$
                            + dominiAplicacio.getNom() + "'.\n" + message); //$NON-NLS-1$
        }
    }

    public void toDomini(es.caib.seycon.ng.model.DominiAplicacioEntity source,
            es.caib.seycon.ng.comu.Domini target) {
        super.toDomini(source, target);
        toDominiCustom(source, target);
    }

    private void toDominiCustom(
            es.caib.seycon.ng.model.DominiAplicacioEntity source,
            es.caib.seycon.ng.comu.Domini target) {
        target.setNom(source.getNom());
        AplicacioEntity aplicacioEntity = source.getAplicacio();
        if (aplicacioEntity != null) {
            target.setCodiExtern(aplicacioEntity.getCodi());
        }
        target.setDescripcio(source.getDescripcio());
    }

    /**
     * @see es.caib.seycon.ng.model.DominiAplicacioEntityDao#toDomini(es.caib.seycon.ng.model.DominiAplicacioEntity)
     */
    public es.caib.seycon.ng.comu.Domini toDomini(
            final es.caib.seycon.ng.model.DominiAplicacioEntity entity) {
        try {
            return super.toDomini(entity);
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
    private es.caib.seycon.ng.model.DominiAplicacioEntity loadDominiAplicacioEntityFromDomini(
            es.caib.seycon.ng.comu.Domini domini) {
        DominiAplicacioEntity dominiAplicacioEntity = null;
        if (domini.getId() != null) {
            dominiAplicacioEntity = load(domini.getId());
        }
        if (dominiAplicacioEntity == null) {
            dominiAplicacioEntity = newDominiAplicacioEntity();
        }
        return dominiAplicacioEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.DominiAplicacioEntityDao#dominiToEntity(es.caib.seycon.ng.comu.Domini)
     */
    public es.caib.seycon.ng.model.DominiAplicacioEntity dominiToEntity(
            es.caib.seycon.ng.comu.Domini domini) {
        es.caib.seycon.ng.model.DominiAplicacioEntity entity = this
                .loadDominiAplicacioEntityFromDomini(domini);
        this.dominiToEntity(domini, entity, true);
        return entity;
    }

    public void dominiToEntityCustom(es.caib.seycon.ng.comu.Domini source,
            es.caib.seycon.ng.model.DominiAplicacioEntity target) {
        String codiExtern = source.getCodiExtern();
        if (codiExtern == null) {
            throw new SeyconException(Messages.getString("DominiAplicacioEntityDaoImpl.12")); //$NON-NLS-1$
        }
        AplicacioEntity aplicacioEntity = getAplicacioEntityDao().findByCodi(
                codiExtern);
        if (aplicacioEntity == null) {
        	throw new SeyconException(String.format(Messages.getString("DominiAplicacioEntityDaoImpl.0"), codiExtern)); //$NON-NLS-1$
            //throw new SeyconException("Aplicacio amb codi '" + codiExtern //$NON-NLS-1$
            //      + "' no trobada."); //$NON-NLS-1$
        }
        target.setAplicacio(aplicacioEntity);

        target.setNom(source.getNom());

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
    public void dominiToEntity(es.caib.seycon.ng.comu.Domini source,
            es.caib.seycon.ng.model.DominiAplicacioEntity target,
            boolean copyIfNull) {
        super.dominiToEntity(source, target, copyIfNull);
        dominiToEntityCustom(source, target);
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#find(int, java.lang.String,
     *      es.caib.seycon.ng.model.Parameter[])
     */
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
                if (obj instanceof DominiAplicacioEntity) {
                    DominiAplicacioEntity entity = (DominiAplicacioEntity) obj;
                    this.create(entity); // cridem al mètode 1 per 1
                }
            }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof DominiAplicacioEntity) {
                    DominiAplicacioEntity entity = (DominiAplicacioEntity) obj;
                    this.update(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof DominiAplicacioEntity) {
                    DominiAplicacioEntity entity = (DominiAplicacioEntity) obj;
                    this.remove(entity);// cridem al mètode 1 per 1
                }
            }
    }
}
