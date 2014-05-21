//
// (C) 2013 Soffid
//
//

package es.caib.seycon.ng.model;

import es.caib.seycon.ng.comu.AttributeMapping;

/**
 * DAO AttributeMappingEntity implementation
 */
public class AttributeMappingEntityDaoImpl extends AttributeMappingEntityDaoBase
{

	@Override
	public void toAttributeMapping (AttributeMappingEntity source,
					AttributeMapping target)
	{
		super.toAttributeMapping(source, target);
		
		target.setObjectId(source.getObject().getId());
	}

	@Override
	public void attributeMappingToEntity (AttributeMapping source,
					AttributeMappingEntity target, boolean copyIfNull)
	{
		super.attributeMappingToEntity(source, target, copyIfNull);
		target.setObject(getObjectMappingEntityDao().load(source.getObjectId()));
	}
	
	
}
