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
    public void toGrupDispatcher(com.soffid.iam.model.SystemGroupEntity source, es.caib.seycon.ng.comu.GrupDispatcher target) {
        // @todo verify behavior of toGrupDispatcher
        super.toGrupDispatcher(source, target);
        if (source.getSystem() != null)
        	target.setCodiDispatcher(source.getSystem().getName());
        if (source.getGroup() != null)
        	target.setCodiGrup(source.getGroup().getName());
    }


    /**
     * @see es.caib.seycon.ng.model.GrupDispatcherEntityDao#toGrupDispatcher(es.caib.seycon.ng.model.GrupDispatcherEntity)
     */
    public es.caib.seycon.ng.comu.GrupDispatcher toGrupDispatcher(final com.soffid.iam.model.SystemGroupEntity entity) {
        // @todo verify behavior of toGrupDispatcher
        return super.toGrupDispatcher(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.SystemGroupEntity loadGrupDispatcherEntityFromGrupDispatcher(es.caib.seycon.ng.comu.GrupDispatcher grupDispatcher) {
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
    public com.soffid.iam.model.SystemGroupEntity grupDispatcherToEntity(es.caib.seycon.ng.comu.GrupDispatcher grupDispatcher) {
        // @todo verify behavior of grupDispatcherToEntity
        com.soffid.iam.model.SystemGroupEntity entity = this.loadGrupDispatcherEntityFromGrupDispatcher(grupDispatcher);
        this.grupDispatcherToEntity(grupDispatcher, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.GrupDispatcherEntityDao#grupDispatcherToEntity(es.caib.seycon.ng.comu.GrupDispatcher, es.caib.seycon.ng.model.GrupDispatcherEntity)
     */
	public void grupDispatcherToEntity(es.caib.seycon.ng.comu.GrupDispatcher source, com.soffid.iam.model.SystemGroupEntity target, boolean copyIfNull) {
		// @todo verify behavior of grupDispatcherToEntity
		super.grupDispatcherToEntity(source, target, copyIfNull);
		if (source.getId() != null)
			target.setId(source.getId());
		if (source.getCodiDispatcher() != null) {
			SystemEntity agent = getSystemEntityDao().findByName(source.getCodiDispatcher());
			if (agent != null)
				target.setSystem(agent);
		}
		if (source.getCodiGrup() != null) {
			GroupEntity grup = getGroupEntityDao().findByName(source.getCodiGrup());
			if (grup != null)
				target.setGroup(grup);
		}
	}

}
