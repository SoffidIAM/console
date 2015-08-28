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
import com.soffid.iam.model.UserTypeEntity;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.model.*;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * @see es.caib.seycon.ng.model.TipusUsuariEntity
 */
public class UserTypeEntityDaoImpl
    extends com.soffid.iam.model.UserTypeEntityDaoBase
{
    private void audit(String accio, UserTypeEntity type) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setUserType(type.getName());
        auditoria.setAuthor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObject("SC_TIPUSU"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }
    /**
     * @see es.caib.seycon.ng.model.TipusUsuariEntityDao#toTipusUsuari(es.caib.seycon.ng.model.TipusUsuariEntity, es.caib.seycon.ng.comu.TipusUsuari)
     */
    public void toUserType(com.soffid.iam.model.UserTypeEntity source, com.soffid.iam.api.UserType target) {
        // @todo verify behavior of toTipusUsuari
        super.toUserType(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.TipusUsuariEntityDao#toTipusUsuari(es.caib.seycon.ng.model.TipusUsuariEntity)
     */
    public com.soffid.iam.api.UserType toUserType(final com.soffid.iam.model.UserTypeEntity entity) {
        // @todo verify behavior of toTipusUsuari
        return super.toUserType(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.UserTypeEntity loadTipusUsuariEntityFromTipusUsuari(com.soffid.iam.api.UserType tipusUsuari) {
		com.soffid.iam.model.UserTypeEntity tipusUsuariEntity = null;
		if (tipusUsuari.getId() != null) {
			tipusUsuariEntity = this.load(tipusUsuari.getId());
		}
		if (tipusUsuariEntity == null) {
			tipusUsuariEntity = newUserTypeEntity();
		}
		return tipusUsuariEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.TipusUsuariEntityDao#tipusUsuariToEntity(es.caib.seycon.ng.comu.TipusUsuari)
     */
    public com.soffid.iam.model.UserTypeEntity userTypeToEntity(com.soffid.iam.api.UserType tipusUsuari) {
        // @todo verify behavior of tipusUsuariToEntity
        com.soffid.iam.model.UserTypeEntity entity = this.loadTipusUsuariEntityFromTipusUsuari(tipusUsuari);
        this.userTypeToEntity(tipusUsuari, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.TipusUsuariEntityDao#tipusUsuariToEntity(es.caib.seycon.ng.comu.TipusUsuari, es.caib.seycon.ng.model.TipusUsuariEntity)
     */
    public void userTypeToEntity(com.soffid.iam.api.UserType source, com.soffid.iam.model.UserTypeEntity target, boolean copyIfNull) {
        // @todo verify behavior of tipusUsuariToEntity
        super.userTypeToEntity(source, target, copyIfNull);
    }
	@Override
    public void create(UserTypeEntity entity) {
		super.create(entity);
		audit ("C", entity); //$NON-NLS-1$
	}
	@Override
    public void update(UserTypeEntity entity) {
		super.update(entity);
		audit ("U", entity); //$NON-NLS-1$
	}
	@Override
    public void remove(UserTypeEntity entity) {
		audit ("D", entity); //$NON-NLS-1$
		super.remove(entity);
	}

}