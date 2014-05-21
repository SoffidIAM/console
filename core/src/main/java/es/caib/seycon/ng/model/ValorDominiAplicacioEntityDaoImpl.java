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
import java.util.List;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.ValorDominiAplicacioEntity
 */
public class ValorDominiAplicacioEntityDaoImpl extends
        es.caib.seycon.ng.model.ValorDominiAplicacioEntityDaoBase {

    private void auditarValorDominiAplicacio(String accio,
            String codiAplicacio, String nomDomini, String valorDomini) {
        String codiUsuari = Security.getCurrentAccount(); //$NON-NLS-1$
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setAplicacio(codiAplicacio);
        auditoria.setDomini(nomDomini);
        auditoria.setValorDomini(valorDomini);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_VALDOMAPP"); //$NON-NLS-1$
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
                .auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    public void create(
            es.caib.seycon.ng.model.ValorDominiAplicacioEntity valorDomini)
            throws RuntimeException {
        try {
            super.create(valorDomini);
            getSession(false).flush();
            String codiAplicacio = null;
            DominiAplicacioEntity domini = valorDomini.getDomini();
            if (domini != null) {
                AplicacioEntity aplicacio = domini.getAplicacio();
                if (aplicacio != null) {
                    codiAplicacio = aplicacio.getCodi();
                }
            }
            String nomDomini = valorDomini.getDomini().getNom();
            String valorDominiString = valorDomini.getValor();
            auditarValorDominiAplicacio("C", codiAplicacio, nomDomini, //$NON-NLS-1$
                    valorDominiString);
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ValorDominiAplicacioEntityDaoImpl.errorCreating"), //$NON-NLS-1$
					valorDomini.getValor(), message));
        }
    }

    public void update(
            es.caib.seycon.ng.model.ValorDominiAplicacioEntity valorDomini)
            throws RuntimeException {
        try {
            super.update(valorDomini);
            getSession(false).flush();
            String codiAplicacio = null;
            DominiAplicacioEntity domini = valorDomini.getDomini();
            if (domini != null) {
                AplicacioEntity aplicacio = domini.getAplicacio();
                if (aplicacio != null) {
                    codiAplicacio = aplicacio.getCodi();
                }
            }
            String nomDomini = valorDomini.getDomini().getNom();
            String valorDominiString = valorDomini.getValor();
            auditarValorDominiAplicacio("U", codiAplicacio, nomDomini, //$NON-NLS-1$
                    valorDominiString);
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ValorDominiAplicacioEntityDaoImpl.errorUpdating"), //$NON-NLS-1$
					valorDomini.getValor(), message));
        }
    }

    public void remove(
            es.caib.seycon.ng.model.ValorDominiAplicacioEntity valorDomini)
            throws RuntimeException {
        try {
            String codiAplicacio = null;
            DominiAplicacioEntity domini = valorDomini.getDomini();
            if (domini != null) {
                AplicacioEntity aplicacio = domini.getAplicacio();
                if (aplicacio != null) {
                    codiAplicacio = aplicacio.getCodi();
                }
            }
            String nomDomini = valorDomini.getDomini().getNom();
            String valorDominiString = valorDomini.getValor();
            super.remove(valorDomini);
            getSession(false).flush();
            auditarValorDominiAplicacio("D", codiAplicacio, nomDomini, //$NON-NLS-1$
                    valorDominiString);
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ValorDominiAplicacioEntityDaoImpl.errorCreating"), //$NON-NLS-1$
					valorDomini.getValor(), message));
        }
    }

    public void toValorDomini(
            es.caib.seycon.ng.model.ValorDominiAplicacioEntity source,
            es.caib.seycon.ng.comu.ValorDomini target) {
        super.toValorDomini(source, target);
        target.setDescripcio(source.getDescripcio());
        DominiAplicacioEntity dominiEntity = source.getDomini();
        Domini domini = getDominiAplicacioEntityDao().toDomini(dominiEntity);
        if (domini == null) {
            // el domini no és d'aplicació
            throw new SeyconException(Messages.getString("ValorDominiAplicacioEntityDaoImpl.invalidDomain")); //$NON-NLS-1$
        } else {
            target.setNomDomini(domini.getNom());
            target.setValor(source.getValor());
            target.setCodiExternDomini(domini.getCodiExtern());
            target.setDescripcio(source.getDescripcio());
        }
    }

    /**
     * @see es.caib.seycon.ng.model.ValorDominiAplicacioEntityDao#toValorDomini(es.caib.seycon.ng.model.ValorDominiAplicacioEntity)
     */
    public es.caib.seycon.ng.comu.ValorDomini toValorDomini(
            final es.caib.seycon.ng.model.ValorDominiAplicacioEntity entity) {
        try {
            return super.toValorDomini(entity);
        } catch (SeyconException e) {
            // el valor del domini no és d'aplicació
            return null;
        }
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.ValorDominiAplicacioEntity loadValorDominiAplicacioEntityFromValorDomini(
            es.caib.seycon.ng.comu.ValorDomini valorDomini) {
        ValorDominiAplicacioEntity valorDominiAplicacioEntity = null;
        if (valorDomini.getId() != null) {
            valorDominiAplicacioEntity = this.load(valorDomini.getId());
        }
        if (valorDominiAplicacioEntity == null) {
            valorDominiAplicacioEntity = newValorDominiAplicacioEntity();
        }
        return valorDominiAplicacioEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.ValorDominiAplicacioEntityDao#valorDominiToEntity(es.caib.seycon.ng.comu.ValorDomini)
     */
    public es.caib.seycon.ng.model.ValorDominiAplicacioEntity valorDominiToEntity(
            es.caib.seycon.ng.comu.ValorDomini valorDomini) {
        es.caib.seycon.ng.model.ValorDominiAplicacioEntity entity = this
                .loadValorDominiAplicacioEntityFromValorDomini(valorDomini);
        this.valorDominiToEntity(valorDomini, entity, true);
        return entity;
    }

    public void valorDominiToEntityCustom(
            es.caib.seycon.ng.comu.ValorDomini source,
            es.caib.seycon.ng.model.ValorDominiAplicacioEntity target) {
        target.setDescripcio(source.getDescripcio());
        target.setValor(source.getValor());
        String nom = source.getNomDomini();
        String codiAplicacio = source.getCodiExternDomini();
        String query = "select domini " //$NON-NLS-1$
                + "from es.caib.seycon.ng.model.DominiAplicacioEntity domini " //$NON-NLS-1$
                + "left join domini.aplicacio aplicacio " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "((:codiAplicacio is null and aplicacio is null) or (aplicacio.codi = :codiAplicacio)) and " //$NON-NLS-1$
                + "domini.nom = :nom"; //$NON-NLS-1$
        Parameter codiAplicacioParameter = new Parameter("codiAplicacio", //$NON-NLS-1$
                codiAplicacio);
        Parameter nomParameter = new Parameter("nom", nom); //$NON-NLS-1$
        Parameter[] parameters = { codiAplicacioParameter, nomParameter };
        Collection dominis = getDominiAplicacioEntityDao().query(query,
                parameters);
        DominiAplicacioEntity dominiAplicacio = null;
        if (dominis != null) {
            try {
                dominiAplicacio = (DominiAplicacioEntity) dominis.iterator()
                        .next();
            } catch (Exception e) {
                // do nothing
            }
        }
        if (dominiAplicacio == null) {
			throw new SeyconException(String.format(Messages.getString("ValorDominiAplicacioEntityDaoImpl.domainNotFound"), //$NON-NLS-1$
					codiAplicacio, 
					nom));
        }
        target.setDomini(dominiAplicacio);
    }

    /**
     * @see es.caib.seycon.ng.model.ValorDominiAplicacioEntityDao#valorDominiToEntity(es.caib.seycon.ng.comu.ValorDomini,
     *      es.caib.seycon.ng.model.ValorDominiAplicacioEntity)
     */
    public void valorDominiToEntity(es.caib.seycon.ng.comu.ValorDomini source,
            es.caib.seycon.ng.model.ValorDominiAplicacioEntity target,
            boolean copyIfNull) {
        super.valorDominiToEntity(source, target, copyIfNull);
        valorDominiToEntityCustom(source, target);
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#find(int, java.lang.String,
     *      es.caib.seycon.ng.model.Parameter[])
     */
    public List<ValorDominiAplicacioEntity> find(
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
                if (obj instanceof ValorDominiAplicacioEntity) {
                    ValorDominiAplicacioEntity entity = (ValorDominiAplicacioEntity) obj;
                    this.create(entity); // cridem al mètode 1 per 1
                }
            }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof ValorDominiAplicacioEntity) {
                    ValorDominiAplicacioEntity entity = (ValorDominiAplicacioEntity) obj;
                    this.update(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof ValorDominiAplicacioEntity) {
                    ValorDominiAplicacioEntity entity = (ValorDominiAplicacioEntity) obj;
                    this.remove(entity);// cridem al mètode 1 per 1
                }
            }
    }
}
