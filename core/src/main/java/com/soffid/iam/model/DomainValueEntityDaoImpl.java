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
import com.soffid.iam.api.Domain;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.ApplicationDomainEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
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
import java.util.List;

/**
 * @see es.caib.seycon.ng.model.ValorDominiAplicacioEntity
 */
public class DomainValueEntityDaoImpl extends
        com.soffid.iam.model.DomainValueEntityDaoBase {

    private void auditarValorDominiAplicacio(String accio,
            String codiAplicacio, String nomDomini, String valorDomini) {
        String codiUsuari = Security.getCurrentAccount(); //$NON-NLS-1$
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setApplication(codiAplicacio);
        auditoria.setDomain(nomDomini);
        auditoria.setDomainValue(valorDomini);
        auditoria.setAuthor(codiUsuari);
        auditoria.setObject("SC_VALDOMAPP"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    public void create(com.soffid.iam.model.DomainValueEntity valorDomini) throws RuntimeException {
        try {
            super.create(valorDomini);
            getSession(false).flush();
            String codiAplicacio = null;
            ApplicationDomainEntity domini = valorDomini.getDomain();
            if (domini != null) {
                InformationSystemEntity aplicacio = domini.getInformationSystem();
                if (aplicacio != null) {
                    codiAplicacio = aplicacio.getName();
                }
            }
            String nomDomini = valorDomini.getDomain().getName();
            String valorDominiString = valorDomini.getValue();
            auditarValorDominiAplicacio("C", codiAplicacio, nomDomini, //$NON-NLS-1$
                    valorDominiString);
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("DomainValueEntityDaoImpl.errorCreating"), valorDomini.getValue(), message));
        }
    }

    public void update(com.soffid.iam.model.DomainValueEntity valorDomini) throws RuntimeException {
        try {
            super.update(valorDomini);
            getSession(false).flush();
            String codiAplicacio = null;
            ApplicationDomainEntity domini = valorDomini.getDomain();
            if (domini != null) {
                InformationSystemEntity aplicacio = domini.getInformationSystem();
                if (aplicacio != null) {
                    codiAplicacio = aplicacio.getName();
                }
            }
            String nomDomini = valorDomini.getDomain().getName();
            String valorDominiString = valorDomini.getValue();
            auditarValorDominiAplicacio("U", codiAplicacio, nomDomini, //$NON-NLS-1$
                    valorDominiString);
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("DomainValueEntityDaoImpl.errorUpdating"), valorDomini.getValue(), message));
        }
    }

    public void remove(com.soffid.iam.model.DomainValueEntity valorDomini) throws RuntimeException {
        try {
            String codiAplicacio = null;
            ApplicationDomainEntity domini = valorDomini.getDomain();
            if (domini != null) {
                InformationSystemEntity aplicacio = domini.getInformationSystem();
                if (aplicacio != null) {
                    codiAplicacio = aplicacio.getName();
                }
            }
            String nomDomini = valorDomini.getDomain().getName();
            String valorDominiString = valorDomini.getValue();
            super.remove(valorDomini);
            getSession(false).flush();
            auditarValorDominiAplicacio("D", codiAplicacio, nomDomini, //$NON-NLS-1$
                    valorDominiString);
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("DomainValueEntityDaoImpl.errorCreating"), valorDomini.getValue(), message));
        }
    }

    public void toDomainValue(com.soffid.iam.model.DomainValueEntity source, com.soffid.iam.api.DomainValue target) {
        super.toDomainValue(source, target);
        target.setDescription(source.getDescription());
        ApplicationDomainEntity dominiEntity = source.getDomain();
        Domain domini = getApplicationDomainEntityDao().toDomain(dominiEntity);
        if (domini == null) {
            // el domini no és d'aplicació
            throw new SeyconException(Messages.getString("DomainValueEntityDaoImpl.invalidDomain")); //$NON-NLS-1$
        } else {
            target.setDomainName(domini.getName());
            target.setValue(source.getValue());
            target.setExternalCodeDomain(domini.getExternalCode());
            target.setDescription(source.getDescription());
        }
    }

    /**
     * @see es.caib.seycon.ng.model.ValorDominiAplicacioEntityDao#toValorDomini(es.caib.seycon.ng.model.ValorDominiAplicacioEntity)
     */
    public com.soffid.iam.api.DomainValue toDomainValue(final com.soffid.iam.model.DomainValueEntity entity) {
        try {
            return super.toDomainValue(entity);
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
    private com.soffid.iam.model.DomainValueEntity loadValorDominiAplicacioEntityFromValorDomini(com.soffid.iam.api.DomainValue valorDomini) {
        DomainValueEntity valorDominiAplicacioEntity = null;
        if (valorDomini.getId() != null) {
            valorDominiAplicacioEntity = this.load(valorDomini.getId());
        }
        if (valorDominiAplicacioEntity == null) {
            valorDominiAplicacioEntity = newDomainValueEntity();
        }
        return valorDominiAplicacioEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.ValorDominiAplicacioEntityDao#valorDominiToEntity(es.caib.seycon.ng.comu.ValorDomini)
     */
    public com.soffid.iam.model.DomainValueEntity domainValueToEntity(com.soffid.iam.api.DomainValue valorDomini) {
        com.soffid.iam.model.DomainValueEntity entity = this.loadValorDominiAplicacioEntityFromValorDomini(valorDomini);
        this.domainValueToEntity(valorDomini, entity, true);
        return entity;
    }

    public void valorDominiToEntityCustom(com.soffid.iam.api.DomainValue source, com.soffid.iam.model.DomainValueEntity target) {
        target.setDescription(source.getDescription());
        target.setValue(source.getValue());
        String nom = source.getDomainName();
        String codiAplicacio = source.getExternalCodeDomain();
        String query = "select domini " //$NON-NLS-1$
                + "from com.soffid.iam.model.ApplicationDomainEntity domini " //$NON-NLS-1$
                + "join domini.informationSystem aplicacio " //$NON-NLS-1$
                + "where aplicacio.tenant.id=:tenantId and " //$NON-NLS-1$
                + "aplicacio.name = :codiAplicacio and " //$NON-NLS-1$
                + "domini.name = :nom"; //$NON-NLS-1$
        Parameter codiAplicacioParameter = new Parameter("codiAplicacio", //$NON-NLS-1$
                codiAplicacio);
        Parameter nomParameter = new Parameter("nom", nom); //$NON-NLS-1$
        Parameter[] parameters = { codiAplicacioParameter, nomParameter,
        		new Parameter("tenantId", Security.getCurrentTenantId())};
        Collection dominis = getApplicationDomainEntityDao().query(query, parameters);
        ApplicationDomainEntity dominiAplicacio = null;
        if (dominis != null) {
            try {
                dominiAplicacio = (ApplicationDomainEntity) dominis.iterator().next();
            } catch (Exception e) {
                // do nothing
            }
        }
        if (dominiAplicacio == null) {
			throw new SeyconException(String.format(Messages.getString("DomainValueEntityDaoImpl.domainNotFound"), //$NON-NLS-1$
					codiAplicacio, 
					nom));
        }
        target.setDomain(dominiAplicacio);
    }

    /**
     * @see es.caib.seycon.ng.model.ValorDominiAplicacioEntityDao#valorDominiToEntity(es.caib.seycon.ng.comu.ValorDomini,
     *      es.caib.seycon.ng.model.ValorDominiAplicacioEntity)
     */
    public void domainValueToEntity(com.soffid.iam.api.DomainValue source, com.soffid.iam.model.DomainValueEntity target, boolean copyIfNull) {
        super.domainValueToEntity(source, target, copyIfNull);
        valorDominiToEntityCustom(source, target);
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof DomainValueEntity) {
                DomainValueEntity entity = (DomainValueEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof DomainValueEntity) {
                DomainValueEntity entity = (DomainValueEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof DomainValueEntity) {
                DomainValueEntity entity = (DomainValueEntity) obj;
                this.remove(entity);
            }
        }
    }
	@Override
	public Collection<DomainValueEntity> findByText(CriteriaSearchConfiguration criteria, Domain domain, String text) {
		String[] split = ScimHelper.split(text);
		Parameter[] params = new Parameter[split.length + 3];
		
		StringBuffer sb = new StringBuffer("select u "
				+ "from com.soffid.iam.model.DomainValueEntity as u "
				+ "join u.domain as domain "
				+ "join domain.informationSystem as s "
				+ "where s.tenant.id = :tenantId and s.name=:appName and domain.name=:domainName");
		params[0] = new Parameter("tenantId", Security.getCurrentTenantId());
		params[1] = new Parameter("appName", domain.getExternalCode());
		params[2] = new Parameter("domainName",domain.getName());
		for (int i = 0; i < split.length; i++)
		{
			sb.append(" and ");
			params[i+3] = new Parameter("param"+i, "%"+split[i].toUpperCase()+"%");
			sb.append("(upper(u.value) like :param")
				.append(i)
				.append(" or upper(u.description) like :param")
				.append(i)
				.append(")");
		}
		return query(sb.toString(), params);
	}
}
