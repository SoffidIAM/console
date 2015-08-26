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
    public void toUserTypeDispatcher(com.soffid.iam.model.UserTypeSystemEntity source, com.soffid.iam.api.UserTypeDispatcher target) {
        // @todo verify behavior of toTipusUsuariDispatcher
        super.toUserTypeDispatcher(source, target);
        
        if (source.getSystem() != null) {
        	target.setDispatcherCode(source.getSystem().getName());
        }
        if (source.getUserType() != null) {
        	target.setType(source.getUserType().getName());
        }
    }


    /**
     * @see es.caib.seycon.ng.model.TipusUsuariDispatcherEntityDao#toTipusUsuariDispatcher(es.caib.seycon.ng.model.TipusUsuariDispatcherEntity)
     */
    public com.soffid.iam.api.UserTypeDispatcher toUserTypeDispatcher(final com.soffid.iam.model.UserTypeSystemEntity entity) {
        // @todo verify behavior of toTipusUsuariDispatcher
        return super.toUserTypeDispatcher(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.UserTypeSystemEntity loadTipusUsuariDispatcherEntityFromTipusUsuariDispatcher(com.soffid.iam.api.UserTypeDispatcher tipusUsuariDispatcher) {
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
    public com.soffid.iam.model.UserTypeSystemEntity userTypeDispatcherToEntity(com.soffid.iam.api.UserTypeDispatcher tipusUsuariDispatcher) {
        // @todo verify behavior of tipusUsuariDispatcherToEntity
        com.soffid.iam.model.UserTypeSystemEntity entity = this.loadTipusUsuariDispatcherEntityFromTipusUsuariDispatcher(tipusUsuariDispatcher);
        this.userTypeDispatcherToEntity(tipusUsuariDispatcher, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.TipusUsuariDispatcherEntityDao#tipusUsuariDispatcherToEntity(es.caib.seycon.ng.comu.TipusUsuariDispatcher, es.caib.seycon.ng.model.TipusUsuariDispatcherEntity)
     */
    public void userTypeDispatcherToEntity(com.soffid.iam.api.UserTypeDispatcher source, com.soffid.iam.model.UserTypeSystemEntity target, boolean copyIfNull) {

        super.userTypeDispatcherToEntity(source, target, copyIfNull);
        
        if (source.getId()!=null) 
        	target.setId(source.getId());
        if (source.getType() != null) {
        	UserTypeEntity tipusu = getUserTypeEntityDao().findByName(source.getType());
        	if (tipusu !=null)
        		target.setUserType(tipusu);
        }
        if (source.getDispatcherCode() != null) {
        	SystemEntity agent = getSystemEntityDao().findByName(source.getDispatcherCode());
        	if (agent !=null) 
        		target.setSystem(agent);
        }
        
        
    }

}
