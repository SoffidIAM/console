//
// (C) 2013 Soffid
//
//

package es.caib.seycon.ng.model;

import es.caib.seycon.ng.comu.ObjectMapping;

/**
 * DAO ObjectMappingEntity implementation
 */
public class ObjectMappingEntityDaoImpl extends ObjectMappingEntityDaoBase
{

	@Override
	public void toObjectMapping (ObjectMappingEntity source, ObjectMapping target)
	{
		super.toObjectMapping(source, target);
		target.setDispatcherId(source.getDispatcher() == null? null: source.getDispatcher().getId());
	}

	@Override
	public void objectMappingToEntity (ObjectMapping source, ObjectMappingEntity target,
					boolean copyIfNull)
	{
		super.objectMappingToEntity(source, target, copyIfNull);
		DispatcherEntity dispatcher = getDispatcherEntityDao().load(source.getDispatcherId());
		target.setDispatcher(dispatcher);
	}
}
