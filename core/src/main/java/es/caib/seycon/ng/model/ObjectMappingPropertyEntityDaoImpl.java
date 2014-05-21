//
// (C) 2013 Soffid
//
//

package es.caib.seycon.ng.model;

import es.caib.seycon.ng.comu.ObjectMapping;
import es.caib.seycon.ng.comu.ObjectMappingProperty;

/**
 * DAO ObjectMappingPropertyEntity implementation
 */
public class ObjectMappingPropertyEntityDaoImpl extends ObjectMappingPropertyEntityDaoBase
{
	@Override
	public void toObjectMappingProperty (ObjectMappingPropertyEntity source, ObjectMappingProperty target)
	{
		super.toObjectMappingProperty(source, target);
		target.setObjectId(source.getObject() == null? null: source.getObject().getId());
	}

	@Override
	public void objectMappingPropertyToEntity (ObjectMappingProperty source, ObjectMappingPropertyEntity target,
					boolean copyIfNull)
	{
		super.objectMappingPropertyToEntity(source, target, copyIfNull);
		ObjectMappingEntity object = getObjectMappingEntityDao().load(source.getObjectId());
		target.setObject(object);
	}
}
