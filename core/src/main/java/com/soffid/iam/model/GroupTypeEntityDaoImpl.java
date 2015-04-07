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

import com.soffid.iam.model.GroupTypeEntity;
import es.caib.seycon.ng.comu.TipusUnitatOrganitzativa;
import es.caib.seycon.ng.exception.SeyconException;

/**
 * @author u91940
 * @see es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity
 */
public class GroupTypeEntityDaoImpl
    extends com.soffid.iam.model.GroupTypeEntityDaoBase
{

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.GroupTypeEntity loadTipusUnitatOrganitzativaEntityFromTipusUnitatOrganitzativa(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa tipusUnitatOrganitzativa) {
		GroupTypeEntity tipusUnitatOrganitzativaEntity = null;
		if (tipusUnitatOrganitzativa.getId() != null) {
			tipusUnitatOrganitzativaEntity = load(tipusUnitatOrganitzativa.getId());
		}
		if (tipusUnitatOrganitzativaEntity == null) {
			return newGroupTypeEntity();
		}
		return tipusUnitatOrganitzativaEntity;
	}
	
	
	public GroupTypeEntity tipusUnitatOrganitzativaToEntity(TipusUnitatOrganitzativa tipusUnitatOrganitzativa) {
		GroupTypeEntity entity = loadTipusUnitatOrganitzativaEntityFromTipusUnitatOrganitzativa(tipusUnitatOrganitzativa);
		tipusUnitatOrganitzativaToEntity(tipusUnitatOrganitzativa, entity, true);
		return entity;
		
	}


	public void remove(GroupTypeEntity tipusUnitatOrganitzativaEntity) {
		
		if (tipusUnitatOrganitzativaEntity.getGroupEntities().size() != 0) {
			throw new SeyconException(String.format(Messages.getString("GroupTypeEntityDaoImpl.0"), tipusUnitatOrganitzativaEntity.getGroupEntities().size()));
		}		
		super.remove(tipusUnitatOrganitzativaEntity);
	}
	
	
	
	
}