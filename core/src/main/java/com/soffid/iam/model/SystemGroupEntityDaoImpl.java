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

import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.SystemEntity;

/**
 * @see es.caib.seycon.ng.model.GrupDispatcherEntity
 */
public class SystemGroupEntityDaoImpl
    extends com.soffid.iam.model.SystemGroupEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.GrupDispatcherEntityDao#toGrupDispatcher(es.caib.seycon.ng.model.GrupDispatcherEntity, es.caib.seycon.ng.comu.GrupDispatcher)
     */
    public void toSystemGroup(com.soffid.iam.model.SystemGroupEntity source, com.soffid.iam.api.SystemGroup target) {
        // @todo verify behavior of toGrupDispatcher
        super.toSystemGroup(source, target);
        if (source.getSystem() != null)
        	target.setSystemCode(source.getSystem().getName());
        if (source.getGroup() != null)
        	target.setGroupCode(source.getGroup().getName());
    }


    /**
     * @see es.caib.seycon.ng.model.GrupDispatcherEntityDao#toGrupDispatcher(es.caib.seycon.ng.model.GrupDispatcherEntity)
     */
    public com.soffid.iam.api.SystemGroup toSystemGroup(final com.soffid.iam.model.SystemGroupEntity entity) {
        // @todo verify behavior of toGrupDispatcher
        return super.toSystemGroup(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.SystemGroupEntity loadGrupDispatcherEntityFromGrupDispatcher(com.soffid.iam.api.SystemGroup grupDispatcher) {
        com.soffid.iam.model.SystemGroupEntity grupDispatcherEntity = null;
        
        if (grupDispatcher.getId() !=null) {
        	grupDispatcherEntity = this.load(grupDispatcher.getId());
        }
        if (grupDispatcherEntity == null)
        {
            grupDispatcherEntity = newSystemGroupEntity();
        }
        return grupDispatcherEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.GrupDispatcherEntityDao#grupDispatcherToEntity(es.caib.seycon.ng.comu.GrupDispatcher)
     */
    public com.soffid.iam.model.SystemGroupEntity systemGroupToEntity(com.soffid.iam.api.SystemGroup grupDispatcher) {
        // @todo verify behavior of grupDispatcherToEntity
        com.soffid.iam.model.SystemGroupEntity entity = this.loadGrupDispatcherEntityFromGrupDispatcher(grupDispatcher);
        this.systemGroupToEntity(grupDispatcher, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.GrupDispatcherEntityDao#grupDispatcherToEntity(es.caib.seycon.ng.comu.GrupDispatcher, es.caib.seycon.ng.model.GrupDispatcherEntity)
     */
	public void systemGroupToEntity(com.soffid.iam.api.SystemGroup source, com.soffid.iam.model.SystemGroupEntity target, boolean copyIfNull) {
		// @todo verify behavior of grupDispatcherToEntity
		super.systemGroupToEntity(source, target, copyIfNull);
		if (source.getId() != null)
			target.setId(source.getId());
		if (source.getSystemCode() != null) {
			SystemEntity agent = getSystemEntityDao().findByName(source.getSystemCode());
			if (agent != null)
				target.setSystem(agent);
		}
		if (source.getGroupCode() != null) {
			GroupEntity grup = getGroupEntityDao().findByName(source.getGroupCode());
			if (grup != null)
				target.setGroup(grup);
		}
	}

}
