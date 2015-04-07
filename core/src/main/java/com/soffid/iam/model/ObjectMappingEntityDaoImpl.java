//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.SystemEntity;
import es.caib.seycon.ng.comu.ObjectMapping;

/**
 * DAO ObjectMappingEntity implementation
 */
public class ObjectMappingEntityDaoImpl extends com.soffid.iam.model.ObjectMappingEntityDaoBase
{

	@Override
    public void toObjectMapping(com.soffid.iam.model.ObjectMappingEntity source, ObjectMapping target) {
		super.toObjectMapping(source, target);
		target.setDispatcherId(source.getSystem() == null ? null : source.getSystem().getId());
	}

	@Override
    public void objectMappingToEntity(ObjectMapping source, com.soffid.iam.model.ObjectMappingEntity target, boolean copyIfNull) {
		super.objectMappingToEntity(source, target, copyIfNull);
		SystemEntity dispatcher = getSystemEntityDao().load(source.getDispatcherId());
		target.setSystem(dispatcher);
	}
}
