//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.api.ObjectMappingProperty;

/**
 * DAO ObjectMappingPropertyEntity implementation
 */
public class ObjectMappingPropertyEntityDaoImpl extends com.soffid.iam.model.ObjectMappingPropertyEntityDaoBase
{
	@Override
    public void toObjectMappingProperty(com.soffid.iam.model.ObjectMappingPropertyEntity source, ObjectMappingProperty target) {
		super.toObjectMappingProperty(source, target);
		target.setObjectId(source.getObject() == null? null: source.getObject().getId());
	}

	@Override
    public void objectMappingPropertyToEntity(ObjectMappingProperty source, com.soffid.iam.model.ObjectMappingPropertyEntity target, boolean copyIfNull) {
		super.objectMappingPropertyToEntity(source, target, copyIfNull);
		com.soffid.iam.model.ObjectMappingEntity object = getObjectMappingEntityDao().load(source.getObjectId());
		target.setObject(object);
	}
}
