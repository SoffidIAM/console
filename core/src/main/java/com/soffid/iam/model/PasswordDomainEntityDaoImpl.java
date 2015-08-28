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
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.model.*;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.springframework.dao.InvalidDataAccessResourceUsageException;

/**
 * @see es.caib.seycon.ng.model.DominiContrasenyaEntity
 */
public class PasswordDomainEntityDaoImpl
    extends com.soffid.iam.model.PasswordDomainEntityDaoBase
{

    private void audit(String accio, PasswordDomainEntity domain) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setPasswordDomain(domain.getName());
        auditoria.setAuthor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObject("SC_DOMCON"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }


	@Override
    public PasswordDomainEntity findDefaultDomain(String queryString, CriteriaSearchConfiguration criteria, long userId) {
    	try {
    		return super.findDefaultDomain(queryString, criteria, userId);
	    } catch (InvalidDataAccessResourceUsageException e) {
	    	// More than one domain
	        PasswordDomainEntity dcEntity = findByName(criteria, "DEFAULT"); //$NON-NLS-1$
	        if (dcEntity == null)
	        {
	        	SystemEntity dispatcher = getSystemEntityDao().findByName("soffid"); //$NON-NLS-1$
	        	if (dispatcher != null)
	        		dcEntity = dispatcher.getPasswordDomain();
	        }
	        if (dcEntity == null)
	        {
	        	SystemEntity dispatcher = getSystemEntityDao().findByName("seu"); //$NON-NLS-1$
	        	if (dispatcher != null)
	        		dcEntity = dispatcher.getPasswordDomain();
	        }
	        if (dcEntity == null)
	        	logger.warn(String.format(Messages.getString("PasswordDomainEntityDaoImpl.MoreThanOnePassword"), userId)); //$NON-NLS-1$
	        return dcEntity;
	    }
	}


	/**
     * @see es.caib.seycon.ng.model.DominiContrasenyaEntityDao#toDominiContrasenya(es.caib.seycon.ng.model.DominiContrasenyaEntity, es.caib.seycon.ng.comu.DominiContrasenya)
     */
    public void toPasswordDomain(com.soffid.iam.model.PasswordDomainEntity source, com.soffid.iam.api.PasswordDomain target) {
        // @todo verify behavior of toDominiContrasenya
        super.toPasswordDomain(source, target);
                	
    }


    @Override
    public void create(PasswordDomainEntity entity) {
		super.create(entity);
    	audit ("C", entity); //$NON-NLS-1$
	}


	@Override
    public void update(PasswordDomainEntity entity) {
		super.update(entity);
    	audit ("U", entity); //$NON-NLS-1$
	}


	@Override
    public void remove(PasswordDomainEntity entity) {
    	audit ("D", entity); //$NON-NLS-1$
		super.remove(entity);
	}


	/**
     * @see es.caib.seycon.ng.model.DominiContrasenyaEntityDao#toDominiContrasenya(es.caib.seycon.ng.model.DominiContrasenyaEntity)
     */
    public com.soffid.iam.api.PasswordDomain toPasswordDomain(final com.soffid.iam.model.PasswordDomainEntity entity) {
        // @todo verify behavior of toDominiContrasenya
        return super.toPasswordDomain(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.PasswordDomainEntity loadDominiContrasenyaEntityFromDominiContrasenya(com.soffid.iam.api.PasswordDomain dominiContrasenya) {
        com.soffid.iam.model.PasswordDomainEntity dominiContrasenyaEntity = null; 
        if (dominiContrasenya.getId() !=null) {
        	dominiContrasenyaEntity = this.load(dominiContrasenya.getId());
        }
        if (dominiContrasenyaEntity == null)
        {
            dominiContrasenyaEntity = newPasswordDomainEntity();
        }
        return dominiContrasenyaEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.DominiContrasenyaEntityDao#dominiContrasenyaToEntity(es.caib.seycon.ng.comu.DominiContrasenya)
     */
    public com.soffid.iam.model.PasswordDomainEntity passwordDomainToEntity(com.soffid.iam.api.PasswordDomain dominiContrasenya) {
        // @todo verify behavior of dominiContrasenyaToEntity
        com.soffid.iam.model.PasswordDomainEntity entity = this.loadDominiContrasenyaEntityFromDominiContrasenya(dominiContrasenya);
        this.passwordDomainToEntity(dominiContrasenya, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.DominiContrasenyaEntityDao#dominiContrasenyaToEntity(es.caib.seycon.ng.comu.DominiContrasenya, es.caib.seycon.ng.model.DominiContrasenyaEntity)
     */
    public void passwordDomainToEntity(com.soffid.iam.api.PasswordDomain source, com.soffid.iam.model.PasswordDomainEntity target, boolean copyIfNull) {
        // @todo verify behavior of dominiContrasenyaToEntity
        super.passwordDomainToEntity(source, target, copyIfNull);
    }

}