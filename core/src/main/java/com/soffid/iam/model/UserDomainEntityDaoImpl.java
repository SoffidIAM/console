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
import com.soffid.iam.model.UserDomainEntity;
import es.caib.seycon.ng.comu.Auditoria;
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
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setUserDomain(domain.getCode());
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_DOMUSU"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    /**
     * @see es.caib.seycon.ng.model.DominiUsuariEntityDao#toDominiUsuari(es.caib.seycon.ng.model.DominiUsuariEntity, es.caib.seycon.ng.comu.DominiUsuari)
     */
    public void toDominiUsuari(com.soffid.iam.model.UserDomainEntity source, es.caib.seycon.ng.comu.DominiUsuari target) {
        // @todo verify behavior of toDominiUsuari
        super.toDominiUsuari(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.DominiUsuariEntityDao#toDominiUsuari(es.caib.seycon.ng.model.DominiUsuariEntity)
     */
    public es.caib.seycon.ng.comu.DominiUsuari toDominiUsuari(final com.soffid.iam.model.UserDomainEntity entity) {
        // @todo verify behavior of toDominiUsuari
        return super.toDominiUsuari(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.UserDomainEntity loadDominiUsuariEntityFromDominiUsuari(es.caib.seycon.ng.comu.DominiUsuari dominiUsuari) {
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
    public com.soffid.iam.model.UserDomainEntity dominiUsuariToEntity(es.caib.seycon.ng.comu.DominiUsuari dominiUsuari) {
        // @todo verify behavior of dominiUsuariToEntity
        com.soffid.iam.model.UserDomainEntity entity = this.loadDominiUsuariEntityFromDominiUsuari(dominiUsuari);
        this.dominiUsuariToEntity(dominiUsuari, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.DominiUsuariEntityDao#dominiUsuariToEntity(es.caib.seycon.ng.comu.DominiUsuari, es.caib.seycon.ng.model.DominiUsuariEntity)
     */
    public void dominiUsuariToEntity(es.caib.seycon.ng.comu.DominiUsuari source, com.soffid.iam.model.UserDomainEntity target, boolean copyIfNull) {
        // @todo verify behavior of dominiUsuariToEntity
        super.dominiUsuariToEntity(source, target, copyIfNull);
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