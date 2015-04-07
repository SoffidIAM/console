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

import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.UserTypeEntity;

/**
 * @see es.caib.seycon.ng.model.TipusUsuariDispatcherEntity
 */
public class UserTypeSystemEntityDaoImpl
    extends com.soffid.iam.model.UserTypeSystemEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.TipusUsuariDispatcherEntityDao#toTipusUsuariDispatcher(es.caib.seycon.ng.model.TipusUsuariDispatcherEntity, es.caib.seycon.ng.comu.TipusUsuariDispatcher)
     */
    public void toTipusUsuariDispatcher(com.soffid.iam.model.UserTypeSystemEntity source, es.caib.seycon.ng.comu.TipusUsuariDispatcher target) {
        // @todo verify behavior of toTipusUsuariDispatcher
        super.toTipusUsuariDispatcher(source, target);
        
        if (source.getSystem() != null) {
        	target.setCodiDispatcher(source.getSystem().getCode());
        }
        if (source.getUserType() != null) {
        	target.setTipus(source.getUserType().getCode());
        }
    }


    /**
     * @see es.caib.seycon.ng.model.TipusUsuariDispatcherEntityDao#toTipusUsuariDispatcher(es.caib.seycon.ng.model.TipusUsuariDispatcherEntity)
     */
    public es.caib.seycon.ng.comu.TipusUsuariDispatcher toTipusUsuariDispatcher(final com.soffid.iam.model.UserTypeSystemEntity entity) {
        // @todo verify behavior of toTipusUsuariDispatcher
        return super.toTipusUsuariDispatcher(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.UserTypeSystemEntity loadTipusUsuariDispatcherEntityFromTipusUsuariDispatcher(es.caib.seycon.ng.comu.TipusUsuariDispatcher tipusUsuariDispatcher) {
        com.soffid.iam.model.UserTypeSystemEntity tipusUsuariDispatcherEntity = null; 
        if (tipusUsuariDispatcher.getId() !=null) {
        	tipusUsuariDispatcherEntity = this.load(tipusUsuariDispatcher.getId());
        }
        if (tipusUsuariDispatcherEntity == null)
        {
            tipusUsuariDispatcherEntity = newUserTypeSystemEntity();
        }
        return tipusUsuariDispatcherEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.TipusUsuariDispatcherEntityDao#tipusUsuariDispatcherToEntity(es.caib.seycon.ng.comu.TipusUsuariDispatcher)
     */
    public com.soffid.iam.model.UserTypeSystemEntity tipusUsuariDispatcherToEntity(es.caib.seycon.ng.comu.TipusUsuariDispatcher tipusUsuariDispatcher) {
        // @todo verify behavior of tipusUsuariDispatcherToEntity
        com.soffid.iam.model.UserTypeSystemEntity entity = this.loadTipusUsuariDispatcherEntityFromTipusUsuariDispatcher(tipusUsuariDispatcher);
        this.tipusUsuariDispatcherToEntity(tipusUsuariDispatcher, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.TipusUsuariDispatcherEntityDao#tipusUsuariDispatcherToEntity(es.caib.seycon.ng.comu.TipusUsuariDispatcher, es.caib.seycon.ng.model.TipusUsuariDispatcherEntity)
     */
    public void tipusUsuariDispatcherToEntity(es.caib.seycon.ng.comu.TipusUsuariDispatcher source, com.soffid.iam.model.UserTypeSystemEntity target, boolean copyIfNull) {

        super.tipusUsuariDispatcherToEntity(source, target, copyIfNull);
        
        if (source.getId()!=null) 
        	target.setId(source.getId());
        if (source.getTipus()!=null) {
        	UserTypeEntity tipusu = getUserTypeEntityDao().findByCode(source.getTipus());
        	if (tipusu !=null)
        		target.setUserType(tipusu);
        }
        if (source.getCodiDispatcher() !=null) {
        	SystemEntity agent = getSystemEntityDao().findByCode(source.getCodiDispatcher());
        	if (agent !=null) 
        		target.setSystem(agent);
        }
        
        
    }

}
