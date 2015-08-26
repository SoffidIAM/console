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
import com.soffid.iam.model.UserDomainEntity;
import es.caib.seycon.ng.model.*;
import es.caib.seycon.ng.utils.Security;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * @see es.caib.seycon.ng.model.DominiUsuariEntity
 */
public class UserDomainEntityDaoImpl
    extends com.soffid.iam.model.UserDomainEntityDaoBase
{
    private void audit(String accio, UserDomainEntity domain) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setUserDomain(domain.getName());
        auditoria.setAuthor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObject("SC_DOMUSU"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    /**
     * @see es.caib.seycon.ng.model.DominiUsuariEntityDao#toDominiUsuari(es.caib.seycon.ng.model.DominiUsuariEntity, es.caib.seycon.ng.comu.DominiUsuari)
     */
    public void toUserDomain(com.soffid.iam.model.UserDomainEntity source, com.soffid.iam.api.UserDomain target) {
        // @todo verify behavior of toDominiUsuari
        super.toUserDomain(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.DominiUsuariEntityDao#toDominiUsuari(es.caib.seycon.ng.model.DominiUsuariEntity)
     */
    public com.soffid.iam.api.UserDomain toUserDomain(final com.soffid.iam.model.UserDomainEntity entity) {
        // @todo verify behavior of toDominiUsuari
        return super.toUserDomain(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.UserDomainEntity loadDominiUsuariEntityFromDominiUsuari(com.soffid.iam.api.UserDomain dominiUsuari) {
		com.soffid.iam.model.UserDomainEntity dominiUsuariEntity = null;

		if (dominiUsuari.getId() != null) {
			dominiUsuariEntity = this.load(dominiUsuari.getId());
		}
		if (dominiUsuariEntity == null) {
			dominiUsuariEntity = newUserDomainEntity();
		}
		return dominiUsuariEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.DominiUsuariEntityDao#dominiUsuariToEntity(es.caib.seycon.ng.comu.DominiUsuari)
     */
    public com.soffid.iam.model.UserDomainEntity userDomainToEntity(com.soffid.iam.api.UserDomain dominiUsuari) {
        // @todo verify behavior of dominiUsuariToEntity
        com.soffid.iam.model.UserDomainEntity entity = this.loadDominiUsuariEntityFromDominiUsuari(dominiUsuari);
        this.userDomainToEntity(dominiUsuari, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.DominiUsuariEntityDao#dominiUsuariToEntity(es.caib.seycon.ng.comu.DominiUsuari, es.caib.seycon.ng.model.DominiUsuariEntity)
     */
    public void userDomainToEntity(com.soffid.iam.api.UserDomain source, com.soffid.iam.model.UserDomainEntity target, boolean copyIfNull) {
        // @todo verify behavior of dominiUsuariToEntity
        super.userDomainToEntity(source, target, copyIfNull);
    }

	@Override
    public void create(UserDomainEntity entity) {
		super.create(entity);
		audit ("C", entity); //$NON-NLS-1$
	}

	@Override
    public void update(UserDomainEntity entity) {
		super.update(entity);
		audit ("U", entity); //$NON-NLS-1$
	}

	@Override
    public void remove(UserDomainEntity entity) {
		audit ("D", entity); //$NON-NLS-1$
		super.remove(entity);
	}

}