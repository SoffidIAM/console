//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import es.caib.seycon.ng.comu.SoffidObjectTrigger;
import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.api.ObjectMapping;
import com.soffid.iam.api.SoffidObjectType;

/**
 * DAO ObjectMappingEntity implementation
 */
public class ObjectMappingEntityDaoImpl extends com.soffid.iam.model.ObjectMappingEntityDaoBase
{

	@Override
    public void toObjectMapping(com.soffid.iam.model.ObjectMappingEntity source, ObjectMapping target) {
		super.toObjectMapping(source, target);
		target.setDispatcherId(source.getSystem() == null ? null : source.getSystem().getId());
		target.setSoffidCustomObject(source.getSoffidObject().equals(SoffidObjectType.OBJECT_CUSTOM) &&
				source.getSoffidCustomObject() != null ?
			source.getSoffidCustomObject().getName():
			null);
	}

	@Override
    public void objectMappingToEntity(ObjectMapping source, com.soffid.iam.model.ObjectMappingEntity target, boolean copyIfNull) {
		super.objectMappingToEntity(source, target, copyIfNull);
		SystemEntity dispatcher = getSystemEntityDao().load(source.getDispatcherId());
		target.setSystem(dispatcher);
		if (source.getSoffidObject() == SoffidObjectType.OBJECT_CUSTOM)
		{
			CustomObjectTypeEntity co = getCustomObjectTypeEntityDao().findByName(source.getSoffidCustomObject());
			if (co == null)
				throw new IllegalArgumentException("Wrong custom object type "+source.getSoffidCustomObject());
			target.setSoffidCustomObject(co);
		}
		else
			target.setSoffidCustomObject(null);
	}
}
